//package be.vdab.toysforboys.repositories;
//
//import static org.junit.Assert.*;
//
//import java.math.BigDecimal;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Set;
//
//import javax.persistence.EntityManager;
//
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
//import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//import org.springframework.context.annotation.Import;
//import org.springframework.test.context.jdbc.Sql;
//import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import be.vdab.toysforboys.entities.Order;
//import be.vdab.toysforboys.entities.Product;
//import be.vdab.toysforboys.entities.Productline;
//import be.vdab.toysforboys.enums.Status;
//import be.vdab.toysforboys.valueobjects.Orderdetail;
//
//@RunWith(SpringRunner.class)
//@DataJpaTest
//@AutoConfigureTestDatabase(replace = Replace.NONE)
//@Sql("/insertCountry.sql")
//@Sql("/insertCustomer.sql")
//@Sql("/insertProductline.sql")
//@Sql("/insertProduct.sql")
//@Sql("/insertOrder.sql")
//@Import(JpaProductRepository.class)
//public class JpaProductRepositoryTest extends AbstractTransactionalJUnit4SpringContextTests {
//
//	@Autowired
//	private JpaProductRepository repository;
//	@Autowired
//	private EntityManager manager;
//
//	private Product product;
//	private Productline productline;
//	private Status status;
//
//	@Before
//	public void Before() {
//		productline = new Productline("testProductline", "testDescription", 1);
//		product = new Product("testProduct", "testScale", "testDescription", 20, 5, BigDecimal.TEN, 1, productline);
//	}
//
//	private static final String ORDERS = "orders";
//
//	private long idVanTestProduct() {
//		return super.jdbcTemplate.queryForObject("select id from products where name = 'testProduct'", Long.class);
//	}
//
//	@Test
//	public void readOrder() {
//		Product product = repository.read(idVanTestProduct()).get();
//		assertEquals("testProduct", product.getName());
//	}
//	
//	@Test
//	public void UpdateInOrderEnInStock() {
//		Product product = repository.read(idVanTestProduct()).get();
//		long quantityInStock = product.getQuantityInStock();
//		long quantityInOrder = product.getQuantityInOrder();
//		repository.UpdateInOrderEnInStock(idVanTestProduct());
//		manager.flush();
//		long quantityInStockNieuw = product.getQuantityInStock();
//		long quantityInOrderNieuw = product.getQuantityInOrder();
//			assertEquals(quantityInStock, quantityInStockNieuw + 5);
//			assertEquals(quantityInOrder, quantityInOrderNieuw + 5);
//			
//	}
//
//}
