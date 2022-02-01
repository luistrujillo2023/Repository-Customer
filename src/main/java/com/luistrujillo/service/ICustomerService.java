package com.luistrujillo.service;

import com.luistrujillo.document.Customer;
import com.luistrujillo.document.dto.CustomerDTO;


import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ICustomerService {
	
	//metodo registrar 
	Mono<Customer>registrar(Customer customer);
	//metodo modificar 
	Mono<Customer>modificar(Customer customer);
	//metodo eliminar 
	Mono<Void> eliminar(String id);
	
	//metodo listar 
	Flux<Customer> listar();
	
	//metodo listar 
	Mono<Customer> listarPorId(String id);
	
	//encontrar por Id
	Mono<CustomerDTO> findByIdCustomer(String customerId);
    

}
