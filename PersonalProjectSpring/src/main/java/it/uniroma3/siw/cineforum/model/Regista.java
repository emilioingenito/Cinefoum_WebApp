package it.uniroma3.siw.cineforum.model;

import java.util.LinkedList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@EqualsAndHashCode(callSuper=true)
public @Data class Regista extends Persona {
	
	@OneToMany(mappedBy = "regista")
	private List<Film> film_diretti;
	
	public Regista() {
		this.film_diretti = new LinkedList<Film>();
	}

}
