package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;


@Entity
public class Cfracassados implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	
	@Id
	@GeneratedValue
	private int id;
	@Column(unique=true)
	private String titulo;
	private String justificativa;
	@ManyToMany(cascade=CascadeType.ALL)
	private List<Citem> item = new ArrayList<Citem>() ;
	
	
	
	
	public void addItem(Citem obj){
		item.add(obj);
	}
	
	
	public void removeItem(Citem obj){
		item.remove(obj);
	}
	public List<Citem> getItem() {
		return item;
	}
	public void setItem(List<Citem> item) {
		this.item = item;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getJustificativa() {
		return justificativa;
	}
	public void setJustificativa(String justificativa) {
		this.justificativa = justificativa;
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
		Cfracassados other = (Cfracassados) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
	
	

}
