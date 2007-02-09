package net.kodveus.kumanifest.database;

import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class DatabaseConfiguration extends JDialog {

	private static final long serialVersionUID = 1L;

	private JLabel jLabel = null;

	private JLabel jLabel1 = null;

	private JLabel jLabel11 = null;

	private JLabel jLabel12 = null;

	// private JCheckBox chkTabloGoruntu = null;

	private JButton btnTamam = null;

	private JTextField txtAdres = null;

	private JTextField txtVeritabani = null;

	private JTextField txtKullanici = null;

	private JPasswordField txtSifre = null;

	/**
	 * This is the default constructor
	 */
	public DatabaseConfiguration() {
		super();
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		jLabel12 = new JLabel();
		jLabel12.setBounds(new Rectangle(10, 110, 101, 21));
		jLabel12.setText("Password:");
		jLabel11 = new JLabel();
		jLabel11.setBounds(new Rectangle(10, 80, 101, 21));
		jLabel11.setText("User Name:");
		jLabel1 = new JLabel();
		jLabel1.setBounds(new Rectangle(10, 50, 101, 21));
		jLabel1.setText("Database Name:");
		jLabel = new JLabel();
		jLabel.setBounds(new Rectangle(10, 20, 101, 21));
		jLabel.setText("Server Address:");
		this.setSize(320, 230);
		this.setLocationRelativeTo(null);
		this.setLayout(null);
		this.setTitle("Database Configuration");
		this.add(jLabel, null);
		this.add(jLabel1, null);
		this.add(jLabel11, null);
		this.add(jLabel12, null);
		// this.add(getChkTabloGoruntu(), null);
		this.add(getBtnTamam(), null);
		this.add(getTxtAdres(), null);
		this.add(getTxtVeritabani(), null);
		this.add(getTxtKullanici(), null);
		this.add(getTxtSifre(), null);
		loadParamsIfExist();
		this.setModal(true);
		this.setVisible(true);
		this
				.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
	}

	private void loadParamsIfExist() {
		KUMProperties props = new KUMProperties();
		if (props.load()) {
			txtAdres.setText(props.getProperty("adres"));
			txtVeritabani.setText(props.getProperty("vtAdi"));
			txtKullanici.setText(props.getProperty("kullanici"));
			txtSifre.setText(props.getProperty("sifre"));
		}
	}

	/**
	 * This method initializes chkTabloGoruntu
	 * 
	 * @return javax.swing.JCheckBox
	 */
	/*
	 * private JCheckBox getChkTabloGoruntu() { if (chkTabloGoruntu == null) {
	 * chkTabloGoruntu = new JCheckBox(); chkTabloGoruntu.setBounds(new
	 * Rectangle(10, 140, 281, 21)); chkTabloGoruntu.setText("Create database
	 * structure"); } return chkTabloGoruntu; }
	 */

	/**
	 * This method initializes btnTamam
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getBtnTamam() {
		if (btnTamam == null) {
			btnTamam = new JButton();
			btnTamam.setBounds(new Rectangle(60, 170, 171, 21));
			btnTamam.setText("OK");
			btnTamam.setMnemonic('o');
			btnTamam.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					configureDatabase();
				}
			});

		}
		return btnTamam;
	}

	private void configureDatabase() {
		KUMProperties props = new KUMProperties();
		props.put("adres", txtAdres.getText());
		props.put("kullanici", txtKullanici.getText());
		props.put("sifre", new String(txtSifre.getPassword()));
		props.put("vtAdi", txtVeritabani.getText());
		props.put("vtTipi", "MYSQL");
		// First check db configuration
		if (checkConf(props)) {
			if (props.save()) {
				/*
				 * if (chkTabloGoruntu.isSelected()) {
				 * createDatabaseStructure(props); }
				 */
				this.dispose();
			}
		}
	}

	private boolean checkConf(KUMProperties props) {
		// Check if configuration is correct
		boolean result = false;
		try {
			DBManager.check(props);
			result = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/*
	 * private void createDatabaseStructure(KUMProperties props) { try { //
	 * Execute a command without arguments String command = "mysql -h " +
	 * props.getProperty("adres") + " -u " + props.getProperty("kullanici") + "
	 * -p" + props.getProperty("sifre") + " " + props.getProperty("vtAdi") + " <
	 * kumanifest.sql"; System.out.println("command:"+command); Process proc =
	 * Runtime.getRuntime().exec(command); BufferedReader br = new
	 * BufferedReader(new InputStreamReader(proc.getErrorStream())); String line =
	 * null; System.out.println("<ERROR>"); while ( (line = br.readLine()) !=
	 * null) System.out.println(line); System.out.println("</ERROR>"); br = new
	 * BufferedReader(new InputStreamReader(proc.getInputStream()));
	 * System.out.println("<OUTPUT>"); while ( (line = br.readLine()) != null)
	 * System.out.println(line); System.out.println("</OUTPUT>");
	 * System.out.println("Process exitValue: " + proc.waitFor());
	 *  } catch (Exception e) { e.printStackTrace(); } }
	 */

	/**
	 * This method initializes txtAdres
	 * 
	 * @return javax.swing.JTextField
	 */
	private JTextField getTxtAdres() {
		if (txtAdres == null) {
			txtAdres = new JTextField();
			txtAdres.setBounds(new Rectangle(120, 20, 171, 21));
		}
		return txtAdres;
	}

	/**
	 * This method initializes txtVeritabani
	 * 
	 * @return javax.swing.JTextField
	 */
	private JTextField getTxtVeritabani() {
		if (txtVeritabani == null) {
			txtVeritabani = new JTextField();
			txtVeritabani.setBounds(new Rectangle(120, 50, 171, 21));
		}
		return txtVeritabani;
	}

	/**
	 * This method initializes txtKullanici
	 * 
	 * @return javax.swing.JTextField
	 */
	private JTextField getTxtKullanici() {
		if (txtKullanici == null) {
			txtKullanici = new JTextField();
			txtKullanici.setBounds(new Rectangle(120, 80, 171, 21));
		}
		return txtKullanici;
	}

	/**
	 * This method initializes txtSifre
	 * 
	 * @return javax.swing.JTextField
	 */
	private JTextField getTxtSifre() {
		if (txtSifre == null) {
			txtSifre = new JPasswordField();
			txtSifre.setBounds(new Rectangle(120, 110, 171, 21));
		}
		return txtSifre;
	}

}
