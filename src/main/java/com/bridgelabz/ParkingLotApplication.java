package com.bridgelabz;
/******************************************************************************
 *  Purpose: To Simulate With Parking Lot Problem.
 *
 *  @author KUNAL SURYAWANSHI
 *  @version 1.0
 *  @since 10-11-2021
 ******************************************************************************/

import java.util.ArrayList;
import java.util.List;

public class ParkingLotApplication {
    public static List<ParkingSlot> vehicles;
    private static List<ParkingLotObserver> observers;
    private static int actualCapacity;

    public ParkingLotApplication() {
        this.observers = new ArrayList<>();
        this.vehicles = new ArrayList();
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
     * @param capacity given parameter as a capacity
     */
    public void setCapacity(int capacity) {
        this.actualCapacity = capacity;
    }

    /**
     * Purpose To Add Observer In List
     *
     * @param observer Given Observer as a Parameter
     */
    public void registerParkingLotObserver(ParkingLotObserver observer) {
        this.observers.add(observer);
    }

    /**
     * Purpose To Park Given Vehicle
     *
     * @param vehicle given vehicle as parameter
     */
    public void park(Object vehicle, String time) throws ParkingLotException {
        if (this.vehicles.size() == this.actualCapacity) {
            for (ParkingLotObserver observer : observers) {
                observer.capacityIsFull();
            }
            throw new ParkingLotException("Parking Lot is Full");
        }
        if (isVehicleParked(vehicle))
            throw new ParkingLotException("Vehicle Already Parked");
        ParkingSlot parkingSlot = new ParkingSlot(vehicle, time);
        this.vehicles.add(parkingSlot);

    }

    /**
     * Purpose To Check a Vehicle is Parked Or Not
     *
     * @param vehicle given Vehicle as Parameter
     * @return If Vehicle contains Given Vehicle
     * it will return True
     */
    public boolean isVehicleParked(Object vehicle) {
        boolean check = false;
        for (ParkingSlot slot : vehicles) {
            if (slot.getVehicle().equals(vehicle))
                check = true;
        }
        return check;
    }

    /**
     * Purpose To Check given Vehicle is UnParked or Not
     *
     * @param vehicle given vehicle as parameter
     * @return Boolean type for Vehicle UnPark
     * @throws ParkingLotException If Condition Not Matches Then Throwing Exception Vehicle Not Found
     */
    public boolean unPark(Object vehicle) throws ParkingLotException {
        if (this.vehicles == null) return false;
        for (ParkingSlot slot : vehicles) {
            if (slot.getVehicle().equals(vehicle)) {
                this.vehicles.remove(vehicle);
                for (ParkingLotObserver observer : observers) {
                    observer.capacityIsAvailable();
                }
                return true;
            }
        }
        throw new ParkingLotException("No Such Vehicle Found");
    }

    /**
     * Purpose To Search Slot Number For Parked Vehicle
     *
     * @param vehicle given Vehicle as Parameter
     * @return Vehicle Slot Number
     * @throws ParkingLotException If Vehicle Not Found Throwing Exception
     */
    public int searchVehicle(Object vehicle) throws ParkingLotException {
        for (ParkingSlot slot : vehicles) {
            if (slot.getVehicle().equals(vehicle))
                return vehicles.indexOf(slot);
        }
        throw new ParkingLotException("No Such Vehicle Found");
    }

    /**
     * Purpose To Get Park Time For Parked Vehicle
     *
     * @param vehicle given Vehicle as Parameter
     * @return Vehicle Park Time
     * @throws ParkingLotException If Vehicle Not Found Throw Exception
     */
    public String getParkTime(Object vehicle) throws ParkingLotException {
        for (ParkingSlot slot : vehicles) {
            if (slot.getVehicle().equals(vehicle))
                return slot.getTime();
        }
        throw new ParkingLotException("No Such Vehicle Found");
    }
}
