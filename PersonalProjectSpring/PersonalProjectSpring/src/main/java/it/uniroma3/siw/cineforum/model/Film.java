package it.uniroma3.siw.cineforum.model;

import java.util.LinkedList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import lombok.Data;
import lombok.NonNull;

@Entity
public @Data class Film {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@NonNull
	private String titolo;
	
	@NonNull
	private String trama;
	
	@NonNull
	private Integer annoUscita;
	
	@Lob
	private String foto;
	
	/*ASSOCIAZIONI*/
	
	@ManyToMany(fetch = FetchType.EAGER)
	private List<Attore> attori;
	
	@ManyToOne
	private Regista regista;
	
	@OneToMany(mappedBy = "film")
	private List<Proiezione> proiezioni;
	
	public Film() {
		this.attori = new LinkedList<Attore>();
		this.proiezioni = new LinkedList<Proiezione>(); 
	}
	
	public void addAttore(Attore a) {
		this.attori.add(a);
	}

}
