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
import net.kodveus.kumanifest.jdo.Cargo;
import net.kodveus.kumanifest.utility.LogHelper;

public class CargoOperation extends Operation implements OperationInterface {

	private static CargoOperation instance;

	public static CargoOperation getInstance() {
		if (instance == null) {
			instance = new CargoOperation();
		}
		return instance;
	}

	private CargoOperation() {

	}

	public ArrayList<?> cargoOfContainer(final Long containerId) {
		/*
		 * String nativeQuery = "SELECT cargo FROM Cargo cargo JOIN
		 * cargo.Container c WHERE c.containerId="+containerId; return
		 * manager.executeQuery(nativeQuery);
		 */
		return manager.executeNamedQuery("Cargo.ofContainer",
				new Object[] { containerId });
	}

	public long create(final Object vs) {
		try {
			final Cargo cargo = (Cargo) vs;
			manager.save(cargo);
			return cargo.getCargoId();
		} catch (final Exception e) {
			LogHelper.getInstance().exception(e);
			return 0;
		}
	}

	public boolean delete(final Long cargoId) {
		final Cargo cargo = new Cargo();
		cargo.setCargoId(cargoId);
		return delete(cargo);
	}

	public boolean delete(final Object vs) {
		try {
			return manager.delete(vs);
		} catch (final Exception e) {
			LogHelper.getInstance().exception(e);
			return false;
		}
	}

	public ArrayList<?> findAll() {
		return manager.findAll("Cargo");
	}

	public Object get(final Long id) {
		final Cargo cargo = new Cargo();
		cargo.setCargoId(id);
		return manager.find(Cargo.class, id);
	}

	public Object next(final Long id) {
		return super.next("Cargo", "cargoId", id);
	}

	public Object previous(final Long id) {
		return super.previous("Cargo", "cargoId", id);
	}

	public boolean update(final Object vs) {
		try {
			return manager.update(vs);
		} catch (final Exception e) {
			LogHelper.getInstance().exception(e);
			return false;
		}
	}
}
