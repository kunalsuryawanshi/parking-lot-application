package com.bridgelabz;

/**
 * Purpose To Handle All ParkingLot Exceptions
 *
 * @author KUNAL SURYAWANSHI
 * @since 10/11/2021
 */
public class ParkingLotException extends Exception {
    ExceptionType exceptionType;

    public ParkingLotException(ExceptionType exceptionType, String message) {
        super(message);
        this.exceptionType = exceptionType;
    }

    public enum ExceptionType {PARKING_LOT_IS_FULL, NO_SUCH_VEHICLE, VEHICLE_ALREADY_PARKED}
}