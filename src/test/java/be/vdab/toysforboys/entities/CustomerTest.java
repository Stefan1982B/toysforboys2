package be.vdab.toysforboys.entities;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;

import be.vdab.toysforboys.valueobjects.Address;

public class CustomerTest {

	private Order order1;
	private Customer customer1;
	private Customer customer2;

	@Before
	public void before() {
		customer1 = new Customer("testCustomer1", 1, new Address("testStraat1", "testCity1", "testState1", "testPostalCode1"), new Country("testCountry1", 1) );
		customer2 = new Customer("testCustomer2", 1, new Address("testStraat2", "testCity2", "testState2", "testPostalCode2"), new Country("testCountry2", 1) );
		order1 = new Order(LocalDate.of(2019,1,1), LocalDate.of(2019,2,2), LocalDate.of(2019,1,15));
	}

	@Test
	public void campus1IsDeCampusVanDocent1() {
		assertEquals(campus1, docent1.getCampus());
		assertEquals(1, campus1.getDocenten().size());
		assertTrue(campus1.getDocenten().contains(docent1));
	}

	@Test
	public void docent1VerhuistNaarCampus2() {
		assertTrue(campus2.add(docent1));
		assertTrue(campus1.getDocenten().isEmpty());
		assertEquals(1, campus2.getDocenten().size());
		assertTrue(campus2.getDocenten().contains(docent1));
		assertEquals(campus2, docent1.getCampus());
	}
}