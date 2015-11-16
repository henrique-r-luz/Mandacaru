package dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import model.Cfracassados;
import model.Citem;
import model.CitemProcesso;
import model.Corcamento;
import model.Cpedidos;

public class DAOqueryOrcamento implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public List<Corcamento> pesquisOrcamentoProcesso(){
		EntityManager managers = new JPAUtil().getEntityManager();
		Query querys = managers.createQuery("select  o from Corcamento o  join fetch o.pedidos p   join fetch p.statusPedidos s   where  s.id=1 ");
		//Query querys = managers.createQuery("select  o from  Cprocesso cp join fetch cp.itemProcesso ip join fetch ip.item i join fetch i.orcamento o  join fetch o.pedidos p   join fetch p.statusPedidos s   where  s.id=1 ");
		List<Corcamento> listResp = querys.getResultList();
		List<Citem> listResp2 = new ArrayList<Citem>();
			/*for(Corcamento obj : listResp){
				obj.getItem().toString();
				obj.getPedidos().toString();
				obj.getCotacao().toString();
			}*/
		//List<Citem> listQuest = querys.getResultList();
		//managers.flush();
		//managers.close();
		return  listResp;
		
		 
	}
	
	
	
	/*public List<Corcamento> pesquisOrcamentoProcessoFechados(){
		EntityManager managers = new JPAUtil().getEntityManager();
		Query querys = managers.createQuery("select  o from Corcamento o  join fetch o.item i join fetch i.itemProcesso ip join fetch ip.processo p join fetch p.statusPedidos s   where  s.id=1");
		List<Corcamento> listResp = querys.getResultList();
		List<Citem> listResp2 = new ArrayList<Citem>();
		System.out.println("lista orçamento>>>>>>>>>"+listResp);
			
		return  listResp;
		
		 
	}*/
	
	
	public List<Corcamento> pesquisOrcamentoProcessoFechados(String num){
		EntityManager managers = new JPAUtil().getEntityManager();
		Query querys = managers.createQuery("select o from Corcamento o ");
		//List<Object[]> listResp = querys.getResultList();
	     List<Corcamento> listResp2 = querys.getResultList();
		  /*for(Object[] obj: listResp ){
			  Corcamento aux = (Corcamento) obj[0];
			  aux.setNumeroProcesso((String) obj[1]);
			  listResp2.add(aux);
		//	obj.getItem().getItemProcesso().get(0);
			
		  }*/
		//List<Citem> listQuest = querys.getResultList();
		//managers.flush();
		//managers.close();
		return  listResp2;
		
		 
	}
	
	public List<Corcamento> orcamentoPedidos(int id) {
		EntityManager managers = new JPAUtil().getEntityManager();
		Query querys = managers
				.createQuery("select o from Corcamento o join fetch o.pedidos p where p.id="
						+ id);
		List<Corcamento> listQuest = querys.getResultList();

		return listQuest;

	}
	
	
	public List<Corcamento> pesquisOrcamentoProcesso(int id){
		EntityManager managers = new JPAUtil().getEntityManager();
		Query querys = managers.createQuery("select  o from  Corcamento o inner join o.processo p where p.id="+id);
		List<Corcamento> listResp = querys.getResultList();
		
		
		//List<Citem> listQuest = querys.getResultList();
		//managers.flush();
		//managers.close();
		return  listResp;
		
		 
	}
	
	
	
	public List<Corcamento> pesquisOrcamentoProcessoSelect(int id){
		EntityManager managers = new JPAUtil().getEntityManager();
		Query querys = managers.createQuery("select  o from  Corcamento o inner join o.pedidos p inner join p.statusPedidos s  where p.id="+id+" and s.id=1 ");
		List<Corcamento> listResp = querys.getResultList();
		
		
		//List<Citem> listQuest = querys.getResultList();
		//managers.flush();
		//managers.close();
		return  listResp;
		
		 
	}
	
	
	
	public List<Corcamento> pesquisOrcamentoProcessoSelectFechadoTest(int id){
		EntityManager managers = new JPAUtil().getEntityManager();
		Query querys = managers.createQuery("select o from Cprocesso p inner join p.orcamento o where  p.id="+id);
		
		List<Corcamento> listResp = querys.getResultList();
		System.out.println("listResp>>>>"+listResp+" id==="+id);
		/*List<Object[]> listResp2 = querys.getResultList();
		 for(Object[] obj: listResp2 ){
		    Citem itens = (Citem) obj[2];
		    itens.setTransOrcamento((Corcamento) obj[0]);
		    itens.setNumeroPedido((Integer) obj[1]);
		    listResp.add(itens);
		 }*/
		
		//List<Citem> listQuest = querys.getResultList();
		//managers.flush();
		//managers.close();
		return  listResp;
		
		 
	}
	
	/*public List<Citem> pesquisOrcamentoProcessoSelectFechadoTest2(int id){
		EntityManager managers = new JPAUtil().getEntityManager();
		Query querys = managers.createQuery("select DISTINCT   ri  from  (Cprocesso p inner join p.itemProcesso ip inner join ip.item i) ri where p.id="+id+"  ");
		List<Citem> listResp = new ArrayList<Citem>();
		//List<Citem> listResp =  querys.getResultList();
		System.out.println("resposta teste>>>>>"+listResp.size());
		List<Object[]> listResp2 = querys.getResultList();
		 for(Object[] obj: listResp2 ){
		    Citem itens = (Citem) obj[2];
		  //  itens.setTransOrcamento((Corcamento) obj[0]);
		    itens.setNumeroPedido((Integer) obj[1]);
		    listResp.add(itens);
		 }
		
		//List<Citem> listQuest = querys.getResultList();
		//managers.flush();
		//managers.close();
		return  listResp;
		
		 
	}*/
	
	
	
	

	
	
	public List<Corcamento> ListaOrcamentoFracassados(){
		EntityManager managers = new JPAUtil().getEntityManager();
		Query querys = managers.createQuery("select DISTINCT o, f from Cprocesso pro inner join pro.statusPedidos st inner join st.pedidos cp inner join   cp.orcamento o inner join o.item i inner join i.fracassados f where cp.statusPedidos.id=1 and pro.statusPedidos.id=1");
		List<Corcamento> listResp2 = new ArrayList<Corcamento>();
		List<Object[]> listResp = querys.getResultList();
		 for(Object[] obj: listResp ){
			 Corcamento orca = (Corcamento) obj[0];
			// orca.getItem().getItemProcesso();
			 orca.setFracassados((Cfracassados) obj[1]);
			 listResp2.add(orca); 
		
		 }
		
		//List<Citem> listQuest = querys.getResultList();
		//managers.flush();
		//managers.close();
		return  listResp2;
		
		 
	}
	
	
	
public boolean verificaOrcamentoItem(Corcamento[] list) {
		
		boolean bool = true;
		/*for(Corcamento item : list){
			if(item.getOrcamento().size()>1){
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,"O item("+item.getMaterial().getNome()+") está presente em dois pedidos diferentes. É necessário corrigir essa pendência", null));	
				 bool = false;
			}
		}*/
		//managers.close();
		//System.out.println("size>>>>>>"+listQuest);
		
		return bool;
	}
	

}
//select  o, p.numero from model.Cpedidos cp inner join cp.orcamento  o inner join  o.item i inner join  i.itemProcesso ip inner join  ip.processo p inner join p.statusPedidos status   where  p.numero='1312155445645456456454' and status.id=cp.statusPedidos.id=1
