package controle.quota;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import model.impressao.Iservidor;
import model.quota.QgrupoImpressao;
import model.quota.Qimpressora;

import org.primefaces.model.DualListModel;

import com.sun.faces.context.flash.ELFlash;

import dao.DAO;
import dao.quota.DAOinsereImpressoraNoGrupo;
import dao.quota.DAOqueryGrupoImpressao;


@ManagedBean
@ViewScoped
public class AdicionarImpressoraGrupo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private QgrupoImpressao grupo;

	private List<Qimpressora> impressoraTarget;
	private List<Qimpressora> impressoraSouce;
	private DualListModel<Qimpressora> impressora;
	
	public QgrupoImpressao getGrupo() {
		return grupo;
		
	}
	public void setGrupo(QgrupoImpressao grupo) {
		this.grupo = grupo;
	}
	
	

	
	public List<Qimpressora> getImpressoraTarget() {
		return impressoraTarget;
	}
	public void setImpressoraTarget(List<Qimpressora> impressoraTarget) {
		this.impressoraTarget = impressoraTarget;
	}
	public List<Qimpressora> getImpressoraSouce() {
		return impressoraSouce;
	}
	public void setImpressoraSouce(List<Qimpressora> impressoraSouce) {
		this.impressoraSouce = impressoraSouce;
	}
	public DualListModel<Qimpressora> getImpressora() {
		return impressora;
	}
	public void setImpressora(DualListModel<Qimpressora> impressora) {
		this.impressora = impressora;
	}
	public AdicionarImpressoraGrupo(){
       
		if (ELFlash.getFlash().get("grupo") != null) {

			this.grupo = (QgrupoImpressao) ELFlash.getFlash().get("grupo");
			
		 impressoraTarget = new DAOqueryGrupoImpressao().retornaImpressoraGrupo(grupo.getId());
		 impressoraSouce = new DAOqueryGrupoImpressao().retornaImpressoraSemGrupo();
		 impressoraSouce.removeAll(impressoraTarget);
		 impressora =  new DualListModel<Qimpressora>(impressoraSouce,impressoraTarget);
		 
			
		}else{
			
			 impressoraTarget = new ArrayList<Qimpressora>();
			 impressoraSouce = new ArrayList<Qimpressora>();
		      impressora = new DualListModel<Qimpressora>(impressoraSouce,impressoraTarget);
			
		}
	}
	
	
	public void salvaImpressora(){
		
		new DAOinsereImpressoraNoGrupo().atualiza(impressora.getTarget(), grupo, impressora.getSource());
		
	}
	
	
	public String getResp(){
		   
		if(ELFlash.getFlash().get("grupo") == null){
			return "1";
		}else{
			return "";
		}
	}
	
	
	

}
