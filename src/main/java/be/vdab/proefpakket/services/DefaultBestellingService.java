package be.vdab.proefpakket.services;

import org.springframework.stereotype.Service;

import be.vdab.proefpakket.entities.Bestelling;
import be.vdab.proefpakket.repositories.BestellingRepository;

@Service
class DefaultBestellingService implements BestellingService {
	private BestellingRepository bestellingRepository;

	DefaultBestellingService(BestellingRepository bestellingRepository) {
		this.bestellingRepository = bestellingRepository;
	}

	@Override
	public void create(Bestelling bestelling) {
		bestellingRepository.save(bestelling);
	}
}
