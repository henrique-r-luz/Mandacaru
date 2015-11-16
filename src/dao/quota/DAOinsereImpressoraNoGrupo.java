package dao.quota;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import controle.quota.Label;
import model.quota.QgrupoImpressao;
import model.quota.Qimpressora;
import dao.JPAUtil;

public class DAOinsereImpressoraNoGrupo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void atualiza(List<Qimpressora> impressoraList,
			QgrupoImpressao grupo, List<Qimpressora> removeImpressora) {
		EntityManager em = new JPAUtil().getEntityManager();
		try {
			em.getTransaction().begin();

			Query querys = em
					.createQuery("select g from QgrupoImpressao g where g.id="
							+ grupo.getId());
			QgrupoImpressao grupoInsert = (QgrupoImpressao) querys
					.getSingleResult();
			grupoInsert.setImpressora(impressoraList);
			em.merge(grupoInsert);

			em.getTransaction().commit();
			FacesContext.getCurrentInstance().addMessage("salvar",
					new FacesMessage(Label.getDadosSalvo()));
		} catch (RuntimeException e) {
			em.getTransaction().rollback();
			FacesContext.getCurrentInstance().addMessage(
					"salvar",
					new FacesMessage(FacesMessage.SEVERITY_ERROR,
							Label.getTransConcluida(), null));
			throw e;

		} finally {
			// fecha a entity manager
			em.close();
		}
	}

}
