package ParkingLot.service;

import ParkingLot.model.Floor;
import ParkingLot.model.Slot;
import ParkingLot.model.SlotType;
import ParkingLot.model.Ticket;
import ParkingLot.model.Vehicle;

import java.util.*;

public class ParkingLot  implements ParkingLotService{
    String name;
    List<Floor> floors;
    PaymentService paymentService;

    public ParkingLot(String name, List<Floor> floors) {
        System.out.println("Initialised parking lot: " + name);
        this.name = name;
        this.floors = floors;
        this.paymentService = new Payment();
    }

    @Override
    public Optional<Ticket> assignTicket(Vehicle vehicle) {
        SlotType slotType = Slot.getSlotTypeForVehicleType(vehicle.getVehicleType());
        for(Floor floor: floors) {
            Map<String, Slot> compatibleSlots = floor.getSlots().get(slotType);
            for (String slotName: compatibleSlots.keySet()) {
                if(compatibleSlots.get(slotName).isVacant()) {
                    compatibleSlots.get(slotName).parkVehicle(vehicle);
                    System.out.println("Assigned "+vehicle.getNumber()+" on floor, slot="+floor.getName()+","+slotName);
                    return Optional.of(new Ticket(compatibleSlots.get(slotName), vehicle));
                }
            }
        }
        return Optional.empty();
    }

    @Override
    public double exitVehicle(Ticket ticket) {
        ticket.getSlot().removeVehicle();
        double durationHr = ((System.currentTimeMillis() - ticket.getStartTime())/1000.0)/3600.0;
        double charge = durationHr*ticket.getSlot().getSlotType().getHourlyRate();
        paymentService.collectPayment(charge);
        return charge;
    }

    @Override
    public void displayAvailableSlots() {
        for(Floor floor: floors) {
            System.out.println("Floor: "+floor.getName());
            for(SlotType parkingType: floor.getSlots().keySet()) {
                System.out.print(parkingType + " VacantCount=");
                Map<String, Slot> slots= floor.getSlots().get(parkingType);
                int freeSlots = 0;
                for(String slotName: slots.keySet()) {
                    freeSlots += slots.get(slotName).isVacant()?1:0;
                }
                System.out.println(freeSlots);
            }
        }
    }

}
