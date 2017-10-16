package com.kcs.kursinis2;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class TruckFunc {

    private String model;
    private String license;
    private double fuelStanding;
    private double fuelDriving;
    private double fuelLoading;
    Connection con;


    public void truckAdd(Connection con){
        this.con = con;
        Scanner sc = new Scanner(System.in);
        System.out.print("Iveskite sunkvezimio pavadinima: ");
        model = sc.nextLine();
        System.out.print("Iveskite sunkvezimio numeri: ");
        license = sc.nextLine();
        System.out.print("Iveskite kuro sanaudas STOVINT: ");
        fuelStanding = sc.nextDouble();
        System.out.print("Iveskite kuro sanaudas VAZIUOJANT: ");
        fuelDriving = sc.nextDouble();
        System.out.print("Iveskite kuro sanaudas KRAUNANT: ");
        fuelLoading = sc.nextDouble();
        System.out.println("Ivesta: " + model + " " + license + " " +
                " Stovint: " + fuelStanding +
        "l/100 km, Vaziuojant: " + fuelDriving +
        "l/100 km, Kraunant: " + fuelLoading);
        sqlIsert();
    }

    private void sqlIsert(){
        try {
            Statement stmt = con.createStatement();
            String sql = "INSERT INTO truckData VALUES (DEFAULT, '" + model + "', '" +
                    license + "', " + fuelStanding + ", " + fuelDriving + ", " + fuelLoading
                    + ") ";
            stmt.executeUpdate(sql);
            System.out.println("Truck data upload success!");

        } catch (SQLException e) {
            System.out.println("Duomenu ivedimo SQL klaida" + e);
        }
    }

    public void truckUpdate(Connection con){
        this.con = con;
        Scanner sc = new Scanner(System.in);
        System.out.print("Iveskite sunkvezimio, kurio parametrus keisite, numeri: ");
        license = sc.nextLine();
        System.out.print("Iveskite sunkvezimio pavadinima: ");
        model = sc.nextLine();
        System.out.print("Iveskite kuro sanaudas STOVINT: ");
        fuelStanding = sc.nextDouble();
        System.out.print("Iveskite kuro sanaudas VAZIUOJANT: ");
        fuelDriving = sc.nextDouble();
        System.out.print("Iveskite kuro sanaudas KRAUNANT: ");
        fuelLoading = sc.nextDouble();
        System.out.println("Ivesta: " + model + " " + license + " " +
                " Stovint: " + fuelStanding +
                "l/100 km, Vaziuojant: " + fuelDriving +
                "l/100 km, Kraunant: " + fuelLoading);
        sqlUpdate();
    }

    private void sqlUpdate(){
        try {
            Statement stmt = con.createStatement();
            String sql = "UPDATE truckData SET truckModel = '" + model + "', licenseNumber = '" +
                    license + "', fuelStanding = " + fuelStanding + ", fuelDriving = " +
                    fuelDriving + ", fuelLoading = " + fuelLoading +
                    " WHERE licenseNumber = '" + license + "'";

//            String sql = "UPDATE truckData SET truckModel = '" + model + "', licenseNumber =" +
//                    "'" + license + " ', fuelStanding = " + fuelStanding + ", " +
//                    "fuelDriving = " + fuelDriving + ", fuelLoading = " + fuelLoading +
//                    " WHERE licenseNumber = '" + license + "'";


            stmt.executeUpdate(sql);
            System.out.println("Truck data upload success!");

        } catch (SQLException e) {
            System.out.println("Duomenu ivedimo SQL klaida" + e);
        }
    }



}
