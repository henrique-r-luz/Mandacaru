package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import model.Ctipo;
import model.impressao.Iservidor;

public class DAOqueryIservidor {

	public List<Iservidor> listaTodos(String cpf, String nome) {
		EntityManager managers = new JPAUtil().getEntityManager();
		try {
			List<Iservidor> listQuest = null;
		//	if (cpf != "" && nome=="") {
				Query querys = managers
						.createQuery("select s from Iservidor s where s.cpf like  '%"
								+ cpf + "%' and s.nome like '"+nome+"%'");
				listQuest = querys.getResultList();
			//}
			//if(n)
			// managers.close();
			return listQuest;
		} catch (RuntimeException e) {
			// managers.getTransaction().rollback(); // n�o deveria desfazer o
			// objeto
			// deletado aqui?!?!?!?!?!?
			throw e;
		} finally {
			// fecha a entity manager
			managers.close();
		}

	}
	
	
	
	public Iservidor retornServidorId(EntityManager  managers, int id){
		try {
			
		//	if (cpf != "" && nome=="") {
				Query querys = managers
						.createQuery("select s from Iservidor s where s.id="+id);
				 Iservidor servidor = (Iservidor) querys.getSingleResult();
			//}
			//if(n)
			// managers.close();
			return servidor;
		} catch (RuntimeException e) {
			// managers.getTransaction().rollback(); // n�o deveria desfazer o
			// objeto
			// deletado aqui?!?!?!?!?!?
			throw e;
		} finally {
			// fecha a entity manager
		//	managers.close();
		}
	}
	
	
	
	public List<Iservidor> listaTodosSemUsuario() {
		EntityManager managers = new JPAUtil().getEntityManager();
		try {
			List<Iservidor> listQuest = null;
		//	if (cpf != "" && nome=="") {
				Query querys = managers
						.createQuery("select se from Iservidor se where se not in (select s from Iservidor s INNER JOIN s.usuario u )");
				listQuest = querys.getResultList();
			//}
			//if(n)
			// managers.close();
			return listQuest;
		} catch (RuntimeException e) {
			// managers.getTransaction().rollback(); // n�o deveria desfazer o
			// objeto
			// deletado aqui?!?!?!?!?!?
			throw e;
		} finally {
			// fecha a entity manager
			managers.close();
		}

	}

	public boolean pesquisaCpf(String cpf) {
		EntityManager managers = new JPAUtil().getEntityManager();
		try {
			Query querys = managers
					.createQuery("select s from Iservidor s where s.cpf='"
							+ cpf + "'");
			if (querys.getResultList().isEmpty()) {
				return true;
			} else {
				return false;
			}
		} catch (RuntimeException e) {
			// managers.getTransaction().rollback(); // n�o deveria desfazer o
			// objeto
			// deletado aqui?!?!?!?!?!?
			throw e;
		} finally {
			// fecha a entity manager
			 managers.close();
		}
		// managers.close();
		// return listQuest;

	}

	public Iservidor pesquisaUnicoNome(String nome) {
		EntityManager managers = new JPAUtil().getEntityManager();
		try {
			Query querys = managers
					.createQuery("select s from Iservidor s where s.nome='"
							+ nome + "'");
			List<Iservidor> listQuest = querys.getResultList();
			//Iservidor servidor = listQuest.get(0);
			// managers.close();
			Iservidor servidor = new Iservidor();
			if(listQuest.isEmpty()){
				 servidor = new Iservidor();
			}else{
				servidor = listQuest.get(0);
			}
			return servidor;
		} catch (RuntimeException e) {
			// managers.getTransaction().rollback(); // n�o deveria desfazer o
			// objeto
			// deletado aqui?!?!?!?!?!?
			throw e;
		} finally {
			// fecha a entity manager
			 managers.close();
		}
	}
	
	
	public Iservidor pesquisaServidorLogin(String login) {
		EntityManager managers = new JPAUtil().getEntityManager();
		try {
			Query querys = managers
					.createQuery("select s from Iservidor s inner join s.usuario u where u.login='"
							+ login + "'");
			List<Iservidor> listQuest = querys.getResultList();
			Iservidor servidor = new Iservidor();
			if(listQuest.isEmpty()){
				 servidor = new Iservidor();
			}else{
				servidor = listQuest.get(0);
			}
			// managers.close();
			
			return servidor;
		} catch (RuntimeException e) {
			// managers.getTransaction().rollback(); // n�o deveria desfazer o
			// objeto
			// deletado aqui?!?!?!?!?!?
			throw e;
		} finally {
			// fecha a entity manager
			 managers.close();
		}
	}

}
