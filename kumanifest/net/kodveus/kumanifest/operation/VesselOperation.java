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
import java.sql.SQLException;
import java.util.ArrayList;

import net.kodveus.gui.araclar.VeriSinif;
import net.kodveus.kumanifest.database.DBManager;
import net.kodveus.kumanifest.interfaces.OperationInterface;
import net.kodveus.kumanifest.jdo.Country;
import net.kodveus.kumanifest.jdo.Location;
import net.kodveus.kumanifest.jdo.Vessel;
import net.kodveus.kumanifest.utility.LogHelper;

public class VesselOperation implements OperationInterface {

	private static VesselOperation instance;

	private VesselOperation() {

	}

	public static VesselOperation getInstance() {
		if (instance == null) {
			instance = new VesselOperation();
		}
		return instance;
	}

	public long create(VeriSinif vs) {
		try {
			Vessel vessel = (Vessel) vs;
			String sql = "INSERT INTO vessel(vesselCode, vesselName, flag, company, port)VALUES("
					+ "'"
					+ vessel.getVesselCode()
					+ "','"
					+ vessel.getVesselName()
					+ "',"
					+ vessel.getFlag().getCountryId()
					+ ",'"
					+ vessel.getCompany()
					+ "',"
					+ vessel.getPort().getLocationId() + ")";
			return DBManager.getInstance().insert(sql);
		} catch (Exception e) {
			LogHelper.getInstance().exception(e);
			return 0;
		}
	}

	public boolean delete(VeriSinif vs) {
		try {
			Vessel vessel = (Vessel) vs;
			String sql = "DELETE FROM vessel WHERE vesselId="
					+ vessel.getVesselId();
			return DBManager.getInstance().executeUpdate(sql);
		} catch (Exception e) {
			LogHelper.getInstance().exception(e);
			return false;
		}
	}

	public boolean update(VeriSinif vs) {
		// TODO Buralarda null kontrolu yapsak iyi olmaz mi?
		try {
			Vessel vessel = (Vessel) vs;
			String sql = "UPDATE vessel SET" + " vesselCode = '"
					+ vessel.getVesselCode() + "'," + " vesselName = '"
					+ vessel.getVesselName() + "'," + " flag ="
					+ vessel.getFlag().getCountryId() + "," + " company = '"
					+ vessel.getCompany() + "'," + " port = "
					+ vessel.getPort().getLocationId() + " WHERE "
					+ " vesselId = " + vessel.getVesselId();
			return DBManager.getInstance().executeUpdate(sql);
		} catch (Exception e) {
			LogHelper.getInstance().exception(e);
			return false;
		}
	}

	public VeriSinif get(Long id) {
		Vessel vessel = new Vessel();
		vessel.setVesselId(id);
		ArrayList<Vessel> list = ara(vessel);
		if (list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	private void rsToVessel(ResultSet rs, Vessel vessel) throws Exception {
		vessel.setVesselId(rs.getLong("vesselId"));
		vessel.setVesselCode(rs.getString("vesselCode"));
		vessel.setVesselName(rs.getString("vesselName"));
		vessel.setStatus(rs.getLong("status"));
		vessel.setCompany(rs.getString("company"));
		// Iliskiyi yerlestirelim
		vessel.setPort((Location) LocationOperation.getInstance().get(
				rs.getLong("port")));
		vessel.setFlag((Country) CountryOperation.getInstance().get(
				rs.getLong("flag")));
	}

	public ArrayList<Vessel> ara(VeriSinif vs) {
		ArrayList<Vessel> al = new ArrayList<Vessel>();
		try {
			Vessel vessel = (Vessel) vs;
			String sql = "SELECT * FROM vessel WHERE 1=1";
			if (vessel.getCompany() != null && !vessel.getCompany().equals("")) {
				sql += " AND company='" + vessel.getCompany() + "' ";
			}
			if (vessel.getFlag() != null
					&& vessel.getFlag().getCountryId() != null) {
				sql += " AND flag=" + vessel.getFlag().getCountryId();
			}
			if (vessel.getPort() != null
					&& vessel.getPort().getLocationId() != null) {
				sql += " AND port=" + vessel.getPort().getLocationId();
			}
			if (vessel.getVesselId() != null) {
				sql += " AND vesselId=" + vessel.getVesselId();
			}
			if (vessel.getVesselCode() != null
					&& !vessel.getVesselCode().equals("")) {
				sql += " AND vesselCode='" + vessel.getVesselCode() + "' ";
			}
			if (vessel.getVesselName() != null
					&& !vessel.getVesselName().equals("")) {
				sql += " AND vesselName='" + vessel.getVesselName() + "' ";
			}

			select(al, sql);
		} catch (Exception e) {
			LogHelper.getInstance().exception(e);
		}
		return al;
	}

	private void select(ArrayList<Vessel> al, String sql) throws Exception,
			SQLException {
		Vessel vessel;
		ResultSet rs = DBManager.getInstance().executeQuery(sql);
		while (rs.next()) {
			vessel = new Vessel();
			rsToVessel(rs, vessel);
			al.add(vessel);
		}
	}

	public VeriSinif next(Long id) {
		// TODO id'den bir sonraki kaydi dondurelim
		return null;
	}

	public VeriSinif previous(Long id) {
		// TODO id'den bir onceki kaydi dondurelim
		return null;
	}

	/**
	 * 
	 * @param officeId
	 * @param isExport
	 *            0 veya 1
	 * @return
	 */
	public ArrayList<Vessel> getAgacVerisi(Long officeId, int isExport) {
		ArrayList<Vessel> al = new ArrayList<Vessel>();
		try {
			String sql = "SELECT * FROM vessel WHERE vesselId IN "
					+ "(SELECT vesselId FROM voyage WHERE export=" + isExport
					+ " AND officeId=" + officeId + ")";
			select(al, sql);
		} catch (Exception e) {
			LogHelper.getInstance().exception(e);
		}
		return al;
	}

}
