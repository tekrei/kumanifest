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

import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JTextField;

import net.kodveus.gui.arabirim.AramaSonucInterface;
import net.kodveus.gui.araclar.AramaSonuc;
import net.kodveus.gui.jcombobox.JSteppedComboBox;
import net.kodveus.kumanifest.interfaces.ToolbarInterface;
import net.kodveus.kumanifest.jdo.BL;
import net.kodveus.kumanifest.jdo.Cargo;
import net.kodveus.kumanifest.jdo.Container;
import net.kodveus.kumanifest.jdo.ContainerSize;
import net.kodveus.kumanifest.jdo.ContainerType;
import net.kodveus.kumanifest.operation.BLOperation;
import net.kodveus.kumanifest.operation.CargoOperation;
import net.kodveus.kumanifest.operation.ContainerOperation;
import net.kodveus.kumanifest.operation.ContainerSizeOperation;
import net.kodveus.kumanifest.operation.ContainerTypeOperation;
import net.kodveus.kumanifest.utility.GUIHelper;
import net.kodveus.kumanifest.utility.StatusHelper;

public class ContainerPanel extends JPanel implements ToolbarInterface {

	private static final long serialVersionUID = 1L;

	private JTextField txtContainerNo = null;

	private JTextField txtSealNo = null;

	private JTextField txtRelCom = null;

	private JTextField txtOtherSealNo = null;

	private JTextField txtTareWeight = null;

	private JSteppedComboBox cmbContainerType = null;

	private JSteppedComboBox cmbContainerSize = null;

	private AramaSonuc lstContainer = null;

	private AramaSonuc lstCargo = null;

	private JButton btnNewCargo = null;

	private JButton btnUpdateCargo = null;

	private JButton btnDeleteCargo = null;

	Long containerId;

	private Long blId;

	Long cargoId;

	ContainerPanel self;

	private JDialog cargoPanel;

	public ContainerPanel() {
		super();
		self = this;
		initialize();
	}

	private void initialize() {
		this.setLayout(null);
		this.setSize(800, 700);
		this.setBorder(javax.swing.BorderFactory.createTitledBorder(null,
				"Container Details",
				javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION,
				javax.swing.border.TitledBorder.DEFAULT_POSITION, null, null));
		this.add(GUIHelper.getInstance().createTextLabel("Container No:",
				new java.awt.Rectangle(10, 20, 141, 21)), null);
		this.add(GUIHelper.getInstance().createTextLabel("Seal No:",
				new java.awt.Rectangle(410, 20, 81, 21)), null);
		this.add(GUIHelper.getInstance().createTextLabel("Container Type:",
				new java.awt.Rectangle(10, 80, 141, 21)), null);
		this.add(GUIHelper.getInstance().createTextLabel("Container Size:",
				new java.awt.Rectangle(10, 110, 141, 21)), null);
		this.add(GUIHelper.getInstance().createTextLabel("O.Seal No:",
				new java.awt.Rectangle(410, 50, 81, 21)), null);
		this.add(GUIHelper.getInstance().createTextLabel("Tare Weight:",
				new java.awt.Rectangle(10, 50, 141, 21)), null);
		this.add(getTxtContainerNo(), null);
		this.add(getTxtSealNo(), null);
		this.add(GUIHelper.getInstance().createTextLabel("RelCom:",
				new java.awt.Rectangle(260, 110, 61, 21)), null);
		this.add(getTxtRelCom(), null);
		this.add(getTxtOtherSealNo(), null);
		this.add(getTxtTareWeight(), null);
		this.add(getCmbContainerType(), null);
		this.add(getLstContainer(), null);
		this.add(getLstCargo(), null);
		this.add(getBtnAddCargo(), null);
		this.add(getBtnUpdateCargo(), null);
		this.add(getBtnDeleteCargo(), null);
		this.add(getCmbContainerSize(), null);
	}

	private JTextField getTxtContainerNo() {
		if (txtContainerNo == null) {
			txtContainerNo = new JTextField();
			txtContainerNo.setBounds(new java.awt.Rectangle(160, 20, 241, 21));
		}
		return txtContainerNo;
	}

