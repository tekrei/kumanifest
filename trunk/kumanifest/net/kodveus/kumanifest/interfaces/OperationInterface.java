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
package net.kodveus.kumanifest.interfaces;

import java.util.ArrayList;

import net.kodveus.gui.araclar.VeriSinif;

public interface OperationInterface {

	public abstract long create(VeriSinif vs);

	public abstract boolean delete(VeriSinif vs);

	// TODO public abstract boolean delete(Long id);

	public abstract boolean update(VeriSinif vs);

	@SuppressWarnings("unchecked")
	public abstract ArrayList ara(VeriSinif vs);

	public abstract VeriSinif get(Long id);

	public abstract VeriSinif next(Long id);

	public abstract VeriSinif previous(Long id);
}
