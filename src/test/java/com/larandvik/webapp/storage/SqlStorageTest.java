package com.larandvik.webapp.storage;

import com.larandvik.webapp.util.Config;

class SqlStorageTest extends AbstractStorageTest {

    public SqlStorageTest() {
        super(Config.get().getStorage());
    }
}