package be.vdab.toysforboys.entities;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;

import be.vdab.toysforboys.enums.Status;
import be.vdab.toysforboys.valueobjects.Address;

public class CustomerTest {

	private Order order1;
	private Customer customer1;
	private Customer customer2;

	@Before
	public void before() {
		customer1 = new Customer("testCustomer1", 1, new Address("testStraat1", "testCity1", "testState1", "testPostalCode1"), new Country("testCountry1", 1) );
		customer2 = new Customer("testCustomer2", 1, new Address("testStraat2", "testCity2", "testState2", "testPostalCode2"), new Country("testCountry2", 1) );
		order1 = new Order(LocalDate.of(2019,1,1), LocalDate.of(2019,2,2), LocalDate.of(2019,1,15), "testComment", customer1, Status.SHIPPED, 1);
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