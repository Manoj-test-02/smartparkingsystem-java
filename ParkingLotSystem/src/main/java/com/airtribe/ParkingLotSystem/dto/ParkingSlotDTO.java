package com.airtribe.ParkingLotSystem.dto;

import com.airtribe.ParkingLotSystem.model.VehicleType;

public class ParkingSlotDTO {

    private Long floorId;
    private String slotNumber;
    private VehicleType vehicleType;

    public ParkingSlotDTO(Long floorId, String slotNumber, VehicleType vehicleType) {
        this.floorId = floorId;
        this.slotNumber = slotNumber;
        this.vehicleType = vehicleType;
    }

    public ParkingSlotDTO() {
    }

    public Long getFloorId() {
        return floorId;
    }

    public void setFloorId(Long floorId) {
        this.floorId = floorId;
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
}
