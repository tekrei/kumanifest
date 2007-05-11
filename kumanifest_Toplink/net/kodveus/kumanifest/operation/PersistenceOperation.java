package net.kodveus.kumanifest.operation;

import java.util.Locale;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceUnit;

public class PersistenceOperation {
	private static PersistenceOperation instance;

	protected EntityManagerFactory entityManagerFactory;

	@PersistenceUnit
	protected EntityManager entityManager;

	private PersistenceOperation() {
		Locale.setDefault(Locale.US);
		entityManagerFactory = Persistence
				.createEntityManagerFactory("PersistentUnit");
		entityManager = entityManagerFactory.createEntityManager();
	}

	public static PersistenceOperation getInstance() {
		if (instance == null) {
			instance = new PersistenceOperation();
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

	public void delete(Object sinif) {
		try {
			beginTransaction();
			entityManager.remove(sinif);
		} catch (Exception e) {
			failTransaction();
		} finally {
			commitTransaction();
		}
	}

	public void update(Object sinif) {
		try {
			beginTransaction();
			entityManager.refresh(sinif);
		} catch (Exception e) {
			failTransaction();
		} finally {
			commitTransaction();
		}
	}

	public Object find(Class sinif,Object primKey){
		return entityManager.find(sinif,primKey);
	}

	public java.util.List executeNamedQuery(String queryName){
		return entityManager.createNamedQuery(queryName).getResultList();
	}
}
