/*
 * Created on Jul 3, 2006
 *
 */
package net.kodveus.kumanifest.operation;

import java.sql.ResultSet;
import java.util.ArrayList;

import net.kodveus.gui.araclar.VeriSinif;
import net.kodveus.kumanifest.database.DBManager;
import net.kodveus.kumanifest.interfaces.OperationInterface;
import net.kodveus.kumanifest.jdo.Country;

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
			e.printStackTrace();
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
			e.printStackTrace();
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
			e.printStackTrace();
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
			e.printStackTrace();
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
			e.printStackTrace();
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
			e.printStackTrace();
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
