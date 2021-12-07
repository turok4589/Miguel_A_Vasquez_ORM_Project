package com.fdflib.miguelvasquezormproject.service;
import com.fdflib.miguelvasquezormproject.model.User_ID;
import com.fdflib.miguelvasquezormproject.service.Userservice;
import com.fdflib.model.entity.FdfEntity;
import com.fdflib.model.util.WhereClause;
import com.fdflib.persistence.FdfPersistence;
import com.fdflib.service.impl.FdfCommonServices;
import com.fdflib.model.util.SqlStatement;
import java.util.ArrayList;
import java.util.List;
public class User_IDService extends FdfCommonServices {
    public User_ID saveUser_ID(User_ID user_ID, long uid, long tid){
        Userservice us = new Userservice();
        if(user_ID != null && uid >= 0){
            user_ID.currentuser = us.getUserById(uid, tid); //check if user exists
            if(user_ID.currentuser != null){
                User_ID user_ID2 = getUser_IDbyidnumber(user_ID.idnumber, tid);
                if(user_ID2 != null){
                    //this means that an entity with the same id number was found
                    user_ID.id = user_ID2.id;
                    //this will result in a insert instead of a save
                }
                if(user_ID != null){
                    //updates id or creates a new id for a user
                    if(user_ID2 != null){
                       return this.save(User_ID.class, user_ID).current;
                    }
                    else{
                        user_ID.tid = uid;
                        return this.save(User_ID.class, user_ID).current;
                    }
                }
            }
        }
       return null;
    }
    //gets an id by its id number
    public User_ID getUser_IDbyidnumber(long idnumber, long tid){
        FdfEntity<User_ID> User_IDwithHistory = getUser_IDbyidnumberwithhistory(idnumber, tid);
        if(User_IDwithHistory != null && User_IDwithHistory.current != null){
            return User_IDwithHistory.current;
        }
       return null;
    }
    //pass idnumbner
    public FdfEntity<User_ID> getUser_IDbyidnumberwithhistory(long idnumber, long tid){
        String idnumber2 = Long.toString(idnumber);
        List<FdfEntity<User_ID>> User_IDwithHistory = getEntitiesByValueForPassedField(User_ID.class, "idnumber", idnumber2, tid);
        if(User_IDwithHistory.size() > 0){
            return User_IDwithHistory.get(0); //should only be one entry
        }
       return null;
    }
    //gets all the ids for a specific user, user can have multiple ids, but an id needs a unique id number.
    public List<FdfEntity<User_ID>> getallidsforauserwithhistory(long uid){
        List<FdfEntity<User_ID>> idsbyuserid = getAll(User_ID.class, uid);
        return idsbyuserid;
    }

    public List<User_ID> getallcurrentidsforauser(long uid){
        List<User_ID> currentidsforuser = new ArrayList<>();
        if(uid >= 0){
            List<FdfEntity<User_ID>> idsbyuserid = getallidsforauserwithhistory(uid);
            //check the records to make sure there are entries
            if(idsbyuserid.size() > 0){
                for(FdfEntity<User_ID> idshistory: idsbyuserid){
                    if(idshistory.current != null){
                        currentidsforuser.add(idshistory.current);
                    }
                }
            }
        }
       return currentidsforuser;
    }
}
