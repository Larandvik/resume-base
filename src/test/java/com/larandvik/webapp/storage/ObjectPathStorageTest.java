package com.larandvik.webapp.storage;

import com.larandvik.webapp.storage.serializer.ObjectStreamSerializer;

class ObjectPathStorageTest extends AbstractStorageTest {

    public ObjectPathStorageTest() {
        super(new PathStorage(STORAGE_DIR.getAbsolutePath(), new ObjectStreamSerializer()));
    }
}