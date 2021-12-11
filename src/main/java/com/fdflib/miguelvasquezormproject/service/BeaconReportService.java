package com.fdflib.miguelvasquezormproject.service;
import com.fdflib.miguelvasquezormproject.model.Beacon_Report;
import com.fdflib.miguelvasquezormproject.service.BeaconService;
import com.fdflib.miguelvasquezormproject.service.LocationService;
import com.fdflib.miguelvasquezormproject.service.FloorsService;
import com.fdflib.model.entity.FdfEntity;
import com.fdflib.model.util.WhereClause;
import com.fdflib.persistence.FdfPersistence;
import com.fdflib.service.impl.FdfCommonServices;
import com.fdflib.model.util.SqlStatement;
import com.fdflib.miguelvasquezormproject.service.Clientservice;
import com.fdflib.miguelvasquezormproject.service.UserStatusTypesService;
import java.util.ArrayList;
import java.util.List;
public class BeaconReportService extends FdfCommonServices{
    public Beacon_Report savebeaconreport(Beacon_Report beaconreport, String uuid, long lid, long fid, long tid){
        Clientservice cs = new Clientservice();
        LocationService ls = new LocationService();
        FloorsService fs = new FloorsService();
        BeaconService bs = new BeaconService();
        if(beaconreport != null && uuid.length() > 0 && lid >= 0 && fid >= 0 && tid >= 0){
            //check if everything exists
            beaconreport.currentclient = cs.getClientById(tid);
            if(beaconreport.currentclient != null){
                beaconreport.currentbeacon = bs.getBeaconwithuuid(uuid, tid);
                beaconreport.currentlocation = ls.getlocationbyid(lid, tid);
                beaconreport.currentfloor = fs.getfloorbyid(fid, tid);
                if(beaconreport.currentbeacon != null && beaconreport.currentlocation != null && beaconreport.currentfloor != null){
                   //each beacon should only have one entry
                   Beacon_Report beaconreport2 = getBeacon_Reportbybid(beaconreport.currentbeacon.id, tid);
                   if(beaconreport2 != null){
                       //entry found
                       beaconreport.id = beaconreport2.id;
                       beaconreport.tid = beaconreport2.tid;
                       beaconreport.currentlocationid = lid;
                       beaconreport.currentfloorid = fid;
                       return this.save(Beacon_Report.class, beaconreport).current;
                   }
                   else{
                      beaconreport.currentbeaconid = beaconreport.currentbeacon.id;
                      beaconreport.currentlocationid = lid;
                      beaconreport.currentfloorid = fid;
                      beaconreport.tid = tid;
                      beaconreport.description = "Beacon is on" + beaconreport.currentlocation.name + " " + "," + beaconreport.currentfloor.name;
                      return this.save(Beacon_Report.class, beaconreport).current;
                   }
                }
            }
        }
       return null;
    }
    //Beacons should only have one entry
    public FdfEntity<Beacon_Report> getBeaconidwithhistory(long bid, long tid){
        String beaconid = Long.toString(bid);
        List<FdfEntity<Beacon_Report>> Beacon_Reportbybid = getEntitiesByValueForPassedField(Beacon_Report.class, "currentbeaconid", beaconid, tid);
        if(Beacon_Reportbybid.size() > 0){
           return Beacon_Reportbybid.get(0);
        }
        return null;
    }

    public Beacon_Report getBeacon_Reportbybid(long bid, long tid){
        FdfEntity<Beacon_Report> Beacon_Reportbybid = getBeaconidwithhistory(bid, tid);
        if(Beacon_Reportbybid != null && Beacon_Reportbybid.current != null){
            return Beacon_Reportbybid.current;
        }
        return null;
    }

    public List<FdfEntity<Beacon_Report>> getallbeaconreportsonafloorhistory(long fid, long tid){
        String fid2 = Long.toString(fid);
        List<FdfEntity<Beacon_Report>> beaconreporttablehistory = getEntitiesByValueForPassedField(Beacon_Report.class, "currentfloorid", fid2, tid);
        return beaconreporttablehistory;
    }
    public List<Beacon_Report> getallbeaconreportsonafloor(long fid, long tid){
        List<Beacon_Report> currentbeacontable = new ArrayList<>();
        if(fid >= 0){
            List<FdfEntity<Beacon_Report>> beaconreporttablehistory = getallbeaconreportsonafloorhistory(fid, tid);
            //check records
            if(beaconreporttablehistory.size() > 0){
               for(FdfEntity<Beacon_Report> idsbybeacon: beaconreporttablehistory){
                   if(idsbybeacon.current != null){
                       currentbeacontable.add(idsbybeacon.current);
                   }
               }
            }
        }
       return currentbeacontable;
    }
    

}
