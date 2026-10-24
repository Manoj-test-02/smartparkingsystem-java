package com.airtribe.ParkingLotSystem.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

@Entity
public class ParkingSlot {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long parkingSlotId;

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "floorId")
    private ParkingFloor parkingFloor;

    @NotNull
    @NotEmpty
    private String slotNumber;

    @NotNull
    @Enumerated(EnumType.STRING)
    private VehicleType vehicleType;

    private boolean parked;

    public ParkingSlot(Long parkingSlotId, ParkingFloor parkingFloor, String slotNumber, VehicleType vehicleType) {
        this.parkingSlotId = parkingSlotId;
        this.parkingFloor = parkingFloor;
        this.slotNumber = slotNumber;
        this.vehicleType = vehicleType;
        this.parked = true; //Parking will always be available at the initial stage
    }

    public ParkingSlot() {
    }

    public Long getParkingSlotId() {
        return parkingSlotId;
    }

    public void setParkingSlotId(Long parkingSlotId) {
        this.parkingSlotId = parkingSlotId;
    }

    public ParkingFloor getParkingFloor() {
        return parkingFloor;
    }
    public Long getFloorId(){
        return parkingFloor.getFloorId();
    }

    public void setParkingFloor(ParkingFloor parkingFloor) {
        this.parkingFloor = parkingFloor;
    }

    public String getSlotNumber() {
        return slotNumber;
    }

    public void setSlotNumber(String slotNumber) {
        this.slotNumber = slotNumber;
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(VehicleType vehicleType) {
        this.vehicleType = vehicleType;
    }

    public boolean isParked() {
        return parked;
    }

    public void setParked(boolean parked) {
        this.parked = parked;
    }
}
