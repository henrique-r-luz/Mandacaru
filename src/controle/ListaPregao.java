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
import model.Cfracassados;
import model.Citem;
import model.Corcamento;
import model.Cpedidos;
import model.Cpregao;
import model.Cprocesso;
import model.Cstatus;
import dao.DAO;
import dao.DAOinsereItemPedido;
import dao.DAOinsereItemProcesso;
import dao.DAOisereFracassoPregao;
import dao.DAOqueryFracassados;
import dao.DAOqueryItem;
import dao.DAOqueryOrcamento;
import dao.DAOqueryPedidosItens;
import dao.DAOqueryProcesso;

@ManagedBean
@ViewScoped
public class ListaPregao implements Serializable   {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private DataModelOrcamento modelItens;
	private DataModelOrcamento modelItensSelect;
	private DataModelOrcamento modelItensSelectAux;
	private Corcamento[] selectedItens;
	private Corcamento[] selectedItensAux;
	private Corcamento[] selectedItensList;
	private Cpregao pregao;

	// private PedidosControl PedidosControl;
	private List<Corcamento> listAux;
	private List<Corcamento> listAuxItem;
	private List<Corcamento> listaTodosItens ;
	private String id;
	private String numProcesso; 
	private StatusControl  statusControl ;
	private Cfracassados fracasso;
	
	
	

	
	
	


	public Cpregao getPregao() {
		return pregao;
	}

	public void setPregao(Cpregao pregao) {
		this.pregao = pregao;
	}

	public Cfracassados getFracasso() {
		return fracasso;
	}

	public void setFracasso(Cfracassados fracasso) {
		this.fracasso = fracasso;
	}

	public DataModelOrcamento getModelItensSelectAux() {
		return modelItensSelectAux;
	}

	public void setModelItensSelectAux(DataModelOrcamento modelItensSelectAux) {
		this.modelItensSelectAux = modelItensSelectAux;
	}

	public List<Corcamento> getListAux() {
		return listAux;
	}

	public void setListAux(List<Corcamento> listAux) {
		this.listAux = listAux;
	}

	public ListaPregao() {
		adicionaModeloTable();
	//	inicia();

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

	public DataModelOrcamento getModelItensSelect() {
		return modelItensSelect;
	}

	public void setModelItensSelect(DataModelOrcamento modelItensSelect) {
		this.modelItensSelect = modelItensSelect;
	}

	public DataModelOrcamento getModelItens() {

		return modelItens;
	}

	public void setModelItens(DataModelOrcamento modelItens) {
		this.modelItens = modelItens;
	}
	
	

	private void adicionaModeloTable() {
		
	
		if (ELFlash.getFlash().get("pregao") == null) {
		
		}else{
			pregao =   (Cpregao) ELFlash.getFlash().get("pregao");
		}
		
		
		//processo = new DAOqueryProcesso().processos(id);
		// processo.setItem(new DAOqueryItem().pesquisItemAddProcesso(id));
	//	listAux = new DAOqueryOrcamento().pesquisOrcamentoProcessoSelect(id);// realizar uma
																// pesquisa para
																// retornar os
																// itens do
																// processo
		//listaTodosItens = new DAOqueryItem().pesquisItemProcesso();// gerar a
																	// lista de
		// pedidos
		Cprocesso processo = pregao.getProcessoList().get(0);
		numProcesso = processo.getNumero();
		List<Corcamento> itensProcesso = new DAOqueryOrcamento().pesquisOrcamentoProcessoFechados(numProcesso);
		listaTodosItens =  new ValorMedio().valoMedioTotal(itensProcesso);
		listAuxItem = new DAOqueryOrcamento().ListaOrcamentoFracassados();
		for (Corcamento item : listAuxItem) {
			listaTodosItens.remove(item);
		}
		modelItensSelect = new DataModelOrcamento(listaTodosItens);
        
		
		//lista todos os itens fracassados
		
		/*for (Corcamento item : listAux) {
			// orcamen.setQuantidade(orcamento.getQuantidade());
			listAuxItem.add(item);
		}*/
		modelItens = new DataModelOrcamento(new ValorMedio().valoMedioTotal(listAuxItem));
	}

	
	
    // cria lista de itens fracassados
	public void itenfracassados(){
		List<Corcamento> orca = new ArrayList<Corcamento>();
		selectedItensAux = selectedItens;
		for(Corcamento obj : selectedItens){
			
			 orca.add(obj);
		}
		modelItensSelectAux = new DataModelOrcamento(orca);
	}
	
	public void adicionarItem() {
		// criar novos procedimentos
	
		//Cfracassados fracassoObj = new  DAOqueryFracassados().getObjTitulo(fracasso);
		new DAOisereFracassoPregao().addItem(selectedItensAux, fracasso ,listaTodosItens);
		//new DAOisereFracassoPregao().removeItem(selectedItensList, fracasso);
		adicionaModeloTable();
	}
	
	
	public void removeItem() {
		
		new DAOisereFracassoPregao().removeItem(selectedItensList);
		adicionaModeloTable();
	
	}
	
	

	
	
	
	public String enviaPregao(){
		System.out.println("envia pregao");
		List<Citem> listItem  = new ArrayList<Citem>();
		for(Corcamento orca : listaTodosItens){
			listItem .add(orca.getItem());
		}
		statusControl = new StatusControl();
		//criara atributo de pregoa
		statusControl.addStatusEmpenho(listItem, pregao);
         ELFlash.getFlash().put("msg", "1");
		

		return "/processo/criar-pregao?faces-redirect=true";

		// new DAOatualizaPedidoStatus().atualizaEstatusDiretoria(pedidos);
		//ELFlash.getFlash().put("msg", "1");
	}

	
}
