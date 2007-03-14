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
package net.kodveus.kumanifest.report;

import java.net.URL;

import javax.swing.JApplet;
import javax.swing.JOptionPane;

import net.kodveus.kumanifest.utility.LogHelper;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperPrintManager;
import net.sf.jasperreports.engine.util.JRLoader;

/**
 * TODO Bu sinif uygulamaya uygun bir hale getirilmeli
 * 
 * @author emre
 * 
 */
public class PrintReport extends JApplet {

	private static final long serialVersionUID = 1L;

	private URL url = null;

	JasperPrint jasperPrint = null;

	public PrintReport() {
		// initComponents();
	}

	@Override
	public void init() {
		String strUrl = getParameter("url");// JasperPrinti gonderecek olan
		// metodun adresi
		LogHelper.getLogger().info(strUrl);
		if (strUrl != null) {
			try {
				url = getURL(strUrl);
				printReport();
			} catch (Exception e) {
				JOptionPane.showMessageDialog(this, e.toString());
			}
		} else {
			JOptionPane.showMessageDialog(this, "URL Hatasý");
		}
	}

	private void printReport() {
		if (url != null) {
			try {
				jasperPrint = (JasperPrint) JRLoader.loadObject(url);
			} catch (Exception e) {
				JOptionPane.showMessageDialog(this, e.toString());
			}

			if (jasperPrint != null) {
				Thread thread = new Thread(new Runnable() {
					public void run() {
						try {
							if (jasperPrint.getPages() != null
									&& jasperPrint.getPages().size() > 0) {
								JasperPrintManager.printReport(jasperPrint,
										false);
							} else {
								JOptionPane.showMessageDialog(null,
										"Yeterli Sayýda Sayfa Yok!");
							}
						} catch (Exception e) {
							JOptionPane.showMessageDialog(null, e.toString());
						}
					}
				});
				thread.start();
			} else {
				JOptionPane.showMessageDialog(this, "Boþ Rapor");
			}
		} else {
			JOptionPane.showMessageDialog(this, "URL Hatasý");
		}
	}

	private URL getURL(String path) throws Exception {
		URL _url = this.getCodeBase();
		URL newUrl = new URL(_url.getProtocol(), _url.getHost(),
				_url.getPort(), path);
		return newUrl;
	}
}