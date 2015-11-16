package dao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import model.Cgrupo;
import model.Cstatus;

public class DAOqueryStatus implements Serializable  {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Cstatus pesquisaNome(int valorStatus){
		EntityManager managers = new JPAUtil().getEntityManager();
		Query querys = managers.createQuery("select c from Cstatus c where c.nome="+valorStatus);
		List<Cstatus> listQuest = querys.getResultList();
		managers.close();
		if(listQuest.size()==0){
			return null;
		}else{
		return listQuest.get(0);
		}
		
		 
	}
}
