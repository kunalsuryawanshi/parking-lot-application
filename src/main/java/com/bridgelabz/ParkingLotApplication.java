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
     * @return Vehicle Parked Return True
     */
    public void park(Object vehicle) throws ParkingLotException {
        if (this.vehicle != null)
            throw new ParkingLotException("Parking Lot is Full");
        this.vehicle = vehicle;
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

    /**
     * Purpose To Check a Vehicle is Parked Or Not
     *
     * @param vehicle given Vehicle
     * @return If Vehicle Equal to Given Vehicle
     * it will return True or False
     */
    public boolean isVehicleParked(Object vehicle) {
        return this.vehicle.equals(vehicle);
    }

    /**
     * Purpose To Check a Vehicle is UnParked Or Not
     *
     * @param vehicle given Vehicle
     * @return Vehicle Equal to null -> Vehicle is UnParked and return True
     */
    public boolean isVehicleUnParked(Object vehicle) {
        return this.vehicle == null;
    }
}
