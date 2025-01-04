package com.larandvik.webapp.util;

import com.larandvik.webapp.model.Resume;
import com.larandvik.webapp.model.Section;
import com.larandvik.webapp.model.TextSection;
import org.junit.jupiter.api.Test;

import static com.larandvik.webapp.TestData.R1;
import static org.junit.jupiter.api.Assertions.assertEquals;

class JsonParserTest {

    @Test
    void testResume() {
        String json = JsonParser.write(R1);
        System.out.println(json);
        Resume resume = JsonParser.read(json, Resume.class);
        assertEquals(R1, resume);
    }

    @Test
    void write() {
        Section section1 = new TextSection("Objective1");
        String json = JsonParser.write(section1, Section.class);
        System.out.println(json);
        Section section2 = JsonParser.read(json, Section.class);
        assertEquals(section1, section2);
    }
}