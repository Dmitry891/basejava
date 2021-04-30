package ru.javaops.webapp.storage;

import ru.javaops.webapp.exception.ExistStorageException;
import ru.javaops.webapp.exception.NotExistStorageException;
import ru.javaops.webapp.model.Resume;

public abstract class AbstractStorage implements Storage {

    public void save(Resume resume) {
        String currentUuid = resume.getUuid();
        Object key = getKey(currentUuid);
        if (isExist(key)) {
            throw new ExistStorageException(currentUuid);
        }
        doSave(resume, key);
    }

    public void delete(String uuid) {
        Object key = getKey(uuid);
        if (isNotExist(key)) {
            throw new NotExistStorageException(uuid);
        }
        doDelete(key);
    }

    public Resume get(String uuid) {
        Object key = getKey(uuid);
        if (isNotExist(key)) {
            throw new NotExistStorageException(uuid);
        }
        return doGet(key);
    }

    public void update(Resume resume) {
        String currentUuid = resume.getUuid();
        Object key = getKey(currentUuid);
        if (isNotExist(key)) {
            throw new NotExistStorageException(currentUuid);
        }
        doUpdate(resume, key);
        System.out.println("Резюме " + currentUuid + " перезаписано!");
    }

    protected abstract boolean isExist(Object key);

    protected abstract boolean isNotExist(Object key);

    protected abstract Object getKey(String currentUuid);

    protected abstract void doSave(Resume resume, Object key);

    protected abstract void doDelete(Object key);

    protected abstract void doUpdate(Resume resume, Object key);

    protected abstract Resume doGet(Object key);

}
