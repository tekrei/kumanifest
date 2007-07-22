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

import net.kodveus.gui.araclar.AliasMap;
import net.kodveus.kumanifest.utility.LogHelper;

@Entity
@NamedQueries({
	@NamedQuery(name = "Cargo.findAll", query = "SELECT Obj FROM Cargo AS Obj"),
	@NamedQuery(name = "Cargo.ofContainer",
			query = "SELECT cargo FROM Cargo cargo WHERE cargo.container.containerId=:param0")
})
public class Cargo extends TemelVeriSinif {

	private static final long serialVersionUID = 1L;

	@Override
	public void prepareMap() {
		try {
			aliasMap = new AliasMap(this.getClass());
			aliasMap.addAlias("Commodity", "commodity");
			aliasMap.addAlias("CargoType", "cargoType");
			aliasMap.addAlias("Pack", "pack");
			aliasMap.addAlias("Imco", "imco");
			aliasMap.addAlias("Unno", "unno");
			aliasMap.addAlias("NetWeight", "netWeight");
			aliasMap.addAlias("Pack Total", "packTotal");
		} catch (Exception e) {
			LogHelper.getInstance().exception(e);
		}
	}

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long cargoId;

	@OneToOne(cascade=CascadeType.ALL,fetch=FetchType.LAZY)
	private Container container;

	@OneToOne(cascade=CascadeType.ALL,fetch=FetchType.LAZY)
	private Commodity commodity;

	private String cargoType;

	@OneToOne(cascade=CascadeType.ALL,fetch=FetchType.LAZY)
	private Pack pack;

	private String imco;

	private String unno;

	private Double netWeight;

	private Double packTotal;

	public Double getPackTotal() {
		return this.packTotal;
	}

	public void setPackTotal(Double packTotal) {
		this.packTotal = packTotal;
	}

	public Double getNetWeight() {
		return netWeight;
	}

	public void setNetWeight(Double netWeight) {
		this.netWeight = netWeight;
	}

	public String getCargoType() {
		return cargoType;
	}

	public void setCargoType(String cargoType) {
		this.cargoType = cargoType;
	}

	public Commodity getCommodity() {
		return commodity;
	}

	public void setCommodity(Commodity commodity) {
		this.commodity = commodity;
	}

	public Container getContainer() {
		return container;
	}

	public void setContainer(Container _container) {
		this.container = _container;
	}

	public String getImco() {
		return imco;
	}

	public void setImco(String imco) {
		this.imco = imco;
	}

	public Pack getPack() {
		return pack;
	}

	public void setPack(Pack pack) {
		this.pack = pack;
	}

	public String getUnno() {
		return unno;
	}

	public void setUnno(String unno) {
		this.unno = unno;
	}

	public Long getCargoId() {
		return cargoId;
	}

	public void setCargoId(Long cargoId) {
		this.cargoId = cargoId;
	}

	@Override
	public String toString() {
		return this.cargoType + " " + this.netWeight;
	}

	@Override
	public boolean equals(Object object) {
		try {
			return ((Cargo) object).getCargoId().equals(this.getCargoId());
		} catch (Exception e) {
			return false;
		}
	}
}
