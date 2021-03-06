package be.vdab.toysforboys.valueobjects;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class Address implements Serializable {

	private static final long serialVersionUID = 1L;

	private String streetAndNumber;
	private String city;
	private String state;
	private String postalCode;

	public Address(String streetAndNumber, String city, String state, String postalCode) {
		this.streetAndNumber = streetAndNumber;
		this.city = city;
		this.state = state;
		this.postalCode = postalCode;
	}

	protected Address() {

	}

	public String getStreetAndNumber() {
		return streetAndNumber;
	}

	public String getCity() {
		return city;
	}

	public String getState() {
		return state;
	}

	public String getPostalCode() {
		return postalCode;
	}

}
