package com.larandvik.webapp.model;

import java.time.LocalDate;
import java.util.Objects;

public class Organization {

    private final Link homePage;
    private final LocalDate startDate;
    private final LocalDate endDate;
    private final String title;
    private final String description;

    public Organization(String name, String url, LocalDate startDate, LocalDate endDate, String title, String description) {
        this.homePage = new Link(name, url);
        Objects.requireNonNull(startDate, "startDate must not be null");
        Objects.requireNonNull(endDate, "endDate must not be null");
        Objects.requireNonNull(title, "title must not be null");
        this.startDate = startDate;
        this.endDate = endDate;
        this.title = title;
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;

        Organization that = (Organization) o;
        return homePage.equals(that.homePage) && startDate.equals(that.startDate) && endDate.equals(that.endDate) && title.equals(that.title) && Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        int result = homePage.hashCode();
        result = 31 * result + startDate.hashCode();
        result = 31 * result + endDate.hashCode();
        result = 31 * result + title.hashCode();
        result = 31 * result + Objects.hashCode(description);
        return result;
    }

    @Override
    public String toString() {
        return "Organization{" +
               "homePage=" + homePage +
               ", startDate=" + startDate +
               ", endDate=" + endDate +
               ", title='" + title + '\'' +
               ", description='" + description + '\'' +
               '}';
    }
}
