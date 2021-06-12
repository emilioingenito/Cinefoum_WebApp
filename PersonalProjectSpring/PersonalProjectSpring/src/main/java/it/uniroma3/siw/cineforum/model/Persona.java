package it.uniroma3.siw.cineforum.model;

import java.time.LocalDate;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.MappedSuperclass;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@MappedSuperclass
@NoArgsConstructor
public @Data abstract class Persona {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@NonNull
	private String nome;

	@NonNull
	private String cognome;

	@NonNull
	private LocalDate dataNascita;
	
	private LocalDate dataMorte;

	@NonNull
	private String luogoNascita;
	
	private String luogoMorte;
	
	@Lob
	private String foto;
}
