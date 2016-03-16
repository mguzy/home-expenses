package home.expense.sql;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public abstract class BasicDao {

	private static String PERSISTENCE_UNIT_NAME = "manager1";
	private static EntityManagerFactory entityManagerFactory = Persistence
			.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
	private static EntityManager entityManager = entityManagerFactory.createEntityManager();
	protected EntityManager getEntityManager(){
		return entityManager;
	}
	public static void close(){
		entityManager.close();
		entityManagerFactory.close();
	}
}
