package com.fdflib.miguelvasquezormproject.service;
import com.fdflib.miguelvasquezormproject.model.Gendertypes;
import com.fdflib.model.entity.FdfEntity;
import com.fdflib.model.util.WhereClause;
import com.fdflib.persistence.FdfPersistence;
import com.fdflib.service.impl.FdfCommonServices;
import com.fdflib.model.util.SqlStatement;
import java.util.ArrayList;
import java.util.List;
public class GendertypesService extends FdfCommonServices{
    public Gendertypes saveGendertypes(Gendertypes gendertypes){
        if(gendertypes != null){
           long number_of_rows = numberofgenders();
           if(number_of_rows > 3)
             {
                System.out.println("No more roles should be added to this table");
             }
            else
              {
                 return this.save(Gendertypes.class, gendertypes).current; 
              }
        }
       return null;
    }

    public long numberofgenders(){
        long numberofrows = getEntityCount(Gendertypes.class);
        return numberofrows;
    }

    public Gendertypes getGendertypesById(long id) {
        return getGendertypesWithHistoryById(id).current;
    }

    public FdfEntity<Gendertypes> getGendertypesWithHistoryById(long id) {
        FdfEntity<Gendertypes> gendertypes = new FdfEntity<>();

        // get the test
        if(id >= 0) {
            gendertypes = this.getEntityById(Gendertypes.class, id);
        }

        return gendertypes;
    }

    public FdfEntity<Gendertypes> getgenderbynamewithhistory(String gender){
        List<FdfEntity<Gendertypes>> genderwithHistory = getEntitiesByValueForPassedField(Gendertypes.class, "name", gender);
        if(genderwithHistory.size() > 0){
            return genderwithHistory.get(0);
        }
        return null;
    }

    public Gendertypes getgenderbyname(String gender){
        FdfEntity<Gendertypes> genderwithHistory = getgenderbynamewithhistory(gender);
        if(genderwithHistory != null && genderwithHistory.current != null){
            return genderwithHistory.current;
        }
       return null;
    }

}
