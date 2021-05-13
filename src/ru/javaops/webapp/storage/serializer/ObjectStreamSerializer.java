package ru.javaops.webapp.storage.serializer;

import ru.javaops.webapp.exception.StorageException;
import ru.javaops.webapp.model.Resume;
import ru.javaops.webapp.storage.serializer.StreamSerializer;

import java.io.*;

public class ObjectStreamSerializer implements StreamSerializer {

    @Override
    public void doWrite(Resume r, OutputStream os) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(os)) {
            oos.writeObject(r);
        }

    }

    @Override
    public Resume doRead(InputStream is) throws IOException {
        try (ObjectInputStream ois = new ObjectInputStream(is)) {
            return (Resume) ois.readObject();
        } catch (ClassNotFoundException e) {
            throw new StorageException("Error read resume",null, e);
        }
    }
}
