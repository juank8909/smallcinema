# Small Cinema The fast and The furious

## Table of contents
- [Overview](#overview-)
- [Stack](#stack-)
- [Choices](#Choices-)
- [Some Assumptions](#some-assumptions-)
- [Local Setup](#local-setup-)
- [Testing](#testing-)
- [Next Steps](#next-steps-)

## Overview 📜

This is the backend intended to serve endpoints, domain logic and persistence capabilities 
to a small cinema which only plays  [Fast & Furious movies](https://en.wikipedia.org/wiki/Fast_%26_Furious)

## Stack 📚

This project uses Spring boot 2.5.x, PostgresQL 13.x, Java 11, Maven, Immutables 2.8.x, VAVR 0.10.4 and JOOQ 3.16.1

## Choices

- Regarding the design of this project, and since I followed the approach of functional programming, I used Java 11 and Vavr
to create less verbose code and better error processing using Either, Immutables in this case is a good choice since it offers
by itself everything needed to avoid object mutation and easier object creation/copy.

- Immutables are used at the domain layer since it is where the business logic happens and object creation from other objects
are more likely to happen, in the API layer, and the client, normal POJOs are used but without attribute setters, I implemented this
that way because I wanted to rely on springboot JSON serealization/deserealization which is not easy to do with Immutables 
since a default constructor is not supported.
  
- Jooq was used not by connecting to the Database directly to build the objects but relying on a DDL that is located in the resources folder,
I did this because as a next step I would consider to use Flyway or some other Database migrator.
  
- For API documentation I used SpringFox which is available [here](http://localhost:8080/swagger-ui.html) after local setup.

- When developing integration tests I used TestContainers to create a postgres instance which relies on the same script 
provided in the resources folder to create the initial schema, after Flyway is implemented I would also use this migration 
  feature to provide records in the database to test many other cases and being applied as a migration.
  
## Some assumptions

- I assumed that all movies are already part of the database(no more movies are going to be released), no endpoint for movie creation is developed yet.
- Consuming the OMDb Api is just a pass through call, to retrieve extra info for a movie, no domain layer is implemented 
for this code, since is not something that is part of the bussiness logic (updating prices and show times, rating a movie),
  logic of a small cinema.

## Local setup

Please be sure, you have maven installed in your computer.

After cloning the project, please provide these configurations on the /resources/application.yaml
````yaml
spring:
  config.activate.on-profile: local
  datasource:
    driver-class-name: org.postgresql.Driver
    url: [your-database-url] #example: jdbc:postgresql://localhost:5432/postgres
    username: [your-database-username] # example: postgres
    password: [your-database-password] #example: mysecretpassword
resource:
  OMDb-movies-url: http://www.omdbapi.com/?apikey=
  OMDb-apiKey: [your-OMDb-apiKey]
````

if no postgresSql is running in your local machine you can use a dockerized one like [this one](https://hub.docker.com/_/postgres)
to make it work I used next command:

````bash
docker run --name some-postgres -p 5432:5432 -e POSTGRES_PASSWORD=mysecretpassword -d postgres
````

I know this could be improved, but since I do not have flyway implemented we need to create the database schema by hand,
to achieve this, let's run `/resources/db/migration/initial_schema.sql` in your local database.

after everything is set up, let's run the app with:
````bash
mvn spring-boot:run -Dspring-boot.run.profiles=local
````

It should be up and running.

## Testing

I implemented integration test and unit tests just for demonstrating my approach, no full coverage is supported, in order 
to execute them let's run:
````bash
mvn test -DOMDB_API_KEY=[provide an OMDb apiKey]
````

## Next steps

- Definitely provide Database migration using Flyway or Liquidbase.
- After Database migration support, improve integration test to also support those migrations.
- Implement and endpoint for Movie creation, in case a new movie is released.
- Dockerized the application.
- Use Helm Charts to provide configuration for different environments.










