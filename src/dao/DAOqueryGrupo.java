package dao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;


import model.Cgrupo;

public class DAOqueryGrupo implements Serializable  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public List<Cgrupo> pesquisaNome(String nome){
		EntityManager managers = new JPAUtil().getEntityManager();
		Query querys = managers.createQuery("select c from Cgrupo c where c.nome='"+nome+"'");
		List<Cgrupo> listQuest = querys.getResultList();
		managers.close();
		return listQuest;
		
		 
	}
	
	
	
	
	
	
}
