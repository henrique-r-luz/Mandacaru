package dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import model.Cfracassados;
import model.Citem;
import model.Corcamento;

public class DAOisereFracassoPregao {

	private List<Citem> listaItem;

	public void addItem(Corcamento[] selectedOrcament, Cfracassados fracasso,
			List<Corcamento> lista) {

		insereOrcamentoItem(selectedOrcament);
		EntityManager em = new JPAUtil().getEntityManager();
		try {
			em.getTransaction().begin();
			
				for (Corcamento obj : selectedOrcament) {
                    
					if (new DAOqueryItem().verificaFracassoItem(listaItem)) {
						// adiciona fracasso em item
				
						Citem itens = obj.getItem();
						itens.addItemFracasso(fracasso);
						//fracasso.addItem(itens);
						em.merge(itens);
						//em.merge(fracasso);
					} else {

					}

				}
		

			em.getTransaction().commit();
			
		} catch (RuntimeException e) {
			;
			em.getTransaction().rollback();
			throw e;
		} finally {

			em.close();
		}
	}

	public void insereOrcamentoItem(Corcamento[] selecionados) {
		listaItem = new ArrayList<Citem>();
		for (Corcamento orca : selecionados) {

			listaItem.add(orca.getItem());
		}

	}

	public void removeItem(Corcamento[] selectedOrcamentoList) {

		EntityManager em = new JPAUtil().getEntityManager();
		try {
			em.getTransaction().begin();
			for (Corcamento obj : selectedOrcamentoList) {
				// remover item de um relacionamento ManyToMany
				Citem itens = obj.getItem();
				Cfracassados fracasso = obj.getFracassados();
				itens.removeFracasso(fracasso);
				//itens.removeFracasso(fracasso);
               em.merge(itens);
				// getItemProcesso(obj.getItem(), fracasso);

			}
			em.getTransaction().commit();
		} catch (RuntimeException e) {
			em.getTransaction().rollback();
			throw e;
		} finally {

			em.close();
		}

	}

}
