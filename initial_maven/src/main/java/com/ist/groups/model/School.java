package com.ist.groups.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class School {

    /**
     * Represents the school name
     */
    private String name;

    /**
     * Represents all the students who study in the schoool
     */

    private List<Student> allStudentsInSchool;
    /**
     * Represents all the classes in the school
     */

    private List<Class> allClassesInSchool;
    /**
     * Represents all the groups available in the school
     */

    private List<Group> allGroupsInSchool;
    /**
     * Represents all the levels in the school
     */

    private List<Grade> allGradesInSchool;
    /**
     * Represents all the subjects in the school
     */

    private List<Subject> allSubjectsInSchool;


    /**
     * Constructor for the school
     */
    public School() {
        this.name = "";
        this.allStudentsInSchool = new ArrayList<>();
        this.allClassesInSchool = new ArrayList<>();
        this.allGroupsInSchool = new ArrayList<>();
        this.allGradesInSchool = new ArrayList<>();
    }

    /**
     * Constructor for the school
     *
     * @param name represents school name
     */
    public School(String name) {
        this.name = name;
        this.allStudentsInSchool = new ArrayList<>();
        this.allClassesInSchool = new ArrayList<>();
        this.allGroupsInSchool = new ArrayList<>();
        this.allGradesInSchool = new ArrayList<>();
        this.allSubjectsInSchool = new ArrayList<>();
    }

    /**
     * Add student to the school
     *
     * @param student
     */

    public void addStudentToSchool(Student student) {
        allStudentsInSchool.add(student);
    }

    /**
     * Add class to the school
     *
     * @param theClass
     */
    public void addClassToSchool(Class theClass) {
        allClassesInSchool.add(theClass);
    }

    /**
     * Add group to the school
     *
     * @param group
     */

    public void addGroupToSchool(Group group) {
        allGroupsInSchool.add(group);
    }

    /**
     * Add grade to the school
     *
     * @param grade
     */

    public void addGradeToSchool(Grade grade) {
        allGradesInSchool.add(grade);
    }

    /**
     * Add Subject to the school
     *
     * @param subject
     */

    public void addSubjectToSchool(Subject subject) {
        allSubjectsInSchool.add(subject);
    }

    /**
     * Get all the common subjects provided in the school
     *
     * @return List of common subjects
     */
    public List<Subject> getAllCommonSubjects() {
        return allSubjectsInSchool
                .stream()
                .filter(common -> common.getIsCommon())
                .collect(Collectors.toList());
    }

    /**
     * Get all the individual Subjects provided in the school
     *
     * @return List of indivual projects
     */
    public List<Subject> getAllIndividualSubjects() {
        return allSubjectsInSchool
                .stream()
                .filter(individual -> !individual.getIsCommon())
                .collect(Collectors.toList());
    }

    /**
     * Find a students by his/her name
     * @param name Student's name
     * @return List of students by their name
     */
    public List<Student> searchAllStudentsByName(String name) {
        return allStudentsInSchool
                .stream()
                .filter(student -> student.getName().toUpperCase().equals(name.toUpperCase()))
                .collect(Collectors.toList());
    }

    /**
     * Find a subject
     * @param name Subject name
     * @return The subject profile
     */
    public Subject getSubjectByName(String name) {
        return allSubjectsInSchool
                .stream()
                .filter(subject -> subject.getName().toUpperCase().equals(name.toUpperCase()))
                .findAny().get();
    }

    /**
     * Find the students in the same level
     * @param level Level of a grade
     * @return List of the students in the same level
     */
    public List<Student> getStudentsInGrade(int level) {
        return allStudentsInSchool
                .stream()
                .filter(student -> student.getGrade().getLevel() == level)
                .collect(Collectors.toList());
    }

    /**
     * Find the students who study the same subject
     * @param subject Subject level
     * @return List of students
     */
    public List<Student> getStudentsBySubject(Subject subject) {
        return allStudentsInSchool
                .stream()
                .filter(student -> student.getSubjects().contains(subject))
                .collect(Collectors.toList());
    }

}
