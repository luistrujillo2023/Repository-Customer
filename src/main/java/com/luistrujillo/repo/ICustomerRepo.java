package com.luistrujillo.repo;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import com.luistrujillo.document.Customer;

import reactor.core.publisher.Mono;

//@Repository
public interface ICustomerRepo   extends ReactiveMongoRepository<Customer,String>{
	Mono<Customer> findByDocumentNumber(String documentNumber);

}
