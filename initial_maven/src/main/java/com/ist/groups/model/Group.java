package com.ist.groups.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
public class Group {

    private String id;

    /**
     * Represents a subject or subjects in a group
     */
    private List<Subject> subjects;
    /**
     * Represents a list of students in a group
     */
    private List<Student> students;
    /**
     * Represents maximum capacity of a group
     */
    private int maxCapacity;
    /**
     * Represents the minimum capacity of a group
     */
    private int minCapacity;

    /**
     * if the number of students is less than min capacity
     * the group is marked invalid
     */
    private boolean isValid;

    /**
     * Constructor for the group class
     *
     * @param subjects
     * @param students
     * @param maxCapacity
     * @param minCapacity
     * @param isValid
     */

    public Group(List<Subject> subjects, List<Student> students, int maxCapacity, int minCapacity, boolean isValid) {
        this.id = UUID.randomUUID().toString();
        this.subjects = subjects;
        this.students = students;
        this.maxCapacity = maxCapacity;
        this.minCapacity = minCapacity;
        this.isValid = false;
    }

    /**
     * Constructor for the group
     *
     * @param subjects
     * @param maxCapacity
     * @param minCapacity
     */
    public Group(List<Subject> subjects, int maxCapacity, int minCapacity) {
        this.id = UUID.randomUUID().toString();
        this.subjects = subjects;
        this.maxCapacity = maxCapacity;
        this.minCapacity = minCapacity;
        this.students = new ArrayList<>();
        this.isValid = false;
    }

    /**
     * Add the student to the group
     *
     * @param student
     * @return the method retun true if the student can be added and false otherwise
     */
    public boolean addStudentToGroup(Student student) {
        if (student.isUnAttended() && students.size() <= maxCapacity) {
            students.add(student);
            maxCapacity--;
            checkValidity(students.size());
            return true;

        } else return false;
    }

    /**
     * checks if the group is applicable. If the number of students is less or equal
     * to the minimum of the capacity of the class, the class is not a valid class
     *
     * @param size
     */
    private void checkValidity(int size) {
        if (size >= minCapacity) isValid = true;
        else isValid = false;
    }

    /**
     * Remove the student from the group.
     * @param student
     */
    public void removeStudentFromGroup(Student student){
        students.remove(student);
        maxCapacity ++;
        checkValidity(students.size());
    }

}
