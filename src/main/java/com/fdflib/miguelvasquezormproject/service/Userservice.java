package com.fdflib.miguelvasquezormproject.service;
import com.fdflib.miguelvasquezormproject.model.User;
import com.fdflib.model.entity.FdfEntity;
import com.fdflib.model.util.WhereClause;
import com.fdflib.persistence.FdfPersistence;
import com.fdflib.service.impl.FdfCommonServices;
import com.fdflib.model.util.SqlStatement;
import com.fdflib.miguelvasquezormproject.service.Clientservice;
import java.util.ArrayList;
import java.util.List;
public class Userservice extends FdfCommonServices{
    //save the user, but requires passing the tid as an arguement. 
    public User saveuser(User user, long tid){
        Clientservice cs = new Clientservice();
        if(user != null && tid >= 0){
            user.currentclient = cs.getClientById(tid); //Checking to see if tenant id exists
            if(user.currentclient != null){
                user.tid = tid;
              return this.save(User.class, user).current;
            }
            
        }
       return null;
    }
    //update a user
    public User updateUser(User user, long uid){
        if(user != null){
            //check if client exists
            if(getUserById(uid) != null){
                user.id = uid;
               return this.save(User.class, user).current;
            }
        }
       return null;
    }
    
    //Save a multi tenant user and it's used to connect a user that is inside multiple tenants
    //User first has to be saved with the saveuser method
    //Then if the user wants to be in another tenant, then pass to this method the first user.id entry.
    //method was created because I wanted a way to potentially query/know if a user has multiple tenants.
    public User savemultitenantuser(User user, long uid){
         if(user != null && uid >= 0){
            //check if uid passed exists
            if(getUserById(uid) != null){
                user.multitenantuserid = uid;
                user.isinmultipletables = true;
                return this.save(User.class, user).current;
            }
         }
       return null;  
    }

    //Will return an entity of type client. Only gets 1 specific thing as uid represents an entity, and if a user has another tenant then it will get another entry.
    //It will also recieve a new uid.
    public User getUserById(long id) {
        return getUserWithHistoryById(id).current;

    }

    public FdfEntity<User> getUserWithHistoryById(long id) {
        FdfEntity<User> user = new FdfEntity<>();

        // get the test
        if(id >= 0) {
            user = this.getEntityById(User.class, id);
        }

        return user;
    }
    //returns a list of all users for a specific tenant.
    //use .size() > 0 to see if any users are returned.
    public List<FdfEntity<User>> getUsersbytidwithhistory(long tid){
        List<FdfEntity<User>> userbytid = getAll(User.class, tid);
        return userbytid;
    }

    //returns only the current records for a user with a specified tenant
    public List<User> getcurrentUsersbytid(long tid){
        List<User> currentusersoftenant = new ArrayList<>();
        if(tid >= 0){
            List<FdfEntity<User>> userwithhistory = getUsersbytidwithhistory(tid);
            //check the records to make sure there are any entries
            if(userwithhistory.size() > 0){
               for(FdfEntity<User> userhistory: userwithhistory){
                   if(userhistory.current != null){
                      currentusersoftenant.add(userhistory.current);
                   }
               }
            }
        }
      return currentusersoftenant;
    }


}
