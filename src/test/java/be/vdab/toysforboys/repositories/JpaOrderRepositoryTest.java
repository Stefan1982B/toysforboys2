package be.vdab.toysforboys.repositories;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringRunner;

import be.vdab.toysforboys.entities.Order;
import be.vdab.toysforboys.entities.Product;
import be.vdab.toysforboys.entities.Productline;
import be.vdab.toysforboys.enums.Status;
import be.vdab.toysforboys.valueobjects.Orderdetail;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Sql("/insertCountry.sql")
@Sql("/insertCustomer.sql")
@Sql("/insertProductline.sql")
@Sql("/insertProduct.sql")
@Sql("/insertOrder.sql")

@Import(JpaOrderRepository.class)
public class JpaOrderRepositoryTest extends AbstractTransactionalJUnit4SpringContextTests {

	@Autowired
	private JpaOrderRepository repository;
	@Autowired
	private EntityManager manager;

	private Product product;
	private Productline productline;
	private Status status;

	@Before
	public void Before() {
		productline = new Productline("testProductline", "testDescription", 1);
		product = new Product("testProduct", "testScale", "testDescription", 20, 5, BigDecimal.TEN, 1, productline);
	}

	private static final String ORDERS = "orders";

	private long idVanTestOrder() {
		return super.jdbcTemplate.queryForObject("select id from orders where comments = 'testComment'", Long.class);
	}

	@Test
	public void readOrder() {
		Order order = repository.read(idVanTestOrder()).get();
		assertEquals("testComment", order.getComments());
	}

	@Test
	public void readCustomerLazyLoaded() {
		Order order = repository.read(idVanTestOrder()).get();
		assertEquals("testCustomer", order.getCustomer().getName());
	}

	@Test
	public void readCountryLazyLoaded() {
		Order order = repository.read(idVanTestOrder()).get();
		assertEquals("testCustomer", order.getCustomer().getName());
		assertEquals("testCountry", order.getCustomer().getCountry().getName());
	}

	@Test
	public void readAddress() {
		Order order = repository.read(idVanTestOrder()).get();
		assertEquals("testStreet", order.getCustomer().getAddress().getStreetAndNumber());
		assertEquals("testCountry", order.getCustomer().getCountry().getName());
	}

	@Test
	public void readOrderdetailsEnProductInfo() {
		Order order = repository.read(idVanTestOrder()).get();
		Set<Orderdetail> orderdetails = order.getOrderdetails();
		assertTrue(orderdetails.contains(new Orderdetail(5, BigDecimal.TEN, product)));
		for (Orderdetail orderdetail : orderdetails) {
			System.out.println(orderdetail.getPriceEach() + " " + orderdetail.getQuantityOrdered() + " "
					+ orderdetail.getProduct().getQuantityInOrder());
		}
	}

	@Test
	public void readOnbestaandArtikel() {
		assertFalse(repository.read(-1).isPresent());
	}

	@Test
	public void findAllButCancelledAndShipped() {
		List<Order> orders = repository.findAllButCancelledAndShipped();
		manager.clear();
		assertEquals(super.countRowsInTableWhere(ORDERS, "status not in ('cancelled','shipped')"), orders.size());
		long vorigId = 0;
		for (Order order : orders) {
			assertTrue(order.getId() >= vorigId);
			vorigId = order.getId();
		}
	}

	@Test
	public void setAsShipped() {
		Long ids[] = { idVanTestOrder() };
		repository.setAsShipped(ids);
		manager.flush();
		Order order = repository.read(idVanTestOrder()).get();
		assertEquals(status.SHIPPED, order.getStatus());
		assertEquals(LocalDate.now(), order.getShippedDate());
	}
	
	@Test
	public void UpdateInOrderEnInStock() {
		Order order = repository.read(idVanTestOrder()).get();
		Set<Orderdetail>orderDetails = order.getOrderdetails();
		List<Long>quantityInStocks = new ArrayList<>();
		orderDetails.stream().forEach(orderDetail -> quantityInStocks.add(orderDetail.getProduct().getQuantityInStock()));
		quantityInStocks.forEach(instock -> System.out.println(instock));
		repository.UpdateInOrderEnInStock(idVanTestOrder());
		manager.flush();
		Order orderNieuw = repository.read(idVanTestOrder()).get();
		Set<Orderdetail>orderDetailsNieuw = order.getOrderdetails();
		List<Long>quantityInStocksNieuw = new ArrayList<>();
		orderDetailsNieuw.stream().forEach(orderDetail -> quantityInStocksNieuw.add(orderDetail.getProduct().getQuantityInStock()));
		quantityInStocksNieuw.forEach(aantal -> System.out.println(aantal));
		List<Long>quantityOrdered = new ArrayList<>();
		orderDetailsNieuw.stream().forEach(orderDetail -> quantityOrdered.add(orderDetail.getQuantityOrdered()));
 		for (int i = 0; i < quantityInStocks.size(); i++) {
			assertEquals(0, quantityInStocks.get(i).compareTo(quantityInStocksNieuw.get(i) + quantityOrdered.get(i)));
		}
	}
}
