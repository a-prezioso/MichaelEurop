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
@Table(name = "Ordineacquisto")
public class OrdineAcquisto {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ordineacquisto_generator")
	@SequenceGenerator(name = "ordineacquisto_generator", sequenceName = "Ordineacquisto_sec", initialValue = 1, allocationSize = 1)
	@Column(name = "IDOrdineacquisto", updatable = false, nullable = false)
	private Integer chiaveOrdineAcquisto;
	
	@Column(name = "Importo")
	private Double importo;
	
	@Column(name = "Numero")
	private String numero;
	
	@Column(name = "Ordineacquisto")
	private String ordineAcquisto;
	
	@Column(name = "Data")
	@Temporal(TemporalType.DATE)
	private Date data;
	
	@ManyToOne
	@JoinColumn(name="IDFornitore")
	private Fornitore fornitore;
	
	@OneToMany(cascade = CascadeType.ALL,orphanRemoval = true)
	@JoinColumn(name="IDOrdineacquisto")
	private List<OrdineAcquistoDettaglio> dettagli;
	
	
	public OrdineAcquisto () {}
	
	
	
	public Integer getChiaveOrdineAcquisto() {
		return chiaveOrdineAcquisto;
	}

	public void setChiaveOrdineAcquisto(Integer chiaveOrdineAcquisto) {
		this.chiaveOrdineAcquisto = chiaveOrdineAcquisto;
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

	public String getOrdineAcquisto() {
		return ordineAcquisto;
	}

	public void setOrdineAcquisto(String ordineAcquisto) {
		this.ordineAcquisto = ordineAcquisto;
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

	public List<OrdineAcquistoDettaglio> getDettagli() {
		return dettagli;
	}

	public void setDettagli(List<OrdineAcquistoDettaglio> dettagli) {
		this.dettagli = dettagli;
	}
	
	
//	@Entity
//	@Table(name = "Ordineacquistodettaglio")
//	public class Dettaglio {
//		
//
//		@Id
//		@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ordineacquistodettaglio_generator")
//		@SequenceGenerator(name = "ordineacquistodettaglio_generator", sequenceName = "Ordineacquistodettaglio_sec", initialValue = 1, allocationSize = 1)
//		@Column(name = "IDOrdineacquistodettaglio", updatable = false, nullable = false)
//		private Integer chiaveOrdineAcquistoDettaglio;
//		
//		@ManyToOne
//		@JoinColumn(name="IDSpesainvestimento")
//		private SpesaInvestimento spesaInvestimento;
//		
//		@ManyToOne
//		@JoinColumn(name="IDProgetto")
//		private Progetto progetto;
//		
//		@Column(name = "descrizione")
//		private String descrizione;
//		
//		@Column(name = "Importounitario")
//		private Double importoUnitario;
//		
//		@Column(name = "quantita")
//		private Integer quantita;
//		
//		
//		
//		
//		
//		
//		
//		public Integer getChiaveOrdineAcquistoDettaglio() {
//			return chiaveOrdineAcquistoDettaglio;
//		}
//
//		public void setChiaveOrdineAcquistoDettaglio(Integer chiaveOrdineAcquistoDettaglio) {
//			this.chiaveOrdineAcquistoDettaglio = chiaveOrdineAcquistoDettaglio;
//		}
//
//		public SpesaInvestimento getSpesaInvestimento() {
//			return spesaInvestimento;
//		}
//
//		public void setSpesaInvestimento(SpesaInvestimento spesaInvestimento) {
//			this.spesaInvestimento = spesaInvestimento;
//		}
//
//		public Progetto getProgetto() {
//			return progetto;
//		}
//
//		public void setProgetto(Progetto progetto) {
//			this.progetto = progetto;
//		}
//
//		public String getDescrizione() {
//			return descrizione;
//		}
//
//		public void setDescrizione(String descrizione) {
//			this.descrizione = descrizione;
//		}
//
//		public Double getImportoUnitario() {
//			return importoUnitario;
//		}
//
//		public void setImportoUnitario(Double importoUnitario) {
//			this.importoUnitario = importoUnitario;
//		}
//
//		public Integer getQuantita() {
//			return quantita;
//		}
//
//		public void setQuantita(Integer quantita) {
//			this.quantita = quantita;
//		}
//		
//		
//		
//		
//	}
	
	
	
	
}
