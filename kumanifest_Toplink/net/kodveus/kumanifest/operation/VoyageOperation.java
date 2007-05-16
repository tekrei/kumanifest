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

import net.kodveus.kumanifest.interfaces.OperationInterface;
import net.kodveus.kumanifest.jdo.Voyage;
import net.kodveus.kumanifest.utility.LogHelper;

public class VoyageOperation extends Operation implements OperationInterface {

	private static VoyageOperation instance;

	private VoyageOperation() {

	}

	public static VoyageOperation getInstance() {
		if (instance == null) {
			instance = new VoyageOperation();
		}
		return instance;
	}

	public long create(Object vs) {
		try {
			Voyage voyage = (Voyage) vs;
			manager.save(voyage);
			return voyage.getVoyageId();
		} catch (Exception e) {
			LogHelper.getInstance().exception(e);
			return 0;
		}
	}

	public boolean delete(Object vs) {
		try {
			Voyage voyage = (Voyage) vs;
			return manager.delete(voyage);
		} catch (Exception e) {
			LogHelper.getInstance().exception(e);
			return false;
		}
	}

	public boolean update(Object vs) {
		try {
			Voyage voyage = (Voyage) vs;
			return manager.update(voyage);
		} catch (Exception e) {
			LogHelper.getInstance().exception(e);
			return false;
		}
	}

	public Object get(Long id) {
		return (Voyage) manager.find(Voyage.class, id);
	}

	public ArrayList<Voyage> findAll() {
		return manager.findAll("Voyage");
	}

	public ArrayList<Voyage> getAgacVerisi(Long officeId, Long vesselId,
			int isExport) {
		try {
			return manager.executeNamedQuery("Voyage.treeData", new Object[] {
					isExport, vesselId, officeId });
		} catch (Exception e) {
			LogHelper.getInstance().exception(e);
			return new ArrayList<Voyage>();
		}
	}

	public Object next(Long id) {
		return super.next("Voyage", "voyageId", id);
	}

	public Object previous(Long id) {
		return super.previous("Voyage", "voyageId", id);
	}

}
