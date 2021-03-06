package com.fdflib.miguelvasquezormproject.model;
import com.fdflib.annotation.FdfIgnore;
import com.fdflib.model.state.CommonState;
public class Locations extends CommonState {
    public long currentclientid = -1L; //probably don't need, might just use tenant id in the future
    public long currentlocationid = -1L;
    public long currentfloorid = -1L;
    public String name = "";
    public String description = "";
    public Double maxLat = 0.0;
    public Double maxLng = 0.0;
    public Double minLat = 0.0;
    public Double minLng = 0.0;

    @FdfIgnore
    public Client currentclient = null;
    @FdfIgnore
    public Map currentMap = null;
    @FdfIgnore
    public Locations currentlocation = null;
    @FdfIgnore
    public Floors currentfloor = null;

    public Locations(){
        super();
    }

}
