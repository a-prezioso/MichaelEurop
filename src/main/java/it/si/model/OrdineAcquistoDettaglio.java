package it.si.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


@Entity
@Table(name = "Ordineacquistodettaglio")
public class OrdineAcquistoDettaglio {
	

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ordineacquistodettaglio_generator")
	@SequenceGenerator(name = "ordineacquistodettaglio_generator", sequenceName = "Ordineacquistodettaglio_sec", initialValue = 1, allocationSize = 1)
	@Column(name = "IDOrdineacquistodettaglio", updatable = false, nullable = false)
	private Integer chiaveOrdineAcquistoDettaglio;
	
	@ManyToOne
	@JoinColumn(name="IDSpesainvestimento")
	private SpesaInvestimento spesaInvestimento;
	
	@ManyToOne
	@JoinColumn(name="IDProgetto")
	private Progetto progetto;
	
	@Column(name = "Descrizione")
	private String descrizione;
	
	@Column(name = "Importounitario")
	private Double importoUnitario;
	
	@Column(name = "quantita")
	private Integer quantita;
	
	
	@ManyToOne
	@JoinColumn(name="IDOrdineacquisto")
	private OrdineAcquisto ordineAcquisto;
	
	
	
	
	
	
	
	
	
	public Integer getChiaveOrdineAcquistoDettaglio() {
		return chiaveOrdineAcquistoDettaglio;
	}

	public void setChiaveOrdineAcquistoDettaglio(Integer chiaveOrdineAcquistoDettaglio) {
		this.chiaveOrdineAcquistoDettaglio = chiaveOrdineAcquistoDettaglio;
	}

	public SpesaInvestimento getSpesaInvestimento() {
		return spesaInvestimento;
	}

	public void setSpesaInvestimento(SpesaInvestimento spesaInvestimento) {
		this.spesaInvestimento = spesaInvestimento;
	}

	public Progetto getProgetto() {
		return progetto;
	}

	public void setProgetto(Progetto progetto) {
		this.progetto = progetto;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public Double getImportoUnitario() {
		return importoUnitario;
	}

	public void setImportoUnitario(Double importoUnitario) {
		this.importoUnitario = importoUnitario;
	}

	public Integer getQuantita() {
		return quantita;
	}

	public void setQuantita(Integer quantita) {
		this.quantita = quantita;
	}

	public OrdineAcquisto getOrdineAcquisto() {
		return ordineAcquisto;
	}

	public void setOrdineAcquisto(OrdineAcquisto ordineAcquisto) {
		this.ordineAcquisto = ordineAcquisto;
	}

	
	
	
	
	
}
