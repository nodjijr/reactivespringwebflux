package com.arthuro.reactivespringwebflux.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.ReactiveMongoDatabaseFactory;
import org.springframework.data.mongodb.ReactiveMongoTransactionManager;
import org.springframework.data.mongodb.config.AbstractReactiveMongoConfiguration;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;
import org.springframework.data.mongodb.gridfs.ReactiveGridFsTemplate;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;

import com.mongodb.reactivestreams.client.MongoClient;
import com.mongodb.reactivestreams.client.MongoClients;

@Configuration
@EnableReactiveMongoRepositories(basePackages = "com.arthuro.reactivespringwebflux.repository")
public class ReactiveDBConfiguration extends AbstractReactiveMongoConfiguration {
	
	@Value("${arthuro.mongodb.replicaset.name}")
	private String replicasetName;

	@Value("${arthuro.mongodb.replicaset.username}")
	private String replicasetUsername;
	@Value("${arthuro.mongodb.replicaset.password}")
	private String replicasetPassword;

	@Value("${arthuro.mongodb.replicaset.primary}")
	private String replicasetPrimary;

	@Value("${arthuro.mongodb.replicaset.port}")
	private String replicasetPort;

	@Value("${arthuro.mongodb.replicaset.database}")
	private String database;

	@Value("${arthuro.mongodb.replicaset.authentication-database}")
	private String replicasetAuthenticationDb;
	  @Autowired
	    private MappingMongoConverter mongoConverter; 
	@Override
	public MongoClient reactiveMongoClient() {

		return MongoClients.create("mongodb://" + replicasetUsername + ":" + replicasetPassword + "@"
				+ replicasetPrimary + ":" + replicasetPort + "/" + database + "?replicaSet=" + replicasetName
				+ "&authSource=" + replicasetAuthenticationDb);

	}

	@Override
	protected String getDatabaseName() {
		return database;
	}

	@Bean
	public ReactiveMongoTemplate reactiveMongoTemplate() {
		return new ReactiveMongoTemplate(reactiveMongoClient(), getDatabaseName());
	}

	@Bean
	ReactiveMongoTransactionManager transactionManager(ReactiveMongoDatabaseFactory factory) {
		return new ReactiveMongoTransactionManager(factory);
	}
	  @Bean
	    public ReactiveGridFsTemplate reactiveGridFsTemplate() throws Exception {
	        return new ReactiveGridFsTemplate(reactiveMongoDbFactory(), mongoConverter);
	    }
}
