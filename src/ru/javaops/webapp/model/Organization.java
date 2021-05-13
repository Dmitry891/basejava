package ru.javaops.webapp.model;

import ru.javaops.webapp.util.DateUtil;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static ru.javaops.webapp.util.DateUtil.NOW;

public class Organization implements Serializable {
    private static final long serialVersionUID = 1L;
    private final Link homePage;
    private List<Position> position;

    public Organization(String name, String url, Position... position) {
        this(new Link(name, url), Arrays.asList(position));
    }

    public Organization(Link homePage, List<Position> position) {
        this.homePage = homePage;
        this.position = position;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Organization that = (Organization) o;
        return Objects.equals(homePage, that.homePage) && Objects.equals(position, that.position);
    }

    @Override
    public int hashCode() {
        return Objects.hash(homePage, position);
    }

    @Override
    public String toString() {
        return "Organization{" + "homePage=" + homePage + "position" + position + '}';
    }

    public static class Position implements Serializable {
        private final LocalDate dateOfStart;
        private final LocalDate dateOfFinish;
        private final String title;
        private final String description;

        public Position(int startYear, Month startMonth, String title, String description) {
            this(DateUtil.of(startYear, startMonth), NOW, title, description);
        }

        public Position(int startYear, Month startMonth, int endYear, Month endMonth, String title, String description) {
            this(DateUtil.of(startYear, startMonth), NOW, title, description);
        }

        public Position(LocalDate dateOfStart, LocalDate dateOfFinish, String title, String description) {
            Objects.requireNonNull(dateOfStart, "dateOfStart must not be null");
            Objects.requireNonNull(dateOfFinish, "dateOfFinish must not be null");
            Objects.requireNonNull(title, "title must not be null");
            this.dateOfStart = dateOfStart;
            this.dateOfFinish = dateOfFinish;
            this.title = title;
            this.description = description;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Position position = (Position) o;
            return Objects.equals(dateOfStart, position.dateOfStart) && Objects.equals(dateOfFinish, position.dateOfFinish) && Objects.equals(title, position.title) && Objects.equals(description, position.description);
        }

        @Override
        public int hashCode() {
            return Objects.hash(dateOfStart, dateOfFinish, title, description);
        }

        @Override
        public String toString() {
            return "Position{" +
                    "dateOfStart=" + dateOfStart +
                    ", dateOfFinish=" + dateOfFinish +
                    ", title='" + title + '\'' +
                    ", description='" + description + '\'' +
                    '}';
        }
    }
}
