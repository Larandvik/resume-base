package com.larandvik.webapp.model;

public enum SectionType {
    PERSONAL("Personal"),
    OBJECTIVE("Objective"),
    ACHIEVEMENT("Achievements"),
    QUALIFICATION("Qualification"),
    EXPERIENCE("Experience"),
    EDUCATION("Education"),;

    private final String title;

    SectionType(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
