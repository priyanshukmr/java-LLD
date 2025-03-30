package ParkingLot.model;

public class Ticket {
    Slot slot;
    Vehicle vehicle;
    long startTime;

    public Ticket(Slot slot, Vehicle vehicle) {
        this.slot = slot;
        this.vehicle = vehicle;
        this.startTime = System.currentTimeMillis();
    }

    public Slot getSlot() {
        return slot;
    }

    public long getStartTime() {
        return startTime;
    }

}
