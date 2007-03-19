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
import net.kodveus.kumanifest.jdo.Pack;
import net.kodveus.kumanifest.utility.LogHelper;

public class PackOperation implements OperationInterface {

	private static PackOperation instance;

	private PackOperation() {

	}

	public static PackOperation getInstance() {
		if (instance == null) {
			instance = new PackOperation();
		}
		return instance;
	}

	public long create(VeriSinif vs) {
		try {
			Pack pack = (Pack) vs;
			String sql = "INSERT INTO pack(type, description)VALUES('"
					+ pack.getType() + "', '" + pack.getDescription() + "')";
			return DBManager.getInstance().insert(sql);
		} catch (Exception e) {
			LogHelper.getInstance().exception(e);
			return 0;
		}
	}

	public boolean delete(VeriSinif vs) {
		try {
			Pack pack = (Pack) vs;
			String sql = "DELETE FROM pack WHERE packId=" + pack.getPackId();
			return DBManager.getInstance().executeUpdate(sql);
		} catch (Exception e) {
			LogHelper.getInstance().exception(e);
			return false;
		}
	}

	public boolean update(VeriSinif vs) {
		try {
			Pack pack = (Pack) vs;
			String sql = "UPDATE pack SET type = '" + pack.getType()
					+ "',description = '" + pack.getDescription()
					+ "' WHERE packId=" + pack.getPackId();
			return DBManager.getInstance().executeUpdate(sql);
		} catch (Exception e) {
			LogHelper.getInstance().exception(e);
			return false;
		}
	}

	public ArrayList<Pack> ara(VeriSinif vs) {
		ArrayList<Pack> al = new ArrayList<Pack>();
		try {
			Pack pack = (Pack) vs;
			String sql = "SELECT * FROM pack WHERE 1=1";
			if (pack.getType() != null) {
				sql += " AND type='" + pack.getType() + "' ";
			}
			if (pack.getDescription() != null) {
				sql += " AND description='" + pack.getDescription() + "' ";
			}
			if (pack.getPackId() != null) {
				sql += " AND packId=" + pack.getPackId() + " ";
			}
			ResultSet rs = DBManager.getInstance().executeQuery(sql);
			while (rs.next()) {
				pack = new Pack();
				rsToPack(rs, pack);
				al.add(pack);
			}
		} catch (Exception e) {
			LogHelper.getInstance().exception(e);
		}
		return al;
	}

	public VeriSinif get(Long id) {
		Pack pack = new Pack();
		pack.setPackId(id);
		ArrayList<Pack> list = ara(pack);
		if (list.size() > 0) {
			return list.get(0);
		}
		return null;

	}

	public VeriSinif next(Long id) {
		Pack pack = null;
		try {
			String sql = "SELECT * FROM pack WHERE packId>" + id
					+ " ORDER BY packId";
			ResultSet rs = DBManager.getInstance().executeQuery(sql);

			if (rs.next()) {
				// Tek bir kayit dondurecegiz
				pack = new Pack();
				rsToPack(rs, pack);
			}
			return pack;
		} catch (Exception e) {
			LogHelper.getInstance().exception(e);
			pack = null;
		}
		return pack;
	}

	public VeriSinif previous(Long id) {
		Pack pack = null;
		String sql = "SELECT * FROM pack WHERE packId<" + id
				+ " ORDER BY packId DESC";
		try {
			ResultSet rs = DBManager.getInstance().executeQuery(sql);

			if (rs.next()) {
				// Tek bir kayit dondurecegiz
				pack = new Pack();
				rsToPack(rs, pack);
			}
		} catch (Exception e) {
			LogHelper.getInstance().exception(e);
			pack = null;
		}
		return pack;
	}

	private void rsToPack(ResultSet rs, Pack pack) throws Exception {
		pack.setPackId(rs.getLong("packId"));
		pack.setType(rs.getString("type"));
		pack.setDescription(rs.getString("description"));
	}
}
