package dao.quota;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TemporalType;

import controle.quota.ConfiguracaoQuota;

import model.Cusuario;
import model.quota.QcopiasRealizadas;
import model.quota.QgrupoImpressao;
import model.quota.Qimpressora;
import dao.JPAUtil;

public class RelatorioCopias implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public int verifCopias(String usuario, QgrupoImpressao grupo) {

		Date dataFinal = ConfiguracaoQuota.ajustaData(grupo);

		dataFinal.setDate(dataFinal.getDate() + grupo.getValidadeEmDias());
		


		EntityManager managers = new JPAUtil().getEntityManager();
		/*
		 * .createQuery(
		 * "select SUM(c.numeroCopias) from  Cusuario u, QcopiasRealizadas c " +
		 * "inner join c.servidor s " + "inner join s.grupoImpressao g " +
		 * "where u.servidor.id=s.id " + "and u.login='" + usuario +
		 * "' AND c.id NOT IN (select c.id from QcopiasRealizadas c inner join c.statusCopias st) "
		 * 
		 * + "AND c.data BETWEEN :dataInicial AND :dataFinal")
		 */
		try {

			@SuppressWarnings("unchecked")
			Object totalCopias = managers.createQuery(
							  "select SUM(c.numeroCopias) from  Cusuario u, QcopiasRealizadas c " +
							  "inner join c.servidor s " + "inner join s.grupoImpressao g " +
							  "where u.servidor.id=s.id " + "and u.login='" + usuario +
							  "' AND c.id NOT IN (select c.id from QcopiasRealizadas c inner join c.statusCopias st) "
							  
							  + "AND c.data BETWEEN :dataInicial AND :dataFinal")
					.setParameter("dataInicial", grupo.getDataAtualizacao(),TemporalType.DATE)
					.setParameter("dataFinal", dataFinal, TemporalType.DATE)
					.getSingleResult();

			// List<QcopiasRealizadas> listQuest = querys.getResultList();
			//System.out.println("total222222>>>>>>" + totalCopias);
			if (totalCopias == null) {
				return 0;
			} else {
				return Integer.parseInt(totalCopias.toString());
			}
			// Qimpressora impressora = listQuest.get(0);

		} catch (RuntimeException e) {

			throw e;
		} finally {

			managers.close();
		}
	}

}
