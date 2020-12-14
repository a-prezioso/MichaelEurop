package it.si.model;

import java.util.Date;
import java.util.List;

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
@Table(name = "Preventivo")
public class Preventivo {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "preventivo_generator")
	@SequenceGenerator(name = "preventivo_generator", sequenceName = "Preventivo_sec", initialValue = 1, allocationSize = 1)
	@Column(name = "IDPreventivo", updatable = false, nullable = false)
	private Integer chiavePreventivo;
	
	@Column(name = "Codice")
	private String codice;
	
	@Column(name = "Preventivo")
	private String preventivo;
	
	@Column(name = "importo")
	private Double importo;
	
	@Column(name = "Data")
	@Temporal(TemporalType.DATE)
	private Date data;
	
	@ManyToOne
	@JoinColumn(name="IDFornitore")
	private Fornitore fornitore;
	
	@OneToMany(mappedBy = "preventivo")
	private List<FatturaPassivaDettaglio> fatturaDettagli;
	
	
	
	

	public Integer getChiavePreventivo() {
		return chiavePreventivo;
	}

	public void setChiavePreventivo(Integer chiavePreventivo) {
		this.chiavePreventivo = chiavePreventivo;
	}

	public String getCodice() {
		return codice;
	}

	public void setCodice(String codice) {
		this.codice = codice;
	}

	public String getPreventivo() {
		return preventivo;
	}

	public void setPreventivo(String preventivo) {
		this.preventivo = preventivo;
	}

	public Double getImporto() {
		return importo;
	}

	public void setImporto(Double importo) {
		this.importo = importo;
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

	public List<FatturaPassivaDettaglio> getFatturaDettagli() {
		return fatturaDettagli;
	}

	public void setFatturaDettagli(List<FatturaPassivaDettaglio> fatturaDettagli) {
		this.fatturaDettagli = fatturaDettagli;
	}
	
	

}
