package com.airtribe.ParkingLotSystem.util;

import com.airtribe.ParkingLotSystem.model.VehicleType;

import java.time.Duration;
import java.time.LocalTime;

public class StandardFee implements FeeStrategy{

    public Double calculateFee(VehicleType vehicleType, LocalTime entryTime, LocalTime exitTime){

        if(entryTime == null || exitTime == null){
            throw new IllegalArgumentException("Entry Time or Exit Time cannot be null");
        }
        long totalMinutes =
                Duration.between(entryTime, exitTime).toMinutes();
        if(totalMinutes <= 0){
            throw new IllegalArgumentException("Total Minutes should be greater than 0");
        }

        Double billableHours = Double.valueOf((totalMinutes + 59) / 60);
        billableHours = Math.max(1, billableHours);
        return billableHours * vehicleType.getCost();
    }
}
