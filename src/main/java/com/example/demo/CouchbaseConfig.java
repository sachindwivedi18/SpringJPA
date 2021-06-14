package com.example.demo;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.couchbase.config.AbstractCouchbaseConfiguration;
import org.springframework.data.couchbase.repository.config.EnableCouchbaseRepositories;

@Configuration
public class CouchbaseConfig extends AbstractCouchbaseConfiguration {

    @Override
    public String getConnectionString() {
        return "couchbase://127.0.0.1";
    }

    @Override
    public String getUserName() {
        return "sach";
    }

    @Override
    public String getPassword() {
        return "18@qwerty";
    }

    @Override
    public String getBucketName() {
        return "Customer";
    }
}