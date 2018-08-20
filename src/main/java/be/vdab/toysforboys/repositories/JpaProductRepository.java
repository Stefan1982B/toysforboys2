package be.vdab.toysforboys.repositories;

import java.util.Set;

import javax.persistence.EntityManager;

import be.vdab.toysforboys.valueobjects.Orderdetail;

class JpaProductRepository implements ProductRepository {

	private final EntityManager manager;

	JpaProductRepository(EntityManager manager) {
		this.manager = manager;
	}
	
//	@Override
//	public void UpdateInOrderEnInStock(long id) {
//		Set<Orderdetail>orderdetails = read(id).get().getOrderdetails();
//		for(Orderdetail orderdetail : orderdetails) {
//			manager.createNamedQuery("Product.UpdateInOrderEnInStock")
//			.setParameter("aantal", orderdetail.getQuantityOrdered())
//			.executeUpdate();
//		}
//		orderdetails.stream()
//		.forEach(orderdetail -> manager.createNamedQuery("Product.UpdateInOrderEnInStock")
//				.setParameter("aantal", orderdetail.getQuantityOrdered())
//				.executeUpdate());
	}

