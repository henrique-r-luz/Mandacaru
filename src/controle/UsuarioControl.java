package controle;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.authentication.encoding.PasswordEncoder;
import org.springframework.security.core.context.SecurityContextHolder;

import controle.quota.GrupoImpressaoControl;
import controle.quota.Label;
import dao.DAO;
import dao.DAOinsereUsuarioStatus;
import dao.DAOqueryIservidor;
import dao.DAOqueryUsuario;
import dao.quota.DAOqueryGrupoImpressao;
import model.Cusuario;
import model.CusuarioStatus;
import model.impressao.Iservidor;
import model.quota.QgrupoImpressao;
import seguranca.*;

@ManagedBean
@ViewScoped
public class UsuarioControl implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String comfirmarSenha;
	private Cusuario usuario = new Cusuario();
	private String loginInicial = "";
	// @SuppressWarnings("unused")
	// private CusuarioStatus statusUsuario = new CusuarioStatus();
	public boolean teste = true;
	private String login = "";
	private String nome = "";
	private QgrupoImpressao grupoImpressao = new QgrupoImpressao();
    private boolean senha1 = false;
    private boolean senha2 = false;
    private boolean ldap = false;
  
    
    
    
    
    
	
	
	
	public boolean isLdap() {
		return ldap;
	}

	public void setLdap(boolean ldap) {
		this.ldap = ldap;
	}

	public boolean isSenha1() {
		return senha1;
	}

	public void setSenha1(boolean senha1) {
		this.senha1 = senha1;
	}

	public boolean isSenha2() {
		return senha2;
	}

	public void setSenha2(boolean senha2) {
		this.senha2 = senha2;
	}

	public QgrupoImpressao getGrupoImpressao() {
		return grupoImpressao;
	}

	public void setGrupoImpressao(QgrupoImpressao grupoImpressao) {
		this.grupoImpressao = grupoImpressao;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getComfirmarSenha() {
		return comfirmarSenha;
	}

	public void setComfirmarSenha(String comfirmarSenha) {
		PasswordEncoder encoder = new Md5PasswordEncoder();
		this.comfirmarSenha = encoder.encodePassword(comfirmarSenha, null);

	}

	public Cusuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Cusuario usuario) {
		loginInicial = usuario.getLogin();
		this.usuario = usuario;

	}

	public UsuarioControl() {
		usuario.setLdap(false);
		senha1 = senha2= false;
		// new DAOinsereUsuarioStatus().Insere();
	}

	public void salva() {

		if (new DAOqueryUsuario().pesquisaNome(usuario.getLogin()).isEmpty()) {

			if ((comfirmarSenha.equals(usuario.getSenha())) || usuario.isLdap()==true) {
				new DAOinsereUsuarioStatus().Insere(usuario, grupoImpressao);
				// new DAO<Cusuario>(Cusuario.class)
				// .adiciona(usuario);
				clear();
			} else {
				usuario.setSenha("");
				comfirmarSenha = "";
				FacesContext.getCurrentInstance().addMessage(
						"salvar",
						new FacesMessage(FacesMessage.SEVERITY_FATAL,
								Label.getSenhaConfirmada(), null));
			}
		} else {
			FacesContext.getCurrentInstance().addMessage(
					"salvar",
					new FacesMessage(FacesMessage.SEVERITY_WARN,
							Label.getLoginExite(), "Watch out for PrimeFaces!"));
		}

	}

	public void excluir() {

		// new DAOinsereUsuarioStatus().(usuario);
		new DAO<Cusuario>(Cusuario.class).remove(usuario);
		clear();

		FacesContext.getCurrentInstance().addMessage("salvar",
				new FacesMessage(Label.getExcluir()));

	}

	public void altera() {

		// n�o possui nenhum efeito
		// String loginInicial = usuario.getLogin();

		if (new DAOqueryUsuario().pesquisaNome(usuario.getLogin()).isEmpty() || usuario.isLdap()==true) {
           // System.out.println("isLdap>>>"+usuario.isLdap());
			if ((comfirmarSenha.equals(usuario.getSenha())) || usuario.isLdap()==true) {
				// usuario.setSenha(comfirmarSenha)
				new DAOinsereUsuarioStatus().altera(usuario, grupoImpressao);
				// new DAO<Cusuario>(Cusuario.class)
				// .atualiza(usuario);
				clear();
			} else {
				usuario.setSenha("");
				comfirmarSenha = "";
				FacesContext.getCurrentInstance().addMessage(
						"salvar",
						new FacesMessage(FacesMessage.SEVERITY_FATAL,
								Label.getSenhaConfirmada(), null));
			}
		} else {
			if (loginInicial.equals(usuario.getLogin())) {

				if ((comfirmarSenha.equals(usuario.getSenha()))) {
					// usuario.setSenha(comfirmarSenha)
					new DAOinsereUsuarioStatus()
							.altera(usuario, grupoImpressao);
					// new DAO<Cusuario>(Cusuario.class)
					// .atualiza(usuario);
					clear();
				} else {
					usuario.setSenha("");
					comfirmarSenha = "";
					FacesContext.getCurrentInstance().addMessage(
							"salvar",
							new FacesMessage(FacesMessage.SEVERITY_FATAL,
									Label.getSenhaConfirmada(), null));
				}

			} else {
				FacesContext.getCurrentInstance().addMessage(
						"salvar",
						new FacesMessage(FacesMessage.SEVERITY_WARN,
								Label.getLoginExite(),
								"Watch out for PrimeFaces!"));
			}
		}

	}

	public void mudaStatus(Cusuario user) {

		if (user.getStatus().isStatus() == true) {
			user.setStatus(new DAO<CusuarioStatus>(CusuarioStatus.class)
					.buscaPorId(2));
		} else {
			user.setStatus(new DAO<CusuarioStatus>(CusuarioStatus.class)
					.buscaPorId(1));
		}

		new DAO<Cusuario>(Cusuario.class).atualiza(user);
	}

	public void clear() {
		usuario = new Cusuario();
		grupoImpressao = new QgrupoImpressao();
		comfirmarSenha = "";
	}

	public String getSecao() {
		return SecurityContextHolder.getContext().getAuthentication().getName();
	}
	
	
	public List<Cusuario> getListaTodos() {
		return new DAOqueryUsuario().listaTodos(login, nome);
		// return new DAO<Cusuario>(Cusuario.class).listaTodos();
	}

	public void atualizaRegistro() {
		grupoImpressao = usuario.getServidor().getGrupoImpressao();
		// System.out.println("nome Grupo>>>>>"+grupoImpressao.getNome());
	}

	public String mostraPrimeiroItenGrupoImpressao() {
		
		if (grupoImpressao.getNome() == null)
			return "selecione um item";
		else
			return grupoImpressao.getNome();

	}
	
	
	public void abiltaLdap(){
		if(usuario.isLdap()==false){
			
			//senha1 = senha2= true;
			usuario.setSenha("");
			comfirmarSenha = "";
		//	usuario.setLdap(true);
		}else{
			//senha1 = senha2= false;
			//usuario.setLdap(false);
		}
	}

}
