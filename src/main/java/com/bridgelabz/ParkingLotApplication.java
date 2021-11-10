package com.bridgelabz;

/**
 * Purpose To Simulate With Parking Lot Problem
 *
 * @author KUNAL SURYAWANSHI
 * @since 10/11/2021
 */

public class ParkingLotApplication {
    private Object vehicle;

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
     * Purpose To Park Given Vehicle
     *
     * @param vehicle given vehicle as parameter
     * @return True For Vehicle Parked
     */
    public boolean park(Object vehicle) {
        if (this.vehicle != null)
            return false;
        this.vehicle = vehicle;
        return true;
    }

    /**
     * Purpose To Check given Vehicle is UnParked or Not
     *
     * @param vehicle given vehicle as parameter
     * @return Vehicle UnParked or Not
     */
    public boolean unPark(Object vehicle) {
        if (this.vehicle == null) return false;
        if (this.vehicle.equals(vehicle)) {
            this.vehicle = null;
            return true;
        }
        return false;
    }
}
