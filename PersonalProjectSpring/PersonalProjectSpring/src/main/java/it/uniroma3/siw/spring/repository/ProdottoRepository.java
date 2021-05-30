package it.uniroma3.siw.spring.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siw.spring.model.Prodotto;

public interface ProdottoRepository extends CrudRepository<Prodotto, Long> {

	public List<Prodotto> findByNome(String nome);
}