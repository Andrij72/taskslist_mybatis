# ⚡🌱 TasksList_mybatis 🌀 Application


A Spring Boot-based Task Management REST API with JWT authentication, role-based access control, MyBatis for data mapping, and Liquibase for database migrations.

---

## 📌 Project Overview

This application allows users to manage tasks, assign tasks to users, and track task status.  
It implements role-based access control, JWT authentication, and demonstrates clean separation of layers:

Controller → Service → Repository → DB

markdown
Copy code

**Features:**

- User management (CRUD operations)
- Task management (CRUD operations)
- Task assignment to users
- JWT-based authentication and authorization
- Role-based access (`ROLE_USER`, `ROLE_ADMIN`)
- Centralized exception handling
- OpenAPI / Swagger documentation
- Database migrations using Liquibase
- Optional Redis caching support

---

## 🗂️ Project Structure

    src/main
    ├── java/com/akul/taskslist
    │ ├── config # Spring and security configurations
    │ ├── domain # Entities, roles, exceptions
    │ ├── repository # Interfaces and implementations (MyBatis)
    │ ├── service # Business logic
    │ └── web
    │ ├── controller # REST controllers (AuthController, UserController, TaskController)
    │ ├── dto # Data Transfer Objects
    │ ├── mappers # MapStruct mappers
    │ └── security # JWT and security expressions
    └── resources
    ├── application.yaml
    ├── schema.sql
    ├── demo_data.sql
    └── liquibase
    └── db.changelog.yaml

---

## ⚡ Technology Stack

- **Spring Boot 3.x**
- **Java 17**
- **PostgreSQL / MySQL**
- **MyBatis** for SQL mapping
- **Liquibase** for database migrations
- **Spring Security + JWT** for authentication and authorization
- **OpenAPI / Swagger** for API documentation
- **Lombok** for boilerplate reduction
- **MapStruct** for DTO ↔ Entity mapping
- **Redis** (optional) for caching

---

## 🔐 Security

JWT-based authentication with role-based access control.

**_Note:_** The following method-level security examples using `@PreAuthorize` are implemented in my other project **Tasklist**:
```java
@PreAuthorize("@securityExpressionRoot.canAccessUser(#id)")
public User getUserById(Long id) { ... }

@PreAuthorize("@securityExpressionRoot.canAccessTask(#taskId)")
public Task getTaskById(Long taskId) { ... }
Only admins or task owners can access certain resources.
```

 
---
## 💾 Database & Migrations

 - Supports PostgreSQL and MySQL

- Schema: src/main/resources/schema.sql

- Sample data: src/main/resources/demo_data.sql

- Liquibase migrations: src/main/resources/liquibase/db.changelog.yaml

Example changesets: src/main/resources/changesets

---
## 🚀 Running the Application
Clone the repository:

```bash
git clone https://github.com/Andrij72/taskslist_mybatis.git
cd taskslist_mybatis
```

Configure _application.yaml_ with your database credentials.

Run the application via Maven:

```bash
mvn spring-boot:run
```

Access **Swagger UI** for API documentation:

http://localhost:8080/swagger-ui/index.html

---
## 📦 API Endpoints

**Auth Controller**

 - POST /api/v1/auth/login – Login with username & password → returns JWT

 - POST /api/v1/auth/register – Register a new user → returns created UserDto

 - POST /api/v1/auth/refresh – Refresh JWT token

**User Controller**

- GET /api/v1/users/{id} – Get user by ID

- PUT /api/v1/users/{id} – Update user information

- DELETE /api/v1/users/{id} – Delete user

- GET /api/v1/users/{id}/tasks – Get all tasks assigned to user

- POST /api/v1/users/{id}/tasks – Create task for user

**Task Controller**

- GET /api/v1/tasks/{id} – Get task by ID

- PUT /api/v1/tasks – Update task

- DELETE /api/v1/tasks/{id} – Delete task
---
## 💡 Notes
- Passwords are encoded with BCrypt.

- Method security ensures that users can only access their own tasks unless they are admins.

- Centralized exception handling via @RestControllerAdvice returns consistent error responses for validation, authentication, and access control failures.

- Redis caching is optional and configurable.