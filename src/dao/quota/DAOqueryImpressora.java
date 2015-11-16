package dao.quota;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import model.impressao.Iservidor;
import model.quota.Qimpressora;
import dao.JPAUtil;

public class DAOqueryImpressora {
	
	
	public Qimpressora pesquisaUnicoNome(String nome) {
		EntityManager managers = new JPAUtil().getEntityManager();
		try {
			Query querys = managers
					.createQuery("select i from Qimpressora i where i.nome='"
							+ nome + "'");
			List<Qimpressora> listQuest = querys.getResultList();
			Qimpressora impressora = listQuest.get(0);
			// managers.close();
			return impressora;
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

}
