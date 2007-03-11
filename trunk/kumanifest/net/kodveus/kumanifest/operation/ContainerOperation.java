package net.kodveus.kumanifest.operation;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import net.kodveus.gui.araclar.VeriSinif;
import net.kodveus.kumanifest.database.DBManager;
import net.kodveus.kumanifest.interfaces.OperationInterface;
import net.kodveus.kumanifest.jdo.Container;
import net.kodveus.kumanifest.jdo.ContainerSize;
import net.kodveus.kumanifest.jdo.ContainerType;
import net.kodveus.kumanifest.utility.LogHelper;

public class ContainerOperation implements OperationInterface {

	private static ContainerOperation instance;

	private ContainerOperation() {

	}

	public static ContainerOperation getInstance() {
		if (instance == null) {
			instance = new ContainerOperation();
		}
		return instance;
	}

	public long create(VeriSinif vs) {
		try {
			Container container = (Container) vs;
			String sql = "INSERT INTO container"
					+ "(relCom, containerNo, blId, containerType, sealNo, "
					+ "otherSealNo, containerSize, taraWeight, status)VALUES(";

			sql += container.getRelCom() + ",'" + container.getContainerNo()
					+ "'," + container.getBlId() + ","
					+ container.getContainerType().getContainerTypeId() + ",'"
					+ container.getSealNo() + "','"
					+ container.getOtherSealNo() + "',"
					+ container.getContainerSize().getContainerSizeId() + ","
					+ container.getTaraWeight() + "," + container.getStatus()
					+ ")";

			return DBManager.getInstance().insert(sql);
		} catch (Exception e) {
			LogHelper.getInstance().istisna(e);
			return 0;
		}

	}

	public boolean delete(VeriSinif vs) {
		Container container = (Container) vs;
		String sql = "DELETE FROM container WHERE containerId="
				+ container.getContainerId();
		try {
			return DBManager.getInstance().executeUpdate(sql);
		} catch (Exception e) {
			LogHelper.getInstance().istisna(e);
			return false;
		}
	}

	public boolean update(VeriSinif vs) {
		Container container = (Container) vs;
		String sql = "UPDATE container SET" + " containerId = "
				+ container.getContainerId() + "," + " relCom = "
				+ container.getRelCom() + "," + " containerNo = '"
				+ container.getContainerNo() + "'," + " blId = "
				+ container.getBlId() + "," + " containerType = "
				+ container.getContainerType().getContainerTypeId() + ","
				+ " sealNo = '" + container.getSealNo() + "', "
				+ " otherSealNo = '" + container.getOtherSealNo() + "', "
				+ " containerSize = "
				+ container.getContainerSize().getContainerSizeId() + ","
				+ " status = " + container.getStatus() + "," + " taraWeight = "
				+ container.getTaraWeight() + " WHERE " + "containerId = "
				+ container.getContainerId();
		try {
			return DBManager.getInstance().executeUpdate(sql);
		} catch (Exception e) {
			LogHelper.getInstance().istisna(e);
			return false;
		}
	}

	public VeriSinif get(Long id) {
		Container container = new Container();
		container.setContainerId(id);
		ArrayList<Container> list = ara(container);
		if (list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	protected void rsToContainer(ResultSet rs, Container container)
			throws SQLException {
		container.setBlId(rs.getLong("blId"));
		container.setContainerNo(rs.getString("containerNo"));
		container.setContainerId(rs.getLong("containerId"));
		container.setContainerSize((ContainerSize) ContainerSizeOperation
				.getInstance().get(rs.getLong("containerSize")));
		container.setContainerType((ContainerType) ContainerTypeOperation
				.getInstance().get(rs.getLong("containerType")));
		container.setOtherSealNo(rs.getString("otherSealNo"));
		container.setSealNo(rs.getString("sealNo"));
		container.setRelCom(rs.getLong("relcom"));
		container.setStatus(rs.getLong("status"));
		container.setTaraWeight(rs.getDouble("taraWeight"));
	}

	private void select(ArrayList<Container> al, String sql) throws Exception,
			SQLException {
		Container container;
		ResultSet rs = DBManager.getInstance().executeQuery(sql);
		while (rs.next()) {
			container = new Container();
			rsToContainer(rs, container);
			al.add(container);
		}
	}

	public ArrayList<Container> ara(VeriSinif vs) {
		ArrayList<Container> al = new ArrayList<Container>();
		try {
			Container container = (Container) vs;
			String sql = "SELECT * FROM container WHERE 1=1";

			if (container.getBlId() != null) {
				sql += " AND blId=" + container.getBlId();
			}
			if (container.getContainerId() != null) {
				sql += " AND containerId=" + container.getContainerId();
			}
			if (container.getContainerNo() != null) {
				sql += " AND containerNo='" + container.getContainerNo() + "'";
			}
			if (container.getContainerSize() != null) {
				sql += " AND containerSize="
						+ container.getContainerSize().getContainerSizeId();
			}
			if (container.getContainerType() != null) {
				sql += " AND containerType="
						+ container.getContainerType().getContainerTypeId();
			}
			if (container.getOtherSealNo() != null) {
				sql += " AND otherSealNo='" + container.getOtherSealNo() + "'";
			}
			if (container.getRelCom() != null) {
				sql += " AND relCom=" + container.getRelCom();
			}
			if (container.getSealNo() != null) {
				sql += " AND sealNo='" + container.getSealNo() + "'";
			}
			if (container.getStatus() != null) {
				sql += " AND status=" + container.getStatus();
			}
			if (container.getTaraWeight() != null) {
				sql += " AND taraWeight=" + container.getTaraWeight();
			}
			select(al, sql);
		} catch (Exception e) {
			LogHelper.getInstance().istisna(e);
		}
		return al;
	}

	public VeriSinif next(Long id) {
		Container container = null;
		String sql = "SELECT * FROM container WHERE containerId>" + id
				+ " ORDER BY containerId";
		try {
			ResultSet rs = DBManager.getInstance().executeQuery(sql);

			if (rs.next()) {
				// Tek bir kayit dondurecegiz
				container = new Container();
				rsToContainer(rs, container);
			}
		} catch (Exception e) {
			LogHelper.getInstance().istisna(e);
			container = null;
		}
		return container;
	}

	public VeriSinif previous(Long id) {
		Container container = null;
		String sql = "SELECT * FROM container WHERE containerId<" + id
				+ " ORDER BY containerId DESC";
		try {
			ResultSet rs = DBManager.getInstance().executeQuery(sql);

			if (rs.next()) {
				// Tek bir kayit dondurecegiz
				container = new Container();
				rsToContainer(rs, container);
			}
		} catch (Exception e) {
			LogHelper.getInstance().istisna(e);
			container = null;
		}
		return container;
	}
}