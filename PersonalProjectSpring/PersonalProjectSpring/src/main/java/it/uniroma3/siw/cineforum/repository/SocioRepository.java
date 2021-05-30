package it.uniroma3.siw.cineforum.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siw.cineforum.model.Socio;

public interface SocioRepository extends CrudRepository<Socio, Long>{
	
	public List<Socio> findByNome(String nome);
	public List<Socio> findByCognome(String cognome);
	public List<Socio> findByNumeroTessera(String numeroTessera);
	public List<Socio> findByAnnoIscrizione(LocalDate annoIscrizione);

}
