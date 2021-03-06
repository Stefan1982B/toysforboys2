package be.vdab.toysforboys.entities;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Version;

import be.vdab.toysforboys.entities.exceptions.OnvoldoendeVoorraadInStockException;

@Entity
@Table(name = "products")
public class Product implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String name;
	private String scale;
	private String description;
	private long quantityInStock;
	private long quantityInOrder;
	private BigDecimal buyPrice;
	@Version
	private long version;
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "productlineId")
	private Productline productline;

	public Product(String name, String scale, String description, long quantityInStock, long quantityInOrder,
			BigDecimal buyPrice, long version, Productline productline) {
		this.name = name;
		this.scale = scale;
		this.description = description;
		this.quantityInStock = quantityInStock;
		this.quantityInOrder = quantityInOrder;
		this.buyPrice = buyPrice;
		this.version = version;
		setProductline(productline);
	}

	protected Product() {

	}

	public long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getScale() {
		return scale;
	}

	public String getDescription() {
		return description;
	}

	public long getQuantityInStock() {
		return quantityInStock;
	}

	public long getQuantityInOrder() {
		return quantityInOrder;
	}

	public BigDecimal getBuyPrice() {
		return buyPrice;
	}

	public long getVersion() {
		return version;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Product))
			return false;
		Product other = (Product) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	public Productline getProductline() {
		return productline;
	}

	public void setProductline(Productline productline) {
		if (productline == null) {
			throw new NullPointerException();
		}
		this.productline = productline;
	}

	public void updateQuantityInStock(long quantityOrdered) {
		if (quantityInStock < quantityOrdered) {
			throw new OnvoldoendeVoorraadInStockException();
		} else {
			quantityInStock = quantityInStock - quantityOrdered;
		}
	}

	public void updateQuantityInOrder(long quantityOrdered) {
		quantityInOrder = quantityInOrder - quantityOrdered;
	}
}
