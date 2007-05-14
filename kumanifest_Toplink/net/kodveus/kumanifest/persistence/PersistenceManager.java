package net.kodveus.kumanifest.persistence;

import java.util.ArrayList;
import java.util.Locale;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceUnit;
import javax.persistence.Query;

public class PersistenceManager {
	private static PersistenceManager instance;

	protected EntityManagerFactory entityManagerFactory;

	@PersistenceUnit
	protected EntityManager entityManager;

	private PersistenceManager() {
		Locale.setDefault(Locale.US);
		entityManagerFactory = Persistence
				.createEntityManagerFactory("PersistentUnit");
		entityManager = entityManagerFactory.createEntityManager();
	}

	public static PersistenceManager getInstance() {
		if (instance == null) {
			instance = new PersistenceManager();
		}
		return instance;
	}

	private void beginTransaction() {
		entityManager.getTransaction().begin();
	}

	private void commitTransaction() {
		entityManager.getTransaction().commit();
	}

	private void failTransaction() {
		entityManager.getTransaction().rollback();
	}

	public void save(Object sinif) {
		try {
			beginTransaction();
			entityManager.persist(sinif);
		} catch (Exception e) {
			failTransaction();
		} finally {
			commitTransaction();
		}
	}

	public boolean delete(Object sinif) {
		try {
			beginTransaction();
			entityManager.remove(sinif);
			return true;
		} catch (Exception e) {
			failTransaction();
			return false;
		} finally {
			commitTransaction();
		}
	}

	public boolean update(Object sinif) {
		try {
			beginTransaction();
			entityManager.refresh(sinif);
			return true;
		} catch (Exception e) {
			failTransaction();
			return false;
		} finally {
			commitTransaction();
		}
	}

	public Object find(Class sinif, Object primKey) {
		return entityManager.find(sinif, primKey);
	}

	public ArrayList executeQuery(String nativeQuery) {
		Query query = entityManager.createNativeQuery(nativeQuery);
		return new ArrayList(query.getResultList());
	}

	public ArrayList findAll(String pre) {
		return executeNamedQuery(pre + ".findAll");
	}

	public ArrayList executeNamedQuery(String queryName) {
		return new ArrayList(entityManager.createNamedQuery(queryName)
				.getResultList());
	}
}
