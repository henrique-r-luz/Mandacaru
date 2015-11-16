package model.impressao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import model.Cusuario;
import model.quota.QcopiasRealizadas;
import model.quota.QgrupoImpressao;


@Entity
public class Iservidor implements Serializable  {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue
	private int id;
	private String nome;
	private String matricula;
	@Column(unique=true)
	private String cpf;
	@OneToMany(mappedBy="servidor")
	private List<IdocCopias> copias = new ArrayList<IdocCopias>();
	
	@ManyToOne()
	private QgrupoImpressao grupoImpressao;
	@OneToMany(mappedBy="servidor")
    private List<QcopiasRealizadas> copiasQuotas = new ArrayList<QcopiasRealizadas>();
	
	@OneToOne(mappedBy="servidor")

    private Cusuario usuario;
	
	



	public List<QcopiasRealizadas> getCopiasQuotas() {
		return copiasQuotas;
	}


	public void setCopiasQuotas(List<QcopiasRealizadas> copiasQuotas) {
		this.copiasQuotas = copiasQuotas;
	}




	public QgrupoImpressao getGrupoImpressao() {
		return grupoImpressao;
	}


	public void setGrupoImpressao(QgrupoImpressao grupoImpressao) {
		this.grupoImpressao = grupoImpressao;
	}


	public void addCopias(IdocCopias copia){
		copias.add(copia);
	}
	
	
	public void removeCopias(IdocCopias copia){
		copias.remove(copia);
	}
	
	
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public List<IdocCopias> getCopias() {
		return copias;
	}
	public void setCopias(List<IdocCopias> copias) {
		this.copias = copias;
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
	public String getMatricula() {
		return matricula;
	}
	public void setMatricula(String matricula) {
		this.matricula = matricula;
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
		Iservidor other = (Iservidor) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
	
	
	
}
