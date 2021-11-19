package com.bridgelabz;

/******************************************************************************
 *  Purpose To Park Vehicles Through Attendant.
 *
 *  @author KUNAL SURYAWANSHI
 *  @version 1.0
 *  @since 10-11-2021
 ******************************************************************************/
public class ParkingLotAttendant {
    ParkingLotApplication parkingLotApplication = new ParkingLotApplication();

    public void parkVehicle(Object vehicle) throws ParkingLotException {
        parkingLotApplication.park(vehicle);
    }
}