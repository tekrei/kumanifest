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

import net.kodveus.gui.araclar.VeriSinif;
import net.kodveus.kumanifest.interfaces.OperationInterface;
import net.kodveus.kumanifest.jdo.BL;
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
		try {
			BL bl = (BL) vs;
			manager.save(bl);
			return bl.getBlId();
		} catch (Exception e) {
			LogHelper.getInstance().exception(e);
			return -1;
		}

	}

	public boolean delete(VeriSinif vs) {
		return manager.delete(vs);
	}

	public boolean update(VeriSinif vs) {
		try {
			return manager.update(vs);
		} catch (Exception e) {
			LogHelper.getInstance().exception(e);
			return false;
		}
	}

	public VeriSinif get(Long id) {
		return (BL) manager.find(BL.class, id);
	}

	public java.util.List<BL> findAll() {
		return manager.findAll("BL");
	}

	public java.util.List<BL> blOfVoyages(Long voyageId) {
		String nativeQuery = "SELECT bl FROM BL bl JOIN bl.Voyage v WHERE v.voyageId="
				+ voyageId;
		return manager.executeQuery(nativeQuery);
	}

	public VeriSinif next(Long id) {
		return super.next(new BL(), "bl", "blId", id);
	}

	public VeriSinif previous(Long id) {
		return super.previous(new BL(), "bl", "blId", id);
	}
}
