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
import net.kodveus.kumanifest.jdo.Vessel;
import net.kodveus.kumanifest.utility.TreeHelper;
import javax.swing.BorderFactory;
import javax.swing.border.TitledBorder;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JTextField;
import java.awt.Rectangle;
import javax.swing.JLabel;

public class LeftPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	private VesselDetailPanel vesselDetailPanel = null;

	private MainFrame _anaPencere;

	private JLabel lblVesselCode = null;

	private JLabel lblVesselName = null;

	private JLabel lblFlag = null;

	private JLabel lblPort = null;

	public LeftPanel(MainFrame anaPencere) {
		super();
		this._anaPencere = anaPencere;
		initialize();
	}

	private void initialize() {
		this.setLayout(null);
		this.setSize(200, 700);
		JScrollPane treePane = new JScrollPane(TreeHelper.getInstance()
				.getTree(_anaPencere));
		treePane.setBounds(new java.awt.Rectangle(10, 10, 181, 500));
		this.add(treePane, null);
		this.add(getVesselDetailPanel(), null);
	}

	private VesselDetailPanel getVesselDetailPanel() {
		if (vesselDetailPanel == null) {
			lblPort = new JLabel();
			lblPort.setBounds(new Rectangle(68, 80, 108, 18));
			lblPort.setText("");
			lblFlag = new JLabel();
			lblFlag.setBounds(new Rectangle(68, 61, 108, 18));
			lblFlag.setText("");
			lblVesselName = new JLabel();
			lblVesselName.setBounds(new Rectangle(68, 42, 108, 18));
			lblVesselName.setText("");
			lblVesselCode = new JLabel();
			lblVesselCode.setBounds(new Rectangle(68, 23, 108, 18));
			lblVesselCode.setText("");
			
			vesselDetailPanel = new VesselDetailPanel();
			vesselDetailPanel.setLocation(new java.awt.Point(0, 520));
			vesselDetailPanel.setBorder(BorderFactory.createTitledBorder(null, "Vessel Details", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new Font("Dialog", Font.BOLD, 12), new Color(51, 51, 51)));
			vesselDetailPanel.setSize(new java.awt.Dimension(200, 101));
			vesselDetailPanel.add(lblVesselCode, null);
			vesselDetailPanel.add(lblVesselName, null);
			vesselDetailPanel.add(lblFlag, null);
			vesselDetailPanel.add(lblPort, null);
		}
		return vesselDetailPanel;
	}
	public void loadVesselDetails(Vessel vessel) {
		lblVesselCode.setText(vessel.getVesselCode());
		lblVesselName.setText(vessel.getVesselName());
		lblFlag.setText(vessel.getFlag().getName());
		lblPort.setText(vessel.getPort().getLocation());
	}
}
