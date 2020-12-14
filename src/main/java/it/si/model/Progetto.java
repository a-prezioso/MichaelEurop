package it.si.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "Progetto")
public class Progetto {

	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "progetto_generator")
	@SequenceGenerator(name = "progetto_generator", sequenceName = "Progetto_sec", initialValue = 1, allocationSize = 1)
	@Column(name = "IDProgetto", updatable = false, nullable = false)
	private Integer chiaveProgetto;
	
	@Column(name="Codice")
	private String codice;
	
	@Column(name="Progetto")
	private String progetto;
	
	

	public Integer getChiaveProgetto() {
		return chiaveProgetto;
	}

	public void setChiaveProgetto(Integer chiaveProgetto) {
		this.chiaveProgetto = chiaveProgetto;
	}

	public String getCodice() {
		return codice;
	}

	public void setCodice(String codice) {
		this.codice = codice;
	}

	public String getProgetto() {
		return progetto;
	}

	public void setProgetto(String progetto) {
		this.progetto = progetto;
	}
	
	
}
