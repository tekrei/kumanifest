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

import net.kodveus.kumanifest.utility.LogHelper;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperPrintManager;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.export.JRCsvExporter;
import net.sf.jasperreports.engine.export.JRHtmlExporter;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.export.JRXlsExporterParameter;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

import java.io.OutputStream;
import java.sql.Connection;
import java.util.Map;

public class Reporting {
	public enum RAPORLAMA_CIKTI_TIPI {
		CSV, HTML, PDF, XLS
	}

	public static final String rootPath = "net/kodveus/kumanifest/report/source/";

	public static String getDosyaUzanti(final String tip) {
		if (tip.equals(RAPORLAMA_CIKTI_TIPI.XLS))
			return ".xls";
		if (tip.equals(RAPORLAMA_CIKTI_TIPI.HTML))
			return ".html";
		if (tip.equals(RAPORLAMA_CIKTI_TIPI.CSV))
			return ".csv";
		return ".pdf";
	}

	private JRExporter exporter;

	/*
	 * public JRResultSetDataSource getReportData(ResultSet raporSorguSonucu) {
	 * return new JRResultSetDataSource(raporSorguSonucu); }
	 */

	private JasperPrint print;

	private JasperReport report;

	public Reporting() {
	}

	private boolean createCsvFile(final String hedef) throws Exception {
		exporter = new JRCsvExporter();
		exportAsFile(hedef);
		return true;
	}

	public boolean createEmptyReport(final String raporTasarimDosyasi) {
		try {
			report = JasperCompileManager.compileReport(rootPath
					+ raporTasarimDosyasi);
			return true;
		} catch (final Exception e) {
			LogHelper.getInstance().exception(e);
		}
		return false;
	}

	private boolean createHtmlFile(final String hedef) throws Exception {
		exporter = new JRHtmlExporter();
		exportAsFile(hedef);
		return true;
	}

	private boolean createPdfFile(final String hedef) throws Exception {
		exporter = new JRPdfExporter();
		exportAsFile(hedef);
		return true;
	}

	private boolean createXlsFile(final String hedef) throws Exception {
		exporter = new JRXlsExporter();
		exporter.setParameter(JRXlsExporterParameter.IS_ONE_PAGE_PER_SHEET,
				Boolean.TRUE);
		exportAsFile(hedef);
		return true;
	}

	public boolean exportAsFile(final RAPORLAMA_CIKTI_TIPI tip,
			final String hedefDosya) {
		try {
			if (tip.equals(RAPORLAMA_CIKTI_TIPI.XLS))
				return createXlsFile(hedefDosya);
			if (tip.equals(RAPORLAMA_CIKTI_TIPI.HTML))
				return createHtmlFile(hedefDosya);
			if (tip.equals(RAPORLAMA_CIKTI_TIPI.CSV))
				return createCsvFile(hedefDosya);
			if (tip.equals(RAPORLAMA_CIKTI_TIPI.PDF))
				return createPdfFile(hedefDosya);
		} catch (final Exception e) {
			LogHelper.getInstance().exception(e);
		}
		return false;
	}

	private void exportAsFile(final String hedefDosya) throws Exception {
		exporter.setParameter(JRExporterParameter.JASPER_PRINT, print);
		exporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME, hedefDosya);
		exporter.setParameter(JRExporterParameter.CHARACTER_ENCODING, "Cp1254");
		exporter.exportReport();
	}

	private void exportAsStream(final OutputStream hedefStream)
			throws Exception {
		exporter.setParameter(JRExporterParameter.JASPER_PRINT, print);
		exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, hedefStream);
		exporter.setParameter(JRExporterParameter.CHARACTER_ENCODING, "Cp1254");
		exporter.exportReport();
	}

	public boolean fillReport(final Map<String, Object> parametreler) {
		try {
			print = JasperFillManager.fillReport(report, parametreler);
			return true;
		} catch (final JRException ex) {
			LogHelper.getInstance().exception(ex);
		}
		return false;
	}

	public boolean fillReport(final Map<String, Object> parametreler,
			final Connection connection) {
		try {
			print = JasperFillManager.fillReport(report, parametreler,
					connection);
			return true;
		} catch (final JRException e) {
			LogHelper.getInstance().exception(e);
		}
		return false;
	}

	public boolean fillReport(final Map<String, Object> parametreler,
			final JRDataSource raporVerisi) {
		try {
			print = JasperFillManager.fillReport(report, parametreler,
					raporVerisi);
			return true;
		} catch (final JRException e) {
			LogHelper.getInstance().exception(e);
		}
		return false;
	}

	private OutputStream getCsvStream(final OutputStream hedefStream)
			throws Exception {
		exporter = new JRCsvExporter();
		exportAsStream(hedefStream);
		return hedefStream;
	}

	private OutputStream getHtmlStream(final OutputStream hedefStream)
			throws Exception {
		exporter = new JRHtmlExporter();
		exportAsStream(hedefStream);
		return hedefStream;
	}

	private OutputStream getPdfStream(final OutputStream hedefStream)
			throws Exception {
		exporter = new JRPdfExporter();
		exportAsStream(hedefStream);
		return hedefStream;
	}

	public JasperPrint getPrint() {
		return print;
	}

	public OutputStream getStream(final String tip,
			final OutputStream hedefStream) {
		try {
			if (tip.equals(RAPORLAMA_CIKTI_TIPI.XLS))
				return getXlsStream(hedefStream);
			if (tip.equals(RAPORLAMA_CIKTI_TIPI.HTML))
				return getHtmlStream(hedefStream);
			if (tip.equals(RAPORLAMA_CIKTI_TIPI.CSV))
				return getCsvStream(hedefStream);
			if (tip.equals(RAPORLAMA_CIKTI_TIPI.PDF))
				return getPdfStream(hedefStream);
			return hedefStream;
		} catch (final Exception e) {
			LogHelper.getInstance().exception(e);
			return null;
		}
	}

	private OutputStream getXlsStream(final OutputStream hedefStream)
			throws Exception {
		exporter = new JRXlsExporter();
		exporter.setParameter(JRXlsExporterParameter.IS_ONE_PAGE_PER_SHEET,
				Boolean.TRUE);
		exportAsStream(hedefStream);
		return hedefStream;
	}

	public JasperReport loadReport(final String filePath) {
		try {
			report = (JasperReport) JRLoader.loadObject(filePath);
			return report;
		} catch (final Exception e) {
			LogHelper.getInstance().exception(e);
		}
		return null;
	}

	public boolean printReport() {
		try {
			JasperPrintManager.printReport(print, false);
			return true;
		} catch (final Exception e) {
			LogHelper.getInstance().exception(e);
		}
		return false;
	}

	public boolean showReportDesign() {
		try {
			// FIX kapanmamasi icin ikinci parametre olarak false gonderiyoruz
			JasperViewer.viewReport(print, false);
			return true;
		} catch (final Exception e) {
			LogHelper.getInstance().exception(e);
			return false;
		}
	}

}