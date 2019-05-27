package com.ist.groups.model;

import lombok.Data;

import java.util.List;

@Data
public class Grade {

    /**
     * Represents the Grade level
     */
    private int level;

    /**
     * Represents the subjects of a grade. Each grade has some
     * mandatory(common) subjects that all the student should take.
     */
    private List<Subject> subjects;

    /**
     * Constructor for Grade
     */
    public Grade() {

    }

    /**
     * Constructor for Grade
     *
     * @param level represents Grade Level
     */
    public Grade(int level) {
        this.level = level;
    }

    /**
     * Constructor for Grade
     *
     * @param level
     * @param subjects
     */
    public Grade(int level, List<Subject> subjects) {
        this.level = level;
        this.subjects = subjects;
    }
}
