package controle;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.springframework.security.core.context.SecurityContextHolder;

import com.sun.faces.context.flash.ELFlash;

import dao.DAO;
import dao.DAOqueryPedido;
import dao.DAOqueryPregao;
import dao.DAOqueryUsuario;

import model.Cpregao;
import model.Cprocesso;
import model.CstatusPedidos;
import model.Cusuario;

@ManagedBean
@ViewScoped
public class PregaoControl implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Cpregao pregao = new Cpregao();
	private Cprocesso processo = new Cprocesso();
	private String listaProcesso;
	private List<Cpregao> listaPregao;

	public String getListaProcesso() {
		return listaProcesso;
	}

	public void setListaProcesso(String listaProcesso) {
		this.listaProcesso = listaProcesso;
	}

	public Cprocesso getProcesso() {
		return processo;
	}

	public void setProcesso(Cprocesso processo) {
		this.processo = processo;
	}

	public Cpregao getPregao() {
		return pregao;
	}

	public void setPregao(Cpregao pregao) {
		this.pregao = pregao;
	}

	public List<Cpregao> getListaTodos() {

		listaPregao = new DAOqueryPregao().listatodos();
		return listaPregao;
	}

	public void salvaPregao() {
		pregao.setUsuario((Cusuario) new DAOqueryUsuario().pesquisaNome(
				SecurityContextHolder.getContext().getAuthentication()
						.getName()).get(0));
		pregao.addProcesso(processo);// inserção dever ser feita por uma
										// transação
		pregao.setStatusPedidos(new DAO<CstatusPedidos>(
				CstatusPedidos.class).buscaPorId(0));
		// verifica se o número de pregao já exite
		if (new DAOqueryPregao().verificaNumPregao(pregao.getNumero())
				.isEmpty()) {
			// verifica se o processo já foi atribuido a um pregão
			if(new DAOqueryPregao().verificaProcesso(processo.getNumero()).isEmpty()){
				new DAO<Cpregao>(Cpregao.class).adiciona(pregao);
				clear();
			}else{
				FacesContext.getCurrentInstance()
				.addMessage(
						null,
						new FacesMessage(FacesMessage.SEVERITY_WARN,
								"O processo ja foi atribuido a um pregão",
								"Watch out for PrimeFaces!"));
			}
		}
		else{
			FacesContext.getCurrentInstance()
			.addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_WARN,
							"Já existe um pregão com esse número",
							"Watch out for PrimeFaces!"));
		}
		// new DAOinserePregao().adiciona(pregao, processo);
		
	}
	
	public String criaListaPergao(int id){
		boolean bool =  new DAOqueryPregao().VerificaPedido(id) ; 
		
		ELFlash.getFlash().put("id", id);
		Cpregao pregaoAux = new DAOqueryPregao().pesquisaPregaoId(id);
		//essa linha seta que cada pregão so pode ter um processo vinculado
		//Cprocesso processoAux = (Cprocesso) pregaoAux.getProcessoList().get(0);
		//ELFlash.getFlash().put("processo", processoAux.getNumero());
		ELFlash.getFlash().put("pregao", pregaoAux);
		if( bool){
		return "/processo/lista-pregao?faces-redirect=true";
		}else{
			return "/processo/pregao-fechado?faces-redirect=true";
		}
	}

	public void clear() {
		processo = new Cprocesso();
		pregao = new Cpregao();
	}

}
