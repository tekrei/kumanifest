Kumanifest Manifesto Programı

Hakkında
======================================
kumanifest programı, [KodVeUs](http://kodveus.blogspot.com)'un geliştirmekte olduğu, özellikle konteyner taşımacılığı ile uğraşan firmaların ihtiyaç duyduğu; yükleme girişlerinin yapılabilmesi, manifesto, yükleme ve boşaltma listeleri, konşimento gibi raporların hazırlanabilmesi ve gümrük tescil işlemlerinin EDI dosyaları ile yapılabilmesini sağlayan  bir projedir.

kumanifest'in ilk sürümüne www.kodveus.com/kumanifest adresinden ulaşabilirsiniz. Programı indirebilir, kurulum yönergelerini uygulayarak kurabilir ve ücretsiz olarak kullanabilirsiniz. Kumanifest, Genel Kamu Lisansı(GPL) na sahiptir; bu kapsamda ücretsiz olarak kullanabilir, paylaşabilir hatta değiştirebilirsiniz. Lisansın Türkçesi, İngilizcesi ve programın kurulum yönergeleri indireceğiniz kurulum paketi içerisinde ve projenin sayfasında bulunmaktadır: [http://kodveus.blogcu.com](http://kodveus.blogcu.com)

Kumanifest konşimento, konteyner, mal tanimlari bilgilerinizi girebilmenizi kolaylaştıracağı gibi, bilgilerinizin bilgisayar ortamında tutulmasını, korunmasını ve yedeklenebilmesini sağlayacaktır. Kumanifest halen geliştirilmekte olan bir programdır ve sizlerden gelecek isteklerin geliştirme takımımız tarafından değerlendirilmesi ve eklenmesi sonucu ilerde çıkacak olan sürümler yeni özelliklere sahip olacaktır. Bir sonraki sürümde yapılması planlanan özellikler arasında:
 1. Gümrük tescil işlemlerinizi kolaylaştırmak için EDI, yani elektronik veri transferi özelliğinin eklenmesi
 2. OpenOffice ve Excel gibi uygulamalardan veri okuyabilme ve aktarma özelliğinin eklenmesi.

kumanifest programı maddi bir beklenti ile geliştirilmedi. Tamamen [özgür yazılım felsefesi](http://kodveus.blogspot.com/2006/09/zgr-yazlm-felsefesi.html#links) gözetilerek ve faydalı bir yazılım olması amaçlanarak geliştirildi. Tasarım ve geliştirme aşamasında yine kendisi gibi özgür yazılımlar kullanıldı.

Programın kurulumu ve kullanımı sırasında karşılaşacağınız tüm sorunları kodveus@gmail.com eposta adresine yazabilirsiniz.

Kodveus'un bilgi paylaşım günlüğüne [http://kodveus.blogspot.com](http://kodveus.blogspot.com) adresinden ulaşabilirsiniz.


[KodVeUs](http://kodveus.blogspot.com)

Biz Kimiz
=========

Biz Ege Üniversitesi Bilgisayar Mühendisliğinde tanışan aynı şehirden çıkan iki kişiyiz. Lisans bittikten sonra biraz daha okumaya karar verdik ve Yüksek Lisansı aynı bölümde yaptık. Kafamızda sürekli bazı fikirler oluşuyordu, özgür yazılıma iyice merak sarmıştık.

3. Sınıfta başlayan staj proje arkadaşlığı daha büyük projeler yapmak üzere evrimleşti. IBM Linux projesine katıldık ama bitiremedik. Daha sonra Yüksek lisans sürecinde kafamızdaki paylaşmak istediklerimizi paylaşmak için bir site kurduk. Ayrıca kafamızdaki bazı projeleri özgür yazılım olarak üretmeye karar verdik. Bu amaçla bir kaç yazılıma başladık. Yoğun geçen yaşamlarımızın bize verdiği az zamanda ortak bir şeyler yapmaya çalışırken sayımız arttı azaldı ama sürekli bir şeyler ürettik. Üretmeye de devam ediyoruz. İlk özgür yazılım projemizi internet üzerinden dağıtabildiğimiz zaman biz bütün emeklerimizin gerçekten bir işe yaradığını görmüş olacağız. Her ne kadar sitemiz sayesinde bu emeklerin boşa gitmediğini görsek de biz de bir şeyler başarıyoruz diyebilmemiz için bir projemizi internetten indirilebilir halde yayınlamaya ihtiyacımız var. 

Kapsam

Geliştiriciler için notlar
==========================

* Kumanifest projesindeki MySQL kurma ve kullanma zorluğundan son kullanıcıyı kurtarmak amacıyla yoğun, yorucu ve yıpratıcı bir sürece girdik. Bu süreç henüz tam olarak sonuçlanmadı. Kumanifest içerisinde kalıcı katman olarak JPA (TopLink) ve veritabanı olarak gömülü Derby veritabanı kullanımını bir kaç aydır etkinleştirmeye çalışıyoruz. Raporlar konusunda yaşadığımız sıkıntıları aşar aşmaz ilk sürümümüzü (1.0) sunacağız. 

* IReport'ta SQL sorgularını yazarken JPA'nın yarattığı sütun isimlerinin sınıf özelliklerine bağımlı, standart dışı isimlendirmeler olduğunu (kendi standardında), ve kodda değişecek olan herhangi bir sınıf özelliğinin raporları da doğrudan etkileyeceğini gördük. Ayrı bir "jdbc:derby" bağlantısı kurup doğrudan derby veritabanına bağlanmak sonraki süreci zorlaştıracaktı. Bu nedenle IReport kullanırken veritabanı bağlantısı olarak kumanifest için kullandığımız "Persistence" yapısına sadık kalmayı tercih ettik.

[YourKit](https://www.yourkit.com/) Java Profiler
=================================================

Kumanifest projesinin çalışma zamanındaki başarımını ölçmek, hafıza kullanımını incelemek için başarılı bir araç olan "[YourKit](https://www.yourkit.com/) Java Profiler"'ı kullandık. Bu kullanım sonucunda uygulamamızdaki hafıza taşmalarını, yoğun ve gereksiz işlemci kullanımlarını tespit ederek gerekli düzenlemeleri gerçekleştirerek daha başarılı bir uygulama haline getirebildik. Uygulamalarını bu açılardan değerlendirmek isteyen herkese bu aracı öneriyoruz.

YourKit is kindly supporting open source projects with its full-featured Java Profiler. [YourKit, LLC](https://www.yourkit.com/) is creator of innovative and intelligent tools for profiling Java and .NET applications. Take a look at [YourKit](https://www.yourkit.com/)'s leading software products: [YourKit Java Profiler](https://www.yourkit.com/java/profiler/) and [YourKit .NET Profiler](https://www.yourkit.com/dotnet/features/). 
