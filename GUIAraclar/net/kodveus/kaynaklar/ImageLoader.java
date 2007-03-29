package net.kodveus.kaynaklar;

import java.net.URL;

import javax.swing.Icon;
import javax.swing.ImageIcon;

public class ImageLoader {
	
    private static final String resimKonum = "net/kodveus/kaynaklar/resimler/";
    
    public static Icon getImageIcon(String resimAdi) {
		URL image = ClassLoader.getSystemClassLoader().getResource(resimKonum+resimAdi);
		Icon calendarIcon = new ImageIcon(image);
		return calendarIcon;
	}
}
