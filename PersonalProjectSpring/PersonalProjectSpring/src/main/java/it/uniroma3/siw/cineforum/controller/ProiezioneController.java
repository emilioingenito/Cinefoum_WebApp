package it.uniroma3.siw.cineforum.controller;

import java.time.LocalDate;
import java.time.LocalTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import it.uniroma3.siw.cineforum.model.Proiezione;
import it.uniroma3.siw.cineforum.service.ProiezioneService;

@Controller
public class ProiezioneController {

	@Autowired
	private ProiezioneService proiezioneService;

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@RequestMapping(value="/addProiezione", method = RequestMethod.GET)
	public String addProiezione(Model model) {
		logger.debug("addCollezione");
		return "admin/inserimentoProiezione.html";
	}

	@RequestMapping(value = "/addProiezione", method = RequestMethod.POST)
	public String saveProiezione(@RequestParam("sala") String sala,
			@RequestParam("postiTotali") Integer postiTotali,
			@RequestParam("data") @DateTimeFormat(iso = ISO.DATE) LocalDate data,
			@RequestParam("orario") @DateTimeFormat(iso = ISO.TIME) LocalTime orario,
			@RequestParam("nomeFilm") String nomeFilm,
			@RequestParam("annoFilm") Integer annoFilm,
			Model model)
	{
		this.proiezioneService.saveProiezioneToDB(sala, postiTotali, data, orario, nomeFilm, annoFilm);
		return "admin/successoOperazioneAdmin.html";
	}
	
	@RequestMapping(value="/removeProiezione", method = RequestMethod.GET)
	public String removeProiezione(Model model) {
		logger.debug("removeProiezione");
		return "admin/cancellazioneProiezione.html";
	}
	
	@RequestMapping(value = "/removeProiezione", method = RequestMethod.POST)
	public String removeProiezione(@RequestParam("sala") String sala,
			@RequestParam("data") @DateTimeFormat(iso = ISO.DATE) LocalDate data,
			@RequestParam("ora") @DateTimeFormat(iso = ISO.TIME) LocalTime orario,
			Model model)
	{
		Proiezione p;
		try {
			p = this.proiezioneService.proiezioniPerSalaDataOra(sala, data, orario).get(0);
			this.proiezioneService.elimina(p);
			logger.debug("proiezione rimossa dal DB");
		} catch (Exception e) {
			
		}
		return "admin/successoOperazioneAdmin.html";
	}
	
//	@RequestMapping(value = "/collezione/{id}", method = RequestMethod.GET)
//    public String getCollezione(@PathVariable("id") Long id, Model model) {
//
//        model.addAttribute("collezione", this.proiezioneService.collezionePerId(id));
//        model.addAttribute("opere", this.filmService.operePerIdCollezione(id));
//        return "PaginaCollezione";
//
//    }
//	
}
