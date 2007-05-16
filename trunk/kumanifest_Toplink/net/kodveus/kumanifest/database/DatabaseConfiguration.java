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

import net.kodveus.kumanifest.utility.LogHelper;

class DatabaseConfiguration extends JDialog {

	private static final long serialVersionUID = 1L;

	private JLabel jLabel = null;

	private JLabel jLabel1 = null;

	private JLabel jLabel11 = null;

	private JLabel jLabel12 = null;

	private JButton btnTamam = null;

	private JTextField txtAdres = null;

	private JTextField txtVeritabani = null;

	private JTextField txtKullanici = null;

	private JPasswordField txtSifre = null;

	public DatabaseConfiguration() {
		super();
		initialize();
	}

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
			@Override
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

	void configureDatabase() {
		KUMProperties props = new KUMProperties();
		props.put("adres", txtAdres.getText());
		props.put("kullanici", txtKullanici.getText());
		props.put("sifre", new String(txtSifre.getPassword()));
		props.put("vtAdi", txtVeritabani.getText());
		props.put("vtTipi", "MYSQL");
		// First check db configuration
		if (checkConf(props)) {
			if (props.save()) {
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
			LogHelper.getInstance().exception(e);
		}
		return result;
	}

	private JTextField getTxtAdres() {
		if (txtAdres == null) {
			txtAdres = new JTextField();
			txtAdres.setBounds(new Rectangle(120, 20, 171, 21));
		}
		return txtAdres;
	}

	private JTextField getTxtVeritabani() {
		if (txtVeritabani == null) {
			txtVeritabani = new JTextField();
			txtVeritabani.setBounds(new Rectangle(120, 50, 171, 21));
		}
		return txtVeritabani;
	}

	private JTextField getTxtKullanici() {
		if (txtKullanici == null) {
			txtKullanici = new JTextField();
			txtKullanici.setBounds(new Rectangle(120, 80, 171, 21));
		}
		return txtKullanici;
	}

	private JTextField getTxtSifre() {
		if (txtSifre == null) {
			txtSifre = new JPasswordField();
			txtSifre.setBounds(new Rectangle(120, 110, 171, 21));
		}
		return txtSifre;
	}

}
