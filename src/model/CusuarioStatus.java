package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import dao.DAOinsereUsuarioStatus;




@Entity
public class CusuarioStatus implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
    @Id
	
	private int id;
	private boolean status;
	@OneToMany(mappedBy="status")
	private List<Cusuario> usuario = new ArrayList<Cusuario>();
	
	
	public void removeUsuario(Cusuario user){
		usuario.remove(user);
	}
	
	public void addUsuario(Cusuario user){
		usuario.add(user);
	}
	
	public List<Cusuario> getUsuario() {
		return usuario;
	}
	public void setUsuario(List<Cusuario> usuario) {
		this.usuario = usuario;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public boolean isStatus() {
		
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
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
		CusuarioStatus other = (CusuarioStatus) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
	

}
