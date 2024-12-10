package com.larandvik.webapp.storage;

import com.larandvik.webapp.storage.serializer.XmlStreamSerializer;

class XmlPathStorageTest extends AbstractStorageTest {

    public XmlPathStorageTest() {
        super(new PathStorage(STORAGE_DIR.getAbsolutePath(), new XmlStreamSerializer()));
    }
}