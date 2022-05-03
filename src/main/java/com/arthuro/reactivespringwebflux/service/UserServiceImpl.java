package com.arthuro.reactivespringwebflux.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.stereotype.Service;

import com.arthuro.reactivespringwebflux.model.User;
import com.arthuro.reactivespringwebflux.repository.UserRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepository;
	@Autowired
	ReactiveMongoTemplate reactiveMongoTemplate;

	@Override
	public Mono<User> create(User user) {
		return userRepository.save(user);
	}

	@Override
	public Flux<User> findAll() {
		return userRepository.findAll();
	}

	@Override
	public Mono<User> findById(String id) {
		return userRepository.findById(id);
	}

	@Override
	public Mono<Void> deleteById(String id) {
		return userRepository.deleteById(id);
	}

	@Override
	public Flux<User> findByName(String name) {
		return userRepository.findByName(name);
	}

	@Override
	public Flux<User> findByNameNot(String name) {
		return userRepository.findByNameNot(name);
	}

	@Override
	public Flux<User> findByNameLike(String name) {
		return userRepository.findByNameLike(name);
	}

	@Override
	public Flux<User> findByNameRegex(String name) {
		return userRepository.findByNameRegex(name);
	}

}
