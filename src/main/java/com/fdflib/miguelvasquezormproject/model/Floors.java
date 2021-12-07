package com.fdflib.miguelvasquezormproject.model;
import com.fdflib.annotation.FdfIgnore;
import com.fdflib.model.state.CommonState;
public class Floors extends CommonState{
    public long locationid = -1L;
    public String name = "";
    public String description = "";
    public Double maxLat = 0.0;
    public Double maxLng = 0.0;
    public Double maxAlt = 0.0;
    public Double minLat = 0.0;
    @FdfIgnore
    public Locations currentlocation = null;
    @FdfIgnore
    public Client currentclient = null;
    @FdfIgnore
    public Map currentmap = null;
    public Floors(){
        super();
    }
}
