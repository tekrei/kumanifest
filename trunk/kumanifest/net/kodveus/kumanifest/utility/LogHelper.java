package net.kodveus.kumanifest.utility;

import java.util.logging.Level;
import java.util.logging.Logger;

public class LogHelper {
	private static Logger loglayici = Logger.getLogger("KUMANIFEST LOG");

	private static LogHelper instance = null;

	private LogHelper() {
	}

	/**
	 * Calisma zamaninda log seviyesini degistirmek icin kullanilmalidir
	 * 
	 * Tum mesajlar ekrana yazilir
	 */
	public void infoLevel() {
		loglayici.setLevel(Level.INFO);
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
	public void bilgi(String msg) {
		loglayici.info(msg);
	}

	/**
	 * Istisna olustugunda cagrilmali
	 * 
	 * @param e
	 *            Olusan istisna
	 */
	public void istisna(Exception e) {
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
	public void istisna(String msg, Exception e) {
		loglayici.log(Level.SEVERE, msg, e);
	}
}
