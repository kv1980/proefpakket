package be.vdab.proefpakket.web;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import be.vdab.proefpakket.entities.Bestelling;
import be.vdab.proefpakket.entities.Brouwer;
import be.vdab.proefpakket.exceptions.TemperatuurNietGevondenException;
import be.vdab.proefpakket.restclients.TemperatuurClient;
import be.vdab.proefpakket.services.BestellingService;
import be.vdab.proefpakket.services.BrouwerService;
import be.vdab.proefpakket.services.GemeenteService;

@Controller
@RequestMapping("brouwer")
@SessionAttributes("bestelling")
class BrouwerController {
	private static final String BROUWER_VIEW = "brouwers/brouwer";
	private static final String TEMPERATUUR_VIEW = "brouwers/temperatuur";
	private static final String ONDERNEMINGSNUMMER_VIEW = "brouwers/ondernemingsnummer";
	private static final String BESTELLING_STAP_1_VIEW = "brouwers/bestelling1";
	private static final String BESTELLING_STAP_2_VIEW = "brouwers/bestelling2";
	private static final String REDIRECT_NAAR_HOMEPAGE= "redirect:/";
	private static final String REDIRECT_NAAR_BROUWERINFO= "redirect:/brouwer/{id}";
	private final BrouwerService brouwerService;
	private final GemeenteService gemeenteService;
	private final BestellingService bestellingService;
	private final TemperatuurClient temperatuurClient;
	
	BrouwerController(BrouwerService brouwerService, BestellingService bestellingService, GemeenteService gemeenteService, TemperatuurClient temperatuurClient) {
		this.brouwerService = brouwerService;
		this.bestellingService = bestellingService;
		this.gemeenteService = gemeenteService;
		this.temperatuurClient = temperatuurClient;
	}

	@GetMapping("/{brouwer}")
	ModelAndView brouwer(@PathVariable Optional<Brouwer> brouwer, 
						 RedirectAttributes redirectAttributes) {
		if(brouwer.isPresent()) {
			return new ModelAndView(BROUWER_VIEW,"brouwer",brouwer.get());
		}
		redirectAttributes.addAttribute("fout","Brouwer niet gevonden: kies opnieuw een brouwer.");
		return new ModelAndView(REDIRECT_NAAR_HOMEPAGE);
	}
	
	@GetMapping("/{plaatsnaam}/temperatuur")
	ModelAndView toonTemperatuur(@PathVariable String plaatsnaam) {
		ModelAndView modelAndView = new ModelAndView(TEMPERATUUR_VIEW);
		System.out.println("--------------------"+plaatsnaam+"----------"+temperatuurClient.getTemperatuur(plaatsnaam));
		try {
			modelAndView.addObject("temperatuur",temperatuurClient.getTemperatuur(plaatsnaam));
		} catch (TemperatuurNietGevondenException ex) {
			modelAndView.addObject("fout","De temperatuur van deze plaats werd niet gevonden.");
		}
		return modelAndView;
	}
	
	@GetMapping("/{brouwer}/ondernemingsnummer")
	ModelAndView ondernemingsnummerVoorBewaren(@PathVariable Optional<Brouwer> brouwer, 
											   RedirectAttributes redirectAttributes) {
		if(brouwer.isPresent()) {
			return new ModelAndView(ONDERNEMINGSNUMMER_VIEW)
					.addObject("brouwer",brouwer.get())
					.addObject(new OndernemingsNrForm());
		}
		redirectAttributes.addAttribute("fout","Brouwer niet gevonden: kies opnieuw een brouwer.");
		return new ModelAndView(REDIRECT_NAAR_HOMEPAGE);
	}
	
