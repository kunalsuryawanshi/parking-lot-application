package com.bridgelabz;

/**
 * Purpose To Know Airport Security When The Lot is full & When Space Available.
 *
 * @author KUNAL SURYAWANSHI
 * @since 10/11/2021
 */
public class AirportSecurity implements ParkingLotObserver {
    private boolean isCapacityFull;

    public void capacityIsFull() {
        isCapacityFull = true;
    }

    @Override
    public void capacityIsAvailable() {
        isCapacityFull = false;
    }

    public boolean isCapacityFull() {
        return this.isCapacityFull;
    }
}