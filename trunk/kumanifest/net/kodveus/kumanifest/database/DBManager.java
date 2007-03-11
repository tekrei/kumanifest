/**
 * =======================================================
 * BizSakladik : Verilerin veritabanina saklanmasinda Java 
 * tarafindan kullanilmak uzere gelistirilmis bir kalici 
 * katman altyapisidir.
 * =======================================================
 * Copyright (C) 2006 AYKIRI MUHENDISLER
 * ======================================
 * This library is free software; you can redistribute it and/or modify it 
 * under the terms of the GNU Lesser General Public License as published by 
 * the Free Software Foundation; either version 2.1 of the License, or 
 * (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful, but 
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY 
 * or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public 
 * License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, 
 * USA.  
 * 
 * [Java is a trademark or registered trademark of Sun Microsystems, Inc. 
 * in the United States and other countries.]
 * 
 * Copyright (C) 2006 AYKIRI MUHENDISLER
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
	
	private static DBManager _instance;

	private String[] defaultSuruculer = { "com.mysql.jdbc.Driver",
			"oracle.jdbc.driver.OracleDriver", "sun.jdbc.odbc.JdbcOdbcDriver" };

	private String surucuAdi;

	private String _veritabaniURL;

	private String kullaniciAdi;

	private String parola;

	private ConnectionPool bh;

	private DBManager(KUMProperties props) throws Exception {

		String host = props.getProperty("adres");
		this.kullaniciAdi = props.getProperty("kullanici");
		this.parola = props.getProperty("sifre");

		String vt = props.getProperty("vtAdi");
		String vtTipi = props.getProperty("vtTipi");
		int tip = -1;

		if (vtTipi.equalsIgnoreCase("MYSQL")) {
			tip = MYSQL;
		} else if (vtTipi.equalsIgnoreCase("ORACLE")) {
			tip = ORACLE;
		} else if (vtTipi.equalsIgnoreCase("ODBC")) {
			tip = OBDC;
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
			this._veritabaniURL = "jdbc:oracle:thin:@" + sunucu + ":1521:itb";

			break;

		case OBDC:
			this._veritabaniURL = "jdbc:odbc:" + veritabaniAdi;

			break;
		}

		bh = new ConnectionPool(this._veritabaniURL, kullaniciAdi, parola,
				surucuAdi, 5);
	}

	public boolean executeUpdate(String sql) throws Exception {
		Connection conn = null;

		try {
			conn = bh.baglantiAl();

			PreparedStatement ps = conn.prepareStatement(sql);
			mesaj("executeUpdate:" + sql);
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

			mesaj("insert:" + sql);
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

			mesaj("executeQuery:" + sql);
			return ps.executeQuery();
		} catch (SQLException e) {
			throw e;
		} finally {
			if (conn != null) {
				bh.baglantiBirak(conn);
			}
		}
	}
	
	private void mesaj(String str){
		LogHelper.getInstance().bilgi(str);
	}

	public Connection getConnection() {
		return bh.baglantiAl();
	}
}
