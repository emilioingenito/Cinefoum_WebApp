package it.uniroma3.siw.cineforum.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.cineforum.model.Film;
import it.uniroma3.siw.cineforum.repository.FilmRepository;

@Service
public class FilmService {
	
	@Autowired
	private FilmRepository filmRepository; 
	
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
