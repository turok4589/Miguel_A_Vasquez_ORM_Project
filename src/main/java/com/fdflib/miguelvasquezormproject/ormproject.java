package com.fdflib.miguelvasquezormproject;
import com.fdflib.miguelvasquezormproject.model.Client;
import com.fdflib.miguelvasquezormproject.model.Locations;
import com.fdflib.miguelvasquezormproject.model.Map;
import com.fdflib.miguelvasquezormproject.model.Floors;
import com.fdflib.miguelvasquezormproject.model.User;
import com.fdflib.miguelvasquezormproject.model.Useraddress;
import com.fdflib.miguelvasquezormproject.model.Login;
import com.fdflib.miguelvasquezormproject.model.Gendertypenames;
import com.fdflib.miguelvasquezormproject.model.Gendertypes;
import com.fdflib.miguelvasquezormproject.model.Rolenames;
import com.fdflib.miguelvasquezormproject.model.Roles;
import com.fdflib.miguelvasquezormproject.model.UserRole;
import com.fdflib.miguelvasquezormproject.model.ClientRole;
import com.fdflib.miguelvasquezormproject.model.ID_types_names;
import com.fdflib.miguelvasquezormproject.model.ID_types;
import com.fdflib.miguelvasquezormproject.model.User_ID;
import com.fdflib.miguelvasquezormproject.model.Userstatustypesname;
import com.fdflib.miguelvasquezormproject.model.UserStatusTypes;
import com.fdflib.miguelvasquezormproject.model.UserCheckin;
import com.fdflib.miguelvasquezormproject.model.Beacons;
import com.fdflib.miguelvasquezormproject.model.Beacon_Report;
import com.fdflib.miguelvasquezormproject.service.Clientservice;
import com.fdflib.miguelvasquezormproject.service.ClientRoleService;
import com.fdflib.miguelvasquezormproject.service.UserRoleService;
import com.fdflib.miguelvasquezormproject.service.Userservice;
import com.fdflib.miguelvasquezormproject.service.Rolesservice;
import com.fdflib.miguelvasquezormproject.service.LoginService;
import com.fdflib.miguelvasquezormproject.service.Useraddress;
import com.fdflib.miguelvasquezormproject.service.ID_TypesService;
import com.fdflib.miguelvasquezormproject.service.User_IDService;
import com.fdflib.miguelvasquezormproject.service.FloorsService;
import com.fdflib.miguelvasquezormproject.service.GendertypesService;
import com.fdflib.miguelvasquezormproject.service.UserStatusTypesService;
import com.fdflib.miguelvasquezormproject.service.BeaconService;
import com.fdflib.miguelvasquezormproject.service.LocationService;
import com.fdflib.miguelvasquezormproject.service.UserAddressService;
import com.fdflib.miguelvasquezormproject.service.UserCheckinService;
import com.fdflib.model.entity.FdfEntity;
import com.fdflib.persistence.database.DatabaseUtil;
import com.fdflib.service.FdfServices;
import com.fdflib.util.FdfSettings;
import java.util.ArrayList;
import java.util.List;
import java.util.Calendar;


public class ormproject {
    public static void main(String[] args){
        System.out.println("Miguel Vasquez's ORM Project");
        // initialize 4dfLib
        setOptionalSettings();
         // Create a array that will hold the classes that make up our 4df data model
         List<Class> myModel = new ArrayList<>();
         //Time to add classes to our list.
         myModel.add(Client.class);
         myModel.add(Locations.class);
         myModel.add(Map.class);
         myModel.add(Floors.class);
         myModel.add(User.class);
         myModel.add(Useraddress.class);
         myModel.add(Login.class);
         myModel.add(Gendertypes.class);
         myModel.add(Roles.class);
         myModel.add(UserRole.class);
         myModel.add(ClientRole.class);
         myModel.add(ID_types.class);
         myModel.add(User_ID.class);
         myModel.add(UserStatusTypes.class);
         myModel.add(UserCheckin.class);
         myModel.add(Beacons.class);
         myModel.add(Beacon_Report.class);

          // call the initialization of library!
         FdfServices.initializeFdfDataModel(myModel);

         // insert some demo data
        try {
            insertSomeData();
        } catch(InterruptedException e) {
            e.printStackTrace();
        }

        // do a few queries and output the results

    }

