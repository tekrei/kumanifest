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
import net.kodveus.kumanifest.jdo.ContainerSize;
import net.kodveus.kumanifest.operation.ContainerSizeOperation;
import net.kodveus.kumanifest.utility.GUIHelper;

public class ContainerSizePanel extends JPanel implements AramaSonucInterface,
		DugmeInterface {
	private static final long serialVersionUID = 1L;

	private JLabel jLabel = null;

	private JLabel jLabel1 = null;

	private JTextField txtSize = null;

	private JTextField txtDescription = null;

	private AramaSonuc aramaSonuc = null;

	private Long id;

	private DugmePanel dugmePanel = null;

	public ContainerSizePanel() {
		super();
		initialize();
	}

	private void initialize() {
		jLabel1 = new JLabel();
		jLabel1.setBounds(new java.awt.Rectangle(20, 50, 81, 21));
		jLabel1.setText("Description:");
		jLabel = new JLabel();
		jLabel.setBounds(new java.awt.Rectangle(20, 20, 81, 21));
		jLabel.setText("Size:");
		this.setLayout(null);
		this.setSize(new java.awt.Dimension(404, 310));
		this.add(jLabel, null);
		this.add(jLabel1, null);
		this.add(getTxtSize(), null);
		this.add(getTxtDescription(), null);
		this.add(getAramaSonuc(), null);
		this.add(getDugmePanel(), null);
		updateRecords();
	}

	private void updateRecords() {
		// Veritabanindan kayit cekip tabloya yukleyelim
		setSecili(new ContainerSize());
		aramaSonuc.listeGuncelle(ContainerSizeOperation.getInstance().ara(
				new ContainerSize()));
	}

	private JTextField getTxtSize() {
		if (txtSize == null) {
			txtSize = new JTextField();
			txtSize.setBounds(new java.awt.Rectangle(100, 20, 291, 21));
		}
		return txtSize;
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
					(new ContainerSize()).getAliasMap(),
					new java.awt.Rectangle(20, 80, 371, 191), this);
		}
		return aramaSonuc;
	}

	public void setSecili(Object secili) {
		ContainerSize containerSize = (ContainerSize) secili;
		id = containerSize.getContainerSizeId();
		txtSize.setText(containerSize.getSize());
		txtDescription.setText(containerSize.getDescription());
		this.updateUI();
	}

	public void save() {
		ContainerSize containerSize = new ContainerSize();
		containerSize.setSize(txtSize.getText());
		containerSize.setDescription(txtDescription.getText());
		id = ContainerSizeOperation.getInstance().create(containerSize);
		updateRecords();
	}

	public void delete() {
		if (id != null) {
			ContainerSize containerSize = new ContainerSize();
			containerSize.setContainerSizeId(id);
			ContainerSizeOperation.getInstance().delete(containerSize);
			updateRecords();
		}
	}

	public void update() {
		ContainerSize containerSize = new ContainerSize();
		containerSize.setSize(txtSize.getText());
		containerSize.setDescription(txtDescription.getText());
		containerSize.setContainerSizeId(id);
		ContainerSizeOperation.getInstance().update(containerSize);
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

