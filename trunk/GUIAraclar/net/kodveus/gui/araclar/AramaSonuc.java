/*
 * Emre tarafindan Jun 11, 2005 tarihinde yaratilmistir.
 */
package net.kodveus.gui.araclar;

import java.awt.BorderLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import net.kodveus.gui.jtable.GenericTableModel;
import net.kodveus.gui.jtable.JFocusTable;
import net.kodveus.gui.jtable.JTableMetodlar;
import net.kodveus.gui.jtable.TableSorter;


public class AramaSonuc extends JPanel {

    private static final long serialVersionUID = 1L;
    public static int SIRALAMALI = 0;
    public static int NORMAL = 1;
    JTable jtable;
    GenericTableModel model;
    TableSorter sorter;
    ArrayList liste;
    AliasMap map;
    int tabloTipi;
    private AramaSonucInterface _arayuz;
    
    public AramaSonuc(){
    }

    public AramaSonuc(AliasMap _map) {
        this(_map, new ArrayList(), NORMAL);
    }

    public AramaSonuc(AliasMap _map, int _tabloTipi) {
        this(_map, new ArrayList(), _tabloTipi);
    }

    public AramaSonuc(AliasMap _map, ArrayList _liste, int _tabloTipi) {
        map = _map;
        tabloTipi = _tabloTipi;
        liste = _liste;
        jtable = new JFocusTable();
        prepareGUI();
    }

	private void prepareGUI() {
		prepareTable();

        jtable.addMouseListener(new MouseAdapter() {
                public void mouseReleased(MouseEvent e) {
                    if (jtable.getSelectedRowCount() > 0) {
                        if (_arayuz != null) {
                            _arayuz.setSecili(getSeciliNesne());
                        }
                    }
                }
            });

        this.setLayout(new BorderLayout());

        JScrollPane jscr = new JScrollPane(jtable);
        this.add(jscr, BorderLayout.CENTER);
        this.updateUI();
	}
    
    public void setAliasMap(AliasMap _map){
    	map = _map;
        tabloTipi = NORMAL;
        liste = new ArrayList();
        jtable = new JFocusTable();
        prepareGUI();
    }

    public void setArayuz(AramaSonucInterface arayuz) {
        this._arayuz = arayuz;
    }

    private void prepareTable() {
        //jtable = new JFocusTable();
        model = new GenericTableModel(map, liste, false);

        if (tabloTipi == SIRALAMALI) {
            sorter = new TableSorter(model);
            JTableMetodlar.prepareTable(jtable, sorter);
            sorter.setTableHeader(jtable.getTableHeader());
        } else {
            JTableMetodlar.prepareTable(jtable, model);
        }

        jtable.updateUI();
    }

    public Object getSeciliNesne() {
        if (tabloTipi == SIRALAMALI) {
            return sorter.getObjectAt(jtable.getSelectedRow());
        }

        return liste.get(jtable.getSelectedRow());
    }

    public ArrayList getSecili() {
        ArrayList _liste = new ArrayList();
        int[] secili = jtable.getSelectedRows();

        for (int i = 0; i < secili.length; i++) {
            _liste.add(liste.get(secili[i]));
        }

        return _liste;
    }

    public void listeGuncelle(ArrayList yeniListe) {
        this.liste = yeniListe;
        prepareTable();
        jtable.updateUI();
        this.updateUI();
    }

    public JTable getJTable() {
        return jtable;
    }
}
