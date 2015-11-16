package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class CinfoMaterialGenerico {
	
	@Id
	@GeneratedValue
	private int id;
	@Column(unique=true)
	private String descricao;
	@ManyToOne
	private Ccatmat catmat;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public Ccatmat getCatmat() {
		return catmat;
	}
	public void setCatmat(Ccatmat catmat) {
		this.catmat = catmat;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CinfoMaterialGenerico other = (CinfoMaterialGenerico) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
	
	
	

}
