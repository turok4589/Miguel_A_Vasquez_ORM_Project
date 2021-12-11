package com.fdflib.miguelvasquezormproject.model;
import com.fdflib.annotation.FdfIgnore;
import com.fdflib.model.state.CommonState;
public class Beacons extends CommonState {
    public String uuid = ""; //binary in mysql is similar to varchar
    public Integer majorid = 0;
    public Integer minorid = 0;
    public Integer proximityid = 0;

    @FdfIgnore
    public Client currentclient = null;

    public Beacons(){
        super();
    }
    
}
