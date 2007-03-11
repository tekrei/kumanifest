package net.kodveus.kumanifest.operation;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import net.kodveus.gui.araclar.VeriSinif;
import net.kodveus.kumanifest.database.DBManager;
import net.kodveus.kumanifest.interfaces.OperationInterface;
import net.kodveus.kumanifest.jdo.Location;
import net.kodveus.kumanifest.jdo.Office;
import net.kodveus.kumanifest.jdo.Vessel;
import net.kodveus.kumanifest.jdo.Voyage;
import net.kodveus.kumanifest.utility.FormatHelper;
import net.kodveus.kumanifest.utility.LogHelper;

public class VoyageOperation implements OperationInterface {

	private static VoyageOperation instance;

	private VoyageOperation() {

	}

	public static VoyageOperation getInstance() {
		if (instance == null) {
			instance = new VoyageOperation();
		}
		return instance;
	}

	public long create(VeriSinif vs) {
		try {
			Voyage voyage = (Voyage) vs;
			String sql = "INSERT INTO voyage"
					+ "(voyage, export, vesselId, officeId,"
					+ " status, firstLeavedPort,lastLeavedPort, arrivalDate, "
					+ "departureDate,nameOfCaptain)VALUES(" + "'"
					+ voyage.getVoyage() + "'," + voyage.getExport() + ","
					+ voyage.getVessel().getVesselId() + ","
					+ voyage.getOffice().getOfficeId() + ","
					+ voyage.getStatus() + ","
					+ voyage.getFirstLeavedPort().getLocationId() + ","
					+ voyage.getLastLeavedPort().getLocationId() + ",'"
					+ FormatHelper.convertDate(voyage.getArrivalDate()) + "','"
					+ FormatHelper.convertDate(voyage.getDepartureDate())
					+ "','" + voyage.getNameOfCaptain() + "')";
			return DBManager.getInstance().insert(sql);
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	public boolean delete(VeriSinif vs) {
		Voyage voyage = (Voyage) vs;
		String sql = "DELETE FROM voyage WHERE voyageId="
				+ voyage.getVoyageId();
		try {
			return DBManager.getInstance().executeUpdate(sql);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean update(VeriSinif vs) {
		// TODO Buralarda null kontrolu yapsak iyi olmaz mi?
		Voyage voyage = (Voyage) vs;
		String sql = "UPDATE voyage SET" + " voyage = '" + voyage.getVoyage()
				+ "'," + " export = " + voyage.getExport() + ", "
				+ " vesselId = " + voyage.getVessel().getVesselId() + ","
				+ " officeId = " + voyage.getOffice().getOfficeId() + ", "
				+ " status = " + voyage.getStatus() + ", "
				+ " firstLeavedPort = "
				+ voyage.getFirstLeavedPort().getLocationId() + ","
				+ " lastLeavedPort = "
				+ voyage.getLastLeavedPort().getLocationId() + ", "
				+ " arrivalDate = " + voyage.getArrivalDate() + ","
				+ " departureDate = " + voyage.getDepartureDate() + ","
				+ " nameOfCaptain = '" + voyage.getNameOfCaptain() + "'"
				+ " WHERE voyageId =" + voyage.getVoyageId();
		try {
			return DBManager.getInstance().executeUpdate(sql);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public VeriSinif get(Long id) {
		Voyage voyage = new Voyage();
		voyage.setVoyageId(id);
		ArrayList<Voyage> list = ara(voyage);
		if (list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	private void rsToVoyage(ResultSet rs, Voyage voyage) throws Exception {
		voyage.setVoyageId(rs.getLong("voyageId"));
		voyage.setArrivalDate(rs.getDate("arrivalDate"));
		voyage.setDepartureDate(rs.getDate("departureDate"));
		voyage.setExport(rs.getLong("export"));
		voyage.setFirstLeavedPort((Location) LocationOperation.getInstance()
				.get(rs.getLong("firstLeavedPort")));
		voyage.setLastLeavedPort((Location) LocationOperation.getInstance()
				.get(rs.getLong("lastLeavedPort")));
		voyage.setNameOfCaptain(rs.getString("nameOfCaptain"));
		voyage.setOffice((Office) OfficeOperation.getInstance().get(
				rs.getLong("officeId")));
		voyage.setStatus(rs.getLong("status"));
		voyage.setVessel((Vessel) VesselOperation.getInstance().get(
				rs.getLong("vesselId")));
		voyage.setVoyage(rs.getString("voyage"));
	}

	public ArrayList<Voyage> ara(VeriSinif vs) {
		ArrayList<Voyage> al = new ArrayList<Voyage>();
		try {
			Voyage voyage = (Voyage) vs;
			String sql = "SELECT * FROM voyage WHERE 1=1";
			if (voyage.getVoyage() != null && !voyage.getVoyage().equals("")) {
				sql += " AND voyage='" + voyage.getVoyage() + "' ";
			}
			if (voyage.getNameOfCaptain() != null
					&& !voyage.getNameOfCaptain().equals("")) {
				sql += " AND nameOfCaptain='" + voyage.getNameOfCaptain()
						+ "' ";
			}
			if (voyage.getOffice() != null
					&& voyage.getOffice().getOfficeId() != null) {
				sql += " AND officeId=" + voyage.getOffice().getOfficeId();
			}
			if (voyage.getVessel() != null
					&& voyage.getVessel().getVesselId() != null) {
				sql += " AND vesselId=" + voyage.getVessel().getVesselId();
			}
			if (voyage.getFirstLeavedPort() != null
					&& voyage.getFirstLeavedPort().getLocationId() != null) {
				sql += " AND firstLeavedPort="
						+ voyage.getFirstLeavedPort().getLocationId();
			}
			if (voyage.getLastLeavedPort() != null
					&& voyage.getLastLeavedPort().getLocationId() != null) {
				sql += " AND lastLeavedPort="
						+ voyage.getLastLeavedPort().getLocationId();
			}
			if (voyage.getVoyageId() != null) {
				sql += " AND voyageId=" + voyage.getVoyageId();
			}
			if (voyage.getStatus() != null) {
				sql += " AND status=" + voyage.getStatus();
			}
			if (voyage.getExport() != null) {
				sql += " AND export=" + voyage.getExport();
			}
			if (voyage.getArrivalDate() != null) {
				sql += " AND arrivalDate=" + voyage.getArrivalDate();
			}
			if (voyage.getDepartureDate() != null) {
				sql += " AND departureDate=" + voyage.getDepartureDate();
			}

			select(al, sql);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return al;
	}

	private void select(ArrayList<Voyage> al, String sql) throws Exception,
			SQLException {
		Voyage voyage;
		ResultSet rs = DBManager.getInstance().executeQuery(sql);
		while (rs.next()) {
			voyage = new Voyage();
			rsToVoyage(rs, voyage);
			al.add(voyage);
		}
	}

	/**
	 * 
	 * @param officeId
	 * @param vesselId
	 * @param isExport
	 *            0 veya 1
	 * @return
	 */
	public ArrayList<Voyage> getAgacVerisi(Long officeId, Long vesselId,
			int isExport) {
		ArrayList<Voyage> al = new ArrayList<Voyage>();
		try {
			String sql = "SELECT * FROM voyage WHERE export=" + isExport
					+ " AND vesselId=" + vesselId + " AND officeId=" + officeId;
			select(al, sql);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return al;
	}

	public VeriSinif next(Long id) {
		// TODO id'den bir sonraki kaydi dondurelim
		return null;
	}

	public VeriSinif previous(Long id) {
		// TODO id'den bir onceki kaydi dondurelim
		return null;
	}

}
