package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;




@Entity
public class CstatusPedidos implements Serializable {
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	private int id ;
	private String nome;
	@OneToMany(mappedBy="statusPedidos")
	private List<Cpedidos> pedidos = new ArrayList<Cpedidos>();
	@OneToMany(mappedBy="statusPedidos")
	private List<Cprocesso> processo = new ArrayList<Cprocesso>();
	@OneToMany(mappedBy="statusPedidos")
	private List<Cpregao> pregao = new ArrayList<Cpregao>();
	
	
	
	
	
	public List<Cpregao> getPregao() {
		return pregao;
	}
	public void setPregao(List<Cpregao> pregao) {
		this.pregao = pregao;
	}
	public List<Cprocesso> getProcesso() {
		return processo;
	}
	public void setProcesso(List<Cprocesso> processo) {
		this.processo = processo;
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
	
	
	
	public List<Cpedidos> getPedidos() {
		return pedidos;
	}
	public void setPedidos(List<Cpedidos> pedidos) {
		this.pedidos = pedidos;
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
		CstatusPedidos other = (CstatusPedidos) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
	
	
	
	

}
