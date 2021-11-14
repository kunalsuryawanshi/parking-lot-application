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
    public static List vehicles;
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
    public void park(Object vehicle) throws ParkingLotException {
        if (this.vehicles.size() == this.actualCapacity) {
            for (ParkingLotObserver observer : observers) {
                observer.capacityIsFull();
            }
            throw new ParkingLotException("Parking Lot is Full");
        }
        if (isVehicleParked(vehicle))
            throw new ParkingLotException("Vehicle Already Parked");
        this.vehicles.add(vehicle);

    }

    /**
     * Purpose To Check a Vehicle is Parked Or Not
     *
     * @param vehicle given Vehicle as Parameter
     * @return If Vehicle contains Given Vehicle
     * it will return True
     */
    public boolean isVehicleParked(Object vehicle) {
        return this.vehicles.contains(vehicle);
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
        if (this.vehicles.contains(vehicle)) {
            this.vehicles.remove(vehicle);
            for (ParkingLotObserver observer : observers) {
                observer.capacityIsAvailable();
            }
            return true;
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
        Driver driver = new Driver();
        if (this.vehicles.contains(vehicle)) {
            int slotNo = vehicles.indexOf(vehicle);
            driver.vehicleSlotIs(slotNo);
            return slotNo;
        }
        throw new ParkingLotException("No Such Vehicle Found");
    }
}
