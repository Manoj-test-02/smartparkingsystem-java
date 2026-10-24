package com.airtribe.ParkingLotSystem.exception;

public class ParkingSlotNotAvailableException extends RuntimeException {
    public ParkingSlotNotAvailableException(String message) {
        super(message);
    }
}
