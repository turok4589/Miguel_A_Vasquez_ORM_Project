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
import com.fdflib.miguelvasquezormproject.service.ID_TypesService;
import com.fdflib.miguelvasquezormproject.service.User_IDService;
import com.fdflib.miguelvasquezormproject.service.FloorsService;
import com.fdflib.miguelvasquezormproject.service.GendertypesService;
import com.fdflib.miguelvasquezormproject.service.UserStatusTypesService;
import com.fdflib.miguelvasquezormproject.service.BeaconService;
import com.fdflib.miguelvasquezormproject.service.LocationService;
import com.fdflib.miguelvasquezormproject.service.UserAddressService;
import com.fdflib.miguelvasquezormproject.service.UserCheckinService;
import com.fdflib.miguelvasquezormproject.service.BeaconReportService;
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
           UserAddressService ua = new UserAddressService();
           UserRoleService urs = new UserRoleService();
           ID_TypesService its = new ID_TypesService();
           User_IDService ids = new User_IDService();
           UserStatusTypesService ust = new UserStatusTypesService();
           GendertypesService gts = new GendertypesService();
           UserCheckinService ucs = new UserCheckinService();
           LocationService ls = new LocationService();
           FloorsService fs = new FloorsService();
           BeaconService bs = new BeaconService();
           BeaconReportService brs = new BeaconReportService();
           
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
           System.out.println("-------------------Query A: Query for clients----------------------------");
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
                        System.out.println("--------------------------Role for client----------------------------");
                        System.out.println("Role id: " + idsrole.current.id);
                        idsrole.current.roles = rs.getRoleById(idsrole.current.currentroleid);
                        System.out.println("Role name: " + idsrole.current.roles.name);
                        System.out.println("Is this role active: " + idsrole.current.isactive + "\n");
                    }
                  } 
               }
           }
           Thread.sleep(3000);

           //create location
           Locations firstlocation = new Locations();
           firstlocation.name = "Suny Newpaltz";
           firstlocation.description = "University I graduated from";
           firstlocation.maxLat = 41.7390;
           firstlocation.maxLng = 74.0852;
           ls.savelocation(firstlocation, Miguel.id);

           Locations secondlocation = new Locations();
           secondlocation.name = "Marist College";
           secondlocation.description = "University I am attending";
           secondlocation.maxLat = 41.7231;
           secondlocation.maxLng = 73.9345;
           ls.savelocation(secondlocation, Miguel.id);

           Locations thirdlocation = new Locations();
           thirdlocation.name = "Amazon Headquarters";
           thirdlocation.description = "Amazon building in Seattle";
           thirdlocation.maxLat = 47.623346;
           thirdlocation.maxLng = 122.336775;
           ls.savelocation(thirdlocation, Jeff_Bezos.id);

           Locations fourthlocation = new Locations();
           fourthlocation.name = "Amazon Headquarters";
           fourthlocation.description = "Warehouse in Newburgh, Ny";
           fourthlocation.maxLat = 41.52576;
           fourthlocation.maxLng = 74.13963;
           ls.savelocation(fourthlocation, Jeff_Bezos.id);

           //create floors, for now 2 floors per location(just for the demo)
           Floors floorlocation1 = new Floors();
           floorlocation1.name = "First Floor";
           floorlocation1.description = "In Newpaltz Floor 1";
           fs.saveFloors(floorlocation1, firstlocation.id, firstlocation.tid);

           Floors floorlocation2 = new Floors();
           floorlocation2.name = "Second Floor";
           floorlocation2.description = "In Newpaltz Floor2";
           fs.saveFloors(floorlocation2, firstlocation.id, firstlocation.tid);

           Floors floorlocation3 = new Floors();
           floorlocation3.name = "First Floor";
           floorlocation3.description = "Marist College Floor 1";
           fs.saveFloors(floorlocation3, secondlocation.id, secondlocation.tid);

           Floors floorlocation4 = new Floors();
           floorlocation4.name = "Second Floor";
           floorlocation4.description = "Marist College Floor 2";
           fs.saveFloors(floorlocation4, secondlocation.id, secondlocation.tid);

           Floors floorlocation5 = new Floors();
           floorlocation5.name = "First Floor";
           floorlocation5.description = "Amazon Headquarters Floor 1";
           fs.saveFloors(floorlocation5, thirdlocation.id, thirdlocation.tid);

           Floors floorlocation6 = new Floors();
           floorlocation6.name = "Second Floor";
           floorlocation6.description = "Amazon Headquarters Floor 2";
           fs.saveFloors(floorlocation6, thirdlocation.id, thirdlocation.tid);

           Floors floorlocation7 = new Floors();
           floorlocation7.name = "First Floor";
           floorlocation7.description = "Amazon Warehouse Floor 1";
           fs.saveFloors(floorlocation7, fourthlocation.id, fourthlocation.tid);

           Floors floorlocation8 = new Floors();
           floorlocation8.name = "Second Floor";
           floorlocation8.description = "Amazon Warehouse Floor 2";
           fs.saveFloors(floorlocation8, fourthlocation.id, fourthlocation.tid);
           Thread.sleep(3000);
           //Beacons

           //1
           Beacons beacon = new Beacons();
           String uuid = "3d677038-14b8-4d60-915f-d98e64cadd6e";
           beacon.majorid = 1; //group id
           beacon.minorid = 1; //individual
           bs.saveBeacon(beacon, Miguel.id, uuid);
           //2
           uuid = "345807ab-c222-497b-85bd-9760835b5fa3";
           beacon.id = -1;
           beacon.majorid = 1; //group id
           beacon.minorid = 2; //individual
           bs.saveBeacon(beacon, Miguel.id, uuid);
           //3
           uuid = "00c2f19e-a73f-432d-aa75-ba36448351a1";
           beacon.id = -1;
           beacon.majorid = 1; //group id
           beacon.minorid = 3; //individual
           bs.saveBeacon(beacon, Miguel.id, uuid);
           //4
           uuid = "09a6ac39-b717-478d-a0b2-80e42fd23352";
           beacon.id = -1;
           beacon.majorid = 1; //group id
           beacon.minorid = 4; //individual
           bs.saveBeacon(beacon, Miguel.id, uuid);
           //5
           uuid = "691f799b-7e39-43c1-8906-619aadc1ef6b";
           beacon.id = -1;
           beacon.majorid = 1; //group id
           beacon.minorid = 5; //individual
           bs.saveBeacon(beacon, Miguel.id, uuid);

           //group 2
           //6
           uuid = "1cbe9459-9a1f-42e2-a72a-53676ef892b0";
           beacon.id = -1;
           beacon.majorid = 2; //group id
           beacon.minorid = 1; //individual
           bs.saveBeacon(beacon, Miguel.id, uuid);
           //7
           uuid = "bbacba9a-7c06-4713-b1b7-b2b5d38a4bcc";
           beacon.id = -1;
           beacon.majorid = 2; //group id
           beacon.minorid = 2; //individual
           bs.saveBeacon(beacon, Miguel.id, uuid);

           //8
           uuid = "c8b3bcc8-652d-41e4-9e84-a77158ea388d";
           beacon.id = -1;
           beacon.majorid = 2; //group id
           beacon.minorid = 3; //individual
           bs.saveBeacon(beacon, Miguel.id, uuid);

           //9
           uuid = "6375ac65-fe6f-4711-8560-073ce15a7958";
           beacon.id = -1;
           beacon.majorid = 2; //group id
           beacon.minorid = 4; //individual
           bs.saveBeacon(beacon, Miguel.id, uuid);

           //10
           uuid = "fd093885-9506-446f-be7c-453d17566b65";
           beacon.id = -1;
           beacon.majorid = 2; //group id
           beacon.minorid = 5; //individual
           bs.saveBeacon(beacon, Miguel.id, uuid);
           //group 3
           //11
           uuid = "999a2b64-ae43-49ee-85cd-b8c33925dd29";
           beacon.id = -1;
           beacon.majorid = 3; //group id
           beacon.minorid = 1; //individual
           bs.saveBeacon(beacon, Miguel.id, uuid);

            //12
            uuid = "bb316418-a14c-4597-a0c9-b10eebe4f5cc";
            beacon.id = -1;
            beacon.majorid = 3; //group id
            beacon.minorid = 2; //individual
            bs.saveBeacon(beacon, Miguel.id, uuid);

             //13
             uuid = "f2c6fe49-9f8f-450d-9a2f-7193d8ca84c3";
             beacon.id = -1;
             beacon.majorid = 3; //group id
             beacon.minorid = 3; //individual
             bs.saveBeacon(beacon, Miguel.id, uuid);
           
             //14
             uuid = "bd353d0b-b9ce-4efc-be03-3f2fbe79e3a8";
             beacon.id = -1;
             beacon.majorid = 3; //group id
             beacon.minorid = 4; //individual
             bs.saveBeacon(beacon, Miguel.id, uuid);
             //15
             uuid = "255fd368-d240-42fc-9ccc-3b3b5a5c131a";
             beacon.id = -1;
             beacon.majorid = 3; //group id
             beacon.minorid = 5; //individual
             bs.saveBeacon(beacon, Miguel.id, uuid);
              //Group 4
             //16
             uuid = "764990e2-edbd-4ff5-8683-decdf7060b0a";
             beacon.id = -1;
             beacon.majorid = 4; //group id
             beacon.minorid = 1; //individual
             bs.saveBeacon(beacon, Miguel.id, uuid);

             //17
             uuid = "1001ad4b-fc8d-4e68-9095-037a795d9843";
             beacon.id = -1;
             beacon.majorid = 4; //group id
             beacon.minorid = 2; //individual
             bs.saveBeacon(beacon, Miguel.id, uuid);

             //18
             uuid = "4d909d3c-6c4a-48b7-83ee-2f05ba83dc69";
             beacon.id = -1;
             beacon.majorid = 4; //group id
             beacon.minorid = 3; //individual
             bs.saveBeacon(beacon, Miguel.id, uuid);

            //19
             uuid = "2a833249-fd11-4791-b73b-2d3e1bf5e689";
             beacon.id = -1;
             beacon.majorid = 4; //group id
             beacon.minorid = 4; //individual
             bs.saveBeacon(beacon, Miguel.id, uuid);

             //20
             uuid = "487f8cb0-5782-49d7-b2c0-5333c9c1b97d";
             beacon.id = -1;
             beacon.majorid = 4; //group id
             beacon.minorid = 5; //individual
             bs.saveBeacon(beacon, Miguel.id, uuid);

             //Group 5
             //21
             uuid = "ac227421-ec95-4d2c-89d2-2de130c6cdcb";
             beacon.id = -1;
             beacon.majorid = 1; //group id
             beacon.minorid = 1; //individual
             bs.saveBeacon(beacon, Jeff_Bezos.id, uuid);

             //22
             uuid = "5f853aa3-4f90-4280-b748-49aa1ef622f6";
             beacon.id = -1;
             beacon.majorid = 1; //group id
             beacon.minorid = 2; //individual
             bs.saveBeacon(beacon, Jeff_Bezos.id, uuid);

             //23
             uuid = "fdef8e32-c837-4e14-afd7-c468a50bf7db";
             beacon.id = -1;
             beacon.majorid = 1; //group id
             beacon.minorid = 3; //individual
             bs.saveBeacon(beacon, Jeff_Bezos.id, uuid);
            
             //24
             uuid = "79fcf3ad-76f8-4963-862b-4c8203ec9f3e";
             beacon.id = -1;
             beacon.majorid = 1; //group id
             beacon.minorid = 4; //individual
             bs.saveBeacon(beacon, Jeff_Bezos.id, uuid);

             //25
             uuid = "40b3edb9-7561-407b-b221-b19b06f70654";
             beacon.id = -1;
             beacon.majorid = 1; //group id
             beacon.minorid = 5; //individual
             bs.saveBeacon(beacon, Jeff_Bezos.id, uuid);

             //26
             uuid = "68a924f4-f79c-4a0c-aa25-6cd3730150bb";
             beacon.id = -1;
             beacon.majorid = 2; //group id
             beacon.minorid = 1; //individual
             bs.saveBeacon(beacon, Jeff_Bezos.id, uuid);

             //27
             uuid = "c4e48adf-21c6-4bc2-8c73-ab89fb5bed99";
             beacon.id = -1;
             beacon.majorid = 2; //group id
             beacon.minorid = 2; //individual
             bs.saveBeacon(beacon, Jeff_Bezos.id, uuid);
            
             //28
             uuid = "21b467ae-e923-466a-a832-7e9d1d42d269";
             beacon.id = -1;
             beacon.majorid = 2; //group id
             beacon.minorid = 3; //individual
             bs.saveBeacon(beacon, Jeff_Bezos.id, uuid);

             //29
             uuid = "35cb21a7-21e6-475e-a875-95c035ca7428";
             beacon.id = -1;
             beacon.majorid = 2; //group id
             beacon.minorid = 4; //individual
             bs.saveBeacon(beacon, Jeff_Bezos.id, uuid);
            
             //30
             uuid = "2ccff436-9e0e-4edf-8f9f-4b290546075c";
             beacon.id = -1;
             beacon.majorid = 2; //group id
             beacon.minorid = 5; //individual
             bs.saveBeacon(beacon, Jeff_Bezos.id, uuid);
            
             //31
             uuid = "5f27b853-4cbe-42e6-8d88-b757da7041e0";
             beacon.id = -1;
             beacon.majorid = 3; //group id
             beacon.minorid = 1; //individual
             bs.saveBeacon(beacon, Jeff_Bezos.id, uuid);
            
             //32
             uuid = "66b80b88-2e87-4744-b330-002a71643ac1";
             beacon.id = -1;
             beacon.majorid = 3; //group id
             beacon.minorid = 2; //individual
             bs.saveBeacon(beacon, Jeff_Bezos.id, uuid);
            
             //33
             uuid = "d5f85f98-5a05-4f01-9946-3b923c1bf126";
             beacon.id = -1;
             beacon.majorid = 3; //group id
             beacon.minorid = 3; //individual
             bs.saveBeacon(beacon, Jeff_Bezos.id, uuid);

             //34
             uuid = "1358bdf7-5981-496a-beb6-94271419ad03";
             beacon.id = -1;
             beacon.majorid = 3; //group id
             beacon.minorid = 4; //individual
             bs.saveBeacon(beacon, Jeff_Bezos.id, uuid);

             //35
             uuid = "685b361d-056a-43e5-a609-57accdacc47c";
             beacon.id = -1;
             beacon.majorid = 3; //group id
             beacon.minorid = 5; //individual
             bs.saveBeacon(beacon, Jeff_Bezos.id, uuid);

             //36
             uuid = "c4bf6716-dc8b-4ac6-9ee1-1c401d709bc7";
             beacon.id = -1;
             beacon.majorid = 4; //group id
             beacon.minorid = 1; //individual
             bs.saveBeacon(beacon, Jeff_Bezos.id, uuid);

            //37
             uuid = "2413eb56-ffc7-4169-8bae-89fd8e6d38bc";
             beacon.id = -1;
             beacon.majorid = 4; //group id
             beacon.minorid = 2; //individual
             bs.saveBeacon(beacon, Jeff_Bezos.id, uuid);

            //38
            uuid = "c5c3ab60-b9ad-478c-a503-03a4cc68f979";
            beacon.id = -1;
            beacon.majorid = 4; //group id
            beacon.minorid = 3; //individual
            bs.saveBeacon(beacon, Jeff_Bezos.id, uuid);

            //39
            uuid = "19ebb248-1567-4aa3-a5fe-a9ee4a5ab86c";
            beacon.id = -1;
            beacon.majorid = 4; //group id
            beacon.minorid = 4; //individual
            bs.saveBeacon(beacon, Jeff_Bezos.id, uuid);

            //40
            uuid = "dbfe4eda-aceb-4f02-b22e-2c44cb68d75c";
            beacon.id = -1;
            beacon.majorid = 4; //group id
            beacon.minorid = 5; //individual
            bs.saveBeacon(beacon, Jeff_Bezos.id, uuid);

            Thread.sleep(3000);

            //create beacon reports for now 5 beacons per floor
            //1
            Beacon_Report report = new Beacon_Report();
            uuid = "3d677038-14b8-4d60-915f-d98e64cadd6e";
            brs.savebeaconreport(report, uuid, firstlocation.id, floorlocation1.id, Miguel.id);
            //2
            report.id = -1;
            uuid = "345807ab-c222-497b-85bd-9760835b5fa3";
            brs.savebeaconreport(report, uuid, firstlocation.id, floorlocation1.id, Miguel.id);
            //3
            report.id = -1;
            uuid = "00c2f19e-a73f-432d-aa75-ba36448351a1";
            brs.savebeaconreport(report, uuid, firstlocation.id, floorlocation2.id, Miguel.id);
            //4
            report.id = -1;
            uuid = "09a6ac39-b717-478d-a0b2-80e42fd23352";
            brs.savebeaconreport(report, uuid, firstlocation.id, floorlocation2.id, Miguel.id);
            //5
            report.id = -1;
            uuid = "691f799b-7e39-43c1-8906-619aadc1ef6b";
            brs.savebeaconreport(report, uuid, firstlocation.id, floorlocation1.id, Miguel.id);

            //6
            report.id = -1;
            uuid = "1cbe9459-9a1f-42e2-a72a-53676ef892b0";
            brs.savebeaconreport(report, uuid, firstlocation.id, floorlocation2.id, Miguel.id);

            //7
            report.id = -1;
            uuid = "bbacba9a-7c06-4713-b1b7-b2b5d38a4bcc";
            brs.savebeaconreport(report, uuid, firstlocation.id, floorlocation2.id, Miguel.id);

            //8
            report.id = -1;
            uuid = "c8b3bcc8-652d-41e4-9e84-a77158ea388d";
            brs.savebeaconreport(report, uuid, firstlocation.id, floorlocation2.id, Miguel.id);

            //9
            report.id = -1;
            uuid = "6375ac65-fe6f-4711-8560-073ce15a7958";
            brs.savebeaconreport(report, uuid, firstlocation.id, floorlocation2.id, Miguel.id);
            //10
            report.id = -1;
            uuid = "fd093885-9506-446f-be7c-453d17566b65";
            brs.savebeaconreport(report, uuid, firstlocation.id, floorlocation2.id, Miguel.id);
            //11
            report.id = -1;
            uuid = "999a2b64-ae43-49ee-85cd-b8c33925dd29";
            brs.savebeaconreport(report, uuid, secondlocation.id, floorlocation3.id, Miguel.id);
            //12
            report.id = -1;
            uuid = "bb316418-a14c-4597-a0c9-b10eebe4f5cc";
            brs.savebeaconreport(report, uuid, secondlocation.id, floorlocation3.id, Miguel.id);
            //13
            report.id = -1;
            uuid = "f2c6fe49-9f8f-450d-9a2f-7193d8ca84c3";
            brs.savebeaconreport(report, uuid, secondlocation.id, floorlocation3.id, Miguel.id);
            //14
            report.id = -1;
            uuid = "bd353d0b-b9ce-4efc-be03-3f2fbe79e3a8";
            brs.savebeaconreport(report, uuid, secondlocation.id, floorlocation3.id, Miguel.id);

            //15
            report.id = -1;
            uuid = "255fd368-d240-42fc-9ccc-3b3b5a5c131a";
            brs.savebeaconreport(report, uuid, secondlocation.id, floorlocation3.id, Miguel.id);

            //16
            report.id = -1;
            uuid = "764990e2-edbd-4ff5-8683-decdf7060b0a";
            brs.savebeaconreport(report, uuid, secondlocation.id, floorlocation4.id, Miguel.id);

            //17
            report.id = -1;
            uuid = "1001ad4b-fc8d-4e68-9095-037a795d9843";
            brs.savebeaconreport(report, uuid, secondlocation.id, floorlocation4.id, Miguel.id);

            //18
            report.id = -1;
            uuid = "4d909d3c-6c4a-48b7-83ee-2f05ba83dc69";
            brs.savebeaconreport(report, uuid, secondlocation.id, floorlocation4.id, Miguel.id);

            //19
            report.id = -1;
            uuid = "2a833249-fd11-4791-b73b-2d3e1bf5e689";
            brs.savebeaconreport(report, uuid, secondlocation.id, floorlocation4.id, Miguel.id);

            //20
            report.id = -1;
            uuid = "487f8cb0-5782-49d7-b2c0-5333c9c1b97d";
            brs.savebeaconreport(report, uuid, secondlocation.id, floorlocation4.id, Miguel.id);

            //21
            report.id = -1;
            uuid = "ac227421-ec95-4d2c-89d2-2de130c6cdcb";
            brs.savebeaconreport(report, uuid, thirdlocation.id, floorlocation5.id, Jeff_Bezos.id);
            
            //22
            report.id = -1;
            uuid = "5f853aa3-4f90-4280-b748-49aa1ef622f6";
            brs.savebeaconreport(report, uuid, thirdlocation.id, floorlocation5.id, Jeff_Bezos.id);

            //23
            report.id = -1;
            uuid = "fdef8e32-c837-4e14-afd7-c468a50bf7db";
            brs.savebeaconreport(report, uuid, thirdlocation.id, floorlocation5.id, Jeff_Bezos.id);

            //24
            report.id = -1;
            uuid = "79fcf3ad-76f8-4963-862b-4c8203ec9f3e";
            brs.savebeaconreport(report, uuid, thirdlocation.id, floorlocation5.id, Jeff_Bezos.id);
        
            //25
            report.id = -1;
            uuid = "40b3edb9-7561-407b-b221-b19b06f70654";
            brs.savebeaconreport(report, uuid, thirdlocation.id, floorlocation5.id, Jeff_Bezos.id);

            //26
            report.id = -1;
            uuid = "68a924f4-f79c-4a0c-aa25-6cd3730150bb";
            brs.savebeaconreport(report, uuid, thirdlocation.id, floorlocation6.id, Jeff_Bezos.id);
            
            //27
            report.id = -1;
            uuid = "c4e48adf-21c6-4bc2-8c73-ab89fb5bed99";
            brs.savebeaconreport(report, uuid, thirdlocation.id, floorlocation6.id, Jeff_Bezos.id);

            //28
            report.id = -1;
            uuid = "21b467ae-e923-466a-a832-7e9d1d42d269";
            brs.savebeaconreport(report, uuid, thirdlocation.id, floorlocation6.id, Jeff_Bezos.id);

            //29
            report.id = -1;
            uuid = "35cb21a7-21e6-475e-a875-95c035ca7428";
            brs.savebeaconreport(report, uuid, thirdlocation.id, floorlocation6.id, Jeff_Bezos.id);

            //30
            report.id = -1;
            uuid = "2ccff436-9e0e-4edf-8f9f-4b290546075c";
            brs.savebeaconreport(report, uuid, thirdlocation.id, floorlocation6.id, Jeff_Bezos.id);

            //31
            report.id = -1;
            uuid = "5f27b853-4cbe-42e6-8d88-b757da7041e0";
            brs.savebeaconreport(report, uuid, fourthlocation.id, floorlocation7.id, Jeff_Bezos.id);

            //32
            report.id = -1;
            uuid = "66b80b88-2e87-4744-b330-002a71643ac1";
            brs.savebeaconreport(report, uuid, fourthlocation.id, floorlocation7.id, Jeff_Bezos.id);

            //33
            report.id = -1;
            uuid = "d5f85f98-5a05-4f01-9946-3b923c1bf126";
            brs.savebeaconreport(report, uuid, fourthlocation.id, floorlocation7.id, Jeff_Bezos.id);

            //34
            report.id = -1;
            uuid = "1358bdf7-5981-496a-beb6-94271419ad03";
            brs.savebeaconreport(report, uuid, fourthlocation.id, floorlocation7.id, Jeff_Bezos.id);

            //35
            report.id = -1;
            uuid = "685b361d-056a-43e5-a609-57accdacc47c";
            brs.savebeaconreport(report, uuid, fourthlocation.id, floorlocation7.id, Jeff_Bezos.id);

            //36
            report.id = -1;
            uuid = "c4bf6716-dc8b-4ac6-9ee1-1c401d709bc7";
            brs.savebeaconreport(report, uuid, fourthlocation.id, floorlocation8.id, Jeff_Bezos.id);

            //37
            report.id = -1;
            uuid = "2413eb56-ffc7-4169-8bae-89fd8e6d38bc";
            brs.savebeaconreport(report, uuid, fourthlocation.id, floorlocation8.id, Jeff_Bezos.id);

            //38
            report.id = -1;
            uuid = "c5c3ab60-b9ad-478c-a503-03a4cc68f979";
            brs.savebeaconreport(report, uuid, fourthlocation.id, floorlocation8.id, Jeff_Bezos.id);

            //39
            report.id = -1;
            uuid = "19ebb248-1567-4aa3-a5fe-a9ee4a5ab86c";
            brs.savebeaconreport(report, uuid, fourthlocation.id, floorlocation8.id, Jeff_Bezos.id);

             //40
            report.id = -1;
            uuid = "dbfe4eda-aceb-4f02-b22e-2c44cb68d75c";
            brs.savebeaconreport(report, uuid, fourthlocation.id, floorlocation8.id, Jeff_Bezos.id);

           //create users

           User bob = new User();
           bob.username = "Bob123";
           bob.firstName = "Bob";
           bob.lastName = "Williams";
           bob.color = "#ff0000";
           bob = us.saveuserversion2(bob, Miguel.id);

           User james = new User();
           james.username = "James1234";
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

           User paul = new User();
           paul.username = "Paul124";
           paul.firstName = "Paul";
           paul.lastName = "Wilson";
           paul.color = "#66ffff";
           paul = us.saveuserversion2(paul, Miguel.id);

           User Andrew = new User();
           Andrew.username = "Andrew1234";
           Andrew.firstName = "Andrew";
           Andrew.lastName = "Thomas";
           Andrew.color = "#cc99ff";
           Andrew = us.saveuserversion2(Andrew, Miguel.id);

           User Joshua = new User();
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
           Timothy.firstName = "Timothy";
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

           User James2 = us.getCurrentUserbyusername("James1234", Miguel.id);
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

           User Eric2 = us.getCurrentUserbyusername("Eric241!", Jeff_Bezos.id);
           Eric2.firstName = "Jonathan";
           Eric2.color = "#ffffcc";
           Eric2 = us.saveuserversion2(Eric2, Eric2.tid);

           User Jeffrey2 = us.getCurrentUserbyusername("Jeffreyyyyy12", Jeff_Bezos.id);
           Jeffrey2.firstName = "Samuel";
           Jeffrey2.lastName = "Klien";
           Jeffrey2.color = "#ff0066";
           Jeffrey2 = us.saveuserversion2(Jeffrey2, Jeffrey2.tid);

           Thread.sleep(3000);

           //add user address information

           Useraddress useraddress = new Useraddress();
           useraddress.street_address1 = "466 Fieldstone ST";
           useraddress.city = "Mishawaka";
           useraddress.state = "Indiana";
           useraddress.zipcode = "46544";
           useraddress.country = "United States";
           useraddress.phonenumber = "908-5251";
           useraddress = ua.saveUseraddress(useraddress, bob.id, bob.tid);

           
           useraddress.id = -1;
           useraddress.street_address1 = "478 Somerset St";
           useraddress.city = "North Wales";
           useraddress.state = "Pennsylvania";
           useraddress.zipcode = "19454";
           useraddress.country = "United States";
           useraddress.phonenumber = "245-3706";
           useraddress = ua.saveUseraddress(useraddress, james.id, james.tid);

           
           useraddress.id = -1;
           useraddress.street_address1 = "492 West Beaver Ridge Street";
           useraddress.city = "Matawan";
           useraddress.state = "New Jersey";
           useraddress.zipcode = "07747";
           useraddress.country = "United States";
           useraddress.phonenumber = "841-1684";
           useraddress = ua.saveUseraddress(useraddress, robert.id, robert.tid);

           
           useraddress.id = -1;
           useraddress.street_address1 = "1 Ohio St.";
           useraddress.city = "Selden";
           useraddress.state = "New York";
           useraddress.zipcode = "11784";
           useraddress.country = "United States";
           useraddress.phonenumber = "935-8961";
           useraddress = ua.saveUseraddress(useraddress, Micheal.id, Micheal.tid);
           
           
           useraddress.id = -1;
           useraddress.street_address1 = "47 Hillside Dr";
           useraddress.city = "Doylestown";
           useraddress.state = "Pennsylvania";
           useraddress.zipcode = "18901";
           useraddress.country = "United States";
           useraddress.phonenumber = "666-8331";
           useraddress = ua.saveUseraddress(useraddress, David.id, David.tid);

           
           useraddress.id = -1;
           useraddress.street_address1 = "7712 Sleepy Hollow St.";
           useraddress.city = "Gallatin";
           useraddress.state = "Tennessee";
           useraddress.zipcode = "37066";
           useraddress.country = "United States";
           useraddress.phonenumber = "318-1169";
           useraddress = ua.saveUseraddress(useraddress, Donald.id, Donald.tid);

           
           useraddress.id = -1;
           useraddress.street_address1 = "62 Elizabeth Road";
           useraddress.city = "Wantagh";
           useraddress.state = "New York";
           useraddress.zipcode = "11793";
           useraddress.country = "United States";
           useraddress.phonenumber = "846-2799";
           useraddress = ua.saveUseraddress(useraddress, Mickey.id, Mickey.tid);

           
           useraddress.id = -1;
           useraddress.street_address1 = "249 Hanover St.";
           useraddress.city = "Bloomfield";
           useraddress.state = "New Jersey";
           useraddress.zipcode = "07003";
           useraddress.country = "United States";
           useraddress.phonenumber = "523-3134";
           useraddress = ua.saveUseraddress(useraddress, paul.id, paul.tid);
           
           
           useraddress.id = -1;
           useraddress.street_address1 = "7282 SE. Strawberry Rd.";
           useraddress.city = "Williamsport";
           useraddress.state = "Pennsylvania";
           useraddress.zipcode = "17701";
           useraddress.country = "United States";
           useraddress.phonenumber = "813-5007";
           useraddress = ua.saveUseraddress(useraddress, Andrew.id, Andrew.tid);

           
           useraddress.id = -1;
           useraddress.street_address1 = "904 Wakehurst Ave.";
           useraddress.city = "Revere";
           useraddress.state = "Massachusetts";
           useraddress.zipcode = "02151";
           useraddress.country = "United States";
           useraddress.phonenumber = "694-2075";
           useraddress = ua.saveUseraddress(useraddress, Joshua.id, Joshua.tid);

           
           useraddress.id = -1;
           useraddress.street_address1 = "31 Bedford Drive";
           useraddress.city = "Pensacola";
           useraddress.state = "Florida";
           useraddress.zipcode = "32503";
           useraddress.country = "United States";
           useraddress.phonenumber = "523-4980";
           useraddress = ua.saveUseraddress(useraddress, jones.id, jones.tid);

           
           useraddress.id = -1;
           useraddress.street_address1 = "963 W. Williams Circle";
           useraddress.city = "Huntsville";
           useraddress.state = "Alabama";
           useraddress.zipcode = "35803";
           useraddress.country = "United States";
           useraddress.phonenumber = "697-0695";
           useraddress = ua.saveUseraddress(useraddress, Garcia.id, Garcia.tid);

           
           useraddress.id = -1;
           useraddress.street_address1 = "751 Illinois Lane";
           useraddress.city = "Oklahoma City";
           useraddress.state = "Oklahoma";
           useraddress.zipcode = "73112";
           useraddress.country = "United States";
           useraddress.phonenumber = "675-8185";
           useraddress = ua.saveUseraddress(useraddress, Jason.id, Jason.tid);

           
           useraddress.id = -1;
           useraddress.street_address1 = "8922 Roberts Circle";
           useraddress.street_address2 = "Apt 47";
           useraddress.city = "Rego Park";
           useraddress.state = "New York";
           useraddress.zipcode = "11374";
           useraddress.country = "United States";
           useraddress.phonenumber = "495-6541";
           useraddress = ua.saveUseraddress(useraddress, Brian.id, Brian.tid);

          
           useraddress.id = -1;
           useraddress.street_address1 = "9630 Plymouth Ave.";
           useraddress.city = "Bedford";
           useraddress.state = "Ohio";
           useraddress.zipcode = "44146";
           useraddress.country = "United States";
           useraddress.phonenumber = "448-6188";
           useraddress = ua.saveUseraddress(useraddress, Ronald.id, Ronald.tid);

           
           useraddress.id = -1;
           useraddress.street_address1 = "8365 Rock Maple Dr.";
           useraddress.city = "Lincoln Park";
           useraddress.state = "Michigan";
           useraddress.zipcode = "48146";
           useraddress.country = "United States";
           useraddress.phonenumber = "943-8555";
           useraddress = ua.saveUseraddress(useraddress, Timothy.id, Timothy.tid);

           
           useraddress.id = -1;
           useraddress.street_address1 = "4 West Westport Lane";
           useraddress.city = "Enfield";
           useraddress.state = "Connecticut";
           useraddress.zipcode = "06082";
           useraddress.country = "United States";
           useraddress.phonenumber = "366-4804";
           useraddress = ua.saveUseraddress(useraddress, Jeffrey.id, Jeffrey.tid);

           
           useraddress.id = -1;
           useraddress.street_address1 = "7477 NE. Brook Lane";
           useraddress.city = "Smithtown";
           useraddress.state = "New York";
           useraddress.zipcode = "11787";
           useraddress.country = "United States";
           useraddress.phonenumber = "293-3588";
           useraddress = ua.saveUseraddress(useraddress, Ryan.id, Ryan.tid);

           
           useraddress.id = -1;
           useraddress.street_address1 = "976 W. Hilltop St.";
           useraddress.city = "Findlay";
           useraddress.state = "Ohio";
           useraddress.zipcode = "45840";
           useraddress.country = "United States";
           useraddress.phonenumber = "522-2089";
           useraddress = ua.saveUseraddress(useraddress, Eric.id, Eric.tid);

           
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

           //jones id
           User_ID Jonesid = new User_ID();
           genderid = gts.getgenderbyname("Male").id; //might not work
           typeid = its.getID_typesbyname("Drivers_License").id;
           Jonesid.idnumber = "258083211";
           Jonesid.dateissued.set(2021, 10, 19);
           Jonesid.expirationdate.set(2026, 10, 19);
           Jonesid.dateofbirth.set(1993, 6, 24);
           Jonesid.eyecolor = "#0000ff";
           Jonesid.userheight = 65; //height in inches
           Jonesid.userweight = 140; //weight in lbs
           Jonesid = ids.saveUser_ID(Jonesid, jones.id, jones.tid, genderid, typeid);

           User_ID Jonespassport = new User_ID();
           genderid = gts.getgenderbyname("Male").id; //might not work
           typeid = its.getID_typesbyname("Passport").id;
           Jonespassport.idnumber = "847401935";
           Jonespassport.dateissued.set(2016, 10, 19);
           Jonespassport.expirationdate.set(2026, 10, 18);
           Jonespassport.dateofbirth.set(1993, 6, 24);
           Jonespassport.eyecolor = "#0000ff";
           Jonespassport.userheight = 65; //height in inches
           Jonespassport.userweight = 140; //weight in lbs
           Jonespassport = ids.saveUser_ID(Jonespassport, jones.id, jones.tid, genderid, typeid);
           

           //Garcia id

           User_ID Garciaid = new User_ID();
           genderid = gts.getgenderbyname("Female").id; //might not work
           typeid = its.getID_typesbyname("Drivers_License").id;
           Garciaid.idnumber = "062833676";
           Garciaid.dateissued.set(2018, 8, 7);
           Garciaid.expirationdate.set(2023, 8, 7);
           Garciaid.dateofbirth.set(1991, 7, 9);
           Garciaid.eyecolor = "#99ff99";
           Garciaid.userheight = 63; //height in inches
           Garciaid.userweight = 125; //weight in lbs
           Garciaid = ids.saveUser_ID(Garciaid, Garcia.id, Garcia.tid, genderid, typeid);

           User_ID Garciapassport = new User_ID();
           genderid = gts.getgenderbyname("Female").id; //might not work
           typeid = its.getID_typesbyname("Passport").id;
           Garciapassport.idnumber = "341160022";
           Garciapassport.dateissued.set(2013, 8, 7);
           Garciapassport.expirationdate.set(2023, 8, 6);
           Garciapassport.dateofbirth.set(1991, 7, 9);
           Garciapassport.eyecolor = "#99ff99";
           Garciapassport.userheight = 63; //height in inches
           Garciapassport.userweight = 125; //weight in lbs
           Garciapassport = ids.saveUser_ID(Garciapassport, Garcia.id, Garcia.tid, genderid, typeid);

           //Jason id

           User_ID Jasonid = new User_ID();
           genderid = gts.getgenderbyname("Female").id; //might not work
           typeid = its.getID_typesbyname("Drivers_License").id;
           Jasonid.idnumber = "863122282";
           Jasonid.dateissued.set(2021, 2, 13);
           Jasonid.expirationdate.set(2026, 2, 13);
           Jasonid.dateofbirth.set(1978, 8, 14);
           Jasonid.eyecolor = "#663300";
           Jasonid.userheight = 75; //height in inches
           Jasonid.userweight = 185; //weight in lbs
           Jasonid = ids.saveUser_ID(Jasonid, Jason.id, Jason.tid, genderid, typeid);

           User_ID Jasonpassport = new User_ID();
           genderid = gts.getgenderbyname("Female").id; //might not work
           typeid = its.getID_typesbyname("Passport").id;
           Jasonpassport.idnumber = "803363086";
           Jasonpassport.dateissued.set(2016, 2, 13);
           Jasonpassport.expirationdate.set(2026, 2, 12);
           Jasonpassport.dateofbirth.set(1978, 8, 14);
           Jasonpassport.eyecolor = "#663300";
           Jasonpassport.userheight = 75; //height in inches
           Jasonpassport.userweight = 185; //weight in lbs
           Jasonpassport = ids.saveUser_ID(Jasonpassport, Jason.id, Jason.tid, genderid, typeid);

           //Brian id

           User_ID Brianid = new User_ID();
           genderid = gts.getgenderbyname("Male").id; //might not work
           typeid = its.getID_typesbyname("Drivers_License").id;
           Brianid.idnumber = "673519310";
           Brianid.dateissued.set(2017, 2, 15);
           Brianid.expirationdate.set(2022, 2, 15);
           Brianid.dateofbirth.set(1996, 4, 16);
           Brianid.eyecolor = "#663300";
           Brianid.userheight = 68; //height in inches
           Brianid.userweight = 160; //weight in lbs
           Brianid = ids.saveUser_ID(Brianid, Brian.id, Brian.tid, genderid, typeid);

           User_ID Brianpassport = new User_ID();
           genderid = gts.getgenderbyname("Male").id; //might not work
           typeid = its.getID_typesbyname("Passport").id;
           Brianpassport.idnumber = "411773635";
           Brianpassport.dateissued.set(2012, 2, 15);
           Brianpassport.expirationdate.set(2022, 2, 14);
           Brianpassport.dateofbirth.set(1996, 4, 16);
           Brianpassport.eyecolor = "#663300";
           Brianpassport.userheight = 68; //height in inches
           Brianpassport.userweight = 160; //weight in lbs
           Brianpassport = ids.saveUser_ID(Brianpassport, Brian.id, Brian.tid, genderid, typeid);

           Thread.sleep(3000);
           //Checkin in people
           
           //bob is gonna go through the full cycle
           UserCheckin checkin = new UserCheckin();
           long statusid = ust.getstatusbyname("Just_Arrived").id;
           ucs.saveusercheckin(checkin, bob.id, statusid, firstlocation.id, floorlocation1.id, bob.tid);
           Thread.sleep(3000);

           checkin.id = -1;
           statusid = ust.getstatusbyname("Checked_in").id;
           ucs.saveusercheckin(checkin, bob.id, statusid, firstlocation.id, floorlocation1.id, bob.tid);
           
           Thread.sleep(3000);
           checkin.id = -1;
           statusid = ust.getstatusbyname("Checked_Out").id;
           ucs.saveusercheckin(checkin, bob.id, statusid, firstlocation.id, floorlocation1.id, bob.tid);
           
           Thread.sleep(3000);
           
           //james
           checkin.id = -1;
           statusid = ust.getstatusbyname("Just_Arrived").id;
           ucs.saveusercheckin(checkin, james.id, statusid, firstlocation.id, floorlocation2.id, james.tid);
           
           Thread.sleep(3000);
           checkin.id = -1;
           statusid = ust.getstatusbyname("Checked_in").id;
           ucs.saveusercheckin(checkin, james.id, statusid, firstlocation.id, floorlocation2.id, james.tid);
           
           Thread.sleep(3000);
           //Robert
           checkin.id = -1;
           statusid = ust.getstatusbyname("Just_Arrived").id;
           ucs.saveusercheckin(checkin, robert.id, statusid, secondlocation.id, floorlocation3.id, robert.tid);
           
           Thread.sleep(3000);
           checkin.id = -1;
           statusid = ust.getstatusbyname("Checked_in").id;
           ucs.saveusercheckin(checkin, robert.id, statusid, secondlocation.id, floorlocation3.id, robert.tid);
           
           Thread.sleep(3000);
           //Micheal
           checkin.id = -1;
           statusid = ust.getstatusbyname("Just_Arrived").id;
           ucs.saveusercheckin(checkin, Micheal.id, statusid, secondlocation.id, floorlocation4.id, Micheal.tid);
           
           Thread.sleep(3000);
           checkin.id = -1;
           statusid = ust.getstatusbyname("Checked_in").id;
           ucs.saveusercheckin(checkin, Micheal.id, statusid, secondlocation.id, floorlocation4.id, Micheal.tid);
           
           Thread.sleep(3000);
           //bob 2 he's a multitenant
           checkin.id = -1;
           statusid = ust.getstatusbyname("Just_Arrived").id;
           ucs.saveusercheckin(checkin, bob2.id, statusid, thirdlocation.id, floorlocation5.id, bob2.tid);
           
           Thread.sleep(3000);
           checkin.id = -1;
           statusid = ust.getstatusbyname("Checked_in").id;
           ucs.saveusercheckin(checkin, bob2.id, statusid, thirdlocation.id, floorlocation5.id, bob2.tid);
           
           Thread.sleep(3000);
           //Jones checkin
           checkin.id = -1;
           statusid = ust.getstatusbyname("Just_Arrived").id;
           ucs.saveusercheckin(checkin, jones.id, statusid, thirdlocation.id, floorlocation6.id, jones.tid);
           
           Thread.sleep(3000);
           checkin.id = -1;
           statusid = ust.getstatusbyname("Checked_in").id;
           ucs.saveusercheckin(checkin, jones.id, statusid, thirdlocation.id, floorlocation6.id, jones.tid);
           
           Thread.sleep(3000);
           //Garcia Checkin
           checkin.id = -1;
           statusid = ust.getstatusbyname("Just_Arrived").id;
           ucs.saveusercheckin(checkin, Garcia.id, statusid, fourthlocation.id, floorlocation7.id, Garcia.tid);
           
           Thread.sleep(3000);
           checkin.id = -1;
           statusid = ust.getstatusbyname("Checked_in").id;
           ucs.saveusercheckin(checkin, Garcia.id, statusid, fourthlocation.id, floorlocation7.id, Garcia.tid);
           
           Thread.sleep(3000);
           checkin.id = -1;
           statusid = ust.getstatusbyname("Checked_Out").id;
           ucs.saveusercheckin(checkin, Garcia.id, statusid, fourthlocation.id, floorlocation7.id, Garcia.tid);
           
           Thread.sleep(3000);
           //Jason 
           checkin.id = -1;
           statusid = ust.getstatusbyname("Just_Arrived").id;
           ucs.saveusercheckin(checkin, Jason.id, statusid, fourthlocation.id, floorlocation8.id, Jason.tid);
           
           Thread.sleep(3000);
           checkin.id = -1;
           statusid = ust.getstatusbyname("Checked_in").id;
           ucs.saveusercheckin(checkin, Jason.id, statusid, fourthlocation.id, floorlocation8.id, Jason.tid);
           Thread.sleep(3000);
           //Brian
           Thread.sleep(3000);
           checkin.id = -1;
           statusid = ust.getstatusbyname("Just_Arrived").id;
           ucs.saveusercheckin(checkin, Brian.id, statusid, fourthlocation.id, floorlocation8.id, Brian.tid);
           
           Thread.sleep(3000);
           checkin.id = -1;
           statusid = ust.getstatusbyname("Checked_in").id;
           ucs.saveusercheckin(checkin, Brian.id, statusid, fourthlocation.id, floorlocation8.id, Brian.tid);
           
           List<FdfEntity<User>> usertable = new ArrayList<>();
           List<FdfEntity<User>> userroletable = new ArrayList<>();
           System.out.println("-------------------Query B: Query for all users in each tenant----------------------------");
           //output all users in each tenant
           System.out.println("\nClients in clients table: ");
           if(clientstable.size() > 0){
              for(FdfEntity<Client> idsclient: clientstable){
                 System.out.println("---------------Client-----------------");
                 System.out.println("Client id: " + idsclient.current.id);
                 System.out.println("Client name: " + idsclient.current.name);
                 System.out.print("Users: ");
                 usertable = us.getUsersbytidwithhistory(idsclient.current.id);
                 for(FdfEntity<User> idsuser: usertable){
                     System.out.println("---------------Next User-----------------");
                     System.out.println("ID: " + idsuser.current.id);
                     System.out.println("Username: " + idsuser.current.username);
                     System.out.println("Firstname: " + idsuser.current.firstName);
                     System.out.println("Lastname: " + idsuser.current.lastName);
                     System.out.println("Color: " + idsuser.current.color + "\n");
                 }
              }
           }

           //Query for 2 users
           //You can query for a user by using their username or their id
           System.out.println("\n---------------Querying for 2 users and showing their ids-----------------");
           System.out.println("Query for first user: ");
           User query1 = new User();
           query1 = us.getCurrentUserbyusername("Bob123", Miguel.id);
           Useraddress query1address = new Useraddress();
           query1address = ua.getcurrentUseraddress(query1.id, query1.tid);
           System.out.println("User ID: " + query1.id);
           System.out.println("Username: " + query1.username);
           System.out.println("User:" + query1.firstName + " " + query1.lastName + "\n");

           if(query1address.street_address1.length() > 0 && query1address.street_address2.length() > 0){
               System.out.println("User address: " + query1address.street_address1 + ", " + query1address.street_address2);
               System.out.println("City: " + query1address.city);
               System.out.println("State: " + query1address.state);
               System.out.println("Zipcode: " + query1address.zipcode);
               System.out.println("Country: " + query1address.country);
               System.out.println("Phone Number: " + query1address.phonenumber);
           }
           else{
                System.out.println("User address: " + query1address.street_address1);
                System.out.println("City: " + query1address.city);
                System.out.println("State: " + query1address.state);
                System.out.println("Zipcode: " + query1address.zipcode);
                System.out.println("Country: " + query1address.country);
                System.out.println("Phone Number: " + query1address.phonenumber);
           }
           System.out.println("\nRoles for userid: " + query1.id);
           List<UserRole> userrole2 = urs.getUserRoleServicesbyuid(query1.id);
           if(userrole2.size() > 0){
                for(UserRole userrole3: userrole2){
                    Roles roles = rs.getRoleById(userrole3.currentroleid);
                    System.out.println("Role id: " + roles.id);
                    System.out.println("Role name: " + roles.name);
                    System.out.println("Is Active?: " + userrole.isactive + "\n");
                }
           }
           System.out.println("IDS for user: " );
           List<User_ID> query1id = ids.getallcurrentidsforauser(query1.id, query1.tid);
           if(query1id.size() > 0){
               for(User_ID userid: query1id){
                   ID_types type = its.getID_TypesById(userid.currenttypesid);
                   Gendertypes gender = gts.getGendertypesById(userid.currentgenderid);
                   System.out.println("ID Type: " + type.name);
                   System.out.println("User ID: " + userid.idnumber);
                   System.out.println("Date Issued: " + userid.dateissued.getTime());
                   System.out.println("Date Expired: " + userid.expirationdate.getTime());
                   System.out.println("Date Of Birth: " + userid.dateofbirth.getTime());
                   System.out.println("Gender: " + gender.name);
               }
           }
           //query a second user
           query1 = us.getCurrentUserbyusername("Jones12345", Jeff_Bezos.id);
           query1address = ua.getcurrentUseraddress(query1.id, query1.tid);
           System.out.println("User ID: " + query1.id);
           System.out.println("Username: " + query1.username);
           System.out.println("User:" + query1.firstName + " " + query1.lastName + "\n");
           System.out.println("User address: ");
           if(query1address.street_address1.length() > 0 && query1address.street_address2.length() > 0){
               System.out.println("User address: " + query1address.street_address1 + ", " + query1address.street_address2);
               System.out.println("City: " + query1address.city);
               System.out.println("State: " + query1address.state);
               System.out.println("Zipcode: " + query1address.zipcode);
               System.out.println("Country: " + query1address.country);
               System.out.println("Phone Number: " + query1address.phonenumber);
           }
           else{
                System.out.println("User address: " + query1address.street_address1);
                System.out.println("City: " + query1address.city);
                System.out.println("State: " + query1address.state);
                System.out.println("Zipcode: " + query1address.zipcode);
                System.out.println("Country: " + query1address.country);
                System.out.println("Phone Number: " + query1address.phonenumber);
           }
           System.out.println("\nRoles for userid: " + query1.id);
           userrole2 = urs.getUserRoleServicesbyuid(query1.id);
           if(userrole2.size() > 0){
                for(UserRole userrole3: userrole2){
                    Roles roles = rs.getRoleById(userrole3.currentroleid);
                    System.out.println("Role id: " + roles.id);
                    System.out.println("Role name: " + roles.name);
                    System.out.println("Is Active?: " + userrole.isactive + "\n");
                }
           }
           System.out.println("IDS for user: " );
           query1id = ids.getallcurrentidsforauser(query1.id, query1.tid);
           if(query1id.size() > 0){
               for(User_ID userid: query1id){
                   ID_types type = its.getID_TypesById(userid.currenttypesid);
                   Gendertypes gender = gts.getGendertypesById(userid.currentgenderid);
                   System.out.println("ID Type: " + type.name);
                   System.out.println("User ID: " + userid.idnumber);
                   System.out.println("Date Issued: " + userid.dateissued.getTime());
                   System.out.println("Date Expired: " + userid.expirationdate.getTime());
                   System.out.println("Date Of Birth: " + userid.dateofbirth.getTime());
                   System.out.println("Gender: " + gender.name);
               }
           }

           //query for modified users
           List<FdfEntity<User>> modifiedusers = new ArrayList<>();
           modifiedusers.add(us.getUserbyusernamewithhistory("James1234", Miguel.id));
           modifiedusers.add(us.getUserbyusernamewithhistory("Minnie124", Miguel.id));
           modifiedusers.add(us.getUserbyusernamewithhistory("Daisey1234", Miguel.id));
           modifiedusers.add(us.getUserbyusernamewithhistory("Eric241!", Jeff_Bezos.id));
           modifiedusers.add(us.getUserbyusernamewithhistory("Jeffreyyyyy12", Jeff_Bezos.id));
           System.out.println("----------USER-----------------");
           for(FdfEntity<User> muser: modifiedusers){
               //outputting modified current user
               System.out.println("\nPrinting out current version");
               System.out.println("Start Date: " + muser.current.arsd);
               System.out.println("End Date: " + muser.current.ared);
               System.out.println("User ID: " + muser.current.id);
               System.out.println("Name: " + muser.current.firstName + " " + muser.current.lastName);

               System.out.println("---------- History ----------");
               // Now show the historical records for the user
               for(User userhistory: muser.history){
                    System.out.println("Start Date: " + userhistory.arsd);
                    System.out.println("End Date: " + userhistory.ared);
                    System.out.println("User ID: " + userhistory.id);
                    System.out.println("Name: " + userhistory.firstName + " " + userhistory.lastName);
               }
               System.out.println("---------- Next User----------");
           }

           //Checkins per tenant
           List<UserCheckin> modifiedcheckins = new ArrayList<>();
           if(clientstable.size() > 0){
            for(FdfEntity<Client> idsclient: clientstable){
                    System.out.println("----------Client-----------------");
                    modifiedcheckins = ucs.getallcheckins(idsclient.current.id);
                    System.out.println("Client id: " + idsclient.current.id);
                    System.out.println("Client name: " + idsclient.current.name);
                    System.out.println("Client description: " + idsclient.current.description);
                    for(UserCheckin ucheck: modifiedcheckins){
                        System.out.println("----------Next Check in-----------------");
                        UserStatusTypes statusid2 = ust.getstatustypesbyID(ucheck.currentuserstatustypeid);
                        query1 = us.getUserById(ucheck.currentuserid, ucheck.tid);
                        System.out.println("Status: " + statusid2.name);
                        System.out.println("Start Date: " + ucheck.arsd);
                        System.out.println("End Date: " + ucheck.ared);
                        System.out.println("User: " + query1.firstName + " " + query1.lastName);
                        System.out.println("Destination: " + ucheck.description);
                    }
                }
           }

           //show all check ins for a specific user, and show the history
           query1 = us.getCurrentUserbyusername("Bob123", Miguel.id);
           FdfEntity<UserCheckin> modifiedcheckin = ucs.getUserCheckinbyuidwithhistory(query1.id, query1.tid);
           System.out.println("Querying for user: " + query1.username);
           System.out.println("ID: " + query1.id);
           System.out.println("Firstname " + query1.firstName);
           System.out.println("Lastname " + query1.lastName);
           System.out.println("-------------Current Check In Status-------------------");
           UserStatusTypes statusid2 = ust.getstatustypesbyID(modifiedcheckin.current.currentuserstatustypeid);
           System.out.println("Status: " + statusid2.name);
           System.out.println("Start Date: " + modifiedcheckin.current.arsd);
           System.out.println("End Date: " + modifiedcheckin.current.ared);
           System.out.println("Destination: " + modifiedcheckin.current.description);
           for(UserCheckin mc: modifiedcheckin.history){
                System.out.println("-------------History Next Entry-------------------");
                statusid2 = ust.getstatustypesbyID(mc.currentuserstatustypeid);
                System.out.println("Status: " + statusid2.name);
                System.out.println("Start Date: " + mc.arsd);
                System.out.println("End Date: " + mc.ared);
                System.out.println("Destination: " + mc.description);
           }

           //querying for all beacons on each floor
           System.out.println("\nClients in clients table: ");
           if(clientstable.size() > 0){
              for(FdfEntity<Client> idsclient: clientstable){
                 System.out.println("Client id: " + idsclient.current.id);
                 System.out.println("Client name: " + idsclient.current.name);
                 List<Locations> locationbeacon = ls.getcurrentlocationbytid(idsclient.current.id);
                 for(Locations currentlocation: locationbeacon){
                    System.out.println("-------------Location-------------------");
                     List<Floors> currentfloors = fs.getfloorbylid(currentlocation.id, idsclient.current.id);
                     System.out.println("Location name" + currentlocation.name);
                     System.out.println("Description" + currentlocation.description);
                     for(Floors floorbeacon: currentfloors){
                        System.out.println("-------------Floor-------------------");
                        System.out.println("Floor ID: " + floorbeacon.id);
                        System.out.println("Floor name: " + floorbeacon.name);
                        System.out.println("Floor description: " + floorbeacon.description);
                        List<Beacon_Report> currentreport = brs.getallbeaconreportsonafloor(floorbeacon.id, idsclient.current.id);
                        for(Beacon_Report currentbeacon: currentreport){
                            System.out.println("-------------Beacons on floor-------------------");
                            Beacons b = bs.getbeaconbyid(currentbeacon.currentbeaconid, idsclient.current.id);
                            System.out.println("UUID: " + b.uuid);
                            System.out.println("Major id: " + b.majorid);
                            System.out.println("Minor id: " + b.minorid);
                            System.out.println(currentbeacon.description);
                        }
                     }
                 }
              }
           }










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
       Roles SecurityGuard = new Roles();
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
        System.out.println("Number of id types available " + amountofrows);
    }

    private static void createuserstatustables(UserStatusTypesService ust){
        //1
        UserStatusTypes Just_Arrived = new UserStatusTypes();
        Just_Arrived.name = Userstatustypesname.Just_Arrived;
        Just_Arrived.description = "User just arrived";
        ust.savestatustypes(Just_Arrived);
        //2
        UserStatusTypes Checked_in = new UserStatusTypes();
        Checked_in.name = Userstatustypesname.Checked_in;
        Checked_in.description = "User just checked in";
        ust.savestatustypes(Checked_in);
        //3
        UserStatusTypes Checked_Out = new UserStatusTypes();
        Checked_Out.name = Userstatustypesname.Checked_Out;
        Checked_Out.description = "User just checked out";
        ust.savestatustypes(Checked_Out);

        long amountofrows = ust.numberofuserstatusids();
        System.out.println("Number of user status types available " + amountofrows);
    }

    private static void creategendertypes(GendertypesService gts){
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
        System.out.println("Number of gender types available " + amountofrows);

    }
    
}