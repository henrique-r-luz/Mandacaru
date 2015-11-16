package dao.quota;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TemporalType;
import javax.persistence.criteria.CriteriaQuery;

import controle.UsuarioControl;
import model.impressao.Iservidor;
import model.quota.QcopiasRealizadas;
import model.quota.Qimpressora;
import dao.JPAUtil;

public class DAOqueryCopias {

	private String linhaServidor = " ";
	private String linhaImpressora = " ";
	private String linhaDocNome = "";
	
	
	

	
	// adequar fun��o
	public List<QcopiasRealizadas> copiasUsuario() {
		EntityManager em = new JPAUtil().getEntityManager();
		try {
			Query querys = em
					.createQuery("select c from QcopiasRealizadas  c inner join c.servidor s inner join s.usuario u where u.login ='"
							+ new UsuarioControl().getSecao()
							+ "' order by c.data DESC ");
			List<QcopiasRealizadas> lista = querys.getResultList();

			// em.close();
			return lista;
		} catch (RuntimeException e) {
			// em.getTransaction().rollback();
			throw e;
		} finally {
			// fecha a entity manager
			em.close();
		}
	}

	public long somaCopiasUsuario() {
		long soma = 0;
		EntityManager em = new JPAUtil().getEntityManager();
		try {
			Query querys = em
					.createQuery("select SUM(c.numeroCopias) from QcopiasRealizadas  c inner join c.servidor s inner join s.usuario u where u.login ='"
							+ new UsuarioControl().getSecao() + "'");
			if (querys.getSingleResult() != null) {
				soma = (long) querys.getSingleResult();
			}

			// em.close();
			return soma;
		} catch (RuntimeException e) {
			// em.getTransaction().rollback();
			throw e;
		} finally {
			// fecha a entity manager
			em.close();
		}
	}

	public long somaCopias() {
		long soma = 0;
		EntityManager em = new JPAUtil().getEntityManager();
		try {
			Query querys = em
					.createQuery("select SUM(c.numeroCopias) from QcopiasRealizadas  c ");
			if (querys.getSingleResult() != null) {
				soma = (long) querys.getSingleResult();
			}

			// em.close();
			return soma;
		} catch (RuntimeException e) {
			// em.getTransaction().rollback();
			throw e;
		} finally {
			// fecha a entity manager
			em.close();
		}
	}

	public List<QcopiasRealizadas> todos() {
		EntityManager em = new JPAUtil().getEntityManager();
		try {
			Query querys = em
					.createQuery("select c from QcopiasRealizadas  c order by c.data DESC ");
			List<QcopiasRealizadas> lista = querys.getResultList();

			// em.close();
			return lista;
		} catch (RuntimeException e) {
			// em.getTransaction().rollback();
			throw e;
		} finally {
			// fecha a entity manager
			em.close();
		}
	}

	@SuppressWarnings("finally")
	public String paramentrosFiltro(String initQuery, Iservidor servidor,
			Qimpressora impressora, Date dataInicial, Date dataFinal,
			String nomeDoc) {
		String joinLogin = "";
		String joinImpressora = "";
		String datavalor = "";
		linhaServidor = " ";
		linhaImpressora = " ";
		linhaDocNome = " AND c.nomeDocumento like '%" + nomeDoc + "%'";
		

		if (servidor == null) {
			linhaServidor = " ";

		} else {
			linhaServidor = " AND s.nome like '" + servidor.getNome() + "'";
		}

		if (impressora == null) {
			
			linhaImpressora = " ";

		} else {
			linhaImpressora = " AND i.nome='" + impressora.getNome() + "'";
		}
		
		if(dataInicial !=null && dataFinal!=null){
			datavalor = "c.data BETWEEN :start AND :end AND ";
		}else{
			datavalor = "";
		}
		


		// select SUM(c.numeroCopias)

		return initQuery + " from Qimpressora i inner join i.copias c "
				+ "inner join c.servidor s "
				+ joinLogin + " WHERE "
				+ datavalor+
				 " c.id NOT IN (select c.id from QcopiasRealizadas c inner join c.statusCopias st) " +

				linhaServidor + linhaImpressora + linhaDocNome 
				+ " order by c.data DESC";

	}

