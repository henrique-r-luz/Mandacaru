package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;




@Entity
public class CtipoCatMat implements Serializable  {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private int id;
	
	private String nome;
	private String sigla;
	
	@OneToMany(mappedBy="tipocatmat",fetch=FetchType.EAGER)
	private List<Ccatmat> catmat = new ArrayList<Ccatmat>();

	
	
	
	
	
	public String getSigla() {
		return sigla;
	}

	public void setSigla(String sigla) {
		this.sigla = sigla;
	}

	public List<Ccatmat> getCatmat() {
		return catmat;
	}

	public void setCatmat(List<Ccatmat> catmat) {
		this.catmat = catmat;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
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
		CtipoCatMat other = (CtipoCatMat) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
	
	
	

}
