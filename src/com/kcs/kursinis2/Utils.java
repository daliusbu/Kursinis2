package com.kcs.kursinis2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalTime;

import static java.time.temporal.ChronoUnit.MINUTES;

public class Utils {

    public static void createTableTrucks(Connection con){
        try {
            Statement stmt = con.createStatement();
            String sql = "CREATE TABLE TruckData " +
                    "(id INTEGER not NULL, " +
                    "truckModel VARCHAR(255), " +
                    "licenseNumber VARCHAR(20), " +
                    "fuelStanding FLOAT(5,3), " +
                    "fuelDriving FLOAT(5,3), " +
                    "fuelLoading FLOAT(5,3), " +
                    "PRIMARY KEY (id))";
            stmt.executeUpdate(sql);
            System.out.println("Created table TruckData in the database");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void createTableRoutes(Connection con){
        try {
            Statement stmt = con.createStatement();
            String sql = "CREATE TABLE RouteData " +
                    "(id INTEGER not NULL, " +
                    "licenseNumber VARCHAR(20), " +
                    "driver VARCHAR (100), " +
                    "date DATE , " +
                    "destination  VARCHAR(100), " +
                    "deparTime  TIME, " +
                    "deparOdo  INT, " +
                    "arriveTime  TIME, " +
                    "loadTime  INT, " +
                    "leaveTime  TIME, " +
                    "returnTime  TIME, " +
                    "returnOdo  INT, " +
                    "distance  INT, " +
                    "fuelUsed FLOAT(5,3), " +
                    "PRIMARY KEY (id))";
            stmt.executeUpdate(sql);
            System.out.println("Created table TruckData in the database");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static int timeDif(String a, String b){
        int min;
        LocalTime t1 = LocalTime.parse( a );
        LocalTime t2 = LocalTime.parse( b );
        return min = (int)t1.until(t2, MINUTES);
    }

//    Datos palyginimas su siandiena ir skirtumo dienomis gavimas
//        Date thisdate = Calendar.getInstance().getTime();
//            //diff in msec
//            long diff = thisdate.getTime() - date.getTime();
//            //diff in days
//            long diffdays = diff / (24 * 60 * 60 * 1000);
//            System.out.println(diffdays);
}
