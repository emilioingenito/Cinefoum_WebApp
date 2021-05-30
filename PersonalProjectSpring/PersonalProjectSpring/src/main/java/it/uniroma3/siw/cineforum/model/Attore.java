package it.uniroma3.siw.cineforum.model;


import java.util.LinkedList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@EqualsAndHashCode(callSuper=true)
public @Data class Attore extends Persona{
	
	@ManyToMany(mappedBy = "attori")
	private List<Film> film_recitati;

	public Attore() {
		this.film_recitati = new LinkedList<Film>();
	}
	

}
