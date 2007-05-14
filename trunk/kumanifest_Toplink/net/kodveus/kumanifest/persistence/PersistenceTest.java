package net.kodveus.kumanifest.persistence;

import java.util.List;

import net.kodveus.kumanifest.jdo.Country;
import net.kodveus.kumanifest.jdo.Location;
import net.kodveus.kumanifest.jdo.Vessel;

public class PersistenceTest {

	public void createAll() {
		PersistenceManager.getInstance().save(createVessel());
	}

	public List<Vessel> findAll() {
		return PersistenceManager.getInstance().executeNamedQuery("findAll");
	}

	public void display(List<Vessel> Vessels) {
		for (Vessel Vessel : Vessels) {
			display(Vessel);
		}
	}

	private void display(Vessel vessel) {
		StringBuilder results = new StringBuilder();
		results.append(vessel.getAciklama() + ",");
		results.append(vessel.getCompany() + ",");
		results.append(vessel.getFlag() + ",");
		results.append(vessel.getPort() + ",");
		results.append(vessel.getStatus() + ",");
		results.append(vessel.getVesselCode() + ",");
		results.append(vessel.getVesselId() + ",");
		results.append(vessel.getVesselName());
		System.out.println(results.toString());
	}

	private Vessel createVessel() {
		Vessel vessel = new Vessel();
		vessel.setAciklama("Aciklama");
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
		t.display(t.findAll());
		t.display((Vessel) PersistenceManager.getInstance().find(Vessel.class,
				1L));
	}
}
