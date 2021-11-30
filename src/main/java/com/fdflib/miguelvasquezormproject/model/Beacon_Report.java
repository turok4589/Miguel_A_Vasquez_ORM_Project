package com.fdflib.miguelvasquezormproject.model;
import com.fdflib.annotation.FdfIgnore;
import com.fdflib.model.state.CommonState;
public class Beacon_Report extends CommonState {
    public long currentbeaconid = -1L;
    public long currentfloorid = -1L;
    public Integer RSSI = 0;
    public Integer txpower = 0;
    public Double Accuracy = 0.0;
    public Integer Remote_Time = 0;

    @FdfIgnore
    public Beacons currentbeacon = null;
    @FdfIgnore 
    public Floors currentfloor = null;

    public Beacon_Report(){
        super();
    }
}
