package net.kodveus.kumanifest.db;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class PersistenceXMLReader {
	
	public static void main(String[] args) {
		PersistenceXMLReader.readParameters();
	}

	public static DBParameters readParameters() {
		try {
			//Ilk once DocumentBuilder nesnesini yaratiyoruz
			DocumentBuilder builder = DocumentBuilderFactory.newInstance()
					.newDocumentBuilder();
			//Indirecegimiz xml belgesinin URL'si
			//Burada yerel bir adreste kullanabilirsiniz
			File file = new File("META-INF/persistence.xml");

			//Belgeyi URL'den yukleyip, builder yardimiyla parse ediyoruz
			//Butun belge bellege alinmis oldu
			Document doc = builder.parse(file);

			//Belge icerisindeki Currency etiketli elemanlari aliyoruz
			NodeList nodes = doc.getElementsByTagName("property");
			//Bu elemanlari dolasiyoruz
			DBParameters dbp = new DBParameters();
			for (int i = 0; i < nodes.getLength(); i++) {
				Element element = (Element) nodes.item(i);
				String name = element.getAttribute("name");
				if(name.equals("toplink.jdbc.url")){
					dbp.dbURL = element.getAttribute("value");
				}
				if(name.equals("toplink.jdbc.user")){
					dbp.dbUser = element.getAttribute("value");
				}
				if(name.equals("toplink.jdbc.password")){
					dbp.dbPassword = element.getAttribute("value");
				}
				if(name.equals("toplink.jdbc.driver")){
					dbp.dbDriver = element.getAttribute("value");
				}
			}
			return dbp;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
