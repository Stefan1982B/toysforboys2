package be.vdab.toysforboys.repositories;

import java.util.Optional;

import javax.persistence.EntityManager;

import be.vdab.toysforboys.entities.Order;

class JpaOrderRepository implements OrderRepository {

	private final EntityManager manager;

	JpaOrderRepository(EntityManager manager) {
		this.manager = manager;
	}

	@Override
	public Optional<Order> read(long id) {
		return Optional.ofNullable(manager.find(Order.class, id));
	}

}