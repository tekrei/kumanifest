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
package net.kodveus.kumanifest.operation;

import java.sql.ResultSet;
import java.util.ArrayList;

import net.kodveus.gui.araclar.VeriSinif;
import net.kodveus.kumanifest.database.DBManager;
import net.kodveus.kumanifest.interfaces.OperationInterface;
import net.kodveus.kumanifest.jdo.Country;
import net.kodveus.kumanifest.utility.LogHelper;

public class CountryOperation implements OperationInterface {

	private static CountryOperation instance;

	private CountryOperation() {

	}

	public static CountryOperation getInstance() {
		if (instance == null) {
			instance = new CountryOperation();
		}
		return instance;
	}

	public long create(VeriSinif vs) {
		try {
			Country country = (Country) vs;
			String sql = "INSERT INTO country(code, name)VALUES('"
					+ country.getCode() + "', '" + country.getName() + "')";
			return DBManager.getInstance().insert(sql);
		} catch (Exception e) {
			LogHelper.getInstance().exception(e);
			return 0;
		}
	}

	public boolean delete(VeriSinif vs) {
		try {
			Country country = (Country) vs;
			String sql = "DELETE FROM country WHERE countryId="
					+ country.getCountryId();
			return DBManager.getInstance().executeUpdate(sql);
		} catch (Exception e) {
			LogHelper.getInstance().exception(e);
			return false;
		}
	}

	public boolean update(VeriSinif vs) {
		try {
			Country country = (Country) vs;
			String sql = "UPDATE country SET code = '" + country.getCode()
					+ "',name = '" + country.getName() + "' WHERE countryId="
					+ country.getCountryId();
			return DBManager.getInstance().executeUpdate(sql);
		} catch (Exception e) {
			LogHelper.getInstance().exception(e);
			return false;
		}
	}

	public ArrayList<Country> ara(VeriSinif vs) {
		ArrayList<Country> al = new ArrayList<Country>();
		try {
			Country country = (Country) vs;
			String sql = "SELECT * FROM country WHERE 1=1";
			if (country.getCode() != null) {
				sql += " AND code='" + country.getCode() + "' ";
			}
			if (country.getName() != null) {
				sql += " AND name='" + country.getName() + "' ";
			}
			if (country.getCountryId() != null) {
				sql += " AND countryId=" + country.getCountryId() + " ";
			}
			ResultSet rs = DBManager.getInstance().executeQuery(sql);
			while (rs.next()) {
				country = new Country();
				rsToCountry(rs, country);
				al.add(country);
			}
		} catch (Exception e) {
			LogHelper.getInstance().exception(e);
		}
		return al;
	}

	public VeriSinif get(Long id) {
		Country country = new Country();
		country.setCountryId(id);
		ArrayList<Country> list = ara(country);
		if (list.size() > 0) {
			return list.get(0);
		}
		return null;

	}

	public VeriSinif next(Long id) {
		Country country = null;
		try {
			String sql = "SELECT * FROM country WHERE countryId>" + id
					+ " ORDER BY countryId";
			ResultSet rs = DBManager.getInstance().executeQuery(sql);

			if (rs.next()) {
				// Tek bir kayit dondurecegiz
				country = new Country();
				rsToCountry(rs, country);
			}
			return country;
		} catch (Exception e) {
			LogHelper.getInstance().exception(e);
			country = null;
		}
		return country;
	}

	public VeriSinif previous(Long id) {
		Country country = null;
		String sql = "SELECT * FROM country WHERE countryId<" + id
				+ " ORDER BY countryId DESC";
		try {
			ResultSet rs = DBManager.getInstance().executeQuery(sql);

			if (rs.next()) {
				// Tek bir kayit dondurecegiz
				country = new Country();
				rsToCountry(rs, country);
			}
		} catch (Exception e) {
			LogHelper.getInstance().exception(e);
			country = null;
		}
		return country;
	}

	private void rsToCountry(ResultSet rs, Country country) throws Exception {
		country.setCountryId(rs.getLong("countryId"));
		country.setCode(rs.getString("code"));
		country.setName(rs.getString("name"));
	}
}
