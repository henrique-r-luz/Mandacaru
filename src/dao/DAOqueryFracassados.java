package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;


import model.Cfracassados;
import model.Cgrupo;

public class DAOqueryFracassados {
	
	public List<Cfracassados> pesquisaTitulo(String titutlo){
		EntityManager managers = new JPAUtil().getEntityManager();
		Query querys = managers.createQuery("select f from Cfracassados f where f.titulo='"+titutlo+"'");
		List<Cfracassados> listQuest = querys.getResultList();
		managers.close();
		return listQuest;
		
		 
	}
	
	
	
	public Cfracassados getObjTitulo(String titutlo){
		EntityManager managers = new JPAUtil().getEntityManager();
		Query querys = managers.createQuery("select f from Cfracassados f where f.titulo='"+titutlo+"'");
		List<Cfracassados> listQuest = querys.getResultList();
		if(listQuest.isEmpty()){
			managers.close();
			return null;
		}else{
			Cfracassados fracasso = listQuest.get(0);
			managers.close();
			return fracasso;
		}
		//
		//return listQuest;
		
		 
	}
	
	
	
	public List<Cfracassados> pesquisaJustificativa(String justificativa){
		EntityManager managers = new JPAUtil().getEntityManager();
		Query querys = managers.createQuery("select f from Cfracassados f where f.justificativa='"+justificativa+"'");
		List<Cfracassados> listQuest = querys.getResultList();
		managers.close();
		return listQuest;
		
		 
	}
	
	
	
	public List<Cfracassados> pesquisaNome(String nome){
		EntityManager managers = new JPAUtil().getEntityManager();
		Query querys = managers.createQuery("select c from Cfracassados c where c.titulo='"+nome+"'");
		List<Cfracassados> listQuest = querys.getResultList();
		managers.close();
		return listQuest;
		
		 
	}
	

}
