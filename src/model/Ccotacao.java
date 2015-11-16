package model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;

import javax.faces.context.FacesContext;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import controle.Config;


@Entity
public class Ccotacao implements Serializable  {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private int id;

	private BigDecimal valor;
	@Temporal(TemporalType.DATE)
	private Date data;
	@Column(unique=true)
	private String url;
	@ManyToOne(fetch=FetchType.LAZY)
	private Corcamento  orcamento;
	private String empresa;
	private String site;
	private String email;
	

	
	
	
	
	
	public String getSite() {
		return site;
	}
	public void setSite(String site) {
		this.site = site;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getEmpresa() {
		return empresa;
	}
	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public BigDecimal getValor() {
		return valor;
	}
	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}
	
	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data;
	}
	public String getUrl() {
		Config config = new Config();
		return config.getUrlBase()
				+ FacesContext.getCurrentInstance().getExternalContext()
				.getRequestContextPath() + "/"+config.getFileImageOrcamento()+"/" + url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public Corcamento getOrcamento() {
		return orcamento;
	}
	public void setOrcamento(Corcamento orcamento) {
		this.orcamento = orcamento;
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
		Ccotacao other = (Ccotacao) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
	
	
	
	
}



