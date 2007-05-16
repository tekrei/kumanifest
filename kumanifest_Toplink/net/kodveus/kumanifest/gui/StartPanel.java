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

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JProgressBar;

import net.kodveus.kumanifest.persistence.PersistenceManager;
import net.kodveus.kumanifest.utility.GUIHelper;

public class StartPanel extends JDialog {
	private static final long serialVersionUID = 1L;

	private javax.swing.JPanel jContentPane = null;

	private JLabel jlbProgramAdi = null;

	private JProgressBar jpbDurum = null;

	private JLabel jlbBekleme = null;

	public StartPanel() {
		super();
		initialize();
	}

	private void initialize() {
		this.setResizable(false);
		this.setTitle("Kod Ve Us");
		this.setName("splash");
		this
				.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
		this.setSize(334, 287);
		this.setLocationRelativeTo(null);
		this.setContentPane(getJContentPane());
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
	}

	private javax.swing.JPanel getJContentPane() {
		if (jContentPane == null) {
			jlbBekleme = new JLabel();
			jlbProgramAdi = new JLabel();
			jContentPane = new javax.swing.JPanel();
			jContentPane.setLayout(new java.awt.BorderLayout());
			jlbProgramAdi.setText("Manifest Program");
			jlbProgramAdi.setHorizontalAlignment(JLabel.CENTER);

			jlbBekleme.setIcon(GUIHelper.getInstance().createImageIcon(
					"kvu.gif"));
			jlbBekleme
					.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
			jContentPane.add(jlbProgramAdi, java.awt.BorderLayout.SOUTH);
			jContentPane.add(getJpbDurum(), java.awt.BorderLayout.CENTER);
			jContentPane.add(jlbBekleme, java.awt.BorderLayout.NORTH);
		}

		return jContentPane;
	}

	public boolean start() {
		try {
			this.setVisible(true);
			PersistenceManager.getInstance();
		} catch (Exception e) {
			e.printStackTrace();
			//new DatabaseConfiguration();
		}
		this.dispose();
		return true;
	}

	private JProgressBar getJpbDurum() {
		if (jpbDurum == null) {
			jpbDurum = new JProgressBar();
		}

		jpbDurum.setIndeterminate(true);

		return jpbDurum;
	}
} // @@jve:decl-index=0:visual-constraint="10,10"
