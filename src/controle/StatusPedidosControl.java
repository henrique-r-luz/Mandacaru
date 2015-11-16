package controle;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import antlr.collections.List;

import dao.DAO;
import dao.DAOinsereStatusPedido;

import model.Cpedidos;
import model.CstatusPedidos;



@ManagedBean
@ViewScoped
public class StatusPedidosControl implements Serializable  {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private CstatusPedidos statusPedidos = new CstatusPedidos();
	

	public CstatusPedidos getStatusPedidos() {
		return statusPedidos;
	}

	public void setStatusPedidos(CstatusPedidos statusPedidos) {
		this.statusPedidos = statusPedidos;
	}
	
	public String insereStatus(){
		if(vericaStatusTabela()==0){
			new DAOinsereStatusPedido().insere();
		}
		return "/pedidos/criar-pedido?faces-redirect=true";
	}
	
	
	public int vericaStatusTabela(){
		java.util.List<CstatusPedidos> lista = new DAO<CstatusPedidos>(CstatusPedidos.class).listaTodos();
		return lista.size();
	}
	
	public void salvar(){
		
	}
	
	
	

}
