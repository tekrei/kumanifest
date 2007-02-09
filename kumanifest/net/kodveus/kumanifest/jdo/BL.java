package net.kodveus.kumanifest.jdo;

public class BL extends TemelVeriSinif {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void prepareMap() {
	}

	private Long blId;

	private Voyage voyage;

	private String blNo;

	private String companyName;

	private Long status;

	private Long reportType;

	private Location placeOfOrigin;

	private Location placeOfReceipt;

	private Location portOfLoading;

	private Location portOfDischarge;

	private Location finalDischargePlace;

	private Location finalDestination;

	private String description;

	private String description2;

	private String shipper;

	private String consignee;

	private String notify;

	private String notify2;

	public Long getBlId() {
		return blId;
	}

	public void setBlId(Long blId) {
		this.blId = blId;
	}

	public String getBlNo() {
		return blNo;
	}

	public void setBlNo(String blNo) {
		this.blNo = blNo;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getConsignee() {
		return consignee;
	}

	public void setConsignee(String consignee) {
		this.consignee = consignee;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDescription2() {
		return description2;
	}

	public void setDescription2(String description2) {
		this.description2 = description2;
	}

	public Location getFinalDestination() {
		return finalDestination;
	}

	public void setFinalDestination(Location finalDestination) {
		this.finalDestination = finalDestination;
	}

	public Location getFinalDischargePlace() {
		return finalDischargePlace;
	}

	public void setFinalDischargePlace(Location finalDischargePlace) {
		this.finalDischargePlace = finalDischargePlace;
	}

	public String getNotify() {
		return notify;
	}

	public void setNotify(String notify) {
		this.notify = notify;
	}

	public String getNotify2() {
		return notify2;
	}

	public void setNotify2(String notify2) {
		this.notify2 = notify2;
	}

	public Location getPlaceOfOrigin() {
		return placeOfOrigin;
	}

	public void setPlaceOfOrigin(Location placeOfOrigin) {
		this.placeOfOrigin = placeOfOrigin;
	}

	public Location getPlaceOfReceipt() {
		return placeOfReceipt;
	}

	public void setPlaceOfReceipt(Location placeOfReceipt) {
		this.placeOfReceipt = placeOfReceipt;
	}

	public Location getPortOfDischarge() {
		return portOfDischarge;
	}

	public void setPortOfDischarge(Location portOfDischarge) {
		this.portOfDischarge = portOfDischarge;
	}

	public Location getPortOfLoading() {
		return portOfLoading;
	}

	public void setPortOfLoading(Location portOfLoading) {
		this.portOfLoading = portOfLoading;
	}

	public Long getReportType() {
		return reportType;
	}

	public void setReportType(Long reportType) {
		this.reportType = reportType;
	}

	public String getShipper() {
		return shipper;
	}

	public void setShipper(String shipper) {
		this.shipper = shipper;
	}

	public Long getStatus() {
		return status;
	}

	public void setStatus(Long status) {
		this.status = status;
	}

	public Voyage getVoyage() {
		return voyage;
	}

	public void setVoyage(Voyage voyage) {
		this.voyage = voyage;
	}

	public String toString() {
		return blNo;
	}

	public boolean equals(Object object) {
		try {
			return ((BL) object).getBlId().equals(this.blId);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
}
