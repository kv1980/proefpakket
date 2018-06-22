package be.vdab.proefpakket.entities;

import java.io.Serializable;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.SafeHtml;

import be.vdab.proefpakket.valueobjects.Adres;

@Entity
@Table(name = "brouwers")
public class Brouwer implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@NotBlank
	@SafeHtml
	private String naam;
	@Embedded
	private Adres adres;
	private Long ondernemingsNr;

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