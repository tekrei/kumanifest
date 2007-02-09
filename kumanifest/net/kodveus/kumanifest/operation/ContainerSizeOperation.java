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
import net.kodveus.kumanifest.jdo.ContainerSize;

public class ContainerSizeOperation implements OperationInterface {

	private static ContainerSizeOperation instance;

	private ContainerSizeOperation() {

	}

	public static ContainerSizeOperation getInstance() {
		if (instance == null) {
			instance = new ContainerSizeOperation();
		}
		return instance;
	}

	public long create(VeriSinif vs) {
		try {
			ContainerSize containerSize = (ContainerSize) vs;
			String sql = "INSERT INTO containersize(size, description)VALUES('"
					+ containerSize.getSize() + "', '"
					+ containerSize.getDescription() + "')";
			return DBManager.getInstance().insert(sql);
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	public boolean delete(VeriSinif vs) {
		try {
			ContainerSize containerSize = (ContainerSize) vs;
			String sql = "DELETE FROM containersize WHERE containerSizeId="
					+ containerSize.getContainerSizeId();
			return DBManager.getInstance().execute(sql);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean update(VeriSinif vs) {
		try {
			ContainerSize containerSize = (ContainerSize) vs;
			String sql = "UPDATE containersize SET size = '"
					+ containerSize.getSize() + "',description = '"
					+ containerSize.getDescription()
					+ "' WHERE containerSizeId="
					+ containerSize.getContainerSizeId();
			return DBManager.getInstance().execute(sql);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public ArrayList<ContainerSize> ara(VeriSinif vs) {
		ArrayList<ContainerSize> al = new ArrayList<ContainerSize>();
		try {
			ContainerSize containerSize = (ContainerSize) vs;
			String sql = "SELECT * FROM containersize WHERE 1=1";
			if (containerSize.getSize() != null) {
				sql += " AND size='" + containerSize.getSize() + "' ";
			}
			if (containerSize.getDescription() != null) {
				sql += " AND description='" + containerSize.getDescription()
						+ "' ";
			}
			if (containerSize.getContainerSizeId() != null) {
				sql += " AND containerSizeId="
						+ containerSize.getContainerSizeId() + " ";
			}
			ResultSet rs = DBManager.getInstance().executeQuery(sql);
			while (rs.next()) {
				containerSize = new ContainerSize();
				rsToContainerSize(rs, containerSize);
				al.add(containerSize);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return al;
	}

	public VeriSinif get(Long id) {
		ContainerSize containerSize = new ContainerSize();
		containerSize.setContainerSizeId(id);
		ArrayList<ContainerSize> list = ara(containerSize);
		if (list.size() > 0) {
			return list.get(0);
		}
		return null;

	}

	public VeriSinif next(Long id) {
		ContainerSize containerSize = null;
		try {
			String sql = "SELECT * FROM containersize WHERE containerSizeId>"
					+ id + " ORDER BY containerSizeId";
			ResultSet rs = DBManager.getInstance().executeQuery(sql);

			if (rs.next()) {
				// Tek bir kayit dondurecegiz
				containerSize = new ContainerSize();
				rsToContainerSize(rs, containerSize);
			}
			return containerSize;
		} catch (Exception e) {
			e.printStackTrace();
			containerSize = null;
		}
		return containerSize;
	}

	public VeriSinif previous(Long id) {
		ContainerSize containerSize = null;
		String sql = "SELECT * FROM containersize WHERE containerSizeId<" + id
				+ " ORDER BY containerSizeId DESC";
		try {
			ResultSet rs = DBManager.getInstance().executeQuery(sql);

			if (rs.next()) {
				// Tek bir kayit dondurecegiz
				containerSize = new ContainerSize();
				rsToContainerSize(rs, containerSize);
			}
		} catch (Exception e) {
			e.printStackTrace();
			containerSize = null;
		}
		return containerSize;
	}

	private void rsToContainerSize(ResultSet rs, ContainerSize containerSize)
			throws Exception {
		containerSize.setContainerSizeId(rs.getLong("containerSizeId"));
		containerSize.setSize(rs.getString("size"));
		containerSize.setDescription(rs.getString("description"));
	}
}
