package be.vdab.toysforboys.entities;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;

import be.vdab.toysforboys.enums.Status;
import be.vdab.toysforboys.valueobjects.Address;
import be.vdab.toysforboys.valueobjects.Orderdetail;

public class OrderTest {

	private Order order1, nogEensorder1, order2;
	private Customer customer;
	private Orderdetail orderdetail;
	private Product product;
	private Productline productline;

	@Before
	public void before() {
		productline = new Productline("testName", "testDescription", 1);
		product = new Product("testName", "testScale", "testDescription", 5, 3, BigDecimal.TEN, 1, productline);
		customer = new Customer("testCustomer1", 1, new Address("testStraat1", "testCity1", "testState1", "testPostalCode1"), new Country("testCountry1", 1) );
		orderdetail = new Orderdetail(5, BigDecimal.TEN, product);
		order1 = new Order(LocalDate.of(2019,1,1), LocalDate.of(2019,2,2), LocalDate.of(2019,1,15), "testComment", customer, Status.SHIPPED, 1, orderdetail);
		nogEensorder1 = new Order(LocalDate.of(2019,1,1), LocalDate.of(2019,2,2), LocalDate.of(2019,1,15), "testComment", customer, Status.SHIPPED, 1, orderdetail);
		order2 = new Order(LocalDate.of(2019,1,2), LocalDate.of(2019,2,3), LocalDate.of(2019,1,16), "testComment2", customer, Status.SHIPPED, 1, orderdetail);
	}



}
