package com.fdflib.miguelvasquezormproject.model;
import com.fdflib.annotation.FdfIgnore;
import com.fdflib.model.state.CommonState;
public class Useraddress extends CommonState{
    public long userid = -1L; //could be used later but for now user is the tenant of this model
    public String street_address1 = "";
    public String street_address2 = "";
    public String city = "";
    public String state = "";
    public String zipcode = "";
    public String country = "";
    public String phonenumber = "";

    @FdfIgnore
    public User currentuser = null;

    public Useraddress(){
        super();
    }

}
