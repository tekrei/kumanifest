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

import net.kodveus.gui.jcombobox.JSteppedComboBox;
import net.kodveus.gui.jcombobox.TableSelectUI;
import net.kodveus.gui.jtextfield.NoTabTextArea;
import net.kodveus.kumanifest.interfaces.ToolbarInterface;
import net.kodveus.kumanifest.jdo.BL;
import net.kodveus.kumanifest.jdo.Location;
import net.kodveus.kumanifest.jdo.Voyage;
import net.kodveus.kumanifest.operation.BLOperation;
import net.kodveus.kumanifest.operation.LocationOperation;
import net.kodveus.kumanifest.operation.VoyageOperation;
import net.kodveus.kumanifest.utility.GUIHelper;
import net.kodveus.kumanifest.utility.StatusHelper;
import net.kodveus.kumanifest.utility.TreeHelper;

public class BLPanel extends JPanel implements ToolbarInterface {

	private static final long serialVersionUID = 1L;

	private JLabel jLabel = null;

	private JLabel jLabel1 = null;

	private JLabel jLabel2 = null;

	private JLabel jLabel3 = null;

	private JLabel jLabel4 = null;

	private JLabel jLabel5 = null;

	private JLabel jLabel6 = null;

	private JLabel jLabel7 = null;

	private JLabel jLabel8 = null;

	private JTextField txtBlNo = null;

	private JTextField txtCompany = null;

	private JLabel jLabel9 = null;

	private NoTabTextArea txtShipper = null;

	private JLabel jLabel10 = null;

	private NoTabTextArea txtConsignee = null;

	private JLabel jLabel11 = null;

	private NoTabTextArea txtNotify = null;

	private JLabel jLabel12 = null;

	private NoTabTextArea txtDescription = null;

	private JLabel jLabel13 = null;

	private NoTabTextArea txtDescription2 = null;

	private JLabel jLabel14 = null;

	private NoTabTextArea txtNotify2 = null;

	private Long id;

	private JSteppedComboBox cmbPlaceOfOrigin = null;

	private JSteppedComboBox cmbPortOfLoading = null;

	private JSteppedComboBox cmbPortOfDischarge = null;

	private JSteppedComboBox cmbFinalDischargePlace = null;

	private JSteppedComboBox cmbFinalDestination = null;

	private TableSelectUI cmbVoyage = null;

	private JSteppedComboBox cmbPlaceOfReceipt = null;

	public BLPanel() {
		super();
		initialize();
	}

