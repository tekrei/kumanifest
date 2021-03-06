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
import net.kodveus.kumanifest.jdo.Location;
import net.kodveus.kumanifest.utility.LogHelper;

public class LocationOperation implements OperationInterface {

	private static LocationOperation instance;

	private LocationOperation() {

	}

	public static LocationOperation getInstance() {
		if (instance == null) {
			instance = new LocationOperation();
		}
		return instance;
	}

	public long create(VeriSinif vs) {
		try {
			Location location = (Location) vs;
			String sql = "INSERT INTO location(location, isport, code)VALUES('"
					+ location.getLocation() + "', '" + location.getIsport()
					+ "', '" + location.getCode() + "')";
			return DBManager.getInstance().insert(sql);
		} catch (Exception e) {
			LogHelper.getInstance().exception(e);
			return 0;
		}
	}

	public boolean delete(VeriSinif vs) {
		try {
			Location location = (Location) vs;
			String sql = "DELETE FROM location WHERE locationId="
					+ location.getLocationId();
			return DBManager.getInstance().executeUpdate(sql);
		} catch (Exception e) {
			LogHelper.getInstance().exception(e);
			return false;
		}
	}

	public boolean update(VeriSinif vs) {
		try {
			Location location = (Location) vs;
			String sql = "UPDATE location SET location = '"
					+ location.getLocation() + "',isport = '"
					+ location.getIsport() + "',code = '" + location.getCode()
					+ "' WHERE locationId=" + location.getLocationId();
			return DBManager.getInstance().executeUpdate(sql);
		} catch (Exception e) {
			LogHelper.getInstance().exception(e);
			return false;
		}
	}

	public ArrayList<Location> getPorts() {
		Location l = new Location();
		l.setIsport("Y");
		return ara(l);
	}

	public ArrayList<Location> ara(VeriSinif vs) {
		ArrayList<Location> al = new ArrayList<Location>();
		try {
			Location location = (Location) vs;
			String sql = "SELECT * FROM location WHERE 1=1";
			if (location.getLocation() != null) {
				sql += " AND location='" + location.getLocation() + "' ";
			}
			if (location.getIsport() != null) {
				sql += " AND isport='" + location.getIsport() + "' ";
			}
			if (location.getCode() != null) {
				sql += " AND code='" + location.getCode() + "' ";
			}
			if (location.getLocationId() != null) {
				sql += " AND locationId=" + location.getLocationId() + " ";
			}
			ResultSet rs = DBManager.getInstance().executeQuery(sql);
			while (rs.next()) {
				location = new Location();
				rsToLocation(rs, location);
				al.add(location);
			}
		} catch (Exception e) {
			LogHelper.getInstance().exception(e);
		}
		return al;
	}

	public VeriSinif get(Long id) {
		Location location = new Location();
		location.setLocationId(id);
		ArrayList<Location> list = ara(location);
		if (list.size() > 0) {
			return list.get(0);
		}
		return null;

	}

	public VeriSinif next(Long id) {
		Location location = null;
		try {
			String sql = "SELECT * FROM location WHERE locationId>" + id
					+ " ORDER BY locationId";
			ResultSet rs = DBManager.getInstance().executeQuery(sql);

			if (rs.next()) {
				// Tek bir kayit dondurecegiz
				location = new Location();
				rsToLocation(rs, location);
			}
			return location;
		} catch (Exception e) {
			LogHelper.getInstance().exception(e);
			location = null;
		}
		return location;
	}

	public VeriSinif previous(Long id) {
		Location location = null;
		String sql = "SELECT * FROM location WHERE locationId<" + id
				+ " ORDER BY locationId DESC";
		try {
			ResultSet rs = DBManager.getInstance().executeQuery(sql);

			if (rs.next()) {
				// Tek bir kayit dondurecegiz
				location = new Location();
				rsToLocation(rs, location);
			}
		} catch (Exception e) {
			LogHelper.getInstance().exception(e);
			location = null;
		}
		return location;
	}

	private void rsToLocation(ResultSet rs, Location location) throws Exception {
		location.setLocationId(rs.getLong("locationId"));
		location.setLocation(rs.getString("location"));
		location.setCode(rs.getString("code"));
		location.setIsport(rs.getString("isport"));
	}
}
