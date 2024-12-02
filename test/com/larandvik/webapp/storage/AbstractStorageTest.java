package com.larandvik.webapp.storage;

import com.larandvik.webapp.exception.ExistStorageException;
import com.larandvik.webapp.exception.NotExistStorageException;
import com.larandvik.webapp.exception.StorageException;
import com.larandvik.webapp.model.Resume;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

abstract class AbstractStorageTest {
    private final Storage storage;

    private static final String UUID_1 = "uuid_1";
    private static final Resume RESUME_1 = new Resume(UUID_1);

    private static final String UUID_2 = "uuid_2";
    private static final Resume RESUME_2 = new Resume(UUID_2);

    private static final String UUID_3 = "uuid_3";
    private static final Resume RESUME_3 = new Resume(UUID_3);

    private static final String UUID_4 = "uuid_4";
    private static final Resume RESUME_4 = new Resume(UUID_4);


    protected AbstractStorageTest(Storage storage) {
        this.storage = storage;
    }

    @BeforeEach
    public void setUp() {
        storage.clear();
        storage.save(RESUME_1);
        storage.save(RESUME_2);
        storage.save(RESUME_3);
    }

    @Test
    void save() {
        storage.save(RESUME_4);
        assertSize(4);
        assertGet(RESUME_4);
    }

    @Test
    void shouldThrowExistStorageExceptionWhenSavingExistingKey() {
        Assertions.assertThrows(ExistStorageException.class,
                () -> storage.save(new Resume(UUID_1)));
    }

    @Test
    void shouldThrowOverflowExceptionWhenStorageFull() {
        try {
            for (int i = 4; i < AbstractArrayStorage.STORAGE_LIMIT; i++) {
                storage.save(new Resume());
            }
        } catch (StorageException e) {
            Assertions.fail();
        }
        storage.save(new Resume());
    }

    @Test
    void update() {
        Resume newResume = new Resume(UUID_1);
        storage.update(newResume);
        Assertions.assertSame(newResume, storage.get(UUID_1));
    }

    @Test()
    void shouldThrowNotExistStorageExceptionWhenUpdatingNonExistingKey() {
        Assertions.assertThrows(NotExistStorageException.class,
                () -> storage.get("dummy"));
    }

    @Test
    void size() {
        assertSize(3);
    }

    @Test
    void get() {
        assertGet(RESUME_1);
        assertGet(RESUME_2);
        assertGet(RESUME_3);
    }

    @Test
    void getAll() {
        Resume[] array = storage.getAll();
        Assertions.assertEquals(3, array.length);
        Assertions.assertEquals(RESUME_1, array[0]);
        Assertions.assertEquals(RESUME_2, array[1]);
        Assertions.assertEquals(RESUME_3, array[2]);
    }

    @Test()
    void shouldThrowNotExistStorageExceptionWhenGettingNonExistingKey() {
        Assertions.assertThrows(NotExistStorageException.class,
                () -> storage.get("dummy"));
    }

    @Test
    void delete() {
        storage.delete(UUID_1);
        assertSize(2);
    }

    @Test()
    void shouldThrowNotExistStorageExceptionWhenDeletingNonExistingKey() {
        Assertions.assertThrows(NotExistStorageException.class,
                () -> storage.get("dummy"));
    }

    @Test
    void clear() {
        storage.clear();
        assertSize(0);
    }

    private void assertGet(Resume resume) {
        Assertions.assertEquals(resume, storage.get(resume.getUuid()));
    }

    private void assertSize(int size) {
        Assertions.assertEquals(size, storage.size());
    }
}