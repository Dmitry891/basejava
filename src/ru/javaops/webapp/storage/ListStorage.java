package ru.javaops.webapp.storage;

import ru.javaops.webapp.model.Resume;

import java.util.ArrayList;
import java.util.List;

public class ListStorage extends AbstractStorage {
    private List<Resume> list = new ArrayList<>();

    @Override
    protected Integer getKey(String currentUuid) {
        for(int i = 0; i < list.size(); i++) {
            if(list.get(i).getUuid().equals(currentUuid)) {
                return i;
            }
        }
        return null;
    }

    @Override
    protected void doSave(Resume resume, Object key) {
        list.add(resume);
    }

    @Override
    protected void doDelete(Object key) {
        list.remove(((Integer) key).intValue());
    }

    @Override
    protected void doUpdate(Resume resume, Object key) {
        list.set((Integer) key, resume);
    }

    @Override
    protected Resume doGet(Object key) {
        return list.get((Integer) key);
    }

    @Override
    public int size() {
        return list.size();
    }

    @Override
    public List<Resume> doCopyAll() {
        return new ArrayList<>(list);
    }

    @Override
    public void clear() {
        list.clear();
    }

    @Override
    protected boolean isExist(Object key) {
        return key != null;
    }
}