	private void initialize() {
		jLabel14 = new JLabel();
		jLabel14.setBounds(new java.awt.Rectangle(370, 400, 71, 21));
		jLabel14.setText("Notify (2):");
		jLabel13 = new JLabel();
		jLabel13.setBounds(new java.awt.Rectangle(10, 400, 121, 21));
		jLabel13.setText("Description (2):");
		jLabel12 = new JLabel();
		jLabel12.setBounds(new java.awt.Rectangle(10, 290, 121, 21));
		jLabel12.setText("Description:");
		jLabel11 = new JLabel();
		jLabel11.setBounds(new java.awt.Rectangle(370, 290, 71, 21));
		jLabel11.setText("Notify:");
		jLabel10 = new JLabel();
		jLabel10.setBounds(new java.awt.Rectangle(370, 170, 71, 21));
		jLabel10.setText("Consignee:");
		jLabel9 = new JLabel();
		jLabel9.setBounds(new java.awt.Rectangle(370, 20, 71, 21));
		jLabel9.setText("Shipper:");
		jLabel8 = new JLabel();
		jLabel8.setBounds(new java.awt.Rectangle(10, 260, 121, 21));
		jLabel8.setText("Voyage:");
		jLabel7 = new JLabel();
		jLabel7.setBounds(new java.awt.Rectangle(10, 230, 121, 21));
		jLabel7.setText("Place of Receipt:");
		jLabel6 = new JLabel();
		jLabel6.setBounds(new java.awt.Rectangle(10, 200, 121, 21));
		jLabel6.setText("Final Destination:");
		jLabel5 = new JLabel();
		jLabel5.setBounds(new java.awt.Rectangle(10, 170, 121, 21));
		jLabel5.setText("Final Disc. Place:");
		jLabel4 = new JLabel();
		jLabel4.setBounds(new java.awt.Rectangle(10, 140, 121, 21));
		jLabel4.setText("Port of Discharge:");
		jLabel3 = new JLabel();
		jLabel3.setBounds(new java.awt.Rectangle(10, 110, 121, 21));
		jLabel3.setText("Port of Loading:");
		jLabel2 = new JLabel();
		jLabel2.setBounds(new java.awt.Rectangle(10, 80, 121, 21));
		jLabel2.setText("Place of Origin:");
		jLabel1 = new JLabel();
		jLabel1.setBounds(new java.awt.Rectangle(10, 50, 121, 21));
		jLabel1.setText("Company:");
		jLabel = new JLabel();
		jLabel.setBounds(new java.awt.Rectangle(10, 20, 121, 21));
		jLabel.setText("BL No:");
		this.setLayout(null);
		this.setSize(800, 700);
		this.setBorder(javax.swing.BorderFactory.createTitledBorder(null,
				"BL Details",
				javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION,
				javax.swing.border.TitledBorder.DEFAULT_POSITION, null, null));
		this.add(jLabel, null);
		this.add(jLabel1, null);
		this.add(jLabel2, null);
		this.add(jLabel3, null);
		this.add(jLabel4, null);
		this.add(jLabel5, null);
		this.add(jLabel6, null);
		this.add(jLabel7, null);
		this.add(jLabel8, null);
		this.add(getTxtBlNo(), null);
		this.add(getTxtCompany(), null);
		this.add(jLabel9, null);
		this.add(GUIHelper.getInstance().addScrollToTextArea(getTxtShipper()),
				null);
		this.add(jLabel10, null);
		this.add(
				GUIHelper.getInstance().addScrollToTextArea(getTxtConsignee()),
				null);
		this.add(jLabel11, null);
		this.add(GUIHelper.getInstance().addScrollToTextArea(getTxtNotify()),
				null);
		this.add(jLabel12, null);
		this.add(GUIHelper.getInstance().addScrollToTextArea(
				getTxtDescription()), null);
		this.add(jLabel13, null);
		this.add(GUIHelper.getInstance().addScrollToTextArea(
				getTxtDescription2()), null);
		this.add(jLabel14, null);
		this.add(getCmbPlaceOfOrigin(), null);
		this.add(getCmbPortOfLoading(), null);
		this.add(getCmbPortOfDischarge(), null);
		this.add(getCmbFinalDischargePlace(), null);
		this.add(getCmbFinalDestination(), null);
		this.add(getCmbVoyage(), null);
		this.add(getCmbPlaceOfReceipt(), null);
		this.add(GUIHelper.getInstance().addScrollToTextArea(getTxtNotify2()),
				null);
	}

	private JTextField getTxtBlNo() {
		if (txtBlNo == null) {
			txtBlNo = new JTextField();
			txtBlNo.setBounds(new java.awt.Rectangle(140, 20, 221, 21));
			txtBlNo.setName("txtBlNo");
		}
		return txtBlNo;
	}

	private JTextField getTxtCompany() {
		if (txtCompany == null) {
			txtCompany = new JTextField();
			txtCompany.setBounds(new java.awt.Rectangle(140, 50, 221, 21));
			txtCompany.setName("txtCompany");
		}
		return txtCompany;
	}

	private NoTabTextArea getTxtShipper() {
		if (txtShipper == null) {
			txtShipper = new NoTabTextArea();
			txtShipper.setBounds(new java.awt.Rectangle(450, 20, 261, 141));
			txtShipper.setWrapStyleWord(true);
			txtShipper.setLineWrap(true);
		}
		return txtShipper;
	}

	private NoTabTextArea getTxtConsignee() {
		if (txtConsignee == null) {
			txtConsignee = new NoTabTextArea();
			txtConsignee.setBounds(new java.awt.Rectangle(450, 170, 261, 111));
			txtConsignee.setWrapStyleWord(true);
			txtConsignee.setLineWrap(true);
		}
		return txtConsignee;
	}

