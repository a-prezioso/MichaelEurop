package it.si.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import it.si.model.Utente;

@SuppressWarnings("serial")
public class UtentePrincipale implements UserDetails{

	
	private Utente utente;
	
	public UtentePrincipale(Utente utente) {
		this.utente = utente;
	}
	
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		
		List<GrantedAuthority> authorities = new ArrayList<>();
		
		// Lista di permessi (nome)
		this.utente.getPermessiList().forEach(p -> {
			GrantedAuthority authority = new SimpleGrantedAuthority(p);
			authorities.add(authority);
		});
		// Lista di ruoli (ROLE_nome)
		this.utente.getRuoliList().forEach(r -> {
			GrantedAuthority authority = new SimpleGrantedAuthority("ROLE_"+r);
			authorities.add(authority);
		});
		
		return authorities;
	}

	@Override
	public String getPassword() {
		return this.utente.getPassword();
	}

	@Override
	public String getUsername() {
		return this.utente.getUsername();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return this.utente.getValido() == 1;
	}

}
