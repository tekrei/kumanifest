package net.kodveus.gui.jtextfield.jdatepicker;

import java.awt.Color;
import java.awt.Insets;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.MenuSelectionManager;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;
import javax.swing.plaf.ComboBoxUI;
import javax.swing.plaf.basic.ComboPopup;
import javax.swing.plaf.metal.MetalComboBoxButton;
import javax.swing.plaf.metal.MetalComboBoxUI;

import net.kodveus.gui.araclar.TipCevirici;
import net.kodveus.kaynaklar.ImageLoader;

import com.sun.java.swing.plaf.motif.MotifComboBoxUI;
import com.sun.java.swing.plaf.windows.WindowsComboBoxUI;


//////////////////////////////////////////////////////////////
public class JDatePicker extends JComboBox {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public JDatePicker() {
        super();
        super.setEditable(true);
    }

    public JDatePicker(Date tarih) {
        this();
        setSelectedItem(tarih);
    }

    public void setSelectedItem(Object obj) {
        try {
            setSelectedItem((Date) obj);
        } catch (Exception e) {
        }
    }

    public void setSelectedItem(Date tarih) {
        super.setSelectedItem(TipCevirici.cevirDate(tarih));
        hidePopup();
    }

    public Date getSelectedDate() {
        Object selectedItem = super.getSelectedItem();

        if (selectedItem != null) {
            return TipCevirici.cevirDate(selectedItem.toString());
        }

        return null;
    }

    public void updateUI() {
        ComboBoxUI cui = (ComboBoxUI) UIManager.getUI(this);

        if (cui instanceof MetalComboBoxUI) {
            cui = new MetalDateComboBoxUI();
        } else if (cui instanceof MotifComboBoxUI) {
            cui = new MotifDateComboBoxUI();
        } else if (cui instanceof WindowsComboBoxUI) {
            cui = new WindowsDateComboBoxUI();
        }

        if (cui != null) {
            setUI(cui);
        }
    }

	//////////////////////////////////////////////////////////////
    // UI Inner classes -- one for each supported Look and Feel
    //////////////////////////////////////////////////////////////
    class MetalDateComboBoxUI extends MetalComboBoxUI {
        protected ComboPopup createPopup() {
            return new DatePopup(comboBox);
        }

        protected JButton createArrowButton() {
            JButton button = new MetalComboBoxButton(comboBox, ImageLoader.getImageIcon("takvim.gif"),
                    comboBox.isEditable() ? true : false, currentValuePane,
                    listBox);
            button.setMargin(new Insets(1, 1, 1, 1));

            return button;
        }
    }

    class WindowsDateComboBoxUI extends WindowsComboBoxUI {
        protected ComboPopup createPopup() {
            return new DatePopup(comboBox);
        }

        protected JButton createArrowButton() {
            JButton button = new JButton(ImageLoader.getImageIcon("takvim.gif"));

            return button;
        }
    }

    class MotifDateComboBoxUI extends MotifComboBoxUI {
        /**
         *
         */
        private static final long serialVersionUID = 1L;

        protected ComboPopup createPopup() {
            return new DatePopup(comboBox);
        }
    }

