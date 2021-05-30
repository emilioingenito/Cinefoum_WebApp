package it.uniroma3.siw.cineforum.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.cineforum.model.Regista;
import it.uniroma3.siw.cineforum.repository.RegistaRepository;

@Service
public class RegistaService {
	
	@Autowired
	private RegistaRepository registaRepository; 
	
	@Transactional
	public long numeroRegisti() {
		return this.registaRepository.count();
	}
	
	@Transactional
	public Regista inserisci(Regista regista) {
		return this.registaRepository.save(regista);
	}
	
	@Transactional
	public void elimina(Regista regista) {
		if (this.alreadyExists(regista))
			registaRepository.delete(regista);
	}
	
	@Transactional 
	public void eliminaTutti() {
		this.registaRepository.deleteAll();
	}

	@Transactional
	public List<Regista> tutti() {
		return (List<Regista>) this.registaRepository.findAll();
	}

	@Transactional
	public Regista registaPerId(Long id) {
		Optional<Regista> optional = this.registaRepository.findById(id);
		if (optional.isPresent())
			return optional.get();
		else 
			return null;
	}
	
	@Transactional
	public List<Regista> registaPerNome(String nome) {
		return this.registaRepository.findByNome(nome);
	}
	
	@Transactional
	public List<Regista> registaPerCognome(String cognome) {
		return this.registaRepository.findByCognome(cognome);
	}

	@Transactional
	public boolean alreadyExists(Regista regista) {
		List<Regista> registi = this.registaRepository.findByCognome(regista.getCognome());
		return registi.size() > 0;
	}
}
