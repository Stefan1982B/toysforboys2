package be.vdab.toysforboys.valueobjects;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import be.vdab.toysforboys.entities.Order;
import be.vdab.toysforboys.entities.Product;

@Embeddable
public class Orderdetail implements Serializable {

	private static final long serialVersionUID = 1L;

	private long quantityOrdered;
	private BigDecimal priceEach;
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "productId")
	private Product product;
//	@ManyToOne(fetch = FetchType.LAZY, optional = false)
//	@JoinColumn(name = "orderId")
//	private Order order;

	public long getQuantityOrdered() {
		return quantityOrdered;
	}

	public BigDecimal getPriceEach() {
		return priceEach;
	}

	public Orderdetail(long quantityOrdered, BigDecimal priceEach, Product product/*, Order order*/) {
		this.quantityOrdered = quantityOrdered;
		this.priceEach = priceEach;
		setProduct(product);
//		setOrder(order);
	}

	protected Orderdetail() {

	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		if (product == null) {
			throw new NullPointerException();
		}
		this.product = product;
	}

//	public Order getOrder() {
//		return order;
//	}
//
//	public void setOrder(Order order) {
//		if (order == null) {
//			throw new NullPointerException();
//		}
//		this.order = order;
//	}
	

}
