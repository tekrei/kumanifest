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
