package be.vdab.proefpakket.valueobjects;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.SafeHtml;

import be.vdab.proefpakket.entities.Bestelling;

@Embeddable
public class Klant implements Serializable {
	private static final long serialVersionUID = 1L;
	@NotBlank (groups = Bestelling.Stap1.class)
	@SafeHtml (groups = Bestelling.Stap1.class)
	private String voornaam;
	@NotBlank (groups = Bestelling.Stap1.class)
	@SafeHtml (groups = Bestelling.Stap1.class)
	private String familienaam;
	@NotBlank (groups = Bestelling.Stap1.class)
	@SafeHtml (groups = Bestelling.Stap1.class)
	@Email (groups = Bestelling.Stap1.class)
	private String emailAdres;
	@Embedded
	@Valid
	private Adres adres;

	public void setVoornaam(String voornaam) {
		this.voornaam = voornaam;
	}

	public void setFamilienaam(String familienaam) {
		this.familienaam = familienaam;
	}

	public void setEmailAdres(String emailAdres) {
		this.emailAdres = emailAdres;
	}

	public void setAdres(Adres adres) {
		this.adres = adres;
	}

	public String getVoornaam() {
		return voornaam;
	}

	public String getFamilienaam() {
		return familienaam;
	}

	public String getEmailAdres() {
		return emailAdres;
	}

	public Adres getAdres() {
		return adres;
	}
}
