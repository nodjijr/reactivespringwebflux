# Sample Reactive - Spring Boot application

The purpose of this project is to demonstrate how we can
use [Spring WebFlux](https://docs.spring.io/spring/docs/current/spring-framework-reference/web-reactive.html) to create
a simple reactive web application.

This project uses [MongoDB](https://www.mongodb.com/) with resultset.

# How to build and run

project can be compiled with JDK 8 and above `javac`.

To compile just do `gradle build`.

## Prerequisites

* JAVA 8 should be installed
* MongoDB should be up and running at : <localhost:9042>

To run the application execute the following:

```
java -jar target/reactivespringwebflux*.jar
```

for more detailed technical information please check my
post : <https://dassum.medium.com/building-a-reactive-restful-web-service-using-spring-boot-and-postgres-c8e157dbc81d>

The server will start at <http://localhost:8080>.

## Exploring the Rest APIs

The application contains the following REST APIs

```
1. GET /users - Get All Users

2. POST /users/create - To create a User
rId} - Delete an User


```

It contain a sample WebClient to retrieve data from our User Management application.


