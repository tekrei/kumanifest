package net.kodveus.kumanifest.jdo;

import java.util.List;

import net.kodveus.kumanifest.operation.PersistenceOperation;

public class PersistenceTest {

	public void createAll() {
		Commodity emp1 = createCommodity("Johny", "35", "10000", "d", "a", "c",
				0L);
		PersistenceOperation.getInstance().save(emp1);
		Commodity emp2 = createCommodity("David", "43", "50000", "d", "a", "c",
				0L);
		PersistenceOperation.getInstance().save(emp2);
		Commodity emp3 = createCommodity("Philip", "55", "70000", "d", "a",
				"c", 0L);
		PersistenceOperation.getInstance().save(emp3);
		Commodity emp4 = createCommodity("James", "29", "30000", "d", "a", "c",
				0L);
		PersistenceOperation.getInstance().save(emp4);
	}

	public List<Commodity> findAllCommoditys() {
		return PersistenceOperation.getInstance()
				.executeNamedQuery("Commodity.findAll");
	}

	public void display(List<Commodity> Commoditys) {
		for (Commodity Commodity : Commoditys) {
			display(Commodity);
		}
	}

	private void display(Commodity Commodity) {
		StringBuilder results = new StringBuilder();
		results.append(Commodity.getAciklama() + ",");
		results.append(Commodity.getCode() + ",");
		results.append(Commodity.getCommodityId() + ",");
		results.append(Commodity.getDescription() + ",");
		results.append(Commodity.getName() + ",");
		results.append(Commodity.getStatus() + ",");
		System.out.println(results.toString());
	}

	private Commodity createCommodity(String name, String age, String company,
			String description, String aciklama, String code, Long status) {
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
		t.display((Commodity)PersistenceOperation.getInstance().find(Commodity.class, 1L));
	}
}
