package net.kodveus.kumanifest.jdo;

import net.kodveus.gui.araclar.AliasMap;
import net.kodveus.kumanifest.utility.LogHelper;

public class Location extends TemelVeriSinif {

	private static final long serialVersionUID = 1L;

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
