package net.kodveus.gui.jcombobox;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;

import net.kodveus.gui.arabirim.STInterface;
import net.kodveus.gui.arabirim.TableSelectButtonInterface;
import net.kodveus.gui.araclar.AliasMap;
import net.kodveus.gui.araclar.VeriSinif;
import net.kodveus.gui.jcombobox.JSearchableTableUI.DefaultItemListener;

public class TableSelectUI extends JPanel implements STInterface,TableSelectButtonInterface {

	private static final long serialVersionUID = 1L;
	ArrayList liste;
    VeriSinif selected;
    private JLabel showComponent;
    TableSelectButtonUI buttonPanel;
    STInterface me;
    DefaultItemListener dil;
    Color defaultColor;
    private AliasMap aliasMap;

    public TableSelectUI(ArrayList _liste, VeriSinif _selected, AliasMap _aliasMap) {
        this.aliasMap = _aliasMap;
    	liste = _liste;
        selected = _selected;
        me = this;
        init();
    }

    public void updateItems(ArrayList _list){
    	liste = _list;
    }

    public void setSelected(VeriSinif _dvo, Object _requestSource) {
        selected = _dvo;
        showComponent.setText(selected.toString());
    }

    public void odakDinleyici(FocusListener _listener){
        buttonPanel.odakDinleyici(_listener);
    }

    public void clear(){
        selected = null;
        showComponent.setText("");
    }

    public void setNextFocusableComponent(Component comp) {
        super.setNextFocusableComponent(comp);
        //buttonPanel.setNextFocusableComponent(comp);
    }

    public Object getSelectedItem() {
        return selected;
    }

    public void addKeyListener(KeyListener keyListener) {
        super.addKeyListener(keyListener);
        buttonPanel.addKeyListener(keyListener);
    }

    private void init() {
        this.setLayout(new BorderLayout());
        this.add(getLabel(),BorderLayout.CENTER);
        buttonPanel = new TableSelectButtonUI(this);
        this.add(buttonPanel,BorderLayout.EAST);
        this.addFocusListener(new FocusAdapter() {
                public void focusGained(FocusEvent e) {
                    buttonPanel.requestFocusInWindow();
                }
            });
    }

    private JComponent getLabel() {
        showComponent = new JLabel("");
        showComponent.setPreferredSize(new Dimension(super.getPreferredSize().width-40,super.getPreferredSize().height));

        if (selected != null) {
            showComponent.setText(selected.toString());
        }

        showComponent.setForeground(Color.RED);

        showComponent.setOpaque(true);

        return showComponent;
    }

    public void refreshItems(ArrayList list) {
        liste = list;
    }

    public void removeAllItems() {
        liste = null;
    }

    public void addItemListener(DefaultItemListener _dil) {
        this.dil = _dil;
    }

    public void setSelectedItem(VeriSinif item) {
        if (item != null) {
            showComponent.setText(item.toString());
            selected = item;
        } else {
            showComponent.setText("");
        }
    }

    public void addItem(VeriSinif dvoBase) {
        if (liste == null) {
            liste = new ArrayList();
        }

        liste.add(dvoBase);
    }

    public void showPopup(){
        JSearchableTableUI.showPopup(liste, selected, me, dil,null,this.aliasMap);
    }
    public ArrayList getAllItems() {
        return liste;
    }
}
