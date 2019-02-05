package gtu;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GtuCseCoursesTest {
    GtuCseCourses test = new GtuCseCourses();

    /**
     * Tests course codes of each courses.
     */
    @Test
    void getByCode() {
        GtuCseCourses tmp = test.getByCode("XXX XXX");
        for (GtuCseCourses.GTUCourse course :
                tmp.getCourses()) {
            assertEquals("XXX", course.courseCode[0]);
            assertEquals("XXX", course.courseCode[1]);
        }
    }

    /**
     * Tests semesters of each list
     */
    @Test
    void listSemesterCourses() {
        for (int i = 1; i <= 8; i++) {
            GtuCseCourses tmp = test.listSemesterCourses(i);
            for (GtuCseCourses.GTUCourse course :
                    tmp.getCourses()) {
                assertEquals(i, course.semester);
            }
        }
    }

    /**
     * Tests size of new list
     */
    @Test
    void getByRange() {
        int start = 3, end = 15;
        GtuCseCourses tmp = test.getByRange(start,end);
        assertEquals(end - start + 1,tmp.getCourses().size());
    }
}