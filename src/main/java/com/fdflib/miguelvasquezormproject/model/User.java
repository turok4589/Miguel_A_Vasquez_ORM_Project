package com.fdflib.miguelvasquezormproject.model;
import com.fdflib.annotation.FdfIgnore;
import com.fdflib.model.state.CommonState;
public class User extends CommonState{
    public long multitenantuserid = -1; //a field to identify multiple tenants for a particular user. A seperate method called save multi tentant user will be used.
    public Boolean isinmultipletables = false;
    public String firstName = "";
    public String lastName = "";
    public String color = ""; //store the hexadecimal number for the color that the user wants.

    @FdfIgnore
    public Client currentclient = null;
    @FdfIgnore
    public Login currentlogin = null;
    @FdfIgnore
    public UserRole currentrole = null;

    public User(){
        super();
    }

}
