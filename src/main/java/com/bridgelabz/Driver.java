package com.bridgelabz;

/**
 * Purpose To Driver Know About Self Vehicle Slot
 *
 * @author KUNAL SURYAWANSHI
 * @version 1.0
 * @since 10-11-2021
 */
public class Driver {

    public static int slotNo;

    /**
     * Purpose To Get Slot Number
     *
     * @param slotNo Vehicle Slot Number
     */
    public void vehicleSlotIs(int slotNo) {
        this.slotNo = slotNo;
    }

    /**
     * Purpose To Return Slot Number
     *
     * @return Vehicle Slot Number
     */
    public int showSlot() {
        return slotNo;
    }
}
