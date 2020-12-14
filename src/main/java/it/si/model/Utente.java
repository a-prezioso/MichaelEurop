package it.si.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


@Entity
@Table(name = "Utente")
public class Utente {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "utente_generator")
	@SequenceGenerator(name = "utente_generator", sequenceName = "Utente_sec", initialValue = 1, allocationSize = 1)
	@Column(name = "IDUtente", updatable = false, nullable = false)
	private Integer chiaveUtente;
	
	@Column(name = "Username", nullable = false)
	private String username;
	
	@Column(name = "Password", nullable = false)
	private String password;
	
	@Column(name = "Valido")
	private int valido;
	
	@Column(name = "Ruoli")
	private String ruoli = "";
	
	@Column(name = "Permessi")
	private String permessi = "";

	public Utente(String username, String password, String ruoli, String permessi) {
		this.username = username;
		this.password = password;
		this.ruoli = ruoli;
		this.permessi = permessi;
		this.valido = 1;
	}
	
	public Utente () {}

	public Integer getChiaveUtente() {
		return chiaveUtente;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public int getValido() {
		return valido;
	}

	public String getRuoli() {
		return ruoli;
	}

	public String getPermessi() {
		return permessi;
	}

	public List<String> getRuoliList() {
		if(this.ruoli.length()>0) {
			return Arrays.asList(this.ruoli.split(","));
		} else {
			return new ArrayList<String>();
		}
	}
	
	public List<String> getPermessiList() {
		if(this.permessi.length()>0) {
			return Arrays.asList(this.permessi.split(","));
		} else {
			return new ArrayList<String>();
		}
	}
	
	
	
	

}
