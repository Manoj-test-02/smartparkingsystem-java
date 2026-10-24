package com.airtribe.ParkingLotSystem.service;


import com.airtribe.ParkingLotSystem.exception.ParkingFloorNotFoundException;
import com.airtribe.ParkingLotSystem.model.ParkingFloor;
import com.airtribe.ParkingLotSystem.model.ParkingSlot;
import com.airtribe.ParkingLotSystem.dto.ParkingSlotDTO;
import com.airtribe.ParkingLotSystem.repository.ParkingFloorRepository;
import com.airtribe.ParkingLotSystem.repository.ParkingSlotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ParkingSlotService {

    @Autowired
    private ParkingSlotRepository parkingSlotRepository;

    @Autowired
    private ParkingFloorRepository parkingFloorRepository;

    public ParkingSlot addParkingSlot(ParkingSlotDTO parkingSlotDTO) {
        if(!parkingFloorRepository.findById(parkingSlotDTO.getFloorId()).isPresent()) {
            throw new ParkingFloorNotFoundException("Parking Floor with ID: " + parkingSlotDTO.getFloorId() + "is not Present");
        }
        ParkingFloor floor = parkingFloorRepository.findById(parkingSlotDTO.getFloorId()).get();
        ParkingSlot slot = new  ParkingSlot(null, floor,  parkingSlotDTO.getSlotNumber(),parkingSlotDTO.getVehicleType());
        return parkingSlotRepository.save(slot);
    }
}
