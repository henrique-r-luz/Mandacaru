package dao;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;

import model.Ccatmat;
import model.CinfoMaterialGenerico;

public class DAOisereMateriasGenerico {

	
	public void adiciona(Ccatmat catmat, CinfoMaterialGenerico material){
		
		EntityManager em = new JPAUtil().getEntityManager();
		try {
			// abre transacao
			em.getTransaction().begin();
			
            material.setCatmat(catmat);
            em.persist(material);
			// persiste o objeto
			//em.merge(material);
			catmat.addMaterialGenerico(material);
            em.merge(catmat);
			// commita a transacao
			em.getTransaction().commit();
			FacesContext.getCurrentInstance().addMessage("salvar",
					new FacesMessage("Os dados foram salvos"));
		} catch (RuntimeException e) {
			em.getTransaction().rollback();
			throw e;
		} finally {
			// fecha a entity manager
			em.close();
		}
		
	}
	
}
