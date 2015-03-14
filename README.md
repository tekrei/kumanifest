Kumanifest Manifesto Programı
=============================

Kumanifest nedir?
-----------------

Belki de en kritik soru şu an bu. Nedir bu kumanifest? Ne işe yarar? Nereden çıkmıştır? Bu sayfada elden geldiğince bu sorulara cevap arayacağız.

Kumanifest programı, Kodveus' un geliştirmekte olduğu; özellikle konteyner taşımacılığı ile uğraşan firmaların ihtiyaç duyduğu; yükleme girişlerinin yapılabilmesi, manifesto, yükleme ve boşaltma listeleri, konşimento gibi raporların hazırlanabilmesi ve gümrük tescil işlemlerinin EDI dosyaları ile yapılabilmesini sağlayan bir projedir.

Kumanifest; konşimento, konteyner, mal tanimlari bilgilerinizi girebilmenizi kolaylaştıracağı gibi, bilgilerinizin bilgisayar ortamında tutulmasını, korunmasını ve yedeklenebilmesini sağlayacaktır.

Kumanifest halen geliştirilmekte olan bir programdır ve sizlerden gelecek isteklerin geliştirme takımımız tarafından değerlendirilmesi ve eklenmesi sonucu ilerde çıkacak olan sürümler yeni özelliklere sahip olacaktır. 

