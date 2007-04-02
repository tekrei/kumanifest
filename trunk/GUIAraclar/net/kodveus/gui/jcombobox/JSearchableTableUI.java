package net.kodveus.gui.jcombobox;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

import net.kodveus.gui.arabirim.STInterface;
import net.kodveus.gui.araclar.AliasMap;
import net.kodveus.gui.araclar.VeriSinif;
import net.kodveus.gui.jtable.GenericTableModel;

import org.jdesktop.swingx.JXTable;
import org.jdesktop.swingx.decorator.AlternateRowHighlighter;
import org.jdesktop.swingx.decorator.Filter;
import org.jdesktop.swingx.decorator.FilterPipeline;
import org.jdesktop.swingx.decorator.Highlighter;
import org.jdesktop.swingx.decorator.HighlighterPipeline;
import org.jdesktop.swingx.decorator.PatternFilter;

public class JSearchableTableUI extends JPanel {

	private static final long serialVersionUID = 1L;
	private static Object requestSource;
	private GenericTableModel model;
	private JXTable jtable;
	private ArrayList<VeriSinif> liste;
	private VeriSinif selected;
	private STInterface listener;
	JDialog self;
	private DefaultItemListener dil;
	private AliasMap aliasMap;
	private JTextField txtFilter;

	private JSearchableTableUI(STInterface _listener,
			ArrayList<VeriSinif> _liste, VeriSinif _selected, JDialog _self,
			AliasMap _aliasMap) {
		listener = _listener;
		liste = _liste;
		selected = _selected;
		self = _self;
		this.aliasMap = _aliasMap;
		init();
	}

	public void addItemListener(DefaultItemListener _dil) {
		this.dil = _dil;
	}

	private void init() {
		prepareTable();

		JScrollPane jscr = new JScrollPane(jtable);
		jscr.setPreferredSize(new Dimension(750, 450));

		JPanel header = new JPanel(new BorderLayout());

		header.setOpaque(true);

		header.add(jscr, BorderLayout.CENTER);
		header.add(getSearchTextfield(), BorderLayout.NORTH);

		this.add(header);
	}

	private JTextField getSearchTextfield() {
		if (aliasMap != null) {
			txtFilter = new JTextField();
			txtFilter.addKeyListener(new KeyAdapter() {
				public void keyReleased(KeyEvent ke) {
					if (ke.getKeyCode() == KeyEvent.VK_UP) {
						oncekiKayit();

						return;
					}

					if (ke.getKeyCode() == KeyEvent.VK_DOWN) {
						sonrakiKayit();

						return;
					}

					JTextField jtf = (JTextField) ke.getSource();
					Filter[] filterArray = { new PatternFilter(jtf.getText(),
							0, 0) };
					FilterPipeline filters = new FilterPipeline(filterArray);
					jtable.setFilters(filters);
					ke.consume();
				}

				public void keyPressed(KeyEvent ke) {
					if (ke.getKeyChar() == KeyEvent.VK_ENTER) {
						enterKeyPressed();
						ke.consume();
					}

					if (ke.getKeyChar() == KeyEvent.VK_ESCAPE) {
						self.dispose();
					}
				}
			});

			return txtFilter;
		}

		return null;
	}

	void oncekiKayit() {
		if (jtable.getSelectedRow() > 0) {
			jtable.setRowSelectionInterval(jtable.getSelectedRow() - 1, jtable
					.getSelectedRow() - 1);
		}
	}

	void sonrakiKayit() {
		if (jtable.getSelectedRow() < (jtable.getRowCount() - 1)) {
			jtable.setRowSelectionInterval(jtable.getSelectedRow() + 1, jtable
					.getSelectedRow() + 1);
		}
	}

	private static JPanel getKayitYokPanel(final JDialog _self) {
		JPanel pnl = new JPanel();
		pnl.add(new JLabel("KAYIT BULUNAMADI"));

		JButton _button = new JButton("OK");
		_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				_self.dispose();
			}
		});
		_button.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				_self.dispose();
			}
		});
		pnl.add(_button);

		return pnl;
	}

	private void prepareTable() {
		prepareTable(liste);
	}

	void enterKeyPressed() {
		try {
			if (jtable.getSelectedRow() > -1) {
				selected = liste.get(jtable.getSelectedRow());

				if (selected != null) {
					listener.setSelected(selected, requestSource);
				}

				if (dil != null) {
					dil.itemStateChanged();
				}

				self.dispose();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void showPopup(ArrayList<VeriSinif> liste,
			VeriSinif selected, STInterface listener, DefaultItemListener dil,
			Object _requestSource) {
		showPopup(liste, selected, listener, dil, _requestSource, null);
	}

	public static void showPopup(ArrayList<VeriSinif> liste,
			VeriSinif selected, STInterface listener, DefaultItemListener dil,
			Object _requestSource, AliasMap aliasMap) {
		requestSource = _requestSource;

		JDialog dialog = (new JOptionPane()).createDialog(null, "");
		dialog.setLocation(0, 0);
		dialog.getContentPane().removeAll();

		if ((liste == null) || (liste.size() <= 0)) {
			dialog.getContentPane().add(getKayitYokPanel(dialog));
		} else {
			JSearchableTableUI tableUI = new JSearchableTableUI(listener,
					liste, selected, dialog, aliasMap);

			if (dil != null) {
				tableUI.addItemListener(dil);
			}

			dialog.getContentPane().add(tableUI);
		}

		dialog.pack();
		dialog.setResizable(true);
		dialog.setModal(true);
		dialog.setBackground(Color.WHITE);
		dialog.setVisible(true);
	}

	protected void prepareTable(ArrayList<VeriSinif> items) {
		if (aliasMap != null) {
			model = new GenericTableModel(aliasMap, items, false, false);
			jtable = new JXTable();
			jtable.setModel(model);
			jtable.getSelectionModel().setSelectionInterval(0, 0);
			jtable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

			jtable.setModel(model);
			jtable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
			jtable
					.setPreferredScrollableViewportSize(jtable
							.getPreferredSize());
			jtable.setColumnControlVisible(true);
			jtable.setShowHorizontalLines(false);
			jtable.setShowVerticalLines(false);
			jtable
					.setHighlighters(new HighlighterPipeline(
							new Highlighter[] { AlternateRowHighlighter.classicLinePrinter }));
			jtable.packAll();
		}
	}

	interface DefaultItemListener {
		public void itemStateChanged();
	}
}
