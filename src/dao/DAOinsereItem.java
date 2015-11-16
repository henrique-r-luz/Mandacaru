package dao;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import controle.Status;

import model.*;

public class DAOinsereItem implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void adiciona(Citem item, CinfoMaterial material, Ccatmat catmats) {

		// consegue a entity manager
		EntityManager em = new JPAUtil().getEntityManager();
		try {
			// abre transacao
			em.getTransaction().begin();

			// persiste o objeto

			// item.setCatmatGenerico(null);
			Query querys = em
					.createQuery("select d from Ccatmat d where d.numero='"
							+ catmats.getNumero() + "'");
			List<Ccatmat> listQuest2 = querys.getResultList();

			Ccatmat catmat = (Ccatmat) listQuest2.get(0);
			item.setCatmat(catmat);

			if (catmat.getTipocatmat().getSigla().equals("false")) {

				//item.setMaterial(catmat.getMaterial());
			} else {
				if (catmat.getMaterial().getNome().equals(material.getNome())
						&& catmat.getMaterial().getDescricao()
								.equals(material.getDescricao())) {
				//	item.setMaterial(catmat.getMaterial());
				} else {
					CinfoMaterial newMaterial = new CinfoMaterial();
					newMaterial.setNome(material.getNome());
					newMaterial.setDescricao(material.getDescricao());
					em.persist(newMaterial);
					//item.setMaterial(newMaterial);
				}
			}

			Cstatus status = new DAOqueryStatus()
					.pesquisaNome(Status.INSERIDO_SISTEMA.getValor());
			if (status == null) {
				status = new Cstatus();
				status.setNome(Status.INSERIDO_SISTEMA.getValor());
				em.persist(status);
			}
			item.setCatmat(catmat);
			em.persist(item);
			em.persist(catmat);

			CstatusItem statusItem = new DAOinsereStatus().geraStatus(item,
					status);
			em.persist(statusItem);

			// commita a transacao
			em.getTransaction().commit();
			FacesContext.getCurrentInstance().addMessage("salvar",
					new FacesMessage("Os dados foram salvos"));
		} catch (RuntimeException e) {
			em.getTransaction().rollback(); // não deveria desfazer o objeto
											// deletado aqui?!?!?!?!?!?
			throw e;
		} finally {
			// fecha a entity manager
			em.close();
		}
		
	}

	public void insereMaterialItem(Citem item, CinfoMaterial material,Ccatmat catmat ) {
		EntityManager em = new JPAUtil().getEntityManager();
		try{
		em.getTransaction().begin();
		CinfoMaterial newMaterial = new CinfoMaterial();
		newMaterial.setNome(material.getNome());
		newMaterial.setDescricao(material.getDescricao());
		em.persist(newMaterial);
		//item.setMaterial(newMaterial);

		Cstatus status = new DAOqueryStatus()
				.pesquisaNome(Status.INSERIDO_SISTEMA.getValor());
		if (status == null) {
			status = new Cstatus();
			status.setNome(Status.INSERIDO_SISTEMA.getValor());
			em.persist(status);
		}
		item.setCatmat(catmat);
		em.persist(item);
	//	em.persist(catmat);

		CstatusItem statusItem = new DAOinsereStatus().geraStatus(item, status);
		em.persist(statusItem);

		// commita a transacao
		em.getTransaction().commit();
		FacesContext.getCurrentInstance().addMessage("salvar",
				new FacesMessage("Os dados foram salvos"));
		}catch(RuntimeException e){
			em.getTransaction().rollback(); 
		}finally{
			em.close();
		}
		
	

	}
}
