package be.vdab.toysforboys.services;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import be.vdab.toysforboys.entities.Country;
import be.vdab.toysforboys.entities.Customer;
import be.vdab.toysforboys.entities.Order;
import be.vdab.toysforboys.entities.Product;
import be.vdab.toysforboys.entities.Productline;
import be.vdab.toysforboys.entities.exceptions.OnvoldoendeVoorraadInStockException;
import be.vdab.toysforboys.enums.Status;
import be.vdab.toysforboys.repositories.OrderRepository;
import be.vdab.toysforboys.valueobjects.Address;
import be.vdab.toysforboys.valueobjects.Orderdetail;

public class DefaultOrderServiceTest {
	
	private Product product1, product2;
	private Productline productline;
	private Orderdetail orderdetail1, orderdetail2;
	private Order order1, order2;
	private Customer customer;
	private Address address;
	private Country country;
	private DefaultOrderService orderService;
	
	@Mock
	private OrderRepository repository;


	@Before
	public void before() {
		address =new Address("testStraat1", "testCity1", "testState1", "testPostalCode1");
		country = new Country("testCountry1",1);
		productline = new Productline("testName", "testDescription", 1);
		product1 = new Product("testName", "testScale", "testDescription", 50, 30, BigDecimal.TEN, 1, productline);
		orderdetail1 = new Orderdetail(10, BigDecimal.valueOf(20), product1);
		product2 = new Product("testName2", "testScale2", "testDescription2", 5, 3, BigDecimal.TEN, 1, productline);
		orderdetail2= new Orderdetail(10, BigDecimal.valueOf(20), product2);
		customer = new Customer("testCustomer1", 1, address, country );
		order1 = new Order(LocalDate.of(2019, 1, 1), LocalDate.of(2019, 2, 2), LocalDate.of(2019, 1, 15), "testComment", customer, Status.WAITING, 1);
		order2 = new Order(LocalDate.of(2019, 8, 1), LocalDate.of(2019, 10, 2), LocalDate.of(2019, 9, 15), "testComment2", customer, Status.WAITING, 1);
		orderService = new DefaultOrderService(repository);
		}


	@Test
	public void SetAsShippedAndUpdateStock() {
		order1.addOrderdetail(orderdetail1);
		assertEquals(Status.WAITING, order1.getStatus());
		orderService.SetAsShippedAndUpdateStock(order1);
		assertEquals(Status.SHIPPED, order1.getStatus());
		assertEquals(LocalDate.now(), order1.getShippedDate());
		assertEquals(40, product1.getQuantityInStock());
		assertEquals(20, product1.getQuantityInOrder());
	}
	
	@Test(expected = OnvoldoendeVoorraadInStockException.class)
	public void SetAsShippedAndUpdateStockBijOnvoldoendeVoorraad() {
		order2.addOrderdetail(orderdetail2);
		assertEquals(Status.WAITING, order2.getStatus());
		orderService.SetAsShippedAndUpdateStock(order2);
	}
	

}
