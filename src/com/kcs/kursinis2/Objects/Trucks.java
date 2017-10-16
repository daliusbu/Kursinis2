package com.kcs.kursinis2.Objects;

public class Trucks {
    private String model;
    private String license;
    private double fuelStanding;
    private double fuelDriving;
    private double fuelLoading;

    public Trucks(String model, String license, double fuelStanding, double fuelDriving, double fuelLoading) {
        this.model = model;
        this.license = license;
        this.fuelStanding = fuelStanding;
        this.fuelDriving = fuelDriving;
        this.fuelLoading = fuelLoading;
    }

    public String getModel() {
        return model;
    }

    public String getLicense() {
        return license;
    }

    public double getFuelStanding() {
        return fuelStanding;
    }

    public double getFuelDriving() {
        return fuelDriving;
    }

    public double getFuelLoading() {
        return fuelLoading;
    }
}
