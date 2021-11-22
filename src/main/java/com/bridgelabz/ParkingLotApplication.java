package com.bridgelabz;
/******************************************************************************
 *  Purpose: To Simulate With Parking Lot Problem.
 *
 *  @author KUNAL SURYAWANSHI
 *  @version 1.0
 *  @since 10-11-2021
 ******************************************************************************/

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class ParkingLotApplication {
    private static List<ParkingSlot> parkingLot1;
    private static List<ParkingSlot> parkingLot2;
    private static List<ParkingLotObserver> observers;
    private static int actualCapacity;
    private static Police police;

    public ParkingLotApplication() {
        this.observers = new ArrayList<>();
        this.parkingLot1 = new ArrayList();
        this.parkingLot2 = new ArrayList();
        police = new Police();
    }

    /**
     * Purpose To Print Given Welcome Message
     *
     * @param message Welcome Message
     * @return Welcome Message
     */
    public String welcomeMessage(String message) {
        return message;
    }

    /**
     * Purpose To Set Capacity For Parking Lot
     *
     * @param capacity given as a Slot Capacity
     */
    public void setCapacity(int capacity) {
        this.actualCapacity = capacity;
    }

    /**
     * Purpose To Add Observer In List
     *
     * @param observer Given Observer as a Parameter For add to in List
     */
    public void registerParkingLotObserver(ParkingLotObserver observer) {
        this.observers.add(observer);
    }

    /**
     * Purpose to get Vehicle Parked Time
     *
     * @return Date and Time For Parked Vehicle
     */
    public String getDateTime() {
        LocalDateTime myDateObj = LocalDateTime.now();
        DateTimeFormatter formatTime = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String formattedDate = myDateObj.format(formatTime);
        return formattedDate;
    }

    /**
     * Purpose To Park Given Vehicle
     *
     * @param vehicle given vehicle as parameter For Park
     */
    public void park(String numberPlate, String vehicle, String vehicleColour) throws ParkingLotException {
        if (isVehicleParked(vehicle))
            throw new ParkingLotException
                    (ParkingLotException.ExceptionType.VEHICLE_ALREADY_PARKED, "Vehicle Already Parked");

        checkCapacity();

        ParkingSlot parkingSlot = new ParkingSlot(numberPlate, vehicle, vehicleColour, getDateTime());
        if (parkingLot1.size() > parkingLot2.size()) {
            this.parkingLot2.add(parkingSlot);
        } else
            this.parkingLot1.add(parkingSlot);

        checkSuspiciousVehicle(parkingSlot, vehicle);
    }

    /**
     * Purpose To add Suspicious Vehicle In List
     *
     * @param parkingSlot given parkingSlot For Check it's Suspicious or not
     * @param vehicle     given vehicle For Check it's Suspicious Vehicle or not
     */
    private void checkSuspiciousVehicle(ParkingSlot parkingSlot, String vehicle) throws ParkingLotException {
        if (parkingSlot.getVehicleColour() == "White") {
            police.addInSuspiciousVehicles(searchVehicle(vehicle), parkingSlot);
        }

        if (parkingSlot.getVehicleColour() == "Blue" && parkingSlot.getVehicle() == "Toyota") {
            police.addInSuspiciousVehicles(searchVehicle(vehicle), parkingSlot);
        }
    }

    /**
     * Purpose To check Capacity Of Slots
     *
     * @throws ParkingLotException if Capacity is full Throw Exception
     */
    private void checkCapacity() throws ParkingLotException {
        if (this.parkingLot1.size() == this.actualCapacity && this.parkingLot2.size() == this.actualCapacity) {
            for (ParkingLotObserver observer : observers) {
                observer.capacityIsFull();
            }
            throw new ParkingLotException
                    (ParkingLotException.ExceptionType.PARKING_LOT_IS_FULL, "Parking Lot is Full");
        }
    }

    /**
     * Purpose To Check a Vehicle is Parked Or Not
     *
     * @param vehicle given Vehicle as Parameter For Check is Parked Or Not
     * @return If Vehicle contains Given Vehicle
     * it will return True
     */
    public boolean isVehicleParked(Object vehicle) {
        boolean isParked = false;
        for (ParkingSlot slot : parkingLot1) {
            if (slot.getVehicle().equals(vehicle))
                isParked = true;
        }
        for (ParkingSlot slot : parkingLot2) {
            if (slot.getVehicle().equals(vehicle))
                isParked = true;
        }
        return isParked;
    }

    /**
     * Purpose To Check given Vehicle is UnParked or Not
     *
     * @param vehicle For Check Vehicle UnParked Or Not
     * @return Boolean type for Vehicle UnPark
     * @throws ParkingLotException If Condition Not Matches Then Throwing Exception Vehicle Not Found
     */
    public boolean unPark(String vehicle) throws ParkingLotException {
        if (this.parkingLot1 == null || this.parkingLot2 == null) return false;
        for (ParkingSlot slot : parkingLot1) {
            if (slot.getVehicle().equals(vehicle)) {
                this.parkingLot1.remove(vehicle);
                for (ParkingLotObserver observer : observers) {
                    observer.capacityIsAvailable();
                }
                return true;
            }
        }
        for (ParkingSlot slot : parkingLot2) {
            if (slot.getVehicle().equals(vehicle)) {
                this.parkingLot2.remove(vehicle);
                for (ParkingLotObserver observer : observers) {
                    observer.capacityIsAvailable();
                }
                return true;
            }
        }
        throw new ParkingLotException(ParkingLotException.ExceptionType.NO_SUCH_VEHICLE, "No Such Vehicle Found");
    }

    /**
     * Purpose To Search Slot Number For Parked Vehicle
     *
     * @param vehicle given Vehicle as Parameter
     * @return Vehicle Slot Number
     * @throws ParkingLotException If Vehicle Not Found Throwing Exception
     */
    public int searchVehicle(String vehicle) throws ParkingLotException {
        for (ParkingSlot slot : parkingLot1) {
            if (slot.getVehicle().equals(vehicle))
                return parkingLot1.indexOf(slot);
        }
        for (ParkingSlot slot : parkingLot2) {
            if (slot.getVehicle().equals(vehicle))
                return parkingLot2.indexOf(slot);
        }
        throw new ParkingLotException(ParkingLotException.ExceptionType.NO_SUCH_VEHICLE, "No Such Vehicle Found");
    }

    /**
     * Purpose To Get Park Time For Parked Vehicle
     *
     * @param vehicle given Vehicle as Parameter
     * @return Vehicle Park Time
     * @throws ParkingLotException If Vehicle Not Found Throw Exception
     */
    public String getParkTime(String vehicle) throws ParkingLotException {
        for (ParkingSlot slot : parkingLot1) {
            if (slot.getVehicle().equals(vehicle))
                return slot.getTime();
        }
        for (ParkingSlot slot : parkingLot2) {
            if (slot.getVehicle().equals(vehicle))
                return slot.getTime();
        }
        throw new ParkingLotException(ParkingLotException.ExceptionType.NO_SUCH_VEHICLE, "No Such Vehicle Found");
    }
}