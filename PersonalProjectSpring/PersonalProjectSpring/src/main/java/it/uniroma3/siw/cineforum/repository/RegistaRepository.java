package it.uniroma3.siw.cineforum.repository;

import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siw.cineforum.model.Regista;

import java.util.List;

public interface RegistaRepository extends CrudRepository<Regista, Long>{
	
	public List<Regista> findByNome(String nome);
	public List<Regista> findByCognome(String cognome);
	public List<Regista> findByNomeAndCognome(String nome, String cognome);
	
}
