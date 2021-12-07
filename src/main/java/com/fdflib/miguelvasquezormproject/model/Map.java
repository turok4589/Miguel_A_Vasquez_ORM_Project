package com.fdflib.miguelvasquezormproject.model;
import com.fdflib.annotation.FdfIgnore;
import com.fdflib.model.state.CommonState;
public class Map extends CommonState{
    public long locationid = -1L;
    public long floorid = -1L;
    public String urlpath = "";
    @FdfIgnore
    public Locations currentlocation = null;
    @FdfIgnore
    public Floors currentfloors = null;
    @FdfIgnore
    public Client currentclient = null;
    public Map(){
        super();
    }
}
