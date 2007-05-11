create table containersize (containersizeid bigint not null GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
       size varchar(20) not null, description varchar(100))

create table containertype (containertypeid bigint not null GENERATED ALWAYS AS IDENTITY PRIMARY KEY, 
    type varchar(20) not null, description varchar(100))

create table country (countryid bigint not null GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    code varchar(5) not null,name varchar(100) not null)

create table location (locationid bigint not null GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    code varchar(2000), location varchar(2000), isport char)

create table office (officeid bigint not null GENERATED ALWAYS AS IDENTITY PRIMARY KEY, 
    office varchar(2000), description varchar(2000), code varchar(2000))

create table pack (packid bigint not null GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    type varchar(2000), description varchar(2000))

create table commodity (commodityid bigint not null GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    code varchar(2000), name varchar(2000), description varchar(2000))

create table vessel (vesselid bigint not null GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    vesselcode varchar(2000), vesselname varchar(2000), flag bigint, status bigint, company varchar(2000), port bigint)

create table voyage (voyageid bigint not null GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    voyage varchar(2000), export bigint, vesselid bigint, officeid bigint, status bigint,
    firstleavedport bigint, lastleavedport bigint, arrivaldate date, departuredate date, nameofcaptain varchar(2000))

create table bl (blid bigint not null GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    voyageid bigint, blno varchar(2000), companyname varchar(2000), status bigint, reporttype bigint, placeoforigin bigint,
    placeofreceipt bigint, portofloading bigint, portofdischarge bigint, finaldischargeplace bigint, finaldestination bigint,
    description varchar(2000), description2 varchar(2000), shipper varchar(2000), consignee varchar(2000), notify varchar(2000), notify2 varchar(2000), issueplace bigint default 0,
    issuedate date)

create table container (containerid bigint not null GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    containerno varchar(2000), blid bigint, containertype bigint, sealno varchar(2000), othersealno varchar(2000), containersize bigint,
    taraweight double, status bigint, relcom bigint)

create table cargo (cargoid bigint not null GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    containerid bigint not null, commodityid bigint not null, cargotype varchar(2000), packid bigint, imco varchar(2000), unno varchar(2000),
    netweight double default 0, packtotal double)

alter table vessel add constraint fk_vessel_country foreign key (flag) references country (countryid)

alter table vessel add constraint fk_vessel_location foreign key (port) references location (locationid)

alter table voyage add constraint fk_voyage_1 foreign key (vesselid) references vessel (vesselid)

alter table voyage add constraint fk_voyage_location_1 foreign key (firstleavedport) references location (locationid)

alter table voyage add constraint fk_voyage_location_2 foreign key (lastleavedport) references location (locationid)

alter table voyage add constraint fk_voyage_office foreign key (officeid) references office (officeid)

alter table bl add constraint fk_bl_1 foreign key (voyageid) references voyage (voyageid)

alter table bl add constraint fk_bl_location_1 foreign key (placeoforigin) references location (locationid)

alter table bl add constraint fk_bl_location_2 foreign key (placeofreceipt) references location (locationid)

alter table bl add constraint fk_bl_location_3 foreign key (portofloading) references location (locationid)

alter table bl add constraint fk_bl_location_4 foreign key (portofdischarge) references location (locationid)

alter table bl add constraint fk_bl_location_5 foreign key (finaldischargeplace) references location (locationid)

alter table bl add constraint fk_bl_location_6 foreign key (finaldestination) references location (locationid)

alter table container add constraint fk_container_2 foreign key (blid) references bl (blid)

alter table container add constraint fk_container_3 foreign key (containersize) references containersize (containersizeid)

alter table container add constraint fk_container_containertype foreign key (containertype) references containertype (containertypeid)

alter table cargo add constraint fk_cargo_2 foreign key (packid) references pack (packid)

alter table cargo add constraint fk_cargo_3 foreign key (containerid) references container (containerid)

alter table cargo add constraint fk_cargo_4 foreign key (commodityid) references commodity (commodityid)

--vwcontainerlist
create view vwcontainerlist as
	select	container.blid as blid,container.containerno as containerno,containertype.type as type,
			containersize.size as size,container.sealno as sealno
	from ((container left join containertype on((container.containertype = containertype.containertypeid)))
			left join containersize on((container.containersize = containersize.containersizeid)))

--vwcargodetails

create view vwcargodetails as 
    select container.blid as blid,sum(cargo.packtotal) as packtotal,
        count(container.containerno) as containercount,containersize.size as size,pack.type as type
            from (containersize left join (container left join (cargo
                left join pack on((cargo.packid = pack.packid)))
                    on((container.containerid = cargo.containerid)))
                    on((container.containersize = containersize.containersizeid)))
    group by container.blid,container.containersize

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
			left join location location_4 on((bl.issueplace = location_4.locationid)))