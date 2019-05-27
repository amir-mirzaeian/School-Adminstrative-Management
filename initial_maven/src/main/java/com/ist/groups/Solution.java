package com.ist.groups;

import com.ist.groups.jsondata.PopulateSchool;
import com.ist.groups.model.School;
import com.ist.groups.model.Student;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class Solution {

    // This is your starting point!
    // Good luck!
    public static void main(String[] args) throws IOException {

        PopulateSchool populateSchool = new PopulateSchool();
        School school = populateSchool.startPopulation();
        populateTheGroup(school);
//        school.getAllStudentsInSchool().forEach(System.out::println);
        System.out.println(reportUnAttendedStudents(school).stream().count());
        reportUnAttendedStudents(school).forEach(System.out::println);


    }

    /**
     * Represents all the unplaced students.
     *
     * @param school
     * @return List of unplaced students
     */

    public static List<Student> reportUnAttendedStudents(School school) {
        return school.getAllStudentsInSchool()
                .stream()
                .filter(s -> s.isUnAttended())
                .collect(Collectors.toList());
    }


    /**
     * Populates the group with students
     *
     * @param school
     */
    public static void populateTheGroup(School school) {

        school.getAllStudentsInSchool().stream().forEach(student -> school.getAllGroupsInSchool()
                .stream()
                .forEach(group -> group.getSubjects()
                        .forEach(subject -> {
                            student.getSubjects()
                                    .forEach(studentSubject -> {
                                        if (subject.equals(studentSubject)) {
                                            student.addGroupToStudent(group);
                                        }
                                    });
                        })));


    }
}
