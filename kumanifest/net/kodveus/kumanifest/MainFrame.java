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
package net.kodveus.kumanifest;

import java.awt.BorderLayout;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.UIManager;

import net.kodveus.kumanifest.gui.LeftPanel;
import net.kodveus.kumanifest.gui.RightPanel;
import net.kodveus.kumanifest.gui.StartPanel;
import net.kodveus.kumanifest.jdo.Vessel;
import net.kodveus.kumanifest.utility.LogHelper;
import net.kodveus.kumanifest.utility.MenuHelper;
import net.kodveus.kumanifest.utility.ToolbarHelper;

public class MainFrame extends JFrame {

	private static final long serialVersionUID = 1L;

	private JPanel jContentPane = null;

	private JSplitPane splPane = null;

	private LeftPanel pnlSol = null;

	private RightPanel pnlSag = null;

	public MainFrame(String logLevel) {
		super();
		if (logLevel.equalsIgnoreCase("debug")) {
			LogHelper.getInstance().all();
		} else {
			LogHelper.getInstance().severe();
		}
		if ((new StartPanel()).start()) {
			initialize();
		}
	}

	private void initialize() {
		this.setSize(1024, 768);
		this.setTitle("KUManifest");
		this.setIconImage(Toolkit.getDefaultToolkit().createImage(
				"net/kodveus/kumanifest/images/rudder.png"));
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e) {
				MenuHelper.getInstance().exit();
		    }
		});
		this.setContentPane(getJContentPane());
		ToolbarHelper.getInstance().getRecordToolbar().add(
				ToolbarHelper.getInstance().getTravelToolbar());
		this.getContentPane().add(
				ToolbarHelper.getInstance().getRecordToolbar(),
				BorderLayout.EAST);
		this.setJMenuBar(MenuHelper.getInstance().getJMenu());
		this.setVisible(true);
	}

	private JPanel getJContentPane() {
		if (jContentPane == null) {
			jContentPane = new JPanel();
			jContentPane.setLayout(new BorderLayout());
			jContentPane.add(getSplPane(), java.awt.BorderLayout.CENTER);
		}
		return jContentPane;
	}

	private JSplitPane getSplPane() {
		if (splPane == null) {
			splPane = new JSplitPane();
			splPane.setDividerLocation(200);
			splPane.setDividerSize(1);
			pnlSol = new LeftPanel(this);
			pnlSag = new RightPanel(this);
			splPane.setLeftComponent(pnlSol);
			splPane.setRightComponent(pnlSag);
		}
		return splPane;
	}

	public static void main(String[] args) throws Exception {
		setUIType();
		// Log seviyesi kontrolu
		String level = "";
		if (args.length > 0) {
			level = args[0];
		}
		new MainFrame(level);
	}

	public static void setUIType() throws Exception {
		UIManager.setLookAndFeel(new com.nilo.plaf.nimrod.NimRODLookAndFeel());
	}

	public void loadBL(Long id) {
		pnlSag.loadBL(id);
	}
	public void loadVesselDetails(Vessel vessel) {
		pnlSol.loadVesselDetails(vessel);
	}
}
