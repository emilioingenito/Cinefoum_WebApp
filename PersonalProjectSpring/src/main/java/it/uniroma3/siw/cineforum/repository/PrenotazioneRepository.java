package it.uniroma3.siw.cineforum.repository;

import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siw.cineforum.model.Prenotazione;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface PrenotazioneRepository extends CrudRepository<Prenotazione, Long>{
	
	public Optional<Prenotazione> findById(Long id);
	
}
