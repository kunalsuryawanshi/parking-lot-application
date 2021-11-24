package com.bridgelabz;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/******************************************************************************
 *  Purpose To add Suspicious Vehicle in List So Police Can Investigate
 *
 *  @author KUNAL SURYAWANSHI
 *  @version 1.0
 *  @since 10-11-2021
 ******************************************************************************/
public class Police {
    public static HashMap<Integer, ParkingSlot> suspiciousVehicles;
    public static HashMap<Integer, ParkingSlot> parkedVehicleInLast30Min;

    public Police() {
        this.suspiciousVehicles = new HashMap();
        this.parkedVehicleInLast30Min = new HashMap();
    }

    public static boolean isVehicleAdded(int slotNumber) {
        return suspiciousVehicles.containsKey(slotNumber);
    }

    /**
     * Purpose To Check Vehicle Number Plates
     *
     * @param vehicleNumberPlate for Check fraudulent Number Plate
     * @return If Pattern Matches return true else false
     */
    public static boolean checkNumberPlate(String vehicleNumberPlate) {
        Pattern pattern = Pattern.compile("^[A-Z]{2}[0-9]{2}[ ][A-Z]{2}[0-9]{4}$");
        Matcher matcher = pattern.matcher(vehicleNumberPlate);
        return matcher.matches();
    }

    public void addInSuspiciousVehicles(int slotNo, ParkingSlot parkingSlot) {
        suspiciousVehicles.put(slotNo, parkingSlot);
    }

    public void parkedVehicleInLast30Min(int slotNo, ParkingSlot parkingSlot) {
        parkedVehicleInLast30Min.put(slotNo, parkingSlot);
    }
}