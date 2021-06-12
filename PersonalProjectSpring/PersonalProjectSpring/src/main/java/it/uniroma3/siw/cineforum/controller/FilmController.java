package it.uniroma3.siw.cineforum.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import it.uniroma3.siw.cineforum.service.FilmService;

@Controller
public class FilmController {

	@Autowired
	private FilmService filmService;

	private final Logger logger = LoggerFactory.getLogger(this.getClass());


	@RequestMapping(value="/addFilm", method = RequestMethod.GET)
	public String addFilm(Model model) {
		logger.debug("addFilm");
		return "admin/inserimentoFilm.html";
	}

	@RequestMapping(value = "/addFilm", method = RequestMethod.POST)
	public String saveFilm(@RequestParam("file") MultipartFile file,
			@RequestParam("titolo") String titolo,
			@RequestParam("trama") String trama,
			@RequestParam("annoUscita") Integer annoUscita,
			@RequestParam("nomeRegista") String nomeRegista,
			@RequestParam("cognomeRegista") String cognomeRegista,
			Model model)
	{
		this.filmService.saveFilmToDB(file, titolo, trama, annoUscita, nomeRegista, cognomeRegista);
		logger.debug("film inserito nel DB");
		return "admin/home.html";
	}
	
//	@RequestMapping(value="/removeOpera", method = RequestMethod.GET)
//	public String removeOpera(Model model) {
//		logger.debug("removeOpera");
//		return "admin/cancellazioneOpera.html";
//	}
//	
//	@RequestMapping(value = "/removeOpera", method = RequestMethod.POST)
//	public String removeOpera(@RequestParam("titolo") String titolo, 
//			@RequestParam("anno") String anno, Model model)
//	{
//		logger.debug("removeOpera");
//		Opera o;
//		try {
//			o = this.operaService.operePerTitoloEAnno(titolo, anno).get(0);
//			this.operaService.rimuovi(o);
//			logger.debug("opera rimossa dal DB");
//		} catch (Exception e) {
//			
//		}
//		return "admin/HomeLogin.html";
//	}
//	
//	@RequestMapping(value="/opera/{id}", method = RequestMethod.GET)
//	public String getOpera(@PathVariable("id") Long id, Model model) {
//		model.addAttribute("opera", this.operaService.operaPerId(id));
//		return "opera.html";
//	}
	
	
}