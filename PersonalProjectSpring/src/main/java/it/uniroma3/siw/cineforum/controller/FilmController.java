package it.uniroma3.siw.cineforum.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import it.uniroma3.siw.cineforum.model.Film;
import it.uniroma3.siw.cineforum.service.AttoreService;
import it.uniroma3.siw.cineforum.service.FilmService;
import it.uniroma3.siw.cineforum.service.ProiezioneService;
import it.uniroma3.siw.cineforum.service.RegistaService;

@Controller
public class FilmController {

	@Autowired
	private FilmService filmService;
	
	@Autowired
	private ProiezioneService proiezioneService;
	
	@Autowired
	private AttoreService attoreService;
	
	@Autowired
	private RegistaService registaService;

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
		return "admin/successoOperazioneAdmin.html";
	}
	
	@RequestMapping(value="/removeFilm", method = RequestMethod.GET)
	public String removeFilm(Model model) {
		logger.debug("removeFilm");
		return "admin/cancellazioneFilm.html";
	}
	
	@RequestMapping(value = "/removeFilm", method = RequestMethod.POST)
	public String removeFilm(@RequestParam("titolo") String titolo, 
			@RequestParam("annoUscita") Integer annoUscita, Model model)
	{
		logger.info("removeFilm");
		Film f;
		try {
			f = this.filmService.filmPerTitoloEAnnoUscita(titolo, annoUscita).get(0);
			this.filmService.elimina(f);
			logger.info("film rimosso dal DB");
		} catch (Exception e) {
			logger.info("film NON rimosso dal DB");
		}
		return "admin/successoOperazioneAdmin.html";
	}
	
	@RequestMapping(value="/addCast", method = RequestMethod.GET)
	public String addCast(Model model) {
		logger.debug("addCast");
		return "admin/inserimentoCast.html";
	}

	@RequestMapping(value = "/addCast", method = RequestMethod.POST)
	public String saveCast(@RequestParam("titolo") String titolo,
			@RequestParam("annoUscita") Integer annoUscita,
			@RequestParam("nome") String nomeAttore,
			@RequestParam("cognome") String cognomeAttore,
			Model model)
	{
		this.filmService.inserisciCast(titolo, annoUscita, nomeAttore, cognomeAttore);
		logger.debug("cast inserito nel DB");
		return "admin/successoOperazioneAdmin.html";
	}
	
	@RequestMapping(value = "/schedaFilm/{id}", method = RequestMethod.GET)
    public String getCollezione(@PathVariable("id") Long id, Model model) {
        model.addAttribute("film", this.filmService.filmPerId(id));
        model.addAttribute("proiezioni", this.proiezioneService.proiezioniPerIdFilm(id));
        model.addAttribute("regista", this.filmService.filmPerId(id).getRegista());
        model.addAttribute("cast", this.filmService.filmPerId(id).getAttori());
        return "SchedaFilm";

    }
	
	@RequestMapping(value="/attore/{id}", method = RequestMethod.GET)
	public String attore(@PathVariable("id") Long id, Model model) {
		model.addAttribute("artista", this.attoreService.attorePerId(id));
		logger.debug("attore");
		return "persona.html";
	}
	
	@RequestMapping(value="/regista/{id}", method = RequestMethod.GET)
	public String regista(@PathVariable("id") Long id, Model model) {
		model.addAttribute("artista", this.registaService.registaPerId(id));
		logger.debug("regista");
		return "persona.html";
	}
	
	
}