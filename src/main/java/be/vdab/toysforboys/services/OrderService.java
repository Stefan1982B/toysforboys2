package be.vdab.toysforboys.services;

import java.util.List;
import java.util.Optional;

import be.vdab.toysforboys.entities.Order;

public interface OrderService {
	Optional<Order> read(long id);
	List<Order> findAllButCancelledAndShipped();
	List<Order> findSelectedIds(Long[] selectedIds);
	public void SetAsShippedAndUpdateStock(Order order);
//	int setAsShipped(Long id);
//	void UpdateInOrderEnInStock(Long id);
//	void setAsShippedEnUpdateStock(Long id);
}
