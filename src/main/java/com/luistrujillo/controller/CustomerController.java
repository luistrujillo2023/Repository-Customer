package com.luistrujillo.controller;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.luistrujillo.document.Customer;
import com.luistrujillo.service.CustomerRepository;
import com.luistrujillo.service.ICustomerService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;



@RestController
//@RequestMapping("/customer")
@RequestMapping(value = "/api/v1/customer")
public class CustomerController {
	
	
	@Autowired
	private ICustomerService service;
	
	
	
	
	// metodo listar 
	@GetMapping
	public Mono<ResponseEntity<Flux<Customer>>> listar(){		
		Flux<Customer> fxCustomer = service.listar();
		
		return Mono.just(ResponseEntity
				.ok()
				.contentType(MediaType.APPLICATION_JSON)
				.body(fxCustomer)
				);
	}
     
	//metodo listar por id
	@GetMapping("/{id}")
	public Mono<ResponseEntity<Customer>> listarPorId(@PathVariable("id") String id){
		return service.listarPorId(id)
				.map(c -> ResponseEntity
						.ok()
						.contentType(MediaType.APPLICATION_JSON)
						.body(c)
						)				
				.defaultIfEmpty(ResponseEntity.notFound().build());
	}
	
	//metodo registar 
	@PostMapping
	public Mono<ResponseEntity<Customer>> registrar(@RequestBody Customer c, final ServerHttpRequest req){
		return service.registrar(c)
				.map(cu -> ResponseEntity.created(URI.create(req.getURI().toString().concat("/").concat(cu.getId())))
						.contentType(MediaType.APPLICATION_JSON)
						.body(cu)
					);
	}
	
	//metodo modificar 
	
	@PutMapping("/{id}")
	public Mono<ResponseEntity<Customer>> modificar(@PathVariable("id") String id, @RequestBody Customer c){
		
		Mono<Customer> monoBody = Mono.just(c);
		Mono<Customer> monoBD = service.listarPorId(id);
		
		return monoBD
				.zipWith(monoBody, (bd, cu) -> {
					bd.setId(id);
					bd.setFirstName(cu.getFirstName());									
					return bd;
				})
				.flatMap(service::modificar)
				.map(cu -> ResponseEntity.ok()
						.contentType(MediaType.APPLICATION_JSON)
						.body(cu))
				.defaultIfEmpty(new ResponseEntity<Customer>(HttpStatus.NOT_FOUND));
	}
	
	
	
	//metodo eliminar 
	
	@DeleteMapping("/{id}")
	public Mono<ResponseEntity<Void>> eliminar(@PathVariable("id") String id){
		return service.listarPorId(id)
				.flatMap(p -> {
					return service.eliminar(p.getId())
							.then(Mono.just(new ResponseEntity<Void>(HttpStatus.NO_CONTENT)));
				})				
				.defaultIfEmpty(new ResponseEntity<Void>(HttpStatus.NOT_FOUND));
	}
	
	
	
	
	
	
	
	
	
	
}
