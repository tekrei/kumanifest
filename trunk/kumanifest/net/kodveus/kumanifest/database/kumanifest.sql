--Dogru calismasi icin bunun icerigi kucuk harf olmali
--Eclipse icin AnyEdit eklentisi is goruyor :D
create table containersize (
       containersizeid bigint(20) not null auto_increment
     , size varchar(20) not null
     , description varchar(100)
     , primary key(containersizeid)
)type=innodb;

create table containertype (
       containertypeid bigint(20) not null auto_increment
     , type varchar(20) not null
     , description varchar(100)
     , primary key(containertypeid)
)type=innodb;

create table country (
       countryid bigint(20) not null auto_increment
     , code varchar(5) not null
     , name varchar(100) not null
     , primary key(countryid)
)type=innodb;

create table location (
       locationid bigint(20) not null auto_increment
     , code text
     , location text
     , isport char(1)
     , primary key(locationid)
)type=innodb;

create table office (
       officeid bigint(20) not null auto_increment
     , office text
     , description text
     , code text
     , primary key(officeid)
)type=innodb;

create table pack (
       packid bigint(20) not null auto_increment
     , type text
     , description text
     , primary key(packid)
)type=innodb;

create table commodity (
       commodityid bigint(20) not null auto_increment
     , code text
     , name text
     , description text
     , primary key(commodityid)
)type=innodb;

create table vessel (
       vesselid bigint(20) not null auto_increment
     , vesselcode text
     , vesselname text
     , flag bigint(20)
     , status bigint(20)
     , company text
     , port bigint(20)
     , primary key(vesselid)
)type=innodb;

create table voyage (
       voyageid bigint(20) not null auto_increment
     , voyage text
     , export bigint(20)
     , vesselid bigint(20)
     , officeid bigint(20)
     , status bigint(20)
     , firstleavedport bigint(20)
     , lastleavedport bigint(20)
     , arrivaldate date
     , departuredate date
     , nameofcaptain text
     , primary key(voyageid)
)type=innodb;

create table bl (
       blid bigint(20) not null auto_increment
     , voyageid bigint(20)
     , blno text
     , companyname text
     , status bigint(20)
     , reporttype bigint(20)
     , placeoforigin bigint(20)
     , placeofreceipt bigint(20)
     , portofloading bigint(20)
     , portofdischarge bigint(20)
     , finaldischargeplace bigint(20)
     , finaldestination bigint(20)
     , description text
     , description2 text
     , shipper text
     , consignee text
     , notify text
     , notify2 text
     , issueplace bigint(20) default 0
     , issuedate date
     , primary key(blid)
)type=innodb;

create table container (
       containerid bigint(20) not null auto_increment
     , containerno text
     , blid bigint(20)
     , containertype bigint(20)
     , sealno text
     , othersealno text
     , containersize bigint(20)
     , taraweight double
     , status bigint(20)
     , relcom bigint(20)
     , primary key(containerid)
)type=innodb;

create table cargo (
       cargoid bigint(20) not null auto_increment
     , containerid bigint(20) not null
     , commodityid bigint(20) not null
     , cargotype text
     , packid bigint(20)
     , imco text
     , unno text
     , netweight double default 0
     , packtotal double
     , primary key(cargoid)
)type=innodb;

alter table vessel
  add constraint fk_vessel_country
      foreign key (flag)
      references country (countryid)
   on delete no action
   on update no action;

alter table vessel
  add constraint fk_vessel_location
      foreign key (port)
      references location (locationid)
   on delete no action
   on update no action;

alter table voyage
  add constraint fk_voyage_1
      foreign key (vesselid)
      references vessel (vesselid)
   on delete no action
   on update no action;

alter table voyage
  add constraint fk_voyage_location_1
      foreign key (firstleavedport)
      references location (locationid)
   on delete no action
   on update no action;

