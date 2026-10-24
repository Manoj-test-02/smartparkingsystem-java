package com.airtribe.ParkingLotSystem.util;

import com.airtribe.ParkingLotSystem.model.VehicleType;

import java.time.LocalTime;

public interface FeeStrategy {
    Double calculateFee(VehicleType vehicleType, LocalTime entryTime, LocalTime exitTime);
}
