package dao.quota;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;

import model.Ccatmat;
import model.Cusuario;
import model.impressao.Iservidor;
import model.quota.QgrupoImpressao;
import model.quota.Qimpressora;

import dao.JPAUtil;

public class DAOqueryGrupoImpressao implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public List<Iservidor> retornaServidoresSemGrupo() {

		EntityManager em = new JPAUtil().getEntityManager();
		try {
			Query querys = em.createQuery("select s from Iservidor s where s.grupoImpressao=null");
			List<Iservidor> lista = querys.getResultList();
			return lista;

			// em.close();
		
		} catch (RuntimeException e) {
			// em.getTransaction().rollback();
			throw e;
		} finally {
			// fecha a entity manager
			em.close();
		}
	}
	
	
	
	public List<Iservidor> retornaServidoresGrupo(int id) {

		EntityManager em = new JPAUtil().getEntityManager();
		try {
			Query querys = em.createQuery("select s from Iservidor s inner join s.grupoImpressao g where g.id="+id);
			List<Iservidor> lista = querys.getResultList();
			return lista;

			// em.close();
		
		} catch (RuntimeException e) {
			// em.getTransaction().rollback();
			throw e;
		} finally {
			// fecha a entity manager
			em.close();
		}
	}
	
	
	public List<Qimpressora> retornaImpressoraGrupo(int id) {

		EntityManager em = new JPAUtil().getEntityManager();
		try {
			Query querys = em.createQuery("select i from Qimpressora i inner join i.grupo g where g.id="+id);
			List<Qimpressora> lista = querys.getResultList();
			return lista;

			// em.close();
		
		} catch (RuntimeException e) {
			// em.getTransaction().rollback();
			throw e;
		} finally {
			// fecha a entity manager
			em.close();
		}
	}
	
	
	public List<Qimpressora> retornaImpressoraSemGrupo() {

		EntityManager em = new JPAUtil().getEntityManager();
		try {
			Query querys = em.createQuery("select i from Qimpressora i ");
			List<Qimpressora> lista = querys.getResultList();
			return lista;

			// em.close();
		
		} catch (RuntimeException e) {
			// em.getTransaction().rollback();
			throw e;
		} finally {
			// fecha a entity manager
			em.close();
		}
	}
	
	
	
	
	public QgrupoImpressao grupoId(EntityManager em, int id) {

		///EntityManager em = new JPAUtil().getEntityManager();
		try {
			Query querys = em.createQuery("select g from QgrupoImpressao g where g.id="+id);
		    QgrupoImpressao lista = (QgrupoImpressao) querys.getSingleResult();
			return lista;

			// em.close();
		
		} catch (RuntimeException e) {
			// em.getTransaction().rollback();
			throw e;
		} finally {
			// fecha a entity manager
			//em.close();
		}
	}
	
	
	public List<QgrupoImpressao> ListaTodos() {

		EntityManager em = new JPAUtil().getEntityManager();
		try {
			Query querys = em.createQuery("select g from QgrupoImpressao g ");
			List<QgrupoImpressao> lista = querys.getResultList();
			return lista;

			// em.close();
		
		} catch (RuntimeException e) {
			// em.getTransaction().rollback();
			throw e;
		} finally {
			// fecha a entity manager
			em.close();
		}
	}
	
	
	public QgrupoImpressao listaTodosNome(String nome) {

		EntityManager em = new JPAUtil().getEntityManager();
		try {
			Query querys = em.createQuery("select g from QgrupoImpressao g where g.nome='"+nome+"'");
			List<QgrupoImpressao> lista = querys.getResultList();
			if(lista.get(0)!=null){
				return lista.get(0);
			}else{
				return new QgrupoImpressao();
			}

			// em.close();
		
		} catch (RuntimeException e) {
			// em.getTransaction().rollback();
			throw e;
		} finally {
			// fecha a entity manager
			em.close();
		}
	}
	
	
	
	public List<Object[]> ListagrupoUsuario(String usuario) {
        
		EntityManager em = new JPAUtil().getEntityManager();
		 // QgrupoImpressao grupo;
		try {
			Query querys = em.createQuery("select g, i from Qimpressora i inner join i.grupo g inner join g.servidor s inner join s.usuario u where u.login='"+usuario+"'");
			List<Object[]> lista =  querys.getResultList();
		/*	Object[] aux =  lista.get(0);
		   for(Object[] obj:lista){
			   grupo =  (QgrupoImpressao) obj[0];
			   
		   }*/
		 
		   
		   return lista;
        
			// em.close();
		
		} catch (RuntimeException e) {
			// em.getTransaction().rollback();
			throw e;
		} finally {
			// fecha a entity manager
			em.close();
		}
	}
	
	
public boolean verificaGrupo(String login) {
        
		EntityManager em = new JPAUtil().getEntityManager();
		 // QgrupoImpressao grupo;
		try {
		
			Query querys = em.createQuery("select u from Cusuario u  inner join u.servidor s inner join s.grupoImpressao g where u.login='"+login+"'");
			List<Cusuario> lista =  querys.getResultList();
		/*	Object[] aux =  lista.get(0);
		   for(Object[] obj:lista){
			   grupo =  (QgrupoImpressao) obj[0];
			   
		   }*/
		 
		   if(lista.isEmpty()){
			 return false;   
		   }else{
			 return true;
		   }
		   
        
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
