package be.vdab.proefpakket.services;

import java.util.List;
import java.util.Optional;

import be.vdab.proefpakket.entities.Gemeente;

public interface GemeenteService {
	Optional<Gemeente> read(long id);
	List<Gemeente> findAll();
}
