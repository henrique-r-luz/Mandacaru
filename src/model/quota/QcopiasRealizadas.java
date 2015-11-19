package model.quota;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import model.impressao.Iservidor;


@Entity
public class QcopiasRealizadas  {

	/**
	 * 
	 */
	
	
	
	
	@Id
	@GeneratedValue
	private int id;
	private String urlDocumento;
	private String nomeDocumento;
	@Temporal(TemporalType.DATE)
	private Date data;
	@ManyToOne
	private Qimpressora impressora;
	@ManyToOne
	private Iservidor servidor;
	private int numeroCopias = 1;
	@OneToOne (mappedBy="copias")
	private QstatusCopias statusCopias;
	
	
	
	
	
	public QstatusCopias getStatusCopias() {
		return statusCopias;
	}
	public void setStatusCopias(QstatusCopias statusCopias) {
		this.statusCopias = statusCopias;
	}
	public int getNumeroCopias() {
		return numeroCopias;
	}
	public void setNumeroCopias(int numeroCopias) {
		this.numeroCopias = numeroCopias;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUrlDocumento() {
		return urlDocumento;
	}
	public void setUrlDocumento(String urlDocumento) {
		this.urlDocumento = urlDocumento;
	}
	public String getNomeDocumento() {
		return nomeDocumento;
	}
	public void setNomeDocumento(String nomeDocumento) {
		this.nomeDocumento = nomeDocumento;
	}
	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data;
	}
	public Qimpressora getImpressora() {
		return impressora;
	}
	public void setImpressora(Qimpressora impressora) {
		this.impressora = impressora;
	}
	public Iservidor getServidor() {
		return servidor;
	}
	public void setServidor(Iservidor servidor) {
		this.servidor = servidor;
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
		QcopiasRealizadas other = (QcopiasRealizadas) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
	
	
	
	
	
	

}
