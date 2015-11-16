package controle.quota;

import java.io.File;
import java.io.Serializable;
import java.io.IOException;
import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import controle.Config;
import controle.laboratorio.RemoteComando;

@ManagedBean
@ViewScoped
public class ConexCups implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	final RemoteComando shell;
	private String usuario;
	private String senha;
	private String ip;
	
	
	

	public ConexCups(){
		Config config = new Config();
		
		
		
        ip = config.getIpServidor();
     
		shell = new RemoteComando(ip, usuario,
			senha);
		//shell = new RemoteComando("192.168.0.104", "tomcat",
			//	"tomcat");
	}

	public boolean executeImpressao(String url, int NumeroCopias, String range,
			String impressora, String nome, boolean paisagem) {

		// converte pdf
		// unoconv -f pdf ques.odt
		String resp = "";
		String aux ="";
		int cont = 0;
		int cont2 = 0;
		try {
			// modelo de script
			// lpr -U henrique.luz -P ti -o page-ranges=2 -C henrique01
			// edital_processo_selecao_mestrado_modelagem_computacional_sistemas_02_2014.pdf
			String str = "";
			if(paisagem==true){
				str="landscape";
			}else{
				str="portrait";
			}

			shell.executeCommand("lpr -o "+str+" -#" + NumeroCopias + " -U " + nome
					+ " -P " + impressora + "  -o page-ranges=" + range + " "
					+ url);
			// shell.executeCommand("ifconfig");
			while (resp == "") {

				if (cont > 10) {
					//System.out.println("breakkkkk");
				//	return false;
					break;

				}
				// Aguarde 5 segundos

				try {
					Thread.sleep(300);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				resp = shell.executeCommand("lpstat -W all " + impressora
						+ " | grep  '" + nome + "'");
			//	System.out.println("job all>>>>>"+resp);
				cont++;

			}
			aux = resp;
			resp = "";
			while (resp == "") {

			
				// Aguarde 5 segundos

				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				resp = shell.executeCommand("lpstat -W completed " + impressora
						+ " | grep  '" + nome + "'");
				
			//	System.out.println("complet>>>>"+resp);
				
				//cont2++;

			}
			
			
			if (resp == "") {
				String[] job = aux.split("-");
				shell.executeCommand("lprm -P "+impressora+" "+job[1]);
				return false;
			} else {
				return true;
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}

		// Leia mais em: Executando Shell Scripts utilizando Java
		// http://www.devmedia.com.br/executando-shell-scripts-utilizando-java/26494#ixzz2tlsscBUe

	}

	public String verificaImpressora(String impressora) {
		String resp = "";
		try {
			// modelo de script
			// lpr -U henrique.luz -P ti -o page-ranges=2 -C henrique01
			// edital_processo_selecao_mestrado_modelagem_computacional_sistemas_02_2014.pdf

			resp = shell.executeCommand("ping " + impressora + " -c 1");

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return resp;
	}

	public void convertArq(String url) {

		try {
         
		    String resp = shell.executeCommand("unoconv -f pdf " + url);
           // String resp = shell.executeCommand("ls -a" + url);
		  
			// shell.executeCommand("ifconfig");

		} catch (IOException e) {
			
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}

	}
	

   
}
