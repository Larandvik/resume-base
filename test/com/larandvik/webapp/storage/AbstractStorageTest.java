package com.larandvik.webapp.storage;

import com.larandvik.webapp.exception.ExistStorageException;
import com.larandvik.webapp.exception.NotExistStorageException;
import com.larandvik.webapp.model.Resume;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

abstract class AbstractStorageTest {
    protected final Storage storage;

    private static final String UUID_1 = "uuid_1";
    private static final Resume RESUME_1 = new Resume(UUID_1, "Name1");

    private static final String UUID_2 = "uuid_2";
    private static final Resume RESUME_2 = new Resume(UUID_2, "Name2");

    private static final String UUID_3 = "uuid_3";
    private static final Resume RESUME_3 = new Resume(UUID_3, "Name3");

    private static final String UUID_4 = "uuid_4";
    private static final Resume RESUME_4 = new Resume(UUID_4, "Name4");


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
                () -> storage.save(new Resume(UUID_1, "name")));
    }

    @Test
    void update() {
        Resume newResume = new Resume(UUID_1, "new Name");
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
    void getAllSorted() {
        List<Resume> list = storage.getAllSorted();
        Assertions.assertEquals(3, list.size());
        Assertions.assertEquals(list, Arrays.asList(RESUME_1, RESUME_2, RESUME_3));
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