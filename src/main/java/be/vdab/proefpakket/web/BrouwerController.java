package be.vdab.proefpakket.web;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import be.vdab.proefpakket.entities.Brouwer;
import be.vdab.proefpakket.services.BrouwerService;

@Controller
@RequestMapping("brouwers")
class BrouwerController {
	private static final String BROUWER_VIEW = "brouwers/brouwer";
	private static final String ONDERNEMINGSNUMMER_VIEW = "brouwers/ondernemingsnummer";
	private static final String REDIRECT_BIJ_BROUWER_NIET_GEVONDEN= "redirect:/";
	private static final String REDIRECT_BIJ_BROUWER_GEVONDEN= "redirect:/brouwers/brouwer/{id}";
	private final BrouwerService brouwerService;
	
	BrouwerController(BrouwerService brouwerService) {
		this.brouwerService = brouwerService;
	}

	@GetMapping("/brouwer/{brouwer}")
	ModelAndView brouwer(@PathVariable Optional<Brouwer> brouwer, 
						 RedirectAttributes redirectAttributes) {
		if(brouwer.isPresent()) {
			return new ModelAndView(BROUWER_VIEW,"brouwer",brouwer.get());
		}
		redirectAttributes.addAttribute("fout","Brouwer niet gevonden: kies opnieuw een brouwer.");
		return new ModelAndView(REDIRECT_BIJ_BROUWER_NIET_GEVONDEN);
	}
	
	@GetMapping("/ondernemingsnummer/{brouwer}")
	ModelAndView ondernemingsnummerVoorBewaren(@PathVariable Optional<Brouwer> brouwer, 
											   RedirectAttributes redirectAttributes) {
		if(brouwer.isPresent()) {
			return new ModelAndView(ONDERNEMINGSNUMMER_VIEW)
					.addObject(new OndernemingsNrForm())
					.addObject("brouwer",brouwer.get());
		}
		redirectAttributes.addAttribute("fout","Brouwer niet gevonden: kies opnieuw een brouwer.");
		return new ModelAndView(REDIRECT_BIJ_BROUWER_NIET_GEVONDEN);
	}
	
	@PostMapping("/ondernemingsnummer/{brouwer}")
	ModelAndView ondernemingsnummerNaBewaren(@PathVariable Optional<Brouwer> brouwer, 
											 @Valid OndernemingsNrForm form, 
											 BindingResult bindingResult, 
											 RedirectAttributes redirectAttributes) {
		if(brouwer.isPresent()) {
			if(bindingResult.hasErrors()) {
				return new ModelAndView(ONDERNEMINGSNUMMER_VIEW)
					.addObject("brouwer",brouwer.get());
			}
			brouwer.get().setOndernemingsNr(form.getOndernemingsNr());
			brouwerService.update(brouwer.get());
			redirectAttributes.addAttribute("id",brouwer.get().getId());
			return new ModelAndView(REDIRECT_BIJ_BROUWER_GEVONDEN);
		}
		redirectAttributes.addAttribute("fout","Brouwer niet gevonden: kies opnieuw een brouwer.");
		return new ModelAndView(REDIRECT_BIJ_BROUWER_NIET_GEVONDEN);
	}
}