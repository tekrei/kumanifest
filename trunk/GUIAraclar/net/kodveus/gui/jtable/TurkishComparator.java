/*
 * Emre tarafindan Jun 10, 2005 tarihinde yaratilmistir.
 */
package net.kodveus.gui.jtable;

public class TurkishComparator {
    public static String getTurkceString(String str) {
        String newStr = str.substring(0, str.length());

        return yerDegistir(newStr);
    }

    private static String yerDegistir(String str) {
        str = str.replaceAll("?", "Iz");
        str = str.replaceAll("?", "iz");
        str = str.replaceAll("�", "Oz");
        str = str.replaceAll("�", "oz");
        str = str.replaceAll("�", "cz");
        str = str.replaceAll("�", "Cz");
        str = str.replaceAll("?", "Gz");
        str = str.replaceAll("?", "gz");
        str = str.replaceAll("�", "uz");
        str = str.replaceAll("�", "Uz");
        str = str.replaceAll("?", "sz");
        str = str.replaceAll("?", "Sz");
        str = str.toLowerCase();

        //str = str.replaceAll("","");
        return str;
    }
}
