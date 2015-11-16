package dao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import model.Cpedidos;

public class DAOqueryPedidosStatus implements Serializable  {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;



	public int pesquisPedidosStatusAberto(int pedidosId){
		EntityManager managers = new JPAUtil().getEntityManager();
		Query querys = managers.createQuery("select  p from CstatusPedidos s inner join s.pedidos p where s.id=0 and p.id="+pedidosId);
		List<Cpedidos> listQuest = querys.getResultList();
		managers.close();
		return listQuest.size();
		
		 
	}
	
	
	
	
	
	public int pesquisPedidosStatusFechado(int pedidosId){
		EntityManager managers = new JPAUtil().getEntityManager();
		Query querys = managers.createQuery("select  p from CstatusPedidos s inner join s.pedidos p where s.id=1 and p.id="+pedidosId);
		List<Cpedidos> listQuest = querys.getResultList();
		managers.close();
		return listQuest.size();
		
		 
	}
	
	
	
	public List<Cpedidos> pesquisPedidosFechado(){
		EntityManager managers = new JPAUtil().getEntityManager();
		Query querys = managers.createQuery("select  p from CstatusPedidos s inner join s.pedidos p where s.id=1");
		List<Cpedidos> listQuest = querys.getResultList();
		System.out.println("pedidos Fechados>>>>>>"+listQuest);
		managers.close();
		return listQuest;
		
		 
	}

}
