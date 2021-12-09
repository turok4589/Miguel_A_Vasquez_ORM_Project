package com.fdflib.miguelvasquezormproject.service;
import com.fdflib.miguelvasquezormproject.model.Roles;
import com.fdflib.model.entity.FdfEntity;
import com.fdflib.model.util.WhereClause;
import com.fdflib.persistence.FdfPersistence;
import com.fdflib.service.impl.FdfCommonServices;
import com.fdflib.model.util.SqlStatement;
import java.util.ArrayList;
import java.util.List;
public class Rolesservice extends FdfCommonServices {
    public Roles saveRole(Roles role){
        if(role != null){
           long number_of_rows = numberofroles();
           if(number_of_rows > 24)
             {
                System.out.println("No more roles should be added to this table");
             }
            else
              {
                 return this.save(Roles.class, role).current; 
              }
        }
       return null;
    }

    public long numberofroles(){
        long numberofrows = getEntityCount(Roles.class);
        if(numberofrows <= 0){
            System.out.println("There are no roles in this table");
        }
        else
        {
            return numberofrows;
        }
       return -1;
    }
    public Roles getRoleById(long id) {
        return getRolesWithHistoryById(id).current;

    }

    public FdfEntity<Roles> getRolesWithHistoryById(long id) {
        FdfEntity<Roles> roles = new FdfEntity<>();

        // get the test
        if(id >= 0) {
            roles = this.getEntityById(Roles.class, id);
        }

        return roles;
    }
}
