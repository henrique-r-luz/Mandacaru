package dao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;

import model.Citem;
import model.Corcamento;

public class DAOinsereOrcamento implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void insereOrcamento(List<Citem> itens) {
		EntityManager em = new JPAUtil().getEntityManager();
		try {
			em.getTransaction().begin();

			for (Citem item : itens) {

				Corcamento orcamento = new Corcamento();
				orcamento.setItem(item);
				em.persist(orcamento);
			}

			// em.merge(pedidos);

			// new DAO<Cpedidos>(Cpedidos.class).atualiza(pedidos);
			em.getTransaction().commit();
		} catch (RuntimeException e) {
			em.getTransaction().rollback(); //não deveria desfazer o objeto deletado aqui?!?!?!?!?!?    
	          throw e;    
		} finally {
			em.close();
		}

	}
}
