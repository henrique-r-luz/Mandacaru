package controle;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean
@ViewScoped
public class PaginaControle implements Serializable  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//private String pagina="/WEB-INF/componentes/tabelaItem.xhtml";
	private String pagina="/criar-pedido.xhtml";

	public String getPagina() {
		return pagina;
	}

	
	
	public void setPage1(){
		pagina ="/WEB-INF/componentes/tabelaItem.xhtml" ;
	}
	
}
