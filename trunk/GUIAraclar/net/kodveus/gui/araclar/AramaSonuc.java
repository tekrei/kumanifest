/*
 * Emre tarafindan Jun 11, 2005 tarihinde yaratilmistir.
 */
package net.kodveus.gui.araclar;

import java.awt.BorderLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import net.kodveus.gui.arabirim.AramaSonucInterface;
import net.kodveus.gui.jtable.GenericTableModel;
import net.kodveus.gui.jtable.JTablePrint;

import org.jdesktop.swingx.JXTable;
import org.jdesktop.swingx.decorator.AlternateRowHighlighter;
import org.jdesktop.swingx.decorator.Highlighter;
import org.jdesktop.swingx.decorator.HighlighterPipeline;

public class AramaSonuc extends JPanel {

	private static final long serialVersionUID = 1L;

	private JXTable jtable;
	private GenericTableModel model;
	private List liste;
	private AliasMap map;
	boolean adetKolonu;
	private AramaSonucInterface _arayuz;

	public AramaSonuc() {
	}

	public AramaSonuc(AliasMap _map) {
		this(_map, new ArrayList(), false);
	}

	public AramaSonuc(AliasMap _map, boolean _adetSatirDestegi) {
		this(_map, new ArrayList(), _adetSatirDestegi);
	}

	public AramaSonuc(AliasMap _map, List _liste,
			boolean _adetSatirDestegi) {
		map = _map;
		adetKolonu = _adetSatirDestegi;
		liste = _liste;
		jtable = new JXTable();
		prepareGUI();
	}

	private void prepareGUI() {
		prepareTable();

		// FIX Fare dinlemek yerine asagidaki gibi secmeleri dinlemek klavyeyi
		// de destekliyor
		jtable.getSelectionModel().addListSelectionListener(
				new ListSelectionListener() {
					public void valueChanged(ListSelectionEvent e) {
						// Gereksiz mesajlari yok say
						if (e.getValueIsAdjusting())
							return;
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
		adetKolonu = false;
		liste = new ArrayList();
		jtable = new JXTable();
		prepareGUI();
	}

	public void setArayuz(AramaSonucInterface arayuz) {
		this._arayuz = arayuz;
	}

	private void prepareTable() {
		// jtable = new JFocusTable();
		model = new GenericTableModel(map, liste, false, adetKolonu);

		jtable.setModel(model);
		jtable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		if (adetKolonu) {
			jtable.getColumnModel().getColumn(0).setPreferredWidth(35);
			jtable.getColumnModel().getColumn(0).setMaxWidth(70);
		}
		jtable.setPreferredScrollableViewportSize(jtable.getPreferredSize());
		jtable.getSelectionModel().setSelectionMode(
				ListSelectionModel.SINGLE_SELECTION);
		jtable.setColumnControlVisible(true);
		jtable.setShowHorizontalLines(false);
		jtable.setShowVerticalLines(false);
		jtable
				.setHighlighters(new HighlighterPipeline(
						new Highlighter[] { AlternateRowHighlighter.classicLinePrinter }));
		jtable.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				if (e.isControlDown() && e.getKeyChar() == KeyEvent.VK_P) {
					JTablePrint.yazdir(jtable);
				}
			}
		});
		jtable.packAll();
	}

	public Object getSeciliNesne() {
		return liste.get(jtable.getSelectedRow());
	}

	public List getSecili() {
		List _liste = new ArrayList();
		int[] secili = jtable.getSelectedRows();

		for (int i = 0; i < secili.length; i++) {
			_liste.add(liste.get(secili[i]));
		}

		return _liste;
	}

	public void listeGuncelle(List yeniListe) {
		this.liste = yeniListe;
		prepareTable();
		jtable.updateUI();
		this.updateUI();
	}

	public JTable getJTable() {
		return jtable;
	}
}
