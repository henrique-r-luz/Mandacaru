package dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import model.Corcamento;
import model.Cpedidos;
import model.Citem;

public class DAOqueryPedido implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public List<Cpedidos> pesquisaNome(String nome) {
		EntityManager managers = new JPAUtil().getEntityManager();
		Query querys = managers
				.createQuery("select c from Cpedidos c where c.nome='" + nome
						+ "'");
		List<Cpedidos> listQuest = querys.getResultList();
		managers.close();
		return listQuest;

	}

	

	public List<Cpedidos> listaTodosUsuarios(String login) {
		EntityManager managers = new JPAUtil().getEntityManager();
		Query querys = managers
				.createQuery("select c from Cpedidos c  inner join c.statusPedidos s where c.usuario.login='"
						+ login + "'");
		List<Cpedidos> listQuest = querys.getResultList();

		// Query querys2 =
		// managers.createQuery("select p.nome from Cpedidos p inner join p.orcamento o join o.item i inner join  i.statusItem si inner join si.status s  where s.nome=0");
		// List<Cpedidos> listQuest2 = querys2.getResultList();
		// System.out.print("teste join>>>>>"+listQuest2);

		// entra com
		/*
		 * Query querys2 = managers.createQuery(
		 * "select DISTINCT c, max(si.tempo), s.nome from Cpedidos c inner join c.orcamento o inner join  o.item i inner join  i.statusItem si inner join si.status s where c.usuario.login='"
		 * +login+"'  GROUP BY c.id, s.nome"); List<Object[]> queryRes =
		 * querys2.getResultList(); List<Cpedidos> listQuest = new
		 * ArrayList<Cpedidos>(); for (Object[] res : queryRes) { Cpedidos
		 * pedido = (Cpedidos) res[0]; listQuest.add(pedido); long tempo =
		 * (Long) res[1]; int valor = (Integer) res[2];
		 * System.out.print("status>>> "+valor);
		 * 
		 * 
		 * }
		 */

		managers.close();
		return listQuest;

	}

	public boolean VerificaPedido(int id) {
		EntityManager managers = new JPAUtil().getEntityManager();
		Query querys = managers
				.createQuery("select c from Cpedidos c inner join c.statusPedidos s where s.id=0 and c.id="
						+ id );
		List<Cpedidos> listQuest = querys.getResultList();
		System.out.println("listQuest>>>>>>>"+listQuest);
		managers.close();
		if (listQuest.size() == 0) {
			return false;
		} else {
			return true;
		}
	}

}
