package com.fdflib.miguelvasquezormproject.service;
import com.fdflib.example.model.Client;
import com.fdflib.model.entity.FdfEntity;
import com.fdflib.model.util.WhereClause;
import com.fdflib.persistence.FdfPersistence;
import com.fdflib.service.impl.FdfCommonServices;
import com.fdflib.model.util.SqlStatement;
import java.util.ArrayList;
import java.util.List;
public class Clientservice extends FdfCommonServices{
    public Client saveClient(Client client){
        if(client != null){
            Client newclient = this.save(Client.class, client).current;
            newclient.tid = newclient.id;
            newclient = this.save(Client.class, newclient).current;
            return newclient;
        }
       return null;
    }
    public Client getClientById(long id) {
        return getClientWithHistoryById(id).current;

    }
    public FdfEntity<Client> getClientWithHistoryById(long id) {
        FdfEntity<Client> client = new FdfEntity<>();

        // get the test
        if(id >= 0) {
            client = this.getEntityById(Client.class, id);
        }

        return client;
    }
}
