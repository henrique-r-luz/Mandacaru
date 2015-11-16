package dao;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaQuery;
import controle.quota.*;

public class DAO<T> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final Class<T> classe;

	public DAO(Class<T> classe) {
		this.classe = classe;
	}

	public void adiciona(T t) {

		// consegue a entity manager
		EntityManager em = new JPAUtil().getEntityManager();
		try {
			// abre transacao
			em.getTransaction().begin();

			// persiste o objeto
			em.persist(t);

			// commita a transacao
			em.getTransaction().commit();
			FacesContext.getCurrentInstance().addMessage("salvar",
					new FacesMessage(Label.getDadosSalvo()));
		} catch (RuntimeException e) {
			em.getTransaction().rollback();
			throw e;
		} finally {
			// fecha a entity manager
			em.close();
		}

	}

	public void remove(T t) {
		EntityManager em = new JPAUtil().getEntityManager();

		em.getTransaction().begin();

		em.remove(em.merge(t));

		em.getTransaction().commit();
		em.close();
	}

	public void atualiza(T t) {
		EntityManager em = new JPAUtil().getEntityManager();
		try {
			em.getTransaction().begin();

			em.merge(t);

			em.getTransaction().commit();
			FacesContext.getCurrentInstance().addMessage("salvar",
					new FacesMessage(Label.getDadosSalvo()));
		} catch (RuntimeException e) {
			em.getTransaction().rollback();
			throw e;
		} finally {
			// fecha a entity manager
			em.close();
		}
	}

	public List<T> listaTodos() {
		EntityManager em = new JPAUtil().getEntityManager();
		try {
		CriteriaQuery<T> query = em.getCriteriaBuilder().createQuery(classe);
		query.select(query.from(classe));

		List<T> lista = em.createQuery(query).getResultList();

		// em.close();
		return lista;
		} catch (RuntimeException e) {
			//em.getTransaction().rollback();
			throw e;
		} finally {
			// fecha a entity manager
			em.close();
		}
	}
	
	public T getObj() {
		EntityManager em = new JPAUtil().getEntityManager();
		try {
		CriteriaQuery<T> query = em.getCriteriaBuilder().createQuery(classe);
		query.select(query.from(classe));

		T lista = em.createQuery(query).getSingleResult();

		// em.close();
		return lista;
		} catch (RuntimeException e) {
			//em.getTransaction().rollback();
			throw e;
		} finally {
			// fecha a entity manager
			em.close();
		}
	}


	public T buscaPorId(int id) {
		EntityManager em = new JPAUtil().getEntityManager();
		T instancia = em.find(classe, id);
		em.close();
		return instancia;
	}

	public List<T> listaTodosPaginada(int firstResult, int maxResults) {
		EntityManager em = new JPAUtil().getEntityManager();
		CriteriaQuery<T> query = em.getCriteriaBuilder().createQuery(classe);
		query.select(query.from(classe));

		List<T> lista = em.createQuery(query).setFirstResult(firstResult)
				.setMaxResults(maxResults).getResultList();

		em.close();
		return lista;
	}

}
