package com.fdflib.miguelvasquezormproject.service;
import com.fdflib.miguelvasquezormproject.model.Locations;
import com.fdflib.miguelvasquezormproject.service.Clientservice;
import com.fdflib.model.entity.FdfEntity;
import com.fdflib.model.util.WhereClause;
import com.fdflib.persistence.FdfPersistence;
import com.fdflib.service.impl.FdfCommonServices;
import com.fdflib.model.util.SqlStatement;
import java.util.ArrayList;
import java.util.List;
public class LocationService extends FdfCommonServices{
    public Locations savelocation(Locations locations, long tid){
        //a location should only have one client, but a client can have multiple
        //locations
        Clientservice cs = new Clientservice();
        if(locations != null && tid >= 0){
            //check if tenant exists
            locations.currentclient = cs.getClientById(tid); //client should be unique
            if(locations.currentclient != null){
               locations.tid = tid;
               return this.save(Locations.class, locations).current;
            }
         }
       return null;
     }

     public Locations updateLocation(Locations locations, long lid, long tid){
         //this will update a location by the id
         if(locations != null){
            //check if the location exists
            if(getlocationbyid(lid, tid) != null){
                //check if tid matches location
                if(getlocationbyid(lid, tid) != null){
                   locations.id = lid;
                   return this.save(Locations.class, locations).current;
                }
                else{
                    System.out.println("The client id passed, doesn't match with the location");
                }
            }
         }
        return null;
     }

     public Locations getlocationbyid(long id, long tid){
         return getlocationsbyidwithhistory(id, tid).current;
     }

     public FdfEntity<Locations> getlocationsbyidwithhistory(long id, long tid){
         FdfEntity<Locations> locations = new FdfEntity<>();
         // get the test
         if(id >= 0){
             locations = getEntityById(Locations.class, id, tid);
         }
         return locations;
     }

     public List<FdfEntity<Locations>> getLocationbytidwithhistory(long tid){
        List<FdfEntity<Locations>> locationbytid = getAll(Locations.class, tid);
        return locationbytid;
     }

     public List<Locations> getcurrentlocationbytid(long tid){
         List<Locations> currentlocation = new ArrayList<>();
         if(tid >= 0){
             List<FdfEntity<Locations>> locationwithhistory = getLocationbytidwithhistory(tid);
             //check to see if there are any
             if(locationwithhistory.size() > 0){
                 for(FdfEntity<Locations> locationhistory: locationwithhistory){
                     if(locationhistory.current != null){
                         currentlocation.add(locationhistory.current);
                     }
                 }
             }
         }
        return currentlocation;
     }

}
