package com.larandvik.webapp.exception;

public class NotExistStorageException extends StorageException {

    public NotExistStorageException(String uuid) {
        super(uuid);
    }
}
