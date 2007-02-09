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
import net.kodveus.kumanifest.jdo.ContainerType;
import net.kodveus.kumanifest.operation.ContainerTypeOperation;

public class ContainerTypePanel extends JPanel implements AramaSonucInterface,
		DugmeInterface {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JLabel jLabel = null;

	private JLabel jLabel1 = null;

	private JTextField txtType = null;

	private JTextField txtDescription = null;

	private AramaSonuc aramaSonuc = null;

	private Long id;

	private DugmePanel dugmePanel = null;

	/**
	 * This method initializes
	 * 
	 */
	public ContainerTypePanel() {
		super();
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 */
	private void initialize() {
		jLabel1 = new JLabel();
		jLabel1.setBounds(new java.awt.Rectangle(20, 50, 81, 21));
		jLabel1.setText("Description:");
		jLabel = new JLabel();
		jLabel.setBounds(new java.awt.Rectangle(20, 20, 81, 21));
		jLabel.setText("Type:");
		this.setLayout(null);
		this.setSize(new java.awt.Dimension(404, 310));
		this.add(jLabel, null);
		this.add(jLabel1, null);
		this.add(getTxtType(), null);
		this.add(getTxtDescription(), null);
		this.add(getAramaSonuc(), null);
		this.add(getDugmePanel(), null);
		updateRecords();
	}

	private void updateRecords() {
		// Veritabanindan kayit cekip tabloya yukleyelim
		setSecili(new ContainerType());
		aramaSonuc.listeGuncelle(ContainerTypeOperation.getInstance().ara(
				new ContainerType()));
	}

	/**
	 * This method initializes jTextField
	 * 
	 * @return javax.swing.JTextField
	 */
	private JTextField getTxtType() {
		if (txtType == null) {
			txtType = new JTextField();
			txtType.setBounds(new java.awt.Rectangle(100, 20, 291, 21));
		}
		return txtType;
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

	/**
	 * This method initializes aramaSonuc1
	 * 
	 * @return net.tekrei.gui.araclar.AramaSonuc
	 */
	private AramaSonuc getAramaSonuc() {
		if (aramaSonuc == null) {
			aramaSonuc = new AramaSonuc();
			aramaSonuc.setBounds(new java.awt.Rectangle(20, 80, 371, 191));
			aramaSonuc.setAliasMap((new ContainerType()).getAliasMap());
			aramaSonuc.setArayuz(this);
		}
		return aramaSonuc;
	}

	public void setSecili(Object secili) {
		ContainerType containerType = (ContainerType) secili;
		id = containerType.getContainerTypeId();
		txtType.setText(containerType.getType());
		txtDescription.setText(containerType.getDescription());
		this.updateUI();
	}

	public void save() {
		ContainerType containerType = new ContainerType();
		containerType.setType(txtType.getText());
		containerType.setDescription(txtDescription.getText());
		id = ContainerTypeOperation.getInstance().create(containerType);
		updateRecords();
	}

	public void delete() {
		if (id != null) {
			ContainerType containerType = new ContainerType();
			containerType.setContainerTypeId(id);
			ContainerTypeOperation.getInstance().delete(containerType);
			updateRecords();
		}
	}

	public void update() {
		ContainerType containerType = new ContainerType();
		containerType.setType(txtType.getText());
		containerType.setDescription(txtDescription.getText());
		containerType.setContainerTypeId(id);
		ContainerTypeOperation.getInstance().update(containerType);
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
			dugmePanel.setBounds(new java.awt.Rectangle(50, 270, 314, 31));
			dugmePanel.setDugmeListener(this);
		}
		return dugmePanel;
	}

} // @jve:decl-index=0:visual-constraint="10,10"

