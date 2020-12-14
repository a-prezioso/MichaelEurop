package it.si.model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "Fatturapassiva")
public class FatturaPassiva {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "fatturapassiva_generator")
	@SequenceGenerator(name = "fatturapassiva_generator", sequenceName = "Fatturapassiva_sec", initialValue = 1, allocationSize = 1)
	@Column(name = "IDFatturapassiva", updatable = false, nullable = false)
	private Integer chiaveFatturaPassiva;
	
	@Column(name = "Importo")
	private Double importo;
	
	@Column(name = "Numero")
	private String numero;
	
	@Column(name = "Descrizione")
	private String descrizione;
	
	@Column(name = "Data")
	@Temporal(TemporalType.DATE)
	private Date data;
	
	@ManyToOne
	@JoinColumn(name="IDFornitore")
	private Fornitore fornitore;
	
	@OneToMany(cascade = CascadeType.ALL,orphanRemoval = true)
	@JoinColumn(name="IDFatturapassiva")
	private List<FatturaPassivaDettaglio> dettagli;
	
	

	public Integer getChiaveFatturaPassiva() {
		return chiaveFatturaPassiva;
	}

	public void setChiaveFatturaPassiva(Integer chiaveFatturaPassiva) {
		this.chiaveFatturaPassiva = chiaveFatturaPassiva;
	}

	public Double getImporto() {
		return importo;
	}

	public void setImporto(Double importo) {
		this.importo = importo;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public Fornitore getFornitore() {
		return fornitore;
	}

	public void setFornitore(Fornitore fornitore) {
		this.fornitore = fornitore;
	}

	public List<FatturaPassivaDettaglio> getDettagli() {
		return dettagli;
	}

	public void setDettagli(List<FatturaPassivaDettaglio> dettagli) {
		this.dettagli = dettagli;
	}
	
	
	

}
