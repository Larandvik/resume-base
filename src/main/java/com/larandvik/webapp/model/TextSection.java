package com.larandvik.webapp.model;

import java.io.Serial;
import java.util.Objects;

public class TextSection extends Section {
    @Serial
    private static final long serialVersionUID = 1L;

    public static final TextSection EMPTY = new TextSection("");

    private String content;

    public TextSection(String content) {
        Objects.requireNonNull(content, "content must not be null");
        this.content = content;
    }

    public TextSection() {
    }

    public String getContent() {
        return content;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;

        TextSection that = (TextSection) o;
        return content.equals(that.content);
    }

    @Override
    public int hashCode() {
        return content.hashCode();
    }

    @Override
    public String toString() {
        return content;
    }
}
