package com.example.demo;

import org.springframework.data.couchbase.repository.CouchbaseRepository;
import org.springframework.stereotype.Repository;
import model.Customer;

@Repository
public interface CustomerRepo extends CouchbaseRepository<Customer, Integer>{

	
}