    //////////////////////////////////////////////////////////////
    // DatePopup inner class
    //////////////////////////////////////////////////////////////
    class DatePopup implements ComboPopup, MouseMotionListener, MouseListener,
        KeyListener, PopupMenuListener {
        protected JComboBox comboBox;
        protected JPopupMenu popup;
        protected JPanel jpnlSearch;
        protected Color selectedBackground;
        protected Color selectedForeground;
        protected Color background;
        protected Color foreground;
        protected JList list = new JList();
        protected boolean mouseInside = false;
        protected boolean hideNext = false;
        protected TakvimPanel pnlTakvim;

        public DatePopup(JComboBox comboBox) {
            this.comboBox = comboBox;

            // check Look and Feel
            background = UIManager.getColor("ComboBox.background");
            foreground = UIManager.getColor("ComboBox.foreground");
            selectedBackground = UIManager.getColor(
                    "ComboBox.selectionBackground");
            selectedForeground = UIManager.getColor(
                    "ComboBox.selectionForeground");
            initializePopup();
        }

        //========================================
        // begin ComboPopup method implementations
        //
        public void show() {
            updatePopup();

            try {
                popup.show(comboBox, 0, comboBox.getHeight());
            } catch (Exception e) {
                System.out.println("show():" + e.toString());
            }
        }

        public void hide() {
            try {
                MenuSelectionManager.defaultManager().clearSelectedPath();
            } catch (Exception e) {
                System.out.println("hide():" + e.toString());
            }

            popup.setVisible(false);
        }

        public JList getList() {
            return list;
        }

        public MouseListener getMouseListener() {
            return this;
        }

        public MouseMotionListener getMouseMotionListener() {
            return this;
        }

        public KeyListener getKeyListener() {
            return this;
        }

        public boolean isVisible() {
            return popup.isVisible();
        }

        public void uninstallingUI() {
            popup.removePopupMenuListener(this);
        }

        // MouseListener
        public void mousePressed(MouseEvent e) {
        }

        public void mouseReleased(MouseEvent e) {
        }

        // something else registered for MousePressed
        public void mouseClicked(MouseEvent e) {
            if (!SwingUtilities.isLeftMouseButton(e)) {
                return;
            }

            if (!comboBox.isEnabled()) {
                return;
            }

            if (comboBox.isEditable()) {
                comboBox.getEditor().getEditorComponent().requestFocusInWindow();
            } else {
                comboBox.requestFocusInWindow();
            }

            togglePopup();
        }

        public void mouseEntered(MouseEvent e) {
            mouseInside = true;
        }

        public void mouseExited(MouseEvent e) {
            mouseInside = false;
        }

        public void mouseDragged(MouseEvent e) {
        }

        public void mouseMoved(MouseEvent e) {
        }

        // KeyListener
        public void keyPressed(KeyEvent e) {
            if ((e.getKeyCode() == KeyEvent.VK_SPACE) ||
                    (e.getKeyCode() == KeyEvent.VK_ENTER)) {
                if (!comboBox.isEnabled()) {
                    System.out.println("here");

                    return;
                }

                if (comboBox.isEditable()) {
                    comboBox.getEditor().getEditorComponent()
                            .requestFocusInWindow();
                } else {
                    comboBox.requestFocusInWindow();
                }

                togglePopup();
            }
        }

        public void keyTyped(KeyEvent e) {
        }

        public void keyReleased(KeyEvent e) {
        }

        public void popupMenuCanceled(PopupMenuEvent e) {
        }

        public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {
            hideNext = mouseInside;
            SwingUtilities.windowForComponent(comboBox).requestFocus();
            comboBox.requestFocus();
            comboBox.transferFocus();
        }

        public void popupMenuWillBecomeVisible(PopupMenuEvent e) {
        }

        protected void togglePopup() {
            if (isVisible() || hideNext) {
                hide();
            } else {
                show();
            }

            hideNext = false;
            this.updatePopup();
        }

        protected void initializePopup() {
            pnlTakvim = new TakvimPanel(((JDatePicker) comboBox).getSelectedDate(),
                    (JDatePicker) comboBox);

            popup = new JPopupMenu();
            popup.setBackground(background);
            popup.setBorder(BorderFactory.createLineBorder(Color.black));
            popup.addPopupMenuListener(this);
            popup.add(pnlTakvim);
            updatePopup();
        }

        protected void updatePopup() {
            try {
                if (((JDatePicker) comboBox).getSelectedDate() != null) {
                    pnlTakvim.setDate(((JDatePicker) comboBox).getSelectedDate());
                }

                pnlTakvim.guncelle();
            } catch (NullPointerException e) {
            }

            popup.pack();
        }
    }
}
