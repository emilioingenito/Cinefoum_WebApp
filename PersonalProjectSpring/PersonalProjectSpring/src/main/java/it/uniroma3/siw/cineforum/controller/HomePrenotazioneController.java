package it.uniroma3.siw.cineforum.controller;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import it.uniroma3.siw.cineforum.service.PrenotazioneService;

@Controller
public class HomePrenotazioneController {
	
	@Autowired
	private PrenotazioneService prenotazioneService;
	

	@RequestMapping(value = "/prenotazione", method = RequestMethod.GET) 
	public String prenotazione (Model model) {
		return "prenotazione.html";
	}
	
	@RequestMapping(value = "/mostraPrenotazione", method = RequestMethod.GET) 
	public String mostraprenotazione (Model model) {
		model.addAttribute("prenotazioni", this.prenotazioneService.findAll());
		return "mostraPrenotazione.html";
	}
	
	@RequestMapping(value = "/cancellaPrenotazione", method = RequestMethod.GET) 
	public String cancellaprenotazione (Model model) {
		return "cancellaPrenotazione.html";
	}
	
	@RequestMapping(value = "/cancellaPrenotazione", method = RequestMethod.POST) 
	public String cancella (@RequestParam("idPrenotazione") Long idPrenotazione, Model model) {
		this.prenotazioneService.cancellaPrenotazione(idPrenotazione);
		return "successoOperazione.html";
	}
	
	@RequestMapping(value = "/prenotazione", method = RequestMethod.POST)
	public String savePrenotazione(
			@RequestParam("nomeSala") String nomeSala,
			@RequestParam("username") String username,
			@RequestParam("data") @DateTimeFormat(iso = ISO.DATE) LocalDate data,
			@RequestParam("numeroPosti") Integer numeroPosti,
			Model model)
	{
		this.prenotazioneService.savePrenotazioneToDB(nomeSala, username,data,numeroPosti);
		return "successoOperazione.html";
	}
}
