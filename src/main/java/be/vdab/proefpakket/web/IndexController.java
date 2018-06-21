package be.vdab.proefpakket.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/")
class index {
	private static final String VIEW = "index";
	private static final char[] ALFABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray(); 
	
	@GetMapping
	ModelAndView index() {
		return new ModelAndView(VIEW)
				.addObject("alfabet",ALFABET);
	}
}