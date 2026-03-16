# Task Management REST API

A production-ready RESTful API for task management, built with Spring Boot. Supports JWT-based authentication, full CRUD operations on tasks and users, and is containerized with Docker for deployment to AWS EC2.

---

## Tech Stack

| Layer | Technology |
|---|---|
| Framework | Spring Boot 3.x |
| Language | Java 17 |
| Database | PostgreSQL |
| ORM | JPA / Hibernate |
| Auth | JWT (JSON Web Tokens) |
| Containerization | Docker / Docker Compose |
| Deployment | AWS EC2 |
| Build Tool | Maven |

---

## Features

- **JWT Authentication** — Stateless auth with token-based login and protected endpoints
- **Full CRUD** — Create, read, update, and delete tasks with user-level ownership
- **JPA/Hibernate** — Named queries for type-safe data access; lazy loading to avoid N+1 query issues
- **Dockerized** — Multi-container setup via Docker Compose (app + PostgreSQL)
- **EC2 Deployment** — Runs on AWS EC2 with environment-based config for prod/dev separation
- **RESTful Design** — Clean resource-oriented endpoints with appropriate HTTP semantics

---

## API Endpoints

### Auth
| Method | Endpoint | Description |
|---|---|---|
| `POST` | `/api/auth/register` | Register a new user |
| `POST` | `/api/auth/login` | Login and receive a JWT |

### Tasks
| Method | Endpoint | Description | Auth Required |
|---|---|---|---|
| `GET` | `/api/tasks` | Get all tasks for authenticated user | ✅ |
| `GET` | `/api/tasks/{id}` | Get task by ID | ✅ |
| `POST` | `/api/tasks` | Create a new task | ✅ |
| `PUT` | `/api/tasks/{id}` | Update a task | ✅ |
| `DELETE` | `/api/tasks/{id}` | Delete a task | ✅ |

---

## Getting Started

### Prerequisites

- Java 17+
- Maven 3.8+
- Docker & Docker Compose
- PostgreSQL (if running locally without Docker)

---

### Option 1 — Run with Docker Compose (Recommended)

```bash
git clone https://github.com/Soumya98-dev/task-management-api.git
cd task-management-api
docker-compose up --build
```

The API will be available at `http://localhost:8080`.

---

### Option 2 — Run Locally

1. **Configure the database** in `src/main/resources/application.properties`:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/taskdb
spring.datasource.username=your_username
spring.datasource.password=your_password
spring.jpa.hibernate.ddl-auto=update

app.jwt.secret=your_jwt_secret
app.jwt.expiration=86400000
```

2. **Build and run:**

```bash
mvn clean install
mvn spring-boot:run
```

---

## Project Structure

```
src/
├── main/
│   ├── java/com/taskapi/
│   │   ├── controller/       # REST controllers
│   │   ├── service/          # Business logic
│   │   ├── repository/       # JPA repositories with named queries
│   │   ├── model/            # JPA entity classes
│   │   ├── dto/              # Request/response DTOs
│   │   ├── security/         # JWT filter, UserDetailsService, config
│   │   └── exception/        # Global exception handling
│   └── resources/
│       └── application.properties
├── test/                     # Unit and integration tests
Dockerfile
docker-compose.yml
```

---

## Docker Setup

**Dockerfile** builds a lightweight JAR image. **docker-compose.yml** wires the Spring Boot app to a PostgreSQL container.

Key environment variables:

| Variable | Description |
|---|---|
| `SPRING_DATASOURCE_URL` | JDBC connection string |
| `SPRING_DATASOURCE_USERNAME` | DB username |
| `SPRING_DATASOURCE_PASSWORD` | DB password |
| `APP_JWT_SECRET` | Secret key for signing JWTs |

---

## Deployment — AWS EC2

1. SSH into your EC2 instance
2. Install Docker and Docker Compose
3. Clone the repo and set environment variables
4. Run `docker-compose up -d`

Ensure port `8080` is open in your EC2 Security Group inbound rules.

---

## Design Decisions

- **Lazy loading** on task-user relationships to avoid unnecessary joins on list queries
- **Named queries** defined on entity classes for reusability and compile-time safety
- **Stateless JWT** — no server-side session storage; scales horizontally without sticky sessions
- **DTO pattern** — entities never exposed directly; DTOs used for all request/response contracts

---

## License

MIT
