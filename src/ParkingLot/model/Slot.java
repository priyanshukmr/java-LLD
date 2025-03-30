package ParkingLot.model;

public class Slot {
    String name;
    SlotType slotType;
    Vehicle vehicle;

    public Slot(String name, SlotType slotType) {
        this.name = name;
        this.slotType = slotType;
    }

    public SlotType getSlotType() {
        return slotType;
    }

    public static SlotType getSlotTypeForVehicleType(VehicleType vehicleType) {
        return switch(vehicleType) {
            case Bike -> SlotType.TwoWheeler;
            case Hatchback,Sedan,SUV -> SlotType.FourWheeler;
            case Truck -> SlotType.Large;
        };
    }

    public boolean isVacant() {
        return vehicle==null;
    }

    public boolean parkVehicle(Vehicle vehicle) {
        if(!isVacant()) {
            return false;
        }
        this.vehicle = vehicle;
        return true;
    }

    public boolean removeVehicle() {
        if(isVacant()) {
            return false;
        }
        this.vehicle = null;
        return true;
    }

}
