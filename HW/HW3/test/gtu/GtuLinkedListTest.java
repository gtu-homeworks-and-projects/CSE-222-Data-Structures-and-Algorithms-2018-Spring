package gtu;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GtuLinkedListTest {
    private GtuLinkedList<GtuCseCourses.GTUCourse> test = new GtuLinkedList<>(new GtuCseCourses().getCourses());

    /**
     * Test disabled lists size
     */
    @Test
    void disable() {
        test.disable(0);
        test.disable(2);
        assertEquals(2,test.showDisabled().size());
    }

    /**
     * Tests disabled lists size
     */
    @Test
    void enable() {
        disable();
        test.enable(2);
        assertEquals(1,test.showDisabled().size());
    }

    /**
     * Tests disabled items information
     */
    @Test
    void showDisabled() {
        test.disable(0);
        assertEquals("XXX",test.showDisabled().getFirst().courseCode[1]);
        assertEquals(1,test.showDisabled().getFirst().semester);
    }
}