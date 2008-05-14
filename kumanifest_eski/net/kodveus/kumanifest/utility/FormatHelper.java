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

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FormatHelper {

	public static String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";

	private static SimpleDateFormat dateFormat = null;

	public static String convertDate(Date date) {
		String result = "";

		try {
			result = getDateFormatter().format(date);
		} catch (Exception e) {
		}

		return result;
	}

	private static DateFormat getDateFormatter() {
		if (dateFormat == null) {
			dateFormat = new SimpleDateFormat(DATE_FORMAT);
		}

		return dateFormat;
	}
}
