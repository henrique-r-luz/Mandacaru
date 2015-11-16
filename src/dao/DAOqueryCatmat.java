package dao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import model.Ccatmat;
import model.CinfoMaterialGenerico;

public class DAOqueryCatmat implements Serializable  {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	public List<Ccatmat> pesquisaNumero(String numero){
		EntityManager managers = new JPAUtil().getEntityManager();
		Query querys = managers.createQuery("select c from Ccatmat c where c.numero="+numero);
		List<Ccatmat> listQuest = querys.getResultList();
		managers.close();
		return listQuest;
		
		 
	}
	
	
	public Ccatmat pesquisaUnicoNumero(String numero){
		EntityManager managers = new JPAUtil().getEntityManager();
		Query querys = managers.createQuery("select c from Ccatmat c where c.numero="+numero);
		List<Ccatmat> listQuest = querys.getResultList();
		Ccatmat catmat = listQuest.get(0);
		managers.close();
		return catmat;
		
		 
	}
	

	public String tipoCatmat(int id){
		EntityManager managers = new JPAUtil().getEntityManager();
		Query querys = managers.createQuery("select c from Ccatmat c inner join c.tipocatmat tipo  where c.id="+id+" and tipo.sigla='false'");
		List<Ccatmat> listQuest = querys.getResultList();
		if(listQuest.isEmpty()){
			return "true";
		}else{
			return "false";
		}
		
	
		
		 
	}
	
	
	public List<CinfoMaterialGenerico> listatodosCtamatGenricos(int id){
		EntityManager managers = new JPAUtil().getEntityManager();
		Query querys = managers.createQuery("select m from Ccatmat c inner join c.materialGenerico m where c.id="+id);
		List<CinfoMaterialGenerico> listQuest = querys.getResultList();
	
	//	managers.close();
		return listQuest;
		
		 
	}
	
	

}
