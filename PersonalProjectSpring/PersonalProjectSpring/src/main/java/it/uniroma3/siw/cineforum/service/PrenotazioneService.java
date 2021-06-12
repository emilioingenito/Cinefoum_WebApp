package it.uniroma3.siw.cineforum.service;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.cineforum.model.Prenotazione;
import it.uniroma3.siw.cineforum.model.Proiezione;
import it.uniroma3.siw.cineforum.model.User;
import it.uniroma3.siw.cineforum.repository.PrenotazioneRepository;
import it.uniroma3.siw.cineforum.repository.ProiezioneRepository;
import it.uniroma3.siw.cineforum.repository.UserRepository;

@Service
public class PrenotazioneService {
	
	@Autowired
	private PrenotazioneRepository prenotazioneRepository; 
	
	@Autowired
	private UserRepository userRepository; 
	
	@Autowired
	private ProiezioneRepository proiezioneRepository; 
	
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
	public Prenotazione savePrenotazioneToDB(String nomeSala, String username, LocalDate data, Integer numeroPosti) {
		Prenotazione p = new Prenotazione();
		
		p.setNumeroPosti(numeroPosti);
		
		User u = this.userRepository.findByNome(username).get(0);
		p.setSocio(u);
		
		Proiezione pr = this.proiezioneRepository.findBySalaAndData(nomeSala, data).get(0);
		p.setProiezione(pr);
		
		this.inserisci(p);
		return p;
		
	}

	public List<Prenotazione> findAll() {
		
		List<Prenotazione> l = (List<Prenotazione>) this.prenotazioneRepository.findAll();
		Collections.reverse(l);
		return l;
	}
	

	public void cancellaPrenotazione(Long idPrenotazione) {
		this.prenotazioneRepository.delete(this.prenotazionePerId(idPrenotazione));
		
	}
	
}
