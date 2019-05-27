package com.ist.groups.jsondata;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParser;
import com.ist.groups.model.*;
import com.ist.groups.model.Class;
import lombok.Data;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class PopulateSchool {

    School school = new School("Sample School");
    StudentListSample studentListSample;
    GradeListSample gradeListSample;
    SubjectListSample subjectListSample;
    ClassListSample classListSample;
    GroupListSample groupListSample;


    public int returnStudentNo(){
        return this.studentListSample.studentNo();
    }
    public int returnClassesNo(){
        return this.classListSample.classesNo();
    }
    public int returnGradeNo(){
        return this.gradeListSample.gradeNo();
    }
    //JSON parser object to parse read file
    public void jsonToObject() throws IOException {
        JsonParser jsonParser = new JsonParser();
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String path = new File("").getAbsolutePath() + "/src/main/java/com/ist/groups/jsondata";
        String studentJson = readFile(path + "/student.json", Charset.defaultCharset());
        String gradeJson = readFile(path + "/grade.json", Charset.defaultCharset());
        String subjectJson = readFile(path + "/subject.json", Charset.defaultCharset());
        String classJson = readFile(path + "/class.json", Charset.defaultCharset());
        String groupJson = readFile(path + "/group.json", Charset.defaultCharset());
        studentListSample = gson.fromJson(jsonParser.parse(studentJson), StudentListSample.class);
        gradeListSample = gson.fromJson(jsonParser.parse(gradeJson), GradeListSample.class);
        subjectListSample = gson.fromJson(jsonParser.parse(subjectJson), SubjectListSample.class);
        classListSample = gson.fromJson(jsonParser.parse(classJson), ClassListSample.class);
        groupListSample = gson.fromJson(jsonParser.parse(groupJson), GroupListSample.class);

    }

    public School startPopulation() throws IOException {
        jsonToObject();
        gradeListSample.gradeSampleArrayList.forEach(grade -> {
            Grade g = new Grade(grade.getLevel(), grade.getSubjects());
            school.addGradeToSchool(g);
        });
        studentListSample.studentSampleArrayList.forEach(student -> {
            int level = student.getGrade();
            Grade g = new Grade(student.getGrade());
            List<Subject> commonSubjects = findCommonSubjectForLevel(level);

            List<Subject> subjects = student.getSubjects();
            if (subjects == null) subjects = commonSubjects;
            else subjects.addAll(commonSubjects);

            Student s = new Student(student.getName(), student.getLastName(), subjects, g);
            s.getGrade().setSubjects(commonSubjects);
            school.addStudentToSchool(s);
        });
        subjectListSample.subjectSampleArrayList.forEach(subject -> {
            Subject s = new Subject(subject.getName(), subject.isCommon);
            school.addSubjectToSchool(s);
        });
        classListSample.classSampleArrayList.forEach(theClass -> {
            List<Integer> grades = theClass.getGrades();
            Class c = new Class(theClass.getName(), grades, school.getAllStudentsInSchool());
            school.addClassToSchool(c);

        });
        groupListSample.groupSampleArrayList.forEach(group -> {
            Group g = new Group(group.getSubjects(), group.getMax(), group.getMin());
            school.addGroupToSchool(g);
        });
        return school;
    }

    public List<Subject> findCommonSubjectForLevel(int level) {
        List<Grade> grades = school.getAllGradesInSchool();
        List<Subject> commonSubjects = new ArrayList<>();
        for (Grade g : grades) {
            if (g.getLevel() == level) commonSubjects = g.getSubjects();
        }
        return commonSubjects;
    }

    private static String readFile(String path, Charset encoding) throws IOException {
        byte[] encoded = Files.readAllBytes(Paths.get(path));
        return new String(encoded, encoding);
    }
}

@Data
class StudentSample {
    String name;
    String lastName;
    int grade;
    List<Subject> subjects;
}

@Data
class StudentListSample {
    ArrayList<StudentSample> studentSampleArrayList;
    int studentNo (){
        return studentSampleArrayList.size();
    }
}

@Data
class GradeSample {
    int level;
    List<Subject> subjects;
}

@Data
class GradeListSample {
    ArrayList<GradeSample> gradeSampleArrayList;
    int gradeNo(){
        return gradeSampleArrayList.size();
    }
}

@Data
class SubjectSample {
    String name;
    boolean isCommon;
}

@Data
class SubjectListSample {
    ArrayList<SubjectSample> subjectSampleArrayList;
}

@Data
class ClassSample {
    String name;
    List<Integer> grades;
}

@Data
class ClassListSample {
    ArrayList<ClassSample> classSampleArrayList;
    int classesNo(){
        return classSampleArrayList.size();
    }
}

@Data
class GroupSample {
    List<Subject> subjects;
    int min;
    int max;
}

@Data
class GroupListSample {
    ArrayList<GroupSample> groupSampleArrayList;
}



