package com.arthuro.reactivespringwebflux.handler;

import static org.springframework.web.reactive.function.server.ServerResponse.ok;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.arthuro.reactivespringwebflux.model.User;
import com.arthuro.reactivespringwebflux.service.UserService;

import reactor.core.publisher.Mono;

@Component
public class UserHandler {

	@Autowired
	UserService userService;

	public Mono<ServerResponse> create(ServerRequest serverRequest) {
		return serverRequest.bodyToMono(User.class).flatMap(userService::create)
				.flatMap(data -> ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).bodyValue(data))
				.onErrorResume(error -> {
					if (error instanceof OptimisticLockingFailureException) {
						return ServerResponse.status(HttpStatus.BAD_REQUEST).build();
					}
					return ServerResponse.status(500).build();
				});
	}

	public Mono<ServerResponse> findAll(ServerRequest request) {
		return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(userService.findAll(), User.class);
	}

	public Mono<ServerResponse> findById(ServerRequest request) {
		return userService.findById(request.pathVariable("id"))
				.flatMap(data -> ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).bodyValue(data))
				.switchIfEmpty(ServerResponse.notFound().build());
	}

	public Mono<ServerResponse> delete(ServerRequest request) {
		return ok().contentType(MediaType.APPLICATION_JSON)
				.body(userService.deleteById(request.pathVariable("id")), Void.class).log();
	}

	public Mono<ServerResponse> findByName(ServerRequest request) {
		String name = request.queryParam("name").get();
		return ok().contentType(MediaType.APPLICATION_JSON).body(userService.findByName(name), User.class).log();
	}

	public Mono<ServerResponse> findByNameNot(ServerRequest request) {
		String name = request.queryParam("name").get();
		return ok().contentType(MediaType.APPLICATION_JSON).body(userService.findByNameNot(name), User.class).log();
	}

	public Mono<ServerResponse> findByNameLike(ServerRequest request) {
		String name = request.queryParam("name").get();
		return ok().contentType(MediaType.APPLICATION_JSON).body(userService.findByNameLike(name), User.class).log();
	}

	public Mono<ServerResponse> findByNameRegex(ServerRequest request) {
		String name = request.queryParam("name").get();
		String regex = "^" + name + "";
		return ok().contentType(MediaType.APPLICATION_JSON).body(userService.findByNameRegex(regex), User.class)
				.log();
	}

}
