package be.vdab.toysforboys.entities;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;

import be.vdab.toysforboys.enums.Status;
import be.vdab.toysforboys.valueobjects.Address;
import be.vdab.toysforboys.valueobjects.Orderdetail;

public class CustomerTest {

	private Order order1;
	private Customer customer1;
	private Customer customer2;
	private Orderdetail orderdetail;
	private Product product;
	private Productline productline;

	@Before
	public void before() {
		productline = new Productline("testName", "testDescription", 1);
		product = new Product("testName", "testScale", "testDescription", 5, 3, BigDecimal.TEN, 1, productline);
		customer1 = new Customer("testCustomer1", 1, new Address("testStraat1", "testCity1", "testState1", "testPostalCode1"), new Country("testCountry1", 1) );
		customer2 = new Customer("testCustomer2", 1, new Address("testStraat2", "testCity2", "testState2", "testPostalCode2"), new Country("testCountry2", 1) );
		orderdetail = new Orderdetail(5, BigDecimal.TEN, product);
		order1 = new Order(LocalDate.of(2019,1,1), LocalDate.of(2019,2,2), LocalDate.of(2019,1,15), "testComment", customer1, Status.SHIPPED, 1, orderdetail);
	}

	@Test
	public void order1IsVanCustomer1() {
		assertEquals(customer1, order1.getCustomer());
		assertEquals(1, customer1.getOrders().size());
		assertTrue(customer1.getOrders().contains(order1));
	}

	@Test
	public void order1WordtVerplaatstNaarCustomer2() {
		assertTrue(customer2.addOrder(order1));
		assertTrue(customer1.getOrders().isEmpty());
		assertEquals(1, customer2.getOrders().size());
		assertTrue(customer2.getOrders().contains(order1));
		assertEquals(customer2, order1.getCustomer());
	}
}