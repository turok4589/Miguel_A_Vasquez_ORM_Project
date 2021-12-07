package com.fdflib.miguelvasquezormproject.service;
import com.fdflib.miguelvasquezormproject.model.UserCheckin;
import com.fdflib.miguelvasquezormproject.service.Userservice;
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
    public UserCheckin saveusercheckin(UserCheckin usercheckin, long uid, long roleid, long tid){
        Userservice us = new Userservice();
        UserStatusTypesService ust = new UserStatusTypesService();
        if(usercheckin != null && uid >= 0 && roleid >= 0){
           usercheckin.currentuser = us.getUserById(uid, tid); // checking to see if user exists
           usercheckin.currentuserstatustype = ust.getstatustypesbyID(roleid);
           if(usercheckin.currentuser != null && usercheckin.currentuserstatustype != null){
              //user should only have one status
              UserCheckin usercheckin2 = getcurrentUserCheckin(uid);
              if(usercheckin2 != null){
                  //user has an entry in the table
                  //might add restrictions later like user needs to arrive b4 checks in
                  usercheckin.id = usercheckin2.id;
              }
              if(usercheckin2 != null){
                  return this.save(UserCheckin.class, usercheckin).current;
              }
              else{
                  usercheckin.tid = uid;
                  return this.save(UserCheckin.class, usercheckin).current;
              }

           }
        }
       return null;
    }

    public FdfEntity<UserCheckin> getUserCheckinbyuidwithhistory(long uid){
        List<FdfEntity<UserCheckin>> UserCheckinbyuid = getAll(UserCheckin.class, uid);
        if(UserCheckinbyuid.size() > 0){
           return UserCheckinbyuid.get(0);
        }
        return null;
    }

    public UserCheckin getcurrentUserCheckin(long uid){
        FdfEntity<UserCheckin> UserCheckinwithhistory = getUserCheckinbyuidwithhistory(uid);
        if(UserCheckinwithhistory != null && UserCheckinwithhistory.current != null){
            return UserCheckinwithhistory.current;
        }
        return null;
    }
    
}