Hakkında
--------
kumanifest programı, [KodVeUs](http://kodveus.blogspot.com)'un geliştirmekte olduğu, özellikle konteyner taşımacılığı ile uğraşan firmaların ihtiyaç duyduğu; yükleme girişlerinin yapılabilmesi, manifesto, yükleme ve boşaltma listeleri, konşimento gibi raporların hazırlanabilmesi ve gümrük tescil işlemlerinin EDI dosyaları ile yapılabilmesini sağlayan  bir projedir.

kumanifest'in ilk sürümüne www.kodveus.com/kumanifest adresinden ulaşabilirsiniz. Programı indirebilir, kurulum yönergelerini uygulayarak kurabilir ve ücretsiz olarak kullanabilirsiniz. Kumanifest, Genel Kamu Lisansı(GPL) na sahiptir; bu kapsamda ücretsiz olarak kullanabilir, paylaşabilir hatta değiştirebilirsiniz. Lisansın Türkçesi, İngilizcesi ve programın kurulum yönergeleri indireceğiniz kurulum paketi içerisinde ve projenin sayfasında bulunmaktadır: [http://kodveus.blogcu.com](http://kodveus.blogcu.com)

Kumanifest konşimento, konteyner, mal tanimlari bilgilerinizi girebilmenizi kolaylaştıracağı gibi, bilgilerinizin bilgisayar ortamında tutulmasını, korunmasını ve yedeklenebilmesini sağlayacaktır. Kumanifest halen geliştirilmekte olan bir programdır ve sizlerden gelecek isteklerin geliştirme takımımız tarafından değerlendirilmesi ve eklenmesi sonucu ilerde çıkacak olan sürümler yeni özelliklere sahip olacaktır. Bir sonraki sürümde yapılması planlanan özellikler arasında:
 1. Gümrük tescil işlemlerinizi kolaylaştırmak için EDI, yani elektronik veri transferi özelliğinin eklenmesi
 2. OpenOffice ve Excel gibi uygulamalardan veri okuyabilme ve aktarma özelliğinin eklenmesi.

kumanifest programı maddi bir beklenti ile geliştirilmedi. Tamamen [özgür yazılım felsefesi](http://kodveus.blogspot.com/2006/09/zgr-yazlm-felsefesi.html#links) gözetilerek ve faydalı bir yazılım olması amaçlanarak geliştirildi. Tasarım ve geliştirme aşamasında yine kendisi gibi özgür yazılımlar kullanıldı.

Programın kurulumu ve kullanımı sırasında karşılaşacağınız tüm sorunları kodveus@gmail.com eposta adresine yazabilirsiniz.

Kodveus'un bilgi paylaşım günlüğüne [http://kodveus.blogspot.com](http://kodveus.blogspot.com) adresinden ulaşabilirsiniz.

Kurulum Kılavuzu
----------------

Kumanifest programı java programlama dili ile geliştirilmiş olup, veritabanı olarak da mySQL kullanmaktadır. Bu nedenle, Kumanifest'in çalışabilmesi için kullandığınız bilgisayarda, java uygulamalarını çalıştırmak için gerekli olan Java çalışma ortamının(JRE) ve mySQL veritabanının kurulu olması gerekmektedir. Bu gereksinimlerin nasıl kurulacağı aşağıda anlatılmaktadır. 

Kurulum Aşamaları

1. [Mysql](https://dev.mysql.com/downloads/) (>4.0) kurmanız gerekmektedir ([Yardım](https://dev.mysql.com/doc/refman/5.0/en/installing.html)).
2. [Java Çalışma Ortamı](https://www.java.com/en/download/manual.jsp) (>1.5) kurmanız gerekmektedir.
3. Mysql kurduktan sonra bir veritabanı yaratmanız veya var olan bir veritabanı kullanmanız gerekmektedir.
 1. seçenek [phpMyAdmin](http://www.phpmyadmin.net/) veya [Mysql Navigator](http://sourceforge.net/projects/mysqlnavigator/) tarzı Mysql veritabanı yönetim programları kullanarak yaratabilirsiniz.
 2. seçenek komut satırından mysql aracını kullanarak yapabilirsiniz İlk önce root ile giriş yapmanız gerekiyor komut satiri>mysql -u root -p Daha sonra kumanifest isimli veritabanını yaratıyoruz mysql> CREATE DATABASE kumanifest; Bu veritabanını kullanarak mysql> USE kumanifest; Kendi kullanıcımıza hakları veriyoruz mysql> GRANT ALL ON kumanifest.* TO 'kullanici_adiniz'@'istemci_adresiniz';
 3. seçenek olarak root hakkı olmaması durumunda veritabanı yöneticisinden veritabanını yaratmasını istemelisiniz. 
4. Veritabanı yapısının oluşturulması gerekmektedir.
 1. Kurulum işlemi bittikten sonra kurulum dizini içerisindeki "dbkurulum" betik dosyasını çalıştırınız. Bu dosya veritabanı yapısını oluşturacaktır.
 2. Komut satırından mysql -h sunucu -u kullanici -p veritabani < kumanifest.sql komutuyla da yapılabilir.
 3. Mysql yönetim programlarında kumanifest.sql dosyası betik olarak çalıştırılabilir. ([phpMyAdmin](http://www.phpmyadmin.net/), [Mysql Navigator](http://sourceforge.net/projects/mysqlnavigator/) gibi) 
5. Veritabanı yapısı da hatasız kurulursa programı çalıştırabilirsiniz.
 1. Kurulum dizini içerisindeki calistir betik dosyası çalıştırılabilir.
 2. java -classpath .:kumanifest.jar:./lib/commons-beanutils.jar:./lib/commons-collections.jar:./lib/commons-digester.jar:./lib/commons-javaflow.jar:./lib/commons-logging.jar:./lib/GUIAraclar.jar:./lib/itext.jar:./lib/jasperreports.jar:./lib/jr-bsh-compiler.jar:./lib/mysql.jar:./lib/poi.jar net.kodveus.kumanifest.AnaPencere komutu çalıştırılabilir. (Windows ortamında : yerine ; kullanılmalıdır.)

Kullanıcı Kılavuzu
------------------

Kumanifest konşimento, konteyner, mal tanımları bilgilerinizi girebilmenizi kolaylaştıracağı gibi, bilgilerinizin bilgisayar ortamında tutulmasını, korunmasını ve yedeklenebilmesini sağlayacaktır. Yalnızca bir defa veri girişi yaparak, aynı bilgiyi farklı şekillerde çıktılara dönüştürebilecek, farklı amaçlar için kullanabilecek ve arşivleyebileceksiniz.

Kumanifest halen geliştirilmekte olan bir programdır ve sizlerden gelecek isteklerin geliştirme takımımız tarafından değerlendirilmesi ve eklenmesi sonucu ilerde çıkacak olan sürümler yeni özelliklere sahip olacaktır. Şu an elinize gelecek olan sürüm sadece temel ihtiyaçlara karşılık verebilir.

Bir sonraki sürümde yapılması planlanan özellikler şunlardır:

* Gümrük tescil işlemlerinizi kolaylaştırmak için EDI dosyalama, yani elektronik veri transferi dosyasının hazırlanması özelliğinin eklenmesi. Bu özellik sayesinde gümrük tescil işlemlerinizi internet üzerinden yapabilen programlara, tescil bilgilerini doğrudan aktarabilecek; bu sayede elle yapılan birçok işlemi hızlandırıp hatasız hale getirebileceksiniz ve tekrar tekrar aynı verilerin bilgisayar ortamına girilmesine gerek duymayacaksınız.
* Girilen kayıtlar arasında BL veya konteyner arama özelliği. Yaptığınız veri girişleri arasında aradığınız bir kaydı kumanifest sizin için bulup getirecek, hızlı bir şekilde erişmenizi sağlayacaktır.
* Open Office ve Excel gibi uygulamalardan veri okuyabilme ve aktarma özelliğinin eklenmesi. Excele belli bir düzende girdiğiniz gemi,BL veya konteyner bilgilerini otomatik olarak programın içine aktarabileceksiniz. Aynı şekilde program içerisinde bulunan verilerin de farklı düzen ve dosya tipleri ile dışa aktarımı mümkün olacaktır. 

[KumanifestInstaller_0.96.jar](https://github.com/tekrei/kumanifest/releases/download/0.96/KumanifestInstaller_0.96.jar) sürümünü kullanabilirsiniz. Programı indirebilir, kurulum yönergelerini uygulayarak kurabilir ve ücretsiz olarak kullanabilirsiniz.

Kumanifest, Genel Kamu Lisansı(GPL) na sahiptir; bu kapsamda ücretsiz olarak kullanabilir, paylaşabilir hatta değiştirebilirsiniz. Lisansın Türkçesi, İngilizcesi ve programın kurulum yönergeleri indireceğiniz kurulum paketi içerisinde ve projenin sayfasında bulunmaktadır: [http://kodveus.blogcu.com](http://kodveus.blogcu.com)

Kumanifest programı maddi bir beklenti ile geliştirilmedi. Tamamen özgür yazılım felsefesi gözetilerek ve faydalı bir yazılım olması amaçlanarak geliştirildi. Tasarım ve geliştirme aşamasında yine kendisi gibi özgür yazılımlar kullanıldı.

Programın kurulumu ve kullanımı sırasında karşılaşacağınız tüm sorunları [kodveus@gmail.com](mailto:kodveus@gmail.com) eposta adresine yazabilirsiniz. Kumanifest hakkında daha detaylı ve en güncel bilgiye [README](https://github.com/tekrei/kumanifest/raw/master/README.md) dosyasından ulaşabilirsiniz.

## Program Arayüzü

Kumanifest programı temel olarak 3 bölümden oluşmaktadır:

1. Ekranın sol tarafında bulunan ağaç görünümünden gemi ve seferlere ulaşılabilir. 
2. Sağ tarafta bulunan BL panelinde BL detayları görülebilir. 
3. Yine sağ tarafta BL paneli ile yanyana bulunan konteyner panelinde konteyner detayları görülebilecektir.

Ana ekranın sağ tarafında bulunan bu paneller, Bl, konteyner ve kargo kayıtlarının girişi ve düzenlenmesinde kullanılacak olan bölümlerdir. Kayıt girişleri ve düzenlenmesi yine ekranın sağ üst bölümünde bulunan tuşlar yardımı ile yapılır. Yapılan girişlerin çıktıları rapor olarak alınabilir.

Kumanifest açıldığında üst bölümde File, Reports, Additional v.b. seçenekleri olan ana menüyü göreceksiniz. Programda kullanılacak olan liman/ofis, gemi gibi değişmez bilgilerin girileceği yer ve çıktı olarak alınacak raporların görülebileceği yerlere ana menüdeki seçeneklerden ulaşılacaktır.

Kurulum tamamlandıktan sonra program kullanılırken ihtiyaç duyulacak liman,ofis,paket tipi v.b. değişmez verilerin tanımlanması gerekecektir.

Bu veri girişleri, ana ekranda bulunan üst menüdeki Tools seçeneği altındaki Parameters altseçeneğinde görülebilecek olan Pack, Office, Commodity gibi değerlerin teker teker girilmesi ile yapılır. Buradaki bazı girişler birbiri ile bağımlıdır; bu nedenle veri girişlerini yukarıdan aşağıya doğru, yani sırasıyla Pack, Office, Commodity, Container Size, Container Type ,... şeklinde yapmanız sorun yaşamanızı engelleyecektir.

Değişmez verileri tanımladıktan sonra konşimento(BL) girişlerinizi yapmaya başlayabilirsiniz. Ana ekranın sol tarafındaki ağaç görünümünde, ilgili yukleme bilgilerine fare ile çift tıklayarak tanımladığınız bir sefere ulaşabilirsiniz.

Ana ekran görüntüsünün üst bölümünde bulunan menüdeki seçeneklerin bir kısmı program ilk açıldığında etkin olmayacaktır. Bu seçeneklerin bir kısmı seferlere bir kısmı da konşimentolara ait özellikler olduğundan, ancak ilgili sefer veya konşimento seçildiğinde etkin hale gelecektir. Rapolar menüsü seçenekleri bunlara örnek olarak verilebilir.

Ekran görüntülerinden de anlaşılacağı gibi programın sol tarafında, gemi/sefer ve BL leri listeleyen bir ağaç görünümü bulunmaktadır. Bu ağaç görünümünde bulunan Liman/Ofis, gemi ve sefer bilgileri, sizin parametreler bölümü olan "Tools->Parameters" menüsündeki seçeneklere yaptığınız tanımlamalardan oluşturulur. Seferler altına bilgilerini gireceğiniz BL numaraları da yine bu ağaç görünümünde listelenecektir.

## BL kaydı girebilmek

Yeni bir BL kaydı girebilmek için ilgili sefer seçildikten sonra; BL bilgilerini sağ taraftaki panelde doldurduktan sonra ekranın sağ üst köşesinde bulunan +(artı) işareti şeklindeki ekle tuşunu kullanarak kaydın saklanmasını sağlayabilirsiniz. Bu işlem sırasıyla şöyle yapılır:

1. Sol taraftaki ağaç görünümünden BL'in ilgili seferi seçilir
2. Sağ tarafta bulunan BL paneli içerisinde bulunan veri alanlarına gerekli bilgiler girilir
3. Sağ üst köşede bulunan +(artı) işareti şeklindeki ekle tuşu kullanılarak kayıt saklanır. 

Buradan seçilen BL bilgileri sağdaki panele yüklenecek ve seçme işlemi ile birlikte bu bilgiler üzerinde değiştirme/silme gibi işlemler yapılabilecektir.

Seçilen BL'in konteyner detaylarını görebilmek için üst tarafta bulunan "Container" sekmesi seçilmelidir. Burada her bir konteyner içerisinde bulunan mal detayları da görülebilir ve değiştirilebilir.
Konteyner kaydı girebilmek

Tanımladığınız BL'lere ilgili oldukları konteynerleri bağlayabilmek için sağ taraftaki panelde bulunan "Container" sekmesini seçerek konteyner panelini açmanız gerekir. Yeni bir konteyner kaydı girebilmek için sırasıyla:

1. Sol taraftaki ağaç görünümünden ilgili BL seçilir
2. Sağ tarafta bulunan panellerden "Container" sekmesi seçilerek konteyner paneli açılır
3. Bu panelin üst bölümünde bulunan "Container details" veri grubu içerisindeki alanlara gerekli bilgiler girilir
4. Sağ üst köşede bulunan +(artı) işareti şeklindeki ekle tuşu kullanılarak kayıt saklanır. 

## Kargo detay kaydı girebilmek

Konteynerler içerisinde bulunan yük/kargo bilgileri de yine konteyner panelinde eklenebilir, görülebilir veya değiştirilebilir. Her bir konteynerin içerisindeki kargo bilgileri ayrı ayrı tanımlanmalıdır.

Girilen kargo detayları, konteyner paneli içerisindeki, konteynerlerin listelendiği tabloda ilgili konteynerin seçilmesi ile altta bulunan kargo detay tablosunda listelenecektir. Bu liste üzerinde ilgili kargo seçilerek tablonun altında bulunan "New Cargo", "Delete Cargo" ve "Update Cargo" tuşları ile gerekli düzenlemeler yapılabilir.

Yeni bir kargo detayı girebilmek için izlenecek yol şu şekildedir:

1. Sol taraftaki ağaç görünümünden ilgili BL seçilir
2. Sağ tarafta bulunan panellerden "Container" sekmesi seçilerek konteyner paneli açılır
3. Bu panelin orta bölümünde bulunan ve konteynerleri listeleyen tabloda ilgili konteyner seçilir.
4. Alt bölümde bulunan "New Cargo" tuşu kullanılarak yeni kargo tanımlama ekranı açılır.
5. "New Cargo" başlığına sahip bu pencerede gerekli alanlar doldurulur ve "OK" tuşuna basılarak yeni kayıt kaydedilmiş olunur. 

## Raporlar

Kumanifestin kolaylaştırdığı en önemli özellik raporlamalardır. Konşimento, manifesto ve yükleme listeleri gibi raporları menüdeki "Reports" seçeneği altında bulabilirsiniz. Girişi yapılan tüm veriler bu raporlarda düzenlenmiş ve özetlenmiş bir şekilde karşınıza çıkacaktır.

Her bir gemi/sefer için manifesto ve yükleme listesi hazırlayabilecek, konşimentolarınızın detaylarını gösteren "Bill Of Lading" raporu hazırlayabileceksiniz. Seçtiğiniz rapor görüntülendiğinde pencerenin sol üst bölümünde bulunan tuşlar ile yazıcı çıktısı alabilir yada raporu .pdf, .xls, .rtf, .html gibi dosya formatlarında saklayabilirsiniz. 

Biz Kimiz
---------

Biz Ege Üniversitesi Bilgisayar Mühendisliğinde tanışan aynı şehirden çıkan iki kişiyiz. Lisans bittikten sonra biraz daha okumaya karar verdik ve Yüksek Lisansı aynı bölümde yaptık. Kafamızda sürekli bazı fikirler oluşuyordu, özgür yazılıma iyice merak sarmıştık.

Lisans 3. sınıfta başlayan staj proje arkadaşlığı daha büyük projeler yapmak üzere evrimleşti. IBM Linux projesine katıldık ama bitiremedik. Daha sonra Yüksek lisans sürecinde kafamızdaki paylaşmak istediklerimizi paylaşmak için bir site kurduk. Ayrıca kafamızdaki bazı projeleri özgür yazılım olarak üretmeye karar verdik. Bu amaçla bir kaç yazılıma başladık. Yoğun geçen yaşamlarımızın bize verdiği az zamanda ortak bir şeyler yapmaya çalışırken sayımız arttı azaldı ama sürekli bir şeyler ürettik. Üretmeye de devam ediyoruz. İlk özgür yazılım projemizi internet üzerinden dağıtabildiğimiz zaman biz bütün emeklerimizin gerçekten bir işe yaradığını görmüş olacağız. Her ne kadar sitemiz sayesinde bu emeklerin boşa gitmediğini görsek de biz de bir şeyler başarıyoruz diyebilmemiz için bir projemizi internetten indirilebilir halde yayınlamaya ihtiyacımız var. 

Geliştiriciler için notlar
--------------------------

* Kumanifest projesindeki MySQL kurma ve kullanma zorluğundan son kullanıcıyı kurtarmak amacıyla yoğun, yorucu ve yıpratıcı bir sürece girdik. Bu süreç henüz tam olarak sonuçlanmadı. Kumanifest içerisinde kalıcı katman olarak JPA (TopLink) ve veritabanı olarak gömülü Derby veritabanı kullanımını bir kaç aydır etkinleştirmeye çalışıyoruz. Raporlar konusunda yaşadığımız sıkıntıları aşar aşmaz ilk sürümümüzü (1.0) sunacağız. 

* IReport'ta SQL sorgularını yazarken JPA'nın yarattığı sütun isimlerinin sınıf özelliklerine bağımlı, standart dışı isimlendirmeler olduğunu (kendi standardında), ve kodda değişecek olan herhangi bir sınıf özelliğinin raporları da doğrudan etkileyeceğini gördük. Ayrı bir "jdbc:derby" bağlantısı kurup doğrudan derby veritabanına bağlanmak sonraki süreci zorlaştıracaktı. Bu nedenle IReport kullanırken veritabanı bağlantısı olarak kumanifest için kullandığımız "Persistence" yapısına sadık kalmayı tercih ettik.

[YourKit](https://www.yourkit.com/) Java Profiler
-------------------------------------------------

Kumanifest projesinin çalışma zamanındaki başarımını ölçmek, hafıza kullanımını incelemek için başarılı bir araç olan "[YourKit](https://www.yourkit.com/) Java Profiler"'ı kullandık. Bu kullanım sonucunda uygulamamızdaki hafıza taşmalarını, yoğun ve gereksiz işlemci kullanımlarını tespit ederek gerekli düzenlemeleri gerçekleştirerek daha başarılı bir uygulama haline getirebildik. Uygulamalarını bu açılardan değerlendirmek isteyen herkese bu aracı öneriyoruz.

YourKit is kindly supporting open source projects with its full-featured Java Profiler. [YourKit, LLC](https://www.yourkit.com/) is creator of innovative and intelligent tools for profiling Java and .NET applications. Take a look at [YourKit](https://www.yourkit.com/)'s leading software products: [YourKit Java Profiler](https://www.yourkit.com/java/profiler/) and [YourKit .NET Profiler](https://www.yourkit.com/dotnet/features/). 
