package com.fdflib.miguelvasquezormproject.model;
import com.fdflib.annotation.FdfIgnore;
import com.fdflib.model.state.CommonState;
public class User {
    public long multitenantuserid = -1 //a field to identify multiple tenants for a particular user. By defualt it will be set to the number of records + 1 when a new user is created. A seperate method called save multi tentant user will be used.
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
