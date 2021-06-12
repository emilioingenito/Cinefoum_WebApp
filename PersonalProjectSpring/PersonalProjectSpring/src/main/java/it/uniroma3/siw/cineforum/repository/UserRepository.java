package it.uniroma3.siw.cineforum.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siw.cineforum.model.User;

public interface UserRepository extends CrudRepository<User, Long> {

	public List<User> findByNome(String username);

}