package com.bridgelabz;

import java.time.LocalTime;

/******************************************************************************
 *  @author KUNAL SURYAWANSHI
 *  @version 1.0
 *  @since 10-11-2021
 ******************************************************************************/
public class ParkingSlot {
    private final String numberPlate;
    private final String vehicle;
    private final LocalTime time;
    private final VehicleType vehicleType;
    private final PersonType personType;
    private String vehicleColour;

    public ParkingSlot(String numberPlate, String vehicle, String vehicleColour,
                       VehicleType vehicleType, PersonType personType, LocalTime time) {
        this.numberPlate = numberPlate;
        this.vehicle = vehicle;
        this.vehicleColour = vehicleColour;
        this.time = time;
        this.vehicleType = vehicleType;
        this.personType = personType;
    }

    public String getVehicle() {
        return vehicle;
    }

    public LocalTime getTime() {
        return time;
    }

    public String getVehicleColour() {
        return vehicleColour;
    }

    @Override
    public String toString() {
        return "ParkingSlot{" +
                "vehicle=" + vehicle +
                ", time='" + time + '\'' +
                '}';
    }

    public enum VehicleType {LARGE, NORMAL}

    public enum PersonType {NORMAL, HANDICAP}
}