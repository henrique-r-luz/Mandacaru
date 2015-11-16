package model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

import org.hibernate.annotations.IndexColumn;
import org.springframework.transaction.annotation.Transactional;


import dao.DAO;

@Entity
public class Citem implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private int id;

	@ManyToOne
	private Ccatmat catmat = new Ccatmat();
	@ManyToOne
	private Ctipo tipo = new Ctipo();
	@ManyToOne
	private Ccategoria categoria = new Ccategoria();
	
	
	//atributos transitorio==================================================
	@Transient
    private String numeroCatmat;
//	@Transient
	//private InState state = new SemPedido();
	
	@Transient
	private int numeroPedido;
	@Transient
	private Corcamento transOrcamento;
	@Transient
	private int quantidade;
	
	
	@ManyToMany(mappedBy="item",fetch=FetchType.LAZY)
	private List<CstatusItem> statusItem = new ArrayList<CstatusItem>();
	@OneToMany(mappedBy="item",fetch=FetchType.LAZY)
	private List<Corcamento> orcamento = new ArrayList<Corcamento>();
	@OneToMany(mappedBy="item",fetch=FetchType.LAZY)
	private List<CitemProcesso> itemProcesso = new ArrayList<CitemProcesso>();
	@ManyToMany
	private List<Cfracassados> fracassados = new ArrayList<Cfracassados>();

	//controle de estado //////////////////////////////////////////////////////////////////////////////////////////////////
	
	
	
	
	public Citem(){
		//semPedido();
	}
	
	
	
	



	/*public InState getState() {
		return state;
	}

	public void setState(InState state) {
		this.state = state;
	}

	public void semPedido(){
		state.semPedido(this);
	}
	public void emPedido(){
		state.emPedido(this);
		
	}
	public void enviadoDiretoria(){
	    state.enviadoDiretoria(this);	
	}
	public void enviadoCompras(){
		state.enviadoCompras(this);
	}
	public void enviadoAumoxarifado(){
		state.enviadoAumoxarifado(this);
	}
	public void entrege(Citem item){
		state.entrege(this);
	}
	
	public String getNameState() {
	     return state.getNameState();
	}*/


	
	//get and set metodos /////////////////////////////////////////////////////////////////////////////////////////////////	

	

	
	

	public List<Corcamento> getOrcamento() {
		return orcamento;
	}
	
	
	public int getQuantidade() {
		return quantidade;
	}







	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}







	public Corcamento getTransOrcamento() {
		return transOrcamento;
	}







	public void setTransOrcamento(Corcamento transOrcamento) {
		this.transOrcamento = transOrcamento;
	}







	public int getNumeroPedido() {
		return numeroPedido;
	}







	public void setNumeroPedido(int numeroPedido) {
		this.numeroPedido = numeroPedido;
	}














	public List<Cfracassados> getFracassados() {
		return fracassados;
	}







	public void setFracassados(List<Cfracassados> fracassados) {
		this.fracassados = fracassados;
	}



   public void addItemFracasso(Cfracassados obj ){
	   fracassados.add(obj);   
   }
   
   public void removeFracasso(Cfracassados obj ){
	   fracassados.remove(obj);   
   }



	public void addOrcamento(Corcamento orca){
		orcamento.add(orca);
	}
	
	public void removeOrcamento(Corcamento orca){
		orcamento.remove(orca);
	}


	public List<CitemProcesso> getItemProcesso() {
		return itemProcesso;
	}
	
	

	public void setItemProcesso(List<CitemProcesso> itemProcesso) {
		this.itemProcesso = itemProcesso;
	}



	public void setOrcamento(List<Corcamento> orcamento) {
		this.orcamento = orcamento;
	}

	



	public List<CstatusItem> getStatusItem() {
		return statusItem;
	}

	public void setStatusItem(List<CstatusItem> statusItem) {
		this.statusItem = statusItem;
	}

	

	public void setNumeroCatmat(String numeroCatmat) {
		this.numeroCatmat = numeroCatmat;
	}

	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	

	public Ccatmat getCatmat() {
		return catmat;
	}

	public void setCatmat(Ccatmat catmat) {
		this.catmat = catmat;
	}

	public Ctipo getTipo() {
		return tipo;
	}

	public void setTipo(Ctipo tipo) {
		this.tipo = tipo;
	}

	public Ccategoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Ccategoria categoria) {
		this.categoria = categoria;
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
		Citem other = (Citem) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
	
	

}
