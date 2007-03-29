package net.kodveus.gui.jtextfield;

import java.awt.KeyboardFocusManager;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JTextArea;
import javax.swing.text.Document;

public class NoTabTextArea extends JTextArea {
	private static final long serialVersionUID = 1L;

	public NoTabTextArea() {
		super();
		setTraversalKeys();
	}

	public NoTabTextArea(Document doc, String text, int rows, int columns) {
		super(doc, text, rows, columns);
		setTraversalKeys();
	}

	public NoTabTextArea(Document doc) {
		super(doc);
		setTraversalKeys();
	}

	public NoTabTextArea(int rows, int columns) {
		super(rows, columns);
		setTraversalKeys();
	}

	public NoTabTextArea(String text, int rows, int columns) {
		super(text, rows, columns);
		setTraversalKeys();
	}

	public NoTabTextArea(String text) {
		super(text);
		setTraversalKeys();
	}

	private void setTraversalKeys() {
		this.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_TAB) {
					e.consume();
					KeyboardFocusManager.getCurrentKeyboardFocusManager()
							.focusNextComponent();
				}

				if (e.getKeyCode() == KeyEvent.VK_TAB && e.isShiftDown()) {
					e.consume();
					KeyboardFocusManager.getCurrentKeyboardFocusManager()
							.focusPreviousComponent();
				}
			}
		});
	}

}