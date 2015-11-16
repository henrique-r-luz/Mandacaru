package controle;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.context.RequestContext;
import org.springframework.security.core.context.SecurityContextHolder;

import com.sun.faces.context.flash.ELFlash;

import model.*;
import dao.*;

@ManagedBean
@ViewScoped
public class PedidosControl implements Serializable  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Cpedidos pedidos = new Cpedidos();
	// private ItemControl itemControl = new ItemControl();
	private ListaPedidos listaPedidos;

	public ListaPedidos getListaPedidos() {
		return listaPedidos;
	}

	public void setListaPedidos(ListaPedidos listaPedidos) {
		this.listaPedidos = listaPedidos;
	}

	/*
	 * public ItemControl getItemControl() { return itemControl; }
	 * 
	 * public void setItemControl(ItemControl itemControl) { this.itemControl =
	 * itemControl; }
	 */

	public PedidosControl() {
		iniciar();

	}

	public String page() {

		return "/pedidos/criar-pedido?faces-redirect=true";
	}

	public void iniciar() {
		pedidos = new Cpedidos();
		//messagemPedidosEnviado();
	}

	public PedidosControl(Cpedidos pedidos) {
		this.pedidos = pedidos;

	}

	public Cpedidos getPedidos() {
		return pedidos;
	}

	public void setPedidos(Cpedidos pedidos) {
		this.pedidos = pedidos;
	}

	public void salva() {
		if (new DAOqueryPedido().pesquisaNome(pedidos.getNome()).isEmpty()) {
			pedidos.setUsuario((Cusuario) new DAOqueryUsuario().pesquisaNome(
					SecurityContextHolder.getContext().getAuthentication()
							.getName()).get(0));
			pedidos.setStatusPedidos(new DAO<CstatusPedidos>(
					CstatusPedidos.class).buscaPorId(0));
			new DAO<Cpedidos>(Cpedidos.class).adiciona(pedidos);
			clear();
		} else {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_WARN,
							"Nome do pedidos já existe",
							"Watch out for PrimeFaces!"));
		}
	}

	public String verificaSecao() {
	//	SessionId sessionId = new SessionId();
		return "/pedidos/cria-lista-pedidos?faces-redirect=true";
	/*	if (new DAOqueryPedidosStatus().pesquisPedidosStatusAberto(ped.getId()) != 0) {
		//	sessionId.setId(ped.getId());
			
			
		} else {
			if (new DAOqueryPedidosStatus().pesquisPedidosStatusFechado(ped
					.getId()) != 0) {
			//	return sessionId.setId(
			//			"/pedidos/pedidos-fechado?faces-redirect=true",
				//		ped.getId());
			}else{
				System.out.println("ola>>>>>>>>>>");
				return "";
			}
		}*/

	}

	public String pageLista(Cpedidos pedidos) {
		// listaPedidos = new ListaPedidos(pedidos);
		return "/pedidos/cria-lista-pedidos?faces-redirect=true";
	}

	public void clear() {

		pedidos = new Cpedidos();

	}

	public List<Cpedidos> getListaTodos() {
		// return new DAO<Cpedidos>(Cpedidos.class).listaTodos();

		return new DAOqueryPedido().listaTodosUsuarios(SecurityContextHolder
				.getContext().getAuthentication().getName());
	}
	
	
	public List<Cpedidos> getListaPedidosfechados(){
		return new DAOqueryPedidosStatus().pesquisPedidosFechado();
	}
	
	
	public String criaLista(int id){
		boolean bool =  new DAOqueryPedido().VerificaPedido(id) ; 
		ELFlash.getFlash().put("id", id);
		
		if( bool){
		
		return "/pedidos/cria-lista-pedidos?faces-redirect=true";
		}else{
			return "/pedidos/pedidos-fechados?faces-redirect=true";
		}
	}
	
	
	public String orcamento(int id){
		boolean bool =  new DAOqueryPedido().VerificaPedido(id) ; 
		
		ELFlash.getFlash().put("id", id);
		if( bool){
		return "/pedidos/cria-orcamento?faces-redirect=true";
		}else{
			return "/pedidos/orcamento-fechado?faces-redirect=true";
		}
	}
	
	
	public void messagemPedidosEnviado(){
		FacesContext.getCurrentInstance().addMessage(
				null,
				new FacesMessage(FacesMessage.SEVERITY_WARN,
						"O pedido foi enviado",
						"Watch out for PrimeFaces!"));
		System.out.println("messag>>>"+ELFlash.getFlash().get("msg"));
		if (ELFlash.getFlash().get("msg") == null) {
		
		}else{
			
			if( ELFlash.getFlash().get("msg").equals("1")){
				System.out.println("entro na ,messagen");
				FacesContext.getCurrentInstance().addMessage(
						null,
						new FacesMessage(FacesMessage.SEVERITY_WARN,
								"O pedido foi enviado",
								"Watch out for PrimeFaces!"));
			}else{
				System.out.println("else");
			}
		}
		
		//RequestContext.getCurrentInstance().update("growl");
	}
	
	

}
