package com.fdflib.miguelvasquezormproject.model;
import com.fdflib.annotation.FdfIgnore;
import com.fdflib.model.state.CommonState;
public class ClientRole extends CommonState{
    //public long currentclientid = -1L; //might just make use of the tenant id for this one
    public long currentroleid = -1L;
    public Boolean isactive = true;

    @FdfIgnore
    public Client currentclient = null;
    @FdfIgnore 
    public Roles roles = null;

    public ClientRole(){
        super();
    }
}
