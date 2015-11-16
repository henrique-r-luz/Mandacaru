package dao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;


import model.Cusuario;
import model.impressao.Iservidor;

public class DAOqueryUsuario implements Serializable  {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public List<Cusuario> pesquisaNome(String nome){
		EntityManager managers = new JPAUtil().getEntityManager();
		try{
		Query querys = managers.createQuery("select c from Cusuario c where c.login='"+nome+"'");
		List<Cusuario> listQuest = querys.getResultList();
		//managers.close();
		return listQuest;
		}catch (RuntimeException e) {
			//em.getTransaction().rollback();
			throw e;
		}finally {
			managers.close();
		}
		
		 
	}
	

	public Cusuario getUsuario(String nome){
		EntityManager managers = new JPAUtil().getEntityManager();
		try{
		Query querys = managers.createQuery("select c from Cusuario c where c.login='"+nome+"'");
		Cusuario listQuest = (Cusuario) querys.getSingleResult();
		//managers.close();
		return listQuest;
		}catch (RuntimeException e) {
			//em.getTransaction().rollback();
			throw e;
		}finally {
			managers.close();
		}
		
		 
	}
	
	
	
	
	
	
	public List<Cusuario> listaTodos(String login, String nome){
		EntityManager managers = new JPAUtil().getEntityManager();
		try{
		Query querys = managers.createQuery("select c from Cusuario c where c.login like '%"+login+"%' and c.servidor.nome like '"+nome+"%'");
		List<Cusuario> listQuest = querys.getResultList();
		return listQuest;
		}catch (RuntimeException e) {
			//em.getTransaction().rollback();
			throw e;
		}finally {
			managers.close();
		}
		
		
		 
	}
	
	public boolean AltenticaUsruario(String login, String senha){
		EntityManager managers = new JPAUtil().getEntityManager();
		try{
		Query querys = managers.createQuery("select c from Cusuario c where c.login ='"+login+"' and c.senha ='"+senha+"'");
		List<Cusuario> listQuest = querys.getResultList();
		if(listQuest.isEmpty()){
			return false;
		}else{
			return true;
		}
		}catch (RuntimeException e) {
			//em.getTransaction().rollback();
			throw e;
		}finally {
			managers.close();
		} 
	}
	
	

}
