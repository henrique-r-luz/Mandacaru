package dao;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;

import model.*;

public class DAOinsereCatmat implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void adiciona(Ccatmat catmat, CinfoMaterial material) {

		// consegue a entity manager
		EntityManager em = new JPAUtil().getEntityManager();
		try {
			// abre transacao
			em.getTransaction().begin();

			// persiste o objeto
			em.persist(material);
			catmat.setMaterial(material);
			em.persist(catmat);

			// commita a transacao
			em.getTransaction().commit();
		} catch (RuntimeException e) {
			em.getTransaction().rollback(); //não deveria desfazer o objeto deletado aqui?!?!?!?!?!?    
	          throw e; 
		} finally {
			// fecha a entity manager
			em.close();
		}
		FacesContext.getCurrentInstance().addMessage("salvar",
				new FacesMessage("Os dados foram salvos"));
	}

}
