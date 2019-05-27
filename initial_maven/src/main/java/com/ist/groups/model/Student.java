package com.ist.groups.model;


import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


public class Student {

    /**
     * Represents the Student id which is created automatically
     */

    @Getter
    private String id;

    /**
     * Represents Student's name
     */

    @Setter
    @Getter
    private String name;

    /**
     * Represents the Student's last name
     */

    @Setter
    @Getter
    private String lastName;

    /**
     * Represents the group or groups that the student is a member of
     */

    @Setter
    @Getter
    private List<Group> groups;

    /**
     * Represents the list of the subjects that a student has applied for
     */

    @Setter
    @Getter
    private List<Subject> subjects;

    /**
     * Represents the Grade in which the student is studying in
     */
    @Setter
    @Getter
    private Grade grade;

    /**
     * Represents if the student is a member of a group
     * True of the student is not yet attended in a group
     */

    @Setter
    @Getter
    private boolean isUnAttended;

    /**
     * Represents if the student belongs to more than a group
     */
    private boolean flag;

    /**
     * Constructor for the Student Class
     *
     * @param name
     * @param lastName
     * @param subjects
     * @param groups
     * @param grade
     * @param isUnAttended
     */
    public Student(String name, String lastName, List<Subject> subjects, List<Group> groups, Grade grade, boolean isUnAttended) {
        setId();
        this.name = name;
        this.lastName = lastName;
        this.subjects = subjects;
        this.groups = groups;
        this.grade = grade;
        this.isUnAttended = isUnAttended;
        this.flag = false;
    }

    /**
     * Constructor
     *
     * @param name
     * @param lastName
     * @param subjects
     * @param grade
     */
    public Student(String name, String lastName, List<Subject> subjects, Grade grade) {
        setId();
        this.name = name;
        this.lastName = lastName;
        this.subjects = subjects;
        this.groups = new ArrayList<>();
        this.grade = grade;
        this.isUnAttended = true;
        this.flag = false;
    }

    /**
     * Create a unique id for a student
     */
    public void setId() {
        this.id = UUID.randomUUID().toString();
    }

    /**
     * Constructor for the Student Class
     *
     * @param name
     * @param lastName
     */
    public Student(String name, String lastName) {
        setId();
        this.name = name;
        this.lastName = lastName;
        this.subjects = new ArrayList<>();
        this.groups = new ArrayList<>();
        this.grade = new Grade();
        this.isUnAttended = true;
        this.flag = false;
    }


    @Override
    public String toString() {
        return "Student{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                ", subjects=" + subjects +
                ", grade=" + grade +
                ", group=" + groups +
                ", isUnAttended=" + isUnAttended +
                '}';
    }

    /**
     * Add the Individual subject to the student's profile
     *
     * @param subject
     */
    public void addSubjectToStudent(Subject subject) {
        subjects.add(subject);
    }

    /**
     * Add the specific grade to the student's profile
     *
     * @param grade
     */
    public void addGradeToStudent(Grade grade) {
        this.grade = grade;
    }

    /**
     * Add a group to the student's profile.
     * if a student has a more than one group, he/she will be
     * marked as an unattended student.
     *
     * @param group
     */
    public void addGroupToStudent(Group group) {
        for (Group gr : groups) {
            if (gr.getId().equals(group.getId()))
                return;
        }
        if (!flag) checkRecord(group);
        this.isUnAttended = flag;
        groups.add(group);
    }

    /**
     * Check if the student has already applied and placed in a group
     *
     * @param group
     */
    public void checkRecord(Group group) {
        for (Group gr : groups) {
            for (Subject s : gr.getSubjects()) {
                for (Subject subject : group.getSubjects()) {
                    if (s.equals(subject)) {
                        flag = true;
                        return;

                    }
                }
            }
        }
        this.isUnAttended = false;

    }

    /**
     * Constructor for Student class
     *
     * @param name
     * @param lastName
     * @param grade
     */
    public Student(String name, String lastName, Grade grade) {
        this.name = name;
        this.lastName = lastName;
        this.grade = grade;
        setId();
        this.groups = new ArrayList<>();
        this.subjects = new ArrayList<>();
        this.isUnAttended = true;
        this.flag = false;
    }
}
