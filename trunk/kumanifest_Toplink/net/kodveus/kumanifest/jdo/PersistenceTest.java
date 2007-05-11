package net.kodveus.kumanifest.jdo;


import java.util.List;
import java.util.Locale;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceUnit;
import javax.persistence.Query;

public class PersistenceTest {

	private EntityManagerFactory entityManagerFactory;
	@PersistenceUnit
	private EntityManager entityManager;

	public PersistenceTest() {
		Locale.setDefault(Locale.US);
		entityManagerFactory =
			Persistence.createEntityManagerFactory("PersistentUnit");
		entityManager = entityManagerFactory.createEntityManager();
	}

	public void createAll(){
		try{
			beginTransaction();
			Commodity emp1 = create("Johny", "35", "10000", "d","a","c",0L);
			entityManager.persist(emp1);
			Commodity emp2 = create("David", "43", "50000", "d","a","c",0L);
			entityManager.persist(emp2);
			Commodity emp3 = create("Philip", "55", "70000","d","a","c",0L);
			entityManager.persist(emp3);
			Commodity emp4 = create("James", "29", "30000", "d","a","c",0L);
			entityManager.persist(emp4);
		}catch (Exception exception){
			failTransaction();
		}finally{
			commitTransaction();
		}
	}

	public List<Commodity> findAllCommoditys(){
		Query query = entityManager.createNamedQuery("Commodity.findAll");
		List Commoditys = query.getResultList();
		return ((List<Commodity>)Commoditys);
	}

	public void display(List<Commodity> Commoditys){
		for(Commodity Commodity : Commoditys){
			display(Commodity);
		}
	}

	private void display(Commodity Commodity){
		StringBuilder results = new StringBuilder();
		results.append(Commodity.getAciklama()+",");
		results.append(Commodity.getCode()+",");
		results.append(Commodity.getCommodityId()+",");
		results.append(Commodity.getDescription()+",");
		results.append(Commodity.getName()+",");
		results.append(Commodity.getStatus()+",");
		System.out.println(results.toString());
	}

	private void beginTransaction(){
		entityManager.getTransaction().begin();
	}

	private void commitTransaction(){
		entityManager.getTransaction().commit();
	}

	private void failTransaction(){
		entityManager.getTransaction().rollback();
	}

	private Commodity create(String name, String age, String company, String description,String aciklama,String code,Long status){
		Commodity Commodity = new Commodity();
		Commodity.setAciklama(aciklama);
		Commodity.setCode(code);
		Commodity.setDescription(description);
		Commodity.setName(name);
		Commodity.setStatus(status);
		return Commodity;
	}

	public static void main(String[] args) {
		PersistenceTest t = new PersistenceTest();
		t.createAll();
		t.display(t.findAllCommoditys());
	}
}
