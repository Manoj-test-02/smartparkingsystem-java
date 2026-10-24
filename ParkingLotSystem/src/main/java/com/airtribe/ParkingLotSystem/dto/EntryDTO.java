package com.airtribe.ParkingLotSystem.dto;

import com.airtribe.ParkingLotSystem.model.VehicleType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public class EntryDTO {

    @NotBlank(message = "License Should not be Empty")
    @Pattern(regexp = "^[A-Z0-9-]{4,15}$", message = "License Plate should be alpha numeric")
    private String licensePlate;
    private VehicleType vehicleType;

    public EntryDTO(String licensePlate, VehicleType vehicleType) {
        this.licensePlate = licensePlate;
        this.vehicleType = vehicleType;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(VehicleType vehicleType) {
        this.vehicleType = vehicleType;
    }
}
