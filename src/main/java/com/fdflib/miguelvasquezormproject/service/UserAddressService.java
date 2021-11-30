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
               if(useraddress2 == null){ //means user has no address
                  useraddress.tid = uid;
                  return this.save(Useraddress.class, useraddress).current;
               }
               else if(useraddress2 != null){ //updates address information
                   useraddress.id = useraddress2.id;
                   return this.save(Useraddress.class, useraddress).current;
                }
            }
        }
       return null;
    }

    public FdfEntity<Useraddress> getUseraddressbytidwithhistory(long uid){
        List<FdfEntity<Useraddress>> Useraddressbytid = getAll(Useraddress.class, uid);
        return Useraddressbytid.get(0);
    }

    public Useraddress getcurrentUseraddress(long uid){
        return getUseraddressbytidwithhistory(uid).current;
    }
}
