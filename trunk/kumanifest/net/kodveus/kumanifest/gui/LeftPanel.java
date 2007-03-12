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

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import net.kodveus.kumanifest.MainFrame;
import net.kodveus.kumanifest.utility.TreeHelper;

public class LeftPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	private JButton btnEditVessel = null;

	private VesselDetailPanel vesselDetailPanel = null;

	private MainFrame _anaPencere;

	public LeftPanel(MainFrame anaPencere) {
		super();
		this._anaPencere = anaPencere;
		initialize();
	}

	private void initialize() {
		this.setLayout(null);
		this.setSize(200, 768);
		JScrollPane treePane = new JScrollPane(TreeHelper.getInstance()
				.getTree(_anaPencere));
		treePane.setBounds(new java.awt.Rectangle(10, 10, 181, 561));
		this.add(treePane, null);
		this.add(getBtnEditVessel(), null);
		this.add(getVesselDetailPanel(), null);
	}

	private JButton getBtnEditVessel() {
		if (btnEditVessel == null) {
			btnEditVessel = new JButton();
			btnEditVessel.setText("Edit Vessel");
			btnEditVessel.setLocation(new java.awt.Point(80, 690));
			btnEditVessel.setSize(new java.awt.Dimension(111, 21));
			btnEditVessel.setActionCommand("Edit Vessel");
			btnEditVessel.setMnemonic(java.awt.event.KeyEvent.VK_E);
		}
		return btnEditVessel;
	}

	private VesselDetailPanel getVesselDetailPanel() {
		if (vesselDetailPanel == null) {
			vesselDetailPanel = new VesselDetailPanel();
			vesselDetailPanel.setLocation(new java.awt.Point(0, 580));
			vesselDetailPanel.setSize(new java.awt.Dimension(200, 101));
		}
		return vesselDetailPanel;
	}
}
