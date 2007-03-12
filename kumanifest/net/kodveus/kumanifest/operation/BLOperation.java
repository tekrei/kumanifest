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
import java.sql.SQLException;
import java.util.ArrayList;

import net.kodveus.gui.araclar.VeriSinif;
import net.kodveus.kumanifest.database.DBManager;
import net.kodveus.kumanifest.interfaces.OperationInterface;
import net.kodveus.kumanifest.jdo.BL;
import net.kodveus.kumanifest.jdo.Location;
import net.kodveus.kumanifest.jdo.Voyage;
import net.kodveus.kumanifest.utility.LogHelper;

public class BLOperation extends Operation implements OperationInterface {

	private static BLOperation instance;

	private BLOperation() {

	}

	public static BLOperation getInstance() {
		if (instance == null) {
			instance = new BLOperation();
		}
		return instance;
	}

	public long create(VeriSinif vs) {
		BL bl = (BL) vs;
		// BL kaydini veritabanina ekleyelim
		String sql = "INSERT INTO bl (voyageId, blNo, companyName, status, reportType, placeOfOrigin, placeOfReceipt,"
				+ "portOfLoading, portOfDischarge, finalDischargePlace, finalDestination, description,"
				+ "description2, shipper, consignee, notify, notify2) VALUES(";

		sql += bl.getVoyage().getVoyageId() + ",'" + bl.getBlNo() + "','"
				+ bl.getCompanyName() + "'," + bl.getStatus() + ","
				+ bl.getReportType() + ","
				+ bl.getPlaceOfOrigin().getLocationId() + ","
				+ bl.getPlaceOfReceipt().getLocationId() + ","
				+ bl.getPortOfLoading().getLocationId() + ","
				+ bl.getPortOfDischarge().getLocationId() + ","
				+ bl.getFinalDischargePlace().getLocationId() + ","
				+ bl.getFinalDestination().getLocationId() + ",'"
				+ bl.getDescription() + "','" + bl.getDescription2() + "','"
				+ bl.getShipper() + "','" + bl.getConsignee() + "','"
				+ bl.getNotify() + "','" + bl.getNotify2() + "')";

		try {
			return DBManager.getInstance().insert(sql);
		} catch (Exception e) {
			LogHelper.getInstance().exception(e);
			return 0;
		}

	}

	/**
	 * Silmek yerine status sahasini guncellemeliyiz
	 */
	public boolean delete(VeriSinif vs) {
		BL bl = (BL) vs;
		return delete(bl.getBlId());

	}

	public boolean delete(Long id) {
		String sql = "DELETE FROM bl WHERE blId=" + id;

		try {
			return DBManager.getInstance().executeUpdate(sql);

		} catch (Exception e) {
			LogHelper.getInstance().exception(e);
			return false;
		}
	}

	@Override
	protected void rsToVs(ResultSet rs, VeriSinif vs) throws SQLException {
		BL bl = (BL) vs;
		bl.setBlId(rs.getLong("blId"));
		bl.setBlNo(rs.getString("blNo"));
		bl.setCompanyName(rs.getString("companyName"));
		bl.setConsignee(rs.getString("consignee"));
		bl.setDescription(rs.getString("description"));
		bl.setDescription2(rs.getString("description2"));
		bl.setFinalDestination((Location) LocationOperation.getInstance().get(
				rs.getLong("finalDestination")));
		bl.setFinalDischargePlace((Location) LocationOperation.getInstance()
				.get(rs.getLong("finalDischargePlace")));
		bl.setNotify(rs.getString("notify"));
		bl.setNotify2(rs.getString("notify2"));
		bl.setReportType(rs.getLong("reportType"));
		bl.setPlaceOfOrigin((Location) LocationOperation.getInstance().get(
				rs.getLong("placeOfOrigin")));
		bl.setPlaceOfReceipt((Location) LocationOperation.getInstance().get(
				rs.getLong("placeOfReceipt")));
		bl.setPortOfDischarge((Location) LocationOperation.getInstance().get(
				rs.getLong("portOfDischarge")));
		bl.setPortOfLoading((Location) LocationOperation.getInstance().get(
				rs.getLong("portOfLoading")));
		bl.setShipper(rs.getString("shipper"));
		bl.setVoyage((Voyage) VoyageOperation.getInstance().get(
				rs.getLong("voyageId")));
	}

