package it.uniroma3.siw.cineforum.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.Data;
import lombok.NonNull;

@Entity
public @Data class Prenotazione {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@NonNull
	private LocalDate data;
	
	@NonNull
	private Integer numeroPosti;
	
	private String codice;
	
	/*ASSOCIAZIONI*/
	
	@ManyToOne
	private Proiezione proiezione;
	
	@ManyToOne
	private Socio socio;
	
}
