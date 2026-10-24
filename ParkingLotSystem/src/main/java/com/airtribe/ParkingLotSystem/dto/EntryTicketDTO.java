package com.airtribe.ParkingLotSystem.dto;

import java.time.LocalTime;

public class EntryTicketDTO {

    private Long ticketId;
    private String licensePlate;
    private LocalTime entryTime;

    public EntryTicketDTO(Long ticketId, String licensePlate, LocalTime entryTime) {
        this.ticketId = ticketId;
        this.licensePlate = licensePlate;
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

    public LocalTime getEntryTime() {
        return entryTime;
    }

    public void setEntryTime(LocalTime entryTime) {
        this.entryTime = entryTime;
    }
}
