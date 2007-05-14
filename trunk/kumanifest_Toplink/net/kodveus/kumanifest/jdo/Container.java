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
@NamedQueries({
	@NamedQuery(name = "findAll", query = "SELECT Obj FROM Container AS Obj")
})
public class Container extends TemelVeriSinif {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long containerId;

	private Long relCom;

	private String containerNo;

	@OneToOne(cascade=CascadeType.ALL,fetch=FetchType.LAZY)
	private BL bl;

	@OneToOne(cascade=CascadeType.ALL,fetch=FetchType.LAZY)
	private ContainerType containerType;

	private String sealNo;

	private String otherSealNo;

	@OneToOne(cascade=CascadeType.ALL,fetch=FetchType.LAZY)
	private ContainerSize containerSize;

	private Double taraWeight;

	public BL getBl() {
		return bl;
	}

	public void setBl(BL _bl) {
		this.bl = _bl;
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

	public Double getTaraWeight() {
		return taraWeight;
	}

	public void setTaraWeight(Double taraWeight) {
		this.taraWeight = taraWeight;
	}

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
