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

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@Entity
@NamedQueries({
	@NamedQuery(name = "BL.findAll", query = "SELECT Bl FROM BL AS Bl")
})
public class BL extends TemelVeriSinif{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long blId;

	private Voyage voyage;

	private String blNo;

	private String companyName;

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

	public Voyage getVoyage() {
		return voyage;
	}

	public void setVoyage(Voyage voyage) {
		this.voyage = voyage;
	}

	@Override
	public String toString() {
		return blNo;
	}

	@Override
	public boolean equals(Object object) {
		try {
			return ((BL) object).getBlId().equals(this.blId);
		} catch (Exception e) {
			return false;
		}
	}
}
