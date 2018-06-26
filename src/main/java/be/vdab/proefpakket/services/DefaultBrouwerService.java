package be.vdab.proefpakket.services;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import be.vdab.proefpakket.entities.Brouwer;
import be.vdab.proefpakket.repositories.BrouwerRepository;

@Service
@Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED)
class DefaultBrouwerService implements BrouwerService {
	private final BrouwerRepository repository;

	public DefaultBrouwerService(BrouwerRepository repository) {
		this.repository = repository;
	}

	@Override
	public List<Brouwer> findByBeginNaam(String beginNaam) {
		return repository.findByNaamStartingWithOrderByNaam(beginNaam);
	}

	@Override
	@Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED)
	public void update(Brouwer brouwer) {
		repository.save(brouwer);
	}
}
