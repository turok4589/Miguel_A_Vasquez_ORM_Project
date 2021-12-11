package com.fdflib.miguelvasquezormproject.model;
import com.fdflib.annotation.FdfIgnore;
import com.fdflib.model.state.CommonState;
public class UserCheckin extends CommonState{
    public long currentuserid = -1L;
    public long currentlocationid = -1L;
    public long currentfloorid = -1L;
    public long currentuserstatustypeid = -1L;
    public String description = "";

    @FdfIgnore
    public UserStatusTypes currentuserstatustype = null;
    @FdfIgnore
    public User currentuser = null;
    @FdfIgnore
    public Locations currentlocation = null;
    @FdfIgnore
    public Floors currentfloor = null;

    public UserCheckin(){
        super();
    }
}
