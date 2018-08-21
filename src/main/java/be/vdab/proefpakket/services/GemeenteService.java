package be.vdab.proefpakket.services;

import java.util.List;
import java.util.Optional;

import be.vdab.proefpakket.entities.Gemeente;

public interface GemeenteService {
	List<Gemeente> findAll();
}
