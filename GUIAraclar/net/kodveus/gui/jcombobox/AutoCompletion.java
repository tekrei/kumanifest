package net.kodveus.gui.jcombobox;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.ComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.JTextComponent;
import javax.swing.text.PlainDocument;

public class AutoCompletion extends PlainDocument {
	private static final long serialVersionUID = 1L;

	JComboBox comboBox;

	ComboBoxModel model;

	JTextComponent editor;

	boolean hidePopupOnFocusLost;

	boolean hitBackspace;

	boolean hitBackspaceOnSelection;

	boolean selecting = false;

	public AutoCompletion(JComboBox _comboBox) {
		this.comboBox = _comboBox;
		comboBox.setEditable(true);
		model = comboBox.getModel();
		editor = (JTextComponent) comboBox.getEditor().getEditorComponent();
		editor.setDocument(this);

		// Highlight whole text when user hits enter
		// Register when user hits backspace
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!selecting) {
					highlightCompletedText(0);
				}
			}
		});
		editor.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				// if (comboBox.isDisplayable()) comboBox.setPopupVisible(true);
				hitBackspace = false;

				switch (e.getKeyCode()) {
				// determine if the pressed key is backspace (needed by the
				// remove method)
				case KeyEvent.VK_BACK_SPACE:
					hitBackspace = true;
					hitBackspaceOnSelection = editor.getSelectionStart() != editor
							.getSelectionEnd();

					break;

				// ignore delete key
				case KeyEvent.VK_DELETE:
					e.consume();
					comboBox.getToolkit().beep();

					break;
				}
			}
		});

		// Bug 5100422 on Java 1.5: Editable JComboBox won't hide popup when
		// tabbing out
		hidePopupOnFocusLost = System.getProperty("java.version").startsWith(
				"1.5");

		// Highlight whole text when gaining focus
		editor.addFocusListener(new FocusAdapter() {
			public void focusGained(FocusEvent e) {
				highlightCompletedText(0);
			}

			public void focusLost(FocusEvent e) {
				// Workaround for Bug 5100422 - Hide Popup on focus lost
				if (hidePopupOnFocusLost) {
					comboBox.setPopupVisible(false);
				}
			}
		});

		// Handle initially selected object
		Object selected = comboBox.getSelectedItem();

		if (selected != null) {
			editor.setText(selected.toString());
		}
	}

	public void remove(int offs, int length) throws BadLocationException {
		// ignore no deletion
		if (selecting) {
			return;
		}

		if (length == 0) {
			return;
		}

		// check positions
		if ((offs < 0) || (offs > getLength()) || (length < 0)
				|| ((offs + length) > getLength())) {
			throw new BadLocationException("Invalid parameters.", offs);
		}

		if (hitBackspace) {
			// user hit backspace => move the selection backwards
			// old item keeps being selected
			if (offs > 0) {
				if (hitBackspaceOnSelection) {
					offs--;
				}
			} else {
				// User hit backspace with the cursor positioned on the start =>
				// beep
				comboBox.getToolkit().beep(); // when available use:
												// UIManager.getLookAndFeel().provideErrorFeedback(comboBox);
			}

			highlightCompletedText(offs);

			// show popup when the user types
			if (comboBox.isDisplayable()) {
				comboBox.setPopupVisible(true);
			}
		} else {
			super.remove(offs, length);
		}
	}

	public void insertString(int offs, String str, AttributeSet a)
			throws BadLocationException {
		if (selecting) {
			return;
		}

		// ignore empty insert
		if ((str == null) || (str.length() == 0)) {
			return;
		}

		// check offset position
		if ((offs < 0) || (offs > getLength())) {
			throw new BadLocationException(
					"Invalid offset - must be >= 0 and <= " + getLength(), offs);
		}

		// construct the resulting string
		String currentText = getText(0, getLength());
		String beforeOffset = currentText.substring(0, offs);
		String afterOffset = currentText.substring(offs, currentText.length());
		String futureText = beforeOffset + str + afterOffset;

		// lookup and select a matching item
		Object item = lookupItem(futureText);

		if (item != null) {
			setSelectedItem(item);
		} else {
			// keep old item selected if there is no match
			item = comboBox.getSelectedItem();

			// imitate no insert (later on offs will be incremented by
			// str.length(): selection won't move forward)
			offs = offs - str.length();

			// provide feedback to the user that his input has been received but
			// can not be accepted
			comboBox.getToolkit().beep(); // when available use:
											// UIManager.getLookAndFeel().provideErrorFeedback(comboBox);
		}

		// display the completed string
		String itemString = (item == null) ? "" : item.toString();
		setText(itemString);

		// if the user selects an item via mouse the the whole string will be
		// inserted.
		// highlight the entire text if this happens.
		if (itemString.equals(str) && (offs == 0)) {
			highlightCompletedText(0);
		} else {
			highlightCompletedText(offs + str.length());

			// show popup when the user types
			if (comboBox.isDisplayable()) {
				comboBox.setPopupVisible(true);
			}
		}
	}

	void highlightCompletedText(int start) {
		editor.setCaretPosition(getLength());
		editor.moveCaretPosition(start);
	}

	private Object lookupItem(String pattern) {
		Object selectedItem = model.getSelectedItem();

		// only search for a different item if the currently selected does not
		// match
		if ((selectedItem != null)
				&& startsWithIgnoreCase(selectedItem.toString(), pattern)) {
			return selectedItem;
		}

		// iterate over all items
		for (int i = 0, n = model.getSize(); i < n; i++) {
			Object currentItem = model.getElementAt(i);

			// current item starts with the pattern?
			if ((currentItem != null)
					&& startsWithIgnoreCase(currentItem.toString(), pattern)) {
				return currentItem;
			}
		}

		// no item starts with the pattern => return null
		return null;
	}

	// checks if str1 starts with str2 - ignores case
	private boolean startsWithIgnoreCase(String str1, String str2) {
		return str1.toUpperCase(new java.util.Locale("tr", "TR")).startsWith(
				str2.toUpperCase(new java.util.Locale("tr", "TR")));
	}

	private void setSelectedItem(Object item) {
		selecting = true;
		model.setSelectedItem(item);
		selecting = false;
	}

	private void setText(String text) throws BadLocationException {
		// remove all text and insert the new text
		super.remove(0, getLength());
		super.insertString(0, text, null);
	}

	public static void enable(JComboBox comboBox) {
		new AutoCompletion(comboBox);
	}
}
