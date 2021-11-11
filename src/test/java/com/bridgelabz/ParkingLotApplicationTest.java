package com.bridgelabz;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ParkingLotApplicationTest {
    public final int capacity = 3;
    ParkingLotApplication service;
    ParkingLotOwner owner;
    Object vehicle = null;

    @BeforeEach
    void setUp() {
        vehicle = new Object();
        owner = new ParkingLotOwner();
        service = new ParkingLotApplication(capacity);
    }

    @Test
    public void givenWelcomeMessage_ShouldReturnEqual() {
        String message = "Welcome To The Parking Lot Problem.!";
        String messageCheck = service.welcomeMessage(message);
        Assertions.assertEquals(message, messageCheck);
    }

    @Test
    public void givenAVehicle_WhenParked_ShouldReturnTrue() {
        try {
            service.park(vehicle);
            boolean isParked = service.isVehicleParked(vehicle);
            Assertions.assertTrue(isParked);
        } catch (ParkingLotException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenAVehicle_WhenAlreadyParked_ShouldReturnException() {
        try {
            service.park(vehicle);
            service.park(new Object());
        } catch (ParkingLotException e) {
            Assertions.assertEquals("Parking Lot is Full", e.getMessage());
            e.printStackTrace();
        }
    }

    @Test
    public void givenNullVehicle_WhenUnPark_ShouldReturnException() {
        try {
            service.unPark(vehicle);
        } catch (ParkingLotException e) {
            Assertions.assertEquals("No Such Vehicle Found", e.getMessage());
            e.printStackTrace();
        }
    }

    @Test
    public void givenAVehicle_WhenUnParked_ShouldReturnTrue() {
        try {
            service.park(vehicle);
            service.unPark(vehicle);
            boolean isUnParked = service.isVehicleUnParked(vehicle);
            Assertions.assertTrue(isUnParked);
        } catch (ParkingLotException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenVehicle_WhenParkingLotIsFull_ShouldInformTheOwner() {
        service.registerOwner(owner);
        try {
            service.park(vehicle);
            service.park(new Object());
            service.park(new Object());
        } catch (ParkingLotException e) {
            Assertions.assertEquals("Parking Lot is Full", e.getMessage());
            e.printStackTrace();
        }
    }

    @Test
    public void givenAVehicle_WhenParkingLotIsFull_ShouldInformSecurity() {
        AirportSecurity security = new AirportSecurity();
        service.registerOwner(owner);
        service.registerAirportSecurity(security);
        try {
            service.park(vehicle);
            service.park(new Object());
            service.park(new Object());
        } catch (ParkingLotException e) {
            Assertions.assertEquals("Parking Lot is Full", e.getMessage());
            e.printStackTrace();
        }
    }
}
