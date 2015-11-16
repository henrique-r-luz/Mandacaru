package controle.quota;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import controle.ldap.ConectLdap;
import dao.DAO;
import model.Cusuario;
import model.impressao.*;
import model.quota.Ldap;

@ManagedBean
@ViewScoped
public class LdapControl implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Ldap ldap = new Ldap();
	
	
	
	public LdapControl(){
		//ldap = new Ldap();
	}

	public Ldap getLdap() {
		return ldap;
	}

	public void setLdap(Ldap ldap) {
		
		this.ldap = ldap;
	}

	public void salvar() {
		if(new DAO<Ldap>(Ldap.class).listaTodos().isEmpty())
		{
		       new DAO<Ldap>(Ldap.class).adiciona(ldap);
		       clear();
		       
		}else{
			FacesContext.getCurrentInstance().addMessage(
					"salvar",
					new FacesMessage(FacesMessage.SEVERITY_WARN,
							Label.getConfigLadap(), null));
		}
	}

	public void alterar() {
		new DAO<Ldap>(Ldap.class).atualiza(ldap);
	}

	public void excluir() {
		new DAO<Ldap>(Ldap.class).remove(ldap);
	}

	public List<Ldap> getListaTodos() {
	  return	new DAO<Ldap>(Ldap.class).listaTodos();
	}

	public void clear() {
		ldap = new Ldap();
	}
	
	public void testaLdap(){
		ConectLdap conectLdap  = new  ConectLdap ();
		if(conectLdap.testaConexao()){
			FacesContext.getCurrentInstance().addMessage(
					"salvar",
					new FacesMessage(FacesMessage.SEVERITY_INFO,
							Label.getTestLdap(), null));
		}else{
			FacesContext.getCurrentInstance().addMessage(
					"salvar",
					new FacesMessage(FacesMessage.SEVERITY_FATAL,
							Label.getErroTesteldap(), null));
		}
	}
	
	
	public void dados(){
	 List<Ldap>	lista = new DAO<Ldap>(Ldap.class).listaTodos();
		if(!lista.isEmpty()){
			ldap = lista.get(0);
		}else{
		   ldap = new Ldap();
		}
	}
}
