/*
 * Created on Jul 3, 2006
 *
 */
package net.kodveus.kumanifest.gui;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import net.kodveus.gui.araclar.AramaSonuc;
import net.kodveus.gui.araclar.AramaSonucInterface;
import net.kodveus.kumanifest.interfaces.DugmeInterface;
import net.kodveus.kumanifest.jdo.Country;
import net.kodveus.kumanifest.operation.CountryOperation;

public class CountryPanel extends JPanel implements AramaSonucInterface,
		DugmeInterface {

	private static final long serialVersionUID = 1L;

	private JLabel jLabel = null;

	private JLabel jLabel2 = null;

	private JTextField txtCode = null;

	private JTextField txtName = null;

	private AramaSonuc aramaSonuc = null;

	private DugmePanel dugmePanel = null;

	private Long id;

	public CountryPanel() {
		super();
		initialize();
	}

	private void initialize() {
		jLabel = new JLabel();
		jLabel.setBounds(new java.awt.Rectangle(20, 20, 81, 21));
		jLabel.setText("Code:");
		jLabel2 = new JLabel();
		jLabel2.setBounds(new java.awt.Rectangle(20, 50, 81, 21));
		jLabel2.setText("Name:");
		this.setLayout(null);
		this.setSize(new java.awt.Dimension(404, 311));
		this.add(jLabel, null);
		this.add(jLabel2, null);
		this.add(getTxtCode(), null);
		this.add(getTxtName(), null);
		this.add(getAramaSonuc(), null);
		this.add(getDugmePanel(), null);
		updateRecords();
	}

	private void updateRecords() {
		// Veritabanindan kayit cekip tabloya yukleyelim
		setSecili(new Country());
		aramaSonuc.listeGuncelle(CountryOperation.getInstance().ara(
				new Country()));
	}

	private JTextField getTxtCode() {
		if (txtCode == null) {
			txtCode = new JTextField();
			txtCode.setBounds(new java.awt.Rectangle(100, 20, 291, 21));
		}
		return txtCode;
	}

	private JTextField getTxtName() {
		if (txtName == null) {
			txtName = new JTextField();
			txtName.setBounds(new java.awt.Rectangle(100, 50, 291, 21));
		}
		return txtName;
	}

	/**
	 * This method initializes aramaSonuc1
	 * 
	 * @return net.tekrei.gui.araclar.AramaSonuc
	 */
	private AramaSonuc getAramaSonuc() {
		if (aramaSonuc == null) {
			aramaSonuc = new AramaSonuc();
			aramaSonuc.setBounds(new java.awt.Rectangle(20, 80, 371, 181));
			aramaSonuc.setAliasMap((new Country()).getAliasMap());
			aramaSonuc.setArayuz(this);
		}
		return aramaSonuc;
	}

	public void setSecili(Object secili) {
		Country country = (Country) secili;
		id = country.getCountryId();
		txtName.setText(country.getName());
		txtCode.setText(country.getCode());
		this.updateUI();
	}

	public void save() {
		Country country = new Country();
		country.setName(txtName.getText());
		country.setCode(txtCode.getText());
		id = CountryOperation.getInstance().create(country);
		updateRecords();
	}

	public void delete() {
		if (id != null) {
			Country country = new Country();
			country.setCountryId(id);
			CountryOperation.getInstance().delete(country);
			updateRecords();
		}
	}

	public void update() {
		Country country = new Country();
		country.setName(txtName.getText());
		country.setCode(txtCode.getText());
		country.setCountryId(id);
		CountryOperation.getInstance().update(country);
		updateRecords();
	}

	private DugmePanel getDugmePanel() {
		if (dugmePanel == null) {
			dugmePanel = new DugmePanel();
			dugmePanel.setBounds(new java.awt.Rectangle(50, 270, 311, 38));
			dugmePanel.setDugmeListener(this);
		}
		return dugmePanel;
	}
} // @jve:decl-index=0:visual-constraint="10,10"
