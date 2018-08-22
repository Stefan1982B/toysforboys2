package be.vdab.toysforboys.entities;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;

public class ProductTest {

	private Product product1, nogEensProduct1, product2;
	private Productline productline;

	@Before
	public void before() {
		productline = new Productline("testName", "testDescription", 1);
		product1 = new Product("testName", "testScale", "testDescription", 5, 3, BigDecimal.TEN, 1, productline);
		nogEensProduct1 = new Product("testName", "testScale", "testDescription", 5, 3, BigDecimal.TEN, 1, productline);
		product2 = new Product("testName2", "testScale2", "testDescription2", 6, 4, BigDecimal.TEN, 1, productline);
	}

	@Test
	public void ProductenZijnGelijkAlsHunNamenGelijkZijn() {
		assertEquals(product1, nogEensProduct1);
	}

	@Test
	public void ProductenZijnVerschillendAlsHunNamenVerschillendZijn() {
		assertNotEquals(product1, product2);
	}

	@Test
	public void eenProductVerschiltVanNull() {
		assertNotEquals(product1, null);
	}

	@Test
	public void eenProductVerschiltVanEenAnderTypeObject() {
		assertNotEquals(product1, "");
	}

	@Test
	public void gelijkeProductenGevenDezelfdeHashCode() {
		assertEquals(product1.hashCode(), nogEensProduct1.hashCode());
	}

	@Test
	public void updateQuantityInStockVermindertOrderedVanInStock() {
		product1.updateQuantityInStock(1);
		assertEquals(4, product1.getQuantityInStock());
	}

	@Test
	public void updateQuantityInStockReturnsFalseAlsOnvoldoendeVoorraadInStock() {
		assertFalse(product1.updateQuantityInStock(6));
	}

	@Test
	public void updateQuantityInOrderVermindertOrderedVanInOrder() {
		product1.updateQuantityInOrder(1);
		assertEquals(2, product1.getQuantityInOrder());
	}

}
