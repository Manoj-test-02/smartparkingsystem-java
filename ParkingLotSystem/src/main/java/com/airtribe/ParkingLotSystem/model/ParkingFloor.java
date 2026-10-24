package com.airtribe.ParkingLotSystem.model;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;


@Entity
public class ParkingFloor {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long floorId;

    @NotNull
    private Long floorNumber;

    @NotNull
    private Long parkingLotId;

    @NotNull
    private boolean floorAvailability;

    public ParkingFloor(Long floorId, Long floorNumber, Long parkingLotId, boolean floorAvailability) {
        this.floorId = floorId;
        this.floorNumber = floorNumber;
        this.parkingLotId = parkingLotId;
        this.floorAvailability = floorAvailability;
    }

    public ParkingFloor() {
    }

    public Long getFloorId() {
        return floorId;
    }

    public void setFloorId(Long floorId) {
        this.floorId = floorId;
    }

    public Long getFloorNumber() {
        return floorNumber;
    }

    public void setFloorNumber(Long floorNumber) {
        this.floorNumber = floorNumber;
    }

    public Long getParkingLotId() {
        return parkingLotId;
    }

    public void setParkingLotId(Long parkingLotId) {
        this.parkingLotId = parkingLotId;
    }

    public boolean isFloorAvailability() {
        return floorAvailability;
    }

    public void setFloorAvailability(boolean floorAvailability) {
        this.floorAvailability = floorAvailability;
    }
}
