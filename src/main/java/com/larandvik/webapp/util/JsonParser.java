package com.larandvik.webapp.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.larandvik.webapp.model.Section;

import java.io.Reader;
import java.io.Writer;
import java.time.LocalDate;

public class JsonParser {
    private static final Gson GSON = new GsonBuilder()
            .setPrettyPrinting()
            .registerTypeAdapter(Section.class, new JsonSectionAdapter<>())
            .registerTypeAdapter(LocalDate.class, new LocalDateTypeAdapter())
            .create();


    public static <T> T read(Reader reader, Class<T> clazz) {
        return GSON.fromJson(reader, clazz);
    }

    public static <T> T read(String content, Class<T> clazz) {
        return GSON.fromJson(content, clazz);
    }

    public static <T> void write(T object, Writer writer) {
        GSON.toJson(object, writer);
    }

    public static <T> String write(T object) {
        return GSON.toJson(object);
    }

    public static <T> String write(T object, Class<T> clazz) {
        return GSON.toJson(object, clazz);
    }
}
