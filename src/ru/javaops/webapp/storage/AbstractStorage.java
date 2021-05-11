package ru.javaops.webapp.storage;

import ru.javaops.webapp.exception.ExistStorageException;
import ru.javaops.webapp.exception.NotExistStorageException;
import ru.javaops.webapp.model.Resume;

import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;

public abstract class AbstractStorage<Key> implements Storage {

    private static Logger LOG = Logger.getLogger(AbstractStorage.class.getName());

    public void save(Resume resume) {
        LOG.info("save " + resume);
        Key key = getNotExistedKey(resume.getUuid());
        doSave(resume, key);
    }

    public void delete(String uuid) {
        LOG.info("delete " + uuid);
        Key key = getExistedKey(uuid);
        doDelete(key);
    }

    public Resume get(String uuid) {
        LOG.info("get " + uuid);
        Key key = getExistedKey(uuid);
        return doGet(key);
    }

    public void update(Resume resume) {
        LOG.info("update " + resume);
        Key key = getExistedKey(resume.getUuid());
        doUpdate(resume, key);
        System.out.println("Резюме " + resume.getUuid() + " перезаписано!");
    }

    private Key getExistedKey(String uuid){
        Key key = getKey(uuid);
        if (!isExist(key)) {
            LOG.warning("Resume " + uuid + " not exist");
            throw new NotExistStorageException(uuid);
        }
        return key;
    }

    private Key getNotExistedKey(String uuid){
        Key key = getKey(uuid);
        if (isExist(key)) {
            LOG.warning("Resume " + uuid + " already exist");
            throw new ExistStorageException(uuid);
        }
        return key;
    }

    protected abstract List<Resume> doCopyAll();

    protected abstract boolean isExist(Key key);

    protected abstract Key getKey(String currentUuid);

    protected abstract void doSave(Resume resume, Key key);

    protected abstract void doDelete(Key key);

    protected abstract void doUpdate(Resume resume, Key key);

    protected abstract Resume doGet(Key key);

    @Override
    public List<Resume> getAllSorted() {
        LOG.info("getAllSorted");
        List<Resume> list = doCopyAll();
        Collections.sort(list);
        return list;
    }
}
