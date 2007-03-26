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
import net.kodveus.kumanifest.jdo.Commodity;
import net.kodveus.kumanifest.utility.LogHelper;

public class CommodityOperation extends Operation implements OperationInterface {

	private static CommodityOperation instance;

	private CommodityOperation() {

	}

	public static CommodityOperation getInstance() {
		if (instance == null) {
			instance = new CommodityOperation();
		}
		return instance;
	}

	public long create(VeriSinif vs) {
		try {
			Commodity commodity = (Commodity) vs;
			String sql = "INSERT INTO commodity(code, name, description)VALUES('"
					+ commodity.getCode()
					+ "', '"
					+ commodity.getName()
					+ "', '" + commodity.getDescription() + "')";
			return DBManager.getInstance().insert(sql);
		} catch (Exception e) {
			LogHelper.getInstance().exception(e);
			return 0;
		}
	}

	public boolean delete(VeriSinif vs) {
		return delete(((Commodity) vs).getCommodityId());
	}

	public boolean delete(Long id) {
		try {
			String sql = "DELETE FROM commodity WHERE commodityId=" + id;
			return DBManager.getInstance().executeUpdate(sql);
		} catch (Exception e) {
			LogHelper.getInstance().exception(e);
			return false;
		}
	}

	public boolean update(VeriSinif vs) {
		try {
			Commodity commodity = (Commodity) vs;
			String sql = "UPDATE commodity SET code = '" + commodity.getCode()
					+ "',name = '" + commodity.getName() + "',description = '"
					+ commodity.getDescription() + "' WHERE commodityId="
					+ commodity.getCommodityId();
			return DBManager.getInstance().executeUpdate(sql);
		} catch (Exception e) {
			LogHelper.getInstance().exception(e);
			return false;
		}
	}

	@Override
	protected void rsToVs(ResultSet rs, VeriSinif vs) throws Exception {
		Commodity commodity = (Commodity) vs;
		commodity.setCommodityId(rs.getLong("commodityId"));
		commodity.setCode(rs.getString("code"));
		commodity.setName(rs.getString("name"));
		commodity.setDescription(rs.getString("description"));
	}

	public ArrayList<Commodity> ara(VeriSinif vs) {
		ArrayList<Commodity> al = new ArrayList<Commodity>();
		try {
			Commodity commodity = (Commodity) vs;
			String sql = "SELECT * FROM commodity WHERE 1=1";
			if (commodity.getCode() != null) {
				sql += " AND code='" + commodity.getCode() + "' ";
			}
			if (commodity.getName() != null) {
				sql += " AND name='" + commodity.getName() + "' ";
			}
			if (commodity.getDescription() != null) {
				sql += " AND description='" + commodity.getDescription() + "' ";
			}
			if (commodity.getCommodityId() != null) {
				sql += " AND commodityId=" + commodity.getCommodityId() + " ";
			}
			ResultSet rs = DBManager.getInstance().executeQuery(sql);
			while (rs.next()) {
				commodity = new Commodity();
				rsToVs(rs, commodity);
				al.add(commodity);
			}
		} catch (Exception e) {
			LogHelper.getInstance().exception(e);
		}
		return al;
	}

	public VeriSinif get(Long id) {
		Commodity commodity = new Commodity();
		commodity.setCommodityId(id);
		ArrayList<Commodity> list = ara(commodity);
		if (list.size() > 0) {
			return list.get(0);
		}
		return null;

	}

	public VeriSinif next(Long id) {
		Commodity commodity = new Commodity();
		return super.next(commodity, "commodity", "commodityId", id);

	}

	public VeriSinif previous(Long id) {
		Commodity commodity = new Commodity();
		return super.previous(commodity, "commodity", "commodityId", id);
	}
}
