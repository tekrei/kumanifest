package net.kodveus.kumanifest.jdo;

import net.kodveus.kumanifest.utility.LogHelper;

public class Vessel extends TemelVeriSinif {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void prepareMap() {
		try {
			aliasMap.addAlias("Vessel Code", "vesselCode");
			aliasMap.addAlias("Vessel Name", "vesselName");
			aliasMap.addAlias("Flag", "flag");
			aliasMap.addAlias("Company", "company");
			aliasMap.addAlias("Port", "port");
		} catch (Exception e) {
			LogHelper.getInstance().istisna(e);
		}
	}

	private Long vesselId;

	private String vesselCode;

	private String vesselName;

	private Country flag;

	private Long status;

	private String company;

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

	public Long getStatus() {
		return status;
	}

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

	public String toString() {
		return this.vesselCode + " " + this.vesselName;
	}

	public boolean equals(Object object) {
		try {
			return ((Vessel) object).getVesselId().equals(this.vesselId);
		} catch (Exception e) {
			return false;
		}
	}
}
