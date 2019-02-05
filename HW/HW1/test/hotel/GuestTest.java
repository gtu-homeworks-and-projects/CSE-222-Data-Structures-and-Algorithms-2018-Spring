package hotel;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GuestTest {

    @Test
    void checkOut() {
        Guest tempGuest = new Guest("TestFirstName","TestLastName",15,10,true);
        // A checked in users checkout test
        assertEquals(10,tempGuest.checkOut());
        assertEquals(0,tempGuest.checkOut());
    }
}