package it.uniroma3.siw.cineforum.service;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import it.uniroma3.siw.cineforum.model.Attore;
import it.uniroma3.siw.cineforum.repository.AttoreRepository;

@Service
public class AttoreService {
	
	@Autowired
	private AttoreRepository attoreRepository; 
	
	@Transactional
	public Attore saveAttoreToDB(MultipartFile file,String nome, String cognome, LocalDate dataDiNascita, LocalDate dataDiMorte,
			                   String luogoDiNascita,String luogoDiMorte){
	
		Attore a = new Attore();
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());
		if (fileName.contains("..")) {
			System.out.println("not a a valid file");
		}

		try {
			a.setFoto(Base64.getEncoder().encodeToString(file.getBytes()));
		} catch (IOException e) {
			e.printStackTrace();
		}


		a.setNome(nome);
		a.setCognome(cognome);
		a.setDataNascita(dataDiNascita);
		a.setDataMorte(dataDiMorte);
		a.setLuogoNascita(luogoDiNascita);
		a.setLuogoMorte(luogoDiMorte);
		
		this.inserisci(a);
		return a;
	}
	
	@Transactional
	public long numeroAttori() {
		return this.attoreRepository.count();
	}
	
	@Transactional
	public Attore inserisci(Attore attore) {
		return this.attoreRepository.save(attore);
	}
	
	@Transactional
	public void elimina(Attore attore) {
			attoreRepository.delete(attore);
	}
	
	@Transactional 
	public void eliminaTutti() {
		this.attoreRepository.deleteAll();
	}

	@Transactional
	public List<Attore> tutti() {
		return (List<Attore>) this.attoreRepository.findAll();
	}

	@Transactional
	public Attore attorePerId(Long id) {
		Optional<Attore> optional = this.attoreRepository.findById(id);
		if (optional.isPresent())
			return optional.get();
		else 
			return null;
	}
	
	@Transactional
	public List<Attore> attorePerNome(String nome) {
		return this.attoreRepository.findByNome(nome);
	}
	
	@Transactional
	public List<Attore> attorePerCognome(String cognome) {
		return this.attoreRepository.findByCognome(cognome);
	}
	
	@Transactional
	public List<Attore> attorePerNomeECognome(String nome, String cognome) {
		return this.attoreRepository.findByNomeAndCognome(nome,cognome);
	}

	@Transactional
	public boolean alreadyExists(Attore attore) {
		List<Attore> attori = this.attoreRepository.findByCognome(attore.getCognome());
		return attori.size() > 0;
	}
}
