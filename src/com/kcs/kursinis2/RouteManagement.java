package com.kcs.kursinis2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class RouteManagement {
    public static void main(String[] args) {
        Connection con = null;

        try {
            con = DriverManager.getConnection
                    ("jdbc:mysql://localhost:3306/vaztarastis",
                    "", "");
            if (con != null){
                System.out.println("Connected!");
            }
        } catch (SQLException e) {
            System.out.println("Nepavyko prisijungti" + e);;
        }

        if (con != null) {
//            Sukuria Sunkvezimiu lentele
//            Utils.createTableTrucks(con);

//            Sukuria marsutu lentele
//            Utils.createTableRoutes(con);

//            Sukuria objekta ir pakeicia sunkvezimio duomenis, pasirinkus sunkvezimio Nr
//            TruckFunc truckFunc = new TruckFunc();
//            truckFunc.truckUpdate(con);

            RouteFunc route = new RouteFunc();
//            Papildo marsrutu lentele vienu irasu
//            route.truckAdd(con);

//            Istraukia ir atspausdina consoleje visus duomenis is routeData lenteles
            route.getRouteData(con);


        }



    }
}