	private NoTabTextArea getTxtNotify() {
		if (txtNotify == null) {
			txtNotify = new NoTabTextArea();
			txtNotify.setBounds(new java.awt.Rectangle(450, 290, 261, 101));
			txtNotify.setWrapStyleWord(true);
			txtNotify.setLineWrap(true);
		}
		return txtNotify;
	}

	private NoTabTextArea getTxtDescription() {
		if (txtDescription == null) {
			txtDescription = new NoTabTextArea();
			txtDescription
					.setBounds(new java.awt.Rectangle(140, 290, 221, 101));
			txtDescription.setWrapStyleWord(true);
			txtDescription.setLineWrap(true);
		}
		return txtDescription;
	}

	private NoTabTextArea getTxtDescription2() {
		if (txtDescription2 == null) {
			txtDescription2 = new NoTabTextArea();
			txtDescription2
					.setBounds(new java.awt.Rectangle(140, 400, 221, 101));
			txtDescription2.setWrapStyleWord(true);
			txtDescription2.setLineWrap(true);
		}
		return txtDescription2;
	}

	private NoTabTextArea getTxtNotify2() {
		if (txtNotify2 == null) {
			txtNotify2 = new NoTabTextArea();
			txtNotify2.setBounds(new java.awt.Rectangle(450, 400, 261, 101));
			txtNotify2.setWrapStyleWord(true);
			txtNotify2.setLineWrap(true);
		}
		return txtNotify2;
	}

	private BL generateRecordFromGUI() {
		BL bl = new BL();
		bl.setBlNo(txtBlNo.getText());
		bl.setCompanyName(txtCompany.getText());
		bl.setConsignee(txtConsignee.getText());
		bl.setDescription(txtDescription.getText());
		bl.setDescription2(txtDescription2.getText());
		bl
				.setFinalDestination((Location) cmbFinalDestination
						.getSelectedItem());
		bl.setFinalDischargePlace((Location) cmbFinalDischargePlace
				.getSelectedItem());
		bl.setNotify(txtNotify.getText());
		bl.setNotify2(txtNotify2.getText());
		bl.setPlaceOfOrigin((Location) cmbPlaceOfOrigin.getSelectedItem());
		bl.setPlaceOfReceipt((Location) cmbPlaceOfReceipt.getSelectedItem());
		bl.setPortOfDischarge((Location) cmbPortOfDischarge.getSelectedItem());
		bl.setPortOfLoading((Location) cmbPortOfLoading.getSelectedItem());
		bl.setVoyage((Voyage) cmbVoyage.getSelectedItem());
		bl.setShipper(txtShipper.getText());
		bl.setBlId(id);
		return bl;
	}

	public void loadToPanel(BL bl) {
		txtBlNo.setText(bl.getBlNo());
		txtCompany.setText(bl.getCompanyName());
		txtConsignee.setText(bl.getConsignee());
		txtDescription.setText(bl.getDescription());
		txtDescription2.setText(bl.getDescription2());
		cmbFinalDestination.setSelectedItem(bl.getFinalDestination());
		cmbFinalDischargePlace.setSelectedItem(bl.getFinalDischargePlace());
		txtNotify.setText(bl.getNotify());
		txtNotify2.setText(bl.getNotify2());
		cmbPlaceOfOrigin.setSelectedItem(bl.getPlaceOfOrigin());
		cmbPortOfDischarge.setSelectedItem(bl.getPortOfDischarge());
		cmbPortOfLoading.setSelectedItem(bl.getPortOfLoading());
		cmbPlaceOfReceipt.setSelectedItem(bl.getPlaceOfReceipt());
		txtShipper.setText(bl.getShipper());
		cmbVoyage.setSelectedItem(bl.getVoyage());
		id = bl.getBlId();
	}

	public void clear() {
		loadToPanel(new BL());
	}

	public void add() {
		BL bl = generateRecordFromGUI();
		long tempId = BLOperation.getInstance().create(bl);
		if (tempId > 0) {
			id = tempId;
			StatusHelper.getInstance().kayitEklendi();
			TreeHelper.getInstance().updateTree();
		} else {
			StatusHelper.getInstance().hataOlustu();
		}
	}

