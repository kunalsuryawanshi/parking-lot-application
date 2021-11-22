package com.bridgelabz;

import java.util.HashMap;

/******************************************************************************
 *  Purpose To add Suspicious Vehicle in List So Police Can Investigate
 *
 *  @author KUNAL SURYAWANSHI
 *  @version 1.0
 *  @since 10-11-2021
 ******************************************************************************/
public class Police {
    public static HashMap<Integer, ParkingSlot> SuspiciousVehicles;

    public Police() {
        this.SuspiciousVehicles = new HashMap();
    }

    public static boolean isVehicleAdded(int slotNumber) {
        return SuspiciousVehicles.containsKey(slotNumber);
    }

    public void addInSuspiciousVehicles(int slotNo, ParkingSlot parkingSlot) {
        SuspiciousVehicles.put(slotNo, parkingSlot);
    }
}
