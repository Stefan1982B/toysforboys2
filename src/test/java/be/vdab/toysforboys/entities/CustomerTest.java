package be.vdab.toysforboys.entities;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;

import be.vdab.toysforboys.enums.Status;
import be.vdab.toysforboys.valueobjects.Address;

public class CustomerTest {

	private Order order1;
	private Customer customer1;

	@Before
	public void before() {
		customer1 = new Customer("testCustomer1", 1, new Address("testStraat1", "testCity1", "testState1", "testPostalCode1"), new Country("testCountry1", 1) );
		order1 = new Order(LocalDate.of(2019,1,1), LocalDate.of(2019,2,2), LocalDate.of(2019,1,15), "testComment", customer1, Status.SHIPPED, 1);
	}

	@Test
	public void order1IsVanCustomer1() {
		assertEquals(customer1, order1.getCustomer());
	}
}