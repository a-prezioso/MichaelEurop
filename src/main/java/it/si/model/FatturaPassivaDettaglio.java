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
@Table(name = "Fatturapassivadettaglio")
public class FatturaPassivaDettaglio {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "fatturapassivadettaglio_generator")
	@SequenceGenerator(name = "fatturapassivadettaglio_generator", sequenceName = "Fatturapassivadettaglio_sec", initialValue = 1, allocationSize = 1)
	@Column(name = "IDFatturapassivadettaglio", updatable = false, nullable = false)
	private Integer chiaveFatturaPassivaDettaglio;
	
	@ManyToOne
	@JoinColumn(name="IDSpesainvestimento")
	private SpesaInvestimento spesaInvestimento;
	
	@ManyToOne
	@JoinColumn(name="IDPreventivo")
	private Preventivo preventivo;
	
	@ManyToOne
	@JoinColumn(name="IDAliquotaIVA")
	private AliquotaIVA aliquota;
	
	@Column(name = "Descrizione")
	private String descrizione;
	
	@Column(name = "Importo")
	private Double importo;
	
	@ManyToOne
	@JoinColumn(name="IDFatturapassiva")
	private FatturaPassiva fatturaPassiva;
	
	
	

	public Integer getChiaveFatturaPassivaDettaglio() {
		return chiaveFatturaPassivaDettaglio;
	}

	public void setChiaveFatturaPassivaDettaglio(Integer chiaveFatturaPassivaDettaglio) {
		this.chiaveFatturaPassivaDettaglio = chiaveFatturaPassivaDettaglio;
	}

	public SpesaInvestimento getSpesaInvestimento() {
		return spesaInvestimento;
	}

	public void setSpesaInvestimento(SpesaInvestimento spesaInvestimento) {
		this.spesaInvestimento = spesaInvestimento;
	}

	public Preventivo getPreventivo() {
		return preventivo;
	}

	public void setPreventivo(Preventivo preventivo) {
		this.preventivo = preventivo;
	}

	public AliquotaIVA getAliquota() {
		return aliquota;
	}

	public void setAliquota(AliquotaIVA aliquota) {
		this.aliquota = aliquota;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public Double getImporto() {
		return importo;
	}

	public void setImporto(Double importo) {
		this.importo = importo;
	}

	public FatturaPassiva getFatturaPassiva() {
		return fatturaPassiva;
	}

	public void setFatturaPassiva(FatturaPassiva fatturaPassiva) {
		this.fatturaPassiva = fatturaPassiva;
	}
	
	
	
	
	
}
