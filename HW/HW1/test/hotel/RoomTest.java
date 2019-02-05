package hotel;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RoomTest {
    private final Room testRoom = new Room(15);

    @Test
    void assignGuest() {
        assertEquals(true,testRoom.assignGuest(10));
        assertEquals(false,testRoom.assignGuest(5));
    }

    @Test
    void cancelAssign() {
        assertEquals(false,testRoom.cancelAssign());
        testRoom.assignGuest(10);
        assertEquals(true,testRoom.cancelAssign());
    }
}