--vwcontainerlist
CREATE VIEW `vwcontainerlist` AS
	select `container`.`blid` AS `blid`,`container`.`containerno` AS `containerno`,`containertype`.`type` AS `type`,`containersize`.`size` AS `size`,`container`.`sealno` AS `sealno`
	from ((`container` left join `containertype` on((`container`.`containertype` = `containertype`.`containertypeid`))) left join `containersize` on((`container`.`containersize` = `containersize`.`containersizeid`)));
--vwcargodetails
CREATE VIEW `vwcargodetails` AS select `container`.`blid` AS `blid`,sum(`cargo`.`packtotal`) AS `packtotal`,count(`container`.`containerno`) AS `containercount`,`containersize`.`size` AS `size`,`pack`.`type` AS `type`
	from (`containersize` left join (`container` left join (`cargo` left join `pack` on((`cargo`.`packid` = `pack`.`packid`))) on((`container`.`containerid` = `cargo`.`containerid`))) on((`container`.`containersize` = `containersize`.`containersizeid`)))
		group by `container`.`containersize`;
--vwboflading
CREATE VIEW `vwboflading` AS
	select `bl`.`blid` AS `blid`,`bl`.`blno` AS `blno`,`bl`.`shipper` AS `shipper`,`bl`.`consignee` AS `consignee`,`bl`.`notify` AS `notify`,`bl`.`notify2` AS `notify2`,`bl`.`description` AS `description`,`bl`.`issuedate` AS `issuedate`,`voyage`.`voyageid` AS `voyageid`,`voyage`.`voyage` AS `voyage`,`vessel`.`vesselcode` AS `vesselcode`,`vessel`.`vesselname` AS `vesselname`,`location`.`location` AS `placeofreceipt`,`location_1`.`location` AS `portofdischarge`,`location_2`.`location` AS `portofloading`,`location_3`.`location` AS `finaldischargeplace`,`location_4`.`location` AS `issueplace`
	from (((((((`bl` join `voyage` on((`bl`.`voyageid` = `voyage`.`voyageid`))) join `vessel` on((`voyage`.`vesselid` = `vessel`.`vesselid`))) left join `location` `location_2` on((`bl`.`portofloading` = `location_2`.`locationid`))) left join `location` `location_3` on((`bl`.`finaldischargeplace` = `location_3`.`locationid`))) left join `location` `location_1` on((`bl`.`portofdischarge` = `location_1`.`locationid`))) left join `location` on((`bl`.`portofloading` = `location`.`locationid`))) left join `location` `location_4` on((`bl`.`issueplace` = `location_4`.`locationid`)));

--vwboflading ejbql
select bl.blid AS blid,bl.blno AS blno,bl.shipper AS shipper,bl.consignee AS consignee,bl.notify AS notify,bl.notify2 AS notify2,bl.description AS description,bl.issuedate AS issuedate,voyage.voyageid AS voyageid,voyage.voyage AS voyage,vessel.vesselcode AS vesselcode,vessel.vesselname AS vesselname,location.location AS placeofreceipt,location_1.location AS portofdischarge,location_2.location AS portofloading,location_3.location AS finaldischargeplace,location_4.location AS issueplace
from (((((((bl join voyage on((bl.voyageid = voyage.voyageid)))
	join vessel on((voyage.vesselid = vessel.vesselid)))
	left join location location_2 on((bl.portofloading = location_2.locationid)))
	left join location location_3 on((bl.finaldischargeplace = location_3.locationid)))
	left join location location_1 on((bl.portofdischarge = location_1.locationid)))
	left join location on((bl.portofloading = location.locationid)))
	left join location location_4 on((bl.issueplace = location_4.locationid)));