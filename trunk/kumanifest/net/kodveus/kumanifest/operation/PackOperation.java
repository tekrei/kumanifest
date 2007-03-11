package net.kodveus.kumanifest.operation;

import java.sql.ResultSet;
import java.util.ArrayList;

import net.kodveus.gui.araclar.VeriSinif;
import net.kodveus.kumanifest.database.DBManager;
import net.kodveus.kumanifest.interfaces.OperationInterface;
import net.kodveus.kumanifest.jdo.Pack;

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
			e.printStackTrace();
			return 0;
		}
	}

	public boolean delete(VeriSinif vs) {
		try {
			Pack pack = (Pack) vs;
			String sql = "DELETE FROM pack WHERE packId=" + pack.getPackId();
			return DBManager.getInstance().executeUpdate(sql);
		} catch (Exception e) {
			e.printStackTrace();
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
			e.printStackTrace();
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
			e.printStackTrace();
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
			e.printStackTrace();
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
			e.printStackTrace();
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
