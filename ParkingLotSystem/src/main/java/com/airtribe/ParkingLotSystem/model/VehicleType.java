package com.airtribe.ParkingLotSystem.model;

public enum VehicleType {
    BIKE(20),
    CAR(30),
    BUS(50);
    private final double cost;
    VehicleType(double cost) {
        this.cost = cost;
    }

    public double getCost() {
        return cost;
    }

}
