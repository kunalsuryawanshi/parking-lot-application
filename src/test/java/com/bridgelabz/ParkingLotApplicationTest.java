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
        service.setCapacity(1);
    }

    @Test
    public void givenWelcomeMessage_ShouldReturnEqual() {
        String message = "Welcome To The Parking Lot Problem.!";
        String messageCheck = service.welcomeMessage(message);
        Assertions.assertEquals(message, messageCheck);
    }

    @Test
    public void givenAVehicle_WhenParked_ShouldReturnTrue() throws ParkingLotException {
        service.park(vehicle, "15:00");
        boolean isParked = service.isVehicleParked(vehicle);
        Assertions.assertTrue(isParked);
    }

    @Test
    public void givenAVehicle_WhenAlreadyParked_ShouldReturnException() throws ParkingLotException {
        service.park(vehicle, "15:00");
        Assertions.assertThrows(ParkingLotException.class, () -> service.park(vehicle, "15:00"));
    }

    @Test
    public void givenNullVehicle_WhenUnPark_ShouldReturnException() {
        Assertions.assertThrows(ParkingLotException.class, () -> service.unPark(vehicle));
    }

    @Test
    public void givenAVehicle_WhenUnParked_ShouldReturnTrue() throws ParkingLotException {
        service.park(vehicle, "15:00");
        boolean isUnParked = service.unPark(vehicle);
        Assertions.assertTrue(isUnParked);
    }

    @Test
    public void givenVehicle_WhenParkingLotIsFull_ShouldInformTheOwner() throws ParkingLotException {
        service.registerParkingLotObserver(owner);
        Object vehicle2 = new Object();
        try {
            service.park(vehicle, "15:00");
            service.park(vehicle2, "15:00");
            boolean capacityFull = owner.isCapacityFull();
            Assertions.assertTrue(capacityFull);
        } catch (ParkingLotException e) {
        }
    }

    @Test
    public void givenCapacityIs2_ShouldBeAbleToPark2Vehicles() throws ParkingLotException {
        Object vehicle2 = new Object();
        service.setCapacity(2);
        service.park(vehicle, "15:00");
        service.park(vehicle2, "15:00");
        boolean isVehicleParked1 = service.isVehicleParked(vehicle);
        boolean isVehicleParked2 = service.isVehicleParked(vehicle2);
        Assertions.assertTrue(isVehicleParked1 && isVehicleParked2);
    }

    @Test
    public void givenWhenParkingLotIsFull_ShouldInformTheSecurity() {
        service.registerParkingLotObserver(airportSecurity);
        Object vehicle2 = new Object();
        try {
            service.park(vehicle, "15:00");
            service.park(vehicle2, "15:00");
            boolean capacityFull = airportSecurity.isCapacityFull();
            Assertions.assertTrue(capacityFull);
        } catch (ParkingLotException e) {
        }
    }

    @Test
    public void givenWhenParkingLotSpaceIsAvailableAfterFull_ShouldReturnTrue() {
        service.registerParkingLotObserver(owner);
        try {
            service.park(vehicle, "15:00");
            service.park(new Object(), "15:00");
            service.unPark(vehicle);
            boolean capacityFull = owner.isCapacityFull();
            Assertions.assertFalse(capacityFull);
        } catch (ParkingLotException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenVehicle_ToParkingAttendant_ShouldParkTheVehicle() throws ParkingLotException {
        ParkingLotAttendant parkingLotAttendant = new ParkingLotAttendant();
        parkingLotAttendant.parkVehicle(vehicle, "15:00");
        boolean vehicleParked = service.isVehicleParked(vehicle);
        Assertions.assertTrue(vehicleParked);
    }

    @Test
    public void givenVehicle_WhenParked_ShouldReturnSlotNo() throws ParkingLotException {
        service.setCapacity(3);
        service.park(vehicle, "15:00");
        Object vehicle2 = new Object();
        service.park(vehicle2, "15:00");
        int slotNum = service.searchVehicle(vehicle2);
        Assertions.assertEquals(1, slotNum);
    }

    @Test
    public void givenVehicle_WhenParked_ShouldReturnTime() throws ParkingLotException {
        service.park(vehicle, "15:00");
        String parkTime = service.getParkTime(vehicle);
        Assertions.assertEquals("15:00", parkTime);
    }
}
