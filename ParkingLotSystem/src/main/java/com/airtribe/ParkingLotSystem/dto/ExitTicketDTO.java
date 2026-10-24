package com.airtribe.ParkingLotSystem.dto;

import java.time.LocalTime;

public class ExitTicketDTO {

    private Long ticketId;
    private String licensePlate;
    private LocalTime entryTime;
    private LocalTime exitTime;
    private Double TotalFee;

    public ExitTicketDTO(Long ticketId, String licensePlate, LocalTime entryTime, LocalTime exitTime, Double totalFee) {
        this.ticketId = ticketId;
        this.licensePlate = licensePlate;
        this.entryTime = entryTime;
        this.exitTime = exitTime;
        this.TotalFee = totalFee;
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

    public LocalTime getExitTime() {
        return exitTime;
    }

    public void setExitTime(LocalTime exitTime) {
        this.exitTime = exitTime;
    }

    public Double getTotalFee() {
        return TotalFee;
    }

    public void setTotalFee(Double totalFee) {
        TotalFee = totalFee;
    }
}
