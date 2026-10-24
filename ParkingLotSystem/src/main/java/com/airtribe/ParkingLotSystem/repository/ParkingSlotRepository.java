package com.airtribe.ParkingLotSystem.repository;

import com.airtribe.ParkingLotSystem.model.ParkingSlot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParkingSlotRepository extends JpaRepository<ParkingSlot, Long> {
}
