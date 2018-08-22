package be.vdab.toysforboys.entities;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;

import be.vdab.toysforboys.enums.Status;
import be.vdab.toysforboys.valueobjects.Address;
import be.vdab.toysforboys.valueobjects.Orderdetail;

public class OrderTest {

	private Product product1;
	private Productline productline;
	private Orderdetail orderdetail;
	private Order order;
	private Customer customer;

	@Before
	public void before() {
		productline = new Productline("testName", "testDescription", 1);
		product1 = new Product("testName", "testScale", "testDescription", 5, 3, BigDecimal.TEN, 1, productline);
		orderdetail = new Orderdetail(10, BigDecimal.valueOf(20), product1);
		customer = new Customer("testCustomer1", 1, new Address("testStraat1", "testCity1", "testState1", "testPostalCode1"), new Country("testCountry1", 1) );
		order = new Order(LocalDate.of(2019, 1, 1), LocalDate.of(2019, 2, 2), LocalDate.of(2019, 1, 15), "testComment", customer, Status.SHIPPED, 1);
		
	}
	
	@Test
	public void updateStatusToShipped() {
		order.updateStatusToShipped();
		assertEquals(Status.SHIPPED, order.getStatus());
	}
	
	@Test
	public void updateDateShipped() {
		order.updateShippedDate();
		assertEquals(LocalDate.now(), order.getShippedDate());
	}

}
