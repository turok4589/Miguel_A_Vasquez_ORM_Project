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
import com.fdflib.miguelvasquezormproject.service.Userservice;
import com.fdflib.miguelvasquezormproject.service.Rolesservice;
import com.fdflib.miguelvasquezormproject.service.LoginService;
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
           //create some clients
           Client Miguel = new Client();
           Miguel.name = "Miguel Vasquez";
           Miguel.description = "Owner of Miguel's House";
           cs.saveClient(Miguel);

           Client Jeff_Bezos = new Client();
           Miguel.name = "Jeff Bezos";
           Miguel.description = "Found and chairman of Amazon";
           cs.saveClient(Jeff_Bezos);

           //create the roles table
           createrolestable(rs);
    }

    private static void createrolestable(Rolesservice rs)
    {
       Roles OmniAdministrator = new Roles();
       OmniAdministrator.name = Rolenames.OmniAdministrator; 
       OmniAdministrator.description = "Can administer any client, can also create, edit or delete clients.";
       rs.saveRole(OmniAdministrator);

       Roles ClientAdministrator = new Roles();
       ClientAdministrator.name = Rolenames.ClientAdministrator; 
       OmniAdministrator.description = "Can administer information within client account.";
       rs.saveRole(ClientAdministrator);

       Roles ClientManager = new Roles();
       ClientManager.name = Rolenames.ClientManager; 
       ClientManager.description = "Manager of client account, can perform client operations including managing maps, beacons and viewing users current and past locations.";
       rs.saveRole(ClientManager);

       Roles ClientBeaconManager = new Roles();
       ClientBeaconManager.name = Rolenames.ClientBeaconManager; 
       ClientBeaconManager.description = "Manager of client account can perform client operations including managing beacons and viewing users current locations.";
       rs.saveRole(ClientBeaconManager);

       Roles ClientMapViewer = new Roles();
       ClientMapViewer.name = Rolenames.ClientMapViewer; 
       ClientMapViewer.description = "Client account with privileges to view users current location ifnromation, can be used by school administrators and local authorities to view the current location of personal without needing to modify information.";
       rs.saveRole(ClientMapViewer);

       Roles ClientUser = new Roles();
       ClientUser.name = Rolenames.ClientUser; 
       ClientUser.description = "Basic client user account can report locations to the system but not see maps or locations.";
       rs.saveRole(ClientUser);

       long amountofrows = rs.numberofroles();
       System.out.println("Number of rows available: " + amountofrows);
  
    }
    
}