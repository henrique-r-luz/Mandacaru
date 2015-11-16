package controle;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import dao.DAO;
import dao.DAOqueryTipo;
import model.*;

@ManagedBean
@ViewScoped
public class TipoControl implements Serializable  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Ctipo tipo = new Ctipo();

	public Ctipo getTipo() {
		return tipo;
	}

	public void setTipo(Ctipo tipo) {
		this.tipo = tipo;
	}

	public List<Ctipo> getListaTodos() {

		return new DAO<Ctipo>(Ctipo.class).listaTodos();
	}

	public void salva() {
		//System.out.println(new DAOqueryTipo().pesquisaNome(tipo.getNome()));
		if (new DAOqueryTipo().pesquisaNome(tipo.getNome()).isEmpty()) {
			new DAO<Ctipo>(Ctipo.class).adiciona(tipo);
			clear();
		} else {
			 FacesContext.getCurrentInstance().addMessage("salvar", new FacesMessage(FacesMessage.SEVERITY_WARN,"Nome do tipo já existe", "Watch out for PrimeFaces!"));  
		}
	}

	public void clear() {
		tipo = new Ctipo();

	}
	// private String inicio(){

	// }
}
