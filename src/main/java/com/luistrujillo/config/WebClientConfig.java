package com.luistrujillo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.config.EnableWebFlux;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
@EnableWebFlux
public class WebClientConfig {
	
	/**
	   * Method to connect with other microservice.
	   *
	   * @return connection microservice.
	   */
	  @Bean
	  public WebClient getWebClient() {
	    return WebClient.builder()
	      .baseUrl("http://localhost:8081")
	      .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
	      .build();
	  }

}