	public List<QcopiasRealizadas> filtroRelatorio(Iservidor servidor,
			Qimpressora impressora, Date dataInicial, Date dataFinal,
			String nomeDoc) {

		EntityManager em = new JPAUtil().getEntityManager();
		try {

			Query query =  em.createQuery(paramentrosFiltro("select c", servidor, impressora,dataInicial, dataFinal, nomeDoc));
				if(dataInicial !=null && dataFinal!=null){					
					query.setParameter("start", dataInicial, TemporalType.DATE);
					query.setParameter("end", dataFinal, TemporalType.DATE);
				}
				@SuppressWarnings("unchecked")
				List<QcopiasRealizadas> lista =	 query.getResultList();

			// em.close();
			return lista;
		} catch (RuntimeException e) {
			// em.getTransaction().rollback();
			throw e;
		} finally {
			// fecha a entity manager
			em.close();
		}

	}

	private void createQuery(String paramentrosFiltro) {
		// TODO Auto-generated method stub
		
	}

	public List<QcopiasRealizadas> filtroRelatorioAbatido(Iservidor servidor,
			Qimpressora impressora, Date dataInicial, Date dataFinal,
			String nomeDoc) {

		EntityManager em = new JPAUtil().getEntityManager();
		try {

			@SuppressWarnings("unchecked")
			List<QcopiasRealizadas> lista = em
					.createQuery(
							"select c  from QstatusCopias st, Qimpressora i inner join i.copias c "
									+ "inner join c.servidor s " + "WHERE "
									+ "c.data BETWEEN :start AND :end "
									+ " AND st.copias.id=c.id" +
									" order by c.data DESC")
					.setParameter("start", dataInicial, TemporalType.DATE)
					.setParameter("end", dataFinal, TemporalType.DATE)
					.getResultList();

			// em.close();
			if (lista == null) {
				lista = new ArrayList<QcopiasRealizadas>();
				return lista;
			} else {

				return lista;
			}
		} catch (RuntimeException e) {
			// em.getTransaction().rollback();
			throw e;
		} finally {
			// fecha a entity manager
			em.close();
		}

	}
	
	
	
	
	public long contaCopiasAbatida(Iservidor servidor, Qimpressora impressora,
			Date dataInicial, Date dataFinal, String nomeDoc) {

		EntityManager em = new JPAUtil().getEntityManager();
		try {

			Object obj = em
					.createQuery("select SUM(c.numeroCopias) from QstatusCopias st, Qimpressora i inner join i.copias c "
				+ "inner join c.servidor s " + "WHERE "
				+ "c.data BETWEEN :start AND :end " +
				 " AND st.copias.id=c.id " +

				 " order by c.data DESC")
					.setParameter("start", dataInicial, TemporalType.DATE)
					.setParameter("end", dataFinal, TemporalType.DATE)
					.getSingleResult();

			long soma = 0;
			if (obj == null) {

				return soma;
			} else {
				soma = (long) obj;

				return soma;
			}
			// if(soma==null){

			// }else{

			// }
			// em.close();

		} catch (RuntimeException e) {
			// em.getTransaction().rollback();
			throw e;
		} finally {
			// fecha a entity manager
			em.close();
		}

	}


	public long contaCopias(Iservidor servidor, Qimpressora impressora,
			Date dataInicial, Date dataFinal, String nomeDoc) {

		EntityManager em = new JPAUtil().getEntityManager();
		try {

			Query query = em
					.createQuery(
							paramentrosFiltro("select SUM(c.numeroCopias)",
									servidor, impressora, dataInicial,
									dataFinal, nomeDoc));
			        if(dataInicial !=null && dataFinal!=null){
					query.setParameter("start", dataInicial, TemporalType.DATE);
					query.setParameter("end", dataFinal, TemporalType.DATE);
			        }
					Object obj = query.getSingleResult();

			long soma = 0;
			if (obj == null) {

				return soma;
			} else {
				soma = (long) obj;

				return soma;
			}
			// if(soma==null){

			// }else{

			// }
			// em.close();

		} catch (RuntimeException e) {
			// em.getTransaction().rollback();
			throw e;
		} finally {
			// fecha a entity manager
			em.close();
		}

	}

}
