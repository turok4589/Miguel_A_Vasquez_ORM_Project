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
           Useraddress ua = new UserAddressService();
           UserRoleService urs = new UserRoleService();
           ID_TypesService its = new ID_TypesService();
           User_IDService ids = new User_IDService();
           UserStatusTypesService ust = new UserStatusTypesService();
           GendertypesService gts = new GendertypesService();
           
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

           Thread.sleep(3000);

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
           
           Thread.sleep(3000);

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
           Thread.sleep(3000);
           //create users

           User bob = new User();
           bob.username = "Bob123";
           bob.firstName = "Bob";
           bob.lastName = "Williams";
           bob.color = "#ff0000";
           bob = us.saveuserversion2(bob, Miguel.id);

           User james = new User();
           james.username "James1234";
           james.firstName = "James";
           james.lastName = "Smith";
           james.color = "00ffff";
           james = us.saveuserversion2(james, Miguel.id);

           User robert = new User();
           robert.username = "Robert321";
           robert.firstName = "Robert";
           robert.lastName = "Johnson";
           robert.color = "#6699ff";
           robert = us.saveuserversion2(robert, Miguel.id);

           User Micheal = new User();
           Micheal.username = "Micheal123456";
           Micheal.firstName = "Micheal";
           Micheal.lastName = "Williams";
           Micheal.color = "#ffff00";
           Micheal = us.saveuserversion2(Micheal, Miguel.id);

           User David = new User();
           David.username = "David1234";
           David.firstName = "David";
           David.lastName = "Jones";
           David.color = "#ff3300";
           David = us.saveuserversion2(David, Miguel.id);

           User Donald = new User();
           Donald.username = "Daisey1234";
           Donald.firstName = "Donald";
           Donald.lastName = "Duck";
           Donald.color  = "#ff9933";
           Donald = us.saveuserversion2(Donald, Miguel.id);

           User Mickey = new User();
           Mickey.username = "Minnie124";
           Mickey.firstName = "Mickey";
           Mickey.lastName = "Mouse";
           Mickey.color  = "#9966ff";
           Mickey = us.saveuserversion2(Mickey, Miguel.id);

           User paul = new user();
           paul.username = "Paul124";
           paul.firstName = "Paul";
           paul.lastName = "Wilson";
           paul.color = "#66ffff";
           paul = us.saveuserversion2(paul, Miguel.id);

           User Andrew = new user();
           Andrew.username = "Andrew1234";
           Andrew.firstName = "Andrew";
           Andrew.lastName = "Thomas";
           Andrew.color = "#cc99ff";
           Andrew = us.saveuserversion2(Andrew, Miguel.id);

           User Joshua = new user();
           Joshua.username = "Joshua565";
           Joshua.firstName = "Joshua";
           Joshua.lastName = "Suero";
           Joshua.color = "#66ff33";
           Joshua = us.saveuserversion2(Joshua, Miguel.id);


           User jones = new User();
           jones.username = "Jones12345";
           jones.firstName = "Jones";
           jones.lastName = "Thomas";
           jones.color = "#cc00ff";
           jones = us.saveuserversion2(jones, Jeff_Bezos.id);

           User Garcia = new User();
           Garcia.username = "Garcia5422!";
           Garcia.firstName = "Garica";
           Garcia.lastName = "Paul";
           Garcia.color = "#3366cc";
           Garcia = us.saveuserversion2(Garcia, Jeff_Bezos.id);
           
           User Jason = new User();
           Jason.username = "Jason21!";
           Jason.firstName = "Jason";
           Jason.lastName = "Edward";
           Jason.color = "#ff5050";
           Jason = us.saveuserversion2(Jason, Jeff_Bezos.id);

           User Brian = new User();
           Brian.username = "Bryanp123";
           Brian.firstName = "Bryan";
           Brian.lastName = "Palacios";
           Brian.color = "#ccff66";
           Brian = us.saveuserversion2(Brian, Jeff_Bezos.id);

           User Ronald = new User();
           Ronald.username = "Ronald143!";
           Ronald.firstName = "Ronald";
           Ronald.lastName = "Lee";
           Ronald.color = "#66ccff";
           Ronald = us.saveuserversion2(Ronald, Jeff_Bezos.id);

           User Timothy = new User();
           Timothy.username = "Timothy233!";
           Timothy.firstName "Timothy";
           Timothy.lastName = "Moore";
           Timothy.color = "#996633";
           Timothy = us.saveuserversion2(Timothy, Jeff_Bezos.id);

           User Jeffrey = new User();
           Jeffrey.username = "Jeffreyyyyy12";
           Jeffrey.firstName = "Jeffrey21";
           Jeffrey.lastName = "Jackson";
           Jeffrey.color = "#ff66cc";
           Jeffrey = us.saveuserversion2(Jeffrey, Jeff_Bezos.id);

           User Ryan = new User();
           Ryan.username = "Ryan134!";
           Ryan.firstName = "Ryan";
           Ryan.lastName = "Garcia";
           Ryan.color = "#3399ff";
           Ryan = us.saveuserversion2(Ryan, Jeff_Bezos.id);

           User Eric = new User();
           Eric.username = "Eric241!";
           Eric.firstName = "Eric";
           Eric.lastName = "Vasquez";
           Eric.color = "#66ff99";
           Eric = us.saveuserversion2(Eric, Jeff_Bezos.id);

           Thread.sleep(3000);

           //multi tenant has a seperate entity id
           //test field known as multitenantuserid is meant to connect multi tenant users together, but haven't tested.
           User bob2 = new User();
           bob2.username = "Bob123";
           bob2.firstName = "Bob";
           bob2.lastName = "Williams";
           bob2.color = "#ff0000";
           bob2 = us.saveuserversion2(bob2, Jeff_Bezos.id);

           //updating 5 users to fill fill requirements 3 from tenant 1, 2 from tenant 2
           //We can get the userinformation with getCurrentUserbyusername

           User James2 = us.getCurrentUserbyusername("Jason21!", Miguel.id);
           //username is a unique field so there will be no duplicates inside of this tenant
           //You can update every field besides the username otherwise it won't update it correctly
           James2.lastName = "Jara";
           James2.color = "#000099";
           James2 = us.saveuserversion2(James2, James2.tid);

           User Mickey2 = us.getCurrentUserbyusername("Minnie124", Miguel.id);
           Mickey2.firstName = "Minnie";
           Mickey2.color = "#ff3399";
           Mickey2 = us.saveuserversion2(Mickey2, Mickey2.tid);

           User Donald2 = us.getCurrentUserbyusername("Daisey1234", Miguel.id);
           Donald2.firstName = "Daisey";
           Donald2.color = "#3399ff";
           Donald2 = us.saveuserversion2(Donald2, Donald2.tid);

           User Eric2 = us.getCurrentUserbyusername("Eric241!", Miguel.id);
           Eric2.firstName = "Jonathan";
           Eric2.color = "#ffffcc";
           Eric2 = us.saveuserversion2(Eric2, Eric2.tid);

           User Jeffrey2 = us.getCurrentUserbyusername("Jeffreyyyyy12", Miguel.id);
           Jeffrey2.firstName = "Samuel";
           Jeffrey2.lastName = "Klien";
           Jeffrey2.color = "#ff0066";
           Jeffrey2 = us.saveuserversion2(Jeffrey2, Jeffrey2.tid);

           Thread.sleep(3000);

           //add user address information

           User useraddress = new Useraddress();
           useraddress.street_address1 = "466 Fieldstone ST";
           useraddress.city = "Mishawaka";
           useraddress.state = "Indiana";
           useraddress.zipcode = "46544";
           useraddress.country = "United States";
           useraddress.phonenumber = "908-5251";
           useraddress = ua.saveUseraddress(useraddress, bob.id, bob.tid);

           User useraddress = new Useraddress();
           useraddress.id = -1;
           useraddress.street_address1 = "478 Somerset St";
           useraddress.city = "North Wales";
           useraddress.state = "Pennsylvania";
           useraddress.zipcode = "19454";
           useraddress.country = "United States";
           useraddress.phonenumber = "245-3706";
           useraddress = ua.saveUseraddress(useraddress, james.id, james.tid);

           User useraddress = new Useraddress();
           useraddress.id = -1;
           useraddress.street_address1 = "492 West Beaver Ridge Street";
           useraddress.city = "Matawan";
           useraddress.state = "New Jersey";
           useraddress.zipcode = "07747";
           useraddress.country = "United States";
           useraddress.phonenumber = "841-1684";
           useraddress = ua.saveUseraddress(useraddress, robert.id, robert.tid);

           User useraddress = new Useraddress();
           useraddress.id = -1;
           useraddress.street_address1 = "1 Ohio St.";
           useraddress.city = "Selden";
           useraddress.state = "New York";
           useraddress.zipcode = "11784";
           useraddress.country = "United States";
           useraddress.phonenumber = "935-8961";
           useraddress = ua.saveUseraddress(useraddress, Micheal.id, Micheal.tid);
           
           User useraddress = new Useraddress();
           useraddress.id = -1;
           useraddress.street_address1 = "47 Hillside Dr";
           useraddress.city = "Doylestown";
           useraddress.state = "Pennsylvania";
           useraddress.zipcode = "18901";
           useraddress.country = "United States";
           useraddress.phonenumber = "666-8331";
           useraddress = ua.saveUseraddress(useraddress, David.id, David.tid);

           User useraddress = new Useraddress();
           useraddress.id = -1;
           useraddress.street_address1 = "7712 Sleepy Hollow St.";
           useraddress.city = "Gallatin";
           useraddress.state = "Tennessee";
           useraddress.zipcode = "37066";
           useraddress.country = "United States";
           useraddress.phonenumber = "318-1169";
           useraddress = ua.saveUseraddress(useraddress, Donald.id, Donald.tid);

           User useraddress = new Useraddress();
           useraddress.id = -1;
           useraddress.street_address1 = "62 Elizabeth Road";
           useraddress.city = "Wantagh";
           useraddress.state = "New York";
           useraddress.zipcode = "11793";
           useraddress.country = "United States";
           useraddress.phonenumber = "846-2799";
           useraddress = ua.saveUseraddress(useraddress, Mickey.id, Mickey.tid);

           User useraddress = new Useraddress();
           useraddress.id = -1;
           useraddress.street_address1 = "249 Hanover St.";
           useraddress.city = "Bloomfield";
           useraddress.state = "New Jersey";
           useraddress.zipcode = "07003";
           useraddress.country = "United States";
           useraddress.phonenumber = "523-3134";
           useraddress = ua.saveUseraddress(useraddress, paul.id, paul.tid);
           
           User useraddress = new Useraddress();
           useraddress.id = -1;
           useraddress.street_address1 = "7282 SE. Strawberry Rd.";
           useraddress.city = "Williamsport";
           useraddress.state = "Pennsylvania";
           useraddress.zipcode = "17701";
           useraddress.country = "United States";
           useraddress.phonenumber = "813-5007";
           useraddress = ua.saveUseraddress(useraddress, Andrew.id, Andrew.tid);

           User useraddress = new Useraddress();
           useraddress.id = -1;
           useraddress.street_address1 = "904 Wakehurst Ave.";
           useraddress.city = "Revere";
           useraddress.state = "Massachusetts";
           useraddress.zipcode = "02151";
           useraddress.country = "United States";
           useraddress.phonenumber = "694-2075";
           useraddress = ua.saveUseraddress(useraddress, Joshua.id, Joshua.tid);

           User useraddress = new Useraddress();
           useraddress.id = -1;
           useraddress.street_address1 = "31 Bedford Drive";
           useraddress.city = "Pensacola";
           useraddress.state = "Florida";
           useraddress.zipcode = "32503";
           useraddress.country = "United States";
           useraddress.phonenumber = "523-4980";
           useraddress = ua.saveUseraddress(useraddress, jones.id, jones.tid);

           User useraddress = new Useraddress();
           useraddress.id = -1;
           useraddress.street_address1 = "963 W. Williams Circle";
           useraddress.city = "Huntsville";
           useraddress.state = "Alabama";
           useraddress.zipcode = "35803";
           useraddress.country = "United States";
           useraddress.phonenumber = "697-0695";
           useraddress = ua.saveUseraddress(useraddress, Garcia.id, Garcia.tid);

           User useraddress = new Useraddress();
           useraddress.id = -1;
           useraddress.street_address1 = "751 Illinois Lane";
           useraddress.city = "Oklahoma City";
           useraddress.state = "Oklahoma";
           useraddress.zipcode = "73112";
           useraddress.country = "United States";
           useraddress.phonenumber = "675-8185";
           useraddress = ua.saveUseraddress(useraddress, Jason.id, Jason.tid);

           User useraddress = new Useraddress();
           useraddress.id = -1;
           useraddress.street_address1 = "8922 Roberts Circle";
           useraddress.street_address2 = "Apt 47";
           useraddress.city = "Rego Park";
           useraddress.state = "New York";
           useraddress.zipcode = "11374";
           useraddress.country = "United States";
           useraddress.phonenumber = "495-6541";
           useraddress = ua.saveUseraddress(useraddress, Brian.id, Brian.tid);

           User useraddress = new Useraddress();
           useraddress.id = -1;
           useraddress.street_address1 = "9630 Plymouth Ave.";
           useraddress.city = "Bedford";
           useraddress.state = "Ohio";
           useraddress.zipcode = "44146";
           useraddress.country = "United States";
           useraddress.phonenumber = "448-6188";
           useraddress = ua.saveUseraddress(useraddress, Ronald.id, Ronald.tid);

           User useraddress = new Useraddress();
           useraddress.id = -1;
           useraddress.street_address1 = "8365 Rock Maple Dr.";
           useraddress.city = "Lincoln Park";
           useraddress.state = "Michigan";
           useraddress.zipcode = "48146";
           useraddress.country = "United States";
           useraddress.phonenumber = "943-8555";
           useraddress = ua.saveUseraddress(useraddress, Timothy.id, Timothy.tid);

           User useraddress = new Useraddress();
           useraddress.id = -1;
           useraddress.street_address1 = "4 West Westport Lane";
           useraddress.city = "Enfield";
           useraddress.state = "Connecticut";
           useraddress.zipcode = "06082";
           useraddress.country = "United States";
           useraddress.phonenumber = "366-4804";
           useraddress = ua.saveUseraddress(useraddress, Jeffrey.id, Jeffrey.tid);

           User useraddress = new Useraddress();
           useraddress.id = -1;
           useraddress.street_address1 = "7477 NE. Brook Lane";
           useraddress.city = "Smithtown";
           useraddress.state = "New York";
           useraddress.zipcode = "11787";
           useraddress.country = "United States";
           useraddress.phonenumber = "293-3588";
           useraddress = ua.saveUseraddress(useraddress, Ryan.id, Ryan.tid);

           User useraddress = new Useraddress();
           useraddress.id = -1;
           useraddress.street_address1 = "976 W. Hilltop St.";
           useraddress.city = "Findlay";
           useraddress.state = "Ohio";
           useraddress.zipcode = "45840";
           useraddress.country = "United States";
           useraddress.phonenumber = "522-2089";
           useraddress = ua.saveUseraddress(useraddress, Eric.id, Eric.tid);

           User useraddress = new Useraddress();
           useraddress.street_address1 = "466 Fieldstone ST";
           useraddress.city = "Mishawaka";
           useraddress.state = "Indiana";
           useraddress.zipcode = "46544";
           useraddress.country = "United States";
           useraddress.phonenumber = "908-5251";
           useraddress = ua.saveUseraddress(useraddress, bob2.id, bob2.tid);
           Thread.sleep(3000);
           //add User Role Services

           //bobs roles
           UserRole userrole = new UserRole();
           userrole.currentroleid = 1;
           urs.saveUserRole(userrole, bob.id, userrole.currentroleid, bob.tid);

           userrole.id = -1;
           userrole.currentroleid = 8;
           urs.saveUserRole(userrole, bob.id, userrole.currentroleid, bob.tid);

           userrole.id = -1;
           userrole.currentroleid = 21;
           urs.saveUserRole(userrole, bob.id, userrole.currentroleid, bob.tid);

           //bob is a multiclient who has different roles in a different location
           userrole.id = -1;
           userrole.currentroleid = 1;
           urs.saveUserRole(userrole, bob2.id, userrole.currentroleid, bob2.tid);

           userrole.id = -1;
           userrole.currentroleid = 8;
           urs.saveUserRole(userrole, bob2.id, userrole.currentroleid, bob2.tid);

           userrole.id = -1;
           userrole.currentroleid = 21;
           urs.saveUserRole(userrole, bob2.id, userrole.currentroleid, bob2.tid);

           //james roles - changes lastname to Jara
           userrole.id = -1;
           userrole.currentroleid = 2;
           urs.saveUserRole(userrole, james.id, userrole.currentroleid, james.tid);

           userrole.id = -1;
           userrole.currentroleid = 9;
           urs.saveUserRole(userrole, james.id, userrole.currentroleid, james.tid);

           userrole.id = -1;
           userrole.currentroleid = 22;
           urs.saveUserRole(userrole, james.id, userrole.currentroleid, james.tid);

           //Robert roles
           userrole.id = -1;
           userrole.currentroleid = 3;
           urs.saveUserRole(userrole, robert.id, userrole.currentroleid, robert.tid);

           userrole.id = -1;
           userrole.currentroleid = 10;
           urs.saveUserRole(userrole, robert.id, userrole.currentroleid, robert.tid);

           userrole.id = -1;
           userrole.currentroleid = 23;
           urs.saveUserRole(userrole, robert.id, userrole.currentroleid, robert.tid);
           
           //Micheal Roles
           userrole.id = -1;
           userrole.currentroleid = 4;
           urs.saveUserRole(userrole, Micheal.id, userrole.currentroleid, Micheal.tid);

           userrole.id = -1;
           userrole.currentroleid = 12;
           urs.saveUserRole(userrole, Micheal.id, userrole.currentroleid, Micheal.tid);

           userrole.id = -1;
           userrole.currentroleid = 24;
           urs.saveUserRole(userrole, Micheal.id, userrole.currentroleid, Micheal.tid);

           //David Roles
           userrole.id = -1;
           userrole.currentroleid = 5;
           urs.saveUserRole(userrole, David.id, userrole.currentroleid, David.tid);

           userrole.id = -1;
           userrole.currentroleid = 18;
           urs.saveUserRole(userrole, David.id, userrole.currentroleid, David.tid);

           //Donald Roles
           userrole.id = -1;
           userrole.currentroleid = 6;
           urs.saveUserRole(userrole, Donald.id, userrole.currentroleid, Donald.tid);

           userrole.id = -1;
           userrole.currentroleid = 11;
           urs.saveUserRole(userrole, David.id, userrole.currentroleid, David.tid);
           
           //Mickey Roles
           userrole.id = -1;
           userrole.currentroleid = 6;
           urs.saveUserRole(userrole, Mickey.id, userrole.currentroleid, Mickey.tid);

           userrole.id = -1;
           userrole.currentroleid = 13;
           urs.saveUserRole(userrole, Mickey.id, userrole.currentroleid, Mickey.tid);

           userrole.id = -1;
           userrole.currentroleid = 20;
           urs.saveUserRole(userrole, Mickey.id, userrole.currentroleid, Mickey.tid);

           //Paul Roles
           userrole.id = -1;
           userrole.currentroleid = 6;
           urs.saveUserRole(userrole, paul.id, userrole.currentroleid, paul.tid);

           userrole.id = -1;
           userrole.currentroleid = 14;
           urs.saveUserRole(userrole, paul.id, userrole.currentroleid, paul.tid);

           //Andrew Roles
           userrole.id = -1;
           userrole.currentroleid = 6;
           urs.saveUserRole(userrole, Andrew.id, userrole.currentroleid, Andrew.tid);

           userrole.id = -1;
           userrole.currentroleid = 15;
           urs.saveUserRole(userrole, Andrew.id, userrole.currentroleid, Andrew.tid);

           //Joshua Roles
           userrole.id = -1;
           userrole.currentroleid = 6;
           urs.saveUserRole(userrole, Joshua.id, userrole.currentroleid, Joshua.tid);

           userrole.id = -1;
           userrole.currentroleid = 16;
           urs.saveUserRole(userrole, Joshua.id, userrole.currentroleid, Joshua.tid);

           userrole.id = -1;
           userrole.currentroleid = 19;
           urs.saveUserRole(userrole, Joshua.id, userrole.currentroleid, Joshua.tid);
          
           Thread.sleep(3000);
           
           //jones Roles

           userrole.id = -1;
           userrole.currentroleid = 2;
           urs.saveUserRole(userrole, jones.id, userrole.currentroleid, jones.tid);

           userrole.id = -1;
           userrole.currentroleid = 9;
           urs.saveUserRole(userrole, jones.id, userrole.currentroleid, jones.tid);

           userrole.id = -1;
           userrole.currentroleid = 17;
           urs.saveUserRole(userrole, jones.id, userrole.currentroleid, jones.tid);

           //Garcia Roles
           userrole.id = -1;
           userrole.currentroleid = 3;
           urs.saveUserRole(userrole, Garcia.id, userrole.currentroleid, Garcia.tid);

           userrole.id = -1;
           userrole.currentroleid = 10;
           urs.saveUserRole(userrole, Garcia.id, userrole.currentroleid, Garcia.tid);

           userrole.id = -1;
           userrole.currentroleid = 23;
           urs.saveUserRole(userrole, Garcia.id, userrole.currentroleid, Garcia.tid);
           
           //Jason Roles
           userrole.id = -1;
           userrole.currentroleid = 4;
           urs.saveUserRole(userrole, Jason.id, userrole.currentroleid, Jason.tid);

           userrole.id = -1;
           userrole.currentroleid = 12;
           urs.saveUserRole(userrole, Jason.id, userrole.currentroleid, Jason.tid);

           userrole.id = -1;
           userrole.currentroleid = 24;
           urs.saveUserRole(userrole, Jason.id, userrole.currentroleid, Jason.tid);

           //Brian Roles
           userrole.id = -1;
           userrole.currentroleid = 5;
           urs.saveUserRole(userrole, Brian.id, userrole.currentroleid, Brian.tid);

           userrole.id = -1;
           userrole.currentroleid = 18;
           urs.saveUserRole(userrole, Brian.id, userrole.currentroleid, Brian.tid);


           //Ronald Roles
           userrole.id = -1;
           userrole.currentroleid = 6;
           urs.saveUserRole(userrole, Ronald.id, userrole.currentroleid, Ronald.tid);

           userrole.id = -1;
           userrole.currentroleid = 11;
           urs.saveUserRole(userrole, Ronald.id, userrole.currentroleid, Ronald.tid);

           //Timothy Roles
           userrole.id = -1;
           userrole.currentroleid = 6;
           urs.saveUserRole(userrole, Timothy.id, userrole.currentroleid, Timothy.tid);

           userrole.id = -1;
           userrole.currentroleid = 13;
           urs.saveUserRole(userrole, Timothy.id, userrole.currentroleid, Timothy.tid);

           userrole.id = -1;
           userrole.currentroleid = 20;
           urs.saveUserRole(userrole, Timothy.id, userrole.currentroleid, Timothy.tid);

           //Jeffrey Roles
           userrole.id = -1;
           userrole.currentroleid = 6;
           urs.saveUserRole(userrole, Jeffrey.id, userrole.currentroleid, Jeffrey.tid);

           userrole.id = -1;
           userrole.currentroleid = 14;
           urs.saveUserRole(userrole, Jeffrey.id, userrole.currentroleid, Jeffrey.tid);

           //Ryan Roles
           userrole.id = -1;
           userrole.currentroleid = 6;
           urs.saveUserRole(userrole, Ryan.id, userrole.currentroleid, Ryan.tid);

           userrole.id = -1;
           userrole.currentroleid = 15;
           urs.saveUserRole(userrole, Ryan.id, userrole.currentroleid, Ryan.tid);

           //Eric Roles
           userrole.id = -1;
           userrole.currentroleid = 6;
           urs.saveUserRole(userrole, Eric.id, userrole.currentroleid, Eric.tid);

           userrole.id = -1;
           userrole.currentroleid = 16;
           urs.saveUserRole(userrole, Eric.id, userrole.currentroleid, Eric.tid);

           userrole.id = -1;
           userrole.currentroleid = 19;
           urs.saveUserRole(userrole, Eric.id, userrole.currentroleid, Eric.tid);

           Thread.sleep(3000);
           createidtypestable(its);
           Thread.sleep(3000);
           createuserstatustables(ust);
           Thread.sleep(3000);
           creategendertypes(gts);

           //add User IDS
           //adding bob's id
           User_ID bobsid = new User_ID();
           long genderid = gts.getgenderbyname("Male").id; //might not work
           long typeid = its.getID_typesbyname("Drivers_License").id;
           bobsid.idnumber = "069329569";
           bobsid.dateissued.set(2019, 1, 12);
           bobsid.expirationdate.set(2024, 1, 12);
           bobsid.dateofbirth.set(1997, 7, 19);
           bobsid.eyecolor = "#0000ff";
           bobsid.userheight = 71; //height in inches
           bobsid.userweight = 180; //weight in lbs
           bobsid = ids.saveUser_ID(bobsid, bob.id, bob.tid, genderid, typeid);

           User_ID bobspassport = new User_ID();
           genderid = gts.getgenderbyname("Male").id; //might not work
           typeid = its.getID_typesbyname("Passport").id;
           bobspassport.idnumber = "229621875";
           bobspassport.dateissued.set(2014, 1, 12);
           bobspassport.expirationdate.set(2023, 1, 11);
           bobspassport.dateofbirth.set(1997, 7, 19);
           bobspassport.eyecolor = "#0000ff";
           bobspassport.userheight = 71; //height in inches
           bobspassport.userweight = 180; //weight in lbs
           bobsid = ids.saveUser_ID(bobspassport, bob.id, bob.tid, genderid, typeid);

           User_ID bobsid2 = new User_ID();
           genderid = gts.getgenderbyname("Male").id; //might not work
           typeid = its.getID_typesbyname("Drivers_License").id;
           bobsid2.idnumber = "069329569";
           bobsid2.dateissued.set(2019, 1, 12);
           bobsid2.expirationdate.set(2024, 1, 12);
           bobsid2.dateofbirth.set(1997, 7, 19);
           bobsid2.eyecolor = "#0000ff";
           bobsid2.userheight = 71; //height in inches
           bobsid2.userweight = 180; //weight in lbs
           bobsid2 = ids.saveUser_ID(bobsid2, bob2.id, bob2.tid, genderid, typeid);

           User_ID bobspassport2 = new User_ID();
           genderid = gts.getgenderbyname("Male").id; //might not work
           typeid = its.getID_typesbyname("Passport").id;
           bobspassport2.idnumber = "229621875";
           bobspassport.dateissued.set(2014, 1, 12);
           bobspassport.expirationdate.set(2023, 1, 11);
           bobspassport.dateofbirth.set(1997, 7, 19);
           bobspassport.eyecolor = "#0000ff";
           bobspassport.userheight = 71; //height in inches
           bobspassport.userweight = 180; //weight in lbs
           bobsid2 = ids.saveUser_ID(bobspassport2, bob2.id, bob2.tid, genderid, typeid);
           
           //James ids
           User_ID jamesid = new User_ID();
           genderid = gts.getgenderbyname("Male").id; //might not work
           typeid = its.getID_typesbyname("Drivers_License").id;
           jamesid.idnumber = "084969244";
           jamesid.dateissued.set(2020, 5, 22);
           jamesid.expirationdate.set(2025, 5, 22);
           jamesid.dateofbirth.set(1995, 2, 9);
           jamesid.eyecolor = "#663300";
           jamesid.userheight = 71; //height in inches
           jamesid.userweight = 205; //weight in lbs
           jamesid = ids.saveUser_ID(jamesid, james.id, james.tid, genderid, typeid);

           User_ID jamespassport = new User_ID();
           genderid = gts.getgenderbyname("Male").id; //might not work
           typeid = its.getID_typesbyname("Passport").id;
           jamespassport.idnumber = "229621875";
           jamespassport.dateissued.set(2015, 5, 12);
           jamespassport.expirationdate.set(2024, 5, 11);
           jamespassport.dateofbirth.set(1995, 2, 9);
           jamespassport.eyecolor = "#663300";
           jamespassport.userheight = 71; //height in inches
           jamespassport.userweight = 205; //weight in lbs
           jamespassport = ids.saveUser_ID(jamespassport, james.id, james.tid, genderid, typeid);
           
           //Robert id
           User_ID Robertid = new User_ID();
           genderid = gts.getgenderbyname("Male").id; //might not work
           typeid = its.getID_typesbyname("Drivers_License").id;
           Robertid.idnumber = "828498132";
           Robertid.dateissued.set(2019, 11, 29);
           Robertid.expirationdate.set(2024, 11, 29);
           Robertid.dateofbirth.set(1986, 7, 19);
           Robertid.eyecolor = "#00ffcc";
           Robertid.userheight = 75; //height in inches
           Robertid.userweight = 210; //weight in lbs
           Robertid = ids.saveUser_ID(Robertid, robert.id, robert.tid, genderid, typeid);
           
           User_ID Robertpassport = new User_ID();
           genderid = gts.getgenderbyname("Male").id; //might not work
           typeid = its.getID_typesbyname("Passport").id;
           Robertpassport.idnumber = "881467101";
           Robertpassport.dateissued.set(2014, 11, 29);
           Robertpassport.expirationdate.set(2024, 11, 28);
           Robertpassport.dateofbirth.set(1986, 7, 19);
           Robertpassport.eyecolor = "#00ffcc";
           Robertpassport.userheight = 75; //height in inches
           Robertpassport.userweight = 210; //weight in lbs
           Robertpassport = ids.saveUser_ID(Robertpassport, robert.id, robert.tid, genderid, typeid);

           //Micheal id
           User_ID Michealid = new User_ID();
           genderid = gts.getgenderbyname("Male").id; //might not work
           typeid = its.getID_typesbyname("Drivers_License").id;
           Michealid.idnumber = "431301372";
           Michealid.dateissued.set(2020, 6, 12);
           Michealid.expirationdate.set(2025, 6, 12);
           Michealid.dateofbirth.set(1993, 11, 30);
           Michealid.eyecolor = "#800000";
           Michealid.userheight = 67; //height in inches
           Michealid.userweight = 150; //weight in lbs
           Michealid = ids.saveUser_ID(Michealid, Micheal.id, Micheal.tid, genderid, typeid);


           User_ID MichealPassport = new User_ID();
           genderid = gts.getgenderbyname("Male").id; //might not work
           typeid = its.getID_typesbyname("Passport").id;
           MichealPassport.idnumber = "171905510";
           MichealPassport.dateissued.set(2015, 6, 12);
           MichealPassport.expirationdate.set(2025, 6, 11);
           MichealPassport.dateofbirth.set(1993, 11, 30);
           MichealPassport.eyecolor = "#800000";
           MichealPassport.userheight = 67; //height in inches
           MichealPassport.userweight = 150; //weight in lbs
           MichealPassport = ids.saveUser_ID(Michealid, Micheal.id, Micheal.tid, genderid, typeid);

           //David ids
           User_ID Davidid = new User_ID();
           genderid = gts.getgenderbyname("Male").id; //might not work
           typeid = its.getID_typesbyname("Drivers_License").id;
           Davidid.idnumber = "548748102";
           Davidid.dateissued.set(2018, 5, 24);
           Davidid.expirationdate.set(2023, 5, 24);
           Davidid.dateofbirth.set(1963, 9, 10);
           Davidid.eyecolor = "#800000";
           Davidid.userheight = 67; //height in inches
           Davidid.userweight = 160; //weight in lbs
           Davidid = ids.saveUser_ID(Davidid, David.id, David.tid, genderid, typeid);

           User_ID DavidPassport = new User_ID();
           genderid = gts.getgenderbyname("Male").id; //might not work
           typeid = its.getID_typesbyname("Passport").id;
           DavidPassport.idnumber = "212453288";
           DavidPassport.dateissued.set(2013, 5, 24);
           DavidPassport.expirationdate.set(2023, 5, 23);
           DavidPassport.dateofbirth.set(1963, 9, 10);
           DavidPassport.eyecolor = "#800000";
           DavidPassport.userheight = 67; //height in inches
           DavidPassport.userweight = 160; //weight in lbs
           DavidPassport = ids.saveUser_ID(DavidPassport, David.id, David.tid, genderid, typeid);
           
           //Donald ids
           User_ID Donaldid = new User_ID();
           genderid = gts.getgenderbyname("Female").id; //might not work
           typeid = its.getID_typesbyname("Drivers_License").id;
           Donaldid.idnumber = "337083537";
           Donaldid.dateissued.set(2017, 0, 7);
           Donaldid.expirationdate.set(2022, 0, 7);
           Donaldid.dateofbirth.set(1950, 4, 25);
           Donaldid.eyecolor = "#0000ff";
           Donaldid.userheight = 61; //height in inches
           Donaldid.userweight = 130; //weight in lbs
           Donaldid = ids.saveUser_ID(Donaldid, Donald.id, Donald.tid, genderid, typeid);

           User_ID Donaldpassport = new User_ID();
           genderid = gts.getgenderbyname("Female").id; //might not work
           typeid = its.getID_typesbyname("Passport").id;
           Donaldpassport.idnumber = "787467157";
           Donaldpassport.dateissued.set(2012, 0, 7);
           Donaldpassport.expirationdate.set(2022, 0, 6);
           Donaldpassport.dateofbirth.set(1950, 4, 25);
           Donaldpassport.eyecolor = "#0000ff";
           Donaldpassport.userheight = 61; //height in inches
           Donaldpassport.userweight = 130; //weight in lbs
           Donaldpassport = ids.saveUser_ID(Donaldpassport, Donald.id, Donald.tid, genderid, typeid);

           //Mickey id

           Thread.sleep(3000);
           
           
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
       //18
       Roles SecurityGuard = New Roles();
       SecurityGuard.name = Rolenames.SecurityGuard;
       SecurityGuard.description = "Custom role for security guard";
       rs.saveRole(SecurityGuard);
       //19
       Roles Substitute_Teacher = new Roles();
       Substitute_Teacher.name = Rolenames.Substitute_Teacher;
       Substitute_Teacher.description = "Custom role for a substitute teacher";
       rs.saveRole(Substitute_Teacher);
       //20
       Roles Student = new Roles();
       Student.name = Rolenames.Student;
       Student.description = "Student in the school";
       rs.saveRole(Student);
       //21
       Roles FakeRole = new Roles();
       FakeRole.name = Rolenames.FakeRole;
       FakeRole.description = "Fake role just for fun";
       rs.saveRole(FakeRole);
        //22
       Roles FakeRole2 = new Roles();
       FakeRole2.name = Rolenames.FakeRole2;
       FakeRole2.description = "Fake role just for fun";
       rs.saveRole(FakeRole2);
        //23
       Roles FakeRole3 = new Roles();
       FakeRole3.name = Rolenames.FakeRole3;
       FakeRole3.description = "Fake role just for fun";
       rs.saveRole(FakeRole3);
       //24
       Roles FakeRole4 = new Roles();
       FakeRole4.name = Rolenames.FakeRole4;
       FakeRole4.description = "Fake role just for fun";
       rs.saveRole(FakeRole4);

       long amountofrows = rs.numberofroles();
       System.out.println("Number of rows available: " + amountofrows);
  
    }

    private static void createidtypestable(ID_TypesService its){
        //1
        ID_types Drivers_License = new ID_types();
        Drivers_License.name = ID_types_names.Drivers_License;
        Drivers_License.description = "United States Drivers License";
        its.saveID_Types(Drivers_License);
        //2
        ID_types Passport = new ID_types();
        Passport.name = ID_types_names.Passport;
        Passport.description = "United States Passport";
        its.saveID_Types(Passport);

        long amountofrows = its.numberofids();
        System.out.println("Number of id types available" + amountofrows);
    }

    private static createuserstatustables(UserStatusTypesService ust){
        //1
        UserStatusTypes Just_Arrived = new UserStatusTypes();
        Just_Arrived.name = Userstatustypesname.Just_Arrived;
        Just_Arrived.description = "User just arrived";
        uts.savestatustypes(Just_Arrived);
        //2
        UserStatusTypes Checked_in = new Checked_in();
        Checked_in.name = Userstatustypesname.Checked_in;
        Checked_in.description = "User just checked in";
        uts.savestatustypes(Checked_in);
        //3
        UserStatusTypes Checked_Out = new Checked_Out();
        Checked_Out.name = Userstatustypesname.Checked_Out;
        Checked_Out.description = "User just checked out";
        uts.savestatustypes(Checked_Out);

        long amountofrows = uts.numberofuserstatusids();
        System.out.println("Number of id types available" + amountofrows);
    }

    private static creategendertypes(GendertypesService gts){
        Gendertypes Male = new Gendertypes();
        Male.name = Gendertypenames.Male;
        gts.saveGendertypes(Male);
        
        Gendertypes Female = new Gendertypes();
        Female.name = Gendertypenames.Female;
        gts.saveGendertypes(Female);

        Gendertypes Other = new Gendertypes();
        Other.name = Gendertypenames.Other;
        gts.saveGendertypes(Other);

        long amountofrows = gts.numberofgenders();
        System.out.println("Number of id types available" + amountofrows);

    }
    
}