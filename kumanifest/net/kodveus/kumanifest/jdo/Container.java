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

public class Container extends TemelVeriSinif {

	private static final long serialVersionUID = 1L;

	@Override
	public void prepareMap() {
		try {
			aliasMap.addAlias("Container No", "containerNo");
			aliasMap.addAlias("Seal No", "sealNo");
			aliasMap.addAlias("Other Seal No", "otherSealNo");
			aliasMap.addAlias("Container Type", "containerType");
			aliasMap.addAlias("Container Size", "containerSize");
			aliasMap.addAlias("RelCom", "relCom");
			aliasMap.addAlias("Tara Weight", "taraWeight");
		} catch (Exception e) {
			LogHelper.getInstance().exception(e);
		}
	}

	private Long containerId;

	private Long relCom;

	private String containerNo;

	private Long blId;

	private ContainerType containerType;

	private String sealNo;

	private String otherSealNo;

	private ContainerSize containerSize;

	private Double taraWeight;

	private Long status;

	public Long getBlId() {
		return blId;
	}

	public void setBlId(Long blId) {
		this.blId = blId;
	}

	public Long getContainerId() {
		return containerId;
	}

	public void setContainerId(Long containerId) {
		this.containerId = containerId;
	}

	public String getContainerNo() {
		return containerNo;
	}

	public void setContainerNo(String containerNo) {
		this.containerNo = containerNo;
	}

	public ContainerSize getContainerSize() {
		return containerSize;
	}

	public void setContainerSize(ContainerSize containerSize) {
		this.containerSize = containerSize;
	}

	public ContainerType getContainerType() {
		return containerType;
	}

	public void setContainerType(ContainerType containerType) {
		this.containerType = containerType;
	}

	public String getOtherSealNo() {
		return otherSealNo;
	}

	public void setOtherSealNo(String otherSealNo) {
		this.otherSealNo = otherSealNo;
	}

	public Long getRelCom() {
		return relCom;
	}

	public void setRelCom(Long relCom) {
		this.relCom = relCom;
	}

	public String getSealNo() {
		return sealNo;
	}

	public void setSealNo(String sealNo) {
		this.sealNo = sealNo;
	}

	@Override
	public Long getStatus() {
		return status;
	}

	@Override
	public void setStatus(Long status) {
		this.status = status;
	}

	public Double getTaraWeight() {
		return taraWeight;
	}

	public void setTaraWeight(Double taraWeight) {
		this.taraWeight = taraWeight;
	}

	@Override
	public String toString() {
		return containerNo;
	}

	@Override
	public boolean equals(Object object) {
		try {
			return ((Container) object).getContainerId().equals(
					this.getContainerId());
		} catch (Exception e) {
			return false;
		}
	}
}
