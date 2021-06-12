package it.uniroma3.siw.cineforum.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class FragmentController {

	@RequestMapping(value = "/home", method = RequestMethod.GET) 
	public String home (Model model) {
		return "index.html";
	}
	
	@RequestMapping(value = "/informazioni", method = RequestMethod.GET) 
	public String info (Model model) {
		return "Informazioni.html";
	}
	
	@RequestMapping(value = "/programmazione", method = RequestMethod.GET) 
	public String programmazione (Model model) {
		return "Programmazione.html";
	}
	
	@RequestMapping(value = "/homePrenotazione", method = RequestMethod.GET) 
	public String homePrenotazione (Model model) {
		return "homePrenotazioni.html";
	}
}
