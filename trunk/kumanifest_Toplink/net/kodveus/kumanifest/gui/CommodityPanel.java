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
import net.kodveus.kumanifest.interfaces.DugmeInterface;
import net.kodveus.kumanifest.jdo.Commodity;
import net.kodveus.kumanifest.operation.CommodityOperation;
import net.kodveus.kumanifest.utility.GUIHelper;

public class CommodityPanel extends JPanel implements AramaSonucInterface,
		DugmeInterface {

	private static final long serialVersionUID = 1L;

	private JLabel jLabel = null;

	private JLabel jLabel1 = null;

	private JLabel jLabel2 = null;

	private JTextField txtCode = null;

	private JTextField txtName = null;

	private JTextField txtDescription = null;

	private AramaSonuc aramaSonuc = null;

	private DugmePanel dugmePanel = null;

	private Long id;

	public CommodityPanel() {
		super();
		initialize();
	}

	private void initialize() {
		jLabel = new JLabel();
		jLabel.setBounds(new java.awt.Rectangle(20, 20, 81, 21));
		jLabel.setText("Code:");
		jLabel2 = new JLabel();
		jLabel2.setBounds(new java.awt.Rectangle(20, 50, 81, 21));
		jLabel2.setText("Name:");
		jLabel1 = new JLabel();
		jLabel1.setBounds(new java.awt.Rectangle(20, 80, 81, 21));
		jLabel1.setText("Description:");
		this.setLayout(null);
		this.setSize(new java.awt.Dimension(404, 331));
		this.add(jLabel, null);
		this.add(jLabel1, null);
		this.add(jLabel2, null);
		this.add(getTxtCode(), null);
		this.add(getTxtName(), null);
		this.add(getTxtDescription(), null);
		this.add(getAramaSonuc(), null);
		this.add(getDugmePanel(), null);
		updateRecords();
	}

	private void updateRecords() {
		// Veritabanindan kayit cekip tabloya yukleyelim
		setSecili(new Commodity());
		aramaSonuc.listeGuncelle(CommodityOperation.getInstance().findAll());
	}

	private JTextField getTxtCode() {
		if (txtCode == null) {
			txtCode = new JTextField();
			txtCode.setBounds(new java.awt.Rectangle(100, 20, 291, 21));
		}
		return txtCode;
	}

	private JTextField getTxtName() {
		if (txtName == null) {
			txtName = new JTextField();
			txtName.setBounds(new java.awt.Rectangle(100, 50, 291, 21));
		}
		return txtName;
	}

	private JTextField getTxtDescription() {
		if (txtDescription == null) {
			txtDescription = new JTextField();
			txtDescription.setBounds(new java.awt.Rectangle(100, 80, 291, 21));
		}
		return txtDescription;
	}

	private AramaSonuc getAramaSonuc() {
		if (aramaSonuc == null) {
			aramaSonuc = GUIHelper.getInstance().createAramaSonuc(
					(new Commodity()).getAliasMap(),
					new java.awt.Rectangle(20, 110, 371, 181), this);
		}
		return aramaSonuc;
	}

	public void setSecili(Object secili) {
		Commodity commodity = (Commodity) secili;
		id = commodity.getCommodityId();
		txtName.setText(commodity.getName());
		txtDescription.setText(commodity.getDescription());
		txtCode.setText(commodity.getCode());
		this.updateUI();
	}

	public void save() {
		Commodity commodity = new Commodity();
		commodity.setName(txtName.getText());
		commodity.setDescription(txtDescription.getText());
		commodity.setCode(txtCode.getText());
		id = CommodityOperation.getInstance().create(commodity);
		updateRecords();
	}

	public void delete() {
		if (id != null) {
			Commodity commodity = new Commodity();
			commodity.setCommodityId(id);
			CommodityOperation.getInstance().delete(commodity);
			updateRecords();
		}
	}

	public void update() {
		Commodity commodity = new Commodity();
		commodity.setName(txtName.getText());
		commodity.setDescription(txtDescription.getText());
		commodity.setCode(txtCode.getText());
		commodity.setCommodityId(id);
		CommodityOperation.getInstance().update(commodity);
		updateRecords();
	}

	private DugmePanel getDugmePanel() {
		if (dugmePanel == null) {
			dugmePanel = new DugmePanel();
			dugmePanel.setBounds(new java.awt.Rectangle(60, 290, 311, 38));
			dugmePanel.setDugmeListener(this);
		}
		return dugmePanel;
	}
}
