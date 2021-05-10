package ru.javaops.webapp.storage;

import org.junit.Before;
import org.junit.Test;
import ru.javaops.webapp.exception.ExistStorageException;
import ru.javaops.webapp.exception.NotExistStorageException;
import ru.javaops.webapp.model.Resume;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public abstract class AbstractStorageTest {
    protected Storage storage;
    private static final String UUID_1 = "uuid1";
    private static final String UUID_2 = "uuid2";
    private static final String UUID_3 = "uuid3";
    private static final String UUID_4 = "uuid4";

    public AbstractStorageTest(Storage storage) {
        this.storage = storage;
    }

    @Before
    public void setUp() throws Exception {
        storage.clear();
        storage.save(new Resume(UUID_1, "name_1"));
        storage.save(new Resume(UUID_2, "name_2"));
        storage.save(new Resume(UUID_3, "name_3"));
    }

    @Test
    public void size() {
        assertEquals(3, storage.size());
    }

    @Test
    public void save() throws Exception {
        storage.save(new Resume(UUID_4, "name_4"));
        assertEquals(4, storage.size());
        assertEquals(new Resume(UUID_4, "name_4"), storage.get(UUID_4));
    }

    @Test(expected = ExistStorageException.class)
    public void saveExist() throws Exception {
        storage.save(new Resume(UUID_1, "name_1"));
    }

    @Test(expected = NotExistStorageException.class)
    public void delete() {
        storage.delete(UUID_4);
        assertEquals(3, storage.size());
        storage.get(UUID_4);
    }

    @Test(expected = NotExistStorageException.class)
    public void deleteNotExist() throws Exception {
        storage.delete("dummy");
    }

    @Test
    public void get() {
        assertEquals(new Resume(UUID_1, "name_1"), storage.get(UUID_1));
        assertEquals(new Resume(UUID_2, "name_2"), storage.get(UUID_2));
        assertEquals(new Resume(UUID_3, "name_3"), storage.get(UUID_3));
    }

    @Test(expected = NotExistStorageException.class)
    public void getNotExist() throws Exception {
        storage.get("dummy");
    }

    @Test
    public void clear() {
        storage.clear();
        assertEquals(0, storage.size());
    }

    @Test
    public void update() throws Exception {
        Resume resume = new Resume(UUID_2, "New name");
        storage.update(resume);
        assertEquals(resume, storage.get(UUID_2));
    }

    @Test
    public void getAllSorted() throws Exception {
        List<Resume> list = storage.getAllSorted();
        assertEquals(3, list.size());
        assertEquals(list, Arrays.asList(new Resume(UUID_1, "name_1"),
        new Resume(UUID_2, "name_2"), new Resume(UUID_3,"name_3")));
    }

}