/*
 * Emre tarafindan Jun 18, 2005 tarihinde yaratilmistir.
 */
package net.kodveus.gui.jtable;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.filechooser.FileFilter;

import net.kodveus.gui.araclar.AliasMap;
import net.kodveus.gui.araclar.TipCevirici;
import net.kodveus.gui.araclar.VeriSinif;


/**
 * @author Emre
 * Bu sinif parametre olarak gonderdigimiz tabloyu csv olarak
 * kaydetmemizi saglar
 */
public class JTableExport {
    JTable tablo;
    JPanel pnlTablo;
    AliasMap aliasMap;
    ArrayList<VeriSinif> list;
    final JFileChooser fc = new JFileChooser();

    public JTableExport(JPanel _tabloPanel, JTable _tableView) {
        tablo = _tableView;
        pnlTablo = _tabloPanel;
    }

    private File dosyaSec() {
        fc.setFileFilter(new FileFilter() {
                public String getDescription() {
                    return "CSV Excel";
                }

                public boolean accept(File f) {
                    if (f.isDirectory()) {
                        return true;
                    }

                    if (f.getName().endsWith(".csv")) {
                        return true;
                    }

                    return false;
                }
            });
        fc.setSelectedFile(new File("export.csv"));

        int returnVal = fc.showSaveDialog(pnlTablo);

        if (returnVal == JFileChooser.APPROVE_OPTION) {
            return fc.getSelectedFile();
        }

        return null;
    }

    public static void export(JPanel _panel, JTable _table) {
        //Once bir dosya sectirmeliyiz
        JTableExport exporter = new JTableExport(_panel, _table);
        exporter.exportAsCsv();
    }

    public void exportAsCsv() {
        try {
            File dosya = dosyaSec();

            if (dosya.exists()) {
                dosya.delete();
            }

            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(
                        dosya, true));
            PrintWriter fileWriter = new PrintWriter(bufferedWriter);

            //Once basligi yazmaliyiz
            for (int i = 0; i < tablo.getColumnCount(); i++) {
                if (i > 0) {
                    fileWriter.print(",");
                }

                fileWriter.print(tablo.getModel().getColumnName(i));
            }

            fileWriter.println();

            //Daha sonra verileri
            for (int i = 0; i < tablo.getRowCount(); ++i) {
                for (int j = 0; j < tablo.getColumnCount(); ++j) {
                    if (j > 0) {
                        fileWriter.print(",");
                    }

                    fileWriter.print(getValue(tablo.getValueAt(i, j)));
                }

                fileWriter.println("");
            }

            fileWriter.close();
            JOptionPane.showMessageDialog(null,
                "Tablonun d??a aktar?m? tamamland?.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String getValue(Object nesne) {
        try {
            String value = "-";

            if (value != null) {
                if (nesne instanceof Date) {
                    value = TipCevirici.cevirDate((Date) nesne);
                } else {
                    value = nesne.toString();
                }
            }

            return value;
        } catch (Exception e) {
            return "-";
        }
    }
}
