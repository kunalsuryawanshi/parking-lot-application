package com.bridgelabz;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ParkingLotApplicationApplicationTest {
    ParkingLotApplication service;
    Object vehicle = null;

    @BeforeEach
    void setUp() {
        vehicle = new Object();
        service = new ParkingLotApplication();
    }

    @Test
    public void givenWelcomeMessage_ShouldReturnEqual() {
        String message = "Welcome To The Parking Lot Problem.!";
        String messageCheck = service.welcomeMessage(message);
        Assertions.assertEquals(message, messageCheck);
    }

    @Test
    public void givenAVehicle_WhenParked_ShouldReturnTrue() {
        boolean isParked = service.park(new Object());
        Assertions.assertTrue(isParked);
    }

    @Test
    public void givenAVehicle_WhenAlreadyParked_ShouldReturnFalse() {
        service.park(vehicle);
        boolean isParked = service.park(new Object());
        Assertions.assertFalse(isParked);
    }

    @Test
    public void givenAVehicle_WhenUnParked_ShouldReturnTrue() {
        service.park(vehicle);
        boolean isUnParked = service.unPark(vehicle);
        Assertions.assertTrue(isUnParked);
    }
}
