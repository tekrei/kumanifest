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
package net.kodveus.kumanifest.jdo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

import net.kodveus.gui.araclar.AliasMap;
import net.kodveus.kumanifest.utility.LogHelper;

@Entity
@NamedQueries({
	@NamedQuery(name = "Location.findAll", query = "SELECT Obj FROM Location AS Obj"),
	@NamedQuery(name = "Location.Ports", query = "SELECT Obj FROM Location AS Obj WHERE Obj.isPort='Y'")
})
public class Location extends TemelVeriSinif {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long locationId;

	private String code;

	private String location;

	private String isPort;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getIsport() {
		return isPort;
	}

	public void setIsport(String isport) {
		this.isPort = isport;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public Long getLocationId() {
		return locationId;
	}

	public void setLocationId(Long locationId) {
		this.locationId = locationId;
	}

	@Override
	public void prepareMap() {
		try {
			aliasMap = new AliasMap(this.getClass());
			aliasMap.addAlias("Location", "location");
			aliasMap.addAlias("Port", "isport");
			aliasMap.addAlias("Code", "code");
		} catch (Exception e) {
			LogHelper.getInstance().exception(e);
		}
	}

	@Override
	public String toString() {
		return this.code + " " + this.location;
	}

	@Override
	public boolean equals(Object object) {
		try {
			return ((Location) object).getLocationId().equals(
					this.getLocationId());
		} catch (Exception e) {
			return false;
		}
	}
}
