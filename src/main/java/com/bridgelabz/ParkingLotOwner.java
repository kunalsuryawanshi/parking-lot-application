package com.bridgelabz;

/**
 * Purpose: To Know Lot Owner When The Lot is full & Space Available.
 *
 * @author KUNAL SURYAWANSHI
 * @since 10/11/2021
 */
public class ParkingLotOwner implements ParkingLotObserver {
    private boolean isCapacityFull;

    @Override
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