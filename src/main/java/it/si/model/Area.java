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
@Table(name = "Area")
public class Area {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "area_generator")
	@SequenceGenerator(name = "area_generator", sequenceName = "Area_sec", initialValue = 1, allocationSize = 1)
	@Column(name = "IDArea", updatable = false, nullable = false)
	private Integer chiaveArea;
	
	@Column(name = "Codice")
	private String codice;
	
	@Column(name = "Area")
	private String area;
	
	@OneToMany(mappedBy = "area")
	private List<Sottocategoria> sottocategorie;
	
	

	

	public Integer getChiaveArea() {
		return chiaveArea;
	}

	public void setChiaveArea(Integer chiaveArea) {
		this.chiaveArea = chiaveArea;
	}

	public String getCodice() {
		return codice;
	}

	public void setCodice(String codice) {
		this.codice = codice;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}
	
	public List<Sottocategoria> getSottocategorie() {
		return sottocategorie;
	}

	public void setSottocategorie(List<Sottocategoria> sottocategorie) {
		this.sottocategorie = sottocategorie;
	}
	
	
}
