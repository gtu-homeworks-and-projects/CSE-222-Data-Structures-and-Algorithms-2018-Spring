package gtu;

import java.io.File;
import java.io.FileNotFoundException;
import java.security.InvalidParameterException;
import java.util.*;

/**
 * A class for keeping course database using linked list
 */
public class GtuCseCourses {
    private LinkedList<GTUCourse> courses;

    /**
     * Constructs courses from file.
     */
    public GtuCseCourses(){
        courses = new LinkedList<>();
        try{
            readDatabase();
        } catch (FileNotFoundException e){
            e.printStackTrace();
        }
    }

    /**
     * Constructs courses from given list
     * @param givenCourseList Course list
     */
    private GtuCseCourses(LinkedList<GTUCourse> givenCourseList){
        courses = givenCourseList;
    }

    /**
     * reads list from "courses.csv" file and adds to the list.
     * @throws FileNotFoundException If file not found
     */
    private void readDatabase() throws FileNotFoundException{
        Scanner csvReader = new Scanner(new File("courses.csv"),"ISO-8859-9");
        csvReader.nextLine();
        while (csvReader.hasNext()) {
            String[] line = csvReader.nextLine().split(";");
            String[] courseCode = line[1].split(" ");
            int[][] credits = new int[3][3];
            for (int i = 0; i < 3; i++) for (int j = 0; j < 3; j++) credits[i][j] = 0;
            for (int i = 0; i < 2; i++) credits[i][0] = Integer.parseInt(line[i+3]);
            String[] HTLCredits = line[5].replaceAll(" ","").split("\\+");
            for (int i = 0; i < 3; i++) credits[2][i] = Integer.parseInt(HTLCredits[i]);
            GTUCourse tempCourse = new GTUCourse(Integer.parseInt(line[0]), courseCode, line[2], credits);
            courses.add(tempCourse);
        }
    }

    /**
     * Gets a new courses object from same course code
     * @param code Course code
     * @return A new courses object which only includes given course codes
     * @throws NoSuchElementException No course in that course code
     */
    public GtuCseCourses getByCode(String code) throws NoSuchElementException{
        String[] courseCode = code.split(" ");
        LinkedList<GTUCourse> queryCourses = new LinkedList<>();
        for (GTUCourse temp: courses) if(Arrays.equals(temp.courseCode, courseCode)) queryCourses.add(temp);
        if(queryCourses.size() == 0) throw new NoSuchElementException("There are no matched course");
        return new GtuCseCourses(queryCourses);
    }

    /**
     * Gets a new courses object from same semester
     * @param semester semester number
     * @return A new courses object which only includes given semester
     * @throws NoSuchElementException No course in that semester
     */
    public GtuCseCourses listSemesterCourses(int semester) throws NoSuchElementException{
        LinkedList<GTUCourse> queryCourses = new LinkedList<>();
        for (GTUCourse temp: courses) if(temp.semester == semester) queryCourses.add(temp);
        if(queryCourses.size() == 0) throw new NoSuchElementException("There are no matched course");
        return new GtuCseCourses(queryCourses);
    }

    /**
     * Gets a new courses object between the range
     * @param startIndex start index
     * @param lastIndex end index
     * @return New GtuCseCourses object
     * @throws InvalidParameterException invalid range parameters
     */
    public GtuCseCourses getByRange(int startIndex, int lastIndex) throws InvalidParameterException{
        if(startIndex < 0 || lastIndex > courses.size() || startIndex > lastIndex) throw new InvalidParameterException("Invalid Index Parameters");
        LinkedList<GTUCourse> queryCourses = new LinkedList<>();
        ListIterator<GTUCourse> courseListIterator = courses.listIterator(startIndex);
        for (int i = 0; i <= lastIndex - startIndex; i++) queryCourses.add(courseListIterator.next());
        return new GtuCseCourses(queryCourses);
    }

    /**
     * Getter for courses list
     * @return Courses list
     */
    public LinkedList<GTUCourse> getCourses(){
        return courses;
    }

    /**
     * Course class which includes all necessary information of a course
     */
    public class GTUCourse {
        int semester;
        String[] courseCode;
        String courseTitle;
        int[][] credits;

        GTUCourse(int semester, String[] courseCode, String courseTitle, int[][] credits) {
            this.semester = semester;
            this.courseCode = courseCode;
            this.courseTitle = courseTitle;
            this.credits = credits;
        }

        @Override
        public String toString() {
            return  "semester=" + semester +
                    ", courseCode=" + courseCode[0] + " " + courseCode[1] +
                    ", courseTitle='" + courseTitle + '\'' +
                    ", credits=" + credits[0][0] + ";" + credits[1][0] + ";" + credits[2][0] + "+" + credits[2][1] + "+" + credits[2][2];
        }
    }
}

