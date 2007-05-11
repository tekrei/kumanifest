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

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import net.kodveus.kumanifest.utility.LogHelper;

public class DBManager {
	public static final int MYSQL = 0;

	public static final int ORACLE = 1;

	public static final int OBDC = 2;

	public static final int DERBY = 3;

	private static DBManager _instance;

	private String[] defaultSuruculer = { "com.mysql.jdbc.Driver",
			"oracle.jdbc.driver.OracleDriver", "sun.jdbc.odbc.JdbcOdbcDriver",
			"org.apache.derby.jdbc.EmbeddedDriver" };

	private String surucuAdi;

	private String _veritabaniURL;

	private String kullaniciAdi;

	private String parola;

	private ConnectionPool bh;

	private DBManager(KUMProperties props) throws Exception {

		String host = props.getAdres();
		this.kullaniciAdi = props.getKullaniciAdi();
		this.parola = props.getSifre();

		String vt = props.getVeritabaniAdi();
		String vtTipi = props.getVeritabaniTipi();
		int tip = -1;

		if (vtTipi.equalsIgnoreCase("MYSQL")) {
			tip = MYSQL;
		} else if (vtTipi.equalsIgnoreCase("ORACLE")) {
			tip = ORACLE;
		} else if (vtTipi.equalsIgnoreCase("ODBC")) {
			tip = OBDC;
		} else if (vtTipi.equalsIgnoreCase("DERBY")) {
			tip = DERBY;
		}

		vtIlkle(tip, vt, host);
	}

	public static DBManager getInstance() throws Exception {
		if (_instance == null) {
			KUMProperties props = new KUMProperties();
			if (props.load()) {
				_instance = new DBManager(props);
			} else {
				throw new Exception("vt.props bulunamadı!");
			}
		}
		return _instance;
	}

	public static void check(KUMProperties props) throws Exception {
		new DBManager(props);
	}

	private void vtIlkle(int surucuKodu, String veritabaniAdi, String sunucu)
			throws Exception {
		this.surucuAdi = defaultSuruculer[surucuKodu];

		switch (surucuKodu) {
		case MYSQL:
			this._veritabaniURL = "jdbc:mysql://" + sunucu + ":3306/"
					+ veritabaniAdi;

			break;

		case ORACLE:
			this._veritabaniURL = "jdbc:oracle:thin:@" + sunucu + ":1521:"
					+ veritabaniAdi;

			break;

		case OBDC:
			this._veritabaniURL = "jdbc:odbc:" + veritabaniAdi;

			break;

		case DERBY:
			this._veritabaniURL = "jdbc:derby:" + veritabaniAdi;
		}

		bh = new ConnectionPool(this._veritabaniURL, kullaniciAdi, parola,
				surucuAdi, 5);
	}

	public boolean executeUpdate(String sql) throws Exception {
		Connection conn = null;

		try {
			conn = bh.baglantiAl();

			PreparedStatement ps = conn.prepareStatement(sql);
			LogHelper.getLogger().info(sql);
			ps.executeUpdate();
			return true;
		} catch (SQLException e) {
			throw e;
		} finally {
			if (conn != null) {
				bh.baglantiBirak(conn);
			}
		}
	}

	/**
	 * Auto generated id sahasini alabilmek icin bu insert kullanilmali
	 *
	 * @param sql
	 * @return
	 * @throws Exception
	 */
	public long insert(String sql) throws Exception {
		Connection conn = null;

		try {
			conn = bh.baglantiAl();

			PreparedStatement ps = conn.prepareStatement(sql,
					PreparedStatement.RETURN_GENERATED_KEYS);

			ps.executeUpdate();
			ResultSet rs = ps.getGeneratedKeys();
			rs.next();
			long key = rs.getLong(1);

			LogHelper.getLogger().info(sql);
			return key;

		} catch (SQLException e) {
			throw e;
		} finally {
			if (conn != null) {
				bh.baglantiBirak(conn);
			}
		}
	}

	public ResultSet executeQuery(String sql) throws Exception {
		Connection conn = null;

		try {
			conn = bh.baglantiAl();

			PreparedStatement ps = conn.prepareStatement(sql);

			LogHelper.getLogger().info(sql);
			return ps.executeQuery();
		} catch (SQLException e) {
			throw e;
		} finally {
			if (conn != null) {
				bh.baglantiBirak(conn);
			}
		}
	}

	public Connection getConnection() {
		return bh.baglantiAl();
	}
}
