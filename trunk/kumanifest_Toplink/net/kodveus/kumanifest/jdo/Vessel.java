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

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;

import net.kodveus.kumanifest.utility.LogHelper;

@Entity
@NamedQueries( {
		@NamedQuery(name = "Vessel.findAll", query = "SELECT Ves FROM Vessel AS Ves"),
		@NamedQuery(name = "Vessel.treeData", query = "SELECT vessel FROM Vessel vessel WHERE vesselId IN "
				+ "(SELECT vesselId FROM voyage WHERE export=:0 AND officeId=:1)")
})

public class Vessel extends TemelVeriSinif {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long vesselId;

	private String vesselCode;

	private String vesselName;

	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Country flag;

	private Long status;

	private String company;

	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Location port;

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public Country getFlag() {
		return flag;
	}

	public void setFlag(Country flag) {
		this.flag = flag;
	}

	public Location getPort() {
		return port;
	}

	public void setPort(Location port) {
		this.port = port;
	}

	@Override
	public Long getStatus() {
		return status;
	}

	@Override
	public void setStatus(Long status) {
		this.status = status;
	}

	public String getVesselCode() {
		return vesselCode;
	}

	public void setVesselCode(String vesselCode) {
		this.vesselCode = vesselCode;
	}

	public Long getVesselId() {
		return vesselId;
	}

	public void setVesselId(Long vesselId) {
		this.vesselId = vesselId;
	}

	public String getVesselName() {
		return vesselName;
	}

	public void setVesselName(String vesselName) {
		this.vesselName = vesselName;
	}

	@Override
	public void prepareMap() {
		try {
			aliasMap.addAlias("Vessel Code", "vesselCode");
			aliasMap.addAlias("Vessel Name", "vesselName");
			aliasMap.addAlias("Flag", "flag");
			aliasMap.addAlias("Company", "company");
			aliasMap.addAlias("Port", "port");
		} catch (Exception e) {
			LogHelper.getInstance().exception(e);
		}
	}

	@Override
	public String toString() {
		return this.vesselCode + " " + this.vesselName;
	}

	@Override
	public boolean equals(Object object) {
		try {
			return ((Vessel) object).getVesselId().equals(this.vesselId);
		} catch (Exception e) {
			return false;
		}
	}
}
