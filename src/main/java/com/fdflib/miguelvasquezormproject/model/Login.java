package com.fdflib.miguelvasquezormproject.model;
import com.fdflib.annotation.FdfIgnore;
import com.fdflib.model.state.CommonState;
public class Login {
    public long userid = -1L;
    public String email = "";
    public String password = "";

    @FdfIgnore
    public User currentuser = null;
    public Login(){
        super();
    }
}
