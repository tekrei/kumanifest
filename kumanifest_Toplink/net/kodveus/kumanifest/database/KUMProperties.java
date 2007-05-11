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
package net.kodveus.kumanifest.database;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import net.kodveus.kumanifest.utility.LogHelper;

public class KUMProperties extends java.util.Properties {

	private static final long serialVersionUID = 1L;

	public KUMProperties() {
		super();
	}

	public boolean load() {
		try {
			load(new FileInputStream("vt.props"));
			return true;
		} catch (IOException e) {
			LogHelper.getInstance().exception(e);
			return false;
		}
	}

	public boolean save() {
		try {
			File file = new File("vt.props");
			file.createNewFile();
			store(new FileOutputStream(file), null);
			return true;
		} catch (IOException e) {
			return false;
		}
	}

	public String getVeritabaniAdi(){
		return getProperty("vtAdi");
	}

	public String getVeritabaniTipi(){
		return getProperty("vtTipi");
	}

	public String getSifre(){
		return getProperty("sifre");
	}

	public String getKullaniciAdi(){
		return getProperty("kullanici");
	}

	public String getAdres(){
		return getProperty("adres");
	}
}