     /**
     * Everything set in this method is optional, but useful
     */
    private static void setOptionalSettings() {

        // get the 4dflib settings singleton
        FdfSettings fdfSettings = FdfSettings.getInstance();

        // set the database type and name and connection information
        // PostgreSQL settings
        //fdfSettings.PERSISTENCE = DatabaseUtil.DatabaseType.POSTGRES;
        //fdfSettings.DB_PROTOCOL = DatabaseUtil.DatabaseProtocol.JDBC_POSTGRES;

        // postgres default root user
        // root user settings are only required for initial database creation.  Once the database is created you
        // should remove this information
        //fdfSettings.DB_ROOT_USER = "postgres";

        // MySQL settings
        fdfSettings.PERSISTENCE = DatabaseUtil.DatabaseType.MYSQL;
        fdfSettings.DB_PROTOCOL = DatabaseUtil.DatabaseProtocol.JDBC_MYSQL;

        // MariaDB settings
        //fdfSettings.PERSISTENCE = DatabaseUtil.DatabaseType.MARIADB;
        //fdfSettings.DB_PROTOCOL = DatabaseUtil.DatabaseProtocol.JDBC_MARIADB;

        // MariaDB and MySQL default
        // root user settings are only required for initial database creation.  Once the database is created you
        // should remove this information
        fdfSettings.DB_ROOT_USER = "root";

        // root user password
        fdfSettings.DB_ROOT_PASSWORD = "Drat6767";

        // Database encoding
        fdfSettings.DB_ENCODING = DatabaseUtil.DatabaseEncoding.UTF8;

        // Application Database name
        fdfSettings.DB_NAME = "ORM_Project";

        // Database host
        fdfSettings.DB_HOST = "localhost";

        // Port is not required for DB defaults can be changed when needed
        // fdfSettings.DB_PORT = 3306;

        // Database user information
        fdfSettings.DB_USER = "Miguel_Vasquez_ORM_Project";
        fdfSettings.DB_PASSWORD = "ormpass";






        // set the default system information
        fdfSettings.DEFAULT_SYSTEM_NAME = "ORM Core API";
        fdfSettings.DEFAULT_SYSTEM_DESCRIPTION = "Central API service for the Mapcushion ORM Application";

        // set the default tenant information
        fdfSettings.DEFAULT_TENANT_NAME = "ORM";
        fdfSettings.DEFAULT_TENANT_DESRIPTION = "Main system Tenant";
        fdfSettings.DEFAULT_TENANT_IS_PRIMARY = true;
        fdfSettings.DEFAULT_TENANT_WEBSITE = "http://www.4dflib.com";

        // local dev, no ssl
        fdfSettings.USE_SSL = false;
        

    }

    private static void insertSomeData() throws InterruptedException {
           Clientservice cs = new Clientservice();
           Rolesservice rs = new Rolesservice();
           ClientRoleService crs = new ClientRoleService();
           Userservice us = new Userservice();
           
           //create the roles table
           createrolestable(rs);
           
           //create some clients
           
           Client Miguel = new Client();
           Miguel.name = "Miguel Vasquez";
           Miguel.description = "Owner of Miguel's House";
           Miguel = cs.saveClient(Miguel);

           Client Jeff_Bezos = new Client();
           Jeff_Bezos.name = "Jeff Bezos";
           Jeff_Bezos.description = "Found and chairman of Amazon";
           Jeff_Bezos = cs.saveClient(Jeff_Bezos);

           ClientRole clientrole = new ClientRole();
           clientrole.currentroleid = 1;
           clientrole.isactive = true;
           crs.saveClientRole(clientrole, Miguel.id, clientrole.currentroleid);
           
           clientrole.id = -1;
           clientrole.currentroleid = 7;
           crs.saveClientRole(clientrole, Miguel.id, clientrole.currentroleid);
         
           clientrole.currentroleid = 11;
           clientrole.id = -1;
           crs.saveClientRole(clientrole, Miguel.id, clientrole.currentroleid);

           clientrole.currentroleid = 16;
           clientrole.id = -1;
           crs.saveClientRole(clientrole, Miguel.id, clientrole.currentroleid);

           clientrole.currentroleid = 2;
           clientrole.id = -1;
           crs.saveClientRole(clientrole, Jeff_Bezos.id, clientrole.currentroleid);

           clientrole.currentroleid = 12;
           clientrole.id = -1;
           crs.saveClientRole(clientrole, Jeff_Bezos.id, clientrole.currentroleid);

           clientrole.currentroleid = 17;
           clientrole.id = -1;
           crs.saveClientRole(clientrole, Jeff_Bezos.id, clientrole.currentroleid);

           List<FdfEntity<Client>> clientstable = cs.getallclients();
           List<FdfEntity<ClientRole>> clientroletable = new ArrayList<>();
           //output all clients
           System.out.println("Clients in clients table: ");
           if(clientstable.size() > 0){
               for(FdfEntity<Client> idsclient: clientstable){
                  System.out.println("Client id: " + idsclient.current.id);
                  System.out.println("Client name: " + idsclient.current.name);
                  System.out.println("Client description: " + idsclient.current.description);
                  System.out.println("All Roles for client: " + "\n");
                  clientroletable = crs.getClientRoleServicesbytidwithhistory(idsclient.current.id);
                  if(clientroletable.size() > 0){
                    for(FdfEntity<ClientRole> idsrole: clientroletable){
                        System.out.println("Role id: " + idsrole.current.id);
                        idsrole.current.roles = rs.getRoleById(idsrole.current.currentroleid);
                        System.out.println("Role name: " + idsrole.current.roles.name);
                        System.out.println("Is this role active: " + idsrole.current.isactive + "\n");
                    }
                  }
                  System.out.println("------------------------------------------------------");
                  
               }
           }

           //create users

           User bob = new User();
           username = "Bob123";
           firstName = "Bob";
           lastName = "Williams";
           color = "#ff0000";
           bob = us.saveuserversion2(bob, Miguel.id);

           User james = new User();
           username "James1234";
           firstName = "James";
           lastName = "Smith";
           color = "00ffff";
           james = us.saveuserversion2(james, Miguel.id);

           User robert = new User();
           username = "Robert321";
           firstName = "Robert";
           lastName = "Johnson";
           color = "#6699ff";
           robert = us.saveuserversion2(robert, Miguel.id);

           User Micheal = new User();
           username = "Micheal123456";
           firstName = "Micheal";
           lastName = "Williams";
           color = "#ffff00";
           Micheal = us.saveuserversion2(Micheal, Miguel.id);

           User David = new User();
           username = "David1234";
           firstName = "David";
           lastName = "Jones";
           color = "#ff3300";
           David = us.saveuserversion2(David, Miguel.id);

           User jones = new User();
           username = "Jones12345";
           firstName = "Jones";
           lastName = "Thomas";
           color = "#cc00ff";
           jones = us.saveuserversion2(jones, Jeff_Bezos.id);

           User Garcia = new User();
           username = "Garcia5422!";
           firstName = "Garica";
           lastName = "Paul";
           color = "#3366cc";
           Garcia = us.saveuserversion2(Garcia, Jeff_Bezos.id);
    }

