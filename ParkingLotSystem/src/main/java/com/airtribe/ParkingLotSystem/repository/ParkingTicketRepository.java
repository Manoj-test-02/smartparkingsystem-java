package com.airtribe.ParkingLotSystem.repository;

import com.airtribe.ParkingLotSystem.model.ParkingStatus;
import com.airtribe.ParkingLotSystem.model.ParkingTicket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParkingTicketRepository extends JpaRepository<ParkingTicket, Long> {
    ParkingTicket findByLicensePlateAndParkingStatus(String licensePlate, ParkingStatus parkingStatus);
}
