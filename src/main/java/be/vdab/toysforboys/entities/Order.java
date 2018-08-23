package be.vdab.toysforboys.entities;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedAttributeNode;
import javax.persistence.NamedEntityGraph;
import javax.persistence.Table;
import javax.persistence.Version;

import be.vdab.toysforboys.enums.Status;
import be.vdab.toysforboys.valueobjects.Orderdetail;

@Entity
@Table(name = "orders")
@NamedEntityGraph(name = "Order.metCustomer", attributeNodes = @NamedAttributeNode("customer"))
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
	@Version
	private long version;
	@ElementCollection
	@CollectionTable(name = "orderdetails", joinColumns = @JoinColumn(name = "orderId"))
	private Set<Orderdetail> orderdetails;

	public Order(LocalDate orderDate, LocalDate requiredDate, LocalDate shippedDate, String comments, Customer customer,
			Status status, long version) {
		this.orderDate = orderDate;
		this.requiredDate = requiredDate;
		this.shippedDate = shippedDate;
		this.comments = comments;
		setCustomer(customer);
		this.status = status;
		this.version = version;
		this.orderdetails = new LinkedHashSet<>();
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
		this.customer = customer;
	}

	public Set<Orderdetail> getOrderdetails() {
		return Collections.unmodifiableSet(orderdetails);
	}

	public boolean addOrderdetail(Orderdetail orderdetail) {
		if (orderdetail == null) {
			throw new NullPointerException();
		}
		return orderdetails.add(orderdetail);
	}

	public boolean removeOrderdetail(Orderdetail orderdetail) {
		return orderdetails.remove(orderdetail);
	}

	public void updateStatusToShipped() {
		status = Status.SHIPPED;
	}

	public void updateShippedDate() {
		shippedDate = LocalDate.now();
	}

}
