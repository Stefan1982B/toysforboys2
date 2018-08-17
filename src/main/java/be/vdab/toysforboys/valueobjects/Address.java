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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((postalCode == null) ? 0 : postalCode.hashCode());
		result = prime * result + ((streetAndNumber == null) ? 0 : streetAndNumber.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Address))
			return false;
		Address other = (Address) obj;
		if (postalCode == null) {
			if (other.postalCode != null)
				return false;
		} else if (!postalCode.equals(other.postalCode))
			return false;
		if (streetAndNumber == null) {
			if (other.streetAndNumber != null)
				return false;
		} else if (!streetAndNumber.equals(other.streetAndNumber))
			return false;
		return true;
	}
	
	



}
