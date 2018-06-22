package be.vdab.proefpakket.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import be.vdab.proefpakket.services.BrouwerService;

@Controller
@RequestMapping("/")
class index {
	private static final String VIEW = "index";
	private static final char[] ALFABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray(); 
	private final BrouwerService brouwerService;

	index(BrouwerService brouwerService) {
		this.brouwerService = brouwerService;
	}

	@GetMapping
	ModelAndView startpagina() {
		return new ModelAndView(VIEW)
				.addObject("alfabet",ALFABET);
	}
	
	@GetMapping(params = "letter")
	ModelAndView brouwerslijst(String letter) {
		return new ModelAndView(VIEW)
				.addObject("alfabet",ALFABET)
				.addObject("brouwers",brouwerService.findByBeginNaam(letter));
	}
}