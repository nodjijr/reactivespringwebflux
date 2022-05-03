package com.arthuro.reactivespringwebflux;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.arthuro.reactivespringwebflux.handler.UserHandler;
import com.arthuro.reactivespringwebflux.repository.UserRepository;
import com.arthuro.reactivespringwebflux.service.UserService;

@SpringBootTest
class ReactivespringwebfluxApplicationTests {

	@Autowired
	private UserHandler controller;

	@Autowired
	private UserService service;

	@Autowired
	private UserRepository repository;

	@Test
	void contextLoads() {
		assertThat(controller).isNotNull();
		assertThat(service).isNotNull();
		assertThat(repository).isNotNull();
	}

}
