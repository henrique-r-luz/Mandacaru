package controle;

import java.io.IOException;
import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;


@ManagedBean
@ViewScoped
public class LoginBean implements Serializable {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String nome;
	private String senha;
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	
	public LoginBean(){
		
	}
	
	public String login() throws ServletException, IOException {
	    //do any job with the associated values that you've got from the user, like persisting attempted login, etc.
	    FacesContext facesContext = FacesContext.getCurrentInstance();
	    ExternalContext extenalContext = facesContext.getExternalContext();
	    RequestDispatcher dispatcher = ((ServletRequest)extenalContext.getRequest()).getRequestDispatcher("/j_spring_security_check");
	    dispatcher.forward((ServletRequest)extenalContext.getRequest(), (ServletResponse)extenalContext.getResponse());
	    facesContext.responseComplete();
	    return null;
	}
	
	

}
