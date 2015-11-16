package controle;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import controle.quota.Label;
import dao.DAO;
import dao.DAOinsereLogin;
import dao.DAOqueryGrupo;


import model.Cgrupo;

@ManagedBean
@ViewScoped
public class GrupoControl implements Serializable  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Cgrupo grupo = new Cgrupo();
	
	
	
	
	
	
	
	public String getVerificaGrupo(){
		if(new DAO<Cgrupo>(Cgrupo.class).listaTodos().isEmpty()){
			new DAOinsereLogin().Insere();
			return "true";
		}
		else{
			return "true";
		}
		 
	}
	
	
	
	public Cgrupo getGrupo() {
		return grupo;
	}
	public void setGrupo(Cgrupo grupo) {
		this.grupo = grupo;
	}
	
	
	public void salva(){
		if(new DAOqueryGrupo().pesquisaNome(grupo.getNome()).isEmpty()){
			grupo.setNome("ROLE_"+grupo.getNome().toUpperCase());
			new DAO<Cgrupo>(Cgrupo.class)
			.adiciona(grupo);
			clear();
		}
		else{
			FacesContext.getCurrentInstance().addMessage(
					"salvar",
					new FacesMessage(FacesMessage.SEVERITY_WARN,
							Label.getGrupoExit(),
							"Watch out for PrimeFaces!"));
		}
		
	}
	
	public void altera(){
		if(new DAOqueryGrupo().pesquisaNome(grupo.getNome()).isEmpty()){
			grupo.setNome(grupo.getNome().toUpperCase());
			new DAO<Cgrupo>(Cgrupo.class).atualiza(grupo);
			clear();
		}
		else{
			FacesContext.getCurrentInstance().addMessage(
					"salvar",
					new FacesMessage(FacesMessage.SEVERITY_WARN,
							Label.getGrupoExit(),
							"Watch out for PrimeFaces!"));
		}
	}
	
	public void clear(){
		grupo = new Cgrupo();	
	}
	public List<Cgrupo> getListaTodos(){
		return new DAO<Cgrupo>(Cgrupo.class).listaTodos();
	}
}
