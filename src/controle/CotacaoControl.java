package controle;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import dao.DAO;
import dao.DAOqueryCotacao;
import model.Ccotacao;
import model.Corcamento;

@ManagedBean
@ViewScoped
public class CotacaoControl implements Serializable  {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Ccotacao cotacao = new Ccotacao();
	private int idOrcamento;
	
	

	public int getIdOrcamento() {
		return idOrcamento;
	}

	public void setIdOrcamento(int idOrcamento) {
		this.idOrcamento = idOrcamento;
	}

	public Ccotacao getCotacao() {
		return cotacao;
	}

	public void setCotacao(Ccotacao cotacao) {
		this.cotacao = cotacao;
	}
	
    
	
	public void salvaCotacao(String nomeBd, Corcamento orcamento){
		if(new DAOqueryCotacao().verificaImg(cotacao.getUrl())){
		      salva(nomeBd,orcamento);
		}else{
			cotacao.setUrl(new GeraNomeImg(".jpg").getNome("#"));
			salva(nomeBd,orcamento);
		}
	}
	
	
	public void salva(String nomeBd, Corcamento orcamento){
		if(getListaTodos().size()<3){
			
			//criar uma DAO pra inserir os objetos
		cotacao.setUrl(nomeBd);
		 cotacao.setOrcamento(orcamento);
		 new DAO<Ccotacao>(Ccotacao.class).adiciona(cotacao);
		}else{
			FacesContext.getCurrentInstance().addMessage(
					"salvar",
					new FacesMessage(FacesMessage.SEVERITY_WARN,
							"Só pode Haver 3 Orçamentos",
							null));
		}
	}
	
	public void clear(){
	
		cotacao = new Ccotacao();
	}
	
	
	
	
	public List<Ccotacao> getListaTodos(){
	
		return new DAOqueryCotacao().pesquisaNome(idOrcamento);
		
		//return new DAO<Ccotacao>(Ccotacao.class).listaTodos();
	}
	
	public String vericaStatus(int idOrcamento){
		
		if(new DAOqueryCotacao().pesquisaNome(idOrcamento).size()==3){
			return "ui-icon-check";
		}else{
			return "ui-icon ui-icon-closethick";
		}
	}
	
	// verifica se existe 3 cotações
	public boolean verificaCotacao(Corcamento orcamento){
		
		if(new DAOqueryCotacao().contaCotacao(orcamento.getId())>=3){
			return true;
		}
		else{
			return false;
		}
		
	}
	
	

}
