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
import net.kodveus.kumanifest.jdo.Country;
import net.kodveus.kumanifest.utility.LogHelper;

public class CountryOperation extends Operation implements OperationInterface {

	private static CountryOperation instance;

	private CountryOperation() {

	}

	public static CountryOperation getInstance() {
		if (instance == null) {
			instance = new CountryOperation();
		}
		return instance;
	}

	public long create(Object vs) {
		try {
			Country country = (Country) vs;
			manager.save(country);
			return country.getCountryId();
		} catch (Exception e) {
			LogHelper.getInstance().exception(e);
			return 0;
		}
	}

	public boolean delete(Object vs) {
		try {
			Country country = (Country) vs;
			return manager.delete(country);
		} catch (Exception e) {
			LogHelper.getInstance().exception(e);
			return false;
		}
	}

	public boolean update(Object vs) {
		try {
			Country country = (Country) vs;
			return manager.update(country);
		} catch (Exception e) {
			LogHelper.getInstance().exception(e);
			return false;
		}
	}

	public ArrayList<Country> findAll() {
		return manager.findAll("Country");
	}

	public Object get(Long id) {
		return (Country) manager.find(Country.class, id);
	}

	public Object next(Long id) {
		return super.next("Country", "countryId", id);
	}

	public Object previous(Long id) {
		return super.previous("Country", "countryId", id);
	}
}
