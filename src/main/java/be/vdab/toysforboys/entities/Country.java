package be.vdab.toysforboys.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "countries")
public class Country implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String name;
	private long version;
	
	public Country(String name, long version) {
		this.name = name;
		this.version = version;
	}
	
	protected Country() {
		
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

}
