package net.kodveus.kumanifest.database;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class KUMProperties extends java.util.Properties {

	private static final long serialVersionUID = 1L;

	public KUMProperties() {
		super();
	}

	public boolean load() {
		try {
			load(new FileInputStream("vt.props"));
			return true;
		} catch (IOException e) {
			System.out.println(e.toString());
			return false;
		}
	}

	public boolean save() {
		try {
			File file = new File("vt.props");
			file.createNewFile();
			store(new FileOutputStream(file), null);
			return true;
		} catch (IOException e) {
			return false;
		}
	}
}
