package be.vdab.toysforboys.entities;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import be.vdab.toysforboys.enums.Status;

@Entity
@Table(name = "orders")
public class Order implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private LocalDate orderDate;
	private LocalDate requiredDate;
	private LocalDate shippedDate;
	private String comments;
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "customerId")
	private Customer customer;
	@Enumerated(EnumType.STRING)
	private Status status;
	private long version;

	public Order(LocalDate orderDate, LocalDate requiredDate, LocalDate shippedDate, String comments, Customer customer,
			Status status, long version) {
		this.orderDate = orderDate;
		this.requiredDate = requiredDate;
		this.shippedDate = shippedDate;
		this.comments = comments;
		setCustomer(customer);
		this.status = status;
		this.version = version;
	}
	
	protected Order() {
		
	}

	public long getId() {
		return id;
	}

	public LocalDate getOrderDate() {
		return orderDate;
	}

	public LocalDate getRequiredDate() {
		return requiredDate;
	}

	public LocalDate getShippedDate() {
		return shippedDate;
	}

	public String getComments() {
		return comments;
	}

	public Customer getCustomer() {
		return customer;
	}

	public Status getStatus() {
		return status;
	}

	public long getVersion() {
		return version;
	}

	public void setCustomer(Customer customer) {
		if (customer == null) {
			throw new NullPointerException();
		}
		if(! customer.getOrders().contains(this)) {
			customer.addOrder(this);
		}
		this.customer = customer;
	}

}
