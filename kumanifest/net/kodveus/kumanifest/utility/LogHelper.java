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

import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Formatter;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;
import java.util.logging.XMLFormatter;

public class LogHelper {
	private static LogHelper instance = null;

	private Logger loglayici = Logger.getLogger("KumanifestLog");

	class ShortFormatter extends Formatter {
		// inefficient implementation
		public String format(LogRecord record) {
			StringBuffer buffer = new StringBuffer(record.getLevel() + " [");
			buffer.append(record.getSourceMethodName() + "] ");
			buffer.append(record.getMessage() + "\n");
			return buffer.toString();
		}
	}

	private LogHelper() {
		try {
			// 5 log files, switching when they reach approximately 1MB
			FileHandler fh = new FileHandler("kumanifest%g.log", 1000000, 5,
					true);
			fh.setFormatter(new XMLFormatter());
			loglayici.addHandler(fh);
			ConsoleHandler ch = new ConsoleHandler();
			ch.setFormatter(new ShortFormatter());
			loglayici.addHandler(ch);
			loglayici.setUseParentHandlers(false);
		} catch (Exception e) {
			exception(e);
		}
	}

	public static LogHelper getInstance() {
		if (instance == null) {
			instance = new LogHelper();
		}
		return instance;
	}

	public static Logger getLogger() {
		return getInstance().loglayici;
	}

	/**
	 * Calisma zamaninda log seviyesini degistirmek icin kullanilmalidir
	 * 
	 * Tum mesajlar ekrana yazilir
	 */
	public void all() {
		loglayici.setLevel(Level.ALL);
	}

	/**
	 * Sadece hata mesajlari ekrana yazilir
	 */
	public void severe() {
		loglayici.setLevel(Level.SEVERE);
	}

	/**
	 * Istisna olustugunda cagrilmali
	 * 
	 * @param e
	 *            Olusan istisna
	 */
	public void exception(Exception e) {
		loglayici.log(Level.SEVERE, e.getMessage(), e);
	}

	/**
	 * Istisna olustugunda, istisnanin mesajindan farkli daha anlasilir bir
	 * mesaj kullanilacagi zaman cagrilmali
	 * 
	 * @param msg
	 *            Hata mesaji
	 * @param e
	 *            Olusan istisna
	 */
	public void exception(String msg, Exception e) {
		loglayici.log(Level.SEVERE, msg, e);
	}
}
