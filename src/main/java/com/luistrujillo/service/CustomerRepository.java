package com.luistrujillo.service;

import java.util.Map;
import java.util.UUID;

import javax.annotation.PostConstruct;

import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import com.luistrujillo.document.Customer;
import com.luistrujillo.repo.IRedisRepository;


public class CustomerRepository    implements  IRedisRepository{

	 private static final String KEY = "Customer";
	
	private RedisTemplate<String, Customer> redisTemplate;
	private HashOperations hashOperations;
	
	public CustomerRepository(RedisTemplate<String, Customer> redisTemplate) {
		this.redisTemplate = redisTemplate;
	}
	
	@PostConstruct
    private void init() {
        hashOperations = redisTemplate.opsForHash();
    }
	

	@Override
	public Map<String, Customer> findAll() {
		return hashOperations.entries(KEY);
	}

	@Override
	public Customer findById(String id) {
		return (Customer) hashOperations.get(KEY, id);
	}

	@Override
	public void save(Customer customer) {
		hashOperations.put(KEY, UUID.randomUUID().toString(),customer);
		
	}

	@Override
	public void delete(String id) {
		hashOperations.delete(KEY, id);
		
	}

}
