package controle;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;

import java.io.StringReader;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.ParserConfigurationException;

import javax.xml.parsers.DocumentBuilderFactory;

import model.ConfigModel;

import org.w3c.dom.Document;

import org.w3c.dom.Element;

import org.w3c.dom.Node;

import org.w3c.dom.NodeList;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import dao.DAO;

//Leia mais em: Manipulando arquivos XML em Java http://www.devmedia.com.br/manipulando-arquivos-xml-em-java/3245#ixzz2fkbNJHEX

@ManagedBean
@RequestScoped
public class Config implements Serializable  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private  ConfigModel conf = new ConfigModel();
	private List<ConfigModel> lista =  new DAO<ConfigModel>( ConfigModel.class).listaTodos();

	
	
	
	
	public ConfigModel getConf() {
		return conf;
	}


	public void setConf(ConfigModel conf) {
		this.conf = conf;
	}


	public String getUrl(){
		
	   if(!lista.isEmpty()){
		   conf  = lista.get(0);
		  return  conf.getUrlDiretorio();
	   }else{
		   FacesContext.getCurrentInstance().addMessage("salvar", new FacesMessage(FacesMessage.SEVERITY_FATAL,"É necessário realizar a configuração do sistema", null)); 
		   return "";
	   }
		/*if(System.getProperty("os.name").equals("Windows 7")){
			return "D:\\disco E\\compras2\\sistema\\WebContent\\";
		}else{
			return "/usr/share/tomcat7/webapps/sistema/";
		}*/
	}

		
   public String getUrlBase(){
	   
	   if(!lista.isEmpty()){
		   conf  = lista.get(0);
		  return  conf.getUrlBase();
	   }else{
		   FacesContext.getCurrentInstance().addMessage("salvar", new FacesMessage(FacesMessage.SEVERITY_FATAL,"É necessário realizar a configuração do sistema", "Watch out for PrimeFaces!")); 
		   return "";
	   }
	 /*  if(System.getProperty("os.name").equals("Windows 7")){
			return "http://localhost:8080/sistema";
		}else{
			return "http://192.168.11.46:8080/sistema";
		}*/
	   
   }
   
  public String getFileImageOrcamento(){
	  return "orcamento";
  }
  
  
  public String getDocImpressao(){
	  if(System.getProperty("os.name").equals("Windows 7")){
		  return "docImpressao\\";
		}else{
			return "docImpressao/";
		}
	  
  }
  
  public String getIpServidor(){
	  
	  if(!lista.isEmpty()){
		   conf  = lista.get(0);
		  return  conf.getIpServidor();
	   }else{
		   FacesContext.getCurrentInstance().addMessage("salvar", new FacesMessage(FacesMessage.SEVERITY_FATAL,"É necessário realizar a configuração do sistema", "Watch out for PrimeFaces!")); 
		   return "";
	   }
  }
  
  //usuario servidor
  
  

	

		
		
		
	
}
