package dao;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;

import model.Citem;
import model.Cpedidos;
import model.Cpregao;
import model.Cprocesso;
import model.Cstatus;
import model.CstatusItem;
import model.CstatusPedidos;
import controle.Status;

public class DAOinsereStatus implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CstatusItem geraStatus(Citem item, Cstatus status) {

		CstatusItem statusItem = new CstatusItem();
		statusItem.setTempo(System.currentTimeMillis());
		statusItem.setItem(item);
		statusItem.setStatus(status);

		// for (Citem objItem : item) {
		// status.addItem(item);
		// }

		return statusItem;

	}
   // nessa função tem que ser inserido o status do objeto que vai ser alterado  
	public void insereStatus(List<Citem> item, int valor, Cpedidos pedidos) {
		EntityManager em = new JPAUtil().getEntityManager();
		CstatusPedidos statusPedidos = new DAO<CstatusPedidos>(
				CstatusPedidos.class).buscaPorId(1);
		try {
			// abre transacao
			em.getTransaction().begin();

			Cstatus status = new Cstatus();
			status.setNome(valor);
			em.persist(status);

			for (Citem itemObj : item) {
				CstatusItem statusItem = new CstatusItem();
				statusItem.setStatus(status);
				statusItem.setItem(itemObj);
				statusItem.setTempo(System.currentTimeMillis());
				em.persist(statusItem);
			}
			pedidos.setStatusPedidos(statusPedidos);
			em.merge(pedidos);
			em.getTransaction().commit();
		} catch (RuntimeException e) {
		
			em.getTransaction().rollback(); // não deveria desfazer o objeto
											// deletado aqui?!?!?!?!?!?
			throw e;
		} finally {
			// fecha a entity manager
			em.close();
		}
	}
	
	
	
	public void insereStatusPregao(List<Citem> item, int valor,Cprocesso processo) {
		EntityManager em = new JPAUtil().getEntityManager();
		CstatusPedidos statusPedidos = new DAO<CstatusPedidos>(
				CstatusPedidos.class).buscaPorId(1);
		try {
			// abre transacao
			em.getTransaction().begin();

			Cstatus status = new Cstatus();
			status.setNome(valor);
			em.persist(status);

			for (Citem itemObj : item) {
				CstatusItem statusItem = new CstatusItem();
				statusItem.setStatus(status);
				statusItem.setItem(itemObj);
				statusItem.setTempo(System.currentTimeMillis());
				em.persist(statusItem);
			}
			processo.setStatusPedidos(statusPedidos);
			em.merge(processo);
			em.getTransaction().commit();
		} catch (RuntimeException e) {
			System.out.println("não gravo status");
			em.getTransaction().rollback(); // não deveria desfazer o objeto
											// deletado aqui?!?!?!?!?!?
			throw e;
		} finally {
			// fecha a entity manager
			em.close();
		}
	}
	
	
	
	public void insereStatusEmpenho(List<Citem> item, int valor,Cpregao pregao) {
		EntityManager em = new JPAUtil().getEntityManager();
		CstatusPedidos statusPedidos = new DAO<CstatusPedidos>(
				CstatusPedidos.class).buscaPorId(1);
		try {
			// abre transacao
			em.getTransaction().begin();

			Cstatus status = new Cstatus();
			status.setNome(valor);
			em.persist(status);

			for (Citem itemObj : item) {
				CstatusItem statusItem = new CstatusItem();
				statusItem.setStatus(status);
				statusItem.setItem(itemObj);
				statusItem.setTempo(System.currentTimeMillis());
				em.persist(statusItem);
			}
			pregao.setStatusPedidos(statusPedidos);
			em.merge(pregao);
			em.getTransaction().commit();
		} catch (RuntimeException e) {
			System.out.println("não gravo status");
			em.getTransaction().rollback(); // não deveria desfazer o objeto
											// deletado aqui?!?!?!?!?!?
			throw e;
		} finally {
			// fecha a entity manager
			em.close();
		}
	}
	

	public void atualizaStatus(Cstatus status, List<Citem> item, int valor,
			Cpedidos pedidos) {

		CstatusPedidos statusPedidos = new DAO<CstatusPedidos>(
				CstatusPedidos.class).buscaPorId(1);

		EntityManager em = new JPAUtil().getEntityManager();
		try {
			// abre transacao
			em.getTransaction().begin();

			status.setNome(valor);

			for (Citem itemObj : item) {
				CstatusItem statusItem = new CstatusItem();
				statusItem.setStatus(status);
				statusItem.setItem(itemObj);
				statusItem.setTempo(System.currentTimeMillis());
				em.persist(statusItem);
			}
			pedidos.setStatusPedidos(statusPedidos);
			em.merge(pedidos);

			em.getTransaction().commit();
		} catch (RuntimeException e) {
			em.getTransaction().rollback(); //não deveria desfazer o objeto deletado aqui?!?!?!?!?!?    
	          throw e; 
		} finally {
			// fecha a entity manager
			em.close();
		}
	}
	
	
	public void atualizaStatusProcesso(Cstatus status, List<Citem> item, int valor,
			Cprocesso processo) {

		CstatusPedidos statusPedidos = new DAO<CstatusPedidos>(
				CstatusPedidos.class).buscaPorId(1);

		EntityManager em = new JPAUtil().getEntityManager();
		try {
			// abre transacao
			em.getTransaction().begin();

			status.setNome(valor);

			for (Citem itemObj : item) {
				CstatusItem statusItem = new CstatusItem();
				statusItem.setStatus(status);
				statusItem.setItem(itemObj);
				statusItem.setTempo(System.currentTimeMillis());
				em.persist(statusItem);
			}
			//criar esse relacionamento
			processo.setStatusPedidos(statusPedidos);
			em.merge(processo);

			em.getTransaction().commit();
		} catch (RuntimeException e) {
			em.getTransaction().rollback(); //não deveria desfazer o objeto deletado aqui?!?!?!?!?!?    
	          throw e; 
		} finally {
			// fecha a entity manager
			em.close();
		}
	}
	
	
	public void atualizaStatusEmpenho(Cstatus status, List<Citem> item, int valor,
			Cpregao pregao) {

		CstatusPedidos statusPedidos = new DAO<CstatusPedidos>(
				CstatusPedidos.class).buscaPorId(1);

		EntityManager em = new JPAUtil().getEntityManager();
		try {
			// abre transacao
			em.getTransaction().begin();

			status.setNome(valor);

			for (Citem itemObj : item) {
				CstatusItem statusItem = new CstatusItem();
				statusItem.setStatus(status);
				statusItem.setItem(itemObj);
				statusItem.setTempo(System.currentTimeMillis());
				em.persist(statusItem);
			}
			//criar esse relacionamento
			pregao.setStatusPedidos(statusPedidos);
			em.merge(pregao);

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
