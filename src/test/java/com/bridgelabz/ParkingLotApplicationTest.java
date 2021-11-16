package com.bridgelabz;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ParkingLotApplicationTest {
    ParkingLotApplication service;
    AirportSecurity airportSecurity;
    ParkingLotOwner owner;
    Object vehicle = null;

    @BeforeEach
    void setUp() {
        vehicle = new Object();
        owner = new ParkingLotOwner();
        airportSecurity = new AirportSecurity();
        service = new ParkingLotApplication();
    }

    @Test
    public void givenWelcomeMessage_ShouldReturnEqual() {
        String message = "Welcome To The Parking Lot Problem.!";
        String messageCheck = service.welcomeMessage(message);
        Assertions.assertEquals(message, messageCheck);
    }

    @Test
    public void givenAVehicle_WhenParked_ShouldReturnTrue() throws ParkingLotException {
        service.setCapacity(1);
        service.park(vehicle);
        boolean isParked = service.isVehicleParked(vehicle);
        Assertions.assertTrue(isParked);
    }

    @Test
    public void givenAVehicle_WhenAlreadyParked_ShouldReturnException() throws ParkingLotException {
        service.setCapacity(1);
        service.park(vehicle);
        Assertions.assertThrows(ParkingLotException.class,
                () -> service.park(vehicle));
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
        service.setCapacity(1);
        service.registerParkingLotObserver(owner);
        Object vehicle2 = new Object();
        service.park(vehicle);
        Assertions.assertThrows(Exception.class, () -> service.park(vehicle2));
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
        service.setCapacity(1);
        service.registerParkingLotObserver(airportSecurity);
        Object vehicle2 = new Object();
        service.park(vehicle);
        Assertions.assertThrows(ParkingLotException.class, () -> service.park(vehicle2));
    }

    @Test
    public void givenWhenParkingLotSpaceIsAvailableAfterFull_ShouldReturnTrue() throws ParkingLotException {
        service.setCapacity(1);
        service.park(vehicle);
        service.unPark(vehicle);
        boolean capacityFull = owner.isCapacityFull();
        Assertions.assertFalse(capacityFull);
    }

    @Test
    public void givenVehicle_ToParkingAttendant_ShouldParkTheVehicle() throws ParkingLotException {
        service.setCapacity(1);
        ParkingLotAttendant parkingLotAttendant = new ParkingLotAttendant();
        parkingLotAttendant.parkVehicle(vehicle);
        boolean vehicleParked = service.isVehicleParked(vehicle);
        Assertions.assertTrue(vehicleParked);
    }

    @Test
    public void givenVehicle_WhenParked_ShouldReturnSlotNo() throws ParkingLotException {
        service.setCapacity(3);
        service.park(vehicle);
        Object vehicle2 = new Object();
        service.park(vehicle2);
        int slotNum = service.searchVehicle(vehicle2);
        Assertions.assertEquals(1, slotNum);
    }

    @Test
    public void givenVehicle_WhenParked_ShouldReturnTime() throws ParkingLotException {
        service.setCapacity(1);
        service.park(vehicle);
        String parkTime = service.getParkTime(vehicle);
        Assertions.assertEquals(service.getTime(), parkTime);
    }
}