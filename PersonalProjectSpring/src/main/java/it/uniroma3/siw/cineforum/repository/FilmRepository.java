package it.uniroma3.siw.cineforum.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siw.cineforum.model.Film;

public interface FilmRepository extends CrudRepository<Film, Long>{
	
	public List<Film> findByTitolo(String titolo);
	public List<Film> findByAnnoUscita(LocalDate annoDiUscita);
	public List<Film> findByTitoloAndAnnoUscita(String titolo, Integer annoUscita);
}
