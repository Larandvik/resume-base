package com.larandvik.webapp.model;

public enum ContactType {

    PHONE("Phone"),
    MOBILE("Mobile"),
    HOME_PHONE("home phone"),
    SKYPE("Skype"),
    MAIL("Mail"),
    LINKEDIN("Linkedin"),
    GITHUB("Github"),
    STACKOVERFLOW("Stackoverflow"),
    HOME_PAGE("home page");

    private final String title;

    ContactType(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
