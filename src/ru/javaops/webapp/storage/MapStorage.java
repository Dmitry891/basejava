package ru.javaops.webapp.storage;

import ru.javaops.webapp.model.Resume;

import java.util.Map;
import java.util.TreeMap;

public class MapStorage extends AbstractStorage {
    private Map<String, Resume> map = new TreeMap<>();

    @Override
    protected boolean isExist(Object key) {
        return map.containsKey(key);
    }

    @Override
    protected Object getKey(String currentUuid) {
        return currentUuid;
    }

    @Override
    protected void doSave(Resume resume, Object uuid) {
        map.put((String) uuid, resume);
    }

    @Override
    protected void doDelete(Object uuid) {
        map.remove((String) uuid);
    }

    @Override
    protected void doUpdate(Resume resume, Object uuid) {
        map.put((String) uuid, resume);
    }

    @Override
    protected Resume doGet(Object uuid) {
        return map.get((String) uuid);
    }

    @Override
    public int size() {
        return map.size();
    }

    @Override
    public Resume[] getAll() {
        return map.values().toArray((new Resume[map.size()]));
    }

    @Override
    public void clear() {
        map.clear();
    }
}
