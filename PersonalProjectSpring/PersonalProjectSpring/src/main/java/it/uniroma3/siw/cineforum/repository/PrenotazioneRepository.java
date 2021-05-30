package it.uniroma3.siw.cineforum.repository;

import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siw.cineforum.model.Prenotazione;

import java.time.LocalDate;
import java.util.List;

public interface PrenotazioneRepository extends CrudRepository<Prenotazione, Long>{
	
	public List<Prenotazione> findByData(LocalDate data);
	public List<Prenotazione> findByCodice(String codice);
	
}
