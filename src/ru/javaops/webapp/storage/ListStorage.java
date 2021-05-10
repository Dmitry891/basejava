package ru.javaops.webapp.storage;

import ru.javaops.webapp.model.Resume;

import java.util.ArrayList;
import java.util.List;

public class ListStorage extends AbstractStorage<Integer> {
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
    protected void doSave(Resume resume, Integer key) {
        list.add(resume);
    }

    @Override
    protected void doDelete(Integer key) {
        list.remove(key.intValue());
    }

    @Override
    protected void doUpdate(Resume resume, Integer key) {
        list.set(key, resume);
    }

    @Override
    protected Resume doGet(Integer key) {
        return list.get(key);
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
    protected boolean isExist(Integer key) {
        return key != null;
    }
}
