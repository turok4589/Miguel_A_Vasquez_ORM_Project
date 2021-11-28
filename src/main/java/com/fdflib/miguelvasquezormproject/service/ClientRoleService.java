package com.fdflib.miguelvasquezormproject.service;
import com.fdflib.example.model.ClientRole;
import com.fdflib.example.model.Client;
import com.fdflib.miguelvasquezormproject.service.Clientservice;
import com.fdflib.model.entity.FdfEntity;
import com.fdflib.model.util.WhereClause;
import com.fdflib.persistence.FdfPersistence;
import com.fdflib.service.impl.FdfCommonServices;
import com.fdflib.model.util.SqlStatement;
import java.util.ArrayList;
import java.util.List;
public class ClientRoleService extends FdfCommonServices{
    //this save method will require the tenant id, to be passed as an arguement.
    public ClientRole saveClientRole(ClientRole clientrole, long tid, long roled){
        Clientservice cs = new Clientservice();
        if(clientrole != null && tid >= 0 && roleid >= 0){
           //check if tenant id exists
           clientrole.currentclient = cs.getClientById(tid); //id is unique so should only be one record.
           if(clientrole.currentclient != null){
              clientrole.tid = tid;
              return this.save(ClientRole.class, clientrole);
           }
        }
      return null;
    }
    //returns a list with the history using the tid.
    public List<FdfEntity<ClientRoleService>> getClientRoleServicesbytidwithhistory(long tid){
        List<FdfEntity<ClientRoleService>> clientrole = getAll(ClientRoleService.class, tid);
        return clientrole;
    }
    //clients can have multiple roles so it's better to query by the tenant id.
    //since it possibly returns multiple entries we need to return a list.
    //problem is IDK how to query the tenant
    //solution: public static <S extends CommonState> List<FdfEntity<S>> getAll(Class<S> entityState, long tenantId)
    public List<ClientRoleService> getClientRoleServicesbytid(long tid){
        List<ClientRoleService> clientrole = new ArrayList<>();
        if(tid >= 0){
            List<FdfEntity<ClientRoleService>> clientroleserviceswithhistory = getClientRoleServicesbytidwithhistory(tid);
            //check the records to make sure there are any entries
            if(clientroleserviceswithhistory.size() > 0){
               for(FdfEntity<ClientRoleService> clientrolewithhistory: clientroleserviceswithhistory){
                   if(clientrolewithhistory.current != null){
                       clientrole.add(clientrolewithhistory.current);
                   }
               }
            }
        }
      return clientrole;
    }

}
