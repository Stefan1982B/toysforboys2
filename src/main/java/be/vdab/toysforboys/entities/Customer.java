package be.vdab.toysforboys.entities;

import java.io.Serializable;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import be.vdab.toysforboys.valueobjects.Address;

@Entity
@Table(name = "customers")
public class Customer implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String name;
	private long version;
	@Embedded
	private Address address;
	@ManyToOne(optional = false)
	@JoinColumn(name = "countryId")
	private Country country;

	public Customer(String name, long version, Address address, Country country) {
		this.name = name;
		this.version = version;
		this.address = address;
		setCountry(country);
	}

	protected Customer() {

	}

	public long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public long getVersion() {
		return version;
	}

	public Address getAddress() {
		return address;
	}

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		if (country == null) {
			throw new NullPointerException();
		}
		this.country = country;
	}

}
