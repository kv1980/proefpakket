package be.vdab.proefpakket.restservices;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import be.vdab.proefpakket.services.GemeenteService;

@RestController
@RequestMapping("/gemeenten")
public class GemeenteRestController {
	private final GemeenteService gemeenteService;
	
	GemeenteRestController(GemeenteService gemeenteService){
		this.gemeenteService = gemeenteService;
	}

}
