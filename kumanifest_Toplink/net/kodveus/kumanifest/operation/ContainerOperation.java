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
import net.kodveus.kumanifest.jdo.Container;
import net.kodveus.kumanifest.utility.LogHelper;

public class ContainerOperation extends Operation implements OperationInterface {

	private static ContainerOperation instance;

	private ContainerOperation() {

	}

	public static ContainerOperation getInstance() {
		if (instance == null) {
			instance = new ContainerOperation();
		}
		return instance;
	}

	public long create(VeriSinif vs) {
		try {
			Container container = (Container) vs;
			manager.save(container);
			return container.getContainerId();
		} catch (Exception e) {
			LogHelper.getInstance().exception(e);
			return 0;
		}

	}

	public boolean delete(VeriSinif vs) {
		try {
			Container container = (Container) vs;
			return manager.delete(container);
		} catch (Exception e) {
			LogHelper.getInstance().exception(e);
			return false;
		}
	}

	public boolean update(VeriSinif vs) {
		try {
			Container container = (Container) vs;
			return manager.delete(container);
		} catch (Exception e) {
			LogHelper.getInstance().exception(e);
			return false;
		}
	}

	public VeriSinif get(Long id) {
		return (Container) manager.find(Container.class, id);
	}

	public ArrayList<Container> findAll() {
		return manager.findAll("Container");
	}

	public VeriSinif next(Long id) {
		return super.next(new Container(), "Container", "containerId", id);
	}

	public VeriSinif previous(Long id) {
		return super.previous(new Container(), "Container", "containerId", id);
	}

	public ArrayList<Container> containerOfBl(Long blId) {
		String nativeQuery = "SELECT container FROM Container container JOIN container.BL b WHERE b.blId="
				+ blId;
		return manager.executeQuery(nativeQuery);
	}
}