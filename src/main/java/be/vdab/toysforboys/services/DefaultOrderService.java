package be.vdab.toysforboys.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import be.vdab.toysforboys.entities.Order;
import be.vdab.toysforboys.entities.exceptions.OnvoldoendeVoorraadInStockException;
import be.vdab.toysforboys.repositories.OrderRepository;

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

//	@Override
//	@Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED)
//	public int setAsShipped(Long[] ids) {
//		return repository.setAsShipped(ids);
//	}

	@Override
	public int setAsShipped(Long id) {
		return repository.setAsShipped(id);
	}

	@Override
	@Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED)
	public void UpdateInOrderEnInStock(Long id) {
		repository.UpdateInOrderEnInStock(id);

	}
//
//	@Override
//	@Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
//	public void setAsShippedEnUpdateStock(Long[] ids) {
//		repository.setAsShipped(ids);
//
//		for (Long id : ids) {
//			repository.UpdateInOrderEnInStock(id);
//		}
//	}

	@Override
	@Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
	public void setAsShippedEnUpdateStock(Long id) {
		repository.setAsShipped(id);
		repository.UpdateInOrderEnInStock(id);
	}
}
