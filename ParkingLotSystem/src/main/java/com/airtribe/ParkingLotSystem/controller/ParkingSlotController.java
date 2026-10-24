package com.airtribe.ParkingLotSystem.controller;


import com.airtribe.ParkingLotSystem.model.ParkingSlot;
import com.airtribe.ParkingLotSystem.dto.ParkingSlotDTO;
import com.airtribe.ParkingLotSystem.service.ParkingSlotService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ParkingSlotController {

    @Autowired
    private ParkingSlotService parkingSlotService;

    @PostMapping("/api/addparkingslots")
    public ResponseEntity<ParkingSlot> addParkingSlots(@Valid @RequestBody ParkingSlotDTO parkingSlotDTO) {
        return ResponseEntity.status(HttpStatus.OK).body(parkingSlotService.addParkingSlot(parkingSlotDTO));
    }
}