	private JTextField getTxtSealNo() {
		if (txtSealNo == null) {
			txtSealNo = new JTextField();
			txtSealNo.setBounds(new java.awt.Rectangle(500, 20, 231, 21));
		}
		return txtSealNo;
	}

	private JTextField getTxtRelCom() {
		if (txtRelCom == null) {
			txtRelCom = new JTextField();
			txtRelCom.setBounds(new java.awt.Rectangle(330, 110, 71, 21));
		}
		return txtRelCom;
	}

	private JTextField getTxtTareWeight() {
		if (txtTareWeight == null) {
			txtTareWeight = new JTextField();
			txtTareWeight.setBounds(new java.awt.Rectangle(160, 50, 241, 21));
		}
		return txtTareWeight;
	}

	private JTextField getTxtOtherSealNo() {
		if (txtOtherSealNo == null) {
			txtOtherSealNo = new JTextField();
			txtOtherSealNo.setBounds(new java.awt.Rectangle(500, 50, 231, 21));
		}
		return txtOtherSealNo;
	}

	private JSteppedComboBox getCmbContainerType() {
		if (cmbContainerType == null) {
			cmbContainerType = new JSteppedComboBox(ContainerTypeOperation
					.getInstance().findAll().toArray());
			cmbContainerType
					.setBounds(new java.awt.Rectangle(160, 80, 241, 21));
		}
		return cmbContainerType;
	}

	private AramaSonuc getLstContainer() {
		if (lstContainer == null) {
			lstContainer = GUIHelper.getInstance().createAramaSonuc(
					(new Container()).getAliasMap(), null,
					new AramaSonucInterface() {
						public void setSecili(Object obj) {
							Container container = (Container) obj;
							containerId = container.getContainerId();
							loadToPanel(container);
							loadCargos(containerId);
						}
					});
			lstContainer.setBounds(new Rectangle(10, 140, 720, 140));
		}
		return lstContainer;
	}

	private AramaSonuc getLstCargo() {
		if (lstCargo == null) {
			lstCargo = GUIHelper.getInstance().createAramaSonuc(
					(new Cargo()).getAliasMap(), null,
					new AramaSonucInterface() {
						public void setSecili(Object obj) {
							Cargo cargo = (Cargo) obj;
							cargoId = cargo.getCargoId();
						}
					});
			lstCargo.setBorder(BorderFactory.createTitledBorder("Cargo"));
			lstCargo.setBounds(new Rectangle(10, 280, 720, 140));
		}
		return lstCargo;
	}

	private Container generateRecordFromGUI() {
		Container container = new Container();
		container.setContainerNo(txtContainerNo.getText());
		container.setContainerSize((ContainerSize) cmbContainerSize
				.getSelectedItem());
		container.setContainerType((ContainerType) cmbContainerType
				.getSelectedItem());
		container.setSealNo(txtSealNo.getText());
		container.setOtherSealNo(txtOtherSealNo.getText());
		container.setRelCom(Long.parseLong(txtRelCom.getText()));
		container.setTaraWeight(Double.parseDouble(txtTareWeight.getText()));
		container.setBl((BL)BLOperation.getInstance().get(blId));
		container.setContainerId(containerId);
		return container;
	}

	void loadToPanel(Container container) {
		if(container.getContainerNo()!=null)
		{
			txtContainerNo.setText(container.getContainerNo());
			cmbContainerSize.setSelectedItem(container.getContainerSize());
			cmbContainerType.setSelectedItem(container.getContainerType());
			txtOtherSealNo.setText(container.getOtherSealNo());
			txtSealNo.setText(container.getSealNo());
			txtRelCom.setText("");
			if (container.getRelCom() != null) {
				txtRelCom.setText(Long.toString(container.getRelCom()));
			}
			txtTareWeight.setText("");
			if (container.getTaraWeight() != null) {
				txtTareWeight.setText(Double.toString(container.getTaraWeight()));
			}
			blId = container.getBl().getBlId();
			containerId = container.getContainerId();
		}
	}

	public void clear() {
		loadToPanel(new Container());
	}

	public void add() {
		Container container = generateRecordFromGUI();
		long tempId = ContainerOperation.getInstance().create(container);
		if (tempId > 0) {
			containerId = tempId;
			StatusHelper.getInstance().kayitEklendi();
			updateContainers();
		} else {
			StatusHelper.getInstance().hataOlustu();
		}
	}

