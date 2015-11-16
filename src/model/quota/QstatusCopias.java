package model.quota;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;


@Entity
public class QstatusCopias implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	@Id
	@GeneratedValue
	private int id;
	private String justificativa;
    //true copias abatidas
	//false copias validas
	//null copias calidas
	private boolean status;
	@OneToOne
	@JoinColumn(name = "copias_id", unique=true) 
	private QcopiasRealizadas copias;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getJustificativa() {
		return justificativa;
	}
	public void setJustificativa(String justificativa) {
		this.justificativa = justificativa;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	public QcopiasRealizadas getCopias() {
		return copias;
	}
	public void setCopias(QcopiasRealizadas copias) {
		this.copias = copias;
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
		QstatusCopias other = (QstatusCopias) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
	
	
	
	
}
