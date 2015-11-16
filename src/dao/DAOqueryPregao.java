package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;


import model.Cpregao;

public class DAOqueryPregao {

	public List<Cpregao> listatodos(){
		EntityManager managers = new JPAUtil().getEntityManager();
		Query querys = managers.createQuery("select p from Cpregao p inner join p.processoList");
		List<Cpregao> listQuest = querys.getResultList();
		//managers.close();
		return listQuest;
		
		 
	}
	
	//verifica se o número de pregão existe
	public List<Cpregao> verificaNumPregao(String num){
		EntityManager managers = new JPAUtil().getEntityManager();
		Query querys = managers.createQuery("select p from Cpregao p inner join p.processoList where p.numero='"+num+"'");
		List<Cpregao> listQuest = querys.getResultList();
		//managers.close();
		return listQuest;
		
		 
	}
	
	//verifica se o processo já foi atribuido
	public List<Cpregao> verificaProcesso(String num){
		EntityManager managers = new JPAUtil().getEntityManager();
		Query querys = managers.createQuery("select p from Cpregao p inner join p.processoList pro where pro.numero='"+num+"'");
		List<Cpregao> listQuest = querys.getResultList();
		//managers.close();
		return listQuest;
		
		 
	}
	
	
	public Cpregao pesquisaPregaoId(int id){
		EntityManager managers = new JPAUtil().getEntityManager();
		Query querys = managers.createQuery("select p from Cpregao p inner join p.processoList where p.id="+id);
		List<Cpregao> listQuest = querys.getResultList();
		//managers.close();
		listQuest.get(0);
		return (Cpregao) listQuest.get(0);
		
		 
	}
	
	
	public boolean VerificaPedido(int id){
		
		EntityManager managers = new JPAUtil().getEntityManager();
		Query querys = managers.createQuery("select p from Cpregao p inner join p.statusPedidos s where s.id=1 and p.id="+id);
		List<Cpregao> listQuest = querys.getResultList();
		//managers.close();
		if(listQuest.isEmpty()){
			return true;
		}else{
			return false;
		}
	
	}
}
