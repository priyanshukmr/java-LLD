package ParkingLot.model;

public class Vehicle {
    String number;
    VehicleType vehicleType;

    public Vehicle(String number, VehicleType vehicleType) {
        this.number = number;
        this.vehicleType = vehicleType;
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }

    public String getNumber() {
        return number;
    }
}
