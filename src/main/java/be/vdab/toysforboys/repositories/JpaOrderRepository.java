package be.vdab.toysforboys.repositories;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import be.vdab.toysforboys.entities.Order;

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
				.setParameter("ids", Arrays.asList(ids))
				.setHint("javax.persistence.loadgraph", manager.createEntityGraph("Orderdetail.metProduct"))
				.executeUpdate();
	}

}
