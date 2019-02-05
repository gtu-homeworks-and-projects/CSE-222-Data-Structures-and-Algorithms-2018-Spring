import gtu.*;

public class Main {
    public static void main(String[] args) {

        /* Part 1 Main Test Starts */
        {
            System.out.println("Part 1 Tests:");
            GtuCseCourses testList = new GtuCseCourses();
            try {
                GtuCseCourses[] test = new GtuCseCourses[3];
                test[0] = testList.getByCode("XXX XXX");
                test[1] = testList.listSemesterCourses(2);
                test[2] = testList.getByRange(12, 16);
                for (int i = 0; i < 3; i++) {
                    for (GtuCseCourses.GTUCourse tmp :
                            test[i].getCourses()) {
                        System.out.println("    " + tmp);
                    }
                    System.out.println("");
                }
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }
        /* Part 1 Main Tests End */

        /* Part 2 Main Test Starts */
        {
            System.out.println("Part 2 Tests:");
            GtuLinkedList<GtuCseCourses.GTUCourse> testList = new GtuLinkedList<>(new GtuCseCourses().getCourses()); // copies course linked list
            try {
                System.out.println("List size before disabling = " + testList.size());
                for (int i = 0; i < 10; i++)
                    testList.disable(i);
                for (int i = 3; i < 7; i++)
                    testList.enable(i);
                System.out.println("List size after disabling = " + testList.size());
                System.out.println("List after disable/enable:");
                for (int i = 0; i < 20; i++) {
                    try{
                        System.out.println("    " + testList.get(i));
                    } catch (Exception e) {
                        System.out.println(e.getMessage() + "- Index at " + i);
                    }
                }
                System.out.println("Disabled items: ");
                GtuLinkedList disabledTest = testList.showDisabled();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            System.out.println();
        }
        /* Part 2 Main Test Ends */

        /* Part 3 Main Test Starts */
        {
            System.out.println("Part 3 Tests:");
            GtuCseCourses courseList = new GtuCseCourses();
            CoursesLinkedList testList = new CoursesLinkedList(courseList.getCourses());
            System.out.println("List size before removal = " + testList.size());
            testList.remove(3);
            System.out.println("List size after removal = " + testList.size());
            int i = 0;
            System.out.println("Printing list using next:");
            while (testList.hasNext() && i < 20){
                System.out.println("    " + testList.next());
                i++;
            }
            System.out.println("Printing list using nextInSemester:");
            for (int j = 0; j < testList.getSemesterCount(); j++) {
                testList.setCurrentSemester(j + 1);
                System.out.println("    Printing semester " + (j+1) + ":");
                for (int k = 0; k < testList.getSemesterSize(j + 1); k++) {
                    System.out.println("        " + testList.nextInSemester());
                }
            }
        }
    }
}