alter table voyage
  add constraint fk_voyage_location_2
      foreign key (lastleavedport)
      references location (locationid)
   on delete no action
   on update no action;

alter table voyage
  add constraint fk_voyage_office
      foreign key (officeid)
      references office (officeid)
   on delete no action
   on update no action;

alter table bl
  add constraint fk_bl_1
      foreign key (voyageid)
      references voyage (voyageid)
   on delete no action
   on update no action;

alter table bl
  add constraint fk_bl_location_1
      foreign key (placeoforigin)
      references location (locationid)
   on delete no action
   on update no action;

alter table bl
  add constraint fk_bl_location_2
      foreign key (placeofreceipt)
      references location (locationid)
   on delete no action
   on update no action;

alter table bl
  add constraint fk_bl_location_3
      foreign key (portofloading)
      references location (locationid)
   on delete no action
   on update no action;

alter table bl
  add constraint fk_bl_location_4
      foreign key (portofdischarge)
      references location (locationid)
   on delete no action
   on update no action;

alter table bl
  add constraint fk_bl_location_5
      foreign key (finaldischargeplace)
      references location (locationid)
   on delete no action
   on update no action;

alter table bl
  add constraint fk_bl_location_6
      foreign key (finaldestination)
      references location (locationid)
   on delete no action
   on update no action;

alter table container
  add constraint fk_container_2
      foreign key (blid)
      references bl (blid)
   on delete no action
   on update no action;

alter table container
  add constraint fk_container_3
      foreign key (containersize)
      references containersize (containersizeid)
   on delete no action
   on update no action;

alter table container
  add constraint fk_container_containertype
      foreign key (containertype)
      references containertype (containertypeid)
   on delete no action
   on update no action;

alter table cargo
  add constraint fk_cargo_2
      foreign key (packid)
      references pack (packid)
   on delete no action
   on update no action;

alter table cargo
  add constraint fk_cargo_3
      foreign key (containerid)
      references container (containerid)
   on delete no action
   on update no action;

alter table cargo
  add constraint fk_cargo_4
      foreign key (commodityid)
      references commodity (commodityid)
   on delete no action
   on update no action;

--vwcontainerlist
create view vwcontainerlist as
	select	container.blid as blid,container.containerno as containerno,containertype.type as type,
			containersize.size as size,container.sealno as sealno
	from ((container left join containertype on((container.containertype = containertype.containertypeid)))
			left join containersize on((container.containersize = containersize.containersizeid)));
--vwcargodetails
create view	vwcargodetails as select container.blid as blid,sum(cargo.packtotal) as packtotal,
			count(container.containerno) as containercount,containersize.size as size,
			pack.type as type
	from (containersize left join (container left join (cargo left join pack on((cargo.packid = pack.packid)))
					on((container.containerid = cargo.containerid)))
					on((container.containersize = containersize.containersizeid)))
		group by container.containersize;
--vwboflading
create view vwboflading as
	select	bl.blid as blid,bl.blno as blno,bl.shipper as shipper,bl.consignee as consignee,
			bl.notify as notify,bl.notify2 as notify2,bl.description as description,
			bl.issuedate as issuedate,voyage.voyageid as voyageid,voyage.voyage as voyage,
			vessel.vesselcode as vesselcode,vessel.vesselname as vesselname,
			location.location as placeofreceipt,location_1.location as portofdischarge,
			location_2.location as portofloading,location_3.location as finaldischargeplace,
			location_4.location as issueplace
	from (((((((bl join voyage on((bl.voyageid = voyage.voyageid)))
			join vessel on((voyage.vesselid = vessel.vesselid)))
			left join location location_2 on((bl.portofloading = location_2.locationid)))
			left join location location_3 on((bl.finaldischargeplace = location_3.locationid)))
			left join location location_1 on((bl.portofdischarge = location_1.locationid)))
			left join location on((bl.portofloading = location.locationid)))
			left join location location_4 on((bl.issueplace = location_4.locationid)));