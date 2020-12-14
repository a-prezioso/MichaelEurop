package it.si.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "AliquotaIVA")
public class AliquotaIVA {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "aliquota_generator")
	@SequenceGenerator(name = "aliquota_generator", sequenceName = "AliquotaIVA_sec", initialValue = 1, allocationSize = 1)
	@Column(name = "IDAliquotaIVA", updatable = false, nullable = false)
	private Integer chiaveAliquotaIVA;
	
	@Column(name = "Aliquota")
	private Integer aliquota;
	
	@Column(name = "Descrizione")
	private String descrizione;
	
	


	public Integer getChiaveAliquotaIVA() {
		return chiaveAliquotaIVA;
	}

	public void setChiaveAliquotaIVA(Integer chiaveAliquotaIVA) {
		this.chiaveAliquotaIVA = chiaveAliquotaIVA;
	}

	public Integer getAliquota() {
		return aliquota;
	}

	public void setAliquota(Integer aliquota) {
		this.aliquota = aliquota;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	
	

}
