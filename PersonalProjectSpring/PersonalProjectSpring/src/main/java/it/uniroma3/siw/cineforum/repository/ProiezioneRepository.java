package it.uniroma3.siw.cineforum.repository;

import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siw.cineforum.model.Proiezione;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface ProiezioneRepository extends CrudRepository<Proiezione, Long>{
	
	public List<Proiezione> findBySala(String sala);
	public List<Proiezione> findByOrario(LocalTime orario);
	public List<Proiezione> findByData(LocalDate data);
	public List<Proiezione> findByCodice(String codice);
	
	
}

