/*
 * Created on Jul 3, 2006
 *
 */
package net.kodveus.kumanifest.jdo;

import net.kodveus.kumanifest.utility.LogHelper;

public class ContainerType extends TemelVeriSinif {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long containerTypeId;

	private String type;

	private String description;

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Long getContainerTypeId() {
		return containerTypeId;
	}

	public void setContainerTypeId(Long containerTypeId) {
		this.containerTypeId = containerTypeId;
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
			LogHelper.getInstance().exception(e);
		}
	}

	public String toString() {
		return this.type;
	}

	public boolean equals(Object object) {
		try {
			return ((ContainerType) object).getContainerTypeId().equals(
					this.containerTypeId);
		} catch (Exception e) {
			return false;
		}
	}

}
