package controle;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import dao.DAO;
import dao.DAOinsereStatus;
import dao.DAOqueryStatus;

import model.Citem;
import model.Cpedidos;
import model.Cpregao;
import model.Cprocesso;
import model.Cstatus;

@ManagedBean
@ViewScoped
public class StatusControl implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Cstatus status;

	public Cstatus getStatus() {
		return status;
	}

	public void setStatus(Cstatus status) {
		this.status = status;
	}

	/*public void addStatusProcesso(Citem item) {
		status = new DAOqueryStatus().pesquisaNome(Status.EM_DIRETORIA_PROCESSO
				.getValor());
		status.addItem(item);

	}*/

	public void addStatusProcesso(List<Citem> item, Cpedidos pedidos) {
		status = new DAOqueryStatus().pesquisaNome(Status.EM_DIRETORIA_PROCESSO
				.getValor());
		if (status == null) {
			new DAOinsereStatus().insereStatus(item,
					Status.EM_DIRETORIA_PROCESSO.getValor(), pedidos);

		}else{
			new DAOinsereStatus().atualizaStatus(status, item, Status.EM_DIRETORIA_PROCESSO.getValor(),pedidos);
		}

	}
	
	
	public void addStatusPregao(List<Citem> item, Cprocesso processo) {
		status = new DAOqueryStatus().pesquisaNome(Status.EM_PREGAO
				.getValor());
		if (status == null) {
			System.out.println("insere status");
			new DAOinsereStatus().insereStatusPregao(item,
					Status.EM_PREGAO.getValor(), processo);

		}else{
			new DAOinsereStatus().atualizaStatusProcesso(status, item, Status.EM_PREGAO.getValor(),processo);
		}

	}
	
	public void addStatusEmpenho(List<Citem> item, Cpregao pregao) {
		status = new DAOqueryStatus().pesquisaNome(Status.EM_EMPENHO
				.getValor());
		if (status == null) {
			System.out.println("insere status");
			new DAOinsereStatus().insereStatusEmpenho(item,
					Status.EM_EMPENHO.getValor(), pregao);

		}else{
			new DAOinsereStatus().atualizaStatusEmpenho(status, item, Status.EM_PREGAO.getValor(),pregao);
		}

	}

}
