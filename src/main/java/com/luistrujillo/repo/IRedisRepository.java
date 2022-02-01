package com.luistrujillo.repo;

import java.util.Map;

import com.luistrujillo.document.Customer;

public interface IRedisRepository {
	
	Map<String, Customer> findAll();
	Customer findById(String id);
    void save(Customer  customer);
    void delete(String id);

}
