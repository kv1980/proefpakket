package be.vdab.proefpakket.messaging;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import be.vdab.proefpakket.entities.Bestelling;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class EmailData {
	private String brouwerNaam;
	private String emailAdres;
	private String voornaam;

	protected EmailData() {
	}

	public EmailData(Bestelling bestelling) {
		this.brouwerNaam = bestelling.getBrouwer().getNaam();
		this.emailAdres = bestelling.getKlant().getEmailAdres();
		this.voornaam = bestelling.getKlant().getVoornaam();
	}

	public String getBrouwerNaam() {
		return brouwerNaam;
	}

	public String getEmailAdres() {
		return emailAdres;
	}

	public String getVoornaam() {
		return voornaam;
	}
}