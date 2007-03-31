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

	JTable jtable;
	GenericTableModel model;
	TableSorter sorter;
	ArrayList liste;
	AliasMap map;
	boolean siralanabilirKolon;
	boolean adetKolonu;
	private AramaSonucInterface _arayuz;

	public AramaSonuc() {
	}

	public AramaSonuc(AliasMap _map) {
		this(_map, new ArrayList(), false, false);
	}

	public AramaSonuc(AliasMap _map, boolean _siralamaDestegi) {
		this(_map, new ArrayList(), _siralamaDestegi, false);
	}

	public AramaSonuc(AliasMap _map, boolean _siralamaDestegi,
			boolean _adetSatirDestegi) {
		this(_map, new ArrayList(), _siralamaDestegi, _adetSatirDestegi);
	}

	public AramaSonuc(AliasMap _map, ArrayList _liste,
			boolean _siralamaDestegi, boolean _adetSatirDestegi) {
		map = _map;
		siralanabilirKolon = _siralamaDestegi;
		adetKolonu = _adetSatirDestegi;
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
		siralanabilirKolon = false;
		adetKolonu = false;
		liste = new ArrayList();
		jtable = new JFocusTable();
		prepareGUI();
	}

	public void setArayuz(AramaSonucInterface arayuz) {
		this._arayuz = arayuz;
	}

	private void prepareTable() {
		// jtable = new JFocusTable();
		model = new GenericTableModel(map, liste, false,adetKolonu);

		if (siralanabilirKolon) {
			sorter = new TableSorter(model);
			JTableMetodlar.prepareTable(jtable, sorter);
			sorter.setTableHeader(jtable.getTableHeader());
		} else {
			JTableMetodlar.prepareTable(jtable, model);
		}

		jtable.updateUI();
	}

	public Object getSeciliNesne() {
		if (siralanabilirKolon) {
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
