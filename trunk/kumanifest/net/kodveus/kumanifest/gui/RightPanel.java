package net.kodveus.kumanifest.gui;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import net.kodveus.kumanifest.MainFrame;
import net.kodveus.kumanifest.interfaces.ToolbarInterface;
import net.kodveus.kumanifest.jdo.BL;
import net.kodveus.kumanifest.utility.ToolbarHelper;

public class RightPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JTabbedPane tabPane = null;

	private BLPanel blPanel;

	private ContainerPanel containerPanel;

	// private AnaPencere _anaPencere;

	public RightPanel(MainFrame pencere) {
		super();
		// this._anaPencere = pencere;
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setLayout(new BorderLayout());
		this.setSize(800, 768);
		this.add(getTabPane(), java.awt.BorderLayout.CENTER);
	}

	/**
	 * This method initializes jTabbedPane
	 * 
	 * @return javax.swing.JTabbedPane
	 */
	private JTabbedPane getTabPane() {
		if (tabPane == null) {
			tabPane = new JTabbedPane();
			blPanel = new BLPanel();
			tabPane.addTab("BL", blPanel);
			containerPanel = new ContainerPanel();
			tabPane.addTab("Container", containerPanel);
			ToolbarHelper.getInstance().setToolbarInterface(blPanel);
			tabPane.addChangeListener(new ChangeListener() {
				public void stateChanged(ChangeEvent evt) {
					ToolbarHelper.getInstance().setToolbarInterface(
							(ToolbarInterface) tabPane.getSelectedComponent());
					checkContainerTab();
				}
			});
		}
		return tabPane;
	}

	public void checkContainerTab()
	{
//		 Eger container yukleniyorsa containerleri update edelim
		if (tabPane.getSelectedComponent() == containerPanel) {
			System.out.println("Containers loading for blId:"+blPanel.getSelectedBlId());
			containerPanel.setBlId(blPanel.getSelectedBlId());
		}
	}
	public void loadBL(BL bl) {
		blPanel.loadToPanel(bl);
		checkContainerTab();
	}

}
