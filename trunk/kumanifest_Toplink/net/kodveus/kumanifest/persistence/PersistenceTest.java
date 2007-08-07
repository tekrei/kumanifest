package net.kodveus.kumanifest.persistence;

import java.util.List;

import net.kodveus.kumanifest.jdo.Country;
import net.kodveus.kumanifest.jdo.Location;
import net.kodveus.kumanifest.jdo.Vessel;

public class PersistenceTest {

	public static void main(final String[] args) {
		final PersistenceTest t = new PersistenceTest();
		t.createAll();
		t.displayAll();
		t.update();
	}

	public void createAll() {
		final Vessel vessel = createVessel();

		PersistenceManager.getInstance().save(vessel);
	}

	private Vessel createVessel() {
		final Vessel vessel = new Vessel();
		vessel.setAciklama("Aciklama 2");
		vessel.setCompany("Company");

		final Country ulke = new Country();
		ulke.setAciklama("Turkiye");
		ulke.setCode("TR");
		ulke.setName("Turkiye");

		vessel.setFlag(ulke);

		final Location liman = new Location();
		liman.setAciklama("Liman");
		liman.setCode("TRL");
		liman.setIsport("Y");
		liman.setLocation("IZMIR");

		vessel.setPort(liman);
		vessel.setVesselCode("VS01");
		vessel.setVesselName("IZMIR CILGINLIGI");

		return vessel;
	}

	private void display(final List<?> list) {
		for (int i = 0; i < list.size(); i++) {
			final Object obj = list.get(i);
			System.out.println(obj);
		}
	}

	public void displayAll() {
		display(findAll("Vessel"));
		display(findAll("Country"));
		display(findAll("Location"));
	}

	public List<?> findAll(final String which) {
		return PersistenceManager.getInstance().findAll(which);
	}

	private void update() {
		final Vessel v = (Vessel) PersistenceManager.getInstance().find(
				Vessel.class, 1L);
		v.setAciklama("A");
		PersistenceManager.getInstance().update(v);
	}
}
