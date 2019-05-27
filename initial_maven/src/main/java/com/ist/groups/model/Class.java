package com.ist.groups.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Class {

    /**
     * Represents Class name example: 1A
     */
    private String name;

    /**
     * Represents Class Grade for example 1
     */
    private List<Grade> grades;

    /**
     * Represents the list of the students in a class
     */
    private List<Student> students;

    /**
     * Remove Student to the class.
     * @param student
     */
    public void removeStudentFromClass(Student student){
        students.remove(student);
    }

    /**
     * Constructor for Class
     * @param name represents Class's name
     */
    public Class(String name) {
        this.name = name;
        this.students = new ArrayList<>();
        this.grades = new ArrayList<>();
    }

    /**
     * Constructor
     * @param name
     * @param grades
     */
    public Class(String name, List<Integer> grades,List<Student> students) {
        this.name = name;
        this.students = addStudentToClass(grades,students);
    }
    public List<Student> addStudentToClass(List<Integer> grade,List<Student> students){
        List<Student> st = new ArrayList<>();
        for (Integer g : grade){
            for (Student s : students){
                if (s.getGrade().getLevel() == g) st.add(s);
            }
        }
        return st;
    }
}
