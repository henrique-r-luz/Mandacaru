package controle.quota;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import model.impressao.Iservidor;
import model.quota.QgrupoImpressao;

import org.primefaces.context.RequestContext;
import org.primefaces.model.DualListModel;

import com.sun.faces.context.flash.ELFlash;

import dao.quota.DAOinsereGrupoImpressao;
import dao.quota.DAOqueryGrupoImpressao;

@ManagedBean
@ViewScoped
public class AdicionaServidorGrupo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private QgrupoImpressao grupo;

	private List<Iservidor> servidorTarget;
	private List<Iservidor> servidorSouce;
	private DualListModel<Iservidor> servidor;

	public QgrupoImpressao getGrupo() {

		return grupo;
		
	}

	public void setGrupo(QgrupoImpressao grupo) {
		this.grupo = grupo;
	}

	public DualListModel<Iservidor> getServidor() {
		return servidor;
	}

	public void setServidor(DualListModel<Iservidor> servidor) {
		this.servidor = servidor;
	}

	@PostConstruct
	void initialiseSession() {
		FacesContext.getCurrentInstance().getExternalContext().getSession(true);
	}

	public AdicionaServidorGrupo() {

		if (ELFlash.getFlash().get("grupo") != null) {

			this.grupo = (QgrupoImpressao) ELFlash.getFlash().get("grupo");
			// System.out.println("grupo>>>>>>>>>>>>>>>>>>>>"+grupo);
			servidorTarget = new DAOqueryGrupoImpressao()
					.retornaServidoresGrupo(grupo.getId());
			servidorSouce = new DAOqueryGrupoImpressao()
					.retornaServidoresSemGrupo();
			servidor = new DualListModel<Iservidor>(servidorSouce,
					servidorTarget);
		} else {
			
			servidorTarget = new ArrayList<Iservidor>();
			servidorSouce = new ArrayList<Iservidor>();
			servidor = new DualListModel<Iservidor>(servidorSouce,
					servidorTarget);

		
		}
	}

	

	public void salvaGrupo() {
		
		new DAOinsereGrupoImpressao().atualiza(servidor.getTarget(), grupo, servidor.getSource());
	}
	
	//caso o objeto ELFlash.getFlash().get("grupo") retorn null, a fun��o redireciona a pagina para o grupo de impress�o
	public String getResp(){
	   
		if(ELFlash.getFlash().get("grupo") == null){
			return "1";
		}else{
			return "";
		}
	}

}
