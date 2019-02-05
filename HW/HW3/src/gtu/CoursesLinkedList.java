package gtu;

import java.security.InvalidParameterException;
import java.util.Collection;
import java.util.NoSuchElementException;

/**
 * Part 3 class.
 * This class is also a Linked List implementation. Uses double link. One link holds whole list. And the other one links semesters together.
 */
public class CoursesLinkedList {
    private Node listHead = null;
    private Node listTail = null;
    private Node[] semesterTails;
    private Node current = null;
    private Node currentPointer = null;
    private int size = 0;
    private int[] coursesInSemester;
    private int semesterCount;

    /**
     * Default constructor constructs a course list with 8 semesters
     */
    public CoursesLinkedList(){
        this(8);
    }

    /**
     * Constructs a course list with given semester count
     * @param semesterCount total semester count.
     */
    public CoursesLinkedList(int semesterCount){
        setSemesterCount(semesterCount);
        semesterTails = new Node[this.semesterCount];
        coursesInSemester = new int[this.semesterCount];
        for (int i = 0; i < this.semesterCount; i++) {
            semesterTails[i] = new Node();
            coursesInSemester[i] = 0;
        }
    }

    /**
     * Constructs a course list with given current course list
     * @param collection course list
     */
    public CoursesLinkedList(Collection<GtuCseCourses.GTUCourse> collection){
        this();
        for (GtuCseCourses.GTUCourse course:
             collection) {
            add(course);
        }
    }

    /**
     * Adds a new course to the list
     * @param course a new course
     */
    public void add(GtuCseCourses.GTUCourse course){
        Node tempNode = new Node(course);
        int semesterIndex = course.semester - 1;
        // SemesterTail set
        if(coursesInSemester[semesterIndex] == 0){
            tempNode.nextInSemester = tempNode;
        }
        else {
            tempNode.nextInSemester = semesterTails[semesterIndex].nextInSemester;
            semesterTails[semesterIndex].nextInSemester = tempNode;
        }
        semesterTails[semesterIndex] = tempNode;
        coursesInSemester[semesterIndex]++;

        // List link handling
        if(listHead == null){
            listHead = tempNode;
            listTail = listHead;
            rewindList();
        }
        else {
            listTail.next = tempNode;
            listTail = listTail.next;
        }
        size++;
    }

    /**
     * Removes specified courses
     * @param index index of course
     */
    public void remove(int index){
        if(size() == 0) throw new NoSuchElementException("List is empty");
        if(index >= size()) throw new IndexOutOfBoundsException("List is much smaller");

        advanceByIndex(index,false);
        int semester = current.course.semester;
        boolean isSemesterTail = current == semesterTails[semester - 1];

        // Semester tail remove and semester links
        if(semesterTails[semester - 1].nextInSemester == current){
            semesterTails[semester - 1].nextInSemester = current.nextInSemester;
        }
        else {
            advanceByIndex(coursesInSemester[semester - 1], true);
            current.nextInSemester = current.nextInSemester.nextInSemester;
            if (isSemesterTail) semesterTails[semester - 1] = current;
        }
        if(coursesInSemester[semester - 1] == 1) semesterTails[semester - 1] = null;
        coursesInSemester[semester - 1]--;

        // List link remove
        if (index == 0) {
            if(currentPointer == listHead)
                currentPointer = currentPointer.next;
            listHead = listHead.next;
        }
        else if (index == size - 1){
            advanceByIndex(index - 1,false);
            listTail = current;
            current.next = null;
        }
        else {
            advanceByIndex(index - 1, false);
            current.next = current.next.next;
        }
        if(size() == 1) resetList();

        size--;
    }

    /**
     * moves current pointer to the next course
     * @return returns old course
     */
    public GtuCseCourses.GTUCourse next(){
        GtuCseCourses.GTUCourse tempCourse;
        if(size() == 0) throw new NoSuchElementException("List is empty");
        else if(currentPointer == null) throw new NoSuchElementException("Already reached the end");
        else {
            tempCourse = currentPointer.course;
            currentPointer = currentPointer.next;
        }
        return tempCourse;
    }

    /**
     * Moves current pointer to the next course in the same semester
     * @return returns old course
     */
    public GtuCseCourses.GTUCourse nextInSemester(){
        GtuCseCourses.GTUCourse tempCourse;
        if(size() == 0) throw new NoSuchElementException("List is empty");
        else {
            tempCourse = currentPointer.course;
            currentPointer = currentPointer.nextInSemester;
        }
        return tempCourse;
    }

    /**
     * Size of the list
     * @return Course count
     */
    public int size(){
        return size;
    }

    /**
     * A helper for next() method. Checks if there are more courses.
     * @return has next or not
     */
    public boolean hasNext(){
        return currentPointer != null && size() != 0;
    }

    /**
     * Course count of given semester
     * @param semester semester
     * @return Course count of a semester
     */
    public int getSemesterSize(int semester){
        return coursesInSemester[semester - 1];
    }

    /**
     * Moves current Node to the index
     * @param index index
     * @param bySemester Move only in semester or not
     */
    private void advanceByIndex(int index, boolean bySemester){
        current = listHead;
        if(bySemester)
            for (int i = 0; i < index; i++)
                current = current.nextInSemester;
        else
            for (int i = 0; i < index; i++)
                current = current.next;
    }

    /**
     * Getter for semester count
     * @return semester count
     */
    public int getSemesterCount() {
        return semesterCount;
    }

    /**
     * A setter for semester count.
     * @param semesterCount semester count
     */
    private void setSemesterCount(int semesterCount) {
        this.semesterCount = semesterCount;
    }

    /**
     * Resets list to the empty state.
     */
    private void resetList(){
        listTail = null;
        listHead = null;
        currentPointer = null;
        current = null;
    }

    /**
     * Points current pointer to the list head.
     */
    public void rewindList(){
        currentPointer = listHead;
    }

    /**

     * Sets current pointer to given semester to continue from there.
     * @param semester given semester
     * @throws InvalidParameterException semester is invalid.
     */
    public void setCurrentSemester(int semester) throws InvalidParameterException {
        if (semester < 1 || semester > semesterCount) throw new InvalidParameterException("Invalid semester.");
        rewindList();
        while (currentPointer.course.semester != semester) currentPointer = currentPointer.next;
    }

    /**
     * A node class for keeping lists data and links.
     */
    private class Node {
        Node next = null;
        Node nextInSemester = null;
        GtuCseCourses.GTUCourse course;

        Node(){
            course = null;
        }

        Node(GtuCseCourses.GTUCourse course){
            this.course = course;
        }
    }
}
