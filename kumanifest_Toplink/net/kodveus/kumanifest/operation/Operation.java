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

import net.kodveus.gui.araclar.VeriSinif;
import net.kodveus.kumanifest.database.DBManager;
import net.kodveus.kumanifest.persistence.PersistenceManager;
import net.kodveus.kumanifest.utility.LogHelper;

public abstract class Operation {

	PersistenceManager manager;

	public Operation() {
		super();
		manager = PersistenceManager.getInstance();
	}

	protected void rsToVs(ResultSet rs, VeriSinif veriSinif){};

	public VeriSinif previous(VeriSinif vs, String table, String field, Long id) {
		String sql = "SELECT * FROM " + table + " WHERE " + field + "<" + id
				+ " ORDER BY " + field + " DESC";
		try {
			ResultSet rs = DBManager.getInstance().executeQuery(sql);
			if (rs.next()) {
				rsToVs(rs, vs);
			}
			return vs;
		} catch (Exception e) {
			LogHelper.getInstance().exception(e);
			return null;
		}
	}

	public VeriSinif next(VeriSinif vs, String table, String field, Long id) {
		try {
			String sql = "SELECT * FROM " + table + " WHERE " + field + ">"
					+ id + " ORDER BY " + field;
			ResultSet rs = DBManager.getInstance().executeQuery(sql);
			if (rs.next()) {
				rsToVs(rs, vs);
			}
			return vs;
		} catch (Exception e) {
			LogHelper.getInstance().exception(e);
			return null;
		}
	}
}