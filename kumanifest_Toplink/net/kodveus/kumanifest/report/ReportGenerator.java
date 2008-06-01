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

import net.kodveus.kumanifest.db.DBConnectionManager;
import net.kodveus.kumanifest.persistence.PersistenceManager;
import net.sf.jasperreports.engine.query.JRJpaQueryExecuterFactory;

public class ReportGenerator {

	private static ReportGenerator instance = null;

	public static ReportGenerator getInstance() {
		if (instance == null) {
			instance = new ReportGenerator();
		}
		return instance;
	}

	/**
	 * Deneme amacli raporu denemek icin yazilmis ana metod
	 * 
	 * @param args
	 */
	/*
	 * public static void main(final String[] args) { try {
	 * ReportGenerator.getInstance().generateLoadingList(1L); } catch (final
	 * Exception e) { e.printStackTrace(); } }
	 */

	private final Reporting raporlama;

	private ReportGenerator() {
		raporlama = new Reporting();
	}

	protected void exportReportUsingJPA(final HashMap<String, Object> map)
			throws Exception {
		// TODO raporlarin sorgulari duzeltilmeli ve hepsi EJBQL haline getirilmeli
		map.put(JRJpaQueryExecuterFactory.PARAMETER_JPA_ENTITY_MANAGER,
				PersistenceManager.getInstance().getEM());
		raporlama.fillReport(map);
		raporlama.showReportDesign();
	}

	private void exportReport(final HashMap<String, Object> map)
			throws Exception {
		raporlama.fillReport(map, DBConnectionManager.getConnection());
		raporlama.showReportDesign();
	}

	public void generateBillOfLading(final Long blId) throws Exception {
		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("prmBLId", blId);
		raporlama.createEmptyReport("billoflading/BillOfLading.jrxml");
		map.put("REPORT_DIR", Reporting.rootPath + "billoflading/");
		exportReport(map);
	}

	/**
	 * Bu rapor deneme amacli bir rapordur
	 * 
	 * @throws Exception
	 */
	public void generateBLList() throws Exception {
		final HashMap<String, Object> map = new HashMap<String, Object>();

		raporlama.createEmptyReport("BLList.jrxml");
		map.put("REPORT_DIR", Reporting.rootPath + "loadinglist/");
		exportReport(map);
	}

	public void generateLoadingList(final Long voyageId) throws Exception {
		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("prmVoyageId", voyageId);
		raporlama.createEmptyReport("loadinglist/LoadingList.jrxml");
		map.put("REPORT_DIR", Reporting.rootPath + "loadinglist/");
		exportReport(map);
	}

	public void generateManifest(final Long voyageId) throws Exception {
		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("prmVoyageId", voyageId);
		raporlama.createEmptyReport("manifest/EnglishManifest.jrxml");
		map.put("REPORT_DIR", Reporting.rootPath + "manifest/");
		exportReport(map);
	}
}
