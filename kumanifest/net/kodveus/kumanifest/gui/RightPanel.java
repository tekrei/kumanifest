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

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import com.mysql.jdbc.log.Log;

import net.kodveus.kumanifest.MainFrame;
import net.kodveus.kumanifest.interfaces.ToolbarInterface;
import net.kodveus.kumanifest.jdo.BL;
import net.kodveus.kumanifest.operation.BLOperation;
import net.kodveus.kumanifest.utility.LogHelper;
import net.kodveus.kumanifest.utility.ToolbarHelper;

public class RightPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	JTabbedPane tabPane = null;

	private BLPanel blPanel;

	private ContainerPanel containerPanel;

	// private AnaPencere _anaPencere;

	public RightPanel(MainFrame pencere) {
		super();
		// this._anaPencere = pencere;
		initialize();
	}

	/**
	 * This method initializes this
	 *
	 * @return void
	 */
	private void initialize() {
		this.setLayout(new BorderLayout());
		this.setSize(800, 768);
		this.add(getTabPane(), java.awt.BorderLayout.CENTER);
	}

	private JTabbedPane getTabPane() {
		if (tabPane == null) {
			tabPane = new JTabbedPane();
			blPanel = new BLPanel();
			tabPane.addTab("BL", blPanel);
			containerPanel = new ContainerPanel();
			tabPane.addTab("Container", containerPanel);
			ToolbarHelper.getInstance().setToolbarInterface(blPanel);
			tabPane.addChangeListener(new ChangeListener() {
				public void stateChanged(ChangeEvent evt) {
					ToolbarHelper.getInstance().setToolbarInterface(
							(ToolbarInterface) tabPane.getSelectedComponent());
					checkContainerTab();
				}
			});
		}
		return tabPane;
	}

	public void checkContainerTab() {
		// Eger container yukleniyorsa containerleri update edelim
		if (tabPane.getSelectedComponent() == containerPanel) {
			if (blPanel.getSelectedBlId() != null) {
				containerPanel.setBlId(blPanel.getSelectedBlId());
			} else {
				// Eger secili bir bl yoksa container paneli acilmasin
				tabPane.setSelectedComponent(blPanel);
			}
		}
	}

	public void loadBL(Long blId) {
		if (blId == null) {
			blPanel.clear();
			checkContainerTab();
			return;
		}
		//BL panele yuklenecek
		blPanel.loadToPanel((BL) BLOperation.getInstance().get(blId));
		// Container panel bosaltilmali
		checkContainerTab();
	}

}
