package controle.impressao;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import controle.quota.Label;
import dao.DAO;
import dao.DAOqueryIservidor;
import model.impressao.Iservidor;

@ManagedBean
@ViewScoped
public class ServidorControl implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Iservidor servidor = new Iservidor();
	private String cpfAtual = "";
	private String cpf="";
	private String nome ="";
	
	
	

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Iservidor getServidor() {
		return servidor;
	}

	public void setServidor(Iservidor servidor) {
		this.servidor = servidor;
		cpfAtual = servidor.getCpf();
	}

	public void salva() {
		DAOqueryIservidor dao = new DAOqueryIservidor();
		if (dao.pesquisaCpf(servidor.getCpf())) {
			new DAO<Iservidor>(Iservidor.class).adiciona(servidor);
			clear();
		} else {
			FacesContext.getCurrentInstance().addMessage(
					"salvar",
					new FacesMessage(FacesMessage.SEVERITY_WARN,
							Label.getCpfExist(), null));
		}
	}

	public void altera() {
		DAOqueryIservidor dao = new DAOqueryIservidor();
		if (servidor.getCpf().equals(cpfAtual)) {
			 new DAO<Iservidor>(Iservidor.class).atualiza(servidor);
			 clear();
		} else {
			if (dao.pesquisaCpf(servidor.getCpf())) {
				new DAO<Iservidor>(Iservidor.class).atualiza(servidor);
				clear();
			} else {
				FacesContext.getCurrentInstance().addMessage(
						"salvar",
						new FacesMessage(FacesMessage.SEVERITY_WARN,
								Label.getCpfOperacao(), null));
			}
		}
	}

	public void clear() {
		servidor = new Iservidor();
	}

	public List<Iservidor> getListaTodos() {
         
		return new DAOqueryIservidor().listaTodos(cpf,nome);

	}
	
	
	public List<Iservidor> getListaTodosSemUsuario() {
        
		return new DAOqueryIservidor().listaTodosSemUsuario();

	}
	
	public void atualizaLista(){
		
	}
	
	
	

}
