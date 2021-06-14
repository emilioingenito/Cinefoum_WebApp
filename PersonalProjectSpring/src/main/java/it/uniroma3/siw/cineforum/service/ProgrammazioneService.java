package it.uniroma3.siw.cineforum.service;

import java.util.Collections;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.cineforum.model.Film;
import it.uniroma3.siw.cineforum.repository.FilmRepository;

@Service
public class ProgrammazioneService {
	
	@Autowired
	private FilmRepository filmRepository;

	@Transactional
	public List<Film> findAll() {
		List<Film> l = (List<Film>) this.filmRepository.findAll();
		Collections.reverse(l);
		return l;
	}

}
