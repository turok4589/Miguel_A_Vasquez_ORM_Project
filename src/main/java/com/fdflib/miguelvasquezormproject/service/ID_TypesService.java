package com.fdflib.miguelvasquezormproject.service;
import com.fdflib.miguelvasquezormproject.model.ID_types;
import com.fdflib.model.entity.FdfEntity;
import com.fdflib.model.util.WhereClause;
import com.fdflib.persistence.FdfPersistence;
import com.fdflib.service.impl.FdfCommonServices;
import com.fdflib.model.util.SqlStatement;
import java.util.ArrayList;
import java.util.List;
public class ID_TypesService extends FdfCommonServices{
    public ID_types saveID_Types(ID_types iD_Types){
        if(iD_Types != null){
           long number_of_rows = numberofids();
           if(number_of_rows > 2)
             {
                System.out.println("No more roles should be added to this table");
             }
            else
              {
                 return this.save(ID_types.class, iD_Types).current; 
              }
        }
       return null;
    }
    
    public long numberofids(){
        long numberofrows = getEntityCount(ID_types.class);
        if(numberofrows <= 0){
            System.out.println("There are no roles in this table");
        }
        else
        {
            return numberofrows;
        }
       return -1;
    }

    public ID_types getID_TypesById(long id) {
        return getID_TypesWithHistoryById(id).current;
    }

    public FdfEntity<ID_types> getID_TypesWithHistoryById(long id) {
        FdfEntity<ID_types> iD_Types = new FdfEntity<>();

        // get the test
        if(id >= 0) {
            iD_Types = this.getEntityById(ID_types.class, id);
        }

        return iD_Types;
    }

    public FdfEntity<ID_types> getID_typesbynamewithhistory(String idtype){
        List<FdfEntity<ID_types>> ID_typeswithHistory = getEntitiesByValueForPassedField(ID_types.class, "name", idtype);
        if(ID_typeswithHistory.size() > 0){
            return ID_typeswithHistory.get(0);
        }
        return null;
    }

    public Gendertypes getID_typesbyname(String idtype){
        List<FdfEntity<Gendertypes>> ID_typeswithHistory = getID_typesbynamewithhistory(idtype);
        if(ID_typeswithHistory != null && ID_typeswithHistory.current != null){
            return ID_typeswithHistory.current;
        }
       return null;
    }
}
