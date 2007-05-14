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
package net.kodveus.kumanifest.operation;

import java.util.ArrayList;

import net.kodveus.gui.araclar.VeriSinif;
import net.kodveus.kumanifest.interfaces.OperationInterface;
import net.kodveus.kumanifest.jdo.Commodity;
import net.kodveus.kumanifest.utility.LogHelper;

public class CommodityOperation extends Operation implements OperationInterface {

	private static CommodityOperation instance;

	private CommodityOperation() {

	}

	public static CommodityOperation getInstance() {
		if (instance == null) {
			instance = new CommodityOperation();
		}
		return instance;
	}

	public long create(VeriSinif vs) {
		try {
			Commodity commodity = (Commodity) vs;
			manager.save(commodity);
			return commodity.getCommodityId();
		} catch (Exception e) {
			LogHelper.getInstance().exception(e);
			return 0;
		}
	}

	public boolean delete(VeriSinif vs) {
		return manager.delete((Commodity) vs);
	}

	public boolean update(VeriSinif vs) {
		try {
			Commodity commodity = (Commodity) vs;
			return manager.update(commodity);
		} catch (Exception e) {
			LogHelper.getInstance().exception(e);
			return false;
		}
	}

	public VeriSinif get(Long id) {
		return (Commodity)manager.find(Commodity.class, id);
	}

	public ArrayList<Commodity> findAll(){
		return manager.findAll("Commodity");
	}

	public VeriSinif next(Long id) {
		Commodity commodity = new Commodity();
		return super.next(commodity, "commodity", "commodityId", id);

	}

	public VeriSinif previous(Long id) {
		Commodity commodity = new Commodity();
		return super.previous(commodity, "commodity", "commodityId", id);
	}
}
