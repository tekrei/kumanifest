## Kurulum Aşamaları ##
  1. [Mysql](http://dev.mysql.com/downloads/mysql/5.0.html#downloads) (>4.0) kurmanız gerekmektedir ([Yardım](http://dev.mysql.com/doc/refman/5.0/en/installing-cs.html)).
  1. [Java Çalışma Ortamı](http://www.java.com/en/download/manual.jsp) (>1.5) kurmanız gerekmektedir.
  1. Mysql kurduktan sonra bir veritabanı yaratmanız veya var olan bir veritabanı kullanmanız gerekmektedir.
    1. seçenek [phpMyAdmin](http://www.phpmyadmin.net/) veya [Mysql Navigator](http://sourceforge.net/projects/mysqlnavigator/) tarzı Mysql veritabanı yönetim programları kullanarak yaratabilirsiniz.
    1. seçenek komut satırından mysql aracını kullanarak yapabilirsiniz İlk önce root ile giriş yapmanız gerekiyor `komut satiri>mysql -u root -p` Daha sonra kumanifest isimli veritabanını yaratıyoruz `mysql> CREATE DATABASE kumanifest;` Bu veritabanını kullanarak `mysql> USE kumanifest;` Kendi kullanıcımıza hakları veriyoruz `mysql> GRANT ALL ON kumanifest.* TO 'kullanici_adiniz'@'istemci_adresiniz';`
    1. seçenek olarak root hakkı olmaması durumunda veritabanı yöneticisinden veritabanını yaratmasını istemelisiniz.
  1. Veritabanı yapısının oluşturulması gerekmektedir.
    1. Kurulum işlemi bittikten sonra kurulum dizini içerisindeki "dbkurulum" betik dosyasını çalıştırınız. Bu dosya veritabanı yapısını oluşturacaktır.
    1. Komut satırından `mysql -h sunucu -u kullanici -p veritabani < kumanifest.sql` komutuyla da yapılabilir.
    1. Mysql yönetim programlarında kumanifest.sql dosyası betik olarak çalıştırılabilir. ([phpMyAdmin](http://www.phpmyadmin.net/), [Mysql Navigator](http://sourceforge.net/projects/mysqlnavigator/) gibi)
  1. Veritabanı yapısı da hatasız kurulursa programı çalıştırabilirsiniz.
    1. Kurulum dizini içerisindeki `calistir` betik dosyası çalıştırılabilir.
    1. `java -classpath .:kumanifest.jar:./lib/commons-beanutils.jar:./lib/commons-collections.jar:./lib/commons-digester.jar:./lib/commons-javaflow.jar:./lib/commons-logging.jar:./lib/GUIAraclar.jar:./lib/itext.jar:./lib/jasperreports.jar:./lib/jr-bsh-compiler.jar:./lib/mysql.jar:./lib/poi.jar net.kodveus.kumanifest.AnaPencere` komutu çalıştırılabilir. (Windows ortamında : yerine ; kullanılmalıdır.)