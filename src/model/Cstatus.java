package model;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;



import javax.persistence.Entity;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import javax.persistence.CascadeType;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;



@Entity
public class Cstatus implements Serializable  {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue
	private int id;
	private int nome;
	
	@OneToMany(mappedBy="status",cascade=CascadeType.REMOVE)
	private List<CstatusItem> statusItem = new ArrayList<CstatusItem>();
	

	
	public List<CstatusItem> getStatusItem() {
		return statusItem;
	}
	public void setStatusItem(List<CstatusItem> statusItem) {
		this.statusItem = statusItem;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getNome() {
		return nome;
	}
	public void setNome(int nome) {
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
		Cstatus other = (Cstatus) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
	

}
