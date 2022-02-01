package com.luistrujillo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.luistrujillo.document.Customer;
import com.luistrujillo.document.dto.CustomerDTO;
import com.luistrujillo.repo.ICustomerRepo;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CustomerServiceImpl   implements ICustomerService{

	@Autowired
	private ICustomerRepo repo;
	
	@Override
	@Transactional(readOnly = true)
	public Mono<Customer> registrar(Customer customer) {
		return repo.save(customer);
	}

	@Override
	@Transactional(readOnly = true)
	public Mono<Customer> modificar(Customer customer) {
		return repo.save(customer);
	}

	@Override
	@Transactional(readOnly = true)
	public Flux<Customer> listar() {
		return repo.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Mono<Customer> listarPorId(String id) {
		return repo.findById(id);
	}

	@Override
	@Transactional(readOnly = true)
	public Mono<Void> eliminar(String id) {
		return repo.deleteById(id);
	}

	@Override
	@Transactional(readOnly = true)
	public Mono<CustomerDTO> findByIdCustomer(String customerId) {
		// TODO Auto-generated method stub
		return null;
	}

}
