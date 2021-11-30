package com.fdflib.miguelvasquezormproject.service;
import com.fdflib.miguelvasquezormproject.model.Useraddress;
import com.fdflib.miguelvasquezormproject.service.Userservice;
import com.fdflib.model.entity.FdfEntity;
import com.fdflib.model.util.WhereClause;
import com.fdflib.persistence.FdfPersistence;
import com.fdflib.service.impl.FdfCommonServices;
import com.fdflib.model.util.SqlStatement;
import com.fdflib.miguelvasquezormproject.service.Clientservice;
import java.util.ArrayList;
import java.util.List;
public class UserAddressService extends FdfCommonServices{
    //save address to specified user, there should only be one address per user
    public Useraddress saveUseraddress(Useraddress useraddress, long uid){
        Userservice us = new Userservice();
        if(useraddress != null && uid >= 0){
            useraddress.currentuser = us.getUserById(uid); //Checking to see if tenant id exists
            if(useraddress.currentuser != null){
               //a user should only have one login, so check to see if they have one
               Useraddress useraddress2 = getcurrentUseraddress(uid);
               if(useraddress2 != null){ //user has an address
                  useraddress.id = useraddress2.id;
               }
               if(useraddress != null){ //updates address information or saves new useraddress 
                   if(useraddress2 != null){
                      return this.save(Useraddress.class, useraddress).current;
                   }
                   else{
                      useraddress.tid = uid;
                      return this.save(Useraddress.class, useraddress).current;
                   }
                }
            }
        }
       return null;
    }

    public FdfEntity<Useraddress> getUseraddressbytidwithhistory(long uid){
        List<FdfEntity<Useraddress>> Useraddressbytid = getAll(Useraddress.class, uid);
        if(Useraddressbytid.size() > 0){
           return Useraddressbytid.get(0);
        }
        return null;
    }

    public Useraddress getcurrentUseraddress(long uid){
        FdfEntity<Useraddress> Useraddresswithhistory = getUseraddressbytidwithhistory(uid);
        if(Useraddresswithhistory != null && Useraddresswithhistory.current != null){
            return getUseraddressbytidwithhistory(uid).current;
        }
        return null;
    }
}
