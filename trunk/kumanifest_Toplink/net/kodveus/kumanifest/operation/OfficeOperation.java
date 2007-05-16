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
import net.kodveus.kumanifest.jdo.Office;
import net.kodveus.kumanifest.utility.LogHelper;

public class OfficeOperation extends Operation implements OperationInterface {

	private static OfficeOperation instance;

	private OfficeOperation() {

	}

	public static OfficeOperation getInstance() {
		if (instance == null) {
			instance = new OfficeOperation();
		}
		return instance;
	}

	public long create(Object vs) {
		try {
			Office office = (Office) vs;
			manager.save(office);
			return office.getOfficeId();
		} catch (Exception e) {
			LogHelper.getInstance().exception(e);
			return 0;
		}
	}

	public boolean delete(Object vs) {
		try {
			Office office = (Office) vs;
			return manager.delete(office);
		} catch (Exception e) {
			LogHelper.getInstance().exception(e);
			return false;
		}
	}

	public boolean update(Object vs) {
		try {
			Office office = (Office) vs;
			return manager.update(office);
		} catch (Exception e) {
			LogHelper.getInstance().exception(e);
			return false;
		}
	}

	public ArrayList<Office> findAll(){
		return manager.findAll("Office");
	}

	public Object get(Long id) {
		return (Office)manager.find(Office.class,id);
	}

	public Object next(Long id) {
		return super.next("Office","officeId", id);
	}

	public Object previous(Long id) {
		return super.previous("Office","officeId", id);
	}
}
