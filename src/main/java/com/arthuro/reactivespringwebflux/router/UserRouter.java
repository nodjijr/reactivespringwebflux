package com.arthuro.reactivespringwebflux.router;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.arthuro.reactivespringwebflux.handler.UserHandler;

@Configuration
public class UserRouter {
	
	@Bean
	public RouterFunction<ServerResponse> routeUsers(UserHandler handler) {
		return RouterFunctions
				.route(RequestPredicates.POST("/user/create")
						.and(RequestPredicates.accept(MediaType.APPLICATION_JSON)), handler::create)
				.andRoute(RequestPredicates.GET("/user")
						.and(RequestPredicates.accept(MediaType.APPLICATION_JSON)), handler::findAll)
				.andRoute(RequestPredicates.GET("/user/{id}")
						.and(RequestPredicates.accept(MediaType.APPLICATION_JSON)), handler::findById)
				.andRoute(RequestPredicates.DELETE("/user/{id}")
						.and(RequestPredicates.accept(MediaType.APPLICATION_JSON)), handler::delete)
				.andRoute(RequestPredicates.GET("/user/find/ByName")
						.and(RequestPredicates.accept(MediaType.APPLICATION_JSON)), handler::findByName)
				.andRoute(RequestPredicates.GET("/user/find/ByNameNot")
						.and(RequestPredicates.accept(MediaType.APPLICATION_JSON)), handler::findByNameNot)
				.andRoute(RequestPredicates.GET("/user/find/ByNameLike")
						.and(RequestPredicates.accept(MediaType.APPLICATION_JSON)), handler::findByNameLike)
				.andRoute(RequestPredicates.GET("/user/find/ByNameRegex")
						.and(RequestPredicates.accept(MediaType.APPLICATION_JSON)), handler::findByNameRegex);
	}
}
