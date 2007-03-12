/*
 * Created on Jul 3, 2006
 *
 */
package net.kodveus.kumanifest.jdo;

import net.kodveus.kumanifest.utility.LogHelper;

public class ContainerSize extends TemelVeriSinif {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long containerSizeId;

	private String size;

	private String description;

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Long getContainerSizeId() {
		return containerSizeId;
	}

	public void setContainerSizeId(Long containerSizeId) {
		this.containerSizeId = containerSizeId;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	@Override
	public void prepareMap() {
		try {
			aliasMap.addAlias("Size", "size");
			aliasMap.addAlias("Description", "description");
		} catch (Exception e) {
			LogHelper.getInstance().exception(e);
		}
	}

	@Override
	public String toString() {
		return this.size;
	}

	@Override
	public boolean equals(Object object) {
		try {
			return ((ContainerSize) object).getContainerSizeId().equals(
					this.containerSizeId);
		} catch (Exception e) {
			return false;
		}
	}

}
