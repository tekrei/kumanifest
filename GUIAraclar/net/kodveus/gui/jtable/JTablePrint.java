/*
 * Created on Jun 17, 2005
 *
 * Window - Preferences - Java - Code Style - Code Templates
 */
package net.kodveus.gui.jtable;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.print.PageFormat;
import java.awt.print.Paper;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;

import javax.swing.JTable;


public class JTablePrint implements Printable {
    JTable tableView;

    public JTablePrint(JTable _tableView) {
        tableView = _tableView;
    }

    public static void yazdir(JTable _jtable) {
        PrinterJob pj = PrinterJob.getPrinterJob();
        pj.setPrintable(new JTablePrint(_jtable));
        pj.printDialog();

        try {
            pj.print();
        } catch (Exception PrintException) {
        }
    }

    public int print(Graphics g, PageFormat pageFormat, int pageIndex)
        throws PrinterException {
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.black);

        //pageFormat.setOrientation(PageFormat.LANDSCAPE);
        Paper paper = pageFormat.getPaper();
        paper.setImageableArea(5, 15, 595, 780);
        pageFormat.setPaper(paper);

        int fontHeight = g2.getFontMetrics().getHeight();
        //int fontDesent = g2.getFontMetrics().getDescent();

        //leave room for page number
        double pageHeight = pageFormat.getImageableHeight() - fontHeight;
        double pageWidth = pageFormat.getImageableWidth();
        double tableWidth = tableView.getColumnModel().getTotalColumnWidth();
        double scale = 1;

        //if (tableWidth >= pageWidth) {
        scale = pageWidth / tableWidth;

        /*}else{
            scale =
        }*/
        double headerHeightOnPage = tableView.getTableHeader().getHeight() * scale;
        double tableWidthOnPage = tableWidth * scale;

        double oneRowHeight = (tableView.getRowHeight() +
            tableView.getRowMargin()) * scale;
        int numRowsOnAPage = (int) ((pageHeight - headerHeightOnPage) / oneRowHeight);
        double pageHeightForTable = oneRowHeight * numRowsOnAPage;
        int totalNumPages = (int) Math.ceil(((double) tableView.getRowCount()) / numRowsOnAPage);

        if (pageIndex >= totalNumPages) {
            return NO_SUCH_PAGE;
        }

        g2.translate(pageFormat.getImageableX(), pageFormat.getImageableY());

        // bottom center
        //g2.drawString("" + (pageIndex + 1), ((int) pageWidth / 2),fontHeight);
        g2.translate(0f, headerHeightOnPage);
        g2.translate(0f, -pageIndex * pageHeightForTable);

        //If this piece of the table is smaller 
        //than the size available,
        //clip to the appropriate bounds.
        if ((pageIndex + 1) == totalNumPages) {
            int lastRowPrinted = numRowsOnAPage * pageIndex;
            int numRowsLeft = tableView.getRowCount() - lastRowPrinted;
            g2.setClip(0, (int) (pageHeightForTable * pageIndex),
                (int) Math.ceil(tableWidthOnPage),
                (int) Math.ceil(oneRowHeight * numRowsLeft));
        }
        //else clip to the entire area available.
        else {
            g2.setClip(0, (int) (pageHeightForTable * pageIndex),
                (int) Math.ceil(tableWidthOnPage),
                (int) Math.ceil(pageHeightForTable));
        }

        g2.scale(scale, scale);
        tableView.paint(g2);
        g2.scale(1 / scale, 1 / scale);
        g2.translate(0f, pageIndex * pageHeightForTable);
        g2.translate(0f, -headerHeightOnPage);
        g2.setClip(0, 0, (int) Math.ceil(tableWidthOnPage),
            (int) Math.ceil(headerHeightOnPage));
        g2.scale(scale, scale);
        tableView.getTableHeader().paint(g2);

        //paint header at top
        return Printable.PAGE_EXISTS;
    }
}
