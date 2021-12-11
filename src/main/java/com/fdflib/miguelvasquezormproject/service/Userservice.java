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
    public User updateUser(User user, long uid, long tid){
        if(user != null){
            //check if client exists, and get the requested user.
            if(getUserById(uid, tid) != null){
                user.id = uid;
               return this.save(User.class, user).current;
            }
        }
       return null;
    }
    //testing a new field known as username. More practical way to query for a specific user
    public User saveuserversion2(User user, long tid){
        Clientservice cs = new Clientservice();
        if(user != null && tid >= 0 && user.username.length() > 0){
            user.currentclient = cs.getClientById(tid); //Checking to see if tenant id exists
            if(user.currentclient != null){ //this means tenant exists
               //check if there's already a matching username
               User user2 = getCurrentUserbyusername(user.username, tid);
               if(user2 != null){
                   //means a user with that username exists
                   user.id = user2.id;
                   return this.save(User.class, user).current;
               }
               else{
                   user.tid = tid;
                   return this.save(User.class, user).current;
               }
            }
        }
       return null;
    }
    
    //retrieve a specific user by their username, username is a unique field
    //there should only be one username per person
    //pass the username, and the tenant id
    public FdfEntity<User> getUserbyusernamewithhistory(String Username, long tid){
        List<FdfEntity<User>> Usernamehistory = getEntitiesByValueForPassedField(User.class, "username", Username, tid);
        if(Usernamehistory.size() > 0){
            return Usernamehistory.get(0);
        }
      return null;
    }

    public User getCurrentUserbyusername(String Username, long tid){
        FdfEntity<User> Usernamehistory = getUserbyusernamewithhistory(Username, tid);
        if(Usernamehistory != null && Usernamehistory.current != null){
            return Usernamehistory.current;
        }
       return null;
    }
    
    //Save a multi tenant user and it's used to connect a user that is inside multiple tenants
    //User first has to be saved with the saveuser method
    //Then if the user wants to be in another tenant, then pass to this method the first user.id entry.
    //method was created because I wanted a way to potentially query/know if a user has multiple tenants.
    public User savemultitenantuser(User user, long uid, long tid){
         if(user != null && uid >= 0){
            //check if uid passed exists
            if(getUserById(uid, tid) != null){
                user.multitenantuserid = uid;
                user.isinmultipletables = true;
                return this.save(User.class, user).current;
            }
         }
       return null;  
    }

    //Will return an entity of type client. Only gets 1 specific thing as uid represents an entity, and if a user has another tenant then it will get another entry.
    //It will also recieve a new uid.
    public User getUserById(long id, long tid) {
        return getUserWithHistoryById(id, tid).current;

    }

    public FdfEntity<User> getUserWithHistoryById(long id, long tid) {
        FdfEntity<User> user = new FdfEntity<>();

        // get the test
        if(id >= 0) {
            user = this.getEntityById(User.class, id, tid);
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
