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

import java.awt.Rectangle;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.text.JTextComponent;

import net.kodveus.gui.araclar.AliasMap;
import net.kodveus.gui.araclar.AramaSonuc;
import net.kodveus.gui.araclar.AramaSonucInterface;

public class GUIHelper {

	private static GUIHelper instance;

	public final static String RESIM_KONUM = "net/kodveus/kumanifest/images/";

	private GUIHelper() {

	}

	public static GUIHelper getInstance() {
		if (instance == null) {
			instance = new GUIHelper();
		}
		return instance;
	}

	public ImageIcon createImageIcon(String imageName) {
		try {
			URL image = ClassLoader.getSystemClassLoader().getResource(
					RESIM_KONUM + imageName);
			return new ImageIcon(image);
		} catch (Exception e) {
			LogHelper.getInstance().exception(e);
			return null;
		}
	}

	public JScrollPane addScrollToTextArea(JTextComponent textArea) {
		JScrollPane scroll = new JScrollPane(textArea);
		scroll.setBounds(textArea.getBounds());
		return scroll;
	}

	public JDialog showPanel(String title, JPanel panel) {
		return showPanel(title, panel, panel.getWidth() + 20,
				panel.getHeight() + 30);
	}

	public JDialog showPanel(String title, JPanel panel, int width, int height) {
		JDialog dialog = new JDialog();
		dialog.setTitle(title);
		dialog.setContentPane(panel);
		dialog.setSize(width, height);
		dialog.setVisible(true);
		return dialog;
	}

	public JLabel createTextLabel(String label, java.awt.Rectangle bounds) {
		JLabel jlabel = new JLabel(label);
		jlabel.setBounds(bounds);
		return jlabel;
	}

	public AramaSonuc createAramaSonuc(AliasMap _aliasMap, Rectangle _bounds,
			AramaSonucInterface _interface) {
		AramaSonuc aramaSonuc = new AramaSonuc(_aliasMap, false, true);
		if (_bounds != null)
			aramaSonuc.setBounds(_bounds);
		if (_interface != null)
			aramaSonuc.setArayuz(_interface);
		return aramaSonuc;
	}
}
