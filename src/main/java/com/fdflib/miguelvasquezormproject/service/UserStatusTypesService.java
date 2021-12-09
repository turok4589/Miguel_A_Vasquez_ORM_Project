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
        if(numberofrows <= 0){
            System.out.println("There are no roles in this table");
        }
        else{
            return numberofrows;
        }
       return -1;
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
}
