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
			LogHelper.getInstance().istisna(e);
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

	public Long getStatus() {
		return status;
	}

	public void setStatus(Long status) {
		this.status = status;
	}

	public Double getTaraWeight() {
		return taraWeight;
	}

	public void setTaraWeight(Double taraWeight) {
		this.taraWeight = taraWeight;
	}

	public String toString() {
		return containerNo;
	}

	public boolean equals(Object object) {
		try {
			return ((Container) object).getContainerId().equals(
					this.getContainerId());
		} catch (Exception e) {
			return false;
		}
	}
}
