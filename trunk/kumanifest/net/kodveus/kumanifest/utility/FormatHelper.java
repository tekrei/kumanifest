/*
 * Created on Jul 16, 2006
 *
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
