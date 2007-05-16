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
import java.sql.DriverManager;
import java.util.ArrayList;

class ConnectionPool {

	private String _url;

	private String _kullanici;

	private String _parola;

	private String _surucuAdi;

	private int _connSayi;

	private ArrayList<Connection> pasifBaglanti = null;

	private ArrayList<Connection> aktifBaglanti = null;

	public ConnectionPool(String url, String kullanici, String parola,
			String surucuAdi, int connSayi) throws Exception {
		_url = url;
		_kullanici = kullanici;
		_parola = parola;
		_connSayi = connSayi;
		_surucuAdi = surucuAdi;
		pasifBaglanti = new ArrayList<Connection>(connSayi);
		aktifBaglanti = new ArrayList<Connection>(connSayi);

		baglantilariIlkle();
	}

	private void baglantilariIlkle() throws Exception {
		Class.forName(_surucuAdi).newInstance();
		for (int i = 0; i < _connSayi; i++) {
			pasifBaglanti.add(baglantiOlustur());
		}
	}

	private Connection baglantiOlustur() throws Exception {
		return DriverManager.getConnection(_url, _kullanici, _parola);
	}

	public synchronized Connection baglantiAl() {
		Connection conn = null;
		if (pasifBaglanti.size() > 0) {
			conn = pasifBaglanti.remove(0);
			aktifBaglanti.add(conn);
		}
		return conn;
	}

	public synchronized boolean baglantiBirak(Connection conn) {
		if (conn == null) {
			return false;
		}
		pasifBaglanti.add(conn);
		return aktifBaglanti.remove(conn);
	}
}
