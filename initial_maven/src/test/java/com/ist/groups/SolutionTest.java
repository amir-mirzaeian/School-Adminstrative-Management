package com.ist.groups;

import com.ist.groups.jsondata.PopulateSchool;
import com.ist.groups.model.School;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

public class SolutionTest {

    // TODO: Add tests
    PopulateSchool populateSchool = new PopulateSchool();
    School school = null;


    public SolutionTest() throws IOException {
        school = populateSchool.startPopulation();
        Solution.populateTheGroup(school);


    }

    @Test
    public void checkTheNumberOfAddedStudent() {
        Assert.assertEquals(school.getAllStudentsInSchool().stream().count(), populateSchool.returnStudentNo());
    }

    @Test
    public void checkAllClassesAdded() {
        Assert.assertEquals(school.getAllClassesInSchool().stream().count(), populateSchool.returnClassesNo());
    }

    @Test
    public void checkAllTheGradesAdded(){
        Assert.assertEquals(school.getAllGradesInSchool().stream().count(),populateSchool.returnGradeNo());
    }


}