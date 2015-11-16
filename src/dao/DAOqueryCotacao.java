package dao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import model.Ccotacao;



public class DAOqueryCotacao implements Serializable  {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public List<Ccotacao> pesquisaNome(int idOrcamento){
		EntityManager managers = new JPAUtil().getEntityManager();
		Query querys = managers.createQuery("select c from Ccotacao c where c.orcamento.id="+idOrcamento);
		List<Ccotacao> listQuest = querys.getResultList();
		managers.close();
		return listQuest;
		
		 
	}
	
	
	public int contaCotacao(int idOrcamento){
		EntityManager managers = new JPAUtil().getEntityManager();
		Query querys = managers.createQuery("select c from Ccotacao c where c.orcamento.id="+idOrcamento);
		List<Ccotacao> listQuest = querys.getResultList();
		managers.close();
		return listQuest.size();
		
		 
	}
	
	public boolean verificaImg(String url){
		
		EntityManager managers = new JPAUtil().getEntityManager();
		Query querys = managers.createQuery("select c from Ccotacao c where c.url='"+url+"'");
		List<Ccotacao> listQuest = querys.getResultList();
		managers.close();
		 if(listQuest.size()==0){
			 return true;
		 }else{
			 return false;
		 }
		
	}

}
