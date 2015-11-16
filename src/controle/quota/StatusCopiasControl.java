package controle.quota;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import dao.DAO;


import model.quota.QcopiasRealizadas;
import model.quota.QstatusCopias;


@ManagedBean
@ViewScoped
public class StatusCopiasControl implements Serializable {

	/**
	 * 
	 */
	
	private static final long serialVersionUID = 1L;
	
	private QstatusCopias statusCopias = new QstatusCopias();
	
	private QcopiasRealizadas copias = new QcopiasRealizadas();
	

	


	public QcopiasRealizadas getCopias() {
		return copias;
	}



	public void setCopias(QcopiasRealizadas copias) {
		this.copias = copias;
	}



	public QstatusCopias getStatusCopias() {
		return statusCopias;
	}



	public void setStatusCopias(QstatusCopias statusCopias) {
		this.statusCopias = statusCopias;
	}



	public void salvar(){
		
		    statusCopias.setCopias(copias);
		    statusCopias.setStatus(true);
			new DAO<QstatusCopias>(QstatusCopias.class).adiciona(statusCopias);
			clear();
	
		
		
	}
	
	
	
	public void clear(){
		statusCopias = new  QstatusCopias();
	}
	
	
	
	

}
