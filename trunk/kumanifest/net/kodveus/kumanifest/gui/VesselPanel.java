package net.kodveus.kumanifest.gui;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import net.kodveus.gui.araclar.AramaSonuc;
import net.kodveus.gui.araclar.AramaSonucInterface;
import net.kodveus.gui.jcombobox.JSteppedComboBox;
import net.kodveus.kumanifest.interfaces.DugmeInterface;
import net.kodveus.kumanifest.jdo.Country;
import net.kodveus.kumanifest.jdo.Location;
import net.kodveus.kumanifest.jdo.Vessel;
import net.kodveus.kumanifest.operation.CountryOperation;
import net.kodveus.kumanifest.operation.LocationOperation;
import net.kodveus.kumanifest.operation.VesselOperation;

public class VesselPanel extends JPanel implements AramaSonucInterface,
		DugmeInterface {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JLabel jLabel = null;

	private JLabel jLabel1 = null;

	private JTextField txtVesselCode = null;

	private JTextField txtVesselName = null;

	private AramaSonuc aramaSonuc = null;

	private Long id;

	private JLabel jLabel2 = null;

	private JLabel jLabel3 = null;

	private JLabel jLabel4 = null;

	private DugmePanel dugmePanel = null;

	private JTextField txtCompany = null;

	private JSteppedComboBox cmbPort = null;

	private JSteppedComboBox cmbFlag = null;

	/**
	 * This method initializes
	 * 
	 */
	public VesselPanel() {
		super();
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 */
	private void initialize() {
		jLabel4 = new JLabel();
		jLabel4.setBounds(new java.awt.Rectangle(10, 140, 91, 21));
		jLabel4.setText("Company:");
		jLabel3 = new JLabel();
		jLabel3.setBounds(new java.awt.Rectangle(10, 110, 91, 21));
		jLabel3.setText("Flag:");
		jLabel2 = new JLabel();
		jLabel2.setBounds(new java.awt.Rectangle(10, 80, 91, 21));
		jLabel2.setText("Port:");
		jLabel1 = new JLabel();
		jLabel1.setBounds(new java.awt.Rectangle(10, 50, 91, 21));
		jLabel1.setText("Vessel Name:");
		jLabel = new JLabel();
		jLabel.setBounds(new java.awt.Rectangle(10, 20, 91, 21));
		jLabel.setText("Vessel Code:");
		this.setLayout(null);
		this.setSize(new java.awt.Dimension(404, 410));
		this.add(jLabel, null);
		this.add(jLabel1, null);
		this.add(getTxtVesselCode(), null);
		this.add(getTxtDescription(), null);
		this.add(getAramaSonuc(), null);
		this.add(jLabel2, null);
		this.add(jLabel3, null);
		this.add(jLabel4, null);
		this.add(getDugmePanel(), null);
		this.add(getTxtCompany(), null);
		this.add(getCmbPort(), null);
		this.add(getCmbFlag(), null);
		updateRecords();
	}

	private void updateRecords() {
		// Veritabanindan kayit cekip tabloya yukleyelim
		aramaSonuc.listeGuncelle(VesselOperation.getInstance()
				.ara(new Vessel()));
	}

	/**
	 * This method initializes jTextField
	 * 
	 * @return javax.swing.JTextField
	 */
	private JTextField getTxtVesselCode() {
		if (txtVesselCode == null) {
			txtVesselCode = new JTextField();
			txtVesselCode.setBounds(new java.awt.Rectangle(100, 20, 291, 21));
		}
		return txtVesselCode;
	}

	/**
	 * This method initializes jTextField
	 * 
	 * @return javax.swing.JTextField
	 */
	private JTextField getTxtDescription() {
		if (txtVesselName == null) {
			txtVesselName = new JTextField();
			txtVesselName.setBounds(new java.awt.Rectangle(100, 50, 291, 21));
		}
		return txtVesselName;
	}

	/**
	 * This method initializes aramaSonuc1
	 * 
	 * @return net.tekrei.gui.araclar.AramaSonuc
	 */
	private AramaSonuc getAramaSonuc() {
		if (aramaSonuc == null) {
			aramaSonuc = new AramaSonuc();
			aramaSonuc.setBounds(new java.awt.Rectangle(10, 170, 381, 191));
			aramaSonuc.setAliasMap((new Vessel()).getAliasMap());
			aramaSonuc.setArayuz(this);
		}
		return aramaSonuc;
	}

	public void setSecili(Object secili) {
		Vessel vessel = (Vessel) secili;
		id = vessel.getVesselId();
		txtCompany.setText(vessel.getCompany());
		cmbFlag.setSelectedItem(vessel.getFlag());
		txtVesselCode.setText(vessel.getVesselCode());
		txtVesselName.setText(vessel.getVesselName());
		cmbPort.setSelectedItem(vessel.getPort());
		this.updateUI();
	}

	public void save() {
		id = VesselOperation.getInstance().create(getVesselFromPanel());
		updateRecords();
	}

	public void delete() {
		if (id != null) {
			Vessel vessel = new Vessel();
			vessel.setVesselId(id);
			VesselOperation.getInstance().delete(vessel);
			updateRecords();
		}
	}

	public void update() {
		Vessel vessel = getVesselFromPanel();
		vessel.setVesselId(id);
		VesselOperation.getInstance().update(vessel);
		updateRecords();
	}

	private Vessel getVesselFromPanel() {
		Vessel vessel = new Vessel();
		vessel.setCompany(txtCompany.getText());
		vessel.setFlag((Country) cmbFlag.getSelectedItem());
		vessel.setVesselCode(txtVesselCode.getText());
		vessel.setVesselName(txtVesselName.getText());
		vessel.setPort((Location) cmbPort.getSelectedItem());
		return vessel;
	}

	private DugmePanel getDugmePanel() {
		if (dugmePanel == null) {
			dugmePanel = new DugmePanel();
			dugmePanel.setBounds(new java.awt.Rectangle(50, 360, 311, 31));
			dugmePanel.setDugmeListener(this);
		}
		return dugmePanel;
	}

	private JTextField getTxtCompany() {
		if (txtCompany == null) {
			txtCompany = new JTextField();
			txtCompany.setBounds(new java.awt.Rectangle(100, 140, 291, 21));
		}
		return txtCompany;
	}

	private JSteppedComboBox getCmbPort() {
		if (cmbPort == null) {
			cmbPort = new JSteppedComboBox(LocationOperation.getInstance().ara(
					new Location()).toArray());
			cmbPort.setBounds(new java.awt.Rectangle(100, 80, 291, 21));
		}
		return cmbPort;
	}

	/**
	 * This method initializes jComboBox
	 * 
	 * @return javax.swing.JSteppedComboBox
	 */
	private JSteppedComboBox getCmbFlag() {
		if (cmbFlag == null) {
			cmbFlag = new JSteppedComboBox(CountryOperation.getInstance().ara(
					new Country()).toArray());
			cmbFlag.setBounds(new java.awt.Rectangle(100, 110, 291, 21));
		}
		return cmbFlag;
	}
} // @jve:decl-index=0:visual-constraint="10,10"
