package dao;

import java.util.List;

import javax.persistence.EntityManager;

import model.Cgrupo;
import model.Cusuario;

public class DAOinsereLogin {
	
	private Cgrupo grupo;
	private Cusuario usuario;
	
	public void Insere() {
        
		
		EntityManager em = new JPAUtil().getEntityManager();
		try {
			// abre transacao
			em.getTransaction().begin();
			
			grupo = new Cgrupo();
			
			grupo.setNome("ROLE_ADMIN");
            em.persist(grupo); 
            usuario = new Cusuario();
          //  usuario.setNome("admin");
        	usuario.setGrupo(grupo);
        	usuario.setLogin("admin");
			usuario.setSenha("admin");
            em.persist(usuario); 
			// persiste o objeto

			em.getTransaction().commit();
			System.out.println("realizo comit");
		} catch (RuntimeException e) {
			em.getTransaction().rollback();
			System.out.println("mando rollback"); // não deveria desfazer o objeto
											// deletado aqui?!?!?!?!?!?
			throw e;
		} finally {
			// fecha a entity manager
			em.close();
		}
	}

}



/*
 // abre transacao
			em.getTransaction().begin();
			
			usuario = new Cusuario();
			usuario.setNome("admin");
			List<Cgrupo> listGrupo = new DAOqueryGrupo().pesquisaNome("ROLE_ADMIN");
			if(!listGrupo.isEmpty()){
				Cgrupo grupo  =  listGrupo.get(0);
				usuario.setGrupo(grupo);
			}
			usuario.setLogin("admin");
			usuario.setSenha("admin");
            em.persist(usuario); 
			// persiste o objeto
*/
