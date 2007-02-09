package net.kodveus.kumanifest.gui;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import net.kodveus.gui.araclar.AramaSonuc;
import net.kodveus.gui.araclar.AramaSonucInterface;
import net.kodveus.gui.jcombobox.JSteppedComboBox;
import net.kodveus.kumanifest.interfaces.DugmeInterface;
import net.kodveus.kumanifest.jdo.Location;
import net.kodveus.kumanifest.operation.LocationOperation;

public class LocationPanel extends JPanel implements AramaSonucInterface,
		DugmeInterface {
	private static final long serialVersionUID = 1L;

	private JLabel jLabel = null;

	private JLabel jLabel2 = null;

	private JTextField txtLocation = null;

	private JTextField txtCode = null;

	private AramaSonuc aramaSonuc = null;

	private Long id;

	private JLabel jLabel1 = null;

	private JSteppedComboBox cmbPort = null;

	private DugmePanel dugmePanel = null;

	/**
	 * This method initializes
	 * 
	 */
	public LocationPanel() {
		super();
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 */
	private void initialize() {
		jLabel1 = new JLabel();
		jLabel1.setBounds(new java.awt.Rectangle(20, 80, 81, 21));
		jLabel1.setText("Port:");
		jLabel2 = new JLabel();
		jLabel2.setBounds(new java.awt.Rectangle(20, 20, 81, 21));
		jLabel2.setText("Location:");
		jLabel = new JLabel();
		jLabel.setBounds(new java.awt.Rectangle(20, 50, 81, 21));
		jLabel.setText("Code:");
		this.setLayout(null);
		this.setSize(new java.awt.Dimension(404, 331));
		this.add(jLabel, null);
		this.add(jLabel2, null);
		this.add(getTxtCode(), null);
		this.add(getAramaSonuc(), null);
		this.add(getTxtLocation(), null);
		this.add(jLabel1, null);
		this.add(getCmbPort(), null);
		this.add(getDugmePanel(), null);
		updateRecords();
	}

	private void updateRecords() {
		// Veritabanindan kayit cekip tabloya yukleyelim
		setSecili(new Location());
		aramaSonuc.listeGuncelle(LocationOperation.getInstance().ara(
				new Location()));
	}

	/**
	 * This method initializes jTextField
	 * 
	 * @return javax.swing.JTextField
	 */
	private JTextField getTxtLocation() {
		if (txtLocation == null) {
			txtLocation = new JTextField();
			txtLocation.setBounds(new java.awt.Rectangle(100, 20, 291, 21));
		}
		return txtLocation;
	}

	private JTextField getTxtCode() {
		if (txtCode == null) {
			txtCode = new JTextField();
			txtCode.setBounds(new java.awt.Rectangle(100, 50, 291, 21));
		}
		return txtCode;
	}

	/**
	 * This method initializes aramaSonuc1
	 * 
	 * @return net.tekrei.gui.araclar.AramaSonuc
	 */
	private AramaSonuc getAramaSonuc() {
		if (aramaSonuc == null) {
			aramaSonuc = new AramaSonuc();
			aramaSonuc.setBounds(new java.awt.Rectangle(20, 110, 371, 181));
			aramaSonuc.setAliasMap((new Location()).getAliasMap());
			aramaSonuc.setArayuz(this);
		}
		return aramaSonuc;
	}

	public void setSecili(Object secili) {
		Location location = (Location) secili;
		id = location.getLocationId();
		txtLocation.setText(location.getLocation());
		cmbPort.setSelectedItem(location.getIsport());
		txtCode.setText(location.getCode());
		this.updateUI();
	}

	private JSteppedComboBox getCmbPort() {
		if (cmbPort == null) {
			cmbPort = new JSteppedComboBox(new Object[] { "Y", "N" });
			cmbPort.setBounds(new java.awt.Rectangle(100, 80, 141, 21));
		}
		return cmbPort;
	}

	public void save() {
		Location location = new Location();
		location.setLocation(txtLocation.getText());
		location.setIsport(cmbPort.getSelectedItem() + "");
		location.setCode(txtCode.getText());
		id = LocationOperation.getInstance().create(location);
		updateRecords();
	}

	public void delete() {
		if (id != null) {
			Location location = new Location();
			location.setLocationId(id);
			LocationOperation.getInstance().delete(location);
			updateRecords();
		}
	}

	public void update() {
		Location location = new Location();
		location.setLocation(txtLocation.getText());
		location.setIsport(cmbPort.getSelectedItem() + "");
		location.setCode(txtCode.getText());
		location.setLocationId(id);
		LocationOperation.getInstance().update(location);
		updateRecords();
	}

	/**
	 * This method initializes dugmePanel
	 * 
	 * @return net.kodveus.kumanifest.gui.DugmePanel
	 */
	private DugmePanel getDugmePanel() {
		if (dugmePanel == null) {
			dugmePanel = new DugmePanel();
			dugmePanel.setBounds(new java.awt.Rectangle(60, 290, 310, 37));
			dugmePanel.setDugmeListener(this);
		}
		return dugmePanel;
	}
} // @jve:decl-index=0:visual-constraint="10,10"
