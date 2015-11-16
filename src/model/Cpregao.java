package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;



@Entity
public class Cpregao implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	private int id;
	private String numero;
	private String descricao;
	@Temporal(TemporalType.DATE)
	private Date data;
	@ManyToOne
	private Cusuario usuario;
	@OneToMany
	private List<Cprocesso> processoList = new ArrayList<Cprocesso>();
	@Transient
    private String viewProcesso= "";  
	@ManyToOne
	private CstatusPedidos statusPedidos;
	
    
	
	
	
	public CstatusPedidos getStatusPedidos() {
		return statusPedidos;
	}




	public void setStatusPedidos(CstatusPedidos statusPedidos) {
		this.statusPedidos = statusPedidos;
	}




	public String getViewProcesso() {
	for(Cprocesso obj : processoList){
		viewProcesso +=  obj.getNumero()+"</br>";
	}
	
	return viewProcesso;
		//return viewProcesso;// crira um for para listar processo
	}


	

	public Cusuario getUsuario() {
		return usuario;
	}


	public void setUsuario(Cusuario usuario) {
		this.usuario = usuario;
	}


	public String getNumero() {
		return numero;
	}


	public void setNumero(String numero) {
		this.numero = numero;
	}


	


	public List<Cprocesso> getProcessoList() {
		return processoList;
	}




	public void setProcessoList(List<Cprocesso> processoList) {
		this.processoList = processoList;
	}




	public void addProcesso(Cprocesso processo){
		processoList.add(processo);
	}
	
	
	public void removeProcesso(Cprocesso processo){
		processoList.remove(processo);
	}
	
	
	

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
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
		Cpregao other = (Cpregao) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
	
	
	
	

}
