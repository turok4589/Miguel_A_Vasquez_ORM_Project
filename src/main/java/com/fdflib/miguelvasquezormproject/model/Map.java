package com.fdflib.miguelvasquezormproject.model;
import com.fdflib.annotation.FdfIgnore;
import com.fdflib.model.state.CommonState;
public class Map extends CommonState{
    public long locationid = -1L;
    public String urlpath = "";
    @FdfIgnore
    public Locations currentlocation = null;

    public Map(){
        super();
    }
}