	public void delete() {
		if (ContainerOperation.getInstance().delete(generateRecordFromGUI())) {
			StatusHelper.getInstance().kayitSilindi();
			updateContainers();
		} else {
			StatusHelper.getInstance().hataOlustu();
		}
	}

	public void first() {
		// Ilk container'i getirmemiz gerekiyor
		Container container = (Container) ContainerOperation.getInstance()
				.next(new Long(0));
		if (container != null) {
			loadToPanel(container);
		}
	}

	public void last() {
		// Son container'i getirmemiz gerekiyor
		Container container = (Container) ContainerOperation.getInstance()
				.previous(Long.MAX_VALUE);
		if (container != null) {
			loadToPanel(container);
		}
	}

	public void next() {
		// Sonraki Container
		Container container = (Container) ContainerOperation.getInstance()
				.next(containerId);
		if (container != null) {
			loadToPanel(container);
		}
	}

	public void previous() {
		// Sonraki Container
		Container container = (Container) ContainerOperation.getInstance()
				.previous(containerId);
		if (container != null) {
			loadToPanel(container);
		}
	}

	public void update() {
		if (ContainerOperation.getInstance().update(generateRecordFromGUI())) {
			StatusHelper.getInstance().kayitGuncellendi();
			updateContainers();
		} else {
			StatusHelper.getInstance().hataOlustu();
		}
	}

	private JSteppedComboBox getCmbContainerSize() {
		if (cmbContainerSize == null) {
			cmbContainerSize = new JSteppedComboBox(ContainerSizeOperation
					.getInstance().findAll().toArray());
			cmbContainerSize
					.setBounds(new java.awt.Rectangle(161, 110, 93, 21));
		}
		return cmbContainerSize;
	}

	private JButton getBtnAddCargo() {
		if (btnNewCargo == null) {
			btnNewCargo = new JButton("New Cargo");
			btnNewCargo.setBounds(new Rectangle(135, 420, 120, 30));
			btnNewCargo.setMnemonic('n');
			btnNewCargo.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if (containerId != null) {
						cargoPanel = GUIHelper.getInstance().showPanel(
								"New Cargo", new CargoPanel(self, containerId));
					}
				}
			});
		}
		return btnNewCargo;
	}

	private JButton getBtnDeleteCargo() {
		if (btnDeleteCargo == null) {
			btnDeleteCargo = new JButton("Delete Cargo");
			btnDeleteCargo.setBounds(new Rectangle(295, 420, 120, 30));
			btnDeleteCargo.setMnemonic('d');
			btnDeleteCargo.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if (cargoId != null) {
						CargoOperation.getInstance().delete(cargoId);
						loadCargos();
					}
				}
			});
		}
		return btnDeleteCargo;
	}

	private JButton getBtnUpdateCargo() {
		if (btnUpdateCargo == null) {
			btnUpdateCargo = new JButton("Update Cargo");
			btnUpdateCargo.setBounds(new Rectangle(455, 420, 120, 30));
			btnUpdateCargo.setMnemonic('u');
			btnUpdateCargo.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if (containerId != null && cargoId != null) {
						cargoPanel = GUIHelper.getInstance().showPanel(
								"Update Cargo",
								new CargoPanel(self, containerId, cargoId));
					}
				}
			});
		}
		return btnUpdateCargo;
	}

	public void setBlId(Long selectedBlId) {
		// Detay ekrani temizlensin
		clear();
		blId = selectedBlId;
		updateContainers();
	}

	private void updateContainers() {		
			lstContainer.listeGuncelle(ContainerOperation.getInstance().containerOfBl(blId));
			// Container'in kargolari yuklensin
			loadCargos();
			this.updateUI();		
	}

	void loadCargos(Long _containerId) {
		// Container secilmemisse herhangi bir cargo gostermemeli
		if (_containerId == null) {
			lstCargo.listeGuncelle(null);
		} else {
			lstCargo.listeGuncelle(CargoOperation.getInstance().cargoOfContainer(_containerId));
		}
	}

	protected void loadCargos() {
		loadCargos(containerId);
	}

	public void closeCargoDialog() {
		cargoPanel.dispose();
		cargoPanel = null;
	}
}
