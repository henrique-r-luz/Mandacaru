package model.impressao;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;



@Entity
public class IdocCopias implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue
	private int id;
	@Column(unique=true)
	private int idGlpi;

	private int numerodeCopias;
	@Temporal(TemporalType.DATE)
	private Date data;
	@ManyToOne
	private Iservidor servidor = new Iservidor();
	@ManyToOne
	private IcotacaoCopias cotacao ;
	
	
	
	public int getIdGlpi() {
		return idGlpi;
	}
	public void setIdGlpi(int idGlpi) {
		this.idGlpi = idGlpi;
	}
	public Iservidor getServidor() {
		return servidor;
	}
	public void setServidor(Iservidor servidor) {
		this.servidor = servidor;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	
	
	
	
	public IcotacaoCopias getCotacao() {
		return cotacao;
	}
	public void setCotacao(IcotacaoCopias cotacao) {
		this.cotacao = cotacao;
	}
	public int getNumerodeCopias() {
		return numerodeCopias;
	}
	public void setNumerodeCopias(int numerodeCopias) {
		this.numerodeCopias = numerodeCopias;
	}
	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data;
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
		IdocCopias other = (IdocCopias) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
	
	
	
	

}
