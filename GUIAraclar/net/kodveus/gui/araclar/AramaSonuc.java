/*
 * Emre tarafindan Jun 11, 2005 tarihinde yaratilmistir.
 */
package net.kodveus.gui.araclar;

import java.awt.BorderLayout;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

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
	ArrayList<Object> liste;
	AliasMap map;
	int tabloTipi;
	private AramaSonucInterface _arayuz;

	public AramaSonuc() {
	}

	public AramaSonuc(AliasMap _map) {
		this(_map, new ArrayList<Object>(), NORMAL);
	}

	public AramaSonuc(AliasMap _map, int _tabloTipi) {
		this(_map, new ArrayList<Object>(), _tabloTipi);
	}

	public AramaSonuc(AliasMap _map, ArrayList<Object> _liste, int _tabloTipi) {
		map = _map;
		tabloTipi = _tabloTipi;
		liste = _liste;
		jtable = new JFocusTable();
		prepareGUI();
	}

	private void prepareGUI() {
		prepareTable();

		// FIX Fare dinlemek yerine asagidaki gibi secmeleri dinlemek klavyeyi
		// de destekliyor
		jtable.getSelectionModel().addListSelectionListener(
				new ListSelectionListener() {
					public void valueChanged(ListSelectionEvent e) {
						// If cell selection is enabled, both row and column
						// change events are fired
						if (e.getSource() == jtable.getSelectionModel()
								&& jtable.getRowSelectionAllowed()
								&& jtable.getSelectedRowCount() > 0) {
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

	public void setAliasMap(AliasMap _map) {
		map = _map;
		tabloTipi = NORMAL;
		liste = new ArrayList<Object>();
		jtable = new JFocusTable();
		prepareGUI();
	}

	public void setArayuz(AramaSonucInterface arayuz) {
		this._arayuz = arayuz;
	}

	private void prepareTable() {
		// jtable = new JFocusTable();
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

	public ArrayList<Object> getSecili() {
		ArrayList<Object> _liste = new ArrayList<Object>();
		int[] secili = jtable.getSelectedRows();

		for (int i = 0; i < secili.length; i++) {
			_liste.add(liste.get(secili[i]));
		}

		return _liste;
	}

	public void listeGuncelle(ArrayList<Object> yeniListe) {
		this.liste = yeniListe;
		prepareTable();
		jtable.updateUI();
		this.updateUI();
	}

	public JTable getJTable() {
		return jtable;
	}
}
