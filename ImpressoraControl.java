package controle.quota;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import controle.UsuarioControl;

import dao.DAO;
import dao.quota.DAOqueryGrupoImpressao;

import model.quota.Qimpressora;


@ManagedBean
@ViewScoped
public class ImpressoraControl implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	
	private Qimpressora impressora = new  Qimpressora() ;

	
		
	public Qimpressora getImpressora() {
		return impressora;
	}


	public void setImpressora(Qimpressora impressora) {
		this.impressora = impressora;
	}


	public void salvar(){
		//criando testo para ser inserido no banco de dados
		// strOrigem = strOrigem.replaceAll("\\s+"," ");
		impressora.setNome(impressora.getNome().replaceAll(" ",""));
		System.out.println("impressora>>>"+impressora.getNome());
		new DAO<Qimpressora>(Qimpressora.class).adiciona(impressora);
		clear();
		
	}
	
	
	public void altera(){
		//criando testo para ser inserido no banco de dados
		// strOrigem = strOrigem.replaceAll("\\s+"," ");
		impressora.setNome(impressora.getNome().replaceAll(" ",""));
		new DAO<Qimpressora>(Qimpressora.class).atualiza(impressora);
		clear();
		
	}
	
	public void clear(){
		 impressora = new  Qimpressora() ;
	}
	
	
	
	public List<Qimpressora> getListaTodos(){
		return new DAO<Qimpressora>(Qimpressora.class).listaTodos();
	}
	
	
	
	/*public List<Qimpressora> getListaImpressoraGrupoUsuario(){
	//	return new DAOqueryGrupoImpressao().ListaImpressoraPorusuario(new UsuarioControl().getSecao());
	}*/
	
	
	

}
