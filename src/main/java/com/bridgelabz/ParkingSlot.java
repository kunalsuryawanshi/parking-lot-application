package com.bridgelabz;

/******************************************************************************
 *  @author KUNAL SURYAWANSHI
 *  @version 1.0
 *  @since 10-11-2021
 ******************************************************************************/
public class ParkingSlot {
    private final String numberPlate;
    private final String vehicle;
    private final String time;
    private final VehicleType vehicleType;
    private String vehicleColour;

    public ParkingSlot(String numberPlate, String vehicle, String vehicleColour, VehicleType vehicleType, String time) {
        this.numberPlate = numberPlate;
        this.vehicle = vehicle;
        this.vehicleColour = vehicleColour;
        this.time = time;
        this.vehicleType = vehicleType;
    }

    public Object getVehicle() {
        return vehicle;
    }

    public String getTime() {
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
}