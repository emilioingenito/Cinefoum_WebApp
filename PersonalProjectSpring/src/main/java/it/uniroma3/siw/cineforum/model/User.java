package it.uniroma3.siw.cineforum.model;

import java.util.LinkedList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;
import lombok.NonNull;

@Entity
@Table(name = "users") // cambiamo nome perchè in postgres user e' una parola riservata
public @Data class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;	
	
	@NonNull
	private String nome;
	
	@NonNull
	private String cognome;
	
	/*ASSOCIAZIONI*/

	@OneToMany(mappedBy = "socio", fetch = FetchType.EAGER, cascade = {CascadeType.REMOVE})
	private List<Prenotazione> prenotazioni;
	
	public User() {
		this.prenotazioni = new LinkedList<Prenotazione>(); //facciamo fetch e cascade in delete
	}

}
