package model;

import java.io.Serializable;
import java.util.Date;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;


@Entity
public class CitemProcesso implements Serializable  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private int id; 
	
	@ManyToOne
	private Citem item;
	@ManyToOne
	private Cprocesso processo;
	
    @Temporal(TemporalType.DATE)
	private Date data;
	
    
    
 

   
	
	public Citem getItem() {
		return item;
	}
	public void setItem(Citem item) {
		this.item = item;
	}
	public Cprocesso getProcesso() {
		return processo;
	}
	public void setProcesso(Cprocesso processo) {
		this.processo = processo;
	}
    
	

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}
	
	

	
	
	
	
	
}
