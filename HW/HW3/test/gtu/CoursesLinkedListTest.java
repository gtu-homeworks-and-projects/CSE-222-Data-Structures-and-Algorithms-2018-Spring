package gtu;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CoursesLinkedListTest {
    private CoursesLinkedList test;
    private GtuCseCourses referenceList = new GtuCseCourses();

    /**
     * Checks if added element is in place and correct
     */
    @Test
    void add() {
        test = new CoursesLinkedList();
        test.add(referenceList.getCourses().get(1));
        assertEquals(1, test.size());
        assertEquals(true,test.hasNext());
        assertEquals("101",test.next().courseCode[1]);
    }

    /**
     * checks first elements course code, removes it. then checks again.
     */
    @Test
    void remove() {
        test = new CoursesLinkedList(referenceList.getCourses());
        assertEquals("XXX",test.next().courseCode[1]);
        test.remove(0);
        test.rewindList();
        assertEquals("101",test.next().courseCode[1]);
    }

    @Test
    void next() {
        test = new CoursesLinkedList(referenceList.getCourses());
        assertEquals(true, test.hasNext());
        assertEquals(1,test.next().semester);
        assertEquals("101", test.next().courseCode[1]);
    }

    /**
     * Circles one time around first semester. Then checks second courses course code.
     */
    @Test
    void nextInSemester() {
        test = new CoursesLinkedList(referenceList.getCourses());
        for (int i = 0; i < test.getSemesterSize(1); i++) {
            test.nextInSemester();
        }
        test.nextInSemester();
        assertEquals("101",test.nextInSemester().courseCode[1]);
    }

    @Test
    void getSemesterSize() {
        test = new CoursesLinkedList(referenceList.getCourses());
        assertEquals(8,test.getSemesterSize(1));
        assertEquals(8,test.getSemesterSize(2));
        assertEquals(6,test.getSemesterSize(3));
        assertEquals(6,test.getSemesterSize(4));
    }
}