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

import net.kodveus.gui.araclar.VeriSinif;

/**
 * Bu sinifin amaci tum veri siniflarimizda olmasi gereken status sahasini
 * tanimlamaktir. Status sahasi sinifin barindirdigi kayitin durum bilgisini
 * tutmaktadir.
 *
 * @author emre
 *
 */
public abstract class TemelVeriSinif extends VeriSinif {
	//TODO findAll metodu tum jdo siniflara yazilmali
	//TODO tum siniflardaki iliskiler ayarlanacak
	private static final long serialVersionUID = 1L;

	private Long status;

	public Long getStatus() {
		return status;
	}

	public void setStatus(Long status) {
		this.status = status;
	}

	@Override
	public void prepareMap() {
	}

	@Override
	public abstract boolean equals(Object obj);

	@Override
	public abstract String toString();

}
