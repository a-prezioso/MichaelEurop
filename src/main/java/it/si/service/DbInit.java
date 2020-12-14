package it.si.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import it.si.model.Utente;
import it.si.repository.UtenteRepository;

@Service
public class DbInit implements CommandLineRunner{
	
	@Autowired
	private UtenteRepository utenteRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	public DbInit(UtenteRepository utenteRepository) {
		this.utenteRepository = utenteRepository;
	}

	@Override
	public void run(String... args) throws Exception {
		
		this.utenteRepository.deleteAll();
		
		Utente utente = new Utente("pippo", passwordEncoder.encode("pluto"), "USER", "");
		Utente admin = new Utente("admin", passwordEncoder.encode("admin123"), "ADMIN", "ORDINE_LIST,ORDINE_EDIT");
		Utente manager = new Utente("manager", passwordEncoder.encode("manager123"), "MANAGER", "ORDINE_LIST");
		
		List<Utente> utenti = Arrays.asList(utente,admin,manager);
		
		this.utenteRepository.saveAll(utenti);
		
	}

}
