package com.fdflib.miguelvasquezormproject.model;
import com.fdflib.annotation.FdfIgnore;
import com.fdflib.model.state.CommonState;
import com.fdflib.miguelvasquezormproject.service.Rolesservice;
public class Roles extends CommonState{
    public Rolenames name = null;
    public String description = ""; 

    public Roles(){
        super();
        //might decide to call the save service here.
        //as these need to be saved into the table by default. 
    }
}
