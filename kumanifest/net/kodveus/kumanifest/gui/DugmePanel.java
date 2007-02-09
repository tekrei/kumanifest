package net.kodveus.kumanifest.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import net.kodveus.kumanifest.interfaces.DugmeInterface;

public class DugmePanel extends JPanel implements ActionListener {

	private static final long serialVersionUID = 1L;

	private JButton btnSave = null;

	private JButton btnUpdate = null;

	private JButton btnDelete = null;

	private DugmeInterface dugmeInterface;

	public DugmePanel() {
		super();
		initialize();
	}

	public void setDugmeListener(DugmeInterface _interface) {
		this.dugmeInterface = _interface;
	}

	private void initialize() {
		this.setLayout(null);
		this.setBounds(new java.awt.Rectangle(0, 0, 310, 33));
		this.add(getBtnSave(), null);
		this.add(getBtnUpdate(), null);
		this.add(getBtnDelete(), null);

	}

	private JButton getBtnSave() {
		if (btnSave == null) {
			btnSave = new JButton();
			btnSave.setBounds(new java.awt.Rectangle(0, 0, 91, 31));
			btnSave.setMnemonic(java.awt.event.KeyEvent.VK_S);
			btnSave.setText("Save");
			btnSave.setActionCommand("save");
			btnSave.addActionListener(this);
		}
		return btnSave;
	}

	private JButton getBtnUpdate() {
		if (btnUpdate == null) {
			btnUpdate = new JButton();
			btnUpdate.setBounds(new java.awt.Rectangle(110, 0, 91, 31));
			btnUpdate.setMnemonic(java.awt.event.KeyEvent.VK_U);
			btnUpdate.setText("Update");
			btnUpdate.setActionCommand("update");
			btnUpdate.addActionListener(this);
		}
		return btnUpdate;
	}

	private JButton getBtnDelete() {
		if (btnDelete == null) {
			btnDelete = new JButton();
			btnDelete.setBounds(new java.awt.Rectangle(220, 0, 91, 31));
			btnDelete.setMnemonic(java.awt.event.KeyEvent.VK_D);
			btnDelete.setText("Delete");
			btnDelete.setActionCommand("delete");
			btnDelete.addActionListener(this);
		}
		return btnDelete;
	}

	public void actionPerformed(ActionEvent e) {
		if (dugmeInterface != null) {
			if (e.getActionCommand().equals("save")) {
				dugmeInterface.save();
			} else if (e.getActionCommand().equals("delete")) {
				dugmeInterface.delete();
			} else if (e.getActionCommand().equals("update")) {
				dugmeInterface.update();
			}
		}
	}

} // @jve:decl-index=0:visual-constraint="10,10"
