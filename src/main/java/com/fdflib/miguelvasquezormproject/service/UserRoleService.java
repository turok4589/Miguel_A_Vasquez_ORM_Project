package com.fdflib.miguelvasquezormproject.service;
import com.fdflib.miguelvasquezormproject.model.UserRole;
import com.fdflib.model.entity.FdfEntity;
import com.fdflib.model.util.WhereClause;
import com.fdflib.persistence.FdfPersistence;
import com.fdflib.service.impl.FdfCommonServices;
import com.fdflib.model.util.SqlStatement;
import com.fdflib.miguelvasquezormproject.service.Userservice;
import com.fdflib.miguelvasquezormproject.service.Rolesservice;
import java.util.ArrayList;
import java.util.List;
public class UserRoleService extends FdfCommonServices {
     public UserRole saveUserRole(UserRole userRole, long uid, long roleid, long tid){
         Userservice us = new Userservice();
         Rolesservice rs = new Rolesservice();
         if(userRole != null && tid >= 0 && roleid >= 0){
             //check if user exists
             userRole.currentuser = us.getUserById(uid, tid);
             //check if roleid exists
             userRole.roles = rs.getRoleById(roleid);
             if(userRole.currentuser != null && userRole.roles != null){
                 userRole.tid = uid;
                 return this.save(UserRole.class, userRole).current;
             }
         }
        return null;
     }

     //returns a list with the history using the uid
     public List<FdfEntity<UserRole>> getUserRoleServicesbyuidwithhistory(long uid){
         List<FdfEntity<UserRole>> userrole = getAll(UserRole.class, uid);
         return userrole;
     }

     //users can have multiple roles so a list will be returned
     //this list will contain only the current records
     public List<UserRole> getUserRoleServicesbyuid(long uid){
         List<UserRole> userrole = new ArrayList<>();
         if(uid >= 0){
             List<FdfEntity<UserRole>> userrrolehistory = getUserRoleServicesbyuidwithhistory(uid);
             //check the records to make sure there are entries
             if(userrrolehistory.size() > 0){
                 for(FdfEntity<UserRole> userrolehistory2: userrrolehistory){
                         if(userrolehistory2.current != null){
                             userrole.add(userrolehistory2.current);
                         }
                    }
                 }
             }
            return userrole;
         }
}
