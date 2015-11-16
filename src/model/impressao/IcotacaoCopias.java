package model.impressao;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class IcotacaoCopias implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	@Id
	@GeneratedValue
	private int id;
	private BigDecimal valor = BigDecimal.ZERO;
	@Temporal(TemporalType.DATE)
	private Date dataInicial;
	@Temporal(TemporalType.DATE)
	private Date dataFinal;
	@OneToMany(mappedBy="cotacao")
	private List<IdocCopias> copias = new ArrayList<IdocCopias>();
	
	
	
	
	
	public void addCopias(IdocCopias objCopias){
		 copias.add(objCopias);
	}
	
	
	public void removeCopias(IdocCopias objCopias){
		copias.remove(objCopias);
	}
	
	
	public BigDecimal getValor() {
		return valor;
	}
	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Date getDataInicial() {
		return dataInicial;
	}
	public void setDataInicial(Date dataInicial) {
		this.dataInicial = dataInicial;
	}
	public Date getDataFinal() {
		return dataFinal;
	}
	public void setDataFinal(Date dataFinal) {
		this.dataFinal = dataFinal;
	}
	public List<IdocCopias> getCopias() {
		return copias;
	}
	public void setCopias(List<IdocCopias> copias) {
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
		IcotacaoCopias other = (IcotacaoCopias) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
	
	

}
