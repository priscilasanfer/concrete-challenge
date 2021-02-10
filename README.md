# Concrete Challenge - Java Academy

## Table of Contents
- [Introduction](#introduction)
- [Technologies](#technologies)
- [How to Run the project](#how-to-run-the-project)
    - [Getting Started](#getting-started)
    - [Running the Application](#running-the-application)
- [How to use the API](#how-to-use-the-api)
    - [Available Routes](#available-routes)
    - [Postman Requests](#postman-requests)
- [TODO](#todo)
  
## Introduction
An API that manage user, it allows to:
- Create a user
- Get a specific user
- List all user
- Delete a specific user
- Authenticate 

## Technologies
What was used:
- **Java 8**
- **Maven** to resolve dependency.
- **SpringBoot** to create stand-alone, production-grade Spring based Applications.
- **PostgreSQL** to store data.
- **Spring's MockMvc**, **Mockito** and **JUnit4** to write the tests.
- **Heroku** to host the application

## How to Run the project
### Getting Started

To get started, you should have  **Maven** and **Java** installed. 

To verify if you already have maven, run:

```
$ mvn --version 
```

To verify if you already have java, run:

```
$ mvn -version 
```

Clone the repository **[concrete-challenge](https://github.com/priscilasanfer/concrete-challenge)**:

```
$ git clone https://github.com/priscilasanfer/concrete-challenge.git
```
In your JAVA IDE, import the application as a maven project. As reference, this project was developed using **[IntelliJ IDEA](https://www.jetbrains.com/idea/)**.

### Running the Application

There are two different ways to run the application:

- By your favorite IDE just run the ConcreteApplication class.

- By the terminal just run the command:
```
mvn spring-boot:run -Dspring.profiles.active=test
```

By default, the API will be available at http://localhost:8080/users

## Available Routes

| Routes                 | Description                          | HTTP Methods |
|------------------------|--------------------------------------|--------------|
|/users                  | register a new user                  | POST         |
|/users/{id}             | get a user  by the id                | GET          |
|/auth                   | list all the users                   | POST         |
|/users/{id}             | delete a user by id                  | DELETE       | 

## Postman Requests

- Create a user
```
curl --location --request POST 'http://localhost:8080/users' \
--header 'Content-Type: application/json' \
--data-raw '{
    "name": "João da Silva",
    "email": "joao@silva.org",
    "password": "hunter2",
    "phones": [
        {
            "number": "987654321",
            "ddd": "21"
        }
    ]
}'
```

- Authenticate 

```
curl --location --request POST 'http://localhost:8080/auth' \
--header 'Content-Type: application/json' \
--header 'Cookie: JSESSIONID=CC99131122B79F01A9C9CAF8C7062298' \
--data-raw '{
    "email": "joao@silva.org",
    "password": "hunter2"
}'
```

- Find All user

```
curl --location --request GET 'http://localhost:8080/users' \
--header 'Authorization: Bearer XXXXXXXX' \
--header 'Content-Type: application/json' \
--data-raw '{
    "name": "João da Silva",
    "email": "joao@silva.org",
    "password": "hunter2",
    "phones": [
        {
            "number": "987654321",
            "ddd": "21"
        }
    ]
}'
```

- Find a user by id

```
curl --location --request GET 'http://localhost:8080/users/1' \
--header 'Authorization: Bearer XXXXXXX' \
--header 'Content-Type: application/json' \
--data-raw ''
```

- Delete user by id

```
curl --location --request DELETE 'http://localhost:8080/users/1' \
--header 'Authorization: Bearer XXXXXXX' \
--data-raw ''
```

### TODO
- I faced a problem with gradle to identify the tests class, so I decided to change to Maven due the time to finish the project 
- For some reason I couldn't fix the relationship between the User and Phones entities, and because of that I couldn't associate the phone to its user.
I also tried to associate both before saving it but was useless.
- I need to learn how to test void methods in order to test the deleteById feature
- Fix test shouldReturnUserWithValidId to return the correct HTTP status
- Fix test shouldCreateUser to save the user correctly
- Add more tests in oder to have 100% of coverage
- Generate token when creating a new user
- Return decrypted password in the response. 

