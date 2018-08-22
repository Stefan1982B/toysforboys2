package be.vdab.toysforboys.services;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import be.vdab.toysforboys.entities.Order;
import be.vdab.toysforboys.entities.exceptions.OnvoldoendeVoorraadInStockException;
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
	public List<Order> findSelectedIds(Long[] selectedIds) {
		return repository.findSelectedIds(selectedIds);
	}

	@Override
	@Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED)
	public void SetAsShippedAndUpdateStock(Order order) {
		Set<Orderdetail> orderdetails = order.getOrderdetails();
		long quantityOrdered = 0;
		for (Orderdetail orderdetail : orderdetails) {
			quantityOrdered = orderdetail.getQuantityOrdered();
			orderdetail.getProduct().updateQuantityInStock(quantityOrdered);
			orderdetail.getProduct().updateQuantityInStock(quantityOrdered);
//			if (orderdetail.getProduct().updateQuantityInStock(quantityOrdered)) {
//				orderdetail.getProduct().updateQuantityInOrder(quantityOrdered);
//			}
//			else if(! orderdetail.getProduct().updateQuantityInStock(quantityOrdered)){
//				throw new OnvoldoendeVoorraadInStockException();
//			} 
		}
		order.updateStatusToShipped();
		order.updateShippedDate();

	}

//	@Override
//	public int setAsShipped(Long id) {
//		return repository.setAsShipped(id);
//	}
//
//	@Override
//	@Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED)
//	public void UpdateInOrderEnInStock(Long id) {
//		repository.UpdateInOrderEnInStock(id);
//
//	}
//
//	@Override
//	@Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
//	public void setAsShippedEnUpdateStock(Long id) {
//		repository.setAsShipped(id);
//		repository.UpdateInOrderEnInStock(id);
//	}

}
