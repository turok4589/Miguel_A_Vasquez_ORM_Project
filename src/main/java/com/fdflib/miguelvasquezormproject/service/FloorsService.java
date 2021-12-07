package com.fdflib.miguelvasquezormproject.service;
import com.fdflib.miguelvasquezormproject.model.Floors;
import com.fdflib.miguelvasquezormproject.service.Clientservice;
import com.fdflib.miguelvasquezormproject.service.LocationService;
import com.fdflib.model.entity.FdfEntity;
import com.fdflib.model.util.WhereClause;
import com.fdflib.persistence.FdfPersistence;
import com.fdflib.service.impl.FdfCommonServices;
import com.fdflib.model.util.SqlStatement;
import java.util.ArrayList;
import java.util.List;
public class FloorsService extends FdfCommonServices {
    //a floor has one location, and one client. Location has multiple floors
    //going to need to pass the location id, and the tenant id
    public Floors saveFloors(Floors floors, long lid, long tid){
         Clientservice cs = new Clientservice();
         LocationService ls = new LocationService();
         if(floors != null && tid >= 0 && lid >= 0){
             //check if tenant exists
             floors.currentclient = cs.getClientById(tid);
             floors.currentlocation = ls.getlocationbyid(lid, tid);
             if(floors.currentclient != null && floors.currentlocation != null){
                 //check if tenant matches location
                 if(floors.currentlocation.tid == tid){
                    floors.tid = tid;
                    floors.locationid = lid;
                    return this.save(Floors.class, floors).current;
                 }
             }
         }
       return null;
    }

    public Floors updatefloor(Floors floor, long fid, long lid, long tid){
        //update a floor by the id, also need the location id and the tenant id
        LocationService ls = new LocationService();
        Clientservice cs = new Clientservice();
        Floors floor2 = getfloorbyid(fid, tid);
        if(floor != null){
            //check if floor, and location exists
            floor.currentlocation = ls.getlocationbyid(lid, tid);
            floor.currentclient = cs.getClientById(tid);
            if(floor2 != null && floor.currentlocation != null && floor.currentclient != null){
                //check if location is correct, and it matches the tenant in the location
                if(floor2.locationid == lid && floor2.currentlocation.tid == tid)
                {
                    floor.id = fid;
                    return this.save(Floors.class, floor).current;
                }
                else{
                    System.out.println("Location doesn't match with the floor");
                }
            }
        }
       return null;
    }

    public Floors getfloorbyid(long id, long tid){
       return getFloorwithhistorybyid(id, tid).current;
    }

    public FdfEntity<Floors> getFloorwithhistorybyid(long id, long tid){
        FdfEntity<Floors> floor = new FdfEntity<>();
        //get
        if(id >= 0){
            floor = this.getEntityById(Floors.class, id, tid);
        }
        return floor;
    }
    //get all floors for a location
    public List<FdfEntity<Floors>> getFloorsbylidwithhistory(long lid, long tid){
        String lid2 = Long.toString(lid);
        List<FdfEntity<Floors>> floorbylocationid = getEntitiesByValueForPassedField(Floors.class, "locationid", lid2, tid);
        if(floorbylocationid.size() > 0){
            return floorbylocationid; //potentially multiple entries
        }
       return null;
    }

    //locations can have multiple floors so query by the locationid
    public List<Floors> getfloorbylid(long lid, long tid){
        List<Floors> currentfloors = new ArrayList<>();
        if(lid >= 0){
            List<FdfEntity<Floors>> Floorswithhistory = getFloorsbylidwithhistory(lid, tid);
            //check the records to make sure there are any entries
            if(Floorswithhistory.size() > 0){
               for(FdfEntity<Floors> floorhistory: Floorswithhistory){
                   if(floorhistory.current != null){
                       currentfloors.add(floorhistory.current);
                   }
               }
            }
        }
        return currentfloors;
    }
}
