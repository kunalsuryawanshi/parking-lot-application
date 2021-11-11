package com.bridgelabz;

/**
 * Purpose To throw a Given Message in Exception
 *
 * @author KUNAL SURYAWANSHI
 * @since 10/11/2021
 */
public class AirportSecurity {
    public void parkingLotIsFull(String message) throws ParkingLotException {
        throw new ParkingLotException(message);
    }
}
