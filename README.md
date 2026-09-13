# Employee Management API

A RESTful Employee Management API developed using Spring Boot. This project is created as a learning project to practice backend development, CRUD operations, database integration, validation, security, and API documentation.

## Features

- Create a new employee
- Retrieve all employees
- Retrieve an employee by ID
- Update employee details
- Delete an employee
- Request validation
- Database integration using Spring Data JPA
- H2 database for development
- HTTP Basic Authentication using Spring Security
- Swagger/OpenAPI documentation
- Layered architecture using Controller, Service, Repository, Entity, and Request classes

## Technologies Used

-------------------------------------------------------
| Technology        | Purpose                         |
|-------------------|---------------------------------|
| Java 21           | Programming Language            |
| Spring Boot       | Backend Framework               |
| Spring Data JPA   | Database Operations             |
| Spring Security   | Authentication                  |
| H2 Database       | Development Database            |
| Maven             | Build and Dependency Management |
| Swagger / OpenAPI | API Documentation               |
| Bean Validation | Request Validation                |
-------------------------------------------------------

## Project Structure

```text
employee-management-api
│
├── src
│   ├── main
│   │   ├── java
│   │   │   └── com.love2code.springboot.employees
│   │   │       ├── controller
│   │   │       ├── dao
│   │   │       ├── entity
│   │   │       ├── request
│   │   │       ├── security
│   │   │       └── service
│   │   │
│   │   └── resources
│   │       └── application.properties
│   │
│   └── test
│
├── pom.xml
├── .gitignore
└── README.md
```

## API Documentation

Swagger/OpenAPI is integrated into the application to make it easier to explore and test the REST endpoints.

After starting the application, open:

```text
http://localhost:8080/swagger-ui/index.html
```

## Authentication

The application uses Spring Security with HTTP Basic Authentication.

The administrator username and password are configured through environment variables:

```text
ADMIN_USERNAME
ADMIN_PASSWORD
```

Default values are provided for local development.

## Database

The project uses an H2 database during development.

Database-related configuration can be found in:

```text
src/main/resources/application.properties
```

Local database files are excluded from Git using `.gitignore`.

## How to Run

### 1. Clone the repository

```bash
git clone https://github.com/khushigarg009/employee-management-api.git
```

### 2. Open the project

Open the project in IntelliJ IDEA.

### 3. Build the project

Using Maven:

```bash
./mvnw clean install
```

On Windows:

```bash
mvnw.cmd clean install
```

### 4. Run the application

Run the main application class:

```text
EmployeesApplication.java 
```

The application will start at:

```text
http://localhost:8080
```

## Learning Objectives

This project helped me practice the following concepts:

- Spring Boot application structure
- REST API development
- CRUD operations
- Spring Data JPA
- Repository and service layers
- Request validation
- Spring Security
- HTTP Basic Authentication
- H2 database
- Swagger/OpenAPI
- Maven project management

## Future Improvements

Some improvements that can be added in future versions:

- Global exception handling
- More comprehensive unit and integration tests
- MySQL database integration
- Role-based authorization
- Improved API response structure
- Pagination and sorting
- Frontend integration

## Author
**Khushi Garg**
