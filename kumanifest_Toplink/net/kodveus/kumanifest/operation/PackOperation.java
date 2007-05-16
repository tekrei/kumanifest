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
import net.kodveus.kumanifest.jdo.Pack;
import net.kodveus.kumanifest.utility.LogHelper;

public class PackOperation extends Operation implements OperationInterface {

	private static PackOperation instance;

	private PackOperation() {

	}

	public static PackOperation getInstance() {
		if (instance == null) {
			instance = new PackOperation();
		}
		return instance;
	}

	public long create(Object vs) {
		try {
			Pack pack = (Pack) vs;
			manager.save(pack);
			return pack.getPackId();
		} catch (Exception e) {
			LogHelper.getInstance().exception(e);
			return 0;
		}
	}

	public boolean delete(Object vs) {
		try {
			Pack pack = (Pack) vs;
			return manager.delete(pack);
		} catch (Exception e) {
			LogHelper.getInstance().exception(e);
			return false;
		}
	}

	public boolean update(Object vs) {
		try {
			Pack pack = (Pack) vs;
			return manager.update(pack);
		} catch (Exception e) {
			LogHelper.getInstance().exception(e);
			return false;
		}
	}

	public ArrayList<Pack> findAll() {
		return manager.findAll("Pack");
	}

	public Object get(Long id) {
		return (Pack) manager.find(Pack.class, id);
	}

	public Object next(Long id) {
		return super.next("Pack","packId", id);
	}

	public Object previous(Long id) {
		return super.previous("Pack","packId", id);
	}
}
