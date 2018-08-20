package be.vdab.toysforboys.valueobjects;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedAttributeNode;
import javax.persistence.NamedEntityGraph;

import be.vdab.toysforboys.entities.Product;

@Embeddable
@NamedEntityGraph(name = "Orderdetail.metProduct", attributeNodes = @NamedAttributeNode("product"))
public class Orderdetail implements Serializable {

	private static final long serialVersionUID = 1L;

	private long quantityOrdered;
	private BigDecimal priceEach;
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "productId")
	private Product product;

	public long getQuantityOrdered() {
		return quantityOrdered;
	}

	public BigDecimal getPriceEach() {
		return priceEach;
	}

	public Orderdetail(long quantityOrdered, BigDecimal priceEach, Product product) {
		this.quantityOrdered = quantityOrdered;
		this.priceEach = priceEach;
		setProduct(product);
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((product == null) ? 0 : product.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Orderdetail))
			return false;
		Orderdetail other = (Orderdetail) obj;
		if (product == null) {
			if (other.product != null)
				return false;
		} else if (!product.equals(other.product))
			return false;
		return true;
	}

}