	@PostMapping("/{brouwer}/ondernemingsnummer")
	ModelAndView ondernemingsnummerNaBewaren(@PathVariable Optional<Brouwer> brouwer, 
											 @Valid OndernemingsNrForm form, 
											 BindingResult bindingResult, 
											 RedirectAttributes redirectAttributes) {
		if(brouwer.isPresent()) {
			if(bindingResult.hasErrors()) {
				return new ModelAndView(ONDERNEMINGSNUMMER_VIEW,"brouwer",brouwer.get());
			}
			brouwer.get().setOndernemingsNr(form.getOndernemingsNr());
			brouwerService.update(brouwer.get());
			redirectAttributes.addAttribute("id",brouwer.get().getId());
			return new ModelAndView(REDIRECT_NAAR_BROUWERINFO);
		}
		redirectAttributes.addAttribute("fout","Brouwer niet gevonden: kies opnieuw een brouwer.");
		return new ModelAndView(REDIRECT_NAAR_HOMEPAGE);
	}
	
	@GetMapping("/{brouwer}/bestelling")
	ModelAndView bestellingVoorBewaren(@PathVariable Optional<Brouwer> brouwer, 
									   RedirectAttributes redirectAttributes) {
		if(brouwer.isPresent()) {
			return new ModelAndView(BESTELLING_STAP_1_VIEW,"brouwer",brouwer.get())
					.addObject(new Bestelling());
		}
		redirectAttributes.addAttribute("fout","Brouwer niet gevonden: kies opnieuw een brouwer.");
		return new ModelAndView(REDIRECT_NAAR_HOMEPAGE);
	}
	
	@PostMapping(value="/{brouwer}/bestelling", params="stap2")
	ModelAndView bestellingStap1NaarStap2(@PathVariable Optional<Brouwer> brouwer, 
									RedirectAttributes redirectAttributes,
									@Validated(Bestelling.Stap1.class) Bestelling bestelling,
									BindingResult bindingResult) {
		if(brouwer.isPresent()) {
			if(bindingResult.hasErrors()) {
				return new ModelAndView(BESTELLING_STAP_1_VIEW,"brouwer",brouwer.get());
			} 
			return new ModelAndView(BESTELLING_STAP_2_VIEW,"brouwer",brouwer.get())
					 .addObject("gemeenten",gemeenteService.findAll());
		}
		redirectAttributes.addAttribute("fout","Brouwer niet gevonden: kies opnieuw een brouwer.");
		return new ModelAndView(REDIRECT_NAAR_HOMEPAGE);
	}
	
	@PostMapping(value="/{brouwer}/bestelling", params="stap1")
	ModelAndView bestellingStap2NaarStap1(@PathVariable Optional<Brouwer> brouwer, 
									RedirectAttributes redirectAttributes) {
		if(brouwer.isPresent()) {
			return new ModelAndView(BESTELLING_STAP_1_VIEW,"brouwer",brouwer.get());
		}
		redirectAttributes.addAttribute("fout","Brouwer niet gevonden: kies opnieuw een brouwer.");
		return new ModelAndView(REDIRECT_NAAR_HOMEPAGE);
	}
	
	@PostMapping(value = "/{brouwer}/bestelling", params = "opslaan") 
	ModelAndView bestellingOpslaan(@PathVariable Optional<Brouwer> brouwer, 
						RedirectAttributes redirectAttributes,
						@Validated(Bestelling.Stap2.class) Bestelling bestelling,
						BindingResult bindingResult,
						SessionStatus sessionStatus) {
		if(brouwer.isPresent()) {
				if(bindingResult.hasErrors()) {
					return new ModelAndView(BESTELLING_STAP_2_VIEW,"brouwer",brouwer.get())
										.addObject("gemeenten",gemeenteService.findAll());
				} 
				bestellingService.create(bestelling);
				sessionStatus.setComplete();
				return new ModelAndView(REDIRECT_NAAR_HOMEPAGE);
		}
		redirectAttributes.addAttribute("fout","Brouwer niet gevonden: kies opnieuw een brouwer.");
		return new ModelAndView(REDIRECT_NAAR_HOMEPAGE);
	}
}