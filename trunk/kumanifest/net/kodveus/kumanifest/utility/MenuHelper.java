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
package net.kodveus.kumanifest.utility;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.HashMap;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import net.kodveus.kumanifest.gui.AboutPanel;
import net.kodveus.kumanifest.gui.CommodityPanel;
import net.kodveus.kumanifest.gui.ContainerSizePanel;
import net.kodveus.kumanifest.gui.ContainerTypePanel;
import net.kodveus.kumanifest.gui.CountryPanel;
import net.kodveus.kumanifest.gui.LocationPanel;
import net.kodveus.kumanifest.gui.OfficePanel;
import net.kodveus.kumanifest.gui.PackPanel;
import net.kodveus.kumanifest.gui.VesselPanel;
import net.kodveus.kumanifest.gui.VoyagePanel;
import net.kodveus.kumanifest.report.ReportGenerator;

public class MenuHelper implements ActionListener {

	private static MenuHelper instance;

	private JMenuBar menuBar = null;

	private JMenu mnReports;

	private MenuHelper() {
	}

	public static MenuHelper getInstance() {
		if (instance == null) {
			instance = new MenuHelper();
		}
		return instance;
	}

	public JMenuBar getJMenu() {
		if (menuBar == null) {
			menuBar = new JMenuBar();
			menuBar.add(getFileMenu());
			menuBar.add(getReportsMenu());
			menuBar.add(getAdditionalMenu());
			menuBar.add(getToolsMenu());
			menuBar.add(getHelpMenu());
			menuBar.add(getManagerialMenu());
		}
		return menuBar;
	}

	private JMenu getManagerialMenu() {
		JMenu menu = new JMenu("Managerial");
		return menu;
	}

	private JMenu getHelpMenu() {
		JMenu menu = new JMenu("Help");
		JMenuItem menuItem = new JMenuItem("About", KeyEvent.VK_A);
		menuItem.setActionCommand("about");
		menuItem.addActionListener(this);
		menu.add(menuItem);
		return menu;
	}

	private JMenu getToolsMenu() {
		JMenu menu = new JMenu("Tools");

		JMenuItem menuItem = new JMenuItem("Search", KeyEvent.VK_S);
		menuItem.addActionListener(this);
		menu.add(menuItem);

		/*
		 * menuItem = new JMenuItem("Send Mail", KeyEvent.VK_M);
		 * menuItem.addActionListener(this); menu.add(menuItem);
		 */

		/*
		 * menuItem = new JMenuItem("Merge Voyages", KeyEvent.VK_E);
		 * menuItem.addActionListener(this); menu.add(menuItem);
		 */

		/*
		 * menuItem = new JMenuItem("Calculator", KeyEvent.VK_A);
		 * menuItem.addActionListener(this); menu.add(menuItem);
		 */

		menu.add(getParametersMenu());

		menu.addSeparator();

		menuItem = new JMenuItem("Settings", KeyEvent.VK_G);
		menuItem.addActionListener(this);
		menu.add(menuItem);

		return menu;
	}

	private JMenu getAdditionalMenu() {
		JMenu menu = new JMenu("Additional");

		JMenuItem menuItem;
		/*
		 * menuItem = new JMenuItem("ConCompare", KeyEvent.VK_C);
		 * menuItem.addActionListener(this); menu.add(menuItem);
		 */

		/*
		 * menuItem = new JMenuItem("TRM to SPS", KeyEvent.VK_T);
		 * menuItem.addActionListener(this); menu.add(menuItem);
		 */

		/*
		 * menuItem = new JMenuItem("3rdParty Reports", KeyEvent.VK_3);
		 * menuItem.addActionListener(this); menu.add(menuItem);
		 */

		menuItem = new JMenuItem("OpsReport", KeyEvent.VK_O);
		menuItem.addActionListener(this);
		menu.add(menuItem);

		/*
		 * menuItem = new JMenuItem("Partners", KeyEvent.VK_A);
		 * menuItem.addActionListener(this); menu.add(menuItem);
		 */

		/*
		 * menuItem = new JMenuItem("FRC Screen", KeyEvent.VK_F);
		 * menuItem.addActionListener(this); menu.add(menuItem);
		 */

		return menu;
	}

	private JMenu getReportsMenu() {
		mnReports = new JMenu("Reports");
		// Raporlar menusu bl'e tiklanmadan acilmiyor
		mnReports.setEnabled(false);

		JMenuItem menuItem = new JMenuItem("Manifesto", KeyEvent.VK_M);
		menuItem.setActionCommand("Manifesto");
		menuItem.addActionListener(this);
		mnReports.add(menuItem);

		menuItem = new JMenuItem("Sticker", KeyEvent.VK_S);
		menuItem.addActionListener(this);
		mnReports.add(menuItem);

		menuItem = new JMenuItem("Loading List", KeyEvent.VK_L);
		menuItem.setActionCommand("LoadingList");
		menuItem.addActionListener(this);
		mnReports.add(menuItem);

		menuItem = new JMenuItem("Discharging List", KeyEvent.VK_D);
		menuItem.addActionListener(this);
		mnReports.add(menuItem);

		/*
		 * menuItem = new JMenuItem("Summary Declaration", KeyEvent.VK_S);
		 * menuItem.addActionListener(this); menu.add(menuItem);
		 */

		menuItem = new JMenuItem("Bill of Lading", KeyEvent.VK_B);
		menuItem.setActionCommand("BillOfLading");
		menuItem.addActionListener(this);
		mnReports.add(menuItem);

		mnReports.addSeparator();

		menuItem = new JMenuItem("BL/Container List", KeyEvent.VK_C);
		menuItem.addActionListener(this);
		mnReports.add(menuItem);

		menuItem = new JMenuItem("Partial Containers", KeyEvent.VK_P);
		menuItem.addActionListener(this);
		mnReports.add(menuItem);

		menuItem = new JMenuItem("CustomerContReport", KeyEvent.VK_U);
		menuItem.addActionListener(this);
		mnReports.add(menuItem);

		return mnReports;
	}

