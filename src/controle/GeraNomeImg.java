package controle;

import java.io.Serializable;
import java.util.Calendar;

import seguranca.Md5;

public class GeraNomeImg implements Serializable  {
	
      /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String tipo;


	public GeraNomeImg(String tipoArquivo){
    	  tipo = tipoArquivo;
      }
      
	
	public GeraNomeImg(){
  	  
    }
      
      public String getNome(String complemento){
    	  Calendar data = Calendar.getInstance();
    	  return new Md5().transMd5(data.getTime().toString())
			+ System.currentTimeMillis() +complemento+ tipo;
      }
      
      public String getNomeDoc(String complemento){
    	  Calendar data = Calendar.getInstance();
    	  return new Md5().transMd5(data.getTime().toString())
			+ System.currentTimeMillis() +complemento;
      }
      
      
      public String geraNamePdf(String nome){
    	  String[] tipo = nome.split("\\.");
    	  return tipo[0]+tipo;
      }
      
      
      
}
