package dao;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import model.Citem;
import model.Corcamento;
import model.Cpedidos;
import model.Cprocesso;
import model.Cstatus;
import model.CstatusItem;

public class DAOqueryItem implements Serializable  {
	
	
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;




	public List<Citem> pesquisCatmatItem(String numero){
		EntityManager managers = new JPAUtil().getEntityManager();
		Query querys = managers.createQuery("select i from Citem i where i.catmat.numero="+numero+"and i.catmat.tipocatmat.sigla!='G'");
		List<Citem> listQuest = querys.getResultList();
		managers.close();
		return  listQuest;
		
		 
	}
	
	
	public List<Cstatus> pesquisItemStatus(int id){
		EntityManager managers = new JPAUtil().getEntityManager();
		Query querys = managers.createQuery("select s from Citem i inner join i.status s  where i.id="+id);
		List<Cstatus> listQuest = querys.getResultList();
		managers.close();
		return  listQuest;
		
		 
	}
	
	
	public List<Citem> pesquisItemDiretoria(){
		EntityManager managers = new JPAUtil().getEntityManager();
		//Query querys = managers.createQuery("select i from Citem i inner join i.orcamento o inner join o.pedidos p inner join o.statusPedidos s.id=1 ");
		Query querys = managers.createQuery("select i from Citem i inner join i.statusItem si inner join si.status s where si.tempo  in (select max(si.tempo)  from Citem i inner join i.statusItem si inner join si.status s  GROUP BY i) and s.nome=2");
		//Query querys2 = managers.createQuery("select max(si.tempo)  from Citem i inner join i.statusItem si inner join si.status s  GROUP BY i");
	//	List<Citem> listQuest2 = querys2.getResultList();	
		//Citem  item = listQuest2.get(0);
	//	List<CstatusItem> statusItem = item.getStatusItem();
		//System.out.println("result>>>>>"+statusItem.get(0).getTempo());
		/*List<Long> queryRes = querys2.getResultList();
		for (Long res : queryRes) { 
			// Citem item  = (Citem) res[0];
			 long temp =  (Long) res;
		
			// System.out.print("id>>> "+item.getId());
			 System.out.print("status>>> "+temp);
		
			
		 
		}*/
		List<Citem> listQuest = querys.getResultList();
		managers.close();
		return  listQuest;
		
	}
	
	
	
	
	public List<Citem> pesquisItemAddProcesso(int id){
		EntityManager managers = new JPAUtil().getEntityManager();
		Query querys = managers.createQuery("select o from Corcamento o inner join o.item  i inner join i.itemProcesso ip inner join  ip.processo p where p.id="+id);
		List<Corcamento> listQuest = querys.getResultList();
		List<Citem> listResp =new ArrayList<Citem>();
		for(Corcamento obj : listQuest){
			Citem auxItem = obj.getItem();
			auxItem.getTransOrcamento().setQuantidade(obj.getQuantidade());
			listResp.add(auxItem);
		}
		managers.close();
		return  listResp;
		
		 
	}
	
	//usar função aagregada para cotar o numero de item repitidos
	/*public boolean verificaOrcamentoItem(List<Citem> list) {
		
		boolean bool = true;
		for(Citem item : list){
			if(item.getOrcamento().size()>1){
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,"O item("+item.getMaterial().getNome()+") está presente em dois pedidos diferentes. É necessário corrigir essa pendência", null));	
				 bool = false;
			}
		}
		//managers.close();
		//System.out.println("size>>>>>>"+listQuest);
		
		return bool;
	}*/
	
	
	
public boolean verificaFracassoItem(List<Citem> list) {
		
		boolean bool = true;
		for(Citem item : list){
			if(item.getFracassados().size()>1){
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,"O item("+item+") já foi adicionado como fracassado", null));	
				 bool = false;
			}
		}
		//managers.close();
		//System.out.println("size>>>>>>"+listQuest);
		
		return bool;
	}
	
	

	

}
