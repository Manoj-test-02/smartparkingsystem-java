package com.airtribe.ParkingLotSystem.util;

import com.airtribe.ParkingLotSystem.exception.ParkingSlotNotAvailableException;
import com.airtribe.ParkingLotSystem.model.ParkingSlot;
import com.airtribe.ParkingLotSystem.model.VehicleType;

import java.util.List;

public class ParkingSlotMap {

    public static ParkingSlot findAvailableSlot (List<ParkingSlot> parkingSlots, VehicleType vehicleType){
        return parkingSlots.stream()
                .filter(slot -> slot.isParked())
                .filter(slot -> slot.getVehicleType() == vehicleType)
                .findFirst()
                .orElseThrow(() ->
                        new ParkingSlotNotAvailableException(
                                "No available parking slot for " + vehicleType));
    }
}
