package com.arthuro.reactivespringwebflux.repository;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.arthuro.reactivespringwebflux.model.User;

import reactor.core.publisher.Flux;

public interface UserRepository extends ReactiveMongoRepository<User, String> {

	public Flux<User> findByName(String name);

	public Flux<User> findByNameNot(String name);

	public Flux<User> findByNameLike(String name);

	public Flux<User> findByNameRegex(String name);

	@Query("{'name' : ?0}")
	public Flux<User> findProjectByNameQuery(String name);

	@Query(value = "{ 'name' : { $regex: ?0 } }", fields = "{'name' : 1,'cost':1}")
	Flux<User> findByNameRegexQuery(String regexp);

}
