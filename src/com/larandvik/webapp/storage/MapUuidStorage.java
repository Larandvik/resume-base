package com.larandvik.webapp.storage;

import com.larandvik.webapp.model.Resume;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapUuidStorage extends AbstractStorage {
    private final Map<String, Resume> map = new HashMap<>();

    @Override
    protected Object getSearchKey(String uuid) {
        return uuid;
    }

    @Override
    protected void doSave(Resume resume, Object uuid) {
        map.put((String) uuid, resume);
    }

    @Override
    protected void doUpdate(Resume resume, Object uuid) {
        map.put((String) uuid, resume);
    }

    @Override
    protected Resume doGet(Object uuid) {
        return map.get((String) uuid);
    }

    @Override
    protected void doDelete(Object uuid) {
        map.remove((String) uuid);
    }

    @Override
    protected boolean isExist(Object uuid) {
        return map.containsKey((String) uuid);
    }

    @Override
    public void clear() {
        map.clear();
    }

    @Override
    public List<Resume> doCopyAll() {
        return new ArrayList<>(map.values());
    }

    @Override
    public int size() {
        return map.size();
    }
}
