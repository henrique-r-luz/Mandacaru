package controle.ldap;

import java.io.Serializable;

import org.springframework.security.authentication.encoding.LdapShaPasswordEncoder;

import com.novell.ldap.LDAPAttribute;
import com.novell.ldap.LDAPAttributeSet;
import com.novell.ldap.LDAPConnection;
import com.novell.ldap.LDAPEntry;
import com.novell.ldap.LDAPException;
import com.novell.ldap.LDAPSearchResults;

import controle.quota.*;

import com.novell.ldap.util.Base64;

public class ConectLdap implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String servidor;
	private int porta;
	private String cn;
	private String ldapSenha;
	private String usuario;
	private String senha;
	private String ou;
	private String testeConexao;

	
	
	
	public String getTesteConexao() {
		return testeConexao;
	}

	public void setTesteConexao(String testeConexao) {
		this.testeConexao = testeConexao;
	}

	public String getLdapSenha() {
		return ldapSenha;
	}

	public void setLdapSenha(String ldapSenha) {
		this.ldapSenha = ldapSenha;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getServidor() {
		return servidor;
	}

	public void setServidor(String servidor) {
		this.servidor = servidor;
	}

	public int getPorta() {
		return porta;
	}

	public void setPorta(int porta) {
		this.porta = porta;
	}

	public String getCn() {
		return cn;
	}

	public void setCn(String cn) {
		this.cn = cn;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
	

	public String getOu() {
		return ou;
	}

	public void setOu(String ou) {
		this.ou = ou;
	}

	public ConectLdap() {

		if (verificaLdap()) {
			LdapControl ldapBd = new LdapControl();
			ldapBd.dados();
			servidor = ldapBd.getLdap().getServidor();
			porta = ldapBd.getLdap().getPorta();
			cn = ldapBd.getLdap().getCn();
			ldapSenha = ldapBd.getLdap().getSenha();
			ou = ldapBd.getLdap().getOuLogin();
			testeConexao = ldapBd.getLdap().getTesteConexao();
		}

	}

	public boolean verificaLdap() {
		LdapControl ldapBd = new LdapControl();
		if (!ldapBd.getListaTodos().isEmpty()) {
			return true;
		} else {
			return false;
		}

	}

	public boolean testaConexao() {
		if (verificaLdap()) {
			
			LDAPConnection conn = new LDAPConnection();
			try {
				conn.connect(this.servidor, this.porta);
				conn.bind(LDAPConnection.LDAP_V3, this.cn, this.ldapSenha.getBytes());
				 LDAPSearchResults search = conn.search( this.testeConexao,
							LDAPConnection.SCOPE_SUB,null, null, false);

			      while(search.hasMore()){
			         /*Recupera cada entidade presente no resultado.*/
			         LDAPEntry entry = search.next();

			         /*Retorna os atributos para cada entidade*/
			         LDAPAttributeSet attribute = entry.getAttributeSet();

			         java.util.Iterator it = attribute.iterator();
			         /*Itera sobre os atributos imprimindo o nome e valor.*/
			         while(it.hasNext()){
			            LDAPAttribute att = (LDAPAttribute) it.next();

			         //   System.out.println(att.getName() +"=>"+ att.getStringValue());

			         }
			         
			      } 
			      return true;
			} catch (LDAPException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				return false;
			}
			
		} else {
		  return false;	
		}
		
	}

	@SuppressWarnings("deprecation")
	public boolean conexaoLdap() {

		boolean retorno = false;
		LDAPConnection conn = new LDAPConnection();

		/* Cria conexão com servidor 192.168.199.105 na porta 389 */

		/*
		 * Efetua o bind no servidor passando a versão do servidor, o
		 * administrador do diretório e a senha como um array de bytes.
		 */
		/*
		 * try { conn.connect(this.servidor, this.porta);
		 * conn.bind(LDAPConnection.LDAP_V3, this.cn,
		 * this.ldapSenha.getBytes()); } catch (LDAPException e) { // TODO
		 * Auto-generated catch block e.printStackTrace(); }
		 */

		try {
			conn.connect(this.servidor, this.porta);
			conn.bind(LDAPConnection.LDAP_V3, "uid=" + this.usuario
					+ ","+this.ou, this.senha.getBytes());
			try {
				LDAPSearchResults search = conn.search(
						"uid=" + this.usuario
						+ ","+this.ou,
						LDAPConnection.SCOPE_SUB, "(uid = " + this.usuario
								+ ")", null, false);

				while (search.hasMore()) {

					/* Recupera cada entidade presente no resultado. */
					LDAPEntry entry = search.next();

					/* Retorna os atributos para cada entidade */
					LDAPAttributeSet attribute = entry.getAttributeSet();

					@SuppressWarnings("rawtypes")
					java.util.Iterator it = attribute.iterator();
					/* Itera sobre os atributos imprimindo o nome e valor. */
					while (it.hasNext()) {
						LDAPAttribute att = (LDAPAttribute) it.next();

						// /System.out.print(att.addBase64Value(this.senha));
						// SSHAPassword

					}

					// System.out.println();
				}
			} catch (LDAPException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				conn.disconnect();
			} catch (LDAPException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			retorno = true;
		} catch (LDAPException e) {

			// TODO Auto-generated catch block
			e.printStackTrace();
			retorno = false;
		}

		return retorno;

		/*
		 * Realiza busca no servidor baseada nos seguintes parâmetros:
		 * --distinguished name da base de origem da busca; --escopo da busca,
		 * que também pode ser modificado para pesquisar na subárvore do
		 * distinguished name; --filtro por algum critério; --atributos a serem
		 * recuperados; --true para retornar apenas o nomes dos atributos, false
		 * para retornar nomes e valores dos atributos encontrados
		 */

	}

}
