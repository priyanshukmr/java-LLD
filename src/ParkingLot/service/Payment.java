package ParkingLot.service;

public class Payment implements PaymentService{

    @Override
    public boolean collectPayment(double charges) {
        System.out.println("collected dollars: "+ charges);
        return true;
    }
}
