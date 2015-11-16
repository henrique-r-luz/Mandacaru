package dao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import model.Citem;
import model.Cpedidos;

public class DAOqueryPedidosItens implements Serializable  {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	public List<Citem> pesquisPedidosItem(int id){
		EntityManager managers = new JPAUtil().getEntityManager();
		Query querys = managers.createQuery("select c from Citem c join fetch c.pedidos p  where p.id="+id);
		List<Citem> listQuest = querys.getResultList();
		managers.close();
		return  listQuest;
		
		 
	}
	
	
	public Cpedidos pesquisPedidosItemStatus(int id){
		EntityManager managers = new JPAUtil().getEntityManager();
		Query querys = managers.createQuery("select p, i, s from Cpedidos p inner join   p.item i inner join  i.status s where p.id="+id);
		List<Cpedidos> listQuest = querys.getResultList();
		managers.close();
		return (Cpedidos) listQuest.get(0);
		
		 
	}
	
	
	

}
