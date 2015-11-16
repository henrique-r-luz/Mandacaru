package dao;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import controle.Status;
import model.Cgrupo;
import model.Citem;
import model.Corcamento;
import model.Cpedidos;
import model.Cstatus;

public class DAOinsereItemPedido implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void addItem(Citem[] selectedItens, Cpedidos pedidos) {

		EntityManager em = new JPAUtil().getEntityManager();
		try{
		em.getTransaction().begin();

		for (Citem item : selectedItens) {

			if (verificaOrcamento(item, pedidos)) {

				Corcamento orcamento = new Corcamento();
				orcamento.setItem(item);
				orcamento.setPedidos(pedidos);
				em.persist(orcamento);
			
			}else{
				FacesContext.getCurrentInstance().addMessage(
						null,
						new FacesMessage(FacesMessage.SEVERITY_WARN,
								"Não pode adicionar o mesmo item , duas vezes, em um pedido.",
								"Watch out for PrimeFaces!"));
			}
		}
		
		em.getTransaction().commit();
		}catch(RuntimeException e){
			em.getTransaction().rollback();
	          //throw e;    
		}finally{
		em.close();
		}
	}

	public void removeItem(Citem[] selectedItensList, Cpedidos pedidos) {

		for (Citem item : selectedItensList) {

			getOrcamento(item, pedidos);

		}

	}

	public void getOrcamento(Citem item, Cpedidos pedidos) {
		EntityManager managers = new JPAUtil().getEntityManager();
		try{
		managers.getTransaction().begin();
		Query querys = managers
				.createQuery("select o from Corcamento o where o.item.id="
						+ item.getId() + "and o.pedidos.id=" + pedidos.getId());
		List<Corcamento> listQuest = querys.getResultList();
		managers.remove(listQuest.get(0));
		managers.getTransaction().commit();
		} catch (RuntimeException e) {
			managers.getTransaction().rollback(); // não deveria desfazer o objeto
											// deletado aqui?!?!?!?!?!?
			throw e;
		} finally {
			// fecha a entity manager
			managers.close();
		}
		//managers.close();
	}

	public boolean verificaOrcamento(Citem item, Cpedidos pedidos) {
		EntityManager managers = new JPAUtil().getEntityManager();
		managers.getTransaction().begin();
		Query querys = managers
				.createQuery("select o from Corcamento o where o.item.id="
						+ item.getId() + "and o.pedidos.id=" + pedidos.getId());
		List<Corcamento> listQuest = querys.getResultList();
		managers.close();
		if (listQuest.size() == 0) {
			return true;
		} else {
			return false;
		}

	}

}
