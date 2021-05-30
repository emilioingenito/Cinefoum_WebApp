package it.uniroma3.siw.cineforum.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.cineforum.model.Prenotazione;
import it.uniroma3.siw.cineforum.repository.PrenotazioneRepository;

@Service
public class PrenotazioneService {
	
	@Autowired
	private PrenotazioneRepository prenotazioneRepository; 
	
	@Transactional
	public long numeroPrenotazioni() {
		return this.prenotazioneRepository.count();
	}
	
	@Transactional
	public Prenotazione inserisci(Prenotazione p) {
		return prenotazioneRepository.save(p);
	}
	
	@Transactional
	public void elimina(Prenotazione p) {
		if (this.alreadyExists(p))
			this.prenotazioneRepository.delete(p);
	}
	
	@Transactional 
	public void eliminaTutti() {
		this.prenotazioneRepository.deleteAll();
	}

	@Transactional
	public List<Prenotazione> tutti() {
		return (List<Prenotazione>) this.prenotazioneRepository.findAll();
	}

	@Transactional
	public Prenotazione prenotazionePerId(Long id) {
		Optional<Prenotazione> optional = this.prenotazioneRepository.findById(id);
		if (optional.isPresent())
			return optional.get();
		else 
			return null;
	}
	
	@Transactional
	public List<Prenotazione> prenotazioniPerData(LocalDate data) {
		return prenotazioneRepository.findByData(data);
	}

	@Transactional
	public boolean alreadyExists(Prenotazione p) {
		List<Prenotazione> prenotazioni = this.prenotazioneRepository.findByCodice(p.getCodice());
		return prenotazioni.size() > 0;
	}
}
