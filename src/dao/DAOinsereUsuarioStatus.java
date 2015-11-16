package dao;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;

import controle.quota.Label;
import dao.quota.DAOqueryGrupoImpressao;
import model.Cgrupo;
import model.Cusuario;
import model.CusuarioStatus;
import model.impressao.Iservidor;
import model.quota.QgrupoImpressao;

public class DAOinsereUsuarioStatus {

	public void Insere(Cusuario usuario, QgrupoImpressao grupoImpressao) {

		EntityManager em = new JPAUtil().getEntityManager();
		try {
			// abre transacao
			em.getTransaction().begin();
			CriteriaQuery<CusuarioStatus> query = em.getCriteriaBuilder().createQuery(CusuarioStatus.class);
			query.select(query.from(CusuarioStatus.class));

			List<CusuarioStatus> lista = em.createQuery(query).getResultList();
			if (lista.isEmpty()) {
				CusuarioStatus status = new CusuarioStatus();
				status.setId(1);
				status.setStatus(true);
				em.persist(status);

			
				CusuarioStatus status2 = new CusuarioStatus();
				status2.setId(2);
				status2.setStatus(false);
				em.persist(status2);
				usuario.setStatus(status);
				status.addUsuario(usuario);
				em.merge(status);
				//em.persist(usuario);
				
			}else{
				CusuarioStatus status = em.find(CusuarioStatus.class, 1);
				//em.persist(arg0);
				usuario.setStatus(status);
				status.addUsuario(usuario);
				em.merge(status);
			}
			em.persist(usuario);
		    //System.out.println("grupoImpressao id: "+grupoImpressao.getId());
			QgrupoImpressao grupoIm = new  DAOqueryGrupoImpressao().grupoId(em,grupoImpressao.getId());
			Iservidor servidor = new DAOqueryIservidor().retornServidorId(em, usuario.getServidor().getId());
		    //   DAOqueryGrupoImpressao().
			grupoIm.addServidor(servidor);
			em.merge(grupoIm);
			servidor.setGrupoImpressao(grupoIm);
			// persiste o objeto

			em.getTransaction().commit();
			FacesContext.getCurrentInstance().addMessage("salvar",
					new FacesMessage(Label.getDadosSalvo()));
		} catch (RuntimeException e) {
			em.getTransaction().rollback();
			FacesContext.getCurrentInstance().addMessage(
					"salvar",
					new FacesMessage(FacesMessage.SEVERITY_FATAL,
							Label.getErroCadastro(),null));
			// deletado aqui?!?!?!?!?!?
			throw e;
		} finally {
			// fecha a entity manager
			em.close();
		}

	}
	
	
	
	
	
	
	
	public void altera(Cusuario usuario , QgrupoImpressao grupoImpressao) {

		EntityManager em = new JPAUtil().getEntityManager();
		try {
			// abre transacao
			em.getTransaction().begin();
			CriteriaQuery<CusuarioStatus> query = em.getCriteriaBuilder().createQuery(CusuarioStatus.class);
			query.select(query.from(CusuarioStatus.class));

			List<CusuarioStatus> lista = em.createQuery(query).getResultList();
			if (lista.isEmpty()) {
				CusuarioStatus status = new CusuarioStatus();
				status.setId(1);
				status.setStatus(true);
				em.persist(status);

			
				CusuarioStatus status2 = new CusuarioStatus();
				status2.setId(2);
				status2.setStatus(false);
				em.persist(status2);
				usuario.setStatus(status);
				status.addUsuario(usuario);
				em.merge(status);
				//em.persist(usuario);
				
			}else{
				CusuarioStatus status = em.find(CusuarioStatus.class, 1);
				//em.persist(arg0);
				usuario.setStatus(status);
				status.addUsuario(usuario);
				em.merge(status);
			}
			em.merge(usuario);
			QgrupoImpressao grupoIm = new  DAOqueryGrupoImpressao().grupoId(em,grupoImpressao.getId());
			Iservidor servidor = new DAOqueryIservidor().retornServidorId(em, usuario.getServidor().getId());
		    //   DAOqueryGrupoImpressao().
			grupoIm.addServidor(servidor);
			em.merge(grupoIm);
			servidor.setGrupoImpressao(grupoIm);
			// persiste o objeto

			em.getTransaction().commit();
			FacesContext.getCurrentInstance().addMessage("salvar",
					new FacesMessage(Label.getDadosSalvo()));
		} catch (RuntimeException e) {
			em.getTransaction().rollback();
			FacesContext.getCurrentInstance().addMessage(
					"salvar",
					new FacesMessage(FacesMessage.SEVERITY_FATAL,
Label.getErroCadastro(),null));
			// deletado aqui?!?!?!?!?!?
			throw e;
		} finally {
			// fecha a entity manager
			em.close();
		}

	}

}

/*
 * // abre transacao em.getTransaction().begin();
 * 
 * usuario = new Cusuario(); usuario.setNome("admin"); List<Cgrupo> listGrupo =
 * new DAOqueryGrupo().pesquisaNome("ROLE_ADMIN"); if(!listGrupo.isEmpty()){
 * Cgrupo grupo = listGrupo.get(0); usuario.setGrupo(grupo); }
 * usuario.setLogin("admin"); usuario.setSenha("admin"); em.persist(usuario); //
 * persiste o objeto
 */
