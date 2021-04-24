package ru.javaops.webapp.storage;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ru.javaops.webapp.exception.ExistStorageException;
import ru.javaops.webapp.exception.NotExistStorageException;
import ru.javaops.webapp.model.Resume;


public abstract class AbstractArrayStorageTest {
    private Storage storage;
    private static final String UUID_1 = "uuid1";
    private static final String UUID_2 = "uuid2";
    private static final String UUID_3 = "uuid3";
    private static final String UUID_4 = "uuid4";

    public AbstractArrayStorageTest(Storage storage) {
        this.storage = storage;
    }

    @Before
    public void setUp() throws Exception {
        storage.clear();
        storage.save(new Resume(UUID_1));
        storage.save(new Resume(UUID_2));
        storage.save(new Resume(UUID_3));
        //storage.save(new Resume(UUID_4));
    }


    @Test
    public void size() {
        Assert.assertEquals(3, storage.size());
    }

    @Test
    public void save() throws Exception {
        storage.save(new Resume(UUID_4));
        Assert.assertEquals(4, storage.size());
        Assert.assertEquals(new Resume(UUID_4), storage.get(UUID_4));
    }

    @Test(expected = NotExistStorageException.class)
    public void delete() {
        storage.delete(UUID_4);
        Assert.assertEquals(3, storage.size());
        storage.get(UUID_4);
    }

    @Test
    public void get() {
        Assert.assertEquals(new Resume(UUID_1), storage.get("uuid1"));
        Assert.assertEquals(new Resume(UUID_2), storage.get("uuid2"));
        Assert.assertEquals(new Resume(UUID_3), storage.get("uuid3"));
    }

    @Test(expected = NotExistStorageException.class)
    public void getNotExist() throws Exception {
        storage.get("dummy");
    }

    @Test
    public void clear() {
        storage.clear();
        Assert.assertEquals(0, storage.size());
    }

    @Test(expected = ExistStorageException.class)
    public void update() {
        Resume resume = new Resume(UUID_2);
        storage.save(resume);
        Assert.assertEquals(resume, storage.get(UUID_2));
    }

    @Test
    public void getAll() throws Exception {
        Resume[] all = storage.getAll();
        Assert.assertEquals(3, all.length);
        Assert.assertEquals(new Resume(UUID_1), all[0]);
        Assert.assertEquals(new Resume(UUID_2), all[1]);
        Assert.assertEquals(new Resume(UUID_3), all[2]);
    }
}