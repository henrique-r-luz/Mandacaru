package dao.quota;

import java.io.Serializable;
import java.util.List;



import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.primefaces.context.RequestContext;

import controle.quota.Label;
import controle.quota.Quota;
import model.impressao.Iservidor;
import model.quota.QcopiasRealizadas;
import dao.JPAUtil;

public class DAOinsereCopias implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public void salva(QcopiasRealizadas  copias, String login, Quota quota) {
		EntityManager em = new JPAUtil().getEntityManager();
		try {
			em.getTransaction().begin();
			Query querys = em.createQuery("select s from  Iservidor s inner join s.usuario u where u.login='"+login+"'");
		  
			Iservidor servidor = (Iservidor) querys.getSingleResult();
          //  List<Object[]> obj = querys.getResultList();
			//System.out.println("obj>>>>"+obj.size());
			
            
            
		    copias.setServidor(servidor);
		    em.persist(copias);
		    
			em.getTransaction().commit();
		
			FacesContext.getCurrentInstance().addMessage("salvar",
					new FacesMessage(Label.getDocumentoImpressora()));
			
			
		//	 quota.setControlePop("pop.hide()");
		    RequestContext request = RequestContext.getCurrentInstance();  
		   request.addCallbackParam("sucesso", true);  
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
