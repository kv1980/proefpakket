package be.vdab.proefpakket.web;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.SafeHtml;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

@Component
@SessionScope
public class BestellingForm implements Serializable{
	private static final long serialVersionUID = 1L;
	public interface Stap1{}
	public interface Stap2{}
	
	@NotBlank (groups = Stap1.class)
	@SafeHtml (groups = Stap1.class)
	private String voornaam;
	@NotBlank (groups = Stap1.class)
	@SafeHtml (groups = Stap1.class)
	private String familienaam;
	@NotBlank (groups = Stap1.class)
	@SafeHtml (groups = Stap1.class)
	@Email (groups = Stap1.class)
	private String emailAdres;
	@NotBlank (groups = Stap2.class)
	@SafeHtml (groups = Stap2.class)
	private String straat;
	@NotBlank (groups = Stap2.class)
	@SafeHtml (groups = Stap2.class)
	private String huisNr;
	private long gemeenteId;

	public void setVoornaam(String voornaam) {
		this.voornaam = voornaam;
	}

	public void setFamilienaam(String familienaam) {
		this.familienaam = familienaam;
	}

	public void setEmailAdres(String emailAdres) {
		this.emailAdres = emailAdres;
	}

	public void setStraat(String straat) {
		this.straat = straat;
	}

	public void setHuisNr(String huisNr) {
		this.huisNr = huisNr;
	}

	public void setGemeenteId(long gemeenteId) {
		this.gemeenteId = gemeenteId;
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

	public String getStraat() {
		return straat;
	}

	public String getHuisNr() {
		return huisNr;
	}

	public long getGemeenteId() {
		return gemeenteId;
	}
}
