package com.airtribe.ParkingLotSystem.model;


import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.sql.Time;
import java.time.LocalTime;

@Entity
public class ParkingTicket {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long ticketId;

    @NotNull
    @NotEmpty
    private String licensePlate;

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "parkingSlotId")
    private ParkingSlot parkingSlot;

    @Enumerated(EnumType.STRING)
    @NotNull
    private VehicleType vehicleType;

    @Nullable
    private LocalTime entryTime;
    @Nullable
    private LocalTime exitTime;
    @Nullable
    private Double totalFee;

    @Enumerated(EnumType.STRING)
    private ParkingStatus parkingStatus;

    public ParkingTicket(Long ticketId, String licensePlate, ParkingSlot parkingSlot, VehicleType vehicleType, LocalTime entryTime, LocalTime exitTime, Double totalFee, ParkingStatus parkingStatus) {
        this.ticketId = ticketId;
        this.licensePlate = licensePlate;
        this.parkingSlot = parkingSlot;
        this.vehicleType = vehicleType;
        this.entryTime = entryTime;
        this.exitTime = exitTime;
        this.totalFee = totalFee;
        this.parkingStatus = parkingStatus;
    }

    public ParkingTicket() {
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

    public ParkingSlot getParkingSlot() {
        return parkingSlot;
    }

    public void setParkingSlot(ParkingSlot parkingSlot) {
        this.parkingSlot = parkingSlot;
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(VehicleType vehicleType) {
        this.vehicleType = vehicleType;
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
        return totalFee;
    }

    public void setTotalFee(Double totalFee) {
        this.totalFee = totalFee;
    }

    public ParkingStatus getParkingStatus() {
        return parkingStatus;
    }

    public void setParkingSlotStatus(boolean parkingSlotStatus){
        parkingSlot.setParked(parkingSlotStatus);
    }

    public void setParkingStatus(ParkingStatus parkingStatus) {
        this.parkingStatus = parkingStatus;
    }

    public Long getParkingFloorNumber() {
        return parkingSlot.getFloorNumber();
    }

    public String getSlotNumber() {
        return parkingSlot.getSlotNumber();
    }
}
