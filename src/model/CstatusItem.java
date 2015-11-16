package model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;


@Entity
public class CstatusItem implements Serializable  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private int id;
	
	private long  tempo;
	@ManyToOne
	private Citem item;
	@ManyToOne
	private Cstatus status;
	
	
	
	
	
	
	public long getTempo() {
		return tempo;
	}
	public void setTempo(long tempo) {
		this.tempo = tempo;
	}
	public Citem getItem() {
		return item;
	}
	public void setItem(Citem item) {
		this.item = item;
	}
	public Cstatus getStatus() {
		return status;
	}
	public void setStatus(Cstatus status) {
		this.status = status;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
		CstatusItem other = (CstatusItem) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
	
	
	
	
}
