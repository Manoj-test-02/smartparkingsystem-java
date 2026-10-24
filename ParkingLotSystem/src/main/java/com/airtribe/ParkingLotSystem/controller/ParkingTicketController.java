package com.airtribe.ParkingLotSystem.controller;


import com.airtribe.ParkingLotSystem.dto.EntryDTO;
import com.airtribe.ParkingLotSystem.dto.EntryTicketDTO;
import com.airtribe.ParkingLotSystem.dto.ExitTicketDTO;
import com.airtribe.ParkingLotSystem.service.ParkingTicketService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ParkingTicketController {

    @Autowired
    private ParkingTicketService parkingTicketService;

    @PostMapping("/api/entry")
    public ResponseEntity<EntryTicketDTO> reserveParkingSpot(@Valid @RequestBody EntryDTO entryDTO) {
        return ResponseEntity.status(HttpStatus.OK).body(parkingTicketService.reserveSpot(entryDTO));
    }

    @PutMapping("/api/exits/{licenseplate}")
    public ResponseEntity<ExitTicketDTO> releaseParkingSpot(@PathVariable("licenseplate") String licenseplate){
        return ResponseEntity.status(HttpStatus.OK).body(parkingTicketService.releaseSpot(licenseplate));
    }
}
