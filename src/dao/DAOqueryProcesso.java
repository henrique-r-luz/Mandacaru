package dao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import model.Cprocesso;

public class DAOqueryProcesso implements Serializable  {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	public List<Cprocesso> pesquisaNome(String numero){
		EntityManager managers = new JPAUtil().getEntityManager();
		Query querys = managers.createQuery("select DISTINCT c from Cprocesso c where c.numero='"+numero+"'");
		List<Cprocesso> listQuest = querys.getResultList();
		managers.close();
		return listQuest;
		
		 
	}
	
	
	
	public List<Cprocesso> listaProcessofechado(){
		EntityManager managers = new JPAUtil().getEntityManager();
		Query querys = managers.createQuery("select DISTINCT c from Cprocesso c inner join c.statusPedidos s where s.id="+1);
		List<Cprocesso> listQuest = querys.getResultList();
		managers.close();
		return listQuest;
		
		 
	}
	
	
	
	public Cprocesso processos(int id){
		
		EntityManager managers = new JPAUtil().getEntityManager();
		Query querys = managers.createQuery("select  c from Cprocesso c where c.id="+id);
		List<Cprocesso> listQuest = querys.getResultList();
		System.out.println("valor>>>>"+listQuest.size());
		Cprocesso processo = listQuest.get(0);
		
		return processo;
		
		 
	}
	
	
public boolean verificaProcesso(int id){
		
		EntityManager managers = new JPAUtil().getEntityManager();
		Query querys = managers.createQuery("select DISTINCT c from Cprocesso c inner join c.statusPedidos s  where s.id="+1+" and c.id="+id);
		List<Cprocesso> listQuest = querys.getResultList();
		//managers.close();
		if(listQuest.isEmpty()){
			return true;
		}else{
			return false;
		}
		
		
		 
	}
	
	
public Cprocesso processosItem(int id){
		
		EntityManager managers = new JPAUtil().getEntityManager();
		Query querys = managers.createQuery("select p from Cprocesso p inner join p.item i  where p.id="+id);
		List<Cprocesso> listQuest = querys.getResultList();
		Cprocesso processo =(Cprocesso) listQuest.get(0);
		managers.close();
		return processo;
		
		 
	}
	
	
	
	
	


}
