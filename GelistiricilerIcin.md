# Kapsam #

Geliştiriciler için notlar


# Notlar #

**IReport'ta sql sorgularını yazarken JPA'nın yarattığı kolon isimlerinin sınıf özelliklerine bağımlı, standart dışı isimlendirmeler olduğunu (kendi standardında), ve kodda değişecek olan herhangi bir sınıf özelliğinin raporları da doğrudan etkileyeceğini gördük. Ayrı bir "jdbc:derby" bağlantısı kurup doğrudan derby veritabanına bağlanmak sonraki süreci zorlaştıracaktı. Bu nedenle IReport kullanırken veritabanı bağlantısı olarak kumanifest için kullandığımız "Persistence" yapısına sadık kalmayı tercih ettik.**


---
