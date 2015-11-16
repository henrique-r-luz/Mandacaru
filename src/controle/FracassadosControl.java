package controle;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import dao.DAO;
import dao.DAOqueryFracassados;

import model.Cfracassados;


@ManagedBean
@ViewScoped
public class FracassadosControl implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private Cfracassados fracassados = new Cfracassados();


	public Cfracassados getFracassados() {
		return fracassados;
	}


	public void setFracassados(Cfracassados fracassados) {
		this.fracassados = fracassados;
	}
	
public void salva(){
	if(new DAOqueryFracassados().pesquisaTitulo(fracassados.getTitulo()).isEmpty()){
		if(new DAOqueryFracassados().pesquisaJustificativa(fracassados.getJustificativa()).isEmpty()){
			new DAO<Cfracassados>(Cfracassados.class).adiciona(fracassados);
			clear();
		}
		else{
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_WARN,
							"Justificativa já exite",
							"Watch out for PrimeFaces!"));
		}
	}
	else{
		FacesContext.getCurrentInstance().addMessage(
				null,
				new FacesMessage(FacesMessage.SEVERITY_WARN,
						"Titulo já exite",
						"Watch out for PrimeFaces!"));
	}
	
	
	
	
}


public List<Cfracassados> getListatodos(){
	return new DAO<Cfracassados>(Cfracassados.class).listaTodos();
}


public void clear(){
	fracassados = new Cfracassados();
}


}
