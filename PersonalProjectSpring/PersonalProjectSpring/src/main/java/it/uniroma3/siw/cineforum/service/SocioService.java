package it.uniroma3.siw.cineforum.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.cineforum.model.Socio;
import it.uniroma3.siw.cineforum.repository.SocioRepository;

@Service
public class SocioService {
	
	@Autowired
	private SocioRepository socioRepository; 
	
	@Transactional
	public long numeroSoci() {
		return this.socioRepository.count();
	}
	
	@Transactional
	public Socio inserisci(Socio socio) {
		return this.socioRepository.save(socio);
	}
	
	@Transactional
	public void elimina(Socio socio) {
		if (this.alreadyExists(socio))
			this.socioRepository.delete(socio);
	}
	
	@Transactional 
	public void eliminaTutti() {
		this.socioRepository.deleteAll();
	}

	@Transactional
	public List<Socio> tutti() {
		return (List<Socio>) this.socioRepository.findAll();
	}

	@Transactional
	public Socio socioPerId(Long id) {
		Optional<Socio> optional = this.socioRepository.findById(id);
		if (optional.isPresent())
			return optional.get();
		else 
			return null;
	}
	
	@Transactional
	public List<Socio> socioPerNome(String nome) {
		return this.socioRepository.findByNome(nome);
	}
	
	@Transactional
	public List<Socio> socioPerCognome(String cognome) {
		return this.socioRepository.findByCognome(cognome);
	}

	@Transactional
	public List<Socio> socioPerNumTessera(String numeroTessera) {
		return this.socioRepository.findByNumeroTessera(numeroTessera);
	}
	
	@Transactional
	public List<Socio> socioPerAnnoIscrizione(LocalDate annoIscrizione) {
		return this.socioRepository.findByAnnoIscrizione(annoIscrizione);
	}
	
	@Transactional
	public boolean alreadyExists(Socio socio) {
		List<Socio> soci = this.socioRepository.findByCognome(socio.getCognome());
		return soci.size() > 0;
	}
}
