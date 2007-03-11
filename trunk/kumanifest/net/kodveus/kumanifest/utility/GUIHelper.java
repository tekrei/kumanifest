package net.kodveus.kumanifest.utility;

import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.text.JTextComponent;

public class GUIHelper {

	private static GUIHelper instance;

	public final static String RESIM_KONUM = "net/kodveus/kumanifest/images/";

	private GUIHelper() {

	}

	public static GUIHelper getInstance() {
		if (instance == null) {
			instance = new GUIHelper();
		}
		return instance;
	}

	public ImageIcon createImageIcon(String imageName) {
		try {
			URL image = ClassLoader.getSystemClassLoader().getResource(
					RESIM_KONUM + imageName);
			return new ImageIcon(image);
		} catch (Exception e) {
			LogHelper.getInstance().istisna(e);
			return null;
		}
	}

	public JScrollPane addScrollToTextArea(JTextComponent textArea) {
		JScrollPane scroll = new JScrollPane(textArea);
		scroll.setBounds(textArea.getBounds());
		return scroll;
	}

	public void showPanel(String title, JPanel panel) {
		showPanel(title, panel, panel.getWidth() + 20, panel.getHeight() + 30);
	}

	public void showPanel(String title, JPanel panel, int width, int height) {
		JDialog dialog = new JDialog();
		dialog.setTitle(title);
		dialog.setContentPane(panel);
		dialog.setSize(width, height);
		dialog.setVisible(true);
	}

	public JLabel createTextLabel(String label, java.awt.Rectangle bounds) {
		JLabel jlabel = new JLabel(label);
		jlabel.setBounds(bounds);
		return jlabel;
	}
}
