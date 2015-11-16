package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;


@Entity
public class Cprocesso implements Serializable  {
     
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue
	private int id;
	@Column(unique=true)
	private String numero;
	//@OneToMany(mappedBy="processo", fetch=FetchType.LAZY)
	//private List<CitemProcesso> itemProcesso  = new ArrayList<CitemProcesso>();
	@OneToMany(fetch=FetchType.LAZY)
	private List<Corcamento> orcamento  = new ArrayList<Corcamento>();
	@ManyToOne
	private Cusuario usuario;
	private  String descricao;
	@ManyToOne
	private CstatusPedidos statusPedidos;
	@ManyToOne
	private Cpregao pregao;
	
	
	
	
	public Cpregao getPregao() {
		return pregao;
	}
	public void setPregao(Cpregao pregao) {
		this.pregao = pregao;
	}
	public CstatusPedidos getStatusPedidos() {
		return statusPedidos;
	}
	public void setStatusPedidos(CstatusPedidos statusPedidos) {
		this.statusPedidos = statusPedidos;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public Cusuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Cusuario usuario) {
		this.usuario = usuario;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
	public List<Corcamento> getOrcamento() {
		return orcamento;
	}
	public void setOrcamento(List<Corcamento> orcamento) {
		this.orcamento = orcamento;
	}
	
	public void addOrcamento(Corcamento orca){
	   orcamento.add(orca);	
	}
	
	public void removeOrcamento(Corcamento orca){
		orcamento.remove(orca);
	}
	
	
	
	
	
}
