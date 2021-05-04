package ru.javaops.webapp.storage;

import ru.javaops.webapp.exception.ExistStorageException;
import ru.javaops.webapp.exception.NotExistStorageException;
import ru.javaops.webapp.model.Resume;

public abstract class AbstractStorage implements Storage {

    public void save(Resume resume) {
        Object key = getNotExistedKey(resume.getUuid());
        doSave(resume, key);
    }

    public void delete(String uuid) {
        Object key = getExistedKey(uuid);
        doDelete(key);
    }

    public Resume get(String uuid) {
        Object key = getExistedKey(uuid);
        return doGet(key);
    }

    public void update(Resume resume) {
        Object key = getExistedKey(resume.getUuid());
        doUpdate(resume, key);
        System.out.println("Резюме " + resume.getUuid() + " перезаписано!");
    }

    private Object getExistedKey(String uuid){
        Object key = getKey(uuid);
        if (!isExist(key)) {
            throw new NotExistStorageException(uuid);
        }
        return key;
    }

    private Object getNotExistedKey(String uuid){
        Object key = getKey(uuid);
        if (isExist(key)) {
            throw new ExistStorageException(uuid);
        }
        return key;
    }

    protected abstract boolean isExist(Object key);

    protected abstract Object getKey(String currentUuid);

    protected abstract void doSave(Resume resume, Object key);

    protected abstract void doDelete(Object key);

    protected abstract void doUpdate(Resume resume, Object key);

    protected abstract Resume doGet(Object key);
}
