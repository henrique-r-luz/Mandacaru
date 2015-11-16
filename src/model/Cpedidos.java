package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.IndexColumn;
import org.springframework.transaction.annotation.Transactional;

@Entity

public class Cpedidos implements Serializable  {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue
	private int id;
	@Column(unique=true)
	private String nome;
	@ManyToOne
	private CstatusPedidos statusPedidos;
	@ManyToOne
	private Cusuario usuario;
	@OneToMany(mappedBy="pedidos",fetch=FetchType.LAZY)
	private List<Corcamento> orcamento = new ArrayList<Corcamento>();
	
	
	
	
	
	
	public CstatusPedidos getStatusPedidos() {
		return statusPedidos;
	}
	public void setStatusPedidos(CstatusPedidos statusPedidos) {
		this.statusPedidos = statusPedidos;
	}
	public List<Corcamento> getOrcamento() {
		return orcamento;
	}
	public void setOrcamento(List<Corcamento> orcamento) {
		this.orcamento = orcamento;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	
	

	public Cusuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Cusuario usuario) {
		this.usuario = usuario;
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
		Cpedidos other = (Cpedidos) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
	

}
