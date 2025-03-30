package ParkingLot.service;

import ParkingLot.model.Ticket;

public interface PaymentService {
    boolean collectPayment(double charges);
}
