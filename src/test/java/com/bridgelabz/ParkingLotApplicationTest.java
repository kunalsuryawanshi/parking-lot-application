package com.bridgelabz;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ParkingLotApplicationTest {
    public int capacity = 1;
    ParkingLotApplication service;
    AirportSecurity airportSecurity;
    ParkingLotOwner owner;
    Object vehicle = null;

    @BeforeEach
    void setUp() {
        vehicle = new Object();
        owner = new ParkingLotOwner();
        airportSecurity = new AirportSecurity();
        service = new ParkingLotApplication(capacity);
    }

    @Test
    public void givenWelcomeMessage_ShouldReturnEqual() {
        String message = "Welcome To The Parking Lot Problem.!";
        String messageCheck = service.welcomeMessage(message);
        Assertions.assertEquals(message, messageCheck);
    }

    @Test
    public void givenAVehicle_WhenParked_ShouldReturnTrue() throws ParkingLotException {
        service.park(vehicle);
        boolean isParked = service.isVehicleParked(vehicle);
        Assertions.assertTrue(isParked);
    }

    @Test
    public void givenAVehicle_WhenAlreadyParked_ShouldReturnException() throws ParkingLotException {
        service.registerParkingLotObserver(airportSecurity);
        service.park(vehicle);
        Assertions.assertThrows(ParkingLotException.class, () -> service.park(vehicle));
    }

    @Test
    public void givenNullVehicle_WhenUnPark_ShouldReturnException() {
        Assertions.assertThrows(ParkingLotException.class, () -> service.unPark(vehicle));
    }

    @Test
    public void givenAVehicle_WhenUnParked_ShouldReturnTrue() throws ParkingLotException {
        service.park(vehicle);
        boolean isUnParked = service.unPark(vehicle);
        Assertions.assertTrue(isUnParked);
    }

    @Test
    public void givenVehicle_WhenParkingLotIsFull_ShouldInformTheOwner() throws ParkingLotException {
        service.registerParkingLotObserver(owner);
        Object vehicle2 = new Object();
        service.park(vehicle);
        service.park(vehicle2);
        boolean capacityFull = owner.isCapacityFull();
        Assertions.assertTrue(capacityFull);
    }

    @Test
    public void givenCapacityIs2_ShouldBeAbleToPark2Vehicles() throws ParkingLotException {
        Object vehicle2 = new Object();
        service.setCapacity(2);
        service.park(vehicle);
        service.park(vehicle2);
        boolean isVehicleParked1 = service.isVehicleParked(vehicle);
        boolean isVehicleParked2 = service.isVehicleParked(vehicle2);
        Assertions.assertTrue(isVehicleParked1 && isVehicleParked2);
    }

    @Test
    public void givenWhenParkingLotIsFull_ShouldInformTheSecurity() throws ParkingLotException {
        service.registerParkingLotObserver(airportSecurity);
        Object vehicle2 = new Object();
        service.park(vehicle);
        service.park(vehicle2);
        boolean capacityFull = airportSecurity.isCapacityFull();
        Assertions.assertTrue(capacityFull);
    }

    @Test
    public void givenWhenParkingLotSpaceIsAvailableAfterFull_ShouldReturnTrue() throws ParkingLotException {
        service.registerParkingLotObserver(owner);
        service.park(vehicle);
        service.park(new Object());
        service.unPark(vehicle);
        boolean capacityFull = owner.isCapacityFull();
        Assertions.assertFalse(capacityFull);
    }
}
