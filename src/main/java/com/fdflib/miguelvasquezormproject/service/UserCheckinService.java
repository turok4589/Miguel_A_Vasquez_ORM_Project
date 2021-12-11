package com.fdflib.miguelvasquezormproject.service;
import com.fdflib.miguelvasquezormproject.model.UserCheckin;
import com.fdflib.miguelvasquezormproject.service.Userservice;
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
public class UserCheckinService extends FdfCommonServices{
    public UserCheckin saveusercheckin(UserCheckin usercheckin, long uid, long roleid, long lid, long fid, long tid){
        Userservice us = new Userservice();
        UserStatusTypesService ust = new UserStatusTypesService();
        LocationService ls = new LocationService();
        FloorsService fs = new FloorsService();
        Clientservice cs = new Clientservice();
        if(usercheckin != null && uid >= 0 && roleid >= 0){
           usercheckin.currentuser = us.getUserById(uid, tid); // checking to see if user exists
           usercheckin.currentuserstatustype = ust.getstatustypesbyID(roleid);
           usercheckin.currentlocation = ls.getlocationbyid(lid, tid);
           usercheckin.currentfloor = fs.getfloorbyid(fid, tid);
           if(usercheckin.currentuser != null && usercheckin.currentuserstatustype != null){
              //user should only have one status
              UserCheckin usercheckin2 = getcurrentUserCheckin(uid, tid);
              if(usercheckin2 != null){
                  //user has an entry in the table
                  //might add restrictions later like user needs to arrive b4 checks in
                  usercheckin.id = usercheckin2.id;
                  usercheckin.tid = usercheckin2.tid;
                  usercheckin.currentuserstatustypeid = roleid;
                  usercheckin.currentlocationid = lid;
                  usercheckin.currentfloorid = fid;
                  usercheckin.description = "Going to " + usercheckin.currentlocation.name + " " + ", " + usercheckin.currentfloor.name;
              }
              if(usercheckin2 != null){
                  return this.save(UserCheckin.class, usercheckin).current;
              }
              else{
                  usercheckin.tid = tid;
                  usercheckin.currentuserid = uid;
                  usercheckin.currentuserstatustypeid = roleid;
                  usercheckin.currentlocationid = lid;
                  usercheckin.currentfloorid = fid;
                  usercheckin.description = "Going to " + usercheckin.currentlocation.name + " " + ", " + usercheckin.currentfloor.name;
                  return this.save(UserCheckin.class, usercheckin).current;
              }

           }
        }
       return null;
    }

    public FdfEntity<UserCheckin> getUserCheckinbyuidwithhistory(long uid, long tid){
        String userid2 = Long.toString(uid);
        List<FdfEntity<UserCheckin>> UserCheckinbyuid = getEntitiesByValueForPassedField(UserCheckin.class, "currentuserid", userid2, tid);
        if(UserCheckinbyuid.size() > 0){
           return UserCheckinbyuid.get(0);
        }
        return null;
    }

    public UserCheckin getcurrentUserCheckin(long uid, long tid){
        FdfEntity<UserCheckin> UserCheckinwithhistory = getUserCheckinbyuidwithhistory(uid, tid);
        if(UserCheckinwithhistory != null && UserCheckinwithhistory.current != null){
            return UserCheckinwithhistory.current;
        }
        return null;
    }

    public List<FdfEntity<UserCheckin>> getallcheckinsforclienthistory(long tid){
        List<FdfEntity<UserCheckin>> checkintablehistory = getAll(UserCheckin.class, tid);
        return checkintablehistory;
    }

    public List<UserCheckin> getallcheckins(long tid){
        List<UserCheckin> currentcheckins = new ArrayList<>();
        if(tid >= 0){
            List<FdfEntity<UserCheckin>> checkintablehistory = getallcheckinsforclienthistory(tid);
            if(checkintablehistory.size() > 0){
                for(FdfEntity<UserCheckin> idsbycheckin: checkintablehistory){
                    if(idsbycheckin.current != null){
                        currentcheckins.add(idsbycheckin.current);
                    }
                }
            } 
        }
        return currentcheckins;
    }
    
}
