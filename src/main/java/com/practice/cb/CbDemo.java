package com.practice.cb;

import com.couchbase.client.java.Bucket;
import com.couchbase.client.java.Cluster;
import com.couchbase.client.java.CouchbaseCluster;
import com.couchbase.client.java.document.JsonDocument;
import com.couchbase.client.java.document.json.JsonObject;
import com.couchbase.client.java.env.CouchbaseEnvironment;
import com.couchbase.client.java.env.DefaultCouchbaseEnvironment;

import java.util.UUID;

public class CbDemo {
    public static final Long CONNECTION_TIMEOUT = 10000L;
    public static final Long KEY_VALUE_FETCH_TIMEOUT = 3000L;
    public static final String BUCKET_NAME = "travel-sample";
    public static final String BUCKET_PASSWORD = "Knoldus@123";
    public static final String USER_NAME = "ayushprashar";
    public static final String CLUSTER_PASSWORD = "Knoldus@123";
    
    
    
    
    public static void main(String[] args) {
        CouchbaseEnvironment environment = DefaultCouchbaseEnvironment
                .builder()
                .connectTimeout(CONNECTION_TIMEOUT)
                .kvTimeout(KEY_VALUE_FETCH_TIMEOUT)
                .build();
        
        Cluster cluster = CouchbaseCluster.create(environment, "localhost:8091");
        cluster.authenticate(USER_NAME, CLUSTER_PASSWORD);
        Bucket bucket = cluster.openBucket(BUCKET_NAME);
    
        JsonObject jsonObject = JsonObject
                .empty()
                .put("name", "Ayush Prashar")
                .put("documentType", "Person")
                .put("email", "ayush.prashar@gmail.com")
                .put("hometown", "Delhi");
    
        String docId = UUID.randomUUID().toString();
    
        JsonDocument doc = JsonDocument.create(docId, jsonObject);
        
        JsonDocument inserted = bucket.insert(doc);
        System.out.println(inserted);
    }
}
