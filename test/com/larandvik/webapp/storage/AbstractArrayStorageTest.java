package com.larandvik.webapp.storage;

import com.larandvik.webapp.exception.StorageException;
import com.larandvik.webapp.model.Resume;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

abstract class AbstractArrayStorageTest extends AbstractStorageTest {

    protected AbstractArrayStorageTest(Storage storage) {
        super(storage);
    }

    @Test
    void shouldThrowOverflowExceptionWhenStorageFull() {
        try {
            for (int i = 4; i < AbstractArrayStorage.STORAGE_LIMIT; i++) {
                storage.save(new Resume("name" + i));
            }
        } catch (StorageException e) {
            Assertions.fail();
        }
        storage.save(new Resume("Overflow"));
    }
}