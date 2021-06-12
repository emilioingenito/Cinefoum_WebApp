package it.uniroma3.siw.cineforum.service;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import it.uniroma3.siw.cineforum.model.Film;
import it.uniroma3.siw.cineforum.model.Regista;
import it.uniroma3.siw.cineforum.repository.FilmRepository;
import it.uniroma3.siw.cineforum.repository.RegistaRepository;

@Service
public class FilmService {
	
	@Autowired
	private FilmRepository filmRepository;
	private RegistaRepository registaRepository; 
	
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
		}
		catch (Exception e) {
			r = null;
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
		if (this.alreadyExists(film))
			filmRepository.delete(film);
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
	public boolean alreadyExists(Film film) {
		List<Film> listaFilm = this.filmRepository.findByTitolo(film.getTitolo());
		return listaFilm.size() > 0;
	}
}
