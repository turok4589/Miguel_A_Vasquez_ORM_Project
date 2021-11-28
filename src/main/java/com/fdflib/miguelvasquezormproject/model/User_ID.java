package com.fdflib.miguelvasquezormproject.model;
import com.fdflib.annotation.FdfIgnore;
import com.fdflib.model.state.CommonState;
import java.util.Calendar;
public class User_ID extends CommonState{
    public long currentuserid = -1L;
    public long currenttypesid = -1L;
    public long currentgenderid = -1L;
    public Calendar dateissued = Calendar.getInstance();
    public Calendar expirationdate = Calendar.getInstance();
    public Calendar dateofbirth = Calendar.getInstance();
    public String eyecolor = ""; //store hex color in here
    public Integer userheight = 0; //height stored in inches
    public Integer userweight = 0; //weight stored in lbs

    @FdfIgnore
    public User currentuser = null;
    @FdfIgnore
    public Gendertypes currentgender =  null;
    @FdfIgnore
    public ID_types currentidtype = null;

    public ID(){
         super();
    }

}
