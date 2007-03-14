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
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Enumeration;
import java.util.Properties;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JEditorPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;

import net.kodveus.kumanifest.utility.BareBonesBrowserLaunch;
import net.kodveus.kumanifest.utility.GUIHelper;
import net.kodveus.kumanifest.utility.LogHelper;

public class AboutPanel extends JDialog implements HyperlinkListener {

	private static final long serialVersionUID = 1L;

	private JTabbedPane tabPane = null;

	private JEditorPane txtAbout = null;

	private JEditorPane txtSetup = null;

	private JButton btnOk = null;

	public AboutPanel() {
		super();
		initialize();
	}

	private void initialize() {
		this.setLayout(new BorderLayout());
		this.setSize(new Dimension(800, 500));
		this.add(getTabPane(), BorderLayout.CENTER);
		this.add(getBtnOk(), BorderLayout.SOUTH);
		this.setVisible(true);
	}

	private Component getTabPane() {
		if (tabPane == null) {
			tabPane = new JTabbedPane();
			tabPane.addTab("About", getPanel(getAbout()));
			tabPane.addTab("Setup Information", getPanel(getSetup()));
		}
		return tabPane;
	}

	private JPanel getPanel(JEditorPane pane) {
		JPanel panel = new JPanel(new BorderLayout());
		panel.add(GUIHelper.getInstance().addScrollToTextArea(pane),
				BorderLayout.CENTER);
		return panel;
	}

	private JEditorPane getAbout() {
		if (txtAbout == null) {
			txtAbout = new JEditorPane();
			txtAbout.setContentType("text/html");
			txtAbout.setText(ABOUT);
			txtAbout.setOpaque(false);
			txtAbout.setBounds(new Rectangle(10, 10, 371, 281));
			txtAbout.setEditable(false);
			txtAbout.setBackground(new java.awt.Color(204, 204, 204));
			txtAbout.setBorder(javax.swing.BorderFactory.createEtchedBorder());
			txtAbout.addHyperlinkListener(this);
		}
		return txtAbout;
	}

	private JEditorPane getSetup() {
		if (txtSetup == null) {
			txtSetup = new JEditorPane();
			txtSetup.setContentType("text/html");
			Properties system_props = System.getProperties();
			Enumeration<Object> system_props_enum = system_props.keys();
			String sysProps = "";
			while (system_props_enum.hasMoreElements()) {

				String key = (String) system_props_enum.nextElement();
				if (key.trim().length() == 0)
					continue;
				sysProps += "<b>" + key + "</b>:"
						+ system_props.getProperty(key, "") + "<br>";
			}
			txtSetup.setText("<html>" + sysProps + "</html>");
			txtSetup.setOpaque(false);
			txtSetup.setBounds(new Rectangle(10, 10, 371, 281));
			txtSetup.setEditable(false);
			txtSetup.setBackground(new java.awt.Color(204, 204, 204));
			txtSetup.setBorder(javax.swing.BorderFactory.createEtchedBorder());
		}
		return txtSetup;
	}

	private JButton getBtnOk() {
		if (btnOk == null) {
			btnOk = new JButton();
			btnOk.setBounds(new Rectangle(90, 300, 201, 21));
			btnOk.setText("OK");
			btnOk.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					dispose();
				}
			});
		}
		return btnOk;
	}

	public static void main(String[] args) {
		new AboutPanel();
	}

	private final static String ABOUT = "<HTML><body><font face=\"SansSerif\" size=\"3\">"
			+ "<center><b> Kumanifest (Gemi taşımacılığı yükleme, boşaltma, manifesto takip programı) </b>"
			+ "&nbsp;&nbsp;&#169; 2006 by <a href=http://www.kodveus.com>Kod ve Us</a></center><br>"
			+ "<br>Credits:<A HREF=\"mailto:tekrei@gmail.com\">Tahir Emre KALAYCI</A> "
			+ "<A HREF=\"mailto:mehmet.kis@gmail.com\">Mehmet KIŞ</A><br></center>"
			+ "Reports: <a href=\"http://www.jasperforge.org\">JasperReports and IReport designer</a><br>"
			+ "Database: <a href=\"http://www.mysql.com\">MySQL</a><br>"
			+ "Installer: <a href=\"http://www.izforge.com/izpack/\">IzPack</a><br>"
			+ "UI: <a href=\"http://personales.ya.com/nimrod/index-en.html\">Nimrod</a><br>"
			+ "</font><br>"
			+ "(Please check legal folder for license files)"
			+ "<pre>This program is free software; you can redistribute it and/or modify\n"
			+ "it under the terms of the GNU General Public License as published by\n"
			+ "the Free Software Foundation; either version 2 of the License, or\n"
			+ "(at your option) any later version.\n\n"
			+ "This program is distributed in the hope that it will be useful,\n"
			+ "but WITHOUT ANY WARRANTY; without even the implied warranty of\n"
			+ "MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the\n"
			+ "GNU General Public License for more details.\n\n"
			+ "You should have received a copy of the GNU General Public License along\n"
			+ "with this program; if not, write to the Free Software Foundation, Inc.,\n"
			+ "51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA.\n"
			+ "</pre></body></html>";

	public void hyperlinkUpdate(HyperlinkEvent event) {
		if (event.getEventType() == HyperlinkEvent.EventType.ACTIVATED) {
			// Baglantiyi default tarayicida acalim
			LogHelper.getInstance().bilgi(event.getURL() + " acilacak!");
			BareBonesBrowserLaunch.openURL(event.getURL().toString());
		}
	}
} // @jve:decl-index=0:visual-constraint="10,10"
