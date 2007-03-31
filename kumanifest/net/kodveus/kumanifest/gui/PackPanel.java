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

import net.kodveus.gui.araclar.AramaSonuc;
import net.kodveus.gui.araclar.AramaSonucInterface;
import net.kodveus.kumanifest.interfaces.DugmeInterface;
import net.kodveus.kumanifest.jdo.Pack;
import net.kodveus.kumanifest.operation.PackOperation;
import net.kodveus.kumanifest.utility.GUIHelper;

public class PackPanel extends JPanel implements AramaSonucInterface,
		DugmeInterface {

	private static final long serialVersionUID = 1L;

	private JLabel jLabel = null;

	private JLabel jLabel1 = null;

	private JTextField txtType = null;

	private JTextField txtDescription = null;

	private AramaSonuc aramaSonuc = null;

	private Long id;

	private DugmePanel dugmePanel = null;

	public PackPanel() {
		super();
		initialize();
	}

	private void initialize() {
		jLabel1 = new JLabel();
		jLabel1.setBounds(new java.awt.Rectangle(20, 50, 81, 21));
		jLabel1.setText("Description:");
		jLabel = new JLabel();
		jLabel.setBounds(new java.awt.Rectangle(20, 20, 81, 21));
		jLabel.setText("Type:");
		this.setLayout(null);
		this.setSize(new java.awt.Dimension(404, 310));
		this.add(jLabel, null);
		this.add(jLabel1, null);
		this.add(getTxtType(), null);
		this.add(getTxtDescription(), null);
		this.add(getAramaSonuc(), null);
		this.add(getDugmePanel(), null);
		updateRecords();
	}

	private void updateRecords() {
		// Veritabanindan kayit cekip tabloya yukleyelim
		setSecili(new Pack());
		aramaSonuc.listeGuncelle(PackOperation.getInstance().ara(new Pack()));
	}

	private JTextField getTxtType() {
		if (txtType == null) {
			txtType = new JTextField();
			txtType.setBounds(new java.awt.Rectangle(100, 20, 291, 21));
		}
		return txtType;
	}

	private JTextField getTxtDescription() {
		if (txtDescription == null) {
			txtDescription = new JTextField();
			txtDescription.setBounds(new java.awt.Rectangle(100, 50, 291, 21));
		}
		return txtDescription;
	}

	private AramaSonuc getAramaSonuc() {
		if (aramaSonuc == null) {
			aramaSonuc = GUIHelper.getInstance().createAramaSonuc(
					(new Pack()).getAliasMap(),
					new java.awt.Rectangle(20, 80, 371, 191), this);
		}
		return aramaSonuc;
	}

	public void setSecili(Object secili) {
		Pack pack = (Pack) secili;
		id = pack.getPackId();
		txtType.setText(pack.getType());
		txtDescription.setText(pack.getDescription());
		this.updateUI();
	}

	public void save() {
		Pack pack = new Pack();
		pack.setType(txtType.getText());
		pack.setDescription(txtDescription.getText());
		id = PackOperation.getInstance().create(pack);
		updateRecords();
	}

	public void delete() {
		if (id != null) {
			Pack pack = new Pack();
			pack.setPackId(id);
			PackOperation.getInstance().delete(pack);
			updateRecords();
		}
	}

	public void update() {
		Pack pack = new Pack();
		pack.setType(txtType.getText());
		pack.setDescription(txtDescription.getText());
		pack.setPackId(id);
		PackOperation.getInstance().update(pack);
		updateRecords();
	}

	private DugmePanel getDugmePanel() {
		if (dugmePanel == null) {
			dugmePanel = new DugmePanel();
			dugmePanel.setBounds(new java.awt.Rectangle(50, 270, 314, 31));
			dugmePanel.setDugmeListener(this);
		}
		return dugmePanel;
	}

} // @jve:decl-index=0:visual-constraint="10,10"
