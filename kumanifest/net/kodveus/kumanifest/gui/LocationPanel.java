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
package net.kodveus.kumanifest.gui;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import net.kodveus.gui.arabirim.AramaSonucInterface;
import net.kodveus.gui.araclar.AramaSonuc;
import net.kodveus.gui.jcombobox.JSteppedComboBox;
import net.kodveus.kumanifest.interfaces.DugmeInterface;
import net.kodveus.kumanifest.jdo.Location;
import net.kodveus.kumanifest.operation.LocationOperation;
import net.kodveus.kumanifest.utility.GUIHelper;

public class LocationPanel extends JPanel implements AramaSonucInterface,
		DugmeInterface {
	private static final long serialVersionUID = 1L;

	private JLabel jLabel = null;

	private JLabel jLabel2 = null;

	private JTextField txtLocation = null;

	private JTextField txtCode = null;

	private AramaSonuc aramaSonuc = null;

	private Long id;

	private JLabel jLabel1 = null;

	private JSteppedComboBox cmbPort = null;

	private DugmePanel dugmePanel = null;

	public LocationPanel() {
		super();
		initialize();
	}

	private void initialize() {
		jLabel1 = new JLabel();
		jLabel1.setBounds(new java.awt.Rectangle(20, 80, 81, 21));
		jLabel1.setText("Port:");
		jLabel2 = new JLabel();
		jLabel2.setBounds(new java.awt.Rectangle(20, 20, 81, 21));
		jLabel2.setText("Location:");
		jLabel = new JLabel();
		jLabel.setBounds(new java.awt.Rectangle(20, 50, 81, 21));
		jLabel.setText("Code:");
		this.setLayout(null);
		this.setSize(new java.awt.Dimension(404, 331));
		this.add(jLabel, null);
		this.add(jLabel2, null);
		this.add(getTxtCode(), null);
		this.add(getAramaSonuc(), null);
		this.add(getTxtLocation(), null);
		this.add(jLabel1, null);
		this.add(getCmbPort(), null);
		this.add(getDugmePanel(), null);
		updateRecords();
	}

	private void updateRecords() {
		// Veritabanindan kayit cekip tabloya yukleyelim
		setSecili(new Location());
		aramaSonuc.listeGuncelle(LocationOperation.getInstance().ara(
				new Location()));
	}

	private JTextField getTxtLocation() {
		if (txtLocation == null) {
			txtLocation = new JTextField();
			txtLocation.setBounds(new java.awt.Rectangle(100, 20, 291, 21));
		}
		return txtLocation;
	}

	private JTextField getTxtCode() {
		if (txtCode == null) {
			txtCode = new JTextField();
			txtCode.setBounds(new java.awt.Rectangle(100, 50, 291, 21));
		}
		return txtCode;
	}

	private AramaSonuc getAramaSonuc() {
		if (aramaSonuc == null) {
			aramaSonuc = GUIHelper.getInstance().createAramaSonuc(
					(new Location()).getAliasMap(),
					new java.awt.Rectangle(20, 110, 371, 181), this);
		}
		return aramaSonuc;
	}

	public void setSecili(Object secili) {
		Location location = (Location) secili;
		id = location.getLocationId();
		txtLocation.setText(location.getLocation());
		cmbPort.setSelectedItem(location.getIsport());
		txtCode.setText(location.getCode());
		this.updateUI();
	}

	private JSteppedComboBox getCmbPort() {
		if (cmbPort == null) {
			cmbPort = new JSteppedComboBox(new Object[] { "Y", "N" });
			cmbPort.setBounds(new java.awt.Rectangle(100, 80, 141, 21));
		}
		return cmbPort;
	}

	public void save() {
		Location location = new Location();
		location.setLocation(txtLocation.getText());
		location.setIsport(cmbPort.getSelectedItem() + "");
		location.setCode(txtCode.getText());
		id = LocationOperation.getInstance().create(location);
		updateRecords();
	}

	public void delete() {
		if (id != null) {
			Location location = new Location();
			location.setLocationId(id);
			LocationOperation.getInstance().delete(location);
			updateRecords();
		}
	}

	public void update() {
		Location location = new Location();
		location.setLocation(txtLocation.getText());
		location.setIsport(cmbPort.getSelectedItem() + "");
		location.setCode(txtCode.getText());
		location.setLocationId(id);
		LocationOperation.getInstance().update(location);
		updateRecords();
	}

	private DugmePanel getDugmePanel() {
		if (dugmePanel == null) {
			dugmePanel = new DugmePanel();
			dugmePanel.setBounds(new java.awt.Rectangle(60, 290, 310, 37));
			dugmePanel.setDugmeListener(this);
		}
		return dugmePanel;
	}
} // @jve:decl-index=0:visual-constraint="10,10"
