package dao;

import java.io.Serializable;

import javax.persistence.EntityManager;

import model.CstatusPedidos;

public class DAOinsereStatusPedido implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void insere() {
		EntityManager em = new JPAUtil().getEntityManager();
		try {
			em.getTransaction().begin();

			CstatusPedidos status1 = new CstatusPedidos();
			status1.setId(0);
			status1.setNome("Aberto");
			em.persist(status1);
			CstatusPedidos status2 = new CstatusPedidos();
			status2.setId(1);
			status2.setNome("Fechado");
			em.persist(status2);

			em.getTransaction().commit();
		} catch (RuntimeException e) {
			em.getTransaction().rollback(); //não deveria desfazer o objeto deletado aqui?!?!?!?!?!?    
	          throw e; 
		} finally {
			// fecha a entity manager
			em.close();
		}
	}

}
