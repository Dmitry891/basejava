package ru.javaops.webapp.storage;

import ru.javaops.webapp.model.Resume;

import java.util.List;

public interface Storage {

    void save(Resume resume);

    void update(Resume resume);

    Resume get(String uuid);

    int size();

    List<Resume> getAllSorted();

    void delete(String uuid);

    void clear();
}
