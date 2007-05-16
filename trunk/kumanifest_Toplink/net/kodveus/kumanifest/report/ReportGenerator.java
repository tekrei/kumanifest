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

import net.kodveus.kumanifest.persistence.PersistenceManager;
import net.sf.jasperreports.engine.query.JRJpaQueryExecuterFactory;

public class ReportGenerator {

	private static ReportGenerator instance = null;

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

	public void generateManifest(Long voyageId) throws Exception {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("prmVoyageId", voyageId);
		raporlama.createEmptyReport("manifest/EnglishManifest.jrxml");
		map.put("REPORT_DIR", Reporting.rootPath + "manifest/");
		exportReport(map);
	}

	public void generateBillOfLading(Long blId) throws Exception {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("prmBLId", blId);
		raporlama.createEmptyReport("billoflading/BillOfLading.jrxml");
		map.put("REPORT_DIR", Reporting.rootPath + "billoflading/");
		exportReport(map);
	}

	public void generateLoadingList(Long voyageId) throws Exception {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("prmVoyageId", voyageId);
		raporlama.createEmptyReport("loadinglist/LoadingList.jrxml");
		map.put("REPORT_DIR", Reporting.rootPath + "loadinglist/");
		exportReport(map);
	}

	private void exportReport(HashMap<String, Object> map) throws Exception {
		//TODO raporlarin sorgulari duzeltilecek
		//http://marceloverdijk.blogspot.com/search/label/JasperReports
		map.put(JRJpaQueryExecuterFactory.PARAMETER_JPA_ENTITY_MANAGER,
				PersistenceManager.getInstance().getEM());
		raporlama.fillReport(map);
		raporlama.showReportDesign();
	}
}
