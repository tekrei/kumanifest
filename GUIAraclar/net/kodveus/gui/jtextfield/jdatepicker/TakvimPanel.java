/*
 * Emre tarafindan Jun 14, 2005 tarihinde yaratilmistir.
 */
package net.kodveus.gui.jtextfield.jdatepicker;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormatSymbols;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import net.kodveus.gui.araclar.TipCevirici;


/**
 * @author Emre
 */
public class TakvimPanel extends JPanel {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    JDatePicker parent;
    JSpinner spnYil;
    JPanel pnlGunler;
    JLabel lblAy;
    Calendar bugun;
    String[] aylar = {
            "OCAK", "?UBAT", "MART", "N?SAN", "MAYIS", "HAZ?RAN", "TEMMUZ",
            "A?USTOS", "EYL�L", "EK?M", "KASIM", "ARALIK"
        };
    String[] gunler = { "Pa", "Pt", "Sa", "�a", "Pe", "Cu", "Ct" };
    int[] monthLength = { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
    GunHucre[] cells;
    DateFormatSymbols dfs;
    Font font;

    public TakvimPanel(Date tarih, JDatePicker _parent) {
        bugun = Calendar.getInstance(new Locale("TR", "tr"));

        if (tarih != null) {
            bugun.setTime(tarih);
        }

        dfs = new DateFormatSymbols();
        font = new Font("Arial", Font.PLAIN, 10);
        parent = _parent;
        init();
    }

    public TakvimPanel(JDatePicker _parent) {
        this(new Date(System.currentTimeMillis()), _parent);
    }

    public void guncelle() {
        try {
            this.remove(pnlGunler);
            lblAy.setText(aylar[bugun.get(Calendar.MONTH)]);
            this.add(getGunPanel());
        } catch (NullPointerException e) {
        }

        this.updateUI();
    }

    private void init() {
        this.setLayout(new BorderLayout());
        this.setFont(font);
        this.setBorder(BorderFactory.createTitledBorder("Takvim"));

        //Once ust kesimi ayarlayalim
        this.add(getAyPanel(), BorderLayout.NORTH);

        //Gun paneli
        this.add(getGunPanel(), BorderLayout.CENTER);
        this.updateUI();
    }

    private JPanel getAyPanel() {
        JPanel pnlAy = new JPanel(new GridLayout(0, 4));
        pnlAy.add(getOncekiAyButton());
        lblAy = createLabel(aylar[bugun.get(Calendar.MONTH)], false);
        lblAy.setFont(font);
        lblAy.setHorizontalAlignment(JLabel.CENTER);
        pnlAy.add(lblAy);
        pnlAy.add(getSonrakiAyButton());
        pnlAy.add(getSpnYil());

        return pnlAy;
    }

    private JSpinner getSpnYil() {
        Calendar temp = Calendar.getInstance();
        temp.setTime(bugun.getTime());

        Date initDate = temp.getTime();
        temp.add(Calendar.YEAR, -100);

        Date earliestDate = temp.getTime();
        temp.add(Calendar.YEAR, 200);

        Date latestDate = temp.getTime();
        SpinnerDateModel model = new SpinnerDateModel(initDate, earliestDate,
                latestDate, Calendar.YEAR);
        spnYil = new JSpinner(model);
        spnYil.setName("spnYil");
        spnYil.addChangeListener(new ChangeListener() {
                public void stateChanged(ChangeEvent e) {
                    Date tarih = (Date) spnYil.getValue();
                    Calendar cal = Calendar.getInstance();
                    cal.setTime(tarih);

                    int yil = cal.get(Calendar.YEAR);
                    bugun.set(yil, getMonth(), getDay());
                    guncelle();
                }
            });
        spnYil.setEditor(new JSpinner.DateEditor(spnYil, "yyyy"));

        return spnYil;
    }

    private JButton getOncekiAyButton() {
        JButton btn = new JButton("<");
        btn.setPreferredSize(new Dimension(40, 15));
        btn.setFont(font);
        btn.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    ayDegistir(false);
                    guncelle();
                }
            });

        return btn;
    }

    private JButton getSonrakiAyButton() {
        JButton btn = new JButton(">");
        btn.setPreferredSize(new Dimension(40, 15));
        btn.setFont(font);
        btn.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    ayDegistir(true);
                    guncelle();
                }
            });

        return btn;
    }

    private JLabel getKisaGun(int haftaGunu) {
        return createLabel(gunler[haftaGunu], true);
    }

    private JLabel getBosluk() {
        return createLabel("", false);
    }

    private JPanel getGunPanel() {
        pnlGunler = new JPanel(new GridLayout(0, 7));
        pnlGunler.setBorder(BorderFactory.createBevelBorder(1));

        Calendar dtFirstOfMonth = Calendar.getInstance();
        dtFirstOfMonth.set(getYear(), getMonth(), 1);

        int currentDay = getDay();
        lblAy.setText(aylar[getMonth()]);

        int max;

        if ((getMonth() == 1) && (bugun instanceof GregorianCalendar) &&
                ((GregorianCalendar) bugun).isLeapYear(getYear())) {
            max = 29;
        } else {
            max = monthLength[getMonth()];
        }

        if (currentDay > max) {
            currentDay = max;
            bugun.set(getYear(), getMonth(), currentDay);
        }

        int weekday = dtFirstOfMonth.get(Calendar.DAY_OF_WEEK);

        for (int i = 0; i < 7; i++)
            pnlGunler.add(getKisaGun(i));

        for (int i = 1; i < weekday; i++)
            pnlGunler.add(getBosluk());

        cells = new GunHucre[max];

        for (int i = 0; i < max; i++) {
            pnlGunler.add(cells[i] = new GunHucre(this, i + 1,
                        new Dimension(5, 5), checkTatilmi(i)));
        }

        for (int i = max; i < (42 - weekday); i++)
            pnlGunler.add(getBosluk());

        cells[currentDay - 1].activate();

        return pnlGunler;
    }

    private boolean checkTatilmi(int gun) {
        Calendar cal = Calendar.getInstance();
        cal.set(getYear(), getMonth(), gun);

        if (cal.get(Calendar.DAY_OF_WEEK) >= 6) {
            return true;
        }

        return false;
    }

    void ayDegistir(boolean arttir) {
        int iYear = getYear();
        int iMonth = getMonth();
        int iDay = getDay();

        if (arttir) {
            if (iMonth == 11) {
                iMonth = 0;
                iYear++;
            } else {
                iMonth++;
            }
        } else if (iMonth == 0) {
            iMonth = 11;
            iYear--;
        } else {
            iMonth--;
        }

        bugun.set(iYear, iMonth, iDay);
    }

    public Date getDate() {
        bugun.set(getYear(), getMonth(), getDay());

        return bugun.getTime();
    }

    public String getDateString() {
        return TipCevirici.cevirDate(getDate());
    }

    public void setDate(Date newDate) {
        bugun.setTime(newDate);
    }

    public int getYear() {
        return bugun.get(Calendar.YEAR);
    }

    public int getMonth() {
        return bugun.get(Calendar.MONTH);
    }

    public int getDay() {
        return bugun.get(Calendar.DATE);
    }

    public void setDay(int newDay) {
        bugun.set(getYear(), getMonth(), newDay);
    }

    public Calendar getCalendar() {
        return bugun;
    }

    public void setSelected(int yeniGun) {
        cells[getDay() - 1].deactivate();
        setDay(yeniGun);
        cells[yeniGun - 1].activate();
        bugun.set(getYear(), getMonth(), yeniGun);
        parent.setSelectedItem(bugun.getTime());
    }

    private JLabel createLabel(String etiket, boolean border) {
        JLabel jlabel = new JLabel(etiket);

        if (border) {
            jlabel.setBorder(BorderFactory.createEtchedBorder());
        }

        return jlabel;
    }
}
