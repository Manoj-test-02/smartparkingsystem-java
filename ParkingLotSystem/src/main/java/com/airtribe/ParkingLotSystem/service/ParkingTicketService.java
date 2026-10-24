package com.airtribe.ParkingLotSystem.service;


import com.airtribe.ParkingLotSystem.dto.EntryDTO;
import com.airtribe.ParkingLotSystem.dto.EntryTicketDTO;
import com.airtribe.ParkingLotSystem.dto.ExitTicketDTO;
import com.airtribe.ParkingLotSystem.exception.VehicleNotFoundException;
import com.airtribe.ParkingLotSystem.model.*;
import com.airtribe.ParkingLotSystem.repository.ParkingSlotRepository;
import com.airtribe.ParkingLotSystem.repository.ParkingTicketRepository;
import com.airtribe.ParkingLotSystem.util.ParkingFee;
import com.airtribe.ParkingLotSystem.util.StandardFee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.List;

import static com.airtribe.ParkingLotSystem.util.ParkingSlotMap.findAvailableSlot;

@Service
public class ParkingTicketService {

    @Autowired
    private ParkingTicketRepository parkingTicketRepository;

    @Autowired
    private ParkingSlotRepository parkingSlotRepository;

    public EntryTicketDTO reserveSpot(EntryDTO entryDTO) {
        List<ParkingSlot> parkingSlots = parkingSlotRepository.findAll();
        ParkingSlot slot = findAvailableSlot(parkingSlots, entryDTO.getVehicleType());
        LocalTime entryTime = LocalTime.now();
        ParkingTicket parkingTicket = new ParkingTicket(null, entryDTO.getLicensePlate(), slot,
                entryDTO.getVehicleType(), entryTime, null, null, ParkingStatus.ACTIVE);
        slot.setParked(false);
        parkingTicketRepository.save(parkingTicket);
        EntryTicketDTO entryTicket = new EntryTicketDTO(parkingTicket.getTicketId(), parkingTicket.getLicensePlate(),
                parkingTicket.getParkingFloorNumber(), parkingTicket.getSlotNumber(), parkingTicket.getEntryTime());
        return entryTicket;
    }

    public ExitTicketDTO releaseSpot(String licensePlate) {
        ParkingFee feeStrategy = new ParkingFee();
        feeStrategy.setFeeStrategy(new StandardFee());
        ParkingTicket parkingTicketOptional = parkingTicketRepository.findByLicensePlateAndParkingStatus(licensePlate, ParkingStatus.ACTIVE);
        if(parkingTicketOptional==null){
            throw new VehicleNotFoundException("Vehicle with the License Plate: "+ licensePlate + "is not available");
        }
        LocalTime vehicleexit = LocalTime.now();
        Double totalFee = feeStrategy.parkingFee(parkingTicketOptional.getVehicleType(), parkingTicketOptional.getEntryTime(), vehicleexit);
        parkingTicketOptional.setExitTime(vehicleexit);
        parkingTicketOptional.setTotalFee(totalFee);
        parkingTicketOptional.setParkingStatus(ParkingStatus.COMPLETED);
        parkingTicketOptional.setParkingSlotStatus(true);
        parkingTicketRepository.save(parkingTicketOptional);

        ExitTicketDTO exitTicket = new ExitTicketDTO(parkingTicketOptional.getTicketId(), parkingTicketOptional.getLicensePlate(),
                parkingTicketOptional.getEntryTime(), parkingTicketOptional.getExitTime(),
                parkingTicketOptional.getTotalFee());
        return exitTicket;
    }
}
