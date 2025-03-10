# API RESTFul with SOLID principles, CQRS pattern and Clean Architecture

This project is a simple example of how to implement a CQRS architecture with Spring Boot and H2 Database embedded.

### Technologies

- Java 17+
- Spring Boot 3.3.8
- H2 Database
- MapStruct

### How to run

1. Run the command `mvn clean install` to build the project.
2. Run the command `$env:SPRING_PROFILES_ACTIVE="test"` to set the environment variable.
   (For Windows, use the command `set SPRING_PROFILES_ACTIVE=test`)
   (For Linux, use the command `export SPRING_PROFILES_ACTIVE=test`)
3. Run the command `mvn spring-boot:run` to start the application.
4. Access the URL `http://localhost:8085/swagger-ui/index.html` to see the API documentation.

### API

The API documentation is available at `http://localhost:8085/v3/api-docs`.

List of available endpoints:

1. Create a new user
2. Get all users
3. Get user by ID
4. Update user
5. Delete user

* Note: The Postman collection is available in the `postman` folder with the name `cqrs-clean-architecture-mapstruct-jpa.postman_collection.json` and Swagger.json is available with the name `swagger.json`.

### Author

- **Raul Bolivar Navas** - [rasysbox](https://github.com/raulrobinson/cqrs-clean-architecture-mapstruct-jpa)

### License

This project is licensed under the MIT License - see the [LICENSE](https://www.apache.org/licenses/LICENSE-2.0) file for details.