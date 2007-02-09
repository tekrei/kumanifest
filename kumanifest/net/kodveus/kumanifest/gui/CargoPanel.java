package net.kodveus.kumanifest.gui;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import net.kodveus.gui.jcombobox.JSteppedComboBox;
import net.kodveus.kumanifest.jdo.Cargo;
import net.kodveus.kumanifest.jdo.Commodity;
import net.kodveus.kumanifest.jdo.Pack;
import net.kodveus.kumanifest.operation.CargoOperation;
import net.kodveus.kumanifest.operation.CommodityOperation;
import net.kodveus.kumanifest.operation.PackOperation;

public class CargoPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	private JLabel jLabel = null;

	private JLabel jLabel1 = null;

	private JLabel jLabel2 = null;

	private JTextField txtCargoType = null;

	private JSteppedComboBox cmbCommodity = null;

	private JTextField txtImco = null;

	private Long containerId;

	private Long cargoId;

	private JLabel jLabel11 = null;

	private JLabel jLabel111 = null;

	private JLabel jLabel112 = null;

	private JButton btnOk = null;

	private JTextField txtUnno = null;

	private JSteppedComboBox cmbPack = null;

	private JTextField txtNetWeight = null;

	private ContainerPanel containerPanel;

	public CargoPanel(ContainerPanel _containerPanel, Long _containerId,
			Long _cargoId) {
		super();
		containerId = _containerId;
		cargoId = _cargoId;
		containerPanel = _containerPanel;
		loadToPanel((Cargo) CargoOperation.getInstance().get(cargoId));
		initialize();
	}

	public CargoPanel(ContainerPanel _containerPanel, Long _containerId) {
		super();
		containerId = _containerId;
		cargoId = null;
		containerPanel = _containerPanel;
		initialize();
	}

	private void initialize() {
		jLabel112 = new JLabel();
		jLabel112.setBounds(new Rectangle(10, 160, 81, 21));
		jLabel112.setText("Net Weight:");
		jLabel111 = new JLabel();
		jLabel111.setBounds(new Rectangle(10, 130, 81, 21));
		jLabel111.setText("Pack:");
		jLabel11 = new JLabel();
		jLabel11.setBounds(new Rectangle(10, 100, 81, 21));
		jLabel11.setText("Unno:");
		jLabel = new JLabel();
		jLabel.setBounds(new Rectangle(10, 10, 81, 21));
		jLabel.setText("Cargo Type:");
		jLabel2 = new JLabel();
		jLabel2.setBounds(new Rectangle(10, 40, 81, 21));
		jLabel2.setText("Commodity:");
		jLabel1 = new JLabel();
		jLabel1.setBounds(new Rectangle(10, 70, 81, 21));
		jLabel1.setText("Imco:");
		this.setLayout(null);
		this.setSize(new Dimension(400, 245));
		this.setName("txtUnno");
		this.add(jLabel, null);
		this.add(jLabel1, null);
		this.add(jLabel2, null);
		this.add(getTxtCargoType(), null);
		this.add(getCmbCommodity(), null);
		this.add(getTxtImco(), null);
		this.add(jLabel11, null);
		this.add(jLabel111, null);
		this.add(jLabel112, null);
		this.add(getBtnOk(), null);
		this.add(getTxtUnno(), null);
		this.add(getCmbPack(), null);
		this.add(getTxtNetWeight(), null);
	}

	private JTextField getTxtCargoType() {
		if (txtCargoType == null) {
			txtCargoType = new JTextField();
			txtCargoType.setBounds(new Rectangle(100, 10, 291, 21));
		}
		return txtCargoType;
	}

	private JSteppedComboBox getCmbCommodity() {
		if (cmbCommodity == null) {
			cmbCommodity = new JSteppedComboBox(CommodityOperation
					.getInstance().ara(new Commodity()).toArray());
			cmbCommodity.setBounds(new Rectangle(100, 40, 291, 21));
		}
		return cmbCommodity;
	}

	private JTextField getTxtImco() {
		if (txtImco == null) {
			txtImco = new JTextField();
			txtImco.setBounds(new Rectangle(100, 70, 291, 21));
		}
		return txtImco;
	}

	private Cargo generateRecordFromGUI() {
		Cargo cargo = new Cargo();
		cargo.setCargoId(cargoId);
		cargo.setCargoType(txtCargoType.getText());
		cargo.setCommodity((Commodity) cmbCommodity.getSelectedItem());
		cargo.setContainerId(containerId);
		cargo.setImco(txtImco.getText());
		cargo.setNetWeight(Double.parseDouble(txtNetWeight.getText()));
		cargo.setPack((Pack) cmbPack.getSelectedItem());
		cargo.setUnno(txtUnno.getText());
		return cargo;
	}

	private void loadToPanel(Cargo cargo) {
		txtCargoType.setText(cargo.getCargoType());
		cmbCommodity.setSelectedItem(cargo.getCommodity());
		txtImco.setText(cargo.getImco());
		txtNetWeight.setText(Double.toString(cargo.getNetWeight()));
		cmbPack.setSelectedItem(cargo.getPack());
		txtUnno.setText(cargo.getUnno());
	}

	private JButton getBtnOk() {
		if (btnOk == null) {
			btnOk = new JButton();
			btnOk.setBounds(new Rectangle(90, 220, 241, 21));
			btnOk.setText("OK");
			btnOk.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Cargo cargo = generateRecordFromGUI();
					if (cargo.getCargoId() == null) {
						CargoOperation.getInstance().create(cargo);
					} else {
						CargoOperation.getInstance().update(cargo);
					}
					containerPanel.loadCargos();
				}
			});
		}
		return btnOk;
	}

	private JTextField getTxtUnno() {
		if (txtUnno == null) {
			txtUnno = new JTextField();
			txtUnno.setBounds(new Rectangle(100, 100, 291, 21));
		}
		return txtUnno;
	}

	private JSteppedComboBox getCmbPack() {
		if (cmbPack == null) {
			cmbPack = new JSteppedComboBox(PackOperation.getInstance().ara(
					new Pack()).toArray());
			cmbPack.setBounds(new Rectangle(100, 130, 291, 21));
		}
		return cmbPack;
	}

	private JTextField getTxtNetWeight() {
		if (txtNetWeight == null) {
			txtNetWeight = new JTextField();
			txtNetWeight.setBounds(new Rectangle(100, 160, 291, 21));
		}
		return txtNetWeight;
	}
} // @jve:decl-index=0:visual-constraint="-17,-3"
