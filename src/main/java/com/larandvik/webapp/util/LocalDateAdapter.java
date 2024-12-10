package com.larandvik.webapp.util;

import jakarta.xml.bind.annotation.adapters.XmlAdapter;

import java.time.LocalDate;

public class LocalDateAdapter extends XmlAdapter<String, LocalDate> {
    @Override
    public LocalDate unmarshal(String string)  {
return LocalDate.parse(string);    }

    @Override
    public String marshal(LocalDate localDate)  {
        return localDate.toString();
    }
}
