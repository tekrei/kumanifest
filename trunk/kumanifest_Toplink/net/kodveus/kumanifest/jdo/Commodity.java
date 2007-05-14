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

import net.kodveus.kumanifest.utility.LogHelper;

@Entity
@NamedQueries({
	@NamedQuery(name = "findAll", query = "SELECT Com FROM Commodity AS Com")
})
public class Commodity extends TemelVeriSinif {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long commodityId;

	private String code;

	private String name;

	private String description;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Long getCommodityId() {
		return commodityId;
	}

	public void setCommodityId(Long commodityId) {
		this.commodityId = commodityId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public void prepareMap() {
		try {
			aliasMap.addAlias("Code", "code");
			aliasMap.addAlias("Name", "name");
			aliasMap.addAlias("Description", "description");
		} catch (Exception e) {
			LogHelper.getInstance().exception(e);
		}
	}

	@Override
	public String toString() {
		return code + " " + name;
	}

	@Override
	public boolean equals(Object object) {
		try {
			return ((Commodity) object).getCommodityId().equals(
					this.getCommodityId());
		} catch (Exception e) {
			return false;
		}
	}
}
