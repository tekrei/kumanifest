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
import net.kodveus.kumanifest.jdo.Office;
import net.kodveus.kumanifest.utility.LogHelper;

public class OfficeOperation implements OperationInterface {

	private static OfficeOperation instance;

	private OfficeOperation() {

	}

	public static OfficeOperation getInstance() {
		if (instance == null) {
			instance = new OfficeOperation();
		}
		return instance;
	}

	public long create(VeriSinif vs) {
		try {
			Office office = (Office) vs;
			String sql = "INSERT INTO office(office, description, code)VALUES('"
					+ office.getOffice()
					+ "', '"
					+ office.getDescription()
					+ "', '" + office.getCode() + "')";
			return DBManager.getInstance().insert(sql);
		} catch (Exception e) {
			LogHelper.getInstance().exception(e);
			return 0;
		}
	}

	public boolean delete(VeriSinif vs) {
		try {
			Office office = (Office) vs;
			String sql = "DELETE FROM office WHERE officeId="
					+ office.getOfficeId();
			return DBManager.getInstance().executeUpdate(sql);
		} catch (Exception e) {
			LogHelper.getInstance().exception(e);
			return false;
		}
	}

	public boolean update(VeriSinif vs) {
		try {
			Office office = (Office) vs;
			String sql = "UPDATE office SET office = '" + office.getOffice()
					+ "',description = '" + office.getDescription()
					+ "',code = '" + office.getCode() + "' WHERE officeId="
					+ office.getOfficeId();
			return DBManager.getInstance().executeUpdate(sql);
		} catch (Exception e) {
			LogHelper.getInstance().exception(e);
			return false;
		}
	}

	public ArrayList<Office> ara(VeriSinif vs) {
		ArrayList<Office> al = new ArrayList<Office>();
		try {
			Office office = (Office) vs;
			String sql = "SELECT * FROM office WHERE 1=1";
			if (office.getOffice() != null) {
				sql += " AND office='" + office.getOffice() + "' ";
			}
			if (office.getDescription() != null) {
				sql += " AND description='" + office.getDescription() + "' ";
			}
			if (office.getCode() != null) {
				sql += " AND code='" + office.getCode() + "' ";
			}
			if (office.getOfficeId() != null) {
				sql += " AND officeId=" + office.getOfficeId() + " ";
			}
			ResultSet rs = DBManager.getInstance().executeQuery(sql);
			while (rs.next()) {
				office = new Office();
				rsToOffice(rs, office);
				al.add(office);
			}
		} catch (Exception e) {
			LogHelper.getInstance().exception(e);
		}
		return al;
	}

	public VeriSinif get(Long id) {
		Office office = new Office();
		office.setOfficeId(id);
		ArrayList<Office> list = ara(office);
		if (list.size() > 0) {
			return list.get(0);
		}
		return null;

	}

	public VeriSinif next(Long id) {
		Office office = null;
		try {
			String sql = "SELECT * FROM office WHERE officeId>" + id
					+ " ORDER BY officeId";
			ResultSet rs = DBManager.getInstance().executeQuery(sql);

			if (rs.next()) {
				// Tek bir kayit dondurecegiz
				office = new Office();
				rsToOffice(rs, office);
			}
			return office;
		} catch (Exception e) {
			LogHelper.getInstance().exception(e);
			office = null;
		}
		return office;
	}

	public VeriSinif previous(Long id) {
		Office office = null;
		String sql = "SELECT * FROM office WHERE officeId<" + id
				+ " ORDER BY officeId DESC";
		try {
			ResultSet rs = DBManager.getInstance().executeQuery(sql);

			if (rs.next()) {
				// Tek bir kayit dondurecegiz
				office = new Office();
				rsToOffice(rs, office);
			}
		} catch (Exception e) {
			LogHelper.getInstance().exception(e);
			office = null;
		}
		return office;
	}

	private void rsToOffice(ResultSet rs, Office office) throws Exception {
		office.setOfficeId(rs.getLong("officeId"));
		office.setOffice(rs.getString("office"));
		office.setDescription(rs.getString("description"));
		office.setCode(rs.getString("code"));
	}
}
