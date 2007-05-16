package net.kodveus.kumanifest.persistence;

import java.util.ArrayList;
import java.util.Locale;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceUnit;
import javax.persistence.Query;

import net.kodveus.kumanifest.utility.LogHelper;

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
			commitTransaction();
		} catch (Exception e) {
			printStackTrace(e);
			failTransaction();
		}
	}

	public boolean delete(Object sinif) {
		try {
			beginTransaction();
			entityManager.remove(entityManager.merge(sinif));
			commitTransaction();
			return true;
		} catch (Exception e) {
			printStackTrace(e);
			failTransaction();
			return false;
		}
	}

	public boolean update(Object sinif) {
		try {
			beginTransaction();
			entityManager.merge(sinif);
			commitTransaction();
			return true;
		} catch (Exception e) {
			printStackTrace(e);
			failTransaction();
			return false;
		}
	}

	private void printStackTrace(Exception e) {
		e.printStackTrace();
		LogHelper.getInstance().exception(e);
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
		return executeNamedQuery(queryName, null);
	}

	public ArrayList executeNamedQuery(String queryName, Object[] parameters) {
		try {
			Query query = entityManager.createNamedQuery(queryName);
			if (parameters != null) {
				for (int i = 0; i < parameters.length; i++) {
					query.setParameter("param" + i, parameters[i]);
				}
			}
			return new ArrayList(query.getResultList());
		} catch (Exception e) {
			printStackTrace(e);
			return null;
		}
	}

	public EntityManager getEM() {
		return entityManager;
	}
}
