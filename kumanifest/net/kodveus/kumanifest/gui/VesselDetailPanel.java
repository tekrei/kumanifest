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

public class VesselDetailPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JLabel jLabel = null;

	private JLabel jLabel1 = null;

	private JLabel jLabel2 = null;

	private JLabel jLabel3 = null;

	private JLabel lblVessel = null;

	private JLabel lblVoyage = null;

	private JLabel lblFlag = null;

	private JLabel lblPort = null;

	/**
	 * This is the default constructor
	 */
	public VesselDetailPanel() {
		super();
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		lblPort = new JLabel();
		lblPort.setBounds(new java.awt.Rectangle(60, 80, 131, 21));
		lblPort.setText("");
		lblFlag = new JLabel();
		lblFlag.setBounds(new java.awt.Rectangle(60, 60, 131, 21));
		lblFlag.setText("");
		lblVoyage = new JLabel();
		lblVoyage.setBounds(new java.awt.Rectangle(60, 40, 131, 21));
		lblVoyage.setText("");
		lblVessel = new JLabel();
		lblVessel.setBounds(new java.awt.Rectangle(60, 20, 131, 21));
		lblVessel.setText("");
		jLabel3 = new JLabel();
		jLabel3.setBounds(new java.awt.Rectangle(10, 80, 51, 21));
		jLabel3.setText("Port     :");
		jLabel2 = new JLabel();
		jLabel2.setBounds(new java.awt.Rectangle(10, 60, 51, 21));
		jLabel2.setText("Flag     :");
		jLabel1 = new JLabel();
		jLabel1.setBounds(new java.awt.Rectangle(10, 40, 51, 21));
		jLabel1.setText("Voyage:");
		jLabel = new JLabel();
		jLabel.setBounds(new java.awt.Rectangle(10, 20, 51, 21));
		jLabel.setText("Vessel :");
		this.setLayout(null);
		this.setSize(200, 110);
		this.setBorder(javax.swing.BorderFactory.createTitledBorder(null,
				"Vessel Details",
				javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION,
				javax.swing.border.TitledBorder.DEFAULT_POSITION, null, null));
		this.add(jLabel, null);
		this.add(jLabel1, null);
		this.add(jLabel2, null);
		this.add(jLabel3, null);
		this.add(lblVessel, null);
		this.add(lblVoyage, null);
		this.add(lblFlag, null);
		this.add(lblPort, null);
	}

} // @jve:decl-index=0:visual-constraint="10,10"
