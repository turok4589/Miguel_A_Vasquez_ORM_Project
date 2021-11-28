package com.fdflib.miguelvasquezormproject.model;
import com.fdflib.annotation.FdfIgnore;
import com.fdflib.model.state.CommonState;
public class Client extends CommonState {
    public String name= "";
    public String description = "";
    @FdfIgnore
    public ClientRole currentrole = null;
    //self note decide when you need to make use of the tenantid
    //i have a client id variable in some of the classes, but making use of the tenant id is most likely better.
    public Client(){
        super();
    }
}
