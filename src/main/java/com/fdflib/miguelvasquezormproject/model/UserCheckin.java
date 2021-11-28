package com.fdflib.miguelvasquezormproject.model;
import com.fdflib.annotation.FdfIgnore;
import com.fdflib.model.state.CommonState;
public class UserCheckin extends CommonState{
    public long currentuserid = -1L;
    public long currentuserstatustypeid = -1L;

    @FdfIgnore
    public UserStatusTypes currentuserstatustype = null;
    @FdfIgnore
    public User currentuser = null;

    public UserCheckin(){
        super();
    }
}
