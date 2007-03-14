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

import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JToolBar;

import net.kodveus.kumanifest.interfaces.ToolbarInterface;

public class ToolbarHelper implements ActionListener {

	private static ToolbarHelper instance;

	private JToolBar record;

	private JToolBar travel;

	private ToolbarInterface toolbarInterface;

	private ToolbarHelper() {

	}

	public void setToolbarInterface(ToolbarInterface _interface) {
		this.toolbarInterface = _interface;
	}

	public static ToolbarHelper getInstance() {
		if (instance == null) {
			instance = new ToolbarHelper();
		}
		return instance;
	}

	public JToolBar getRecordToolbar() {
		if (record == null) {
			record = new JToolBar(JToolBar.VERTICAL);
			record.add(addToolbarButton(true, null, "add",
					"Add record to database"));
			record.add(addToolbarButton(true, null, "delete",
					"Delete record to database"));
			record.add(addToolbarButton(true, null, "update",
					"Update record to database"));
			record.add(addToolbarButton(true, null, "clear", "Clear Screen"));
		}
		return record;
	}

	public JToolBar getTravelToolbar() {
		if (travel == null) {
			travel = new JToolBar(JToolBar.VERTICAL);

			travel
					.add(addToolbarButton(true, null, "next",
							"Bring next record"));
			travel.add(addToolbarButton(true, null, "previous",
					"Bring previous record"));
			travel
					.add(addToolbarButton(true, null, "last",
							"Bring last record"));
			travel.add(addToolbarButton(true, null, "first",
					"Bring first record"));
			setBLOpen(false);
		}
		return travel;
	}

	public JButton addToolbarButton(boolean bUseImage, String sButtonText,
			String sButton, String sToolHelp) {
		JButton b;

		// Create a new button
		if (bUseImage)
			b = new JButton(GUIHelper.getInstance().createImageIcon(
					sButton + ".gif"));
		else
			b = (JButton) record.add(new JButton());

		// Add optional button text
		if (sButtonText != null)
			b.setText(sButtonText);
		else {
			// Only a graphic, so make the button smaller
			b.setMargin(new Insets(0, 0, 0, 0));
		}

		// Add optional tooltip help
		if (sToolHelp != null)
			b.setToolTipText(sToolHelp);

		// Make sure this button sends a message when the user clicks it
		b.setActionCommand(sButton);
		b.addActionListener(this);

		return b;
	}

	public void actionPerformed(ActionEvent event) {
		if (event.getActionCommand().equals("add")) {
			toolbarInterface.add();
		} else if (event.getActionCommand().equals("delete")) {
			toolbarInterface.delete();
		} else if (event.getActionCommand().equals("update")) {
			toolbarInterface.update();
		} else if (event.getActionCommand().equals("next")) {
			toolbarInterface.next();
		} else if (event.getActionCommand().equals("clear")) {
			toolbarInterface.clear();
		} else if (event.getActionCommand().equals("previous")) {
			toolbarInterface.previous();
		} else if (event.getActionCommand().equals("first")) {
			toolbarInterface.first();
		} else if (event.getActionCommand().equals("last")) {
			toolbarInterface.last();
		}
	}

	public void setBLOpen(boolean enabled){
		for(int i=0;i<travel.getComponentCount();i++){
			travel.getComponent(i).setEnabled(enabled);
		}
		//travel.setVisible(enabled);
	}
}
