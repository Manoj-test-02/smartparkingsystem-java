package com.airtribe.ParkingLotSystem.controller;


import com.airtribe.ParkingLotSystem.model.ParkingFloor;
import com.airtribe.ParkingLotSystem.service.ParkingFloorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ParkingFloorController {

    @Autowired
    private ParkingFloorService parkingFloorService;

    @PostMapping("/api/addnewfloors")
    public ResponseEntity<ParkingFloor> addNewFloor(@Valid @RequestBody ParkingFloor newFloor){
        return ResponseEntity.status(HttpStatus.OK).body(parkingFloorService.addParkingFacility(newFloor));
    }

    @GetMapping("/availablefloors")
    public ResponseEntity<List<ParkingFloor>> getAvailableFloors(){
        return ResponseEntity.status(HttpStatus.OK).body(parkingFloorService.findAllParkingFloors());
    }
}
