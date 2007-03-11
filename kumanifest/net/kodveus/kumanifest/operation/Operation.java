package net.kodveus.kumanifest.operation;

import java.sql.ResultSet;

import net.kodveus.gui.araclar.VeriSinif;
import net.kodveus.kumanifest.database.DBManager;
import net.kodveus.kumanifest.utility.LogHelper;

public abstract class Operation {

	public Operation() {
		super();
	}

	protected abstract void rsToVs(ResultSet rs, VeriSinif veriSinif)
			throws Exception;

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
			LogHelper.getInstance().istisna(e);
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
			LogHelper.getInstance().istisna(e);
			return null;
		}
	}
}