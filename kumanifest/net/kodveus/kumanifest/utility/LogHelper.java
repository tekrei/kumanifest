package net.kodveus.kumanifest.utility;

import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class LogHelper {
	private static Logger loglayici = Logger.getLogger("KUMANIFEST LOG");

	private static LogHelper instance = null;

	private LogHelper() {
		try {
			FileHandler fh = new FileHandler("kumanifest.log");
			fh.setFormatter(new SimpleFormatter());
			loglayici.addHandler(fh);
			loglayici.addHandler(new ConsoleHandler());
			loglayici.setUseParentHandlers(false);
		} catch (Exception e) {
			exception(e);
		}
	}

	/**
	 * Calisma zamaninda log seviyesini degistirmek icin kullanilmalidir
	 * 
	 * Tum mesajlar ekrana yazilir
	 */
	public void finestLevel() {
		loglayici.setLevel(Level.FINEST);
	}

	/**
	 * Sadece hata mesajlari ekrana yazilir
	 */
	public void severeLevel() {
		loglayici.setLevel(Level.SEVERE);
	}

	public static LogHelper getInstance() {
		if (instance == null) {
			instance = new LogHelper();
		}
		return instance;
	}

	/**
	 * Elle hata bildirimi icin kullanilmali
	 * 
	 * @param msg
	 *            Hata mesaji
	 */
	public void hata(String msg) {
		loglayici.severe(msg);
	}

	/**
	 * Herhangi bir bilgi mesaji vermek icin kullanilmali
	 * 
	 * @param msg
	 *            Bilgi mesaji
	 */
	public void finest(String msg) {
		if (loglayici.isLoggable(Level.FINEST)) {
			loglayici.finest(msg);
		}
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
