package be.vdab.proefpakket.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "gemeenten")
@XmlRootElement
@XmlAccessorType(value = XmlAccessType.FIELD)
public class Gemeente implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private short postcode;
	private String naam;

	public long getId() {
		return id;
	}

	public long getPostcode() {
		return postcode;
	}

	public String getNaam() {
		return naam.toUpperCase();
	}
}