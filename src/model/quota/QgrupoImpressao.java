package model.quota;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import model.impressao.Iservidor;


@Entity
public class QgrupoImpressao implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	@Id
	@GeneratedValue
	private int id;
	private int quantidade;
	private int validadeEmDias;
	@OneToMany(mappedBy="grupoImpressao")
	private  List<Iservidor> servidor = new ArrayList<Iservidor>();
	private String nome;
	private String descricao;
	
	//dado utilizado para verificar a a validade , em tempo , da quota
	@Temporal(TemporalType.DATE)
	private Date dataAtualizacao;
	@ManyToMany
	private List<Qimpressora> impressora = new ArrayList<Qimpressora>();
	
	
	
	
	public void addImpressora(Qimpressora impri){
		impressora.add(impri);
		
	}
	
	public void removeImpressora(Qimpressora impri){
		impressora.remove(impri);
	}
	
	
	public void addServidor(Iservidor serv){
		servidor.add(serv);
	}
	
	
	public void removeServidor(Iservidor serv){
		servidor.remove(serv);
	}
	
	public List<Qimpressora> getImpressora() {
		return impressora;
	}
	public void setImpressora(List<Qimpressora> impressora) {
		this.impressora = impressora;
	}
	public Date getDataAtualizacao() {
		return dataAtualizacao;
	}
	public void setDataAtualizacao(Date dataAtualizacao) {
		this.dataAtualizacao = dataAtualizacao;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}
	public int getValidadeEmDias() {
		return validadeEmDias;
	}
	public void setValidadeEmDias(int validadeEmDias) {
		this.validadeEmDias = validadeEmDias;
	}
	
	
	
	
	public List<Iservidor> getServidor() {
		return servidor;
	}
	public void setServidor(List<Iservidor> servidor) {
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
		QgrupoImpressao other = (QgrupoImpressao) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
	
	

	
	

}
