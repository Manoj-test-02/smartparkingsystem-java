package com.airtribe.ParkingLotSystem.util;

import com.airtribe.ParkingLotSystem.model.VehicleType;

import java.time.LocalTime;

public class ParkingFee {

    private FeeStrategy feeStrategy;

    public void setFeeStrategy(FeeStrategy feeStrategy) {
        this.feeStrategy = feeStrategy;
    }

    public Double parkingFee(VehicleType vehicleType, LocalTime entryTime, LocalTime exitTime) {
        if(feeStrategy == null) {
            throw new IllegalStateException("FeeStrategy has not been set");
        }
        return feeStrategy.calculateFee(vehicleType, entryTime, exitTime);
    }
}
