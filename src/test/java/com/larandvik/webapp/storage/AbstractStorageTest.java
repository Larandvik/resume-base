package com.larandvik.webapp.storage;

import com.larandvik.webapp.exception.ExistStorageException;
import com.larandvik.webapp.exception.NotExistStorageException;
import com.larandvik.webapp.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.time.Month;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

abstract class AbstractStorageTest {

    protected static final File STORAGE_DIR = new File("E:\\IDEA_Project\\resume-base\\storage");
    protected final Storage storage;

    private static final String UUID_1 = "uuid_1";
    private static final String UUID_2 = "uuid_2";
    private static final String UUID_3 = "uuid_3";
    private static final String UUID_4 = "uuid_4";

    private static final Resume R1;
    private static final Resume R2;
    private static final Resume R3;
    private static final Resume R4;

    static {
        R1 = new Resume(UUID_1, "Name1");
        R2 = new Resume(UUID_2, "Name2");
        R3 = new Resume(UUID_3, "Name3");
        R4 = new Resume(UUID_4, "Name4");

        R1.addContact(ContactType.MAIL, "mail@gmail.com");
        R1.addContact(ContactType.PHONE, "1111111111");
        R1.addSection(SectionType.OBJECTIVE, new TextSection("Objective1"));
        R1.addSection(SectionType.PERSONAL, new TextSection("Personal data"));
        R1.addSection(SectionType.ACHIEVEMENT, new ListSection("Achievement1", "Achievement2", "Achievement3"));
        R1.addSection(SectionType.QUALIFICATION, new ListSection("Java", "SQL", "JavaScript"));
        R1.addSection(SectionType.EXPERIENCE,
                new OrganizationSection(
                        new Organization("Organization1", "http://Organization1.com",
                                new Organization.Position(2005, Month.JANUARY, "position1", "content1"),
                                new Organization.Position(2001, Month.MARCH, 2005, Month.JANUARY, "position2", "content2"))));
        R1.addSection(SectionType.EDUCATION,
                new OrganizationSection(
                        new Organization("Institute", null,
                                new Organization.Position(1996, Month.JANUARY, 2000, Month.DECEMBER, "aspirant", null),
                                new Organization.Position(2001, Month.MARCH, 2005, Month.JANUARY, "student", "IT faculty")),
                        new Organization("Organization12", "https://Organization12.com")));
        R2.addContact(ContactType.SKYPE, "skype@gmail.com");
        R2.addContact(ContactType.PHONE, "222222222222");
        R2.addSection(SectionType.EXPERIENCE,
                new OrganizationSection(
                        new Organization("Organization2", "https://organization2.com",
                                new Organization.Position(2005, Month.MARCH, "position2", "content2")
                        )
                )
        );
    }

    protected AbstractStorageTest(Storage storage) {
        this.storage = storage;
    }

    @BeforeEach
    public void setUp() {
        storage.clear();
        storage.save(R1);
        storage.save(R2);
        storage.save(R3);
    }

    @Test
    void save() {
        storage.save(R4);
        assertSize(4);
        assertGet(R4);
    }

    @Test
    void shouldThrowExistStorageExceptionWhenSavingExistingKey() {
        assertThrows(ExistStorageException.class,
                () -> storage.save(new Resume(UUID_1, "name")));
    }

    @Test
    void update() {
        Resume newResume = new Resume(UUID_1, "new Name");
        storage.update(newResume);
        assertEquals(newResume, storage.get(UUID_1));
    }

    @Test()
    void shouldThrowNotExistStorageExceptionWhenUpdatingNonExistingKey() {
        assertThrows(NotExistStorageException.class,
                () -> storage.get("dummy"));
    }

    @Test
    void size() {
        assertSize(3);
    }

    @Test
    void get() {
        assertGet(R1);
        assertGet(R2);
        assertGet(R3);
    }

    @Test
    void getAllSorted() {
        List<Resume> list = storage.getAllSorted();
        assertEquals(3, list.size());
        assertEquals(list, Arrays.asList(R1, R2, R3));
    }

    @Test()
    void shouldThrowNotExistStorageExceptionWhenGettingNonExistingKey() {
        assertThrows(NotExistStorageException.class,
                () -> storage.get("dummy"));
    }

    @Test
    void delete() {
        storage.delete(UUID_1);
        assertSize(2);
    }

    @Test()
    void shouldThrowNotExistStorageExceptionWhenDeletingNonExistingKey() {
        assertThrows(NotExistStorageException.class,
                () -> storage.get("dummy"));
    }

    @Test
    void clear() {
        storage.clear();
        assertSize(0);
    }

    private void assertGet(Resume resume) {
        assertEquals(resume, storage.get(resume.getUuid()));
    }

    private void assertSize(int size) {
        assertEquals(size, storage.size());
    }
}