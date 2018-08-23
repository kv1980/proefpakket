package be.vdab.proefpakket.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import be.vdab.proefpakket.entities.Bestelling;
import be.vdab.proefpakket.messaging.EmailData;
import be.vdab.proefpakket.repositories.BestellingRepository;

@Service
class DefaultBestellingService implements BestellingService {
	private final BestellingRepository bestellingRepository;
	private final JmsTemplate jmsTemplate;
	private final String nieuwProefpakketQueue;

	DefaultBestellingService(BestellingRepository bestellingRepository, JmsTemplate jmsTemplate,
			@Value("${nieuwProefpakketQueue}") String nieuwProefpakketQueue) {
		this.bestellingRepository = bestellingRepository;
		this.jmsTemplate = jmsTemplate;
		this.nieuwProefpakketQueue = nieuwProefpakketQueue;

	}

	@Override
	public void create(Bestelling bestelling) {
		bestellingRepository.save(bestelling);
		// sender.verstuurProefpakket(bestelling.getBrouwer().getNaam(),
		// bestelling.getKlant().getEmailAdres(),bestelling.getKlant().getVoornaam());
		jmsTemplate.convertAndSend(nieuwProefpakketQueue, new EmailData(bestelling));
	}
}