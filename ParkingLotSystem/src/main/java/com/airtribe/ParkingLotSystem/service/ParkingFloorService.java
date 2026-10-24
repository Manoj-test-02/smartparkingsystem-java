package com.airtribe.ParkingLotSystem.service;


import com.airtribe.ParkingLotSystem.exception.ParkingFloorNotFoundException;
import com.airtribe.ParkingLotSystem.model.ParkingFloor;
import com.airtribe.ParkingLotSystem.repository.ParkingFloorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ParkingFloorService {

    @Autowired
    private ParkingFloorRepository parkingFloorRepository;

    public ParkingFloor addParkingFacility(ParkingFloor parkingFloor) {
        return parkingFloorRepository.save(parkingFloor);
    }

    public List<ParkingFloor> findAllParkingFloors() {
            if(!parkingFloorRepository.findAll().isEmpty()) {
                return parkingFloorRepository.findAll();
            }
            throw new ParkingFloorNotFoundException("No ParkingFloor found");
    }
}
