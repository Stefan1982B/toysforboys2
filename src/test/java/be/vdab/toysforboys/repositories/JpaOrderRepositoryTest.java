package be.vdab.toysforboys.repositories;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;

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

	private static final String ORDERS = "orders";

	private long idVanTestOrder() {
		return super.jdbcTemplate.queryForObject("select id from orders where comments = 'testComment'", Long.class);
	}

	@Test
	public void read() {
		Order order = repository.read(idVanTestOrder()).get();
		assertEquals("testComment", order.getComments());
		assertEquals("testCustomer", order.getCustomer().getName());   
		Set<Orderdetail>orderdetails = order.getOrderdetails();
		for(Orderdetail orderdetail : orderdetails) {
			System.out.println(orderdetail.getPriceEach() + " " + orderdetail.getQuantityOrdered() + " " + orderdetail.getProduct().getQuantityInOrder());
		}
	}

	@Test
	public void readOnbestaandArtikel() {
		assertFalse(repository.read(-1).isPresent());
	}

	@Test
	public void findAll() {
		List<Order> orders = repository.findAll();
		manager.clear();
		assertEquals(super.countRowsInTable(ORDERS), orders.size());
		long vorigId = 0;
		for (Order order : orders) {
			assertTrue(order.getId() >= vorigId);
			vorigId = order.getId();
		}
	}
}