	public void delete() {
		if (BLOperation.getInstance().delete(generateRecordFromGUI())) {
			StatusHelper.getInstance().kayitSilindi();
			// Silince ekrani temizleyelim
			clear();
			TreeHelper.getInstance().updateTree();
		} else {
			StatusHelper.getInstance().hataOlustu();
		}
	}

	public void first() {
		// Ilk bl'i getirmemiz gerekiyor
		BL bl = (BL) BLOperation.getInstance().next(new Long(0));
		if (bl != null) {
			loadToPanel(bl);
		}
	}

	public void last() {
		// Son bl'i getirmemiz gerekiyor
		BL bl = (BL) BLOperation.getInstance().previous(Long.MAX_VALUE);
		if (bl != null) {
			loadToPanel(bl);
		}
	}

	public void next() {
		// Sonraki BL
		BL bl = (BL) BLOperation.getInstance().next(id);
		if (bl != null) {
			loadToPanel(bl);
		}
	}

	public void previous() {
		// Sonraki BL
		BL bl = (BL) BLOperation.getInstance().previous(id);
		if (bl != null) {
			loadToPanel(bl);
		}
	}

	public void update() {
		if (BLOperation.getInstance().update(generateRecordFromGUI())) {
			StatusHelper.getInstance().kayitGuncellendi();
			TreeHelper.getInstance().updateTree();
		} else {
			StatusHelper.getInstance().hataOlustu();
		}
	}

	private JSteppedComboBox getCmbPlaceOfOrigin() {
		if (cmbPlaceOfOrigin == null) {
			cmbPlaceOfOrigin = new JSteppedComboBox(LocationOperation
					.getInstance().getPorts().toArray());
			cmbPlaceOfOrigin
					.setBounds(new java.awt.Rectangle(140, 79, 221, 22));
		}
		return cmbPlaceOfOrigin;
	}

	private JSteppedComboBox getCmbPortOfLoading() {
		if (cmbPortOfLoading == null) {
			cmbPortOfLoading = new JSteppedComboBox(LocationOperation
					.getInstance().getPorts().toArray());
			cmbPortOfLoading
					.setBounds(new java.awt.Rectangle(140, 110, 221, 21));
		}
		return cmbPortOfLoading;
	}

	private JSteppedComboBox getCmbPortOfDischarge() {
		if (cmbPortOfDischarge == null) {
			cmbPortOfDischarge = new JSteppedComboBox(LocationOperation
					.getInstance().getPorts().toArray());
			cmbPortOfDischarge.setBounds(new java.awt.Rectangle(140, 140, 221,
					21));
		}
		return cmbPortOfDischarge;
	}

	private JSteppedComboBox getCmbFinalDischargePlace() {
		if (cmbFinalDischargePlace == null) {
			cmbFinalDischargePlace = new JSteppedComboBox(LocationOperation
					.getInstance().getPorts().toArray());
			cmbFinalDischargePlace.setBounds(new java.awt.Rectangle(140, 170,
					221, 21));
		}
		return cmbFinalDischargePlace;
	}

	private JSteppedComboBox getCmbFinalDestination() {
		if (cmbFinalDestination == null) {
			cmbFinalDestination = new JSteppedComboBox(LocationOperation
					.getInstance().getPorts().toArray());
			cmbFinalDestination.setBounds(new java.awt.Rectangle(140, 200, 221,
					21));
		}
		return cmbFinalDestination;
	}

	private TableSelectUI getCmbVoyage() {
		if (cmbVoyage == null) {
			Voyage temp = new Voyage();
			cmbVoyage = new TableSelectUI(VoyageOperation.getInstance().findAll(),null,temp.getAliasMap());
			cmbVoyage.setBounds(new java.awt.Rectangle(140, 260, 221, 21));
		}
		return cmbVoyage;
	}

	private JSteppedComboBox getCmbPlaceOfReceipt() {
		if (cmbPlaceOfReceipt == null) {
			cmbPlaceOfReceipt = new JSteppedComboBox(LocationOperation
					.getInstance().getPorts().toArray());
			cmbPlaceOfReceipt.setBounds(new java.awt.Rectangle(140, 230, 221,
					21));
		}
		return cmbPlaceOfReceipt;
	}

	public Long getSelectedBlId() {
		return id;
	}
}
