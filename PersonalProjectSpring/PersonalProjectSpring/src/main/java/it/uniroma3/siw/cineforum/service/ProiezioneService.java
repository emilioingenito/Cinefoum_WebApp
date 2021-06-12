package it.uniroma3.siw.cineforum.service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.cineforum.model.Proiezione;
import it.uniroma3.siw.cineforum.repository.ProiezioneRepository;

@Service
public class ProiezioneService {
	
	@Autowired
	private ProiezioneRepository proiezioneRepository; 
	
	@Transactional
	public long numeroProiezione() {
		return this.proiezioneRepository.count();
	}
	
	@Transactional
	public Proiezione inserisci(Proiezione p) {
		return proiezioneRepository.save(p);
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


}
