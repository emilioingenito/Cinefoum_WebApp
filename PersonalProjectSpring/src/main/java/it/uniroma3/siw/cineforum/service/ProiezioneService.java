package it.uniroma3.siw.cineforum.service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.cineforum.model.Film;
import it.uniroma3.siw.cineforum.model.Proiezione;
import it.uniroma3.siw.cineforum.repository.FilmRepository;
import it.uniroma3.siw.cineforum.repository.ProiezioneRepository;

@Service
public class ProiezioneService {
	
	@Autowired
	private ProiezioneRepository proiezioneRepository; 
	
	@Autowired
	private FilmRepository filmRepository; 
	
	@Transactional
	public Proiezione saveProiezioneToDB(String sala, Integer postiTotali, LocalDate data, 
			LocalTime orario, String nomeFilm, Integer annoFilm) {
		
		Proiezione p = new Proiezione();

		Film f;
		
		try {
			f = this.filmRepository.findByTitoloAndAnnoUscita(nomeFilm, annoFilm).get(0);
		}
		catch (Exception e) {
			f = null;
		}
		
		p.setSala(sala);
		p.setPostiTotali(postiTotali);
		p.setData(data);
		p.setOrario(orario);
		p.setFilm(f);
		
		this.inserisci(p);
		return p;
	}

	
	@Transactional
	public long numeroProiezione() {
		return this.proiezioneRepository.count();
	}
	
	@Transactional
	public Proiezione inserisci(Proiezione p) {
		return proiezioneRepository.save(p);
	}
	
	@Transactional
	public void elimina(Proiezione p) {
			this.proiezioneRepository.delete(p);
	}
	
	
	@Transactional 
	public void eliminaTutti() {
		this.proiezioneRepository.deleteAll();
	}

	@Transactional
	public List<Proiezione> tutti() {
		return (List<Proiezione>) this.proiezioneRepository.findAll();
	}

	@Transactional
	public Proiezione prenotazionePerId(Long id) {
		Optional<Proiezione> optional = this.proiezioneRepository.findById(id);
		if (optional.isPresent())
			return optional.get();
		else 
			return null;
	}
	
	@Transactional
	public List<Proiezione> proiezioniPerData(LocalDate data) {
		return this.proiezioneRepository.findByData(data);
	}
	
	@Transactional
	public List<Proiezione> proiezioniPerSala(String sala) {
		return this.proiezioneRepository.findBySala(sala);
	}
	
	@Transactional
	public List<Proiezione> proiezioniPerOrario(LocalTime orario) {
		return this.proiezioneRepository.findByOrario(orario);
	}
	
	@Transactional
	public List<Proiezione> proiezioniPerSalaDataOra(String sala, LocalDate data, LocalTime ora) {
		return this.proiezioneRepository.findBySalaAndDataAndOrario(sala,data,ora);
	}
	
	@Transactional
	public List<Proiezione> findAll() {
		return (List<Proiezione>) this.proiezioneRepository.findAll();
	}

	@Transactional
	public List<Proiezione> proiezioniPerIdFilm(Long id){
		Optional<Film> f = this.filmRepository.findById(id);
		return (List<Proiezione>) this.proiezioneRepository.findByFilm(f.get());
	}


}
