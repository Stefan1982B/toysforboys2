//package be.vdab.toysforboys.repositories;
//
//import java.util.Optional;
//
//import javax.persistence.EntityManager;
//
//import org.springframework.stereotype.Repository;
//
//import be.vdab.toysforboys.entities.Product;
//
//@Repository
//class JpaProductRepository implements ProductRepository {
//
//	private final EntityManager manager;
//
//	JpaProductRepository(EntityManager manager) {
//		this.manager = manager;
//	}
//
//	@Override
//	public Optional<Product> read(long id) {
//		return Optional.ofNullable(manager.find(Product.class, id));
//	}
//
//	@Override
//	public int UpdateInOrderEnInStock(long id) {
//		return manager.createNamedQuery("Product.UpdateInOrderEnInStock")
//		.setParameter("aantal", 5l)
//		.setParameter("id", id)
//		.executeUpdate();
//		
//		
//	}
//
//}
