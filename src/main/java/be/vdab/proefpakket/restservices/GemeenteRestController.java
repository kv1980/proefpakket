package be.vdab.proefpakket.restservices;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import be.vdab.proefpakket.entities.Gemeente;
import be.vdab.proefpakket.exceptions.GemeenteNietGevondenException;
import be.vdab.proefpakket.services.GemeenteService;

@RestController
@RequestMapping("/gemeenten")
public class GemeenteRestController {
//	private final GemeenteService gemeenteService;
//
//	GemeenteRestController(GemeenteService gemeenteService) {
//		this.gemeenteService = gemeenteService;
//	}

	@GetMapping("{gemeente}")
	Gemeente read(@PathVariable Optional<Gemeente> gemeente) {
		if (!gemeente.isPresent()) {
			throw new GemeenteNietGevondenException();
		}
		return gemeente.get();
	}
	
	@ExceptionHandler(GemeenteNietGevondenException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	void gemeenteNietGevonden() {
	}
}