    private static void createrolestable(Rolesservice rs)
    {
        //1
       Roles OmniAdministrator = new Roles();
       OmniAdministrator.name = Rolenames.OmniAdministrator; 
       OmniAdministrator.description = "Can administer any client, can also create, edit or delete clients.";
       rs.saveRole(OmniAdministrator);
       //2
       Roles ClientAdministrator = new Roles();
       ClientAdministrator.name = Rolenames.ClientAdministrator; 
       OmniAdministrator.description = "Can administer information within client account.";
       rs.saveRole(ClientAdministrator);
       //3
       Roles ClientManager = new Roles();
       ClientManager.name = Rolenames.ClientManager; 
       ClientManager.description = "Manager of client account, can perform client operations including managing maps, beacons and viewing users current and past locations.";
       rs.saveRole(ClientManager);
       //4
       Roles ClientBeaconManager = new Roles();
       ClientBeaconManager.name = Rolenames.ClientBeaconManager; 
       ClientBeaconManager.description = "Manager of client account can perform client operations including managing beacons and viewing users current locations.";
       rs.saveRole(ClientBeaconManager);
       //5
       Roles ClientMapViewer = new Roles();
       ClientMapViewer.name = Rolenames.ClientMapViewer; 
       ClientMapViewer.description = "Client account with privileges to view users current location ifnromation, can be used by school administrators and local authorities to view the current location of personal without needing to modify information.";
       rs.saveRole(ClientMapViewer);
       //6
       Roles ClientUser = new Roles();
       ClientUser.name = Rolenames.ClientUser; 
       ClientUser.description = "Basic client user account can report locations to the system but not see maps or locations.";
       rs.saveRole(ClientUser);
       //7
       Roles CEO = new Roles();
       CEO.name = Rolenames.CEO;
       CEO.description = "Custom role for ceo of the company";
       rs.saveRole(CEO);
       //8
       Roles Partner = new Roles();
       Partner.name = Rolenames.Programmer;
       Partner.description = "Custom role for a parner of the company";
       rs.saveRole(Partner);
       //9
       Roles Manager = new Roles();
       Manager.name = Rolenames.Manager;
       Manager.description = "Custom role for a manager";
       rs.saveRole(Manager);
       //10
       Roles Vice_President = new Roles();
       Vice_President.name = Rolenames.Vice_President;
       Vice_President.description = "Custom role a vice president";
       rs.saveRole(Vice_President);
       //11
       Roles Teacher = new Roles();
       Teacher.name = Rolenames.Teacher;
       Teacher.description = "Custom role for a teacher";
       rs.saveRole(Teacher);
       //12
       Roles Programmer = new Roles();
       Programmer.name = Rolenames.Programmer;
       Programmer.description = "Custom role for a programmer";
       rs.saveRole(Programmer);
       //13
       Roles Waterboy = new Roles();
       Waterboy.name = Rolenames.Waterboy;
       Waterboy.description = "Custom role for someone who refills the water in every room";
       rs.saveRole(Waterboy);
       //14
       Roles Chef = new Roles();
       Chef.name = Rolenames.Chef;
       Chef.description = "Custom role for the chef in the building";
       rs.saveRole(Chef);
       //15
       Roles Frontdesk = new Roles();
       Frontdesk.name = Rolenames.Frontdesk;
       Frontdesk.description = "Custom role for a frontdesk worker";
       rs.saveRole(Frontdesk);
       //16
       Roles AnimalLover = new Roles();
       AnimalLover.name = Rolenames.AnimalLover;
       AnimalLover.description = "Custom role an animal lover";
       rs.saveRole(AnimalLover);
       //17
       Roles Founder = new Roles();
       Founder.name = Rolenames.Founder;
       Founder.description = "Custom role for the founder of the company";
       rs.saveRole(Founder);

       long amountofrows = rs.numberofroles();
       System.out.println("Number of rows available: " + amountofrows);
  
    }
    
}