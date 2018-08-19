package be.vdab.toysforboys.entities;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;

import be.vdab.toysforboys.valueobjects.Orderdetail;

public class OrderdetailTest {

	private Orderdetail orderdetail1, nogEensOrderdetail1, orderdetail2;
	private Product product1, product2;
	private Productline productline;

	@Before
	public void before() {
		productline = new Productline("testName", "testDescription", 1);
		product1 = new Product("testName", "testScale", "testDescription", 5, 3, BigDecimal.TEN, 1, productline);
		product2 = new Product("testName2", "testScale", "testDescription", 5, 3, BigDecimal.TEN, 1, productline);
		orderdetail1 = new Orderdetail(10, BigDecimal.valueOf(20), product1);
		nogEensOrderdetail1 = new Orderdetail(10, BigDecimal.valueOf(20), product1);
		orderdetail2 = new Orderdetail(20, BigDecimal.TEN, product2);
	}

	@Test
	public void orderdetailsZijnGelijkAlsHunProductenGelijkZijn() {
		assertEquals(orderdetail1, nogEensOrderdetail1);
	}

	@Test
	public void orderdetailsZijnVerschillendAlsProductenVerschillen() {
		assertNotEquals(orderdetail1, orderdetail2);
	}

	@Test
	public void eenOrderdetailVerschiltVanNull() {
		assertNotEquals(orderdetail1, null);
	}

	@Test
	public void eenOrderdetailVerschiltVanEenAnderTypeObject() {
		assertNotEquals(orderdetail1, "");
	}

	@Test
	public void gelijkeOrderdetailsGevenDezelfdeHashCode() {
		assertEquals(orderdetail1.hashCode(), nogEensOrderdetail1.hashCode());
	}
}
