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
