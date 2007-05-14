package net.kodveus.kumanifest.persistence;

import java.util.List;

import net.kodveus.kumanifest.jdo.Country;
import net.kodveus.kumanifest.jdo.Location;
import net.kodveus.kumanifest.jdo.Vessel;

public class PersistenceTest {

	public void createAll() {
		Vessel vessel = createVessel();

		PersistenceManager.getInstance().save(vessel);
	}

	public List<Vessel> findAll(String which) {
		return PersistenceManager.getInstance().findAll(which);
	}

	public void displayAll() {
		display(findAll("Vessel"));
		display(findAll("Country"));
		display(findAll("Location"));
	}

	private void display(List list){
		for (int i = 0; i < list.size(); i++) {
			Object obj = list.get(i);
			System.out.println(obj);
		}
	}

	private Vessel createVessel() {
		Vessel vessel = new Vessel();
		vessel.setAciklama("Aciklama 2");
		vessel.setCompany("Company");

		Country ulke = new Country();
		ulke.setAciklama("Turkiye");
		ulke.setCode("TR");
		ulke.setName("Turkiye");

		vessel.setFlag(ulke);

		Location liman = new Location();
		liman.setAciklama("Liman");
		liman.setCode("TRL");
		liman.setIsport("Y");
		liman.setLocation("IZMIR");

		vessel.setPort(liman);
		vessel.setVesselCode("VS01");
		vessel.setVesselName("IZMIR CILGINLIGI");

		return vessel;
	}

	public static void main(String[] args) {
		PersistenceTest t = new PersistenceTest();
		t.createAll();
		t.displayAll();
	}
}
