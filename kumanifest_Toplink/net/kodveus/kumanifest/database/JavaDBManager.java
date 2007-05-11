/* Gemi tasimaciligi yukleme, bosaltma, manifesto takip programi.
 * Copyright (C) 2006  Kod ve Us
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 */
package net.kodveus.kumanifest.database;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/**
 *
 * @author Emre
 */
public class JavaDBManager {

	/** Creates a new instance of AddressDao */
	public JavaDBManager(KUMProperties props) {
		this.dbName = "kumanifest";

		setDBSystemDir();
		loadDatabaseDriver("org.apache.derby.jdbc.EmbeddedDriver");
		if (!dbExists()) {
			createDatabase(props);
		}

	}

	private boolean dbExists() {
		boolean bExists = false;
		String dbLocation = getDatabaseLocation();
		File dbFileDir = new File(dbLocation);
		if (dbFileDir.exists()) {
			bExists = true;
		}
		return bExists;
	}

	private void setDBSystemDir() {
		// decide on the db system directory
		String userHomeDir = System.getProperty("user.home", ".");
		String systemDir = userHomeDir + "/.addressbook";
		System.setProperty("derby.system.home", systemDir);

		// create the db system directory
		File fileSystemDir = new File(systemDir);
		fileSystemDir.mkdir();
	}

	private void loadDatabaseDriver(String driverName) {
		// load Derby driver
		try {
			Class.forName(driverName);
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		}

	}

	private boolean createTables(Connection dbConnection) {
		boolean bCreatedTables = false;
		Statement statement = null;
		try {
			statement = dbConnection.createStatement();
			// TODO tablolar yaratilacak
			bCreatedTables = true;
		} catch (SQLException ex) {
			ex.printStackTrace();
		}

		return bCreatedTables;
	}

	private boolean createDatabase(KUMProperties props) {
		boolean bCreated = false;
		Connection dbConnection = null;

		String dbUrl = getDatabaseUrl();
		dbProperties.put("create", "true");

		try {
			dbConnection = DriverManager.getConnection(dbUrl, props
					.getKullaniciAdi(), props.getSifre());
			bCreated = createTables(dbConnection);
		} catch (SQLException ex) {
		}
		dbProperties.remove("create");
		return bCreated;
	}

	public boolean connect() {
		String dbUrl = getDatabaseUrl();
		try {
			dbConnection = DriverManager.getConnection(dbUrl, dbProperties);

			isConnected = dbConnection != null;
		} catch (SQLException ex) {
			isConnected = false;
		}
		return isConnected;
	}

	public void disconnect() {
		if (isConnected) {
			String dbUrl = getDatabaseUrl();
			dbProperties.put("shutdown", "true");
			try {
				DriverManager.getConnection(dbUrl, dbProperties);
			} catch (SQLException ex) {
			}
			isConnected = false;
		}
	}

	public String getDatabaseLocation() {
		String dbLocation = System.getProperty("derby.system.home") + "/"
				+ dbName;
		return dbLocation;
	}

	public String getDatabaseUrl() {
		String dbUrl = dbProperties.getProperty("derby.url") + dbName;
		return dbUrl;
	}

	private Connection dbConnection;

	private Properties dbProperties;

	private boolean isConnected;

	private String dbName;

}