package dao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import model.Ctipo;
import model.CtipoCatMat;

public class DAOqueryTipoCatMat implements Serializable  {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CtipoCatMat pesquisaNomeUnicoNome(String nome){
		EntityManager managers = new JPAUtil().getEntityManager();
		Query querys = managers.createQuery("select t from CtipoCatMat t where t.nome='"+nome+"'");
		List<CtipoCatMat> listQuest = querys.getResultList();
		CtipoCatMat tipocatmat = listQuest.get(0);
		managers.close();
		return tipocatmat;
		
		 
	}
	
	
	public List<CtipoCatMat> pesquisaTipoCat(){
		EntityManager managers = new JPAUtil().getEntityManager();
		Query querys = managers.createQuery("select t from CtipoCatMat t");
		List<CtipoCatMat> listQuest = querys.getResultList();
	//	CtipoCatMat tipocatmat = listQuest.get(0);
		managers.close();
		return listQuest;
		
		 
	}

}
