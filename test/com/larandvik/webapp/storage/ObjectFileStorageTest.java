package com.larandvik.webapp.storage;

import com.larandvik.webapp.storage.serializer.ObjectStreamSerializer;

class ObjectFileStorageTest extends AbstractStorageTest {

    public ObjectFileStorageTest() {
        super(new FileStorage(STORAGE_DIR, new ObjectStreamSerializer()));
    }
}