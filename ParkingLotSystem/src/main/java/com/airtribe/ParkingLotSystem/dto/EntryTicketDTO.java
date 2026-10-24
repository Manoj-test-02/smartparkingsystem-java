package com.airtribe.ParkingLotSystem.dto;

import java.time.LocalTime;

public class EntryTicketDTO {

    private Long ticketId;
    private String licensePlate;
    private Long floorNumber;
    private String slotNumber;
    private LocalTime entryTime;

    public EntryTicketDTO(Long ticketId, String licensePlate, Long floorNumber, String slotNumber, LocalTime entryTime) {
        this.ticketId = ticketId;
        this.licensePlate = licensePlate;
        this.floorNumber = floorNumber;
        this.slotNumber = slotNumber;
        this.entryTime = entryTime;
    }

    public Long getTicketId() {
        return ticketId;
    }

    public void setTicketId(Long ticketId) {
        this.ticketId = ticketId;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public Long getFloorNumber() {
        return floorNumber;
    }

    public void setFloorNumber(Long floorNumber) {
        this.floorNumber = floorNumber;
    }

    public String getSlotNumber() {
        return slotNumber;
    }

    public void setSlotNumber(String slotNumber) {
        this.slotNumber = slotNumber;
    }

    public LocalTime getEntryTime() {
        return entryTime;
    }

    public void setEntryTime(LocalTime entryTime) {
        this.entryTime = entryTime;
    }
}
