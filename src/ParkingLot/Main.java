package ParkingLot;

import ParkingLot.model.Floor;
import ParkingLot.model.Slot;
import ParkingLot.model.SlotType;
import ParkingLot.model.Ticket;
import ParkingLot.model.Vehicle;
import ParkingLot.model.VehicleType;
import ParkingLot.service.ParkingLot;

import java.io.IOException;
import java.util.*;

public class Main {

    public static void main(String args[]) throws IOException, InterruptedException {
        List<Floor> floors= new ArrayList<>();

        // ground-floor
        Map<SlotType, Map<String, Slot>> slots = new TreeMap<>();
        Map<String, Slot> twoWheeler = new TreeMap<>();
        twoWheeler.put("T1", new Slot("T1", SlotType.TwoWheeler));
        twoWheeler.put("T2", new Slot("T2", SlotType.TwoWheeler));
        slots.put(SlotType.TwoWheeler, twoWheeler);
        Map<String, Slot> fourWheeler = new TreeMap<>();
        fourWheeler.put("F1", new Slot("F1", SlotType.FourWheeler));
        slots.put(SlotType.FourWheeler, fourWheeler);
        floors.add(new Floor("Ground", slots));

        // first-floor
        Map<SlotType, Map<String, Slot>> slotsF = new TreeMap<>();
        Map<String, Slot> twoWheelerF = new TreeMap<>();
        twoWheelerF.put("T1", new Slot("T1", SlotType.TwoWheeler));
        slotsF.put(SlotType.TwoWheeler, twoWheelerF);
        Map<String, Slot> fourWheelerF = new TreeMap<>();
        fourWheelerF.put("F1", new Slot("F1", SlotType.FourWheeler));
        slotsF.put(SlotType.FourWheeler, fourWheelerF);
        floors.add(new Floor("First", slotsF));


        ParkingLot parkingLot = new ParkingLot("ShoppingMall", floors);
        parkingLot.displayAvailableSlots();
        Optional<Ticket> ticket1 = parkingLot.assignTicket(new Vehicle("UP78", VehicleType.SUV));
        Optional<Ticket> ticket2 = parkingLot.assignTicket(new Vehicle("MH10", VehicleType.Hatchback));
        Optional<Ticket> ticket3 = parkingLot.assignTicket(new Vehicle("KA99", VehicleType.Sedan));
        parkingLot.displayAvailableSlots();
        Thread.sleep(10000);
        if(ticket2.isPresent()) {
            parkingLot.exitVehicle(ticket2.get());
        }
    }
}
