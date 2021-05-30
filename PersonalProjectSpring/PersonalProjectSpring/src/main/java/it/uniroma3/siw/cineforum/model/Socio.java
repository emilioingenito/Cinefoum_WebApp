package it.uniroma3.siw.cineforum.model;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;


import lombok.Data;
import lombok.NonNull;

@Entity
public @Data class Socio {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@NonNull
	private String numeroTessera;
	
	@NonNull
	private LocalDate annoIscrizione;
	
	@NonNull
	private String nome;
	
	@NonNull
	private String cognome;
	
	/*ASSOCIAZIONI*/

	@OneToMany(mappedBy = "socio", fetch = FetchType.EAGER, cascade = {CascadeType.REMOVE})
	private List<Prenotazione> prenotazioni;
	
	public Socio() {
		this.prenotazioni = new LinkedList<Prenotazione>(); //facciamo fetch e cascade in delete
	}
}
