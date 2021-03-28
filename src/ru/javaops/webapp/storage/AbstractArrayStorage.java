package ru.javaops.webapp.storage;

import ru.javaops.webapp.model.Resume;

import java.util.Arrays;

public abstract class AbstractArrayStorage implements Storage {
    protected static final int STORAGE_LIMIT = 10_000;
    protected Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size = 0;

    public int size() {
        return size;
    }

    public void save(Resume resume) {
        int index = getIndex(resume.getUuid());
        if (index > 0) {
            System.out.println("Резюме " + resume.getUuid() + " уже есть в базе!");
        } else if (size == STORAGE_LIMIT) {
            System.out.println("База резюме переполнена!");
        } else {
            insertElement(resume, index);
            size++;
        }
    }

    public void delete(String uuid) {
        int index = getIndex(uuid);
        if (index > 0) {
            System.out.println("Резюме " + uuid + " нет в базе!");
        } else {
            fillDeletedElement(index);
            storage[size - 1] = null;
            size--;
        }
    }

    public Resume get(String uuid) {
        int index = getIndex(uuid);
        if (index < 0) {
            System.out.println("Резюме " + uuid + " нет в базе!");
            return null;
        }
        return storage[index];
    }

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public void update(Resume resume) {
        int index = getIndex(resume.getUuid());
        String getUuid = resume.getUuid();
        if (getIndex(getUuid) < 0) {
            System.out.println("Резюме " + getUuid + " не существует!");
        } else {
            storage[index] = resume;
            System.out.println("Резюме " + getUuid + " перезаписано!");
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    protected abstract int getIndex(String uuid);

    protected abstract void insertElement(Resume resume, int index);

    protected abstract void fillDeletedElement(int index);
}
