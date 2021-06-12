package it.uniroma3.siw.cineforum.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.Data;

@Entity
public @Data class Prenotazione {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private Integer numeroPosti;

	
	/*ASSOCIAZIONI*/
	
	@ManyToOne
	private Proiezione proiezione;
	
	@ManyToOne
	private User socio;
	

	
}
