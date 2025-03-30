package ParkingLot.service;

import ParkingLot.model.Ticket;
import ParkingLot.model.Vehicle;

import java.util.Optional;

public interface ParkingLotService {
    Optional<Ticket> assignTicket(Vehicle vehicle);
    double exitVehicle(Ticket ticket);
    void displayAvailableSlots();
}
