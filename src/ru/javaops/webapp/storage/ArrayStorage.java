package ru.javaops.webapp.storage;

import ru.javaops.webapp.model.Resume;
import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10000];
    private int size = 0;

    public void save(Resume r) {
        if(getIndex(r.getUuid()) != -1) {
            System.out.println("Резюме " + r.getUuid() + " уже есть в базе!");
        } else if(size == storage.length) {
            System.out.println("База резюме переполнена!");
        } else {
            storage[size] = r;
            size++;
        }
    }

    public void update(Resume r) {
        int index = getIndex(r.getUuid());
        if(getIndex(r.getUuid()) == -1) {
            System.out.println("Резюме " + r.getUuid() + " не существует!");
        } else {
            storage[index] = r;
            System.out.println("Резюме " + r.getUuid() + " перезаписано!");
        }
    }

    public Resume get(String uuid) {
        int index = getIndex(uuid);
        if(index == -1) {
            System.out.println("Данного резюме нет в базе");
            return null;
        }
        return storage[index];
    }

    public int size() {
        return size;
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    public void delete(String uuid) {
        int index = getIndex(uuid);
        if(index == -1) {
            System.out.println("Данного резюме нет в базе");
        } else {
            System.arraycopy(storage, index + 1, storage, index, size - 1);
            storage[size - 1] = null;
        }
    }

    public void clear() {
        Arrays.fill(storage, null);
        size = 0;
    }

    private int getIndex (String uuid) {
        for(int i = 0; i < size; i++) {
            if(storage[i].getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }
}
