#!/bin/bash

#Ilk olarak veritabanini yaratmamiz gerekiyor
echo -n "Database server address > "
read sunucu
echo -n "Database server user name > "
read kullanici
echo -n "Database name > "
read vt
mysql -h $sunucu -u $kullanici -p $vt < kumanifest.sql

