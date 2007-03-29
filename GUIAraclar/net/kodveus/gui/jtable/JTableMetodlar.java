/*
 * Emre tarafindan Jun 10, 2005 tarihinde yaratilmistir.
 */
package net.kodveus.gui.jtable;

import java.awt.Font;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.AbstractTableModel;


public class JTableMetodlar {
    public static void prepareTable(JTable jtable, AbstractTableModel model) {
        jtable.setModel(model);
        jtable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        jtable.setPreferredScrollableViewportSize(jtable.getPreferredSize());
        jtable.setAutoscrolls(true);

        Font oldFont = jtable.getTableHeader().getFont();
        jtable.getTableHeader().setFont(new Font(oldFont.getFontName(),
                Font.BOLD, oldFont.getSize()));
        jtable.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    }
}
