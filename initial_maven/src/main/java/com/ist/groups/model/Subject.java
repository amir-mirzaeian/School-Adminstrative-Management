package com.ist.groups.model;

import lombok.Data;

@Data
public class Subject {
    /**
     * represents the name of the subject ex:Math,Chemistry
     */
    private String name;
    /**
     * represents if the the subject is common or individual
     * true if the subject is common and false otherwise
     */
    private Boolean isCommon;

    /**
     * constructor for Subject class
     * @param name Subject's name
     * @param ifCommon if subject is common
     */
    public Subject(String name, Boolean ifCommon) {

        this.name = name;
        this.isCommon = ifCommon;
    }

    /**
     *
     * @param name Subject's name
     */
    public Subject(String name) {
        this.name = name;
    }
    /**
     *
     */
    public Subject() {
    }
}
