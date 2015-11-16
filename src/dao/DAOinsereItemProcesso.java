package dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import model.Ccotacao;
import model.Citem;
import model.CitemProcesso;
import model.Corcamento;
import model.Cpedidos;
import model.Cprocesso;

public class DAOinsereItemProcesso implements Serializable {

	/**
	 * 
	 */
	
	private List<Citem> listaItem;
	private static final long serialVersionUID = 1L;

	@SuppressWarnings({ "null", "unused" })
	public void addItem(Corcamento[] selectedOrcament, Cprocesso processo) {
		
	//	insereOrcamentoItem(selectedOrcament);
		EntityManager em = new JPAUtil().getEntityManager();
		try{
		em.getTransaction().begin();
		{
			for (Corcamento obj : selectedOrcament) {

			//	if (new DAOqueryOrcamento().verificaOrcamentoItem(selectedOrcament)){
				//	CitemProcesso itemProcesso = new CitemProcesso();
				    obj.setProcesso(processo);
					em.merge(obj);
					processo.addOrcamento(obj);
					// item.setProcesso(processo);
					em.merge(processo);
				
				//}else{
					   
				//}

			}
		}

		em.getTransaction().commit();
		}catch(RuntimeException e){
			em.getTransaction().rollback(); 
	          throw e;    
		}finally{
			
		
		em.close();
		}
	}
	
	
	//adiciona os orcamentos ao itens
	public void insereOrcamentoItem(Corcamento[] selecionados){
	     listaItem = new ArrayList<Citem>();
		for( Corcamento orca : selecionados){
			
			listaItem.add(orca.getItem());
		}
		
		/*for(Citem item: listaItem){
			for(Corcamento orca : selecionados){
				if(item.equals(orca.getItem())){
					System.out.println("nome>>>"+orca.getItem().getMaterial().getNome());
				   //  item.addOrcamento(orca);	
				}
			}
		}*/
	}

	public void removeItem(Corcamento[] selectedOrcamentoList,
			Cprocesso processo) {
		
		EntityManager em = new JPAUtil().getEntityManager();
		try{
		em.getTransaction().begin();

		for (Corcamento obj : selectedOrcamentoList) {
			   obj.setProcesso(null);
			   em.merge(obj);
               processo.removeOrcamento(obj);
               em.merge(processo);
		//	getItemProcesso(obj.getItem(), processo);

		}
		em.getTransaction().commit();
		}catch(RuntimeException e){
			em.getTransaction().rollback(); 
	          throw e;    
		}finally{
			
		
		em.close();
		}
	}

	

	/*public boolean verificaItemProcesso(Citem item, Cprocesso processo) {
		EntityManager managers = new JPAUtil().getEntityManager();
		managers.getTransaction().begin();
		Query querys = managers
				.createQuery("select ip from CitemProcesso ip where ip.item.id="
						+ item.getId() + "and ip.processo=" + processo.getId());
		List<CitemProcesso> listQuest = querys.getResultList();
		managers.close();
		if (listQuest.size() == 0) {
			return true;
		} else {
			return false;
		}

	}*/
	
	

}
