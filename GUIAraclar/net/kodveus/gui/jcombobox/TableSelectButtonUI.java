package net.kodveus.gui.jcombobox;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPanel;

import net.kodveus.gui.arabirim.TableSelectButtonInterface;
import net.kodveus.kaynaklar.ImageLoader;


public class TableSelectButtonUI extends JPanel{

	private static final long serialVersionUID = 1L;
	TableSelectButtonInterface parent;
    Color defaultColor;
    JButton secButton;
    JButton temizleButton;

    private Icon secIcon = null;
    private Icon temizleIcon = null;

    public TableSelectButtonUI(TableSelectButtonInterface _parent){
        this.parent = _parent;
        init();
    }
    
    public void odakDinleyici(FocusListener _listener){
        temizleButton.addFocusListener(_listener);
    }

    public void addKeyListener(KeyListener keyListener){
        secButton.addKeyListener(keyListener);
        temizleButton.addKeyListener(keyListener);
    }

    public void init(){
        if(secIcon!=null){
            this.secIcon = ImageLoader.getImageIcon("sec.gif");
        }
        if(temizleIcon!=null){
            this.temizleIcon = ImageLoader.getImageIcon("temizle.gif");
        }
        this.setLayout(new BorderLayout());
        this.add(getButton(),BorderLayout.WEST);
        this.add(getTemizleButton(),BorderLayout.EAST);
    }

    private JComponent getButton() {
        secButton = new JButton();
        defaultColor = secButton.getBackground();
        secButton.setPreferredSize(new Dimension(20,20));
        secButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    parent.showPopup();
                }
            });
        secButton.addKeyListener(new KeyAdapter() {
                public void keyPressed(KeyEvent e) {
                    if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                        parent.showPopup();
                        e.consume();
                    }
                }
            });
        secButton.addFocusListener(new FocusListener(){
            public void focusGained(FocusEvent e){
                secButton.setBackground(Color.YELLOW);
            }
            public void focusLost(FocusEvent e){
                secButton.setBackground(defaultColor);
            }
        });
        if(secIcon!=null)
            secButton.setIcon(secIcon);
        else
            secButton.setText("...");
        return secButton;
    }

    private JComponent getTemizleButton() {
        //TODO:Button
        temizleButton = new JButton();
        temizleButton.setPreferredSize(new Dimension(20,20));
        temizleButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    parent.clear();
                }
            });
        temizleButton.addKeyListener(new KeyAdapter() {
                public void keyPressed(KeyEvent e) {
                    if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                        parent.clear();
                        e.consume();
                    }
                }
            });
        temizleButton.setNextFocusableComponent(this.getNextFocusableComponent());
        temizleButton.addFocusListener(new FocusListener(){
            public void focusGained(FocusEvent e){
                temizleButton.setBackground(Color.YELLOW);
            }
            public void focusLost(FocusEvent e){
                temizleButton.setBackground(defaultColor);
            }
       });
        if(temizleIcon!=null)
            temizleButton.setIcon(temizleIcon);
        else
            temizleButton.setText("...");
        return temizleButton;
    }

}
