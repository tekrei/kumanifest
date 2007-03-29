/*
 * Emre tarafindan Jun 14, 2005 tarihinde yaratilmistir.
 */
package net.kodveus.gui.jtextfield.jdatepicker;


/**
 * @author Emre
 */
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


class GunHucre extends Canvas {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    TakvimPanel parent;
    int gun;
    boolean secili;
    boolean tatil;

    public GunHucre(TakvimPanel _parent, int yeniGun, Dimension size,
        boolean _tatil) {
        secili = false;
        gun = yeniGun;
        fareDinleyiciEkle();
        setSize(size);
        parent = _parent;
        tatil = _tatil;
    }

    public void paint(Graphics g) {
        if (gun != 0) {
            String szDay = Integer.toString(gun);
            FontMetrics fm = getFontMetrics(getFont());
            int iXpos = (getSize().width - fm.stringWidth(szDay)) / 2;
            int iYpos = getSize().height -
                ((fm.getAscent() + fm.getDescent()) / 2);

            if (tatil) {
                g.setColor(Color.RED);
            }

            g.drawString(szDay, iXpos, iYpos);

            if (secili) {
                g.setColor(Color.BLUE);
                g.drawLine(getSize().width - 1, 0, getSize().width - 1,
                    getSize().height - 1);
                g.drawLine(0, getSize().height - 1, getSize().width - 1,
                    getSize().height - 1);
                g.drawLine(0, 0, getSize().width, 0);
                g.drawLine(0, 0, 0, getSize().height);
            }
        }
    }

    public void activate() {
        secili = true;
        repaint();
    }

    public void deactivate() {
        secili = false;
        repaint();
    }

    int getGun() {
        return gun;
    }

    private void fareDinleyiciEkle() {
        this.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent e) {
                    parent.setSelected(getGun());
                }

                public void mouseEntered(MouseEvent e) {
                    setCursor(new Cursor(12));
                }

                public void mouseExited(MouseEvent e) {
                    setCursor(new Cursor(0));
                }
            });
    }
}
