package dao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import model.Ctipo;

public class DAOqueryTipo implements Serializable  {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	public List<Ctipo> pesquisaNome(String nome){
		EntityManager managers = new JPAUtil().getEntityManager();
		Query querys = managers.createQuery("select t from Ctipo t where t.nome='"+nome+"'");
		List<Ctipo> listQuest = querys.getResultList();
		managers.close();
		return listQuest;
		
		 
	}
	
	
	public Ctipo pesquisaUnicoNome(String nome){
		EntityManager managers = new JPAUtil().getEntityManager();
		Query querys = managers.createQuery("select t from Ctipo t where t.nome='"+nome+"'");
		List<Ctipo> listQuest = querys.getResultList();
		Ctipo tipo  = listQuest.get(0);
		managers.close();
		return tipo;
		
		 
	}


}
