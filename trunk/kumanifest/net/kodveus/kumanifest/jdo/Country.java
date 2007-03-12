/*
 * Created on Jul 3, 2006
 *
 */
package net.kodveus.kumanifest.jdo;

import net.kodveus.kumanifest.utility.LogHelper;

public class Country extends TemelVeriSinif {

	private static final long serialVersionUID = 1L;

	private Long countryId;

	private String code;

	private String name;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Long getCountryId() {
		return countryId;
	}

	public void setCountryId(Long countryId) {
		this.countryId = countryId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public void prepareMap() {
		try {
			aliasMap.addAlias("Code", "code");
			aliasMap.addAlias("Name", "name");
		} catch (Exception e) {
			LogHelper.getInstance().exception(e);
		}
	}

	@Override
	public String toString() {
		return this.code + " " + this.name;
	}

	@Override
	public boolean equals(Object object) {
		try {
			return ((Country) object).getCountryId().equals(this.countryId);
		} catch (Exception e) {
			return false;
		}
	}
}
