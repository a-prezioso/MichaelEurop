package it.si.model;

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

@Entity
@Table(name = "Spesainvestimento")
public class SpesaInvestimento {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "spesainvestimento_generator")
	@SequenceGenerator(name = "spesainvestimento_generator", sequenceName = "Spesainvestimento_sec", initialValue = 1, allocationSize = 1)
	@Column(name = "IDSpesainvestimento", updatable = false, nullable = false)
	private Integer chiaveSpesaInvestimento;
	
	@Column(name = "Spesainvestimento")
	private String spesaInvestimento;
	
	@ManyToOne
	@JoinColumn(name="IDSottocategoria")
	private Sottocategoria sottocategoria;
	
	@OneToMany(mappedBy = "spesaInvestimento")
	private List<OrdineAcquistoDettaglio> ordineDettagli;

	
	
	public Integer getChiaveSpesaInvestimento() {
		return chiaveSpesaInvestimento;
	}

	public void setChiaveSpesaInvestimento(Integer chiaveSpesaInvestimento) {
		this.chiaveSpesaInvestimento = chiaveSpesaInvestimento;
	}

	public String getSpesaInvestimento() {
		return spesaInvestimento;
	}

	public void setSpesaInvestimento(String spesaInvestimento) {
		this.spesaInvestimento = spesaInvestimento;
	}

	public Sottocategoria getSottocategoria() {
		return sottocategoria;
	}

	public void setSottocategoria(Sottocategoria sottocategoria) {
		this.sottocategoria = sottocategoria;
	}

	public List<OrdineAcquistoDettaglio> getOrdineDettagli() {
		return ordineDettagli;
	}

	public void setOrdineDettagli(List<OrdineAcquistoDettaglio> ordineDettagli) {
		this.ordineDettagli = ordineDettagli;
	}
	
	
}
