package controle;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import model.*;
import dao.*;

@ManagedBean
@ViewScoped
public class CatmatControl implements Serializable  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Ccatmat catmat = new Ccatmat();
	private CinfoMaterial material = new CinfoMaterial();
	private CtipoCatMat tipocatmat = new CtipoCatMat(); 


	
	
	
	
	public CtipoCatMat getTipocatmat() {
		return tipocatmat;
	}

	public void setTipocatmat(CtipoCatMat tipocatmat) {
		this.tipocatmat = tipocatmat;
	}

	public CinfoMaterial getMaterial() {
		return material;
	}

	public void setMaterial(CinfoMaterial material) {
		this.material = material;
	}

	public Ccatmat getCatmat() {
		return catmat;
	}

	public void setCatmat(Ccatmat catmat) {
		this.catmat = catmat;
	}

	public void salva() {
		


		if (new DAOqueryCatmat().pesquisaNumero(catmat.getNumero()).isEmpty()) {
			new DAOinsereCatmat().adiciona(catmat,material);
			clear();
		} else {
			// FacesContext.getCurrentInstance().addMessage("salvar",  new FacesMessage("Numero de catmat já existe"));
			  FacesContext.getCurrentInstance().addMessage("salvar", new FacesMessage(FacesMessage.SEVERITY_WARN,"Número de catmat já existe", "Watch out for PrimeFaces!"));  
//	    }  
		}
	}

	public List<Ccatmat> getListaTodos() {
          // essa pesquisa não pode fechar session
		return new DAO<Ccatmat>(Ccatmat.class).listaTodos();

	}
	

	
	
	public void clear() {
		this.catmat = new Ccatmat();
		material = new CinfoMaterial();
	}

}
