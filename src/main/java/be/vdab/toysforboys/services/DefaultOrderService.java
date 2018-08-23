package be.vdab.toysforboys.services;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import be.vdab.toysforboys.entities.Order;
import be.vdab.toysforboys.repositories.OrderRepository;
import be.vdab.toysforboys.valueobjects.Orderdetail;

@Service
@Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED)
class DefaultOrderService implements OrderService {

	private final OrderRepository repository;

	DefaultOrderService(OrderRepository repository) {
		this.repository = repository;
	}

	@Override
	public Optional<Order> read(long id) {
		return repository.read(id);
	}

	@Override
	public List<Order> findAllButCancelledAndShipped() {
		return repository.findAllButCancelledAndShipped();
	}

	@Override
	@Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED)
	public void SetAsShippedAndUpdateStock(Order order) {
		Set<Orderdetail> orderdetails = order.getOrderdetails();
		for (Orderdetail orderdetail : orderdetails) {
			long quantityOrdered = orderdetail.getQuantityOrdered();
			orderdetail.getProduct().updateQuantityInStock(quantityOrdered);
			orderdetail.getProduct().updateQuantityInOrder(quantityOrdered);
		}
		order.updateStatusToShipped();
		order.updateShippedDate();
	}

}
