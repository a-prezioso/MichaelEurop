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
@Table(name = "Sottocategoria")
public class Sottocategoria {

	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sottocategoria_generator")
	@SequenceGenerator(name = "sottocategoria_generator", sequenceName = "Sottocategoria_sec", initialValue = 1, allocationSize = 1)
	@Column(name = "IDSottocategoria", updatable = false, nullable = false)
	private Integer chiaveSottocategoria;
	
	@Column(name="Codice")
	private String codice;
	
	@Column(name="Sottocategoria")
	private String sottocategoria;
	
	@Column(name="Budget")
	private Double budget;
	
	@Column(name="BudgetSpeso")
	private Double budgetSpeso;
	
	@ManyToOne
	@JoinColumn(name="IDArea")
	private Area area;
	
	@OneToMany(mappedBy = "sottocategoria")
	private List<SpesaInvestimento> speseInvestimento;
	
	

	public Integer getChiaveSottocategoria() {
		return chiaveSottocategoria;
	}

	public void setChiaveSottocategoria(Integer chiaveSottocategoria) {
		this.chiaveSottocategoria = chiaveSottocategoria;
	}

	public String getCodice() {
		return codice;
	}

	public void setCodice(String codice) {
		this.codice = codice;
	}

	public String getSottocategoria() {
		return sottocategoria;
	}

	public void setSottocategoria(String sottocategoria) {
		this.sottocategoria = sottocategoria;
	}

	public Double getBudget() {
		return budget;
	}

	public void setBudget(Double budget) {
		this.budget = budget;
	}

	public Double getBudgetSpeso() {
		return budgetSpeso;
	}

	public void setBudgetSpeso(Double budgetSpeso) {
		this.budgetSpeso = budgetSpeso;
	}

	public Area getArea() {
		return area;
	}

	public void setArea(Area area) {
		this.area = area;
	}

	public List<SpesaInvestimento> getSpeseInvestimento() {
		return speseInvestimento;
	}

	public void setSpeseInvestimento(List<SpesaInvestimento> speseInvestimento) {
		this.speseInvestimento = speseInvestimento;
	}
	
	
	
}
