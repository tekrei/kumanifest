/*
 * Emre tarafindan Jun 8, 2005 tarihinde yaratilmistir.
 */
package net.kodveus.gui.araclar;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;



/**
 * @author Emre
 */
public class TipCevirici {
    public static String TARIH_BICEM = "dd.MM.yyyy";
    private static SimpleDateFormat tarihFormat = null;
    private static DecimalFormat sayiBicim = null;

    public static String cevirObjectToString(Object obj) {
        try {
            if (obj instanceof BigDecimal) {
                return getSayiBicim().format(obj);
            }

            return obj.toString();
        } catch (NullPointerException e) {
            return "";
        }
    }

    public static Date cevirDate(String tarih) {
        Date sonuc = null;

        try {
            sonuc = getTarihBicimlendirici().parse(tarih);
        } catch (Exception e) {
        }

        return sonuc;
    }

    public static String cevirDate(Date tarih) {
        String sonuc = "";

        try {
            sonuc = getTarihBicimlendirici().format(tarih);
        } catch (Exception e) {
        }

        return sonuc;
    }

    private static DateFormat getTarihBicimlendirici() {
        if (tarihFormat == null) {
            tarihFormat = new SimpleDateFormat(TARIH_BICEM);
        }

        return tarihFormat;
    }

    //Tip Cevirici Metodlar
    //BigDecimal Ceviriciler
    public static BigDecimal cevirBigDecimal(String deger) {
        try {
            if ((deger == null) || deger.equals("")) {
                return null;
            }

            return new BigDecimal(getSayiBicim().parse(deger).doubleValue());
        } catch (Exception e) {
            return null;
        }
    }

    public static String cevirBigDecimal(BigDecimal deger) {
        try {
            if (deger == null) {
                return "";
            }

            return getSayiBicim().format(deger);
        } catch (Exception e) {
            e.printStackTrace();

            return "";
        }
    }

    public static String cevirString(String deger) {
        try {
            if (deger == null) {
                return "";
            }

            return deger;
        } catch (Exception e) {
            return "";
        }
    }

    public static DecimalFormat getSayiBicim() {
        return getSayiBicim(5);
    }

    public static DecimalFormat getSayiBicim(int noktadanSonra) {
        if (sayiBicim == null) {
            //Sistemimiz simdilik turkceyi destekliyor
            sayiBicim = (DecimalFormat) DecimalFormat.getInstance(yerel());
            sayiBicim.setMinimumFractionDigits(0);
            sayiBicim.setMaximumFractionDigits(noktadanSonra);
        }

        return sayiBicim;
    }

    private static Locale yerel() {
        return new Locale("TR", "tr");
    }
}
