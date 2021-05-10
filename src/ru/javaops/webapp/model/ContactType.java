package ru.javaops.webapp.model;

public enum ContactType {
    MOBILE_PHONE("Мобильный тел.: "),
    SKYPE("Skype: "),
    E_MAIL("Эл. почта: "),
    LINKEDIN("Профиль LinkedIn: "),
    GITHUB("Профиль GitHub: "),
    STACKOVERFLOW("Профиль StackOverflow: "),
    HOMEPAGE("Домашняя страница: ");

    private String title;

    ContactType(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
