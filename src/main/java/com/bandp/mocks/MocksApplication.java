package com.bandp.mocks;

import com.bandp.mocks.repositories.GenericRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.trace.http.HttpTraceRepository;
import org.springframework.boot.actuate.trace.http.InMemoryHttpTraceRepository;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class MocksApplication {

	@Autowired
	private GenericRepository repository;

	public static void main(String[] args) {
		SpringApplication.run(MocksApplication.class, args);
	}

	@Bean
	HttpTraceRepository httpTraceRepository() {
		return new InMemoryHttpTraceRepository();
	}

}
