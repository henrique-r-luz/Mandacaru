package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.authentication.encoding.PasswordEncoder;

import model.impressao.Iservidor;

import seguranca.Md5;


@Entity
public class Cusuario implements Serializable  {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue
	private int id;

	@Column(unique=true)
	private String login;
	private String senha;
	private boolean ldap;
	@ManyToOne
	private Cgrupo grupo;
	@OneToMany(mappedBy="usuario")
	private List<Cpedidos> pedidos = new ArrayList<Cpedidos>();
	@OneToMany(mappedBy="usuario")
	private List<Cpregao> pregao = new ArrayList<Cpregao>();
	@OneToMany(mappedBy="usuario")
	private List<Cprocesso> processo = new ArrayList<Cprocesso>();
	
	@OneToOne
	@JoinColumn(name = "servidor_id", unique=true) 
	private Iservidor servidor;
    @ManyToOne
    private CusuarioStatus status;

  
    
    
	public boolean isLdap() {
		return ldap;
	}
	public void setLdap(boolean ldap) {
		this.ldap = ldap;
	}
	public Iservidor getServidor() {
		return servidor;
	}
	public void setServidor(Iservidor servidor) {
		this.servidor = servidor;
	}
	public CusuarioStatus getStatus() {
		return status;
	}
	public void setStatus(CusuarioStatus status) {
		this.status = status;
	}
	public List<Cpedidos> getPedidos() {
		return pedidos;
	}
	public void setPedidos(List<Cpedidos> pedidos) {
		this.pedidos = pedidos;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		PasswordEncoder encoder = new Md5PasswordEncoder();  
		this.senha = encoder.encodePassword(senha, null);
		//this.senha = Md5.transMd5(senha);
		//System.out.println("senha>>>>>>"+this.senha);
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	
	public Cgrupo getGrupo() {
		return grupo;
	}
	public void setGrupo(Cgrupo grupo) {
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
		Cusuario other = (Cusuario) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
	
	

}
