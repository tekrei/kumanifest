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

import java.util.Date;

public class Voyage extends TemelVeriSinif {

	private static final long serialVersionUID = 1L;

	private Long voyageId;

	private String voyage;

	private Long export;

	private Vessel vessel;

	private Office office;

	private Long status;

	private Location firstLeavedPort;

	private Location lastLeavedPort;

	private Date arrivalDate;

	private Date departureDate;

	private String nameOfCaptain;

	@Override
	public void prepareMap() {
		try {
			aliasMap.addAlias("Voyage", "voyage");
			aliasMap.addAlias("Vessel", "vessel");
			aliasMap.addAlias("Office", "office");
			aliasMap.addAlias("First Leaved Port", "firstLeavedPort");
			aliasMap.addAlias("Last Leaved Port", "lastLeavedPort");
			aliasMap.addAlias("Arrival Date", "arrivalDate");
			aliasMap.addAlias("Departure Date", "departureDate");
			aliasMap.addAlias("Name of Captain", "nameOfCaptain");
		} catch (Exception e) {

		}

	}

	public Date getArrivalDate() {
		return arrivalDate;
	}

	public void setArrivalDate(Date arrivalDate) {
		this.arrivalDate = arrivalDate;
	}

	public Date getDepartureDate() {
		return departureDate;
	}

	public void setDepartureDate(Date departureDate) {
		this.departureDate = departureDate;
	}

	public Long getExport() {
		return export;
	}

	public void setExport(Long export) {
		this.export = export;
	}

	public Location getFirstLeavedPort() {
		return firstLeavedPort;
	}

	public void setFirstLeavedPort(Location firstLeavedPort) {
		this.firstLeavedPort = firstLeavedPort;
	}

	public Location getLastLeavedPort() {
		return lastLeavedPort;
	}

	public void setLastLeavedPort(Location lastLeavedPort) {
		this.lastLeavedPort = lastLeavedPort;
	}

	public String getNameOfCaptain() {
		return nameOfCaptain;
	}

	public void setNameOfCaptain(String nameOfCaptain) {
		this.nameOfCaptain = nameOfCaptain;
	}

	public Office getOffice() {
		return office;
	}

	public void setOffice(Office office) {
		this.office = office;
	}

	@Override
	public Long getStatus() {
		return status;
	}

	@Override
	public void setStatus(Long status) {
		this.status = status;
	}

	public Vessel getVessel() {
		return vessel;
	}

	public void setVessel(Vessel vessel) {
		this.vessel = vessel;
	}

	public String getVoyage() {
		return voyage;
	}

	public void setVoyage(String voyage) {
		this.voyage = voyage;
	}

	public Long getVoyageId() {
		return voyageId;
	}

	public void setVoyageId(Long voyageId) {
		this.voyageId = voyageId;
	}

	@Override
	public String toString() {
		return voyage;
	}

	@Override
	public boolean equals(Object object) {
		try {
			return ((Voyage) object).getVoyageId().equals(this.voyageId);
		} catch (Exception e) {
			return false;
		}
	}
}
