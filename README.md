# ECommerceWeb Application

## Overview

ECommerceWeb is a Spring Boot-based web application designed to provide a robust platform for managing e-commerce operations. It leverages Spring Boot's features for rapid development and includes JPA auditing for tracking entity changes.

## Features

- **User Management**: Manage user accounts, authentication, and authorization.
- **Product Management**: Add, update, and delete products with detailed information.
- **Order Management**: Handle customer orders and track their statuses.
- **JPA Auditing**: Automatically tracks entity creation and modification timestamps.
- **RESTful APIs**: Exposes APIs for integration with external systems.
- **Modular Design**: Easily extendable and maintainable codebase.

## Technologies Used

- **Backend**: Spring Boot, Spring Data JPA, Hibernate
- **Database**: MySQL/PostgreSQL
- **Build Tool**: Maven
- **Version Control**: Git
- **Testing**: JUnit, Mockito


## Prerequisites

- Java 17 or higher
- Maven 3.6+ or Gradle
- A relational database (e.g., MySQL, PostgreSQL)

## Setup Instructions


1. Clone the repository:

git clone https://github.com/vinayvyas21/ECommerceWeb

2. Add the required dependencies in the `pom.xml` file.

3. Configure the database connection in the `src/main/resources/application.properties` file:

4. Build the project:
	mvn clean install

5. Run the application:
	mvn spring-boot:run

6. Access the application:
   Open your browser and navigate to `http://localhost:8080`.
   
## License

This project is licensed under the MIT License. See the `LICENSE` file for details.

## Author

Developed by Vinay Kumar.