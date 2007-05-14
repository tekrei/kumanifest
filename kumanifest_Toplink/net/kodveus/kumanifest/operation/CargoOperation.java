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
import net.kodveus.kumanifest.jdo.Cargo;
import net.kodveus.kumanifest.utility.LogHelper;

public class CargoOperation extends Operation implements OperationInterface {

	private static CargoOperation instance;

	private CargoOperation() {

	}

	public static CargoOperation getInstance() {
		if (instance == null) {
			instance = new CargoOperation();
		}
		return instance;
	}

	public long create(VeriSinif vs) {
		try {
			Cargo cargo = (Cargo) vs;
			manager.save(cargo);
			return cargo.getCargoId();
		} catch (Exception e) {
			LogHelper.getInstance().exception(e);
			return 0;
		}
	}

	public boolean delete(Long cargoId) {
		Cargo cargo = new Cargo();
		cargo.setCargoId(cargoId);
		return delete(cargo);
	}

	public boolean delete(VeriSinif vs) {
		try {
			return manager.delete((Cargo) vs);
		} catch (Exception e) {
			LogHelper.getInstance().exception(e);
			return false;
		}
	}

	public boolean update(VeriSinif vs) {
		try {
			return manager.update((Cargo) vs);
		} catch (Exception e) {
			LogHelper.getInstance().exception(e);
			return false;
		}
	}

	public VeriSinif get(Long id) {
		Cargo cargo = new Cargo();
		cargo.setCargoId(id);
		return (Cargo) manager.find(Cargo.class, id);
	}

	public ArrayList findAll() {
		return manager.findAll("Cargo");
	}


	public ArrayList cargoOfContainer(Long containerId) {
		String nativeQuery = "SELECT cargo FROM Cargo cargo JOIN cargo.Container c WHERE c.containerId="+containerId;
		return manager.executeQuery(nativeQuery);
	}

	public VeriSinif next(Long id) {
		return super.next(new Cargo(), "cargo", "cargoıId", id);
	}

	public VeriSinif previous(Long id) {
		return super.previous(new Cargo(), "argo", "cargoId", id);
	}
}
