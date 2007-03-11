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
import net.kodveus.kumanifest.jdo.ContainerType;
import net.kodveus.kumanifest.utility.LogHelper;

public class ContainerTypeOperation implements OperationInterface {

	private static ContainerTypeOperation instance;

	private ContainerTypeOperation() {

	}

	public static ContainerTypeOperation getInstance() {
		if (instance == null) {
			instance = new ContainerTypeOperation();
		}
		return instance;
	}

	public long create(VeriSinif vs) {
		try {
			ContainerType containerType = (ContainerType) vs;
			String sql = "INSERT INTO containertype(type, description)VALUES('"
					+ containerType.getType() + "', '"
					+ containerType.getDescription() + "')";
			return DBManager.getInstance().insert(sql);
		} catch (Exception e) {
			LogHelper.getInstance().istisna(e);
			return 0;
		}
	}

	public boolean delete(VeriSinif vs) {
		try {
			ContainerType containerType = (ContainerType) vs;
			String sql = "DELETE FROM containertype WHERE containerTypeId="
					+ containerType.getContainerTypeId();
			return DBManager.getInstance().executeUpdate(sql);
		} catch (Exception e) {
			LogHelper.getInstance().istisna(e);
			return false;
		}
	}

	public boolean update(VeriSinif vs) {
		try {
			ContainerType containerType = (ContainerType) vs;
			String sql = "UPDATE containertype SET type = '"
					+ containerType.getType() + "',description = '"
					+ containerType.getDescription()
					+ "' WHERE containerTypeId="
					+ containerType.getContainerTypeId();
			return DBManager.getInstance().executeUpdate(sql);
		} catch (Exception e) {
			LogHelper.getInstance().istisna(e);
			return false;
		}
	}

	public ArrayList<ContainerType> ara(VeriSinif vs) {
		ArrayList<ContainerType> al = new ArrayList<ContainerType>();
		try {
			ContainerType containerType = (ContainerType) vs;
			String sql = "SELECT * FROM containertype WHERE 1=1";
			if (containerType.getType() != null) {
				sql += " AND type='" + containerType.getType() + "' ";
			}
			if (containerType.getDescription() != null) {
				sql += " AND description='" + containerType.getDescription()
						+ "' ";
			}
			if (containerType.getContainerTypeId() != null) {
				sql += " AND containerTypeId="
						+ containerType.getContainerTypeId() + " ";
			}
			ResultSet rs = DBManager.getInstance().executeQuery(sql);
			while (rs.next()) {
				containerType = new ContainerType();
				rsToContainerType(rs, containerType);
				al.add(containerType);
			}
		} catch (Exception e) {
			LogHelper.getInstance().istisna(e);
		}
		return al;
	}

	public VeriSinif get(Long id) {
		ContainerType containerType = new ContainerType();
		containerType.setContainerTypeId(id);
		ArrayList<ContainerType> list = ara(containerType);
		if (list.size() > 0) {
			return list.get(0);
		}
		return null;

	}

	public VeriSinif next(Long id) {
		ContainerType containerType = null;
		try {
			String sql = "SELECT * FROM containertype WHERE containerTypeId>"
					+ id + " ORDER BY containerTypeId";
			ResultSet rs = DBManager.getInstance().executeQuery(sql);

			if (rs.next()) {
				// Tek bir kayit dondurecegiz
				containerType = new ContainerType();
				rsToContainerType(rs, containerType);
			}
			return containerType;
		} catch (Exception e) {
			LogHelper.getInstance().istisna(e);
			containerType = null;
		}
		return containerType;
	}

	public VeriSinif previous(Long id) {
		ContainerType containerType = null;
		String sql = "SELECT * FROM containertype WHERE containerTypeId<" + id
				+ " ORDER BY containerTypeId DESC";
		try {
			ResultSet rs = DBManager.getInstance().executeQuery(sql);

			if (rs.next()) {
				// Tek bir kayit dondurecegiz
				containerType = new ContainerType();
				rsToContainerType(rs, containerType);
			}
		} catch (Exception e) {
			LogHelper.getInstance().istisna(e);
			containerType = null;
		}
		return containerType;
	}

	private void rsToContainerType(ResultSet rs, ContainerType containerType)
			throws Exception {
		containerType.setContainerTypeId(rs.getLong("containerTypeId"));
		containerType.setType(rs.getString("type"));
		containerType.setDescription(rs.getString("description"));
	}
}
