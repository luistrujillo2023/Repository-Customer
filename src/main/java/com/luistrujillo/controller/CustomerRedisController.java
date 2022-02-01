package com.luistrujillo.controller;

import java.util.Map;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.luistrujillo.document.Customer;
import com.luistrujillo.service.CustomerRepository;
import com.luistrujillo.service.ICustomerService;

@RestController
public class CustomerRedisController {
	
	
private CustomerRepository  customerRepository ; 
	
	//constructor 
	public CustomerRedisController(ICustomerService service, CustomerRepository customerRepository) {
		this.customerRepository = customerRepository;
	}

	@GetMapping("/students")
    public Map<String,Customer> findAll() {
        return customerRepository.findAll();
    }

    @GetMapping("/students/{id}")
    public Customer findById(@PathVariable String id) {
        return customerRepository.findById(id);
    }

    @PostMapping("/students")
    public void createStudent(@RequestBody Customer customer) {
    	customerRepository.save(customer);
    }

    @DeleteMapping("/students/{id}")
    public void deleteCustomer(@PathVariable String id) {
    	customerRepository.delete(id);
    }
	
}