	public boolean update(VeriSinif vs) {
		BL bl = (BL) vs;
		String sql = "UPDATE bl SET";
		sql += " voyageId = " + bl.getVoyage().getVoyageId() + ",blNo = "
				+ bl.getBlNo() + ",companyName = '" + bl.getCompanyName()
				+ "'," + "status = " + bl.getStatus() + ", reportType = "
				+ bl.getReportType() + ",placeOfOrigin = "
				+ bl.getPlaceOfOrigin().getLocationId() + ",placeOfReceipt = "
				+ bl.getPlaceOfReceipt().getLocationId() + ",portOfLoading = "
				+ bl.getPortOfLoading().getLocationId() + ",portOfDischarge = "
				+ bl.getPortOfDischarge().getLocationId()
				+ ",finalDischargePlace = "
				+ bl.getFinalDischargePlace().getLocationId()
				+ ",finalDestination = "
				+ bl.getFinalDestination().getLocationId() + ",description = '"
				+ bl.getDescription() + "',description2 = '"
				+ bl.getDescription2() + "',shipper = '" + bl.getShipper()
				+ "'," + "consignee = '" + bl.getConsignee() + "',notify = '"
				+ bl.getNotify() + "',notify2 = '" + bl.getNotify2()
				+ "' WHERE blId = " + bl.getBlId();

		try {
			return DBManager.getInstance().executeUpdate(sql);
		} catch (Exception e) {
			LogHelper.getInstance().exception(e);
			return false;
		}
	}

	public VeriSinif get(Long id) {
		BL bl = new BL();
		bl.setBlId(id);
		ArrayList<BL> list = ara(bl);
		if (list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	public ArrayList<BL> ara(VeriSinif vs) {
		ArrayList<BL> al = new ArrayList<BL>();
		try {
			BL bl = (BL) vs;
			String sql = "SELECT * FROM bl WHERE 1=1";

			if (bl.getBlId() != null) {
				sql += " AND blId=" + bl.getBlId();
			}
			if (bl.getBlNo() != null && !bl.getBlNo().equals("")) {
				sql += " AND blNo='" + bl.getBlNo() + "'";
			}
			if (bl.getCompanyName() != null && !bl.getCompanyName().equals("")) {
				sql += " AND companyName='" + bl.getCompanyName() + "'";
			}
			if (bl.getConsignee() != null && !bl.getConsignee().equals("")) {
				sql += " AND consignee='" + bl.getConsignee() + "'";
			}
			if (bl.getDescription() != null && !bl.getDescription().equals("")) {
				sql += " AND description='" + bl.getDescription() + "'";
			}
			if (bl.getDescription2() != null
					&& !bl.getDescription2().equals("")) {
				sql += " AND description2='" + bl.getDescription2() + "'";
			}
			if (bl.getFinalDestination() != null) {
				sql += " AND finalDestination="
						+ bl.getFinalDestination().getLocationId();
			}
			if (bl.getFinalDischargePlace() != null) {
				sql += " AND finalDischargePlace="
						+ bl.getFinalDischargePlace().getLocationId();
			}
			if (bl.getNotify() != null && !bl.getNotify().equals("")) {
				sql += " AND notify='" + bl.getNotify() + "'";
			}
			if (bl.getNotify2() != null && !bl.getNotify2().equals("")) {
				sql += " AND notify2='" + bl.getNotify2() + "'";
			}
			if (bl.getPlaceOfOrigin() != null) {
				sql += " AND placeOfOrigin="
						+ bl.getPlaceOfOrigin().getLocationId();
			}
			if (bl.getPlaceOfReceipt() != null) {
				sql += " AND placeOfReceipt="
						+ bl.getPlaceOfReceipt().getLocationId();
			}
			if (bl.getPortOfDischarge() != null) {
				sql += " AND portOfDischarge="
						+ bl.getPortOfDischarge().getLocationId();
			}
			if (bl.getPortOfLoading() != null) {
				sql += " AND portOfLoading="
						+ bl.getPortOfLoading().getLocationId();
			}
			if (bl.getReportType() != null) {
				sql += " AND reportType=" + bl.getReportType();
			}
			if (bl.getShipper() != null && !bl.getShipper().equals("")) {
				sql += " AND shipper='" + bl.getShipper() + "'";
			}
			if (bl.getStatus() != null) {
				sql += " AND status=" + bl.getStatus();
			}
			if (bl.getVoyage() != null) {
				sql += " AND voyageId=" + bl.getVoyage().getVoyageId();
			}

			select(al, sql);
		} catch (Exception e) {
			LogHelper.getInstance().exception(e);
		}
		return al;
	}

	private void select(ArrayList<BL> al, String sql) throws Exception,
			SQLException {
		BL bl;
		ResultSet rs = DBManager.getInstance().executeQuery(sql);
		while (rs.next()) {
			bl = new BL();
			rsToVs(rs, bl);
			al.add(bl);
		}
	}

	public VeriSinif next(Long id) {
		return super.next(new BL(), "bl", "blId", id);
	}

	public VeriSinif previous(Long id) {
		return super.previous(new BL(), "bl", "blId", id);
	}
}
