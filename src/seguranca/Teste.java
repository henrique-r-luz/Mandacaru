package seguranca;



	import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

	import javax.persistence.Persistence;

	public class Teste {

	public static void main(String[] args) {

	EntityManagerFactory emf = Persistence.createEntityManagerFactory("comprasf");

	EntityManager em = emf.createEntityManager();

	em.close();

	emf.close();

	 }

	}
	