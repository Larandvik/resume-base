package com.larandvik.webapp.storage;

import com.larandvik.webapp.model.Resume;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapUuidStorage extends AbstractStorage<String> {
    private final Map<String, Resume> map = new HashMap<>();

    @Override
    protected String getSearchKey(String uuid) {
        return uuid;
    }

    @Override
    protected void doSave(Resume resume, String uuid) {
        map.put(uuid, resume);
    }

    @Override
    protected void doUpdate(Resume resume, String uuid) {
        map.put(uuid, resume);
    }

    @Override
    protected Resume doGet(String uuid) {
        return map.get(uuid);
    }

    @Override
    protected void doDelete(String uuid) {
        map.remove(uuid);
    }

    @Override
    protected boolean isExist(String uuid) {
        return map.containsKey(uuid);
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
