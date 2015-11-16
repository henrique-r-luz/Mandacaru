package model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;

import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Transient;


@Entity
public class Corcamento implements Serializable  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue
	private int id;
	private int quantidade;
	@OneToMany(mappedBy="orcamento",fetch=FetchType.LAZY)
	private List<Ccotacao> cotacao = new ArrayList<Ccotacao>();
	@ManyToOne(fetch=FetchType.LAZY)
	private Cpedidos pedidos;
	@ManyToOne(fetch=FetchType.LAZY)
	private Cprocesso processo;
	@ManyToOne
	private Citem item;
	@Transient
	private BigDecimal valorMedio = BigDecimal.ZERO;
	@Transient
    private String numeroProcesso;
	@Transient
    private Cfracassados fracassados;
	
	
	

	



	public Cprocesso getProcesso() {
		return processo;
	}




	public void setProcesso(Cprocesso processo) {
		this.processo = processo;
	}




	public Cfracassados getFracassados() {
		return fracassados;
	}




	public void setFracassados(Cfracassados fracassados) {
		this.fracassados = fracassados;
	}




	public String getNumeroProcesso() {
		return numeroProcesso;
	}




	public void setNumeroProcesso(String numeroProcesso) {
		this.numeroProcesso = numeroProcesso;
	}




	public Citem getItem() {
		return item;
	}
	
	
	

	public BigDecimal getValorMedio() {
		return valorMedio;
	}




	public void setValorMedio(BigDecimal valorMedio) {
		this.valorMedio = valorMedio;
	}




	public List<Ccotacao> getCotacao() {
		return cotacao;
	}

	public void setCotacao(List<Ccotacao> cotacao) {
		this.cotacao = cotacao;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	public void setItem(Citem item) {
		this.item = item;
	}
	
	
	public void adionaCotacao(Ccotacao cota){
		cotacao.add(cota);
	}
	
	public void removeCotacao(Ccotacao cota){
		cotacao.remove(cota);
	}
	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Cpedidos getPedidos() {
		return pedidos;
	}

	public void setPedidos(Cpedidos pedidos) {
		this.pedidos = pedidos;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((item == null) ? 0 : item.hashCode());
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
		Corcamento other = (Corcamento) obj;
		if (item == null) {
			if (other.item != null)
				return false;
		} else if (!item.equals(other.item))
			return false;
		return true;
	}

}
