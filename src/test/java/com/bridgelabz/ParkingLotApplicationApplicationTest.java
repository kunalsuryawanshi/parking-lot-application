package com.bridgelabz;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ParkingLotApplicationApplicationTest {
    @Test
    public void givenWelcomeMessage_ShouldReturn() {
        String message = "Welcome To The Parking Lot Problem.!";
        ParkingLotApplication service = new ParkingLotApplication();
        String messageCheck = service.welcomeMessage(message);
        Assertions.assertEquals(message, messageCheck);
    }
}
