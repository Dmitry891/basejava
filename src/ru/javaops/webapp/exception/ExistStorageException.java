package ru.javaops.webapp.exception;

public class ExistStorageException extends StorageException {
    public ExistStorageException(String uuid) {
        super("Resume " + uuid + " is already exist", uuid);
    }
}
