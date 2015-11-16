package controle;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.springframework.security.core.context.SecurityContextHolder;

import com.sun.faces.context.flash.ELFlash;

import dao.DAO;
import dao.DAOqueryPedido;
import dao.DAOqueryProcesso;
import dao.DAOqueryUsuario;
import model.Ccotacao;
import model.Citem;
import model.Corcamento;
import model.Cprocesso;
import model.CstatusPedidos;
import model.Cusuario;


@ManagedBean
@ViewScoped
public class ProcessoControl implements Serializable  {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Cprocesso processo = new Cprocesso();
	private StatusControl statusControl = new StatusControl();
	
	
	
	public Cprocesso getProcesso() {
		return processo;
	}

	public void setProcesso(Cprocesso processo) {
		this.processo = processo;
	}

	public void salva(){
		
		if(new DAOqueryProcesso().pesquisaNome(processo.getNumero()).size()==0){
		processo.setUsuario((Cusuario) new DAOqueryUsuario().pesquisaNome(
				SecurityContextHolder.getContext().getAuthentication()
						.getName()).get(0));
		processo.setStatusPedidos(new DAO<CstatusPedidos>(CstatusPedidos.class).buscaPorId(0));
		 new DAO<Cprocesso>(Cprocesso.class).adiciona(processo);
		 
		 clear();
		}else{
		
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_WARN,
							"Numero de processo já exite",
							null));
		}
	}
	
	public void clear(){
		processo  = new Cprocesso();
		
	}
	
	public List<Cprocesso> getListaTodos(){
		
		
		
		return new DAO<Cprocesso>(Cprocesso.class).listaTodos();
	}
	
	public String criaListaProcesso(int id){
		boolean bool =  new DAOqueryProcesso().verificaProcesso(id); //criar verificador para processo
		ELFlash.getFlash().put("id", id);
		if( bool){
	     	return "/processo/cria-lista-processo?faces-redirect=true";
		}else{
			return "/processo/processo-fechado?faces-redirect=true";
	    }
	}
	
	
	public String enviaProcesso() {

		List<Citem> auxItem = new ArrayList<Citem>();
		

		statusControl.addStatusPregao(auxItem, processo);

		// new DAOatualizaPedidoStatus().atualizaEstatusDiretoria(pedidos);
		ELFlash.getFlash().put("msg", "1");
		

		return "/pedidos/criar-pedido?faces-redirect=true";
	}
	
	
	public List<Cprocesso> getListaProcessoFechados(){
		return new DAOqueryProcesso().listaProcessofechado();
	}

	
	
	
	

}
