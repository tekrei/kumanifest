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
import net.kodveus.kumanifest.jdo.ContainerSize;
import net.kodveus.kumanifest.utility.LogHelper;

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
			LogHelper.getInstance().exception(e);
			return 0;
		}
	}

	public boolean delete(VeriSinif vs) {
		try {
			ContainerSize containerSize = (ContainerSize) vs;
			String sql = "DELETE FROM containersize WHERE containerSizeId="
					+ containerSize.getContainerSizeId();
			return DBManager.getInstance().executeUpdate(sql);
		} catch (Exception e) {
			LogHelper.getInstance().exception(e);
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
			return DBManager.getInstance().executeUpdate(sql);
		} catch (Exception e) {
			LogHelper.getInstance().exception(e);
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
			LogHelper.getInstance().exception(e);
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
			LogHelper.getInstance().exception(e);
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
			LogHelper.getInstance().exception(e);
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
