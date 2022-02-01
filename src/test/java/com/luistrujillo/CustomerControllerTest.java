package com.luistrujillo;

import static org.mockito.ArgumentMatchers.any;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import com.luistrujillo.controller.CustomerController;
import com.luistrujillo.document.Customer;
import com.luistrujillo.repo.ICustomerRepo;
import com.luistrujillo.service.CustomerServiceImpl;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;




@ExtendWith(SpringExtension.class)
@WebFluxTest(controllers = CustomerController.class)
@Import(CustomerServiceImpl.class)
public class CustomerControllerTest {
	
	@MockBean
	private ICustomerRepo repo ; 
	
	@Autowired
	private WebTestClient client;
	
	
	//metodo listar 
	@Test
	void listarTest() {
		Customer customer = new Customer();
		 customer.setId("1");
		 customer.setFirstName("Luis");
		
		
		Customer customer2 = new Customer();
		customer2.setId("2");
		customer2.setFirstName("Alberto");
		
		
		List<Customer> list = new ArrayList<>();
		list.add(customer);
		list.add(customer2);

		Flux<Customer> fxCustomer = Flux.fromIterable(list);

		Mockito.when(repo.findAll()).thenReturn(fxCustomer);

		client.get().uri("/customer").accept(MediaType.APPLICATION_JSON)
				.exchange()
				.expectStatus()
				.isOk()
				.expectHeader()
				.contentType(MediaType.APPLICATION_JSON)
				.expectBodyList(Customer.class).hasSize(2);
	}
	
	//metodo registrar 
	
	@Test
	void registrarTest() {
		Customer customer = new Customer();
		customer.setId("1");
		customer.setFirstName("Rosa");
		
		Mockito.when(repo.save(any())).thenReturn(Mono.just(customer));

		client.post().uri("/customer")
				.body(Mono.just(customer), Customer.class)
				.accept(MediaType.APPLICATION_JSON)
				.exchange()
				.expectStatus().isCreated()
				.expectHeader().contentType(MediaType.APPLICATION_JSON)
				.expectBody()
				.jsonPath("$.firstName").isNotEmpty();
	}
	
	//metodo modificar 
	@Test
	void modificarTest() {
		Customer customer = new Customer();
		customer.setId("1");
		customer.setFirstName("Rosa");

		Mockito.when(repo.findById("1")).thenReturn(Mono.just(customer));
		Mockito.when(repo.save(any())).thenReturn(Mono.just(customer));

		client.put().uri("/customer/" + customer.getId())
				.body(Mono.just(customer), Customer.class)
				.accept(MediaType.APPLICATION_JSON)
				.exchange().expectStatus().isOk()
				.expectHeader()
				.contentType(MediaType.APPLICATION_JSON)
				.expectBody().jsonPath("$.firstName").isNotEmpty();				
	}
	
	
	@Test
	void eliminarTest() {
		Customer customer = new Customer();
		customer.setId("1");

		Mockito.when(repo.findById("1")).thenReturn(Mono.just(customer));
		Mockito.when(repo.deleteById("1")).thenReturn(Mono.empty());

		client.delete().uri("/customer/" + customer.getId())
		.exchange()
		.expectStatus().isNoContent();
	}
	
	
	
}
