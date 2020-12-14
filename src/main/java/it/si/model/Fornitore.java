package it.si.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "Fornitore")
public class Fornitore {

	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "fornitore_generator")
	@SequenceGenerator(name = "fornitore_generator", sequenceName = "Fornitore_sec", initialValue = 1, allocationSize = 1)
	@Column(name = "IDFornitore", updatable = false, nullable = false)
	private Integer chiaveFornitore;
	
	@Column(name="RagioneSociale")
	private String ragioneSociale;
	
	@Column(name="Indirizzo")
	private String indirizzo;

	@Column(name="Citta")
	private String citta;
	
	@Column(name="CAP")
	private String cap;
	
	@Column(name="Provincia")
	private String provincia;
	
	@Column(name="PartitaIVA")
	private String partitaIVA;
	
	@Column(name="Telefono")
	private Integer telefono;
	
	@Column(name="Referente")
	private String referente;
	
	@OneToMany(mappedBy = "fornitore")
	private List<Preventivo> preventivi;
	
	@OneToMany(mappedBy = "fornitore")
	private List<OrdineAcquisto> ordini;
	
	@OneToMany(mappedBy = "fornitore")
	private List<FatturaPassiva> fatture;
	
	

	public Integer getChiaveFornitore() {
		return chiaveFornitore;
	}

	public void setChiaveFornitore(Integer chiaveFornitore) {
		this.chiaveFornitore = chiaveFornitore;
	}

	public String getRagioneSociale() {
		return ragioneSociale;
	}

	public void setRagioneSociale(String ragioneSociale) {
		this.ragioneSociale = ragioneSociale;
	}

	public String getIndirizzo() {
		return indirizzo;
	}

	public void setIndirizzo(String indirizzo) {
		this.indirizzo = indirizzo;
	}

	public String getCitta() {
		return citta;
	}

	public void setCitta(String citta) {
		this.citta = citta;
	}

	public String getCap() {
		return cap;
	}

	public void setCap(String cap) {
		this.cap = cap;
	}

	public String getProvincia() {
		return provincia;
	}

	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}

	public String getPartitaIVA() {
		return partitaIVA;
	}

	public void setPartitaIVA(String partitaIVA) {
		this.partitaIVA = partitaIVA;
	}

	public Integer getTelefono() {
		return telefono;
	}

	public void setTelefono(Integer telefono) {
		this.telefono = telefono;
	}

	public String getReferente() {
		return referente;
	}

	public void setReferente(String referente) {
		this.referente = referente;
	}

	public List<Preventivo> getPreventivi() {
		return preventivi;
	}

	public void setPreventivi(List<Preventivo> preventivi) {
		this.preventivi = preventivi;
	}

	public List<OrdineAcquisto> getOrdini() {
		return ordini;
	}

	public void setOrdini(List<OrdineAcquisto> ordini) {
		this.ordini = ordini;
	}

	public List<FatturaPassiva> getFatture() {
		return fatture;
	}

	public void setFatture(List<FatturaPassiva> fatture) {
		this.fatture = fatture;
	}
	
	
}
