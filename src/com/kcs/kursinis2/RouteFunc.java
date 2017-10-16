package com.kcs.kursinis2;

import java.io.BufferedReader;
import java.sql.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

public class RouteFunc {

    private String licenseNumber;
    private String driver;
    private String date;
    private String destination;
    private String deparTime;
    private int deparOdo;
    private String arriveTime;
    private double loadTime;
    private String leaveTime;
    private String returnTime;
    private int returnOdo;
    private int distance;
    private double fuelConsumed;


    public void truckAdd(Connection con1) {
        Connection con = con1;
        Scanner sc = new Scanner(System.in);

//        Collect route data from console
        System.out.println("Iveskite keliones duomenis: \n");
        System.out.print("Masinos numeris: ");
        licenseNumber = sc.nextLine();
        System.out.print("Vairuotojas: ");
        driver = sc.nextLine();
        System.out.print("Data: ");
        date = sc.nextLine();
        System.out.print("Keliones tikslas: ");
        destination = sc.nextLine();
        System.out.print("Isvykimo laikas: ");
        deparTime = sc.nextLine();
        System.out.print("Isvykimo odometro parodymai: ");
        deparOdo = sc.nextInt();
        sc.nextLine();
        System.out.print("Atvykimo i objekta laikas: ");
        arriveTime = sc.nextLine();
        System.out.print("Krovimosi laikas: ");
        loadTime = sc.nextDouble();
        sc.nextLine();
        System.out.print("Isvykimo is objekto laikas: ");
        leaveTime = sc.nextLine();
        System.out.print("Grizimo i garaza laikas laikas: ");
        returnTime = sc.nextLine();
        System.out.print("Odomentro parodymai grizus: ");
        returnOdo = sc.nextInt();

//        Calculate and print for test check distance travelled and fuelConsumed
        distance = returnOdo - deparOdo;
        int minutes = Utils.timeDif(deparTime, returnTime);
        fuelConsumtion();
        System.out.println("Nuvaziavote " + distance + " km  ir uztrukote " + minutes + " " +
                "minuciu.");
        System.out.println("Sunaudojote " + fuelConsumed + " litru kuro");

//        Insert all collected and calculated data into DATABASE
        try {
            String sql = "INSERT INTO routeData ('licenseNumber') VALUES ('licenseNumber')";
            Statement st = con.createStatement();
            st.executeUpdate("INSERT INTO routeData (licenseNumber, driver, date, " +
                    "destination, departime, deparOdo, arriveTime, loadTime, leaveTime, " +
                    "returnTime, returnOdo, distance, fuelUsed) " +
                    "VALUES ('" + licenseNumber + "', '" + driver + "', '" + date + "', '" +
                    destination + "', '" + deparTime + "', '" + deparOdo + "', '" +
                    arriveTime + "', '" + loadTime + "', '" + leaveTime + "', '" +
                    returnTime + "', '" + returnOdo + "', '" + distance + "', '" +
                    fuelConsumed  + " ' )");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method for calculation of fuel consumed in one route
    private void fuelConsumtion(){

        double fuelStanding = 5;
        double fuelDriving = 15;
        double fuelLoading = 10;

        double dTime = (double) (Utils.timeDif(deparTime, arriveTime) + Utils.timeDif(leaveTime,
                returnTime)) / 60;
        double lTime = loadTime / 60;
        double sTime = ((double) Utils.timeDif(arriveTime, leaveTime) - loadTime) / 60;
        double dFuel = dTime * fuelDriving;
        double lFuel = lTime * fuelLoading;
        double sFuel = sTime * fuelStanding;

        // Print intermediate results for test checking
        System.out.println("Driving:  " + dTime + " h >> " + dFuel + " L of fuel used");
        System.out.println("Loading:  " + lTime + " h >> " + lFuel + " L of fuel used");
        System.out.println("Standing:  " + sTime + " h >> " + sFuel + " L of fuel used");

        fuelConsumed = dFuel + lFuel + sFuel;
        System.out.println("Total fuel consumed: " + fuelConsumed);
    }

    // Method for getting data from DATABASE

    public void getRouteData(Connection con2){
        Connection con = con2;
        String driv;
        int mon;
        Scanner sc = new Scanner(System.in);
        System.out.println("Pasirinkite menesi ir vairuotoja, kuriu duomenis norite gauti: ");
        System.out.print("Vairuotojas: ");
        driv = sc.nextLine();
        System.out.print("Menuo: ");
        mon = sc.nextInt();
        try {
            Statement stmt = con.createStatement();
            String sql = "SELECT * FROM routeData WHERE MONTH(date) = " + mon + " AND driver = '" +
                    driv + "'";
            ResultSet rs = stmt.executeQuery(sql);
            ResultSetMetaData rsmd = rs.getMetaData();
            int columnCount = rsmd.getColumnCount();
            double monthlyFuel = 0;

            while (rs.next()){
                for (int i = 1; i <= columnCount; i++){
                    System.out.print(rs.getString(i) + " | ");
                    if (i == columnCount){
                        monthlyFuel += rs.getDouble(i);
                    }
                }
                System.out.println();
            }
            System.out.println("Menesio kuro sunaudojimas: " + monthlyFuel + " litru.");
        } catch (SQLException e) {
            e.printStackTrace();
        }

//


    }

}
