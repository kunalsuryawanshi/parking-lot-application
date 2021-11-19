package com.bridgelabz;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ParkingLotApplicationTest {
    ParkingLotApplication parkingLotApplication;
    AirportSecurity airportSecurity;
    ParkingLotOwner owner;
    Object vehicle = null;

    @BeforeEach
    void setUp() {
        vehicle = new Object();
        owner = new ParkingLotOwner();
        airportSecurity = new AirportSecurity();
        parkingLotApplication = new ParkingLotApplication();
    }

    @Test
    public void givenWelcomeMessage_ShouldReturnEqual() {
        String message = "Welcome To The Parking Lot Application...!";
        String messageCheck = parkingLotApplication.welcomeMessage(message);
        Assertions.assertEquals(message, messageCheck);
    }

    @Test
    public void givenAVehicle_WhenParked_ShouldReturnTrue() throws ParkingLotException {
        parkingLotApplication.setCapacity(1);
        parkingLotApplication.park(vehicle);
        boolean isParked = parkingLotApplication.isVehicleParked(vehicle);
        Assertions.assertTrue(isParked);
    }

    @Test
    public void givenAVehicle_WhenAlreadyParked_ShouldReturnException() throws ParkingLotException {
        parkingLotApplication.setCapacity(1);
        parkingLotApplication.park(vehicle);
        Assertions.assertThrows(ParkingLotException.class,
                () -> parkingLotApplication.park(vehicle));
    }

    @Test
    public void givenNullVehicle_WhenUnPark_ShouldReturnException() {
        Assertions.assertThrows(ParkingLotException.class, () -> parkingLotApplication.unPark(vehicle));
    }

    @Test
    public void givenAVehicle_WhenUnParked_ShouldReturnTrue() throws ParkingLotException {
        parkingLotApplication.park(vehicle);
        boolean isUnParked = parkingLotApplication.unPark(vehicle);
        Assertions.assertTrue(isUnParked);
    }

    @Test
    public void givenVehicle_WhenParkingLotIsFull_ShouldInformTheOwner() throws ParkingLotException {
        parkingLotApplication.setCapacity(1);
        parkingLotApplication.registerParkingLotObserver(owner);
        Object vehicle2 = new Object();
        Object vehicle3 = new Object();
        parkingLotApplication.park(vehicle);
        parkingLotApplication.park(vehicle2);
        Assertions.assertThrows(Exception.class, () -> parkingLotApplication.park(vehicle3));
    }

    @Test
    public void givenCapacityIs2_ShouldBeAbleToPark2Vehicles() throws ParkingLotException {
        Object vehicle2 = new Object();
        parkingLotApplication.setCapacity(2);
        parkingLotApplication.park(vehicle);
        parkingLotApplication.park(vehicle2);
        boolean isVehicleParked1 = parkingLotApplication.isVehicleParked(vehicle);
        boolean isVehicleParked2 = parkingLotApplication.isVehicleParked(vehicle2);
        Assertions.assertTrue(isVehicleParked1 && isVehicleParked2);
    }

    @Test
    public void givenWhenParkingLotIsFull_ShouldInformTheSecurity() throws ParkingLotException {
        parkingLotApplication.setCapacity(1);
        parkingLotApplication.registerParkingLotObserver(airportSecurity);
        Object vehicle2 = new Object();
        Object vehicle3 = new Object();
        parkingLotApplication.park(vehicle);
        parkingLotApplication.park(vehicle2);
        Assertions.assertThrows(ParkingLotException.class, () -> parkingLotApplication.park(vehicle3));
    }

    @Test
    public void givenWhenParkingLotSpaceIsAvailableAfterFull_ShouldReturnTrue() throws ParkingLotException {
        parkingLotApplication.setCapacity(1);
        parkingLotApplication.park(vehicle);
        parkingLotApplication.unPark(vehicle);
        boolean capacityFull = owner.isCapacityFull();
        Assertions.assertFalse(capacityFull);
    }

    @Test
    public void givenVehicle_ToParkingAttendant_ShouldParkTheVehicle() throws ParkingLotException {
        parkingLotApplication.setCapacity(1);
        ParkingLotAttendant parkingLotAttendant = new ParkingLotAttendant();
        parkingLotAttendant.parkVehicle(vehicle);
        boolean vehicleParked = parkingLotApplication.isVehicleParked(vehicle);
        Assertions.assertTrue(vehicleParked);
    }

    @Test
    public void givenVehicle_WhenParked_ShouldReturnSlotNo() throws ParkingLotException {
        parkingLotApplication.setCapacity(3);
        parkingLotApplication.park(vehicle);
        Object vehicle2 = new Object();
        parkingLotApplication.park(vehicle2);
        int slotNum = parkingLotApplication.searchVehicle(vehicle2);
        Assertions.assertEquals(0, slotNum);
    }

    @Test
    public void givenVehicle_WhenParked_ShouldReturnTime() throws ParkingLotException {
        parkingLotApplication.setCapacity(1);
        parkingLotApplication.park(vehicle);
        String parkTime = parkingLotApplication.getParkTime(vehicle);
        Assertions.assertEquals(parkingLotApplication.getDateTime(), parkTime);
    }
}