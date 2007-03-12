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

import java.util.HashMap;

import net.kodveus.kumanifest.database.DBManager;

public class ReportGenerator {

	private static ReportGenerator instance = null;

	// private JFileChooser fileChooser;

	private Reporting raporlama;

	private ReportGenerator() {
		raporlama = new Reporting();
	}

	public static ReportGenerator getInstance() {
		if (instance == null) {
			instance = new ReportGenerator();
		}
		return instance;
	}

	public void generateManifest(HashMap<String, Object> map) throws Exception {
		raporlama.createEmptyReport("manifest/EnglishManifest.jrxml");
		map.put("REPORT_DIR", Reporting.rootPath + "manifest/");
		exportReport(map);
	}

	public void generateBillOfLading(HashMap<String, Object> map)
			throws Exception {
		raporlama.createEmptyReport("billoflading/BillOfLading.jrxml");
		map.put("REPORT_DIR", Reporting.rootPath + "billoflading/");
		exportReport(map);
	}

	public void generateLoadingList(HashMap<String, Object> map)
			throws Exception {
		raporlama.createEmptyReport("loadinglist/LoadingList.jrxml");
		map.put("REPORT_DIR", Reporting.rootPath + "loadinglist/");
		exportReport(map);
	}

	private void exportReport(HashMap<String, Object> map) throws Exception {
		raporlama.fillReport(map, DBManager.getInstance().getConnection());
		// raporlama.exportAsFile(Raporlama.RAPORLAMA_CIKTI_TIPI.PDF,
		// getFileNameToSave());
		raporlama.showReportDesign();
	}

	/*
	 * private String getFileNameToSave() { String fileName = "cikti.pdf";
	 * JFileChooser tempFileChooser = getFileChooser();
	 * 
	 * if (tempFileChooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
	 * fileName = tempFileChooser.getSelectedFile().getPath(); } return
	 * fileName; }
	 * 
	 * private JFileChooser getFileChooser() { if (fileChooser == null) {
	 * fileChooser = new JFileChooser(); } return fileChooser; }
	 */
}
