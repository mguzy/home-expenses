package home.expense.sql;


import home.expense.tables.Injectable;

public class SqlInjector extends BasicDao {
	private static SqlInjector instance = null;

	private SqlInjector() {
	}

	public static SqlInjector getInstance() {
		if (instance == null) {
			instance = new SqlInjector();
		}
		return instance;
	}

	public void addToDb(Injectable newRecord) {
		super.getEntityManager().getTransaction().begin();
		super.getEntityManager().persist(newRecord);
		super.getEntityManager().getTransaction().commit();
	}
}
