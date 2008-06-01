package net.kodveus.kumanifest.db;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnectionManager {

	private static DBParameters dbp;

	// TODO connection pooling?

	public static Connection getConnection() {
		try {
			if (dbp == null)
				dbp = PersistenceXMLReader.readParameters();
			Class.forName(dbp.dbDriver);
			return DriverManager.getConnection(dbp.dbURL, dbp.dbUser,
					dbp.dbPassword);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
