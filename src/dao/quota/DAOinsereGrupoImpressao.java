package dao.quota;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;

import controle.quota.Label;
import dao.JPAUtil;
import model.impressao.Iservidor;
import model.quota.QgrupoImpressao;

public class DAOinsereGrupoImpressao implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	
	public void atualiza( List<Iservidor> servidorList , QgrupoImpressao grupo, List<Iservidor> removeServidor) {
		EntityManager em = new JPAUtil().getEntityManager();
		try {
			em.getTransaction().begin();
			 for(Iservidor rem: removeServidor ){
				 if(rem.getGrupoImpressao()!=null)
		    	  rem.setGrupoImpressao(null);
				  em.merge(rem);
		       }
		    for(Iservidor serv: servidorList){  
		        
		    	serv.setGrupoImpressao(grupo);
		    	em.merge(serv);
		    	
		     }  


			em.getTransaction().commit();
			FacesContext.getCurrentInstance().addMessage("salvar",
					new FacesMessage(Label.getDadosSalvo()));
		} catch (RuntimeException e) {
			em.getTransaction().rollback();
			FacesContext.getCurrentInstance().addMessage("salvar",
					new FacesMessage(	FacesMessage.SEVERITY_ERROR,Label.getTransacaoNao(),null));
			throw e;
			
		} finally {
			// fecha a entity manager
			em.close();
		}
	}

	

}
