package com.fdflib.miguelvasquezormproject.service;
import com.fdflib.miguelvasquezormproject.model.UserStatusTypes;
import com.fdflib.model.entity.FdfEntity;
import com.fdflib.model.util.WhereClause;
import com.fdflib.persistence.FdfPersistence;
import com.fdflib.service.impl.FdfCommonServices;
import com.fdflib.model.util.SqlStatement;
import java.util.ArrayList;
import java.util.List;
public class UserStatusTypesService extends FdfCommonServices {
    public UserStatusTypes savestatustypes(UserStatusTypes userstatustypes){
        if(userstatustypes != null){
            long number_of_rows = numberofuserstatusids();
            if(number_of_rows > 3){
                System.out.println("No more roles should be added");
            }
            else{
                return this.save(UserStatusTypes.class, userstatustypes).current;
            }
        }
       return null;
    }

    public long numberofuserstatusids(){
        long numberofrows = getEntityCount(UserStatusTypes.class);
        return numberofrows;
    }

    public UserStatusTypes getstatustypesbyID(long id){
        return getstatustypesIDwithhistory(id).current;
    }

    public FdfEntity<UserStatusTypes> getstatustypesIDwithhistory(long id) {
        FdfEntity<UserStatusTypes> getstatusid = new FdfEntity<>();

        // get the test
        if(id >= 0) {
            getstatusid = this.getEntityById(UserStatusTypes.class, id);
        }

        return getstatusid;
    }
  
    public FdfEntity<UserStatusTypes> getstatustypebynamehistory(String type){
        List<FdfEntity<UserStatusTypes>> statuswithHistory = getEntitiesByValueForPassedField(UserStatusTypes.class, "name", type);
        if(statuswithHistory.size() > 0){
            return statuswithHistory.get(0);
        }
        return null;
    }
    
    public UserStatusTypes getstatusbyname(String type){
        FdfEntity<UserStatusTypes> statuswithHistory = getstatustypebynamehistory(type);
        if(statuswithHistory != null && statuswithHistory.current != null){
            return statuswithHistory.current;
        }
       return null;
    }

}