	private JMenu getFileMenu() {
		JMenu menu = new JMenu("File");

		JMenuItem menuItem = new JMenuItem("Open", KeyEvent.VK_O);
		menuItem.addActionListener(this);
		menu.add(menuItem);

		menuItem = new JMenuItem("Import", KeyEvent.VK_I);
		menuItem.addActionListener(this);
		menu.add(menuItem);

		menuItem = new JMenuItem("Export", KeyEvent.VK_E);
		menuItem.addActionListener(this);
		menu.add(menuItem);

		menuItem = new JMenuItem("Print Setup", KeyEvent.VK_P);
		menuItem.addActionListener(this);
		menu.add(menuItem);

		menu.addSeparator();

		menuItem = new JMenuItem("Exit", KeyEvent.VK_X);
		menuItem.setActionCommand("exit");
		menuItem.addActionListener(this);
		menu.add(menuItem);

		return menu;
	}

	public JMenu getParametersMenu() {
		JMenu menu = new JMenu("Parameters");

		JMenuItem menuItem = new JMenuItem("Pack", KeyEvent.VK_P);
		menuItem.setActionCommand("pack");
		menuItem.addActionListener(this);
		menu.add(menuItem);

		menuItem = new JMenuItem("Office", KeyEvent.VK_O);
		menuItem.setActionCommand("office");
		menuItem.addActionListener(this);
		menu.add(menuItem);

		menuItem = new JMenuItem("Commodity", KeyEvent.VK_C);
		menuItem.setActionCommand("commodity");
		menuItem.addActionListener(this);
		menu.add(menuItem);

		menuItem = new JMenuItem("ContainerSize", KeyEvent.VK_S);
		menuItem.setActionCommand("containerSize");
		menuItem.addActionListener(this);
		menu.add(menuItem);

		menuItem = new JMenuItem("ContainerType", KeyEvent.VK_T);
		menuItem.setActionCommand("containerType");
		menuItem.addActionListener(this);
		menu.add(menuItem);

		menuItem = new JMenuItem("Country", KeyEvent.VK_U);
		menuItem.setActionCommand("country");
		menuItem.addActionListener(this);
		menu.add(menuItem);

		menuItem = new JMenuItem("Location", KeyEvent.VK_L);
		menuItem.setActionCommand("location");
		menuItem.addActionListener(this);
		menu.add(menuItem);

		menuItem = new JMenuItem("Vessel", KeyEvent.VK_V);
		menuItem.setActionCommand("vessel");
		menuItem.addActionListener(this);
		menu.add(menuItem);

		menuItem = new JMenuItem("Voyage", KeyEvent.VK_Y);
		menuItem.setActionCommand("voyage");
		menuItem.addActionListener(this);
		menu.add(menuItem);

		return menu;
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("exit")) {
			if (JOptionPane.showConfirmDialog(null, "Are you sure?") == JOptionPane.OK_OPTION) {
				System.exit(0);
			}
		} else if (e.getActionCommand().equals("pack")) {
			PackPanel pack = new PackPanel();
			GUIHelper.getInstance().showPanel("Pack", pack);
		} else if (e.getActionCommand().equals("office")) {
			OfficePanel office = new OfficePanel();
			GUIHelper.getInstance().showPanel("Office", office);
		} else if (e.getActionCommand().equals("commodity")) {
			CommodityPanel commodity = new CommodityPanel();
			GUIHelper.getInstance().showPanel("Commodity", commodity);
		} else if (e.getActionCommand().equals("containerSize")) {
			ContainerSizePanel containerSize = new ContainerSizePanel();
			GUIHelper.getInstance().showPanel("ContainerSize", containerSize);
		} else if (e.getActionCommand().equals("containerType")) {
			ContainerTypePanel containerType = new ContainerTypePanel();
			GUIHelper.getInstance().showPanel("ContainerType", containerType);
		} else if (e.getActionCommand().equals("country")) {
			CountryPanel country = new CountryPanel();
			GUIHelper.getInstance().showPanel("Country", country);
		} else if (e.getActionCommand().equals("location")) {
			GUIHelper.getInstance().showPanel("Location", new LocationPanel());
		} else if (e.getActionCommand().equals("vessel")) {
			GUIHelper.getInstance().showPanel("Vessel", new VesselPanel());
		} else if (e.getActionCommand().equals("voyage")) {
			GUIHelper.getInstance().showPanel("Voyage", new VoyagePanel());
		} else if (e.getActionCommand().equals("about")) {
			new AboutPanel();
		} else if (e.getActionCommand().equals("LoadingList")) {
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("prmVoyageId", TreeHelper.getInstance().getVoyageId());
			try {
				ReportGenerator.getInstance().generateLoadingList(map);
			} catch (Exception ex) {
				LogHelper.getInstance().exception(ex);
			}
		} else if (e.getActionCommand().equals("BillOfLading")) {
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("prmBLId", TreeHelper.getInstance().getBLId());
			try {
				ReportGenerator.getInstance().generateBillOfLading(map);
			} catch (Exception ex) {
				LogHelper.getInstance().exception(ex);
			}
		} else if (e.getActionCommand().equals("Manifesto")) {
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("prmVoyageId", TreeHelper.getInstance().getVoyageId());
			try {
				ReportGenerator.getInstance().generateManifest(map);
			} catch (Exception ex) {
				LogHelper.getInstance().exception(ex);
			}
		}
	}

	public void setBLOpen(boolean enabled) {
		mnReports.setEnabled(enabled);
	}
}
