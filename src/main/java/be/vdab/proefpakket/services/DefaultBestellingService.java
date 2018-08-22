package be.vdab.proefpakket.services;

import org.springframework.stereotype.Service;

import be.vdab.proefpakket.entities.Bestelling;
import be.vdab.proefpakket.mail.MailSender;
import be.vdab.proefpakket.repositories.BestellingRepository;

@Service
class DefaultBestellingService implements BestellingService {
	private BestellingRepository bestellingRepository;
	private MailSender sender;

	DefaultBestellingService(BestellingRepository bestellingRepository, MailSender sender) {
		this.bestellingRepository = bestellingRepository;
		this.sender = sender;
	}

	@Override
	public void create(Bestelling bestelling) {
		bestellingRepository.save(bestelling);
		sender.verstuurProefpakket(bestelling.getBrouwer().getNaam(), bestelling.getKlant().getEmailAdres(),bestelling.getKlant().getVoornaam());
	}
}
