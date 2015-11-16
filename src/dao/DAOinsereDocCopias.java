package dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TemporalType;

import model.Ccatmat;
import model.CinfoMaterial;
import model.impressao.IcotacaoCopias;
import model.impressao.IdocCopias;
import model.impressao.Iservidor;

public class DAOinsereDocCopias {

	@SuppressWarnings("unchecked")
	public void adiciona(IdocCopias copias, Iservidor servidor) {

		// consegue a entity manager
		EntityManager em = new JPAUtil().getEntityManager();
		em.getTransaction().begin();
		try {

			List<IcotacaoCopias> listQuest = new ArrayList<IcotacaoCopias>();

			listQuest = verificaDataCopia(em, copias.getData());

			// List<IcotacaoCopias> listQuest = querys.getResultList();
			if (listQuest.size() == 0) {
				FacesContext
						.getCurrentInstance()
						.addMessage(
								"salvar",
								new FacesMessage(
										FacesMessage.SEVERITY_ERROR,
										"Não existe cotação cadastrada para essa data.",
										null));
			} else {
			
				// abre transacao
				
				IcotacaoCopias cotacao = listQuest.get(0);
				// em.persist(copias);
				copias.setServidor(servidor);
				copias.setCotacao(cotacao);
				servidor.addCopias(copias);
				cotacao.addCopias(copias);
				// em.merge(servidor);
				em.persist(copias);
				em.getTransaction().commit();
				FacesContext.getCurrentInstance().addMessage("salvar",
						new FacesMessage("Os dados foram salvos"));
			}
		} catch (RuntimeException e) {
			
			em.getTransaction().rollback(); // não deveria desfazer o objeto
											// deletado aqui?!?!?!?!?!?
			FacesContext.getCurrentInstance().addMessage("salvar",
					new FacesMessage(	FacesMessage.SEVERITY_ERROR,"Transação não pode ser concluida",null));
			throw e;
		} finally {
			// fecha a entity manager
			em.close();
		}

	}

	@SuppressWarnings("unchecked")
	public List<IcotacaoCopias> verificaDataCopia(EntityManager em, Date data) {
		return em
				.createQuery(
						"select c from IcotacaoCopias c  where (c.dataFinal >=:data  AND c.dataInicial <=:data )")
				.setParameter("data", data, TemporalType.DATE).getResultList();
	}

	public void altera(IdocCopias copias, Iservidor servidor) {

		// consegue a entity manager
		EntityManager em = new JPAUtil().getEntityManager();
		em.getTransaction().begin();
		try {
			List<IcotacaoCopias> listQuest2 = verificaDataCopia(em, copias.getData());
			
			if (listQuest2.size() == 0) {
				FacesContext
						.getCurrentInstance()
						.addMessage(
								"salvar",
								new FacesMessage(
										FacesMessage.SEVERITY_ERROR,
										"Não existe cotação cadastrada para essa data.",
										null));
			} else {
				
				@SuppressWarnings("unused")
				IcotacaoCopias cotacao = listQuest2.get(0);
				
				if (!servidor.equals(copias.getServidor())) {
					Query querys = em
							.createQuery("select s from Iservidor s inner join s.copias d where s.id="
									+ servidor.getId());
					List<Iservidor> listQuest = querys.getResultList();
					Iservidor serv2 = listQuest.get(0);
					serv2.removeCopias(copias);
					em.merge(serv2);
				}
                copias.setCotacao(cotacao);
                cotacao.addCopias(copias);
				em.merge(copias);
				// em.merge(copias);
				// /Iservidor serv = copias.getServidor();
				// serv.addCopias(copias);
				// em.merge(serv);
				// commita a transacao
				em.getTransaction().commit();
				FacesContext.getCurrentInstance().addMessage("salvar",
						new FacesMessage("Os dados foram salvos"));
			}
		} catch (RuntimeException e) {
			FacesContext.getCurrentInstance().addMessage("salvar",
					new FacesMessage("Transação não pode ser concluida"));
			em.getTransaction().rollback(); // não deveria desfazer o objeto
											// deletado aqui?!?!?!?!?!?
			throw e;
		} finally {
			// fecha a entity manager
			em.close();
		}

	}

}
