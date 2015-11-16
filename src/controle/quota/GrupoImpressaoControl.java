package controle.quota;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.model.DualListModel;

import com.sun.faces.context.flash.ELFlash;

import dao.DAO;
import dao.quota.DAOqueryGrupoImpressao;

import model.impressao.Iservidor;
import model.quota.QgrupoImpressao;


@ManagedBean
@ViewScoped
public class GrupoImpressaoControl implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	
	
	
	
	

	private QgrupoImpressao grupo = new QgrupoImpressao();


	public QgrupoImpressao getGrupo() {
		return grupo;
	}


	public void setGrupo(QgrupoImpressao grupo) {
		this.grupo = grupo;
	}
	
	
	
	
	public void salvar(){
		
		new DAO<QgrupoImpressao>(QgrupoImpressao.class).adiciona(grupo);
		clear();
		
	}
	
	
	public void atualiza(){
		new DAO<QgrupoImpressao>(QgrupoImpressao.class).atualiza(grupo);
		clear();
	}
	
	
	
	public void clear(){
		grupo = new QgrupoImpressao();
	}
	
	
	
	public List<QgrupoImpressao> getListaTodos(){
	   return 	new DAOqueryGrupoImpressao().ListaTodos();
	//	return new DAO<QgrupoImpressao>(QgrupoImpressao.class).listaTodos();
	}
	
	
	public String addServidorGrupo(QgrupoImpressao  grupo){
	
		ELFlash.getFlash().put("grupo", grupo);
		return "/quota/add-servidor-grupo.xhtml?faces-redirect=true";
	}
	
	
	public String addImpressoraGrupo(QgrupoImpressao  grupo){
	
		ELFlash.getFlash().put("grupo", grupo);
		return "/quota/add-impressora-grupo.xhtml?faces-redirect=true";
	}
	
	

}
