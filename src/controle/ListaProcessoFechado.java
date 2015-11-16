package controle;

import java.io.IOException;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.MathContext;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.servlet.http.HttpServletResponse;

import com.sun.faces.context.flash.ELFlash;

import model.Ccotacao;
import model.Citem;
import model.Corcamento;
import model.Cpedidos;
import model.Cprocesso;
import model.Cstatus;
import dao.DAO;
import dao.DAOinsereItemPedido;
import dao.DAOinsereItemProcesso;
import dao.DAOqueryItem;
import dao.DAOqueryOrcamento;
import dao.DAOqueryPedidosItens;
import dao.DAOqueryProcesso;

@ManagedBean
@ViewScoped
public class ListaProcessoFechado implements Serializable   {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private DataModelOrcamento modelItens;
	private Corcamento[] selectedItens;
	private Corcamento[] selectedItensList;
	private Cprocesso processo;

	// private PedidosControl PedidosControl;
	private List<Corcamento> listAux;
	private List<Corcamento> listAuxItem;
	private List<Corcamento> listaTodosItens ;
	private int id;
	private StatusControl  statusControl ;

	public List<Corcamento> getListAux() {
		return listAux;
	}

	public void setListAux(List<Corcamento> listAux) {
		this.listAux = listAux;
	}

	public Cprocesso getProcesso() {
		return processo;
	}

	public void setProcesso(Cprocesso processo) {
		this.processo = processo;
	}

	public ListaProcessoFechado() {

		inicia();

	}

	public String inicia() {
		if (ELFlash.getFlash().get("id") == null) {

			id = -1;
		} else {

			id = (Integer) ELFlash.getFlash().get("id");
		}
		if (id == -1) {
			HttpServletResponse response = (HttpServletResponse) FacesContext
					.getCurrentInstance().getExternalContext().getResponse();
			try {
				response.sendRedirect("/index.jsf");
				FacesContext.getCurrentInstance().responseComplete();
			} catch (Exception e) {
				e.printStackTrace();
			}
			/*
			 * try { FacesContext .getCurrentInstance() .getExternalContext()
			 * .dispatch(FacesContext.getCurrentInstance() .getExternalContext()
			 * .getRequestContextPath() + "/index.jsf"); } catch (IOException e)
			 * { // TODO Auto-generated catch block e.printStackTrace(); }
			 */
		} else {

			// realizar queri para buscar orçamento
			adicionaModeloTable();
		}
		return "selecao";
	}

	public Corcamento[] getSelectedItensList() {
		return selectedItensList;
	}

	public void setSelectedItensList(Corcamento[] selectedItensList) {
		this.selectedItensList = selectedItensList;
	}

	public Corcamento[] getSelectedItens() {
		return selectedItens;
	}

	public void setSelectedItens(Corcamento[] selectedItens) {
		this.selectedItens = selectedItens;
	}

	
	
	public DataModelOrcamento getModelItens() {
		return modelItens;
	}

	public void setModelItens(DataModelOrcamento modelItens) {
		this.modelItens = modelItens;
	}

	private void adicionaModeloTable() {
	
		processo = new DAOqueryProcesso().processos(id);
		List<Corcamento> listAux2 = new ArrayList<Corcamento>();
		// processo.setItem(new DAOqueryItem().pesquisItemAddProcesso(id));
		listAux2 = new  DAOqueryOrcamento().pesquisOrcamentoProcessoSelectFechadoTest(id);
	//	List<Citem> listAux3 = new  DAOqueryOrcamento().pesquisOrcamentoProcessoSelectFechadoTest2(id);
		
		
		
		//listAux = new DAOqueryOrcamento().pesquisOrcamentoProcessoSelect(id);// realizar uma
																// pesquisa para
																// retornar os
																// itens do
																// processo
		//listaTodosItens = new DAOqueryItem().pesquisItemProcesso();// gerar a
																	// lista de
		// pedidos
																	// enviados
		
		modelItens = new DataModelOrcamento(new ValorMedio().valoMedioTotal(listAux2)) ;
	}



	
	

	
}
