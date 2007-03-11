package net.kodveus.kumanifest.jdo;

import net.kodveus.kumanifest.utility.LogHelper;

public class Pack extends TemelVeriSinif {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long packId;

	private String type;

	private String description;

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Long getPackId() {
		return packId;
	}

	public void setPackId(Long packId) {
		this.packId = packId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public void prepareMap() {
		try {
			aliasMap.addAlias("Type", "type");
			aliasMap.addAlias("Description", "description");
		} catch (Exception e) {
			LogHelper.getInstance().istisna(e);
		}
	}

	public String toString() {
		return description;
	}

	public boolean equals(Object object) {
		try {
			return ((Pack) object).getPackId().equals(this.getPackId());
		} catch (Exception e) {
			return false;
		}
	}
}
