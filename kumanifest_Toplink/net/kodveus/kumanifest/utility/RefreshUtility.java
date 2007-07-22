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
package net.kodveus.kumanifest.utility;

import java.util.ArrayList;

import net.kodveus.kumanifest.interfaces.Refreshable;

/**
 * Bu sinifin amaci verilerde bir degisiklik oldugu zaman bu degisikliklerin
 * yansitilmasi gereken panellerin (BLPanel gibi) guncellenmesini saglamaktir.
 *
 *
 * @author emre
 *
 */
public class RefreshUtility {

	private static RefreshUtility instance = null;

	private ArrayList<Refreshable> refreshableClasses;

	private RefreshUtility(){

	}

	public static RefreshUtility getInstance(){
		if(instance==null){
			instance = new RefreshUtility();
		}
		return instance;
	}

	public void addRefreshable(Refreshable _refreshable){
		if(refreshableClasses==null)
			refreshableClasses = new ArrayList<Refreshable>();
		refreshableClasses.add(_refreshable);
	}

	/**
	 * Veritabanina bir commit oldugunda yenilenecek olan ekranlarin yenilenmesi bu
	 * metod yardimiyla saglanmaktadir
	 *
	 */
	public void refresh(){
		for (Refreshable classToRefresh : refreshableClasses) {
			classToRefresh.refresh();
		}
	}

}
