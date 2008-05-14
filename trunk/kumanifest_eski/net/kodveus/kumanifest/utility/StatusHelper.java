package net.kodveus.kumanifest.utility;

import net.kodveus.kumanifest.MainFrame;

public class StatusHelper {

	private static StatusHelper instance = null;

	private MainFrame frame;

	private StatusHelper() {

	}

	public static StatusHelper getInstance() {
		if (instance == null) {
			instance = new StatusHelper();
		}
		return instance;
	}

	public void ilkle(MainFrame _frame) {
		frame = _frame;
	}

	public void kayitEklendi() {
		mesajGoster("Record added succesfully!");
	}

	public void kayitSilindi() {
		mesajGoster("Record deleted succesfully!");
	}

	public void hataOlustu() {
		mesajGoster("An error occured!");
	}

	public void kayitGuncellendi() {
		mesajGoster("Record updated succesfully!");
	}

	public void mesajGoster(String mesaj) {
		frame.setStatus(mesaj);
	}
}
