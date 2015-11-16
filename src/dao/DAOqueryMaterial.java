package dao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;


import model.CinfoMaterial;

public class DAOqueryMaterial implements Serializable  {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	public List<CinfoMaterial> pesquisaNome(String nome){
		EntityManager managers = new JPAUtil().getEntityManager();
		//Query querys = managers.createQuery("select m from CinfoMaterial m where m.nome='"+nome+"'");
		Query querys = managers.createQuery("select m from Citem c inner join c.material m where m.nome='"+nome+"'");
		List<CinfoMaterial> listQuest = querys.getResultList();
		managers.close();
		return listQuest;
		
		 
	}
	
	
	public List<CinfoMaterial> pesquisaDescricao(String nome){
		EntityManager managers = new JPAUtil().getEntityManager();
		//Query querys = managers.createQuery("select m from CinfoMaterial m where m.descricao='"+nome+"'");
		Query querys = managers.createQuery("select m from Citem c inner join c.material m where TRIM(m.descricao)='"+nome.trim()+"'");
		List<CinfoMaterial> listQuest = querys.getResultList();
		System.out.println("descrição numero>>>>>"+listQuest.size());
		managers.close();
		return listQuest;
		
		 
	}
	
	
	

}
