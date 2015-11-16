package controle;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import dao.DAO;
import dao.DAOqueryCategoria;
import model.*;

@ManagedBean
@ViewScoped
public class CategoriaControl implements Serializable  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Ccategoria categoria= new Ccategoria();

	public Ccategoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Ccategoria categoria) {
		this.categoria = categoria;
	}

	public List<Ccategoria> getListaTodos() {

		return new DAO<Ccategoria>(Ccategoria.class).listaTodos();
	}

	public void salva() {
		//System.out.println(new DAOqueryTipo().pesquisaNome(tipo.getNome()));
		if (new DAOqueryCategoria().pesquisaNome(categoria.getNome()).isEmpty()) {
			new DAO<Ccategoria>(Ccategoria.class).adiciona(categoria);
			clear();
		} else {
			 FacesContext.getCurrentInstance().addMessage("salvar", new FacesMessage(FacesMessage.SEVERITY_WARN,"Nome da categoria já existe", "Watch out for PrimeFaces!"));  
		}
	}

	public void clear() {
		categoria = new Ccategoria();

	}
	
	
	public String logout() throws ServletException, IOException {                       
        
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();  
        RequestDispatcher dispatcher = ((ServletRequest) ec.getRequest()).getRequestDispatcher("/j_spring_security_logout");  
        dispatcher.forward((ServletRequest) ec.getRequest(), (ServletResponse) ec.getResponse());  
        FacesContext.getCurrentInstance().responseComplete();  
  
        return "/init/index.jsf";  
    }   
	// private String inicio(){

	// }
}
