package com.fdflib.miguelvasquezormproject.model;
import com.fdflib.annotation.FdfIgnore;
import com.fdflib.model.state.CommonState;
public class UserRole extends CommonState {
    public long currentuserid = -1L;
    public long currentroleid = -1L;
    public Boolean isactive = true;

    @FdfIgnore
    public User currentuser = null;
    @FdfIgnore 
    public Roles roles = null;

    public UserRole(){
        super();
    }
}
