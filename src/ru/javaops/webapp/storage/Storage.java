package ru.javaops.webapp.storage;

import ru.javaops.webapp.model.Resume;

import java.util.Arrays;

public interface Storage {

    void save(Resume resume);

    void update(Resume resume);

    Resume get(String uuid);

    int size();

    Resume[] getAll();

    void delete(String uuid);

    void clear();

}
