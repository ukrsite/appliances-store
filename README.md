# Demonstration Plan and Key Points for the Final Project "Appliance Store Test"

## 1. Introduction
- Presentation of the project and its objectives.
- Overview of the technologies used: Spring Boot, Spring Security, Spring JPA, H2 Database, Swagger, Docker, Azure, etc.
- Core idea of the project: simulating the operation of an online appliance store.

## 2. Functional Capabilities
### 2.1. User Roles
- **Administrator**:
  - Managing users (adding, editing, deleting).
  - Viewing all database tables.
  - Approving orders.
- **Employee**:
  - Viewing tables.
  - Adding, editing, deleting customers, products, and manufacturers.
  - Approving orders.
- **Customer**:
  - Registration and authentication.
  - Adding items to the shopping cart.
  - Creating, editing, and deleting orders.
- **Guest User**:
  - Viewing the available product catalog.

### 2.2. Core Features
- **Authentication and Authorization** (Spring Security, In-Memory Authentication, Dao AuthenticationProvider).
- **Database Interaction** (Spring JPA, H2 Database, JPA repositories, SQL data initialization).
- **Internationalization (i18n)** (translations of interface and content).
- **Input Data Validation** (Spring Validation).
- **API for working with customers, products, orders** (REST controllers, Swagger).
- **Error Handling** (GlobalExceptionHandlerAdvice).

## 3. Architectural Implementation
### 3.1. Multi-layered Architecture
- **Repository Layer**: Database interaction via JPA.
- **Service Layer**: Business logic.
- **Controller Layer**: REST interfaces and web controllers.
- **Caching** (option for cache extension to optimize queries).

### 3.2. Working with Database
- Use of **H2 Database** to store data.
- SQL scripts for initial database population (`manufacturer.sql`, `client.sql`, `employee.sql`, `appliance.sql`).
- Displaying relationships between entities (OneToMany, ManyToOne).

### 3.3. Extended Functionality
- **Shopping Cart (Cart Service/Controller)** for managing customer products.
- **Pagination and Sorting** for viewing product and order lists.
- **Logging** (setting up logging levels).
- **Dockerization** (Docker, docker-compose).

## 4. System Demonstration
### 4.1. Project Launch
- Starting the project via `docker-compose`.
- Checking the connection to the H2 Database.
- Logging into the system with different user roles.

### 4.2. Viewing Tables
- Viewing product, customer, and order lists.
- Adding, editing, and deleting records.

### 4.3. Placing an Order
- Adding items to the shopping cart.
- Confirming the order.
- Viewing order history.

### 4.4. API Testing
- Using **Swagger** to test REST controllers.
- Performing CRUD operations through Postman or Swagger UI.

## 5. Conclusion and Future Expansion Opportunities
- **Possible future extensions**:
  - Adding email notifications.
  - Integration with a payment system.
  - Using a fully relational database (PostgreSQL, MySQL).

- **Summary of the work completed**:
  - Implementation of all basic functions.
  - Ensuring security through Spring Security.
  - Implementation of international support.
  - Optimizing interaction through caching and logging.
