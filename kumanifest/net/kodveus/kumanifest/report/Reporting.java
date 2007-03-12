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

import java.io.OutputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.Map;

import net.kodveus.kumanifest.utility.LogHelper;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JRResultSetDataSource;
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

public class Reporting {
	public static final String rootPath = "net/kodveus/kumanifest/report/source/";

	public Reporting() {
	}

	public boolean showReportDesign() {
		try {
			// FIX kapanmamasi icin ikinci parametre olarak false gonderiyoruz
			JasperViewer.viewReport(print, false);
			return true;
		} catch (Exception e) {
			LogHelper.getInstance().exception(e);
			return false;
		}
	}

	public boolean createEmptyReport(String raporTasarimDosyasi) {
		try {
			report = JasperCompileManager.compileReport(rootPath
					+ raporTasarimDosyasi);
			return true;
		} catch (Exception e) {
			LogHelper.getInstance().exception(e);
		}
		return false;
	}

	public JRResultSetDataSource getReportData(ResultSet raporSorguSonucu) {
		return new JRResultSetDataSource(raporSorguSonucu);
	}

	public boolean fillReport(Map<String, Object> parametreler,
			JRDataSource raporVerisi) {
		try {
			print = JasperFillManager.fillReport(report, parametreler,
					raporVerisi);
			return true;
		} catch (JRException e) {
			LogHelper.getInstance().exception(e);
		}
		return false;
	}

	public boolean fillReport(Map<String, Object> parametreler,
			Connection connection) {
		try {
			print = JasperFillManager.fillReport(report, parametreler,
					connection);
			return true;
		} catch (JRException e) {
			LogHelper.getInstance().exception(e);
		}
		return false;
	}

	public JasperReport loadReport(String filePath) {
		try {
			report = (JasperReport) JRLoader.loadObject(filePath);
			return report;
		} catch (Exception e) {
			LogHelper.getInstance().exception(e);
		}
		return null;
	}

	private boolean createHtmlFile(String hedef) throws Exception {
		exporter = new JRHtmlExporter();
		exportAsFile(hedef);
		return true;
	}

	private boolean createPdfFile(String hedef) throws Exception {
		exporter = new JRPdfExporter();
		exportAsFile(hedef);
		return true;
	}

	private boolean createXlsFile(String hedef) throws Exception {
		exporter = new JRXlsExporter();
		exporter.setParameter(JRXlsExporterParameter.IS_ONE_PAGE_PER_SHEET,
				Boolean.TRUE);
		exportAsFile(hedef);
		return true;
	}

	private boolean createCsvFile(String hedef) throws Exception {
		exporter = new JRCsvExporter();
		exportAsFile(hedef);
		return true;
	}

	private OutputStream getPdfStream(OutputStream hedefStream)
			throws Exception {
		exporter = new JRPdfExporter();
		exportAsStream(hedefStream);
		return hedefStream;
	}

	private OutputStream getXlsStream(OutputStream hedefStream)
			throws Exception {
		exporter = new JRXlsExporter();
		exporter.setParameter(JRXlsExporterParameter.IS_ONE_PAGE_PER_SHEET,
				Boolean.TRUE);
		exportAsStream(hedefStream);
		return hedefStream;
	}

	private OutputStream getHtmlStream(OutputStream hedefStream)
			throws Exception {
		exporter = new JRHtmlExporter();
		exportAsStream(hedefStream);
		return hedefStream;
	}

	private OutputStream getCsvStream(OutputStream hedefStream)
			throws Exception {
		exporter = new JRCsvExporter();
		exportAsStream(hedefStream);
		return hedefStream;
	}

	public OutputStream getStream(String tip, OutputStream hedefStream) {
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
		} catch (Exception e) {
			LogHelper.getInstance().exception(e);
			return null;
		}
	}

	public boolean exportAsFile(RAPORLAMA_CIKTI_TIPI tip, String hedefDosya) {
		try {
			if (tip.equals(RAPORLAMA_CIKTI_TIPI.XLS))
				return createXlsFile(hedefDosya);
			if (tip.equals(RAPORLAMA_CIKTI_TIPI.HTML))
				return createHtmlFile(hedefDosya);
			if (tip.equals(RAPORLAMA_CIKTI_TIPI.CSV))
				return createCsvFile(hedefDosya);
			if (tip.equals(RAPORLAMA_CIKTI_TIPI.PDF))
				return createPdfFile(hedefDosya);
		} catch (Exception e) {
			LogHelper.getInstance().exception(e);
		}
		return false;
	}

	public static String getDosyaUzanti(String tip) {
		if (tip.equals(RAPORLAMA_CIKTI_TIPI.XLS))
			return ".xls";
		if (tip.equals(RAPORLAMA_CIKTI_TIPI.HTML))
			return ".html";
		if (tip.equals(RAPORLAMA_CIKTI_TIPI.CSV))
			return ".csv";
		return ".pdf";
	}

	public boolean printReport() {
		try {
			JasperPrintManager.printReport(print, false);
			return true;
		} catch (Exception e) {
			LogHelper.getInstance().exception(e);
		}
		return false;
	}

	public JasperPrint getPrint() {
		return print;
	}

	private void exportAsStream(OutputStream hedefStream) throws Exception {
		exporter.setParameter(JRExporterParameter.JASPER_PRINT, print);
		exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, hedefStream);
		exporter.setParameter(JRExporterParameter.CHARACTER_ENCODING, "Cp1254");
		exporter.exportReport();
	}

	private void exportAsFile(String hedefDosya) throws Exception {
		exporter.setParameter(JRExporterParameter.JASPER_PRINT, print);
		exporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME, hedefDosya);
		exporter.setParameter(JRExporterParameter.CHARACTER_ENCODING, "Cp1254");
		exporter.exportReport();
	}

	private JRExporter exporter;

	private JasperReport report;

	private JasperPrint print;

	public enum RAPORLAMA_CIKTI_TIPI {
		PDF, XLS, HTML, CSV
	}

}