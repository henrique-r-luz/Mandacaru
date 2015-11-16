package model.quota;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;


@Entity
public class Qimpressora implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	private int id;
	@Column(unique=true)
	private String nome;
	private String ip;
	private String descricao;
	@OneToMany(mappedBy="impressora")
	private List<QcopiasRealizadas> copias =  new ArrayList<QcopiasRealizadas>();
	@ManyToMany(mappedBy="impressora")
	private List<QgrupoImpressao> grupo = new ArrayList<QgrupoImpressao>();
	
	
	
	
	public void addGrupo(QgrupoImpressao gru){
		grupo.add(gru);
	}
	
	
	public void removeGrupo(QgrupoImpressao gru){
		grupo.remove(gru);
	}
	

	
	public String getIp() {
		return ip;
	}


	public void setIp(String ip) {
		this.ip = ip;
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
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public List<QcopiasRealizadas> getCopias() {
		return copias;
	}
	public void setCopias(List<QcopiasRealizadas> copias) {
		this.copias = copias;
	}
	public List<QgrupoImpressao> getGrupo() {
		return grupo;
	}
	public void setGrupo(List<QgrupoImpressao> grupo) {
		this.grupo = grupo;
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
		Qimpressora other = (Qimpressora) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
	
	
	




	
	
	
	
	
	

}
