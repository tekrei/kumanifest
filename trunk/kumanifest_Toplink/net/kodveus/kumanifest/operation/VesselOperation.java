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

import java.util.ArrayList;

import net.kodveus.gui.araclar.VeriSinif;
import net.kodveus.kumanifest.interfaces.OperationInterface;
import net.kodveus.kumanifest.jdo.Vessel;
import net.kodveus.kumanifest.utility.LogHelper;

public class VesselOperation extends Operation implements OperationInterface {

	private static VesselOperation instance;

	private VesselOperation() {

	}

	public static VesselOperation getInstance() {
		if (instance == null) {
			instance = new VesselOperation();
		}
		return instance;
	}

	public long create(VeriSinif vs) {
		try {
			Vessel vessel = (Vessel) vs;
			manager.save(vessel);
			return vessel.getVesselId();
		} catch (Exception e) {
			LogHelper.getInstance().exception(e);
			return 0;
		}
	}

	public boolean delete(VeriSinif vs) {
		try {
			Vessel vessel = (Vessel) vs;
			return manager.delete(vessel);
		} catch (Exception e) {
			LogHelper.getInstance().exception(e);
			return false;
		}
	}

	public boolean update(VeriSinif vs) {
		try {
			Vessel vessel = (Vessel) vs;
			return manager.update(vessel);
		} catch (Exception e) {
			LogHelper.getInstance().exception(e);
			return false;
		}
	}

	public VeriSinif get(Long id) {
		return (Vessel) manager.find(Vessel.class, id);
	}

	public ArrayList<Vessel> findAll() {
		return manager.findAll("Vessel");
	}

	public VeriSinif next(Long id) {
		// TODO id'den bir sonraki kaydi dondurelim
		return null;
	}

	public VeriSinif previous(Long id) {
		// TODO id'den bir onceki kaydi dondurelim
		return null;
	}

	/**
	 *
	 * @param officeId
	 * @param isExport
	 *            0 veya 1
	 * @return
	 */
	public ArrayList<Vessel> getAgacVerisi(Long officeId, int isExport) {
		try {
			//TODO sorgu duzeltilecek
			String nativeQuery = "SELECT vessel FROM Vessel vessel WHERE vesselId IN "
					+ "(SELECT vesselId FROM voyage WHERE export=" + isExport
					+ " AND officeId=" + officeId + ")";
			return manager.executeQuery(nativeQuery);
		} catch (Exception e) {
			LogHelper.getInstance().exception(e);
			return new ArrayList<Vessel>();
		}
	}

}
