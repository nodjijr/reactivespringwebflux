package com.arthuro.reactivespringwebflux.service;

import com.arthuro.reactivespringwebflux.model.User;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface UserService {

	public Mono<User> create(User project);

	public Flux<User> findAll();

	public Mono<User> findById(String id);

	public Mono<Void> deleteById(String id);

	public Flux<User> findByName(String name);

	public Flux<User> findByNameNot(String name);

	public Flux<User> findByNameLike(String name);

	public Flux<User> findByNameRegex(String name);

}
