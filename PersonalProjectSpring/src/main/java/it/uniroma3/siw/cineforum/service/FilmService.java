package it.uniroma3.siw.cineforum.service;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import it.uniroma3.siw.cineforum.model.Attore;
import it.uniroma3.siw.cineforum.model.Film;
import it.uniroma3.siw.cineforum.model.Regista;
import it.uniroma3.siw.cineforum.repository.AttoreRepository;
import it.uniroma3.siw.cineforum.repository.FilmRepository;
import it.uniroma3.siw.cineforum.repository.RegistaRepository;

@Service
public class FilmService {
	
	@Autowired
	private FilmRepository filmRepository;
	
	@Autowired
	private RegistaRepository registaRepository; 
	
	@Autowired
	private AttoreRepository attoreRepository; 
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Transactional
	public Film saveFilmToDB(MultipartFile file, String titolo, String trama, Integer annoUscita,
			                   String nomeRegista, String cognomeRegista){
	
		Film f = new Film();
		
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());
		if(fileName.contains("..")){
			System.out.println("not a a valid file");
		}
		
		try {
			f.setFoto(Base64.getEncoder().encodeToString(file.getBytes()));
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
		
		Regista r;
		
		try {
			r = this.registaRepository.findByNomeAndCognome(nomeRegista,cognomeRegista).get(0);
			logger.info("regista trovato");
		}
		catch (Exception e) {
			r = null;
			logger.info("regista non trovato");
		}
		
		f.setTitolo(titolo);
		f.setAnnoUscita(annoUscita);
		f.setTrama(trama);
		f.setRegista(r);
		
		this.inserisci(f);
		return f;
	}
	
	@Transactional
	public long numeroFilm() {
		return this.filmRepository.count();
	}
	
	@Transactional
	public Film inserisci(Film film) {
		return filmRepository.save(film);
	}
	
	@Transactional
	public void elimina(Film film) {
			this.filmRepository.delete(film);
	}
	
	@Transactional 
	public void eliminaTutti() {
		this.filmRepository.deleteAll();
	}

	@Transactional
	public List<Film> tutti() {
		return (List<Film>) filmRepository.findAll();
	}

	@Transactional
	public Film filmPerId(Long id) {
		Optional<Film> optional = filmRepository.findById(id);
		if (optional.isPresent())
			return optional.get();
		else 
			return null;
	}
	
	@Transactional
	public List<Film> filmPerTitolo(String titolo) {
		return filmRepository.findByTitolo(titolo);
	}
	
	@Transactional
	public List<Film> filmPerAnno(LocalDate anno) {
		return filmRepository.findByAnnoUscita(anno);
	}
	
	@Transactional
	public List<Film> filmPerTitoloEAnnoUscita(String titolo, Integer annoUscita) {
		return this.filmRepository.findByTitoloAndAnnoUscita(titolo, annoUscita);
	}

	@Transactional
	public boolean alreadyExists(Film film) {
		List<Film> listaFilm = this.filmRepository.findByTitolo(film.getTitolo());
		return listaFilm.size() > 0;
	}
	
	@Transactional
	public Film inserisciCast(String titolo, Integer annoUscita, String nome, String cognome) {
		
		/* Ricerca del film */
		Film f;
				
		try {
			f = this.filmRepository.findByTitoloAndAnnoUscita(titolo, annoUscita).get(0);
			logger.info("film trovato");
		}
		catch (Exception e) {
			f = null;
			logger.info("film non trovato");
		}
		
		/* Ricerca dell'attore */
		Attore a;
		
		try {
			a = this.attoreRepository.findByNomeAndCognome(nome, cognome).get(0);
			logger.info("attore trovato");
		}
		catch (Exception e) {
			a = null;
			logger.info("attore non trovato");
		}
		
		/* Realizza collegamento */
		if(f != null && a != null) {
			f.addAttore(a);
			this.filmRepository.save(f);
			logger.info("collegamento realizzato");
		}
		
		return f;
	}
}
