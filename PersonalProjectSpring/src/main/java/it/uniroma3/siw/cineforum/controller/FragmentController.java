package it.uniroma3.siw.cineforum.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import it.uniroma3.siw.cineforum.service.ProgrammazioneService;

@Controller
public class FragmentController {
	
	@Autowired
	private ProgrammazioneService programmazioneService;

	@RequestMapping(value = "/home", method = RequestMethod.GET) 
	public String home (Model model) {
		return "index.html";
	}
	
	@RequestMapping(value = "/admin/home", method = RequestMethod.GET) 
	public String homeAdmin (Model model) {
		return "admin/home.html";
	}
	
	@RequestMapping(value = "/informazioni", method = RequestMethod.GET) 
	public String info (Model model) {
		return "Informazioni.html";
	}
	
	@RequestMapping(value="/programmazione", method=RequestMethod.GET)
	public String mostraProgrammazione(Model model) {
		model.addAttribute("film", this.programmazioneService.findAll());
		return "Programmazione.html";
	}
	
	@RequestMapping(value = "/homePrenotazione", method = RequestMethod.GET) 
	public String homePrenotazione (Model model) {
		return "homePrenotazioni.html";
	}
}
