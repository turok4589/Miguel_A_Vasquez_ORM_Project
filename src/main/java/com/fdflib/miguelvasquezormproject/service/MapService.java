package com.fdflib.miguelvasquezormproject.service;
import com.fdflib.miguelvasquezormproject.model.Map;
import com.fdflib.miguelvasquezormproject.service.Clientservice;
import com.fdflib.miguelvasquezormproject.service.LocationService;
import com.fdflib.miguelvasquezormproject.service.FloorsService;
import com.fdflib.model.entity.FdfEntity;
import com.fdflib.model.util.WhereClause;
import com.fdflib.persistence.FdfPersistence;
import com.fdflib.service.impl.FdfCommonServices;
import com.fdflib.model.util.SqlStatement;
import java.util.ArrayList;
import java.util.List;
public class MapService extends FdfCommonServices {
    public Map savemap(Map maps, long tid, long lid, long fid){
        //map needs a floor and locations id
        //map is tied to a single floor, each floor as a map
        Clientservice cs = new Clientservice();
        LocationService ls = new LocationService();
        FloorsService fs = new FloorsService();
        if(maps != null && tid >= 0 && lid >= 0 && fid >= 0){
            //check if tenant, floor, and location exist
            maps.currentclient = cs.getClientById(tid);
            maps.currentlocation = ls.getlocationbyid(lid, tid);
            maps.currentfloors = fs.getfloorbyid(fid, tid);
            //check if they match
            if(maps.currentclient != null && maps.currentlocation != null && maps.currentfloors != null){
               //check if stuff matches
               if(maps.currentlocation.tid == tid && maps.currentfloors.tid == tid && maps.currentfloors.locationid == lid){
                  //check if map already has a floor attatched to it.
                  Map maps2 = getcurrentfloopmap(fid, tid);
                  if(maps2 != null){
                      maps.id = maps2.id;
                      return this.save(Map.class, maps).current;
                  }
                  else{
                    maps.tid = tid;
                    maps.locationid = lid;
                    maps.floorid = fid;
                    return this.save(Map.class, maps).current;
                  }
               }
            }
        }
       return null;
    }

    public Map updatemap(Map maps, long id, long fid, long lid, long tid){
        //update map
        Clientservice cs = new Clientservice();
        LocationService ls = new LocationService();
        FloorsService fs = new FloorsService();
        if(maps != null){
           //check if everything exists
           Map maps2 = getmapbyid(id, tid);
           maps.currentlocation = ls.getlocationbyid(lid, tid);
           maps.currentfloors = fs.getfloorbyid(fid, tid);
           maps.currentclient = cs.getClientById(tid);
           if(maps2 != null && maps.currentlocation != null && maps.currentfloors != null && maps.currentclient != null){
               //now check if everything matches
               if(maps2.locationid == lid && maps2.tid == tid && maps2.currentfloors.id == fid && maps.currentlocation.tid == tid && maps.currentfloors.tid == tid){
                  maps.id = id;
                  return this.save(Map.class, maps).current;
               }
               else{
                   System.out.println("Can't update this entry");
               }
           }
        }
       return null;
    }

    public Map getmapbyid(long mapid, long tid){
       return getMapwithhistorybyid(mapid, tid).current;
    }
    
    public FdfEntity<Map> getMapwithhistorybyid(long id, long tid){
        FdfEntity<Map> maps = new FdfEntity<>();
        if(id >= 0){
            maps = this.getEntityById(Map.class, id, tid);
        }
       return maps;
    }

    //get all maps for a floor, floor only has one
    public FdfEntity<Map> getmapforafloorhistory(long fid, long tid){
        String fid2 = Long.toString(fid);
        List<FdfEntity<Map>> mapbyfid = getEntitiesByValueForPassedField(Map.class, "floorid", fid2, tid);
        if(mapbyfid.size() > 0){
            return mapbyfid.get(0); //we know there is only going to be one map entry with this floorid
        }
       return null;
    }
    //get all current maps for a floor
    public Map getcurrentfloopmap(long fid, long tid){
        FdfEntity<Map> Mapfloorwithhistory = getmapforafloorhistory(fid, tid);
        if(Mapfloorwithhistory != null && Mapfloorwithhistory.current != null){
            return Mapfloorwithhistory.current;
        }
       return null;
    }


}
