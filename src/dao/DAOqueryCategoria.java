package dao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import model.Ccategoria;

public class DAOqueryCategoria implements Serializable  {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	public List<Ccategoria> pesquisaNome(String numero){
		EntityManager managers = new JPAUtil().getEntityManager();
		Query querys = managers.createQuery("select c from Ccategoria c where c.nome='"+numero+"'");
		List<Ccategoria> listQuest = querys.getResultList();
		managers.close();
		return listQuest;
		
		 
	}
	
	
	public Ccategoria pesquisaUnicoNome(String numero){
		EntityManager managers = new JPAUtil().getEntityManager();
		Query querys = managers.createQuery("select c from Ccategoria c where c.nome='"+numero+"'");
		List<Ccategoria> listQuest = querys.getResultList();
		Ccategoria categoria  = (Ccategoria) listQuest.get(0);
		managers.close();
		return categoria;
		
		 
	}

}
