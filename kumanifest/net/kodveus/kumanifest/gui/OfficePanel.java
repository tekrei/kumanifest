package net.kodveus.kumanifest.gui;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import net.kodveus.gui.araclar.AramaSonuc;
import net.kodveus.gui.araclar.AramaSonucInterface;
import net.kodveus.kumanifest.interfaces.DugmeInterface;
import net.kodveus.kumanifest.jdo.Office;
import net.kodveus.kumanifest.operation.OfficeOperation;

public class OfficePanel extends JPanel implements AramaSonucInterface,
		DugmeInterface {
	private static final long serialVersionUID = 1L;

	private JLabel jLabel = null;

	private JLabel jLabel1 = null;

	private JLabel jLabel2 = null;

	private JTextField txtOffice = null;

	private JTextField txtDescription = null;

	private JTextField txtCode = null;

	private AramaSonuc aramaSonuc = null;

	private Long id;

	private DugmePanel dugmePanel = null;

	/**
	 * This method initializes
	 * 
	 */
	public OfficePanel() {
		super();
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 */
	private void initialize() {
		jLabel2 = new JLabel();
		jLabel2.setBounds(new java.awt.Rectangle(20, 20, 81, 21));
		jLabel2.setText("Office:");
		jLabel1 = new JLabel();
		jLabel1.setBounds(new java.awt.Rectangle(20, 50, 81, 21));
		jLabel1.setText("Description:");
		jLabel = new JLabel();
		jLabel.setBounds(new java.awt.Rectangle(20, 80, 81, 21));
		jLabel.setText("Code:");
		this.setLayout(null);
		this.setSize(new java.awt.Dimension(404, 331));
		this.add(jLabel, null);
		this.add(jLabel1, null);
		this.add(jLabel2, null);
		this.add(getTxtCode(), null);
		this.add(getTxtDescription(), null);
		this.add(getAramaSonuc(), null);
		this.add(getTxtOffice(), null);
		this.add(getDugmePanel(), null);
		updateRecords();
	}

	private void updateRecords() {
		// Veritabanindan kayit cekip tabloya yukleyelim
		setSecili(new Office());
		aramaSonuc.listeGuncelle(OfficeOperation.getInstance()
				.ara(new Office()));
	}

	/**
	 * This method initializes jTextField
	 * 
	 * @return javax.swing.JTextField
	 */
	private JTextField getTxtOffice() {
		if (txtOffice == null) {
			txtOffice = new JTextField();
			txtOffice.setBounds(new java.awt.Rectangle(100, 20, 291, 21));
		}
		return txtOffice;
	}

	/**
	 * This method initializes jTextField
	 * 
	 * @return javax.swing.JTextField
	 */
	private JTextField getTxtDescription() {
		if (txtDescription == null) {
			txtDescription = new JTextField();
			txtDescription.setBounds(new java.awt.Rectangle(100, 50, 291, 21));
		}
		return txtDescription;
	}

	private JTextField getTxtCode() {
		if (txtCode == null) {
			txtCode = new JTextField();
			txtCode.setBounds(new java.awt.Rectangle(100, 80, 291, 21));
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
			aramaSonuc.setAliasMap((new Office()).getAliasMap());
			aramaSonuc.setArayuz(this);
		}
		return aramaSonuc;
	}

	public void setSecili(Object secili) {
		Office office = (Office) secili;
		id = office.getOfficeId();
		txtOffice.setText(office.getOffice());
		txtDescription.setText(office.getDescription());
		txtCode.setText(office.getCode());
		this.updateUI();
	}

	public void save() {
		Office office = new Office();
		office.setOffice(txtOffice.getText());
		office.setDescription(txtDescription.getText());
		office.setCode(txtCode.getText());
		id = OfficeOperation.getInstance().create(office);
		updateRecords();
	}

	public void delete() {
		if (id != null) {
			Office office = new Office();
			office.setOfficeId(id);
			OfficeOperation.getInstance().delete(office);
			updateRecords();
		}
	}

	public void update() {
		Office office = new Office();
		office.setOffice(txtOffice.getText());
		office.setDescription(txtDescription.getText());
		office.setCode(txtCode.getText());
		office.setOfficeId(id);
		OfficeOperation.getInstance().update(office);
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
			dugmePanel.setBounds(new java.awt.Rectangle(50, 290, 317, 36));
			dugmePanel.setDugmeListener(this);
		}
		return dugmePanel;
	}
} // @jve:decl-index=0:visual-constraint="10,10"
