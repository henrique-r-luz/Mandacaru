package controle.quota;

import java.io.IOException;
import java.io.Serializable;

import controle.laboratorio.RemoteComando;

public class TestaConexao  implements Serializable {
	
	
	/**
	 * 
	 */
	
	private static final long serialVersionUID = 1L;
	private RemoteComando shell;
	
	
	
	
	
	 public boolean funcTestaConexao(String ip, String usuario, String senha){
	    	
		 shell = new RemoteComando(ip, usuario,
					senha);
		 
		 
	    	String resp="";
	    	try {
				resp = shell.executeCommand("ls -a");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	if(resp==""){
	    		return false;
	    	}else{
	    		return true;
	    	}
	    }

}
