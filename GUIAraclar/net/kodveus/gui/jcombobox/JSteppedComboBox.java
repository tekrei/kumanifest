package net.kodveus.gui.jcombobox;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FontMetrics;

import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.plaf.basic.BasicComboPopup;

public class JSteppedComboBox extends JComboBox {
	private static final long serialVersionUID = 1L;

	public JSteppedComboBox() {
		super();
		AutoCompletion.enable(this);
	}

	public JSteppedComboBox(Object[] objArray) {
		super(objArray);
		AutoCompletion.enable(this);
	}

	public void updateUI() {
		super.updateUI();
		resizeComboPopup();
	}

	private void resizeComboPopup() {
		FontMetrics fm = getFontMetrics(getFont());
		BasicComboPopup popup = (BasicComboPopup) getUI().getAccessibleChild(
				this, 0);

		if (popup == null) {
			return;
		}

		int size = (int) getPreferredSize().getWidth();

		for (int i = 0; i < getItemCount(); i++) {
			try {
				String str = getItemAt(i).toString();

				if (size < fm.stringWidth(str)) {
					size = fm.stringWidth(str);
				}
			} catch (NullPointerException e) {
			}
		}

		Component comp = popup.getComponent(0);
		JScrollPane scrollpane = null;
		int offset = 0;

		if (comp instanceof JScrollPane) {
			scrollpane = (JScrollPane) comp;

			if (scrollpane.getVerticalScrollBar().isVisible()) {
				offset += scrollpane.getVerticalScrollBar().getWidth();
			}
		}

		popup.setPreferredSize(new Dimension(size + offset + 20, 85));
		popup.setLayout(new BorderLayout());
		popup.add(comp, BorderLayout.CENTER);
	}
}
