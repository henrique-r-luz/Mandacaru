package dao;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TemporalType;

import model.CinfoMaterial;
import model.impressao.IdocCopias;

public class DAOqueryDocCopias {

	public List<IdocCopias> listaTodos(String nomeServidor, Date dataInicial,
			Date dataFinal) {
		EntityManager managers = new JPAUtil().getEntityManager();
		try {
			Date dataInicialaux; 
		    Date dataFinalaux;
			if(dataInicial==null && dataFinal==null){
			   dataInicialaux = new Date(00,0,1);
				dataFinalaux = new Date(4100,0,1);
			}
			else{
				 dataInicialaux = dataInicial;
			     dataFinalaux = dataFinal;
			}
			@SuppressWarnings("unchecked")
			List<IdocCopias> lista = managers
					.createQuery(
							"SELECT c FROM IcotacaoCopias ic inner join ic.copias c inner join c.servidor s WHERE s.nome LIKE '%"+nomeServidor+"%' AND c.data BETWEEN :dataInicial AND :dataFinal ")
					.setParameter("dataInicial", dataInicialaux, TemporalType.DATE)
					.setParameter("dataFinal", dataFinalaux, TemporalType.DATE)
					.getResultList();
			return lista;

			// return listQuest;
		} catch (RuntimeException e) {
			// managers.getTransaction().rollback(); // não deveria desfazer o
			// objeto
			// deletado aqui?!?!?!?!?!?
			throw e;
		} finally {
			// fecha a entity manager
			managers.close();
		}

	}

	public boolean verificaIdGlpi(int id) {
		EntityManager managers = new JPAUtil().getEntityManager();
		try {
			// Query querys =
			// managers.createQuery("select m from CinfoMaterial m where m.nome='"+nome+"'");
			Query querys = managers
					.createQuery("select c from IdocCopias c inner join c.servidor s where c.idGlpi="
							+ id);
			List<IdocCopias> listQuest = querys.getResultList();
			if (listQuest.isEmpty()) {
				return true;
			} else {
				return false;
			}
		} catch (RuntimeException e) {
			// managers.getTransaction().rollback(); // não deveria desfazer o
			// objeto
			// deletado aqui?!?!?!?!?!?
			throw e;
		} finally {
			// fecha a entity manager
			 managers.close();
		}
		// return listQuest;

	}
	
	
	


}
