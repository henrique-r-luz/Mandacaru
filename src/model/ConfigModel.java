package model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class ConfigModel implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	@Id
	@GeneratedValue
	private int id;
	
	
	private String urlBase;
	private String urlDiretorio;
	private String ipServidor;
	private boolean producao;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUrlBase() {
		return urlBase;
	}
	public void setUrlBase(String urlBase) {
		this.urlBase = urlBase;
	}
	public String getUrlDiretorio() {
		return urlDiretorio;
	}
	public void setUrlDiretorio(String urlDiretorio) {
		this.urlDiretorio = urlDiretorio;
	}
	public String getIpServidor() {
		return ipServidor;
	}
	public void setIpServidor(String ipServidor) {
		this.ipServidor = ipServidor;
	}
	
	
	
	public boolean isProducao() {
		return producao;
	}
	public void setProducao(boolean producao) {
		this.producao = producao;
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
		ConfigModel other = (ConfigModel) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
	
	
	

}
