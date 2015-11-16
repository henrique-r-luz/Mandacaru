package dao;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TemporalType;

import model.Corcamento;
import model.impressao.IcotacaoCopias;

public class DAOqueryCotacaocopia {

	@SuppressWarnings("unchecked")
	public List<IcotacaoCopias> pesquisaCotacaoCopias(BigDecimal valor,
			Date dataInicial, Date dataFinal) {
		EntityManager managers = new JPAUtil().getEntityManager();
		try {
			List<IcotacaoCopias> listQuest = new ArrayList<IcotacaoCopias>();
			
				 listQuest = managers
						.createQuery(
								"select c from IcotacaoCopias c  where (c.dataFinal >=:dataInicial  AND c.dataInicial <=:dataFinal )")
						.setParameter("dataInicial", dataInicial, TemporalType.DATE)
						.setParameter("dataFinal", dataFinal, TemporalType.DATE)
						.getResultList();
				
			
			// List<IcotacaoCopias> listQuest = querys.getResultList();
			return listQuest;
		} catch (RuntimeException e) {
			throw e;
		} finally {
			managers.close();
		}
	}
	
	

	public boolean verificaDados(BigDecimal valor, Date dataInicial,
			Date dataFinal) {
		EntityManager managers = new JPAUtil().getEntityManager();
		try {

			Query querys2 = managers.createQuery("from IcotacaoCopias  ");
			List<IcotacaoCopias> listQuest2 = querys2.getResultList();
			if (listQuest2.size() != 0) {
				@SuppressWarnings("unchecked")
				List<IcotacaoCopias> listQuest = managers
						.createQuery(
								"select c from IcotacaoCopias c  where (c.dataFinal >=:dataInicial  AND c.dataInicial <=:dataFinal )")
						.setParameter("dataInicial", dataInicial,
								TemporalType.DATE)
						.setParameter("dataFinal", dataFinal, TemporalType.DATE)
						.getResultList();

				if (listQuest.size() == 0) {
					return false;
				} else {
					return true;
				}
			} else {
				return false;
			}
		} catch (RuntimeException e) {
			throw e;
		} finally {
			managers.close();
		}
	}
	
	
	
	@SuppressWarnings("unchecked")
	public boolean VerificaAltera(int id) {
		EntityManager managers = new JPAUtil().getEntityManager();
		try {
			List<IcotacaoCopias> listQuest = new ArrayList<IcotacaoCopias>();

			listQuest = managers.createQuery(
					"select c from IcotacaoCopias c inner join c.copias cp where c.id="
							+ id).getResultList();

			if (listQuest.size() == 0) {
				
				return true;
			} else {
				
				return false;
			}
			// List<IcotacaoCopias> listQuest = querys.getResultList();
			// return listQuest;
		} catch (RuntimeException e) {
			managers.getTransaction().rollback();
			throw e;
			
		} finally {
			managers.close();
		}
	}

}
