package dao.quota;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TemporalType;

import controle.quota.ConfiguracaoQuota;

import model.quota.QgrupoImpressao;
import model.quota.Qimpressora;
import dao.JPAUtil;
@ManagedBean
@RequestScoped
public class DAOatualizaQuota {
	
	public void atualiza() {
		System.out.println("atualiza>>>>>");
		
		EntityManager managers = new JPAUtil().getEntityManager();
		/*GregorianCalendar calendar = new GregorianCalendar(); 
		calendar.set(GregorianCalendar.HOUR_OF_DAY,0);
		calendar.set(GregorianCalendar.MINUTE,0);
		calendar.set(GregorianCalendar.SECOND,0);
		calendar.set(GregorianCalendar.MILLISECOND,0);
		Date dataNow = calendar.getTime();*/
		Date dataNow =ConfiguracaoQuota.dataNow();
	//	Date datafinal = null;
	//	dataFinal.setDate(grupo.getDataAtualizacao().getDate()+grupo.getValidadeEmDias());
		try {
			
			// managers.getTransaction().begin();
			Query querys = managers.createQuery("select g from QgrupoImpressao g ");
			List<QgrupoImpressao> grupo = querys.getResultList();
		//	System.out.println("executa dao");
			 managers.getTransaction().begin();
			for(QgrupoImpressao g: grupo){
				//String[] auxstr = g.getDataAtualizacao().toString().split("-");
				//int mes = Integer.parseInt(auxstr[1]) - 1;
			
				//Date aux = new Date(Integer.parseInt(auxstr[0])-1900, mes,
				//		Integer.parseInt(auxstr[2]));
				 Date aux = ConfiguracaoQuota.ajustaData(g);
                 //System.out.println("aux.getDate()>>>>"+aux);
				// Date dataFinal = new Date();
				 aux.setDate(aux.getDate()+g.getValidadeEmDias());
				 Date dataFinal = aux;
                  //  System.out.println("data Final:::::"+dataFinal);
			//	datafinal.setDate(g.getDataAtualizacao().getDate()+g.getValidadeEmDias());
				
				if(dataFinal.compareTo(dataNow)<0){
					
					g.setDataAtualizacao(dataNow);
					managers.merge(g);
				}
			}
			
			
			 managers.getTransaction().commit();
			// managers.close();
			//return impressora;
		} catch (RuntimeException e) {
			
			 managers.getTransaction().rollback(); // n�o deveria desfazer o
			// objeto
			// deletado aqui?!?!?!?!?!?
			throw e;
		} finally {
		;
			// fecha a entity manager
			managers.close();
		}
	}
}
