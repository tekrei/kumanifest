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
import net.kodveus.kumanifest.jdo.Location;
import net.kodveus.kumanifest.utility.LogHelper;

public class LocationOperation extends Operation implements OperationInterface {

	private static LocationOperation instance;

	private LocationOperation() {

	}

	public static LocationOperation getInstance() {
		if (instance == null) {
			instance = new LocationOperation();
		}
		return instance;
	}

	public long create(Object vs) {
		try {
			Location location = (Location) vs;
			manager.save(location);
			return location.getLocationId();
		} catch (Exception e) {
			LogHelper.getInstance().exception(e);
			return 0;
		}
	}

	public boolean delete(Object vs) {
		try {
			Location location = (Location) vs;
			return manager.delete(location);
		} catch (Exception e) {
			LogHelper.getInstance().exception(e);
			return false;
		}
	}

	public boolean update(Object vs) {
		try {
			Location location = (Location) vs;
			return manager.update(location);
		} catch (Exception e) {
			LogHelper.getInstance().exception(e);
			return false;
		}
	}

	public ArrayList<Location> findAll(){
		return manager.findAll("Location");
	}

	public ArrayList<Location> getPorts() {
		return manager.executeNamedQuery("Location.Ports");
	}

	public Object get(Long id) {
		return (Location)manager.find(Location.class,id);
	}

	public Object next(Long id) {
		return super.next("Location","locationId", id);
	}

	public Object previous(Long id) {
		return super.previous("Location","locationId", id);
	}
}
