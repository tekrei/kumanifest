# MySQL Navigator Xport
# Database: kumanifest
# root@localhost

# CREATE DATABASE kumanifest;
# USE kumanifest;

#
# Table structure for table 'bl'
#

# DROP TABLE IF EXISTS bl;
CREATE TABLE `bl` (
  `blid` bigint(20) NOT NULL auto_increment,
  `voyageid` bigint(20) default NULL,
  `blno` text,
  `companyname` text,
  `status` bigint(20) default NULL,
  `reporttype` bigint(20) default NULL,
  `placeoforigin` bigint(20) default NULL,
  `placeofreceipt` bigint(20) default NULL,
  `portofloading` bigint(20) default NULL,
  `portofdischarge` bigint(20) default NULL,
  `finaldischargeplace` bigint(20) default NULL,
  `finaldestination` bigint(20) default NULL,
  `description` text,
  `description2` text,
  `shipper` text,
  `consignee` text,
  `notify` text,
  `notify2` text,
  `issueplace` bigint(20) default '0',
  `issuedate` date default NULL,
  PRIMARY KEY  (`blid`),
  KEY `voyageid` (`voyageid`),
  KEY `placeoforigin` (`placeoforigin`),
  KEY `placeofreceipt` (`placeofreceipt`),
  KEY `portofloading` (`portofloading`),
  KEY `portofdischarge` (`portofdischarge`),
  KEY `finaldischargeplace` (`finaldischargeplace`),
  KEY `finaldestination` (`finaldestination`),
  CONSTRAINT `fk_bl_1` FOREIGN KEY (`voyageid`) REFERENCES `voyage` (`voyageid`),
  CONSTRAINT `fk_bl_location_1` FOREIGN KEY (`placeoforigin`) REFERENCES `location` (`locationid`),
  CONSTRAINT `fk_bl_location_2` FOREIGN KEY (`placeofreceipt`) REFERENCES `location` (`locationid`),
  CONSTRAINT `fk_bl_location_3` FOREIGN KEY (`portofloading`) REFERENCES `location` (`locationid`),
  CONSTRAINT `fk_bl_location_4` FOREIGN KEY (`portofdischarge`) REFERENCES `location` (`locationid`),
  CONSTRAINT `fk_bl_location_5` FOREIGN KEY (`finaldischargeplace`) REFERENCES `location` (`locationid`),
  CONSTRAINT `fk_bl_location_6` FOREIGN KEY (`finaldestination`) REFERENCES `location` (`locationid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

#
# Dumping data for table 'bl'
#

INSERT INTO bl VALUES (1,1,'550123456','msl',1,1,1,1,1,1,1,1,'mal tanim denemesi','yok2','dneme','deneme','ad','sdasd',1,NULL);
INSERT INTO bl VALUES (2,1,'123456789','scl',1,1,2,1,1,2,3,3,'tanim deneme',NULL,'gonderen','alici','ilgili',NULL,2,NULL);
INSERT INTO bl VALUES (3,1,'550123451','msl',NULL,NULL,1,1,1,1,1,1,'mal tanim denemesi','yok2','dneme','deneme','ad','sdasd',0,NULL);
INSERT INTO bl VALUES (4,1,'550123452','msl',NULL,NULL,1,1,1,1,1,1,'mal tanim denemesi','yok2','dneme','deneme','ad','sdasd',0,NULL);
INSERT INTO bl VALUES (5,1,'550123453','msl',NULL,NULL,2,1,1,3,1,1,'mal tanim denemesi','yok2','dneme','deneme','ad','sdasd',0,NULL);
INSERT INTO bl VALUES (6,1,'550123454','msl',NULL,NULL,2,1,3,3,3,3,'mal tanim denemesi','yok2','dneme','deneme','ad','sdasd',0,NULL);
INSERT INTO bl VALUES (7,1,'550123455','msl',NULL,NULL,2,1,3,3,3,3,'mal tanim denemesi','yok2','dneme','deneme','ad','sdasd',0,NULL);
INSERT INTO bl VALUES (8,2,'2545325453','FGSDFG DFGD',NULL,NULL,1,1,1,1,1,1,'GSDFG','SDDF','SDF','DFGS','DSF','SDF',0,NULL);

#
# Table structure for table 'cargo'
#

# DROP TABLE IF EXISTS cargo;
CREATE TABLE `cargo` (
  `cargoid` bigint(20) NOT NULL auto_increment,
  `containerid` bigint(20) NOT NULL default '0',
  `commodityid` bigint(20) NOT NULL default '0',
  `cargotype` text,
  `packid` bigint(20) default NULL,
  `imco` text,
  `unno` text,
  `netweight` double default '0',
  `packtotal` double default NULL,
  PRIMARY KEY  (`cargoid`),
  KEY `packid` (`packid`),
  KEY `containerid` (`containerid`),
  KEY `commodityid` (`commodityid`),
  CONSTRAINT `fk_cargo_2` FOREIGN KEY (`packid`) REFERENCES `pack` (`packid`),
  CONSTRAINT `fk_cargo_3` FOREIGN KEY (`containerid`) REFERENCES `container` (`containerid`),
  CONSTRAINT `fk_cargo_4` FOREIGN KEY (`commodityid`) REFERENCES `commodity` (`commodityid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

#
# Dumping data for table 'cargo'
#

INSERT INTO cargo VALUES (1,1,1,'PCS',1,'8','6',2334,120);
INSERT INTO cargo VALUES (2,2,1,'PIECES',2,'7','5',5600,230);
INSERT INTO cargo VALUES (3,3,1,'box',2,NULL,NULL,1200,20);

#
# Table structure for table 'commodity'
#

# DROP TABLE IF EXISTS commodity;
CREATE TABLE `commodity` (
  `commodityid` bigint(20) NOT NULL auto_increment,
  `code` text,
  `name` text,
  `description` text,
  PRIMARY KEY  (`commodityid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

#
# Dumping data for table 'commodity'
#

INSERT INTO commodity VALUES (1,'CIGARRETTE','CIGARRETTE','CIGARRETTE');
INSERT INTO commodity VALUES (2,'OLIVE','OLIVE','OLIVE');

#
# Table structure for table 'container'
#

# DROP TABLE IF EXISTS container;
CREATE TABLE `container` (
  `containerid` bigint(20) NOT NULL auto_increment,
  `containerno` text,
  `blid` bigint(20) default NULL,
  `containertype` bigint(20) default NULL,
  `sealno` text,
  `othersealno` text,
  `containersize` bigint(20) default NULL,
  `taraweight` double default NULL,
  `status` bigint(20) default NULL,
  `relcom` bigint(20) default NULL,
  PRIMARY KEY  (`containerid`),
  KEY `blid` (`blid`),
  KEY `containertype` (`containertype`),
  KEY `containersize` (`containersize`),
  CONSTRAINT `fk_container_2` FOREIGN KEY (`blid`) REFERENCES `bl` (`blid`),
  CONSTRAINT `fk_container_3` FOREIGN KEY (`containersize`) REFERENCES `containersize` (`containersizeid`),
  CONSTRAINT `fk_container_containertype` FOREIGN KEY (`containertype`) REFERENCES `containertype` (`containertypeid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

#
# Dumping data for table 'container'
#

INSERT INTO container VALUES (1,'msku1234567',1,1,'sealno','osealnosedf',1,1234,1,NULL);
INSERT INTO container VALUES (2,'abcd12345',1,1,'dfs','dsfsa',2,1234,1,NULL);
INSERT INTO container VALUES (3,'msku1222345',2,1,'uy?u','yut?y',1,3456,1,NULL);
INSERT INTO container VALUES (4,'msku1234500',1,1,'sealno','sealno344',1,1234,NULL,0);
INSERT INTO container VALUES (5,'',NULL,1,'','null',1,NULL,NULL,NULL);
INSERT INTO container VALUES (6,'',NULL,1,'','',1,NULL,NULL,NULL);

#
# Table structure for table 'containersize'
#

# DROP TABLE IF EXISTS containersize;
CREATE TABLE `containersize` (
  `containersizeid` bigint(20) NOT NULL auto_increment,
  `size` varchar(20) NOT NULL,
  `description` varchar(100) default NULL,
  PRIMARY KEY  (`containersizeid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

#
# Dumping data for table 'containersize'
#

INSERT INTO containersize VALUES (1,'20','20LIK');
INSERT INTO containersize VALUES (2,'40','40LIK');
INSERT INTO containersize VALUES (3,'45','45LIK');

#
# Table structure for table 'containertype'
#

# DROP TABLE IF EXISTS containertype;
CREATE TABLE `containertype` (
  `containertypeid` bigint(20) NOT NULL auto_increment,
  `type` varchar(20) NOT NULL,
  `description` varchar(100) default NULL,
  PRIMARY KEY  (`containertypeid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

#
# Dumping data for table 'containertype'
#

INSERT INTO containertype VALUES (1,'DRY','DRY');
INSERT INTO containertype VALUES (2,'VENT','VENT');

#
# Table structure for table 'country'
#

# DROP TABLE IF EXISTS country;
CREATE TABLE `country` (
  `countryid` bigint(20) NOT NULL auto_increment,
  `code` varchar(5) NOT NULL,
  `name` varchar(100) NOT NULL,
  PRIMARY KEY  (`countryid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

#
# Dumping data for table 'country'
#

INSERT INTO country VALUES (1,'TR','TURKEY');
INSERT INTO country VALUES (2,'UK','UNITED KINGDOM');
INSERT INTO country VALUES (3,'US','UNITED STATES');

#
# Table structure for table 'location'
#

# DROP TABLE IF EXISTS location;
CREATE TABLE `location` (
  `locationid` bigint(20) NOT NULL auto_increment,
  `code` text,
  `location` text,
  `isport` char(1) default NULL,
  PRIMARY KEY  (`locationid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

#
# Dumping data for table 'location'
#

INSERT INTO location VALUES (1,'IZM','IZMIR','Y');
INSERT INTO location VALUES (2,'BUR','BURSA','Y');
INSERT INTO location VALUES (3,'MER','MERSIN','Y');
INSERT INTO location VALUES (4,'MNS','MANISA','N');

#
# Table structure for table 'office'
#

# DROP TABLE IF EXISTS office;
CREATE TABLE `office` (
  `officeid` bigint(20) NOT NULL auto_increment,
  `office` text,
  `description` text,
  `code` text,
  PRIMARY KEY  (`officeid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

#
# Dumping data for table 'office'
#

INSERT INTO office VALUES (1,'IZMIR','IZMIR','TRIZM');
INSERT INTO office VALUES (2,'ISTANBUL','ISTANBUL','TRIST');
INSERT INTO office VALUES (3,'BURSA','BURSA','TRBUR');

#
# Table structure for table 'pack'
#

# DROP TABLE IF EXISTS pack;
CREATE TABLE `pack` (
  `packid` bigint(20) NOT NULL auto_increment,
  `type` text,
  `description` text,
  PRIMARY KEY  (`packid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

#
# Dumping data for table 'pack'
#

INSERT INTO pack VALUES (1,'PACKS','PACKS');
INSERT INTO pack VALUES (2,'BOX','BOX');

#
# Table structure for table 'vessel'
#

# DROP TABLE IF EXISTS vessel;
CREATE TABLE `vessel` (
  `vesselid` bigint(20) NOT NULL auto_increment,
  `vesselcode` text,
  `vesselname` text,
  `flag` bigint(20) default NULL,
  `status` bigint(20) default NULL,
  `company` text,
  `port` bigint(20) default NULL,
  PRIMARY KEY  (`vesselid`),
  KEY `port` (`port`),
  KEY `flag` (`flag`),
  CONSTRAINT `fk_vessel_country` FOREIGN KEY (`flag`) REFERENCES `country` (`countryid`),
  CONSTRAINT `fk_vessel_location` FOREIGN KEY (`port`) REFERENCES `location` (`locationid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

#
# Dumping data for table 'vessel'
#

INSERT INTO vessel VALUES (1,'19L','MAERSK BAHRAIN',3,NULL,'ML',2);
INSERT INTO vessel VALUES (2,'20L','MAERSK ANKARA',1,NULL,'ML',1);

#
# Table structure for table 'voyage'
#

# DROP TABLE IF EXISTS voyage;
CREATE TABLE `voyage` (
  `voyageid` bigint(20) NOT NULL auto_increment,
  `voyage` text,
  `export` bigint(20) default NULL,
  `vesselid` bigint(20) default NULL,
  `officeid` bigint(20) default NULL,
  `status` bigint(20) default NULL,
  `firstleavedport` bigint(20) default NULL,
  `lastleavedport` bigint(20) default NULL,
  `arrivaldate` date default NULL,
  `departuredate` date default NULL,
  `nameofcaptain` text,
  PRIMARY KEY  (`voyageid`),
  KEY `vesselid` (`vesselid`),
  KEY `firstleavedport` (`firstleavedport`),
  KEY `lastleavedport` (`lastleavedport`),
  KEY `officeid` (`officeid`),
  CONSTRAINT `fk_voyage_1` FOREIGN KEY (`vesselid`) REFERENCES `vessel` (`vesselid`),
  CONSTRAINT `fk_voyage_location_1` FOREIGN KEY (`firstleavedport`) REFERENCES `location` (`locationid`),
  CONSTRAINT `fk_voyage_location_2` FOREIGN KEY (`lastleavedport`) REFERENCES `location` (`locationid`),
  CONSTRAINT `fk_voyage_office` FOREIGN KEY (`officeid`) REFERENCES `office` (`officeid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

#
# Dumping data for table 'voyage'
#

INSERT INTO voyage VALUES (1,'0610',0,1,2,NULL,1,3,'2006-07-17','2006-07-20','MAHONI');
INSERT INTO voyage VALUES (2,'0623',1,2,1,NULL,1,3,'2006-07-17','2006-07-20','MAHO');
INSERT INTO voyage VALUES (3,'0511',0,2,1,NULL,1,1,'2007-03-06','2007-03-16','');

#
# Table structure for table 'vwboflading'
#

# DROP TABLE IF EXISTS vwboflading;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `vwboflading` AS select `bl`.`blid` AS `blid`,`bl`.`blno` AS `blno`,`bl`.`shipper` AS `shipper`,`bl`.`consignee` AS `consignee`,`bl`.`notify` AS `notify`,`bl`.`notify2` AS `notify2`,`bl`.`description` AS `description`,`bl`.`issuedate` AS `issuedate`,`voyage`.`voyageid` AS `voyageid`,`voyage`.`voyage` AS `voyage`,`vessel`.`vesselcode` AS `vesselcode`,`vessel`.`vesselname` AS `vesselname`,`location`.`location` AS `placeofreceipt`,`location_1`.`location` AS `portofdischarge`,`location_2`.`location` AS `portofloading`,`location_3`.`location` AS `finaldischargeplace`,`location_4`.`location` AS `issueplace` from (((((((`bl` join `voyage` on((`bl`.`voyageid` = `voyage`.`voyageid`))) join `vessel` on((`voyage`.`vesselid` = `vessel`.`vesselid`))) left join `location` `location_2` on((`bl`.`portofloading` = `location_2`.`locationid`))) left join `location` `location_3` on((`bl`.`finaldischargeplace` = `location_3`.`locationid`))) left join `location` `location_1` on((`bl`.`portofdischarge` = `location_1`.`locationid`))) left join `location` on((`bl`.`portofloading` = `location`.`locationid`))) left join `location` `location_4` on((`bl`.`issueplace` = `location_4`.`locationid`)));

#
# Dumping data for table 'vwboflading'
#

INSERT INTO vwboflading VALUES (1,'550123456','dneme','deneme','ad','sdasd','mal tanim denemesi',NULL,1,'0610','19L','MAERSK BAHRAIN','IZMIR','IZMIR','IZMIR','IZMIR','IZMIR');
INSERT INTO vwboflading VALUES (2,'123456789','gonderen','alici','ilgili',NULL,'tanim deneme',NULL,1,'0610','19L','MAERSK BAHRAIN','IZMIR','BURSA','IZMIR','MERSIN','BURSA');
INSERT INTO vwboflading VALUES (3,'550123451','dneme','deneme','ad','sdasd','mal tanim denemesi',NULL,1,'0610','19L','MAERSK BAHRAIN','IZMIR','IZMIR','IZMIR','IZMIR',NULL);
INSERT INTO vwboflading VALUES (4,'550123452','dneme','deneme','ad','sdasd','mal tanim denemesi',NULL,1,'0610','19L','MAERSK BAHRAIN','IZMIR','IZMIR','IZMIR','IZMIR',NULL);
INSERT INTO vwboflading VALUES (5,'550123453','dneme','deneme','ad','sdasd','mal tanim denemesi',NULL,1,'0610','19L','MAERSK BAHRAIN','IZMIR','MERSIN','IZMIR','IZMIR',NULL);
INSERT INTO vwboflading VALUES (6,'550123454','dneme','deneme','ad','sdasd','mal tanim denemesi',NULL,1,'0610','19L','MAERSK BAHRAIN','MERSIN','MERSIN','MERSIN','MERSIN',NULL);
INSERT INTO vwboflading VALUES (7,'550123455','dneme','deneme','ad','sdasd','mal tanim denemesi',NULL,1,'0610','19L','MAERSK BAHRAIN','MERSIN','MERSIN','MERSIN','MERSIN',NULL);
INSERT INTO vwboflading VALUES (8,'2545325453','SDF','DFGS','DSF','SDF','GSDFG',NULL,2,'0623','20L','MAERSK ANKARA','IZMIR','IZMIR','IZMIR','IZMIR',NULL);

#
# Table structure for table 'vwcargodetails'
#

# DROP TABLE IF EXISTS vwcargodetails;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `vwcargodetails` AS select `container`.`blid` AS `blid`,sum(`cargo`.`packtotal`) AS `packtotal`,count(`container`.`containerno`) AS `containercount`,`containersize`.`size` AS `size`,`pack`.`type` AS `type` from (`containersize` left join (`container` left join (`cargo` left join `pack` on((`cargo`.`packid` = `pack`.`packid`))) on((`container`.`containerid` = `cargo`.`containerid`))) on((`container`.`containersize` = `containersize`.`containersizeid`))) group by `container`.`containersize`;

#
# Dumping data for table 'vwcargodetails'
#

INSERT INTO vwcargodetails VALUES (NULL,NULL,0,'45',NULL);
INSERT INTO vwcargodetails VALUES (1,140,5,'20','PACKS');
INSERT INTO vwcargodetails VALUES (1,230,1,'40','BOX');

#
# Table structure for table 'vwcontainerlist'
#

# DROP TABLE IF EXISTS vwcontainerlist;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `vwcontainerlist` AS select `container`.`blid` AS `blid`,`container`.`containerno` AS `containerno`,`containertype`.`type` AS `type`,`containersize`.`size` AS `size`,`container`.`sealno` AS `sealno` from ((`container` left join `containertype` on((`container`.`containertype` = `containertype`.`containertypeid`))) left join `containersize` on((`container`.`containersize` = `containersize`.`containersizeid`)));

#
# Dumping data for table 'vwcontainerlist'
#

INSERT INTO vwcontainerlist VALUES (1,'msku1234567','DRY','20','sealno');
INSERT INTO vwcontainerlist VALUES (1,'abcd12345','DRY','40','dfs');
INSERT INTO vwcontainerlist VALUES (2,'msku1222345','DRY','20','uy?u');
INSERT INTO vwcontainerlist VALUES (1,'msku1234500','DRY','20','sealno');
INSERT INTO vwcontainerlist VALUES (NULL,'','DRY','20','');
INSERT INTO vwcontainerlist VALUES (NULL,'','DRY','20','');

