package controle.laboratorio;

import java.io.IOException;
import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;


@ManagedBean
@ViewScoped
public class LinuxControl implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private String comando;
	
	
	

	public String getComando() {
		return comando;
	}




	public void setComando(String comando) {
		this.comando = comando;
	}




	public void execute() {

		final RemoteComando shell = new RemoteComando("192.168.11.33", "root",
				"if2N4&mg0");
		try {
			shell.executeCommand(getComando());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Leia mais em: Executando Shell Scripts utilizando Java
		// http://www.devmedia.com.br/executando-shell-scripts-utilizando-java/26494#ixzz2tlsscBUe

	}

}
