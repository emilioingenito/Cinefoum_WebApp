package it.uniroma3.siw.cineforum.repository;

import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siw.cineforum.model.Attore;

import java.util.List;

public interface AttoreRepository extends CrudRepository<Attore, Long>{
	
	public List<Attore> findByNome(String nome);
	public List<Attore> findByCognome(String cognome);
	public List<Attore> findByNomeAndCognome(String nome, String cognome);
	
}
