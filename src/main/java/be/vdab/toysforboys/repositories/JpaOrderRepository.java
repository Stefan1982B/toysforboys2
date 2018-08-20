package be.vdab.toysforboys.repositories;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import be.vdab.toysforboys.entities.Order;
import be.vdab.toysforboys.valueobjects.Orderdetail;

@Repository
class JpaOrderRepository implements OrderRepository {

	private final EntityManager manager;

	JpaOrderRepository(EntityManager manager) {
		this.manager = manager;
	}

	@Override
	public Optional<Order> read(long id) {
		return Optional.ofNullable(manager.find(Order.class, id));
	}

	@Override
	public List<Order> findAllButCancelledAndShipped() {
		return manager.createNamedQuery("Order.findAllButCancelledAndShipped", Order.class)
				.setHint("javax.persistence.loadgraph", manager.createEntityGraph("Order.metCustomer"))
				.getResultList();
	}

	@Override
	public int setAsShipped(Long ids[]) {
		return manager.createNamedQuery("Order.setAsShipped")
				.setParameter("date", LocalDate.now())
				.setParameter("ids", Arrays.asList(ids))
				.setHint("javax.persistence.loadgraph", manager.createEntityGraph("Orderdetail.metProduct"))
				.executeUpdate();
	}

	@Override
	public void UpdateInOrderEnInStock(long id) {
		Set<Orderdetail>orderdetails = read(id).get().getOrderdetails();
		for(Orderdetail orderdetail : orderdetails) {
			manager.createNamedQuery("Product.UpdateInOrderEnInStock")
			.setParameter("aantal", orderdetail.getQuantityOrdered())
			.executeUpdate();
		}
//		orderdetails.stream()
//		.forEach(orderdetail -> manager.createNamedQuery("Product.UpdateInOrderEnInStock")
//				.setParameter("aantal", orderdetail.getQuantityOrdered())
//				.executeUpdate());
	}

}
