# Concrete Challenge - Java Academy

## Table of Contents
- [Introduction](#introduction)
- [Technologies](#technologies)
- [How to Run the project](#how-to-run-the-project)
    - [Getting Started](#getting-started)
    - [Running the Application Locally](#running-the-application-locally)
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

### Running the Application Locally

There are two different ways to run the application:

- By your favorite IDE just run the ConcreteApplication class.

- By the terminal just run the command:
```
mvn spring-boot:run -Dspring.profiles.active=test
```

By default, the API will be available at http://localhost:8080/users

## How to use the API

The API is hosted in the url: https://priscilasanfer.herokuapp.com/

First you need to create a user sending a POST request to endpoint '/users', this request does not require authentication.
Then if you want to use the API to delete, search or update a user, it will be necessary  to authenticate.
To authenticate you need to send a POST request to endpoint '/auth' then use the returned token in the others requests.


## Available Routes

| Routes       | Description               | HTTP Methods | Requires Authentication? |
|--------------|---------------------------|--------------|--------------------------|
|/users        | register a new user       | POST         | NO                       |
|/users/{id}   | get a user  by the id     | GET          | YES                      |
|/auth         | list all the users        | POST         | NO                       |
|/users/{id}   | delete a user by id       | DELETE       | YES                      |

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
- I had a problem identifying the test classes using the gradle, so I decided to switch to Maven due to the project's lead time.
- I still need to learn how to test void methods.
- Fix 'shouldReturnUserWithValidId' test to return the correct HTTP status.
- Fix 'shouldCreateUser' test to save the user correctly
- Add more tests in oder to have 100% of coverage.
- Generate token when creating a new user.
- Return decrypted password in the response body. 

