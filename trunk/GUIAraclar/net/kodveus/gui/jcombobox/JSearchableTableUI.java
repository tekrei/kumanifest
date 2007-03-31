package net.kodveus.gui.jcombobox;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

import net.kodveus.gui.araclar.AliasMap;
import net.kodveus.gui.araclar.ClassUtilities;
import net.kodveus.gui.araclar.TipCevirici;
import net.kodveus.gui.araclar.VeriSinif;
import net.kodveus.gui.jtable.GenericTableModel;
import net.kodveus.gui.jtable.TableSorter;

public class JSearchableTableUI extends JPanel {

	private static final long serialVersionUID = 1L;
	private static Object requestSource;
    private GenericTableModel model;
    private JTable jtable;
    private TableSorter sorter;
    private ArrayList<VeriSinif> liste;
    private JPanel jpnlSearch;
    private VeriSinif selected;
    private STInterface listener;
    JDialog self;
    private DefaultItemListener dil;
    private AliasMap aliasMap;

    /*private JSearchableTableUI(STInterface _listener, ArrayList<VeriSinif> _liste,
        VeriSinif _selected, JDialog _self) {
        listener = _listener;
        liste = _liste;
        selected = _selected;
        self = _self;
        init();
    }*/
    private JSearchableTableUI(STInterface _listener, ArrayList<VeriSinif> _liste,
            VeriSinif _selected, JDialog _self,AliasMap _aliasMap) {
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
        header.add(getSearchPanel(getAliasMap()), BorderLayout.NORTH);

        this.add(header);
    }
    /**
     * @author Erdem Eser EKinci
     * D�zeltmeleri yapt?m,
     * @return
     */
    protected AliasMap getAliasMap() {
    	AliasMap resultAliasMap = null;
        try {
        	if(this.aliasMap != null)
        		resultAliasMap = this.aliasMap;
            else if (liste.size() > 0) {
            	resultAliasMap = liste.get(0).getAliasMap();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return resultAliasMap;
    }

    private JPanel getSearchPanel(final AliasMap aliasMap) {
        if (aliasMap != null) {
            jpnlSearch = new JPanel(new GridLayout(0, 4));

            for (int i = 0; i < aliasMap.getAliasCount(); i++) {
                jpnlSearch.add(new JLabel(aliasMap.getAlias(i) + ":"));

                JTextField jtext = new JTextField();
                jtext.setName(aliasMap.getAttributeName(aliasMap.getAlias(i)));
                jpnlSearch.add(jtext);
                jtext.addKeyListener(new KeyAdapter() {
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
                            requestFilter(jtf.getName(), jtf.getText());
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
            }

            return jpnlSearch;
        }

        return getKayitYokPanel(self);
    }

    void oncekiKayit() {
        if (jtable.getSelectedRow() > 0) {
            jtable.setRowSelectionInterval(jtable.getSelectedRow() - 1,
                jtable.getSelectedRow() - 1);
        }
    }

    void sonrakiKayit() {
        if (jtable.getSelectedRow() < (jtable.getRowCount() - 1)) {
            jtable.setRowSelectionInterval(jtable.getSelectedRow() + 1,
                jtable.getSelectedRow() + 1);
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

    public void requestFilter(String filterName, String filter) {
        sorter.setTableModel(new GenericTableModel(getAliasMap(),
                getFilteredList(filterName, filter),false,false));
        jtable.getSelectionModel().setSelectionInterval(0, 0);

        //prepareColumnWidth();
        jtable.updateUI();
    }

    protected ArrayList<VeriSinif> getFilteredList(String filterName, String filter) {
        ArrayList<VeriSinif> filteredList = new ArrayList<VeriSinif>();

        for (int i = 0; i < liste.size(); i++) {
            VeriSinif dvo = liste.get(i);

            if (dvo != null) {
                String deger = getStringDeger(ClassUtilities.getPropertyWithFastGetMethod(
                            dvo, filterName));

                if ((deger != null) && (filter != null)) {
                    deger = deger.toUpperCase(new java.util.Locale("tr", "TR"));
                    filter = filter.toUpperCase(new java.util.Locale("tr", "TR"));
                    //TODO:Bu kod parcasi icerisinde filtre gecen
                    //tum kayitlari buluyor
                    /*Pattern p = Pattern.compile(filter);
                    Matcher m = p.matcher(deger);
                    if (m.find()) {
                        filteredList.addDVO(liste.getDVO(i));
                    }*/
                    if (deger.startsWith(filter)) {
                        filteredList.add(liste.get(i));
                    }

                }
            }
        }
        return filteredList;
    }

    private String getStringDeger(Object deger) {
        if (deger != null) {
            if (deger instanceof String) {
                return (String) deger;
            }

            if (deger instanceof Date) {
                return TipCevirici.cevirDate((Date) deger);
            }

            return deger.toString();
        }

        return null;
    }

    private void prepareTable() {
        prepareTable(liste);
    }

    void enterKeyPressed() {
        try {
            if (jtable.getSelectedRow() > -1) {
                selected = (VeriSinif) sorter.getObjectAt(jtable.getSelectedRow());

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
    public static void showPopup(ArrayList<VeriSinif> liste, VeriSinif selected,
            STInterface listener, DefaultItemListener dil, Object _requestSource) {
    	showPopup(liste,selected,listener,dil,_requestSource,null);
    }

    public static void showPopup(ArrayList<VeriSinif> liste, VeriSinif selected,
        STInterface listener, DefaultItemListener dil, Object _requestSource,AliasMap aliasMap) {
        requestSource = _requestSource;

        JDialog dialog = (new JOptionPane()).createDialog(null, "");
        dialog.setLocation(0, 0);
        dialog.getContentPane().removeAll();

        if ((liste == null) || (liste.size() <= 0)) {
            dialog.getContentPane().add(getKayitYokPanel(dialog));
        } else {
            JSearchableTableUI tableUI = new JSearchableTableUI(listener,
                    liste, selected, dialog,aliasMap);

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
        AliasMap aliasMap = getAliasMap();

        if (aliasMap != null) {
            model = new GenericTableModel(aliasMap, items,false,false);
            sorter = new TableSorter(model);
            jtable = new JTable(sorter);

            //prepareColumnWidth();
            jtable.setAutoscrolls(true);
            jtable.getSelectionModel().setSelectionInterval(0, 0);
            sorter.setTableHeader(jtable.getTableHeader());
            jtable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

            jtable.addMouseListener(new MouseAdapter() {
                    public void mouseClicked(MouseEvent me) {
                        if (me.getClickCount() == 2) {
                            enterKeyPressed();
                            me.consume();
                        }
                    }
                });
            jtable.addKeyListener(new KeyAdapter() {
                    public void keyPressed(KeyEvent ke) {
                        if (ke.getKeyCode() == KeyEvent.VK_ENTER) {
                            enterKeyPressed();
                            ke.consume();
                        }

                        if (ke.getKeyCode() == KeyEvent.VK_ESCAPE) {
                            self.dispose();
                        }
                    }
                });
            jtable.updateUI();
        }
    }

    /*private void prepareColumnWidth() {
        jtable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        int rowCount = sorter.getRowCount();

        for (int i = 0; i < sorter.getColumnCount(); i++) {
            javax.swing.table.TableColumn col = jtable.getColumnModel().getColumn(i);
            int width = decideColumnLenght(i, rowCount);
            col.setPreferredWidth(width * 10);
        }
    }

    private int decideColumnLenght(int col, int rowCount) {
        String columnName = sorter.getColumnName(col);
        int max = columnName.trim().length();

        for (int i = 0; i < rowCount; i++) {
            Object value = sorter.getValueAt(i, col);

            if ((value != null) && (value.toString().trim().length() > max)) {
                max = value.toString().trim().length();
            }
        }

        return max;
    }*/
}
