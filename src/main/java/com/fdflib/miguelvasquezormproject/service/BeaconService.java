package com.fdflib.miguelvasquezormproject.service;
import com.fdflib.miguelvasquezormproject.model.Beacons;
import com.fdflib.miguelvasquezormproject.model.Client;
import com.fdflib.miguelvasquezormproject.service.Clientservice;
import com.fdflib.model.entity.FdfEntity;
import com.fdflib.model.util.WhereClause;
import com.fdflib.persistence.FdfPersistence;
import com.fdflib.service.impl.FdfCommonServices;
import com.fdflib.model.util.SqlStatement;
import java.util.ArrayList;
import java.util.List;
public class BeaconService extends FdfCommonServices{
    public Beacons saveBeacon(Beacons beacons, long tid, String uuid){
        Clientservice cs = new Clientservice();
        if(beacons != null && tid >= 0 && uuid.length() > 0){
           beacons.currentclient = cs.getClientById(tid); //check if client exists
           if(beacons.currentclient != null){
              Beacons beacons2 = getBeaconwithuuid(uuid, tid);
              //uuid id is unique so if this is not null then that means a bacon with the same uuid is in this table
              if(beacons2 != null){
                  beacons.id = beacons2.id;
                  beacons.tid = beacons2.tid;
                  return this.save(Beacons.class, beacons).current;
              }
              else{
                  beacons.tid = tid;
                  beacons.uuid = uuid;
                  return this.save(Beacons.class, beacons).current;
              }
           }
        }
       return null;
    }
    
    public Beacons getbeaconbyid(long bid, long tid){
       return getbeaconwithhistorybyid(bid, tid).current;
    }

    public FdfEntity<Beacons> getbeaconwithhistorybyid(long id, long tid){
        FdfEntity<Beacons> bea = new FdfEntity<>();
        if(id >= 0){
            bea = this.getEntityById(Beacons.class, id, tid);
        }
        return bea;
    }
    //pass the uuid and get history
    public FdfEntity<Beacons> getbeaconwithuuidhistory(String uuid, long tid){
        List<FdfEntity<Beacons>> Beaconwithhistory = getEntitiesByValueForPassedField(Beacons.class, "uuid", uuid, tid);
        if(Beaconwithhistory.size() > 0){
            return Beaconwithhistory.get(0); //one beacon per uuid
        }
       return null;
    }
    //get currect beacon with uuid
    public Beacons getBeaconwithuuid(String uuid, long tid){
        FdfEntity<Beacons> Beaconswithhistory = getbeaconwithuuidhistory(uuid, tid);
        if(Beaconswithhistory != null && Beaconswithhistory.current != null){
            return Beaconswithhistory.current;
        }
       return null;
    }

    //get all beacons for a client
    public List<FdfEntity<Beacons>> getclientbeaconhistory(long tid){
        List<FdfEntity<Beacons>> beaconsbyclientid = getAll(Beacons.class, tid);
        return beaconsbyclientid;
    }

    public List<Beacons> getallcurrentbeacons(long tid){
        List<Beacons> currentbeacons = new ArrayList<>();
        if(tid >= 0){
            List<FdfEntity<Beacons>> currentbeaconsid = getclientbeaconhistory(tid);
            if(currentbeaconsid.size() > 0){
                for(FdfEntity<Beacons> idsbeacon: currentbeaconsid){
                    if(idsbeacon.current != null){
                        currentbeacons.add(idsbeacon.current);
                    }
                }
            }
        }
      return currentbeacons;
    }
    
}
