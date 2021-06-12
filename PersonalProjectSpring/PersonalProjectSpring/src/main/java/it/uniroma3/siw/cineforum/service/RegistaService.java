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

import it.uniroma3.siw.cineforum.model.Regista;
import it.uniroma3.siw.cineforum.repository.RegistaRepository;

@Service
public class RegistaService {
	
	@Autowired
	private RegistaRepository registaRepository; 
	
	@Transactional
	public Regista saveRegistaToDB(MultipartFile file,String nome, String cognome, LocalDate dataDiNascita, LocalDate dataDiMorte,
			                   String luogoDiNascita,String luogoDiMorte){
	
		Regista r = new Regista();
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());
		if (fileName.contains("..")) {
			System.out.println("not a a valid file");
		}

		try {
			r.setFoto(Base64.getEncoder().encodeToString(file.getBytes()));
		} catch (IOException e) {
			e.printStackTrace();
		}


		r.setNome(nome);
		r.setCognome(cognome);
		r.setDataNascita(dataDiNascita);
		r.setDataMorte(dataDiMorte);
		r.setLuogoNascita(luogoDiNascita);
		r.setLuogoMorte(luogoDiMorte);
		
		this.inserisci(r);
		return r;
	}
	
	@Transactional
	public long numeroRegisti() {
		return this.registaRepository.count();
	}
	
	@Transactional
	public Regista inserisci(Regista regista) {
		return this.registaRepository.save(regista);
	}
	
	@Transactional
	public void elimina(Regista regista) {
		registaRepository.delete(regista);
	}
	
	@Transactional 
	public void eliminaTutti() {
		this.registaRepository.deleteAll();
	}

	@Transactional
	public List<Regista> tutti() {
		return (List<Regista>) this.registaRepository.findAll();
	}

	@Transactional
	public Regista registaPerId(Long id) {
		Optional<Regista> optional = this.registaRepository.findById(id);
		if (optional.isPresent())
			return optional.get();
		else 
			return null;
	}
	
	@Transactional
	public List<Regista> registaPerNome(String nome) {
		return this.registaRepository.findByNome(nome);
	}
	
	@Transactional
	public List<Regista> registaPerCognome(String cognome) {
		return this.registaRepository.findByCognome(cognome);
	}
	
	@Transactional
	public List<Regista> registaPerNomeECognome(String nome, String cognome) {
		return this.registaRepository.findByNomeAndCognome(nome,cognome);
	}

	@Transactional
	public boolean alreadyExists(Regista regista) {
		List<Regista> registi = this.registaRepository.findByCognome(regista.getCognome());
		return registi.size() > 0;
	}
}
