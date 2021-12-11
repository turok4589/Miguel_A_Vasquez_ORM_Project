package com.fdflib.miguelvasquezormproject.service;
import com.fdflib.miguelvasquezormproject.model.Login;
import com.fdflib.miguelvasquezormproject.service.Userservice;
import com.fdflib.model.entity.FdfEntity;
import com.fdflib.model.util.WhereClause;
import com.fdflib.persistence.FdfPersistence;
import com.fdflib.service.impl.FdfCommonServices;
import com.fdflib.model.util.SqlStatement;
import com.fdflib.miguelvasquezormproject.service.Clientservice;
import java.util.ArrayList;
import java.util.List;
public class LoginService extends FdfCommonServices{
    //this will save the login for the specified user, in this case the user will be the tenant of the login
    public Login savelogin(Login login, long uid, long tid){
        Userservice us = new Userservice();
        if(login != null && uid >= 0){
            login.currentuser = us.getUserById(uid, tid); //Checking to see if tenant id exists
            if(login.currentuser != null){
               //a user should only have one login, so check to see if they have one
               Login login2 = getcurrentloginforuser(uid, tid);
               if(login2 != null){
                   //updates login information
                   login.id = login2.id;
                   login.tid = login2.tid;
                   return this.save(Login.class, login).current;
               }
               else{
                   //means user has no login so create an entity
                   login.tid = tid;
                   login.userid = uid;
                   return this.save(Login.class, login).current;
                }
            }
        }
       return null;
    }
    //get login information for user with history, there should only be one entry
    public FdfEntity<Login> getLoginwithhistory(long uid, long tid){
        String userid2 = Long.toString(uid);
        List<FdfEntity<Login>> loginbytid = getEntitiesByValueForPassedField(Login.class, "userid", userid2, tid);
        if(loginbytid.size() > 0){
           return loginbytid.get(0);
        }
        return null;
    }

    public Login getcurrentloginforuser(long uid, long tid){
        return getLoginwithhistory(uid, tid).current;
    }
    
}
