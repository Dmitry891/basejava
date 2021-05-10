package ru.javaops.webapp.storage;

import ru.javaops.webapp.model.Resume;

import java.util.*;

public class MapStorage extends AbstractStorage<String> {
    private Map<String, Resume> map = new TreeMap<>();

    @Override
    protected boolean isExist(String uuid) {
        return map.containsKey(uuid);
    }

    @Override
    protected String getKey(String currentUuid) {
        return currentUuid;
    }

    @Override
    protected void doSave(Resume resume, String uuid) {
        map.put(uuid, resume);
    }

    @Override
    protected void doDelete(String uuid) {
        map.remove(uuid);
    }

    @Override
    protected void doUpdate(Resume resume, String uuid) {
        map.put(uuid, resume);
    }

    @Override
    protected Resume doGet(String uuid) {
        return map.get(uuid);
    }

    @Override
    public int size() {
        return map.size();
    }

    @Override
    public List<Resume> doCopyAll() {
        return new ArrayList<>(map.values());
    }

    @Override
    public void clear() {
        map.clear();
    }
}
