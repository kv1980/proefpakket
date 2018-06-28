package be.vdab.proefpakket.entities;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import be.vdab.proefpakket.valueobjects.Klant;

@Entity
@Table(name = "bestellingen")
public class Bestelling implements Serializable {
	private static final long serialVersionUID = 1L;
	public interface Stap1{}
	public interface Stap2{}
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@DateTimeFormat(style = "S-")
	private LocalDate datum = LocalDate.now();
	@Embedded
	@Valid
	private Klant klant;
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "brouwerid")
	@NotNull
	private Brouwer brouwer;

	public void setKlant(Klant klant) {
		this.klant = klant;
	}

	public void setBrouwer(Brouwer brouwer) {
		this.brouwer = brouwer;
	}

	public long getId() {
		return id;
	}

	public LocalDate getDatum() {
		return datum;
	}

	public Klant getKlant() {
		return klant;
	}

	public Brouwer getBrouwer() {
		return brouwer;
	}
}
