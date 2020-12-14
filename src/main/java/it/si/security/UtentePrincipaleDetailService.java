package it.si.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import it.si.model.Utente;
import it.si.repository.UtenteRepository;

@Service
public class UtentePrincipaleDetailService implements UserDetailsService{

	@Autowired
	private UtenteRepository utenteRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Utente utente = utenteRepository.findByUsername(username);
		return new UtentePrincipale(utente!=null?utente:new Utente());
	}

}
