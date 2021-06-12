package it.uniroma3.siw.cineforum.model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import lombok.Data;
import lombok.NonNull;

@Entity
public @Data class Proiezione {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@NonNull
	private String sala;
	
	@NonNull
	private Integer postiTotali;
	
	@NonNull
	private LocalDate data;
	
	@NonNull
	private LocalTime orario;

	/*ASSOCIAZIONI*/
	
	@ManyToOne
	private Film film;
	
	@OneToMany(mappedBy = "proiezione", cascade = {CascadeType.REMOVE})
	private List<Prenotazione> prenotazioni;
	
	public Proiezione() {
		this.prenotazioni = new LinkedList<Prenotazione>();
	}
	
	
}
