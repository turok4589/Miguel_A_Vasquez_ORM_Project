package com.fdflib.miguelvasquezormproject.service;
import com.fdflib.miguelvasquezormproject.model.ClientRole;
import com.fdflib.miguelvasquezormproject.model.Client;
import com.fdflib.miguelvasquezormproject.service.Clientservice;
import com.fdflib.miguelvasquezormproject.service.Rolesservice;
import com.fdflib.model.entity.FdfEntity;
import com.fdflib.model.util.WhereClause;
import com.fdflib.persistence.FdfPersistence;
import com.fdflib.service.impl.FdfCommonServices;
import com.fdflib.model.util.SqlStatement;
import java.util.ArrayList;
import java.util.List;
public class ClientRoleService extends FdfCommonServices{
    //this save method will require the tenant id, to be passed as an arguement.
    public ClientRole saveClientRole(ClientRole clientrole, long tid, long roleid){
        Clientservice cs = new Clientservice();
        Rolesservice rs = new Rolesservice();
        if(clientrole != null && tid >= 0 && roleid >= 0){
           //check if tenant id exists
           clientrole.currentclient = cs.getClientById(tid); //id is unique so should only be one record.
           clientrole.roles = rs.getRoleById(roleid);
           if(clientrole.currentclient != null && clientrole.roles != null){
              clientrole.tid = tid;
              return this.save(ClientRole.class, clientrole).current;
           }
        }
      return null;
    }
    //returns a list with the history using the tid.
    public List<FdfEntity<ClientRole>> getClientRoleServicesbytidwithhistory(long tid){
        List<FdfEntity<ClientRole>> clientrole = getAll(ClientRole.class, tid);
        return clientrole;
    }
    //clients can have multiple roles so it's better to query by the tenant id.
    //since it possibly returns multiple entries we need to return a list.
    //problem is IDK how to query the tenant
    //solution: public static <S extends CommonState> List<FdfEntity<S>> getAll(Class<S> entityState, long tenantId)
    public List<ClientRole> getClientRoleServicesbytid(long tid){
        List<ClientRole> clientrole = new ArrayList<>();
        if(tid >= 0){
            List<FdfEntity<ClientRole>> clientroleserviceswithhistory = getClientRoleServicesbytidwithhistory(tid);
            //check the records to make sure there are any entries
            if(clientroleserviceswithhistory.size() > 0){
               for(FdfEntity<ClientRole> clientrolewithhistory: clientroleserviceswithhistory){
                   if(clientrolewithhistory.current != null){
                       clientrole.add(clientrolewithhistory.current);
                   }
               }
            }
        }
      return clientrole;
    }

}
