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
