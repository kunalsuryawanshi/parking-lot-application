package com.bridgelabz;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ParkingLotApplicationTest {
    ParkingLotApplication parkingLotApplication;
    AirportSecurity airportSecurity;
    ParkingLotOwner owner;

    @BeforeEach
    void setUp() {
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
        parkingLotApplication.park("MH04 AK5481", "vehicle", "Red",
                ParkingSlot.VehicleType.NORMAL, ParkingSlot.PersonType.NORMAL);
        boolean isParked = parkingLotApplication.isVehicleParked("vehicle");
        Assertions.assertTrue(isParked);
    }

    @Test
    public void givenAVehicle_WhenAlreadyParked_ShouldReturnException() throws ParkingLotException {
        parkingLotApplication.setCapacity(1);
        parkingLotApplication.park("MH04 AK5481", "vehicle", "Red",
                ParkingSlot.VehicleType.NORMAL, ParkingSlot.PersonType.NORMAL);
        Assertions.assertThrows(ParkingLotException.class,
                () -> parkingLotApplication.park("MH04 AK5481", "vehicle", "Red",
                        ParkingSlot.VehicleType.NORMAL, ParkingSlot.PersonType.NORMAL));
    }

    @Test
    public void givenNullVehicle_WhenUnPark_ShouldReturnException() {
        Assertions.assertThrows(ParkingLotException.class,
                () -> parkingLotApplication.unPark("vehicle"));
    }

    @Test
    public void givenAVehicle_WhenUnParked_ShouldReturnTrue() throws ParkingLotException {
        parkingLotApplication.park("MH04 AK5481", "vehicle", "Red",
                ParkingSlot.VehicleType.NORMAL, ParkingSlot.PersonType.NORMAL);
        boolean isUnParked = parkingLotApplication.unPark("vehicle");
        Assertions.assertTrue(isUnParked);
    }

    @Test
    public void givenVehicle_WhenParkingLotIsFull_ShouldInformTheOwner() throws ParkingLotException {
        parkingLotApplication.setCapacity(1);
        parkingLotApplication.registerParkingLotObserver(owner);
        parkingLotApplication.park
                ("MH04 AK5481", "vehicle", "Red", ParkingSlot.VehicleType.NORMAL,
                        ParkingSlot.PersonType.NORMAL);
        parkingLotApplication.park
                ("MH04 AK5481", "vehicle2", "Red", ParkingSlot.VehicleType.NORMAL,
                        ParkingSlot.PersonType.NORMAL);
        Assertions.assertThrows(Exception.class, () -> parkingLotApplication.park
                ("MH04 DQ5551", "vehicle3", "Red", ParkingSlot.VehicleType.NORMAL,
                        ParkingSlot.PersonType.NORMAL));
    }

    @Test
    public void givenCapacityIs2_ShouldBeAbleToPark2Vehicles() throws ParkingLotException {
        parkingLotApplication.setCapacity(2);
        parkingLotApplication.park("MH04 AK5481", "vehicle", "Red",
                ParkingSlot.VehicleType.NORMAL, ParkingSlot.PersonType.NORMAL);
        parkingLotApplication.park("MH04 AK5481", "vehicle2", "Red",
                ParkingSlot.VehicleType.NORMAL, ParkingSlot.PersonType.NORMAL);
        boolean isVehicleParked1 = parkingLotApplication.isVehicleParked("vehicle");
        boolean isVehicleParked2 = parkingLotApplication.isVehicleParked("vehicle2");
        Assertions.assertTrue(isVehicleParked1 && isVehicleParked2);
    }

    @Test
    public void givenWhenParkingLotIsFull_ShouldInformTheSecurity() throws ParkingLotException {
        parkingLotApplication.setCapacity(1);
        parkingLotApplication.registerParkingLotObserver(airportSecurity);
        parkingLotApplication.park("MH04 AK5481", "vehicle", "Red",
                ParkingSlot.VehicleType.NORMAL, ParkingSlot.PersonType.NORMAL);
        parkingLotApplication.park("MH04 AK5481", "vehicle2", "Red",
                ParkingSlot.VehicleType.NORMAL, ParkingSlot.PersonType.NORMAL);
        Assertions.assertThrows(ParkingLotException.class, () -> parkingLotApplication.park
                ("MH04 AW8999", "vehicle3", "Red", ParkingSlot.VehicleType.LARGE,
                        ParkingSlot.PersonType.NORMAL));
    }

    @Test
    public void givenWhenParkingLotSpaceIsAvailableAfterFull_ShouldReturnTrue() throws ParkingLotException {
        parkingLotApplication.setCapacity(1);
        parkingLotApplication.park
                ("MH04 AK5481", "vehicle", "Red", ParkingSlot.VehicleType.NORMAL,
                        ParkingSlot.PersonType.NORMAL);
        parkingLotApplication.unPark("vehicle");
        boolean capacityFull = owner.isCapacityFull();
        Assertions.assertFalse(capacityFull);
    }

    @Test
    public void givenVehicle_ToParkingAttendant_ShouldParkTheVehicle() throws ParkingLotException {
        parkingLotApplication.setCapacity(1);
        ParkingLotAttendant parkingLotAttendant = new ParkingLotAttendant();
        parkingLotAttendant.parkVehicle("MH04 AK5481", "vehicle",
                "Red", ParkingSlot.VehicleType.NORMAL, ParkingSlot.PersonType.NORMAL);
        boolean vehicleParked = parkingLotApplication.isVehicleParked("vehicle");
        Assertions.assertTrue(vehicleParked);
    }

    @Test
    public void givenVehicle_WhenParked_ShouldReturnSlotNo() throws ParkingLotException {
        parkingLotApplication.setCapacity(3);
        parkingLotApplication.park
                ("MH04 AK5481", "vehicle", "Red", ParkingSlot.VehicleType.NORMAL,
                        ParkingSlot.PersonType.NORMAL);
        parkingLotApplication.park
                ("MH04 AK5481", "vehicle2", "Red",
                        ParkingSlot.VehicleType.NORMAL, ParkingSlot.PersonType.NORMAL);
        int slotNum = parkingLotApplication.searchVehicle("vehicle2");
        Assertions.assertEquals(0, slotNum);
    }

    @Test
    public void givenVehicle_WhenParked_ShouldReturnTime() throws ParkingLotException {
        parkingLotApplication.setCapacity(1);
        parkingLotApplication.park
                ("MH04 AK5481", "vehicle", "Black",
                        ParkingSlot.VehicleType.NORMAL, ParkingSlot.PersonType.NORMAL);
        String parkTime = parkingLotApplication.getParkTime("vehicle");
        Assertions.assertEquals(parkingLotApplication.getDateTime(), parkTime);
    }

    @Test
    public void givenWhiteVehicle_WhenParked_ShouldInformPoliceDepartment() throws ParkingLotException {
        parkingLotApplication.setCapacity(2);
        parkingLotApplication.park
                ("MH04 AE0001", "vehicle", "White",
                        ParkingSlot.VehicleType.NORMAL, ParkingSlot.PersonType.NORMAL);
        parkingLotApplication.checkSuspiciousVehicleByColour("White");
        int slotNo = parkingLotApplication.searchVehicle("vehicle");
        boolean isVehicleAdded = Police.isVehicleAdded(slotNo);
        Assertions.assertTrue(isVehicleAdded);
    }

    @Test
    public void givenVehicle_WhenBlueToyota_ShouldInformPolice() throws ParkingLotException {
        parkingLotApplication.setCapacity(2);
        parkingLotApplication.park("MH04 AX5668", "Ford", "Black",
                ParkingSlot.VehicleType.NORMAL, ParkingSlot.PersonType.NORMAL);
        parkingLotApplication.park("MH04 AZ7894", "Toyota", "Blue",
                ParkingSlot.VehicleType.NORMAL, ParkingSlot.PersonType.NORMAL);
        parkingLotApplication.checkSuspiciousVehicle("Toyota", "Blue");
        int slotNo = parkingLotApplication.searchVehicle("Toyota");
        boolean isVehicleAdded = Police.isVehicleAdded(slotNo);
        Assertions.assertTrue(isVehicleAdded);
    }

    @Test
    public void givenVehicle_WhenBMW_ShouldInformPolice() throws ParkingLotException {
        parkingLotApplication.setCapacity(1);
        parkingLotApplication.park("MH04 AB5668", "BMW", "Red",
                ParkingSlot.VehicleType.NORMAL, ParkingSlot.PersonType.NORMAL);
        parkingLotApplication.checkSuspiciousVehicle("BMW");
        int slotNo = parkingLotApplication.searchVehicle("BMW");
        boolean isVehicleAdded = Police.isVehicleAdded(slotNo);
        Assertions.assertTrue(isVehicleAdded);
    }

    @Test
    public void givenVehicle_WhenLarge_ShouldParkInHighestNumberOfFreeSpace() throws ParkingLotException {
        parkingLotApplication.setCapacity(2);
        parkingLotApplication.park("MH04 AB5668", "BMW", "Red",
                ParkingSlot.VehicleType.NORMAL, ParkingSlot.PersonType.NORMAL);
        parkingLotApplication.park("MH04 CZ7856", "Toyota", "Red",
                ParkingSlot.VehicleType.LARGE, ParkingSlot.PersonType.NORMAL);
        int isVehicleAdded = parkingLotApplication.searchVehicle("Toyota");
        Assertions.assertEquals(0, isVehicleAdded);
    }

    @Test
    public void givenVehicle_WhenNumberPlateIsCorrect_ShouldReturnTrue() {
        parkingLotApplication.setCapacity(1);
        boolean checkNumberPlate = Police.checkNumberPlate("MH04 AB7814");
        Assertions.assertTrue(checkNumberPlate);
    }

    @Test
    public void givenVehicle_WhenNumberPlateIsIncorrect_ShouldReturnFalse() {
        parkingLotApplication.setCapacity(1);
        boolean checkNumberPlate = Police.checkNumberPlate("04MH 7814");
        Assertions.assertFalse(checkNumberPlate);
    }

    @Test
    public void givenVehicle_WhenPersonIsHandicap_ShouldParkInNearestFreeSpaceLot() throws ParkingLotException {
        parkingLotApplication.setCapacity(2);
        parkingLotApplication.park("MH04 AB5668", "BMW", "Red",
                ParkingSlot.VehicleType.NORMAL, ParkingSlot.PersonType.HANDICAP);
    }
}