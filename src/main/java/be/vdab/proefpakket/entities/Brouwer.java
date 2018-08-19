package be.vdab.proefpakket.entities;

import java.io.Serializable;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Negative;
import javax.validation.constraints.NotNull;

import be.vdab.proefpakket.constraints.OndernemingsNr;
import be.vdab.proefpakket.valueobjects.Adres;

@Entity
@Table(name = "brouwers")
public class Brouwer implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String naam;
	@Embedded
	private Adres adres;
	@NotNull
	@OndernemingsNr
	private Long ondernemingsNr;
	
	protected Brouwer() {
	}

	public Brouwer(String naam, Adres adres) {
		this.naam = naam;
		this.adres = adres;
	}
	
	public void setOndernemingsNr(Long ondernemingsNr) {
		this.ondernemingsNr = ondernemingsNr;
	}

	public long getId() {
		return id;
	}

	public String getNaam() {
		return naam;
	}

	public Adres getAdres() {
		return adres;
	}

	public Long getOndernemingsNr() {
		return ondernemingsNr;
	}




